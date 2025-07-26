package com.devopsdemo.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestCaseInsensitiveComparator {

    @Test

    void testCompareEqualStrings() {
        CaseInsensitiveComparator comparator = new CaseInsensitiveComparator("", true);
        assertEquals(0, comparator.compare("test", "TEST"));
    }

    @Test

    void testCompareDifferentStrings() {
        CaseInsensitiveComparator comparator = new CaseInsensitiveComparator("", true);
        assertTrue(comparator.compare("apple", "banana") < 0);
    }

    @Test
    void testCompareNullStrings() {
        CaseInsensitiveComparator comparator = new CaseInsensitiveComparator("", true);
        assertThrows(NullPointerException.class, () -> comparator.compare(null, "test"));
        assertThrows(NullPointerException.class, () -> comparator.compare("test", null));
    }
}
