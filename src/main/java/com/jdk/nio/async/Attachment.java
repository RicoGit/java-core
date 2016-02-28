package com.jdk.nio.async;

import java.nio.channels.AsynchronousSocketChannel;

/**
 * User: Constantine Solovev
 * Created: 06.04.15  21:49
 */


public class Attachment {

    public Attachment(AsynchronousSocketChannel channel, int index) {
        this.channel = channel;
        this.index = index;
    }

    AsynchronousSocketChannel channel;
    int index;
}
