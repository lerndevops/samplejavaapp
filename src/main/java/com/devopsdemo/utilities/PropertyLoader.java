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

        if (loader == null) {
            loader = Thread.currentThread().getContextClassLoader();
        }

        Properties result = new Properties();
        boolean loaded = false;

        try {
            String resourcePath = names.endsWith(SUFFIX) ? names : names + SUFFIX;
            try (InputStream in = loader.getResourceAsStream(resourcePath)) {
                if (in != null) {
                    // Don't process keys when loading
                    result.load(in);
                    loaded = true;
                }
            }

            // If still not loaded and LOAD_AS_RESOURCE_BUNDLE is true, try as a resource bundle
            if (!loaded && LOAD_AS_RESOURCE_BUNDLE) {
                String bundleName = names.endsWith(SUFFIX) ? 
                    names.substring(0, names.length() - SUFFIX.length()) : names;
                bundleName = bundleName.replace('/', '.');
                try {
                    ResourceBundle rb = ResourceBundle.getBundle(bundleName, Locale.getDefault(), loader);
                    rb.keySet().forEach(key -> result.put(key, rb.getString(key)));
                    loaded = true;
                } catch (java.util.MissingResourceException e) {
                    LOG.debug("Resource bundle not found: {}", bundleName);
                }
            }
        } catch (Exception e) {
            LOG.debug("Error loading properties: {}", names, e);
        }

        if (!loaded) {
            throw new IllegalArgumentException("Could not load properties: " + names);
        }

        return result;
    }
}
