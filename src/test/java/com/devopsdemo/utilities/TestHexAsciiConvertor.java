package com.devopsdemo.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestHexAsciiConvertor {

    @Test
    void testHexToAscii() {
        String hex = "48656c6c6f";
        String expected = "Hello";
        assertEquals(expected, HexAsciiConvertor.hexToAscii(hex));
    }

    @Test
    void testAsciiToHex() {
        String ascii = "World";
        String expected = "576f726c64";
        assertEquals(expected, HexAsciiConvertor.asciiToHex(ascii));
    }

    @Test
    void testInvalidHexToAscii() {
        String invalidHex = "ZZZZ";
        assertThrows(IllegalArgumentException.class, () -> HexAsciiConvertor.hexToAscii(invalidHex));
    }
}
