package com.devopsdemo.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestPrepareTargetMethod {

    @Test
    void testPrepareTarget() {
        String input = "testInput";
        assertEquals("processedTestInput", PrepareTargetMethod.prepareTarget(input));
    }

    @Test
    void testPrepareTargetNull() {
        assertThrows(NullPointerException.class, () -> PrepareTargetMethod.prepareTarget(null));
    }

    @Test
    void testPrepareTargetEmptyString() {
        String input = "";
        assertEquals("processedEmptyString", PrepareTargetMethod.prepareTarget(input));
    }

    @Test
    void testPrepareTargetWhitespace() {
        String input = "   ";
        assertEquals("processedWhitespace", PrepareTargetMethod.prepareTarget(input));
    }

    @Test
    void testPrepareTargetSpecialCharacters() {
        String input = "!@#$%^&*()";
        assertEquals("processedSpecialCharacters", PrepareTargetMethod.prepareTarget(input));
    }

    @Test
    void testPrepareTargetMethod() {
        PrepareTargetMethod method = new PrepareTargetMethod();
        assertEquals("getName", method.prepareTargetMethod("name"));
    }

    @Test
    void testPrepareTargetMethodEmpty() {
        PrepareTargetMethod method = new PrepareTargetMethod();
        assertThrows(StringIndexOutOfBoundsException.class, () -> method.prepareTargetMethod(""));
    }
}
