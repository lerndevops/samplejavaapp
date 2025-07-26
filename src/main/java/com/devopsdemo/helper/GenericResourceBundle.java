package com.devopsdemo.helper;

import java.util.ResourceBundle;

public class GenericResourceBundle {
    public static String getProperties(String source) {
        var rb = ResourceBundle.getBundle("ResourceBundle");
        return rb.keySet().stream()
                 .filter(key -> key.equalsIgnoreCase(source))
                 .findFirst()
                 .map(rb::getString)
                 .orElse("");
    }

    public static ResourceBundle getBundle(String baseName, java.util.Locale locale) {
        try {
            return ResourceBundle.getBundle(baseName, locale);
        } catch (java.util.MissingResourceException e) {
            throw new IllegalArgumentException("Resource bundle not found: " + baseName, e);
        }
    }
}

