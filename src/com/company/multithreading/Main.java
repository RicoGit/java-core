package com.company.multithreading;

public class Main {

    public static void main(String[] args) {

        System.out.println("Start");

        new MyThread(" 1");
        new MyThread("  2");
        new MyThread("   3");

        System.out.println("End");

    }

}