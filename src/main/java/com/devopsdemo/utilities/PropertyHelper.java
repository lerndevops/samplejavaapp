package com.devopsdemo.utilities;

import java.util.HashMap;
import java.util.Properties;

/**
 * Helper Class to load Properties from a property file to be passed to caller for execution.
 * Multiple properties can be loaded.
 * Note that if the same property is specified multiple times in a single file, there is no guaranteed "Winner".
 * Also note in the case of loading multiple files and duplicate definition of properties across files,
 * the last loaded property file "wins".
 * The getProperty()/get() methods also return "" silently if no such query exists.
 */
public final class PropertyHelper {

    /**
     * hMapProperties contains the hashmap of key/value pairs associated with each property
     */
    protected static final HashMap<String, Object> HMAPPROPERTIES = new HashMap<>();

    /**
     * Loads properties from a file into a HashMap.
     *
     * @param propertyFile the property file to load
     * @return a HashMap containing the properties
     */
    public static HashMap<String, Object> loadProperties(String propertyFile) {
        var properties = PropertyLoader.loadProperties(propertyFile);
        properties.stringPropertyNames().forEach(
            key -> HMAPPROPERTIES.put(key, properties.getProperty(key))
        );
        return HMAPPROPERTIES;
    }

    /**
     * Retrieves the value of a property by name.
     *
     * @param propertyName the name of the property
     * @return the value of the property, or an empty string if not found
     */
    public static String getProperty(String propertyName) {
        try {
            return (String) HMAPPROPERTIES.getOrDefault(propertyName, "");
        } catch (Exception e) {
            LoggerStackTraceUtil.printErrorMessage(e);
            return "";
        }
    }

    /**
     * Retrieves the value of a property by name, or a default value if the property is not found.
     *
     * @param propertyName the name of the property
     * @param strDefault   the default value to return if the property is not found
     * @return the value of the property, or the default value if not found
     */
    public static String getProperty(String propertyName, String strDefault) {
        try {
            return (String) HMAPPROPERTIES.getOrDefault(propertyName, strDefault);
        } catch (Exception e) {
            LoggerStackTraceUtil.printErrorMessage(e);
            return strDefault;
        }
    }

    /**
     * A convenience method (aliasing getProperty).
     *
     * @param propertyName the property to be retrieved
     * @return the value of the property
     */
    public static String get(String propertyName) {
        return getProperty(propertyName);
    }
}

