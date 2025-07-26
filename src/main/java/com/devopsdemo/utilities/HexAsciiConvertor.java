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
            return null;
        }

        var outputAscii = new StringBuilder();
        try {
            for (int i = 0; i < hexValue.length(); i += 2) {
                var str = hexValue.substring(i, i + 2);
                outputAscii.append((char) Integer.parseInt(str, 16));
            }
        } catch (Exception ex) {
            LoggerStackTraceUtil.printErrorMessage(ex);
        }
        return outputAscii.toString();
    }

    /**
     * Converts ASCII values into hexadecimal.
     *
     * @param asciiValue the ASCII value
     * @return the hexadecimal value
     */
    public static String convertAsciiToHex(String asciiValue) {
        if (asciiValue == null || asciiValue.isEmpty()) {
            return null;
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
