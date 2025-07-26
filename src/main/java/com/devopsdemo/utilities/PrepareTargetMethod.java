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
        return METHOD_GET_PREFIX + name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
