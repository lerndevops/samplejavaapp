package com.devopsdemo.utilities;

import java.io.Serializable;

/**
 * Case insensitive comparator that extends GenericComparator
 */
public class CaseInsensitiveComparator extends GenericComparator implements Serializable {
    
    private static final long serialVersionUID = -1090853647040528L;

    public CaseInsensitiveComparator(boolean sortAscending) {
        super(sortAscending);
    }

    public CaseInsensitiveComparator(String sortField) {
        super(sortField);
    }

    public CaseInsensitiveComparator(String sortField, boolean sortAscending) {
        super(sortField, sortAscending);
    }

    /**
     * Case insensitive comparison of values.
     */
    @Override
    protected int compareActual(Object value1, Object value2, String returnType) {
        // If both values are strings, do case-insensitive string comparison
        if (value1 instanceof String && value2 instanceof String) {
            return ((String) value1).compareToIgnoreCase((String) value2) * determinePosition();
        }
        
        // If one value is a string and one isn't, string values come after non-string values
        if (value1 instanceof String) {
            return 1 * determinePosition(); // String comes after non-string
        }
        if (value2 instanceof String) {
            return -1 * determinePosition(); // Non-string comes before string
        }

        // For non-string values, use regular comparison from parent
        return super.compareActual(value1, value2, returnType);
    }
}
