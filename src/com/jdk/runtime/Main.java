package com.jdk.runtime;

/**
 * Project: Shamrock Web Portal.
 * User: Solovev Constantine
 * Date: 18.11.14
 * Time: 21:04
 */

public class Main {

    public static void main(String[] args) {

        String s1 = ScriptRunner.executeCommand("./resources/testscript.sh world1");
        String s3 = ScriptRunner.executeCommand("pwd");
        System.out.println(s1);
        System.out.println(s3);

    }

}
