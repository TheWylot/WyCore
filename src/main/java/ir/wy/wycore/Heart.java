package ir.wy.wycore;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

// Java Imports
import java.io.IOException;
import java.io.File;
import java.lang.reflect.Type;

public class Heart {

    private final ObjectMapper objectMapper;
    private final HeartType heartType;
    private final JavaPlugin javaPlugin;

    public Heart(HeartType heartType, JavaPlugin javaPlugin) {
        this.heartType = heartType;
        this.javaPlugin = javaPlugin;

        objectMapper = new ObjectMapper(heartType.getFactory()).configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
    }

    /**
     * The name of a provided class.
     * Used as the file name for persisting.
     *
     * @param clazz The class whose name is to be returned
     * @return The name of the configuration file
     */
    private static String getName(Class<?> clazz) {
        return clazz.getSimpleName().toLowerCase();
    }

    public static String getName(Object instance) {
        return getName(instance.getClass());
    }

    public static String getName(Type type) {
        return getName(type.getClass());
    }

    // ------------------------------------------------------------ //
    // GET FILE - In which file would we like to store this object?
    // ------------------------------------------------------------ //
    public File getFile(String name) {
        return new File(javaPlugin.getDataFolder(), name + heartType.getExtension());
    }

    public File getFile(Class<?> clazz) {
        return getFile(getName(clazz));
    }

    public File getFile(Object instance) {
        return getFile(getName(instance));
    }

    // SAVE
    public void save(Object instance) {
        save(instance, getFile(instance));
    }

    private void save(Object instance, File file) {
        try {
            objectMapper.writeValue(file, instance);
        } catch (IOException e) {
            javaPlugin.getLogger().severe("Failed to save " + file.toString() + ": " + e.getMessage());
            Bukkit.getPluginManager().disablePlugin(javaPlugin);
        }
    }

    public String toString(Object instance) {
        try {
            return objectMapper.writeValueAsString(instance);
        } catch (IOException e) {
            javaPlugin.getLogger().severe("Failed to save " + instance.toString() + ": " + e.getMessage());
            Bukkit.getPluginManager().disablePlugin(javaPlugin);
        }
        return "";
    }

    // LOAD BY CLASS
    public <T> T load(Class<T> clazz) {
        return load(clazz, getFile(clazz));
    }

    public <T> T load(Class<T> clazz, File file) {
        if (file.exists()) {
            try {
                return objectMapper.readValue(file, clazz);
            } catch (IOException e) {
                javaPlugin.getLogger().severe("Failed to parse " + file + ": " + e.getMessage());
                Bukkit.getPluginManager().disablePlugin(javaPlugin);
            }
        }
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a new instance of a class and deserializes the provided content.
     * Returns the new instance.
     *
     * @param clazz   The class which should be loaded
     * @param content The content which should be loaded into the class
     * @param <T>     The type of class
     * @return The loaded class. Might be null
     */
    public <T> T load(Class<T> clazz, String content) {
        try {
            return objectMapper.readValue(content, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(javaPlugin);
        }

        return null;
    }

    /**
     * Represents a data format which can be
     * used for persisting.
     */
    public enum HeartType {

        YAML(".yml", new YAMLFactory()),
        JSON(".json", new JsonFactory());

        private final String extension;
        private final JsonFactory factory;

        /**
         * The default constructor.
         *
         * @param extension The file extension with a leading full stop
         * @param factory   The JsonFactory which should be used for persisting
         */
        HeartType(String extension, JsonFactory factory) {
            this.extension = extension;
            this.factory = factory;
        }

        public String getExtension() {
            return extension;
        }

        public JsonFactory getFactory() {
            return factory;
        }

    }
}
