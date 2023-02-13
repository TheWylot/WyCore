package ir.wy.wycore.spigot.utils;


import org.bukkit.ChatColor;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextUtils {
    private static final List<Charset> supportedCharsets = new ArrayList<>();

    static {
        supportedCharsets.add(StandardCharsets.UTF_8);
        supportedCharsets.add(StandardCharsets.ISO_8859_1);

        try {
            supportedCharsets.add(Charset.forName("windows-1253"));
            supportedCharsets.add(Charset.forName("ISO-8859-7"));
        } catch (Exception ignore) {
        }

        supportedCharsets.add(StandardCharsets.US_ASCII);
    }

    public static String formatText(String text) {
        return formatText(text, false);
    }

    public static String formatText(String text, boolean capitalize) {
        if (text == null || text.equals("")) {
            return "";
        }

        if (capitalize) {
            text = text.substring(0, 1).toUpperCase() + text.substring(1);
        }

        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> formatText(List<String> list) {
        return list.stream()
                .map(TextUtils::formatText)
                .collect(Collectors.toList());
    }

    public static List<String> formatText(String... list) {
        return Arrays.stream(list)
                .map(TextUtils::formatText)
                .collect(Collectors.toList());
    }

    public static List<String> wrap(String line) {
        return wrap(null, line);
    }

    public static List<String> wrap(String color, String line) {
        if (color != null) {
            color = "&" + color;
        } else {
            color = "";
        }

        List<String> lore = new ArrayList<>();
        int lastIndex = 0;
        for (int n = 0; n < line.length(); ++n) {
            if (n - lastIndex < 25) {
                continue;
            }

            if (line.charAt(n) == ' ') {
                lore.add(TextUtils.formatText(color + TextUtils.formatText(line.substring(lastIndex, n))));
                lastIndex = n;
            }
        }

        if (lastIndex - line.length() < 25) {
            lore.add(TextUtils.formatText(color + TextUtils.formatText(line.substring(lastIndex))));
        }

        return lore;
    }

    public static String convertToInvisibleLoreString(String s) {
        if (s == null || s.equals("")) {
            return "";
        }

        StringBuilder hidden = new StringBuilder();

        for (char c : s.toCharArray()) {
            hidden.append(ChatColor.COLOR_CHAR)
                    .append(';')
                    .append(ChatColor.COLOR_CHAR)
                    .append(c);
        }

        return hidden.toString();
    }


    public static Charset detectCharset(File f, Charset def) {
        byte[] buffer = new byte[2048];
        int len;

        try (FileInputStream input = new FileInputStream(f)) {
            len = input.read(buffer);
        } catch (Exception ex) {
            return null;
        }

        return len != -1 ? detectCharset(buffer, len, def) : def;
    }

    public static Charset detectCharset(BufferedInputStream reader, Charset def) {
        byte[] buffer = new byte[2048];
        int len;

        try {
            reader.mark(2048);
            len = reader.read(buffer);

            reader.reset();
        } catch (Exception ex) {
            return null;
        }

        return len != -1 ? detectCharset(buffer, len, def) : def;
    }

    public static Charset detectCharset(byte[] data, int len, Charset def) {
        if (len > 4) {
            if (data[0] == (byte) 0xFF && data[1] == (byte) 0xFE) {
                return StandardCharsets.UTF_16LE;
            } else if (data[0] == (byte) 0xFE && data[1] == (byte) 0xFF) {
                return StandardCharsets.UTF_16BE;
            } else if (data[0] == (byte) 0xEF && data[1] == (byte) 0xBB && data[2] == (byte) 0xBF) { // UTF-8 with BOM
                return StandardCharsets.UTF_8;
            }
        }

        int newLen = len;
        for (; newLen > 0; --newLen) {
            if (Character.isWhitespace(data[newLen - 1])) break;
        }

        if (len > 512 && newLen < 512) {
            newLen = len;
        }

        ByteBuffer bBuff = ByteBuffer.wrap(data, 0, newLen).asReadOnlyBuffer();
        for (Charset charset : supportedCharsets) {
            if (charset != null && isCharset(bBuff, charset)) {
                return charset;
            }

            bBuff.rewind();
        }

        return def;
    }

    public static boolean isCharset(ByteBuffer data, Charset charset) {
        CharsetDecoder decoder = charset.newDecoder();
        decoder.onMalformedInput(CodingErrorAction.REPORT);
        decoder.onUnmappableCharacter(CodingErrorAction.REPORT);

        return decoder.decode(data, CharBuffer.allocate(data.capacity()), true).isUnderflow();
    }
}
