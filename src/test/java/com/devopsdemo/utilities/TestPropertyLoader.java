package com.devopsdemo.utilities;

import org.junit.jupiter.api.Test;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.*;

class TestPropertyLoader {

    @Test
    void testLoadProperties() {
        Properties properties = PropertyLoader.loadProperties("test.properties");
        assertNotNull(properties);
        assertEquals("value", properties.getProperty("key"));
    }

    @Test
    void testLoadPropertiesFileNotFound() {
        assertThrows(IllegalArgumentException.class, () -> PropertyLoader.loadProperties("nonexistent.properties"));
    }

    @Test
    void testLoadPropertiesEmptyFile() {
        Properties properties = PropertyLoader.loadProperties("empty.properties");
        assertNotNull(properties);
        assertTrue(properties.isEmpty());
    }

    @Test
    void testLoadPropertiesWithSpaces() {
        Properties properties = PropertyLoader.loadProperties("spaces.properties");
        assertNotNull(properties);
        assertEquals("value with spaces", properties.getProperty("key with spaces"));
    }
}
