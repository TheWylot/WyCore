package ir.wy.wycore.spigot.utils;

import lombok.Getter;

@Getter
public class Placeholder {

    private final String value;
    private final String key;

    public Placeholder(String key, String value) {
        this.key = "%" + key + "%";
        this.value = value;
    }

    public String process(String line) {
        if (line == null || line.isEmpty()) return "";
        return line.replace(key, value);
    }
}
