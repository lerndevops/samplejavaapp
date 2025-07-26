package com.devopsdemo.utilities;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

class TestPropertyHelper {

    @Test
    void testLoadProperties() {
        HashMap<String, Object> properties = PropertyHelper.loadProperties("test.properties");
        assertNotNull(properties);
        assertTrue(properties.containsKey("key"));
    }

    @Test
    void testGetProperty() {
        PropertyHelper.loadProperties("test.properties");
        assertEquals("value", PropertyHelper.HMAPPROPERTIES.get("key"));
    }

    @Test
    void testGetPropertyDefault() {
        String key = "nonexistentKey";
        String defaultValue = "default";
        assertEquals(defaultValue, PropertyHelper.getProperty(key, defaultValue));
    }
}
