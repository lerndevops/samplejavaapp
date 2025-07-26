package com.devopsdemo.utilities;

import java.util.ArrayList;
import java.util.Collections;

import com.devopsdemo.utilities.GenericComparator;
import com.devopsdemo.utilities.CaseInsensitiveComparator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Seshagiri Sriram
 *
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class TestGenericComparator {
        public void initialize(ArrayList myData) { 
                UnitDTO d1 = new UnitDTO(); 
                d1.setDeptID(100);d1.setEmpID(200);d1.setEmpName("Sriram");d1.setSpare(new Double(18.0));d1.setSpare2(new Double(18.0)); 
                UnitDTO d2 = new UnitDTO(); 
                d2.setDeptID(100);d2.setEmpID(201);d2.setEmpName("Somebody");d2.setSpare(new Double(11.0));d2.setSpare2(new Double(11.0));
                UnitDTO d3 = new UnitDTO(); 
                d3.setDeptID(100);d3.setEmpID(100);d3.setEmpName("Rajan");d3.setSpare(new Double(12.0));d3.setSpare2(new Double(12.0));
                UnitDTO d4 = new UnitDTO(); 
                d4.setDeptID(100);d4.setEmpID(102);d4.setEmpName("Vellman");d4.setSpare(new Double(10.0));d4.setSpare2(new Double(10.0));
                UnitDTO d5 = new UnitDTO(); 
                d5.setDeptID(100);d5.setEmpID(110);d5.setEmpName("Asma");d5.setSpare(new Double(10.0));d5.setSpare2(new Double(10.0));
                myData.add(d1); myData.add(d2); myData.add(d3); myData.add(d4); myData.add(d5); 

        }
        public void initialize2(ArrayList myData) { 
                UnitDTO d1 = new UnitDTO(); 
                d1.setDeptID(100);d1.setEmpID(200);d1.setEmpName("Sriram");d1.setSpare(new Double(18.0));d1.setSpare2("A"); 
                UnitDTO d2 = new UnitDTO(); 
                d2.setDeptID(100);d2.setEmpID(201);d2.setEmpName("Somebody");d2.setSpare(new Double(11.0));d2.setSpare2("B");
                UnitDTO d3 = new UnitDTO(); 
                d3.setDeptID(100);d3.setEmpID(100);d3.setEmpName("Rajan");d3.setSpare(new Double(12.0));d3.setSpare2("C");
                UnitDTO d4 = new UnitDTO(); 
                d4.setDeptID(100);d4.setEmpID(102);d4.setEmpName("Vellman");d4.setSpare(new Double(10.0));d4.setSpare2("D");
                UnitDTO d5 = new UnitDTO(); 
                d5.setDeptID(100);d5.setEmpID(110);d5.setEmpName("Asma");d5.setSpare(new Double(10.0));d5.setSpare2("Z");
                myData.add(d1); myData.add(d2); myData.add(d3); myData.add(d4); myData.add(d5); 

        }
        public void initialize3(ArrayList myData) { 
                UnitDTO d1 = new UnitDTO(); 
                d1.setDeptID(100);d1.setEmpID(200);d1.setEmpName("Sriram");d1.setSpare(new Double(18.0));d1.setSpare2("A"); 
                UnitDTO d2 = new UnitDTO(); 
                d2.setDeptID(100);d2.setEmpID(201);d2.setEmpName("asma");d2.setSpare(new Double(11.0));d2.setSpare2("B");
                UnitDTO d3 = new UnitDTO(); 
                d3.setDeptID(100);d3.setEmpID(100);d3.setEmpName("Rajan");d3.setSpare(new Double(12.0));d3.setSpare2("C");
                UnitDTO d4 = new UnitDTO(); 
                d4.setDeptID(100);d4.setEmpID(102);d4.setEmpName("Vellman");d4.setSpare(new Double(10.0));d4.setSpare2("D");
                UnitDTO d5 = new UnitDTO(); 
                d5.setDeptID(100);d5.setEmpID(110);d5.setEmpName("Asma");d5.setSpare(new Double(10.0));d5.setSpare2("Z");
                
                UnitDTO d6 = new UnitDTO(); 
                d6.setDeptID(100);d6.setEmpID(110);d6.setEmpName("ASMA");d6.setSpare(new Double(10.0));d6.setSpare2("Z");

                myData.add(d1); myData.add(d2); myData.add(d3); myData.add(d4); myData.add(d5); myData.add(d6);

        }
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */



    /**
     * Test for Sorting by Emp Name Ascending
     */
    @Test
    void testSortEmpNameAsc() {
            ArrayList myData = new ArrayList(); initialize(myData);
            Collections.sort(myData, new GenericComparator("empName", true)); // sort ascending.. 
            assertEquals("Asma", ((UnitDTO)myData.get(0)).getEmpName()); 
    }

    /**
     * Test for Sorting by Emp Name Descending
     */
    @Test
    void testSortEmpNameDesc() {
            ArrayList myData = new ArrayList(); initialize(myData);
            Collections.sort(myData, new GenericComparator("empName", false)); // sort ascending..
               assertEquals("Vellman", ((UnitDTO)myData.get(0)).getEmpName()); 
            
    }

        
    /**
     * Test for Sorting by Emp ID Ascending
     */
    @Disabled("testSortIDAsc")
    @Test
    void testSortEmpIDAsc() {
            ArrayList myData = new ArrayList(); initialize(myData);

            Collections.sort(myData, new GenericComparator("empID", true)); // sort ascending.. 
            assertEquals("Rajan", ((UnitDTO)myData.get(0)).getEmpName()); 
    }


    /**
     * Test for Sorting besy Emp ID Descending...
     */

    @Test
    void testSortEmpIDDesc() {
            ArrayList myData = new ArrayList(); initialize(myData);
            Collections.sort(myData, new GenericComparator("empID", false)); // sort Descending 
            assertEquals("Somebody", ((UnitDTO)myData.get(0)).getEmpName()); 
            
    }

    /**
     * Test for Sorting by spare Ascending
     */
    @Test
    void testSortEmpSpareAsc() {
            ArrayList myData = new ArrayList(); initialize(myData);
            Collections.sort(myData, new GenericComparator("spare", true)); // sort ascending 
            assertEquals("Vellman", ((UnitDTO)myData.get(0)).getEmpName()); 
    }

    /**
     * Test for Sorting by spare Descending
     */
    @Test
    void testSortEmpSpareDesc() {
            ArrayList myData = new ArrayList(); initialize(myData);
            Collections.sort(myData, new GenericComparator("spare", false)); // sort Descending 
            assertEquals("Sriram", ((UnitDTO)myData.get(0)).getEmpName()); 
            
    }


    /**
     * Test for Sorting by spare2 Descending
     */
    @Test
    void testSortEmpSpareDesc2() {
            ArrayList myData = new ArrayList(); initialize(myData);

            Collections.sort(myData, new GenericComparator("spare2", false)); // sort Descending 
            assertEquals("Sriram", ((UnitDTO)myData.get(0)).getEmpName()); 
            
    }

    /**
     * Test for Sorting by spare2 Ascending
     */
    @Test
    void testSortEmpSpareAsc2() {
            ArrayList myData = new ArrayList(); initialize(myData);

            Collections.sort(myData, new GenericComparator("spare2", true)); // sort ascending 
            assertEquals("Vellman", ((UnitDTO)myData.get(0)).getEmpName()); 
            
    }

    /**
     * Test for Sorting by spare2 Ascending
     */
    @Test
    void testSortEmpSpare2StringAsc() {
            ArrayList myData = new ArrayList(); initialize2(myData);
            Collections.sort(myData, new GenericComparator("spare2", true)); // sort ascending 
            assertEquals("A", ((UnitDTO)myData.get(0)).getSpare2()); 
    }

    /**
     * Test for Sorting by spare2 Desc
     */
    @Test
    void testSortEmpSpare2StringDesc() {
            ArrayList myData = new ArrayList(); initialize2(myData);
            Collections.sort(myData, new GenericComparator("spare2", false)); // sort Descending 
            assertEquals("Z", ((UnitDTO)myData.get(0)).getSpare2()); 
    }
    /**
     * Test for Sorting by caseInsensitive Emp Name Ascending
     */
    @Test
    void testSortEmpNameAscNewComparator() {
            ArrayList myData = new ArrayList(); initialize3(myData);
            Collections.sort(myData, new CaseInsensitiveComparator("empName", true)); // sort ascending.. 
            assertEquals("Asma".toUpperCase(), ((UnitDTO)myData.get(0)).getEmpName().toUpperCase());
            assertEquals("Asma".toUpperCase(), ((UnitDTO)myData.get(1)).getEmpName().toUpperCase()); 

    }

    

    /**
     * Test for Sorting caseInsensitive by Emp Name Ascending
     */
    @Test
    void testSortEmpNameAscNewComparator3Element() {
            ArrayList myData = new ArrayList(); initialize3(myData);
            Collections.sort(myData, new CaseInsensitiveComparator("empName", true)); // sort ascending.. 
            assertEquals("Asma".toUpperCase(), ((UnitDTO)myData.get(0)).getEmpName().toUpperCase());
            assertEquals("Asma".toUpperCase(), ((UnitDTO)myData.get(1)).getEmpName().toUpperCase()); 
              assertEquals("Asma".toUpperCase(), ((UnitDTO)myData.get(2)).getEmpName().toUpperCase()); 

    }

    /**
     * Test for Sorting caseInsensitive by Emp Name Descending
     */
    @Test
    void testSortEmpNameDescNewComparator3Element() {
            ArrayList myData = new ArrayList(); initialize3(myData);
            Collections.sort(myData, new CaseInsensitiveComparator("empName", false)); // sort ascending.. 
            assertEquals("Asma".toUpperCase(), ((UnitDTO)myData.get(3)).getEmpName().toUpperCase());
            assertEquals("Asma".toUpperCase(), ((UnitDTO)myData.get(4)).getEmpName().toUpperCase()); 
              assertEquals("Asma".toUpperCase(), ((UnitDTO)myData.get(5)).getEmpName().toUpperCase()); 

    }

    @Test
    void testDoubleSort() {
            ArrayList myData = new ArrayList();            
                UnitDTO d1 = new UnitDTO(); 
                d1.setDeptID(100);d1.setEmpID(200);d1.setEmpName("A");d1.setSpare(new Double(18.0));d1.setSpare2(new Double(18.0)); 
                UnitDTO d2 = new UnitDTO(); 
                d2.setDeptID(100);d2.setEmpID(199);d2.setEmpName("D");d2.setSpare(new Double(11.0));d2.setSpare2(new Double(11.0));
                UnitDTO d3 = new UnitDTO();
                d3.setDeptID(100);d3.setEmpID(201);d3.setEmpName("C");d3.setSpare(new Double(12.0));d3.setSpare2(new Double(12.0));
                myData.add(d1); myData.add(d2); myData.add(d3);
                Collections.sort(myData, new GenericComparator("empID", true)); // sort Asc
                assertEquals (Integer.valueOf(3), writeList(myData));
            Collections.sort(myData, new GenericComparator("empName", true)); // sort Asc
            assertEquals (Integer.valueOf(3), writeList(myData));
            assertFalse( ((UnitDTO)myData.get(1)).getEmpID() < ((UnitDTO)myData.get(2)).getEmpID()); 
    }
    private Integer writeList(ArrayList s){
            Integer  c = new Integer(s.size());
            return c; 
    }
    
    
    @org.junit.jupiter.api.Test
    void testCompareEqualObjects() {
        GenericComparator<String> comparator = new GenericComparator<>();
        assertEquals(0, comparator.compare("test", "test"));
    }

    @org.junit.jupiter.api.Test
    void testCompareDifferentObjects() {
        GenericComparator<Integer> comparator = new GenericComparator<>();
        assertTrue(comparator.compare(1, 2) < 0);
    }

    @org.junit.jupiter.api.Test
    void testCompareNullObjects() {
        GenericComparator<String> comparator = new GenericComparator<>();
        assertThrows(NullPointerException.class, () -> comparator.compare(null, "test"));
        assertThrows(NullPointerException.class, () -> comparator.compare("test", null));
    }
}
