package com.structures;

/**
 * User: Constantine Solovev
 * Created: 13.03.16  11:39
 */


public class Runner {

    public static void main(String[] args) {

        BinarySearchTree<String, String> bst = new BinarySearchTree<>();

        testAll(bst);

    }

    private static void testAll(Table<String, String>... tables) {
        TableTest tableTest = new TableTest();
        for(Table table : tables) {
            tableTest.test(table);
        }
    }

}
