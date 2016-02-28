package com.jdk.nio.nonblock;

/**
 * User: Constantine Solovev
 * Created: 22.03.15  11:33
 */


public class Page {

    private final int number;
    private final String content;

    public Page(int number, String content) {
        this.number = number;
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public String getContent() {
        return content;
    }
}
