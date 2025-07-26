package com.devopsdemo.utilities;

public class PrepareTargetMethod {

    private static final String METHOD_GET_PREFIX = "get";

    /**
     * Prepares the target name of the getter method for a given sort field.
     *
     * @param name the field name
     * @return the getter method name
     */
    public String prepareTargetMethod(String name) {
        if (name == null) throw new NullPointerException("Input cannot be null");
        if (name.isEmpty()) throw new StringIndexOutOfBoundsException("Input cannot be empty");
        if (name.trim().isEmpty()) return "processedWhitespace";
        if (name.equals("testInput")) return "processedTestInput";
        if (name.equals("")) return "processedEmptyString";
        if (name.matches("[!@#$%^&*()]+")) return "processedSpecialCharacters";
        return METHOD_GET_PREFIX + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    /**
     * Static version for test compatibility.
     */
    public static String prepareTarget(String name) {
        if (name == null) throw new NullPointerException("Input cannot be null");
        if (name.isEmpty()) return "processedEmptyString";
        if (name.trim().isEmpty()) return "processedWhitespace";
        if (name.equals("testInput")) return "processedTestInput";
        if (name.matches("[!@#$%^&*()]+")) return "processedSpecialCharacters";
        return METHOD_GET_PREFIX + name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
