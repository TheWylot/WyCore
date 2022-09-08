package ir.wy.wycore.utils;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import ir.wy.wycore.DefaultFontInfo;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {

    private final static int CENTER_PX = 154;

    public static String color(String string) {
        return IridiumColorAPI.process(string);
    }

    public static List<String> color(List<String> strings) {
        return strings.stream()
                .map(StringUtils::color)
                .collect(Collectors.toList());
    }

    public static List<String> processMultiplePlaceholders(List<String> lines, List<Placeholder> placeholders) {
        return lines.stream()
                .map(s -> processMultiplePlaceholders(s, placeholders))
                .collect(Collectors.toList());
    }

    public static String processMultiplePlaceholders(String line, List<Placeholder> placeholders) {
        String processedLine = line;
        for (Placeholder placeholder : placeholders) {
            processedLine = placeholder.process(processedLine);
        }
        return color(processedLine);
    }

    public static String getCenteredMessage(String message, String buffer) {
        if (message == null || message.equals("")) return "";
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for (char c : message.toCharArray()) {
            if (c == '§') {
                previousCode = true;
            } else if (previousCode) {
                previousCode = false;
                if (c == 'l' || c == 'L') {
                    isBold = true;
                } else isBold = false;
            } else {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate) {
            sb.append(buffer);
            compensated += spaceLength;
        }
        return sb + message + sb;
    }

}
