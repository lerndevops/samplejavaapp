package com.devopsdemo.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestStringUtilities {

    @Test
    void testIsNullOrEmpty() {
        assertTrue(StringUtilities.isNullOrEmpty(null));
        assertTrue(StringUtilities.isNullOrEmpty(""));
        assertFalse(StringUtilities.isNullOrEmpty("test"));
    }

    @Test
    void testReverseString() {
        assertEquals("tset", StringUtilities.reverseString("test"));
        assertEquals("", StringUtilities.reverseString(""));
    }

    @Test
    void testReverseStringNull() {
        assertThrows(NullPointerException.class, () -> StringUtilities.reverseString(null));
    }
}
