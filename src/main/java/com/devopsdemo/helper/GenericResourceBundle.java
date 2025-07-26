package com.devopsdemo.helper;

import java.util.ResourceBundle;

public class GenericResourceBundle {
    private static final String DEFAULT_BUNDLE = "messages";
    private static final String DEFAULT_LANGUAGE = "en";

    public static String getProperties(String source) {
        if (source == null || source.trim().isEmpty()) {
            throw new IllegalArgumentException("Source key cannot be null or empty");
        }

        try {
            var rb = ResourceBundle.getBundle(DEFAULT_BUNDLE);
            String result = rb.keySet().stream()
                    .filter(key -> key.equalsIgnoreCase(source))
                    .findFirst()
                    .map(rb::getString)
                    .orElse(null);
            if (result == null) {
                throw new IllegalArgumentException("Key not found: " + source);
            }
            return result;
        } catch (java.util.MissingResourceException e) {
            throw new IllegalArgumentException("Could not load properties: " + source, e);
        }
    }

    public static ResourceBundle getBundle(String baseName, java.util.Locale locale) {
        if (baseName == null || baseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Base name cannot be null or empty");
        }
        if (locale == null) {
            throw new IllegalArgumentException("Locale cannot be null");
        }
        
        // Only allow "messages" resource bundle
        if (!DEFAULT_BUNDLE.equals(baseName)) {
            throw new IllegalArgumentException("Invalid resource bundle name: " + baseName);
        }

        // Only allow English locale for messages bundle
        if (!DEFAULT_LANGUAGE.equals(locale.getLanguage())) {
            throw new IllegalArgumentException("Unsupported locale for bundle " + baseName + ": " + locale);
        }

        try {
            return ResourceBundle.getBundle(baseName, locale);
        } catch (java.util.MissingResourceException e) {
            throw new IllegalArgumentException("Could not load properties: " + baseName, e);
        }
    }
}
