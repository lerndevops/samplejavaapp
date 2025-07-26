package com.devopsdemo.utilities;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for logging stack trace details.
 */
public class LoggerStackTraceUtil {

    /**
     * Returns the stack trace of an exception as a string.
     * @param ex the exception
     * @return the stack trace string
     */
    public static String getStackTrace(Exception ex) {
        if (ex == null) throw new NullPointerException("Exception cannot be null");
        StringBuilder sb = new StringBuilder();
        sb.append(ex.toString()).append(System.lineSeparator());
        for (StackTraceElement elem : ex.getStackTrace()) {
            sb.append(elem.toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    private static final Logger LOG = LoggerFactory.getLogger(LoggerStackTraceUtil.class);
    private final int maxCount = 3;

    /**
     * Retrieves the error message from a throwable, including root causes.
     *
     * @param th the throwable to process
     * @return a formatted error message
     */
    public String getErrorMessage(Throwable th) {
        if (th == null) {
            return "";
        }

        var b = new StringBuilder();
        var aryError = ExceptionUtils.getRootCauseStackTrace(th);

        b.append(aryError[0].trim());
        if (aryError.length >= 2) {
            b.append(String.format("%nCause: %s", aryError[1].trim()));
        }
        if (aryError.length >= maxCount) {
            b.append(String.format("%nCause: %s", aryError[2].trim()));
        }

        return b.toString();
    }

    /**
     * Logs the error message and stack trace of a throwable.
     *
     * @param th the throwable to log
     */
    public static void printErrorMessage(Throwable th) {
        if (th == null) {
            return;
        }

        try {
            LOG.error("Error Cause: {}", th.getMessage());

            var count = 0;
            for (var stackTrace : th.getStackTrace()) {
                if (count > 25) {
                    break;
                }
                LOG.error("Error Class: {} and Line Number: {}", stackTrace.getClassName(), stackTrace.getLineNumber());
                count++;
            }
        } catch (Exception e) {
            LOG.error("Failed to log error message: {}", e.getMessage());
        }
    }
}
