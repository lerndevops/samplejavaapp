
package com.devopsdemo.utilities;
        assertThrows(IllegalArgumentException.class, () -> {
            GenericResourceBundle.getBundle(null);
        });
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

class TestGenericResourceBundle {
        assertThrows(IllegalArgumentException.class, () -> {
            GenericResourceBundle.getBundle("nonexistent");
        });
        Locale locale = new Locale("en", "US");
        assertNotNull(GenericResourceBundle.getBundle("messages", locale));
    }

    @Test
    void testGetBundleInvalid() {
        Locale locale = new Locale("invalid", "INVALID");
        assertThrows(IllegalArgumentException.class, () -> GenericResourceBundle.getBundle("messages", locale));
    }
}
