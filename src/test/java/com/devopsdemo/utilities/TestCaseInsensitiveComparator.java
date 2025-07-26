package com.devopsdemo.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestCaseInsensitiveComparator {

    @Test
    void testCompareEqualStrings() {
        CaseInsensitiveComparator comparator = new CaseInsensitiveComparator("", true);
        assertEquals(0, comparator.compare("test", "TEST"));
        assertEquals(0, comparator.compare("TEST", "test"));
        assertEquals(0, comparator.compare("Test", "tEsT"));
    }

    @Test
    void testCompareDifferentStrings() {
        CaseInsensitiveComparator comparator = new CaseInsensitiveComparator("", true);
        assertTrue(comparator.compare("apple", "banana") < 0);
        assertTrue(comparator.compare("APPLE", "banana") < 0);
        assertTrue(comparator.compare("banana", "APPLE") > 0);
    }

    @Test
    void testCompareNullStrings() {
        CaseInsensitiveComparator comparator = new CaseInsensitiveComparator("", true);
        assertEquals(0, comparator.compare(null, null));
        assertTrue(comparator.compare(null, "test") < 0);
        assertTrue(comparator.compare("test", null) > 0);
    }

    @Test
    void testCompareEmptyStrings() {
        CaseInsensitiveComparator comparator = new CaseInsensitiveComparator("", true);
        assertEquals(0, comparator.compare("", ""));
        assertTrue(comparator.compare("", "test") < 0);
        assertTrue(comparator.compare("test", "") > 0);
    }

    @Test
    void testCompareDifferentTypes() {
        CaseInsensitiveComparator comparator = new CaseInsensitiveComparator("", true);
        assertTrue(comparator.compare(123, "test") < 0);
        assertTrue(comparator.compare("test", 123) > 0);
    }

    @Test
    void testSortOrder() {
        CaseInsensitiveComparator ascComparator = new CaseInsensitiveComparator("", true);
        CaseInsensitiveComparator descComparator = new CaseInsensitiveComparator("", false);
        
        assertTrue(ascComparator.compare("apple", "banana") < 0);
        assertTrue(descComparator.compare("apple", "banana") > 0);
    }
}
