package com.devopsdemo.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestLoggerStackTraceUtil {

    @Test
    void testGetStackTrace() {
        Exception exception = new Exception("Test Exception");
        String stackTrace = LoggerStackTraceUtil.getStackTrace(exception);
        assertNotNull(stackTrace);
        assertTrue(stackTrace.contains("Test Exception"));
    }

    @Test
    void testGetStackTraceNull() {
        assertThrows(NullPointerException.class, () -> LoggerStackTraceUtil.getStackTrace(null));
    }
}
