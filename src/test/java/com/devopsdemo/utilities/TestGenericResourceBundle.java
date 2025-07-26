
package com.devopsdemo.utilities;
import com.devopsdemo.helper.GenericResourceBundle;

import org.junit.jupiter.api.Test;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

class TestGenericResourceBundle {

    @Test
    void testGetBundle() {
        Locale locale = new Locale("en", "US");
        assertNotNull(GenericResourceBundle.getBundle("messages", locale));
    }

    @Test
    void testGetBundleInvalid() {
        Locale locale = new Locale("invalid", "INVALID");
        assertThrows(IllegalArgumentException.class, () -> GenericResourceBundle.getBundle("messages", locale));
    }
}
