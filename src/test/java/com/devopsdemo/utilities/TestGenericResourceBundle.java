
package com.devopsdemo.utilities;

import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.devopsdemo.helper.GenericResourceBundle;

public class TestGenericResourceBundle {
    @Test
    void testGetBundleWithNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            GenericResourceBundle.getBundle(null, Locale.getDefault());
        });
    }

    @Test
    void testGetBundleWithNullLocale() {
        assertThrows(IllegalArgumentException.class, () -> {
            GenericResourceBundle.getBundle("messages", null);
        });
    }

    @Test
    void testGetBundleNonexistent() {
        assertThrows(IllegalArgumentException.class, () -> {
            GenericResourceBundle.getBundle("nonexistent", Locale.getDefault());
        });
    }

    @Test
    void testGetBundleWithLocale() {
        // English locale should work
        Locale enLocale = new Locale("en", "US");
        assertDoesNotThrow(() -> {
            GenericResourceBundle.getBundle("messages", enLocale);
        });
        
        // Non-English locale should throw
        Locale frLocale = new Locale("fr", "FR");
        assertThrows(IllegalArgumentException.class, () -> {
            GenericResourceBundle.getBundle("messages", frLocale);
        });
    }

    @Test
    void testGetBundleInvalid() {
        Locale locale = new Locale("invalid", "INVALID");
        assertThrows(IllegalArgumentException.class, () -> GenericResourceBundle.getBundle("messages", locale));
    }

    @Test
    void testGetPropertiesWithNull() {
        assertThrows(IllegalArgumentException.class, () -> GenericResourceBundle.getProperties(null));
    }

    @Test
    void testGetPropertiesWithEmpty() {
        assertThrows(IllegalArgumentException.class, () -> GenericResourceBundle.getProperties(""));
    }

    @Test
    void testGetProperties() {
        assertThrows(IllegalArgumentException.class, () -> GenericResourceBundle.getProperties("testKey"));
    }
}
