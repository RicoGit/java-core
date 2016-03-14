package com.structures;

import java.util.Arrays;
import java.util.List;

/**
 * User: Constantine Solovev
 * Created: 13.03.16  11:46
 */


public class TableTest {


    public void test(Table<String, String> table) {
        // todo create generic Tester and Runner classes

        complexSimpleTest(table);

    }

    /**
     *This test does not apply for completeness checking, it checks the functionality of an extremely superficial.
     */
    private void complexSimpleTest(Table<String, String> table) {
        assertTrue(table.size() == 0, "table must be empty");
        table.put("a", "test");
        assertTrue(table.get("a").equals("test"), "table should return a1");
        assertTrue(table.size() == 1, "size should be equals 1");

        // fill table
        String[][] testSet = createTestSet();
        for (String[] tuple : testSet) {
            table.put(tuple[0], tuple[1]);
        }

        Arrays.sort(testSet, (tuple1, tuple2) -> tuple1[0].compareTo(tuple2[0]));

        checkKeysOrder(table);

        checkSize(table, testSet);

        checkMin(table, testSet);

        checkMax(table, testSet);

        checkSelectAndRank(table);

        checkFloor(table, testSet);



        checkDelete(table);

        checkDeleteMin(table, testSet);

        checkDeleteMax(table, testSet);


    }

    private void checkFloor(Table<String, String> table, String[][] testSet) {
        String key = "f";
        String floor = table.floor(key);
        String expected = "e";
        assertTrue(floor.equals(expected), String.format("floor expected %s, actually %s", expected, floor));

        String key2 = "x";
        String floor2 = table.floor(key2);
        assertTrue(floor2.equals(key2), String.format("floor expected %s, actually %s", key2, floor2));
    }

    private void checkDelete(Table<String, String> table) {
        int startSize = table.size();
        String key = "c";
        table.delete(key);
        assertTrue(startSize - table.size() == 1, "table size should be decreased by 1");
        assertTrue(table.get(key) == null, "getting deleted key should return null");
        checkKeysOrder(table);
    }


    private void checkKeysOrder(Table<String, String> table) {
        List<String> keys = table.keys();
        for (int i = 1; i < keys.size(); i++) {
            assertTrue(keys.get(i - 1).compareTo(keys.get(i)) < 0, "keys are not ordered");
        }
    }

    private void checkSize(Table<String, String> table, String[][] testSet) {
        int expectedSize = testSet.length;
        assertTrue(table.size() == expectedSize, "table size should be " + expectedSize);
    }

    private void checkSelectAndRank(Table<String, String> table) {
        for (int i = 0; i < table.size() - 1; i++) {
            assertTrue(
                table.rank(table.select(i)) == i,
                String.format("select and rank invalid for i=%s", i)
            );
        }
    }

    private void checkMax(Table<String, String> table, String[][] testSet) {
        String max = table.max();
        String expectedMax = testSet[testSet.length - 1][0];
        assertTrue(max.equals(expectedMax), String.format("max expected %s, actually %s", expectedMax, max));
    }

    private void checkDeleteMin(Table<String, String> table, String[][] testSet) {

        String deletedMin = table.deleteMin();
        String expectedDeletedMin = testSet[0][0];
        assertTrue(
            deletedMin.equals(expectedDeletedMin),
            String.format("min deleted expected %s, actually %s",  deletedMin, expectedDeletedMin)
        );

        String min = table.min();
        String expectedMin = testSet[1][0];
        assertTrue(min.equals(expectedMin), String.format("min expected %s, actually %s", expectedMin, min));
    }

    private void checkDeleteMax(Table<String, String> table, String[][] testSet) {
        String deletedMax = table.deleteMax();
        String expectedDeletedMax = testSet[testSet.length-1][0];
        assertTrue(
            deletedMax.equals(expectedDeletedMax),
            String.format("max deleted expected %s, actually %s",  deletedMax, expectedDeletedMax)
        );

        String max = table.max();
        String expectedMax = testSet[testSet.length - 2][0];
        assertTrue(max.equals(expectedMax), String.format("max expected %s, actually %s",  max, expectedMax));
    }

    private void checkMin(Table<String, String> table, String[][] testSet) {
        String min = table.min();
        String expectedMin = testSet[0][0];
        assertTrue(min.equals(expectedMin), String.format("min expected %s, actually %s",  min, expectedMin));
    }

    private String[][] createTestSet() {
        return new String[][] {
            {"z", "z1"}, {"y", "y1"}, {"x", "x1"} , {"w", "w1"},
            {"a", "a1"}, {"b", "b1"}, {"e", "e1"} , {"d", "d1"},
            {"r", "r1"}, {"i", "i1"}, {"c", "c2"} , {"o", "o1"},
        };
    }

    private void assertTrue(boolean expression, String message) {
        if (expression) return;
        throw new IllegalArgumentException(message);
    }


}
