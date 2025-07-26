package com.devopsdemo.utilities;

public class HexAsciiConvertor {

    /**
     * Converts hexadecimal values into ASCII.
     *
     * @param hexValue the hexadecimal value
     * @return the ASCII value
     */
    public static String convertHexToASCII(String hexValue) {
        if (hexValue == null || hexValue.isEmpty()) {
            throw new IllegalArgumentException("Hex value cannot be null or empty");
        }
        if (hexValue.length() % 2 != 0 || !hexValue.matches("[0-9A-Fa-f]+")) {
            throw new IllegalArgumentException("Invalid hex string");
        }
        var outputAscii = new StringBuilder();
        for (int i = 0; i < hexValue.length(); i += 2) {
            var str = hexValue.substring(i, i + 2);
            outputAscii.append((char) Integer.parseInt(str, 16));
        }
        return outputAscii.toString();
    }
    
    /**
     * Alias for convertHexToASCII to match test usage.
     */
    public static String hexToAscii(String hexValue) {
        return convertHexToASCII(hexValue);
    }

    /**
     * Alias for convertAsciiToHex to match test usage.
     */
    public static String asciiToHex(String asciiValue) {
        return convertAsciiToHex(asciiValue);
    }

    /**
     * Converts ASCII values into hexadecimal.
     *
     * @param asciiValue the ASCII value
     * @return the hexadecimal value
     */
    public static String convertAsciiToHex(String asciiValue) {
        if (asciiValue == null || asciiValue.isEmpty()) {
            throw new IllegalArgumentException("Hex value cannot be null or empty");
        }

        var hex = new StringBuilder();
        try {
            for (char c : asciiValue.toCharArray()) {
                hex.append(Integer.toHexString(c));
            }
        } catch (Exception e) {
            LoggerStackTraceUtil.printErrorMessage(e);
        }
        return hex.toString();
    }
}
