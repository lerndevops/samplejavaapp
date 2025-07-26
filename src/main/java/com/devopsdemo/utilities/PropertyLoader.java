package com.devopsdemo.utilities;

import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for loading properties from files or resource bundles.
 */
public class PropertyLoader {

    private static final boolean THROW_ON_LOAD_FAILURE = true;
    private static final boolean LOAD_AS_RESOURCE_BUNDLE = false;
    private static final String SUFFIX = ".properties";

    /**
     * Logger enabled for the current class
     */
    private static final Logger LOG = LoggerFactory.getLogger(PropertyLoader.class);

    /**
     * Loads properties using the current thread's context classloader.
     *
     * @param name the name of the properties file
     * @return the loaded properties
     */
    public static Properties loadProperties(final String name) {
        return loadProperties(name, Thread.currentThread().getContextClassLoader());
    }

    /**
     * Loads properties from a file or resource bundle.
     *
     * @param names  the name of the properties file
     * @param loader the classloader to use
     * @return the loaded properties
     */
    public static Properties loadProperties(String names, ClassLoader loader) {
        if (names == null) {
            throw new IllegalArgumentException("null input: name");
        }

        String name = names.startsWith("/") ? names.substring(1) : names;
        name = name.endsWith(SUFFIX) ? name.substring(0, name.length() - SUFFIX.length()) : name;

        Properties result = new Properties();
        boolean loaded = false;

        try {
            if (LOAD_AS_RESOURCE_BUNDLE) {
                name = name.replace('/', '.');
                try {
                    ResourceBundle rb = ResourceBundle.getBundle(name, Locale.getDefault(),
                            loader != null ? loader : ClassLoader.getSystemClassLoader());
                    rb.keySet().forEach(key -> result.put(key, rb.getString(key)));
                    loaded = true;
                } catch (java.util.MissingResourceException e) {
                    throw new IllegalArgumentException("Resource bundle not found: " + name, e);
                }
            } else {
                name = name.replace('.', '/');
                if (!name.endsWith(SUFFIX)) {
                    name = name.concat(SUFFIX);
                }

                try (InputStream in = loader != null ? loader.getResourceAsStream(name) : null) {
                    if (in != null) {
                        result.load(in);
                        loaded = true;
                    }
                }
            }
        } catch (Exception e) {
            LoggerStackTraceUtil.printErrorMessage(e);
        }

        if (!loaded) {
            throw new IllegalArgumentException("Could not load properties: " + name);
        }

        return result;
    }
}
