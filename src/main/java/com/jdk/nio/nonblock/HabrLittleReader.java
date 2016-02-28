package com.jdk.nio.nonblock;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Queue;
import java.util.Set;

/**
 * User: Constantine Solovev
 * Created: 21.03.15  19:04
 */


public class HabrLittleReader {

    private final String HOST = "habrahabr.ru";
    private final int PORT = 80;
    private final PageStore store;
    private final Queue<Integer> postNumbers;


    public HabrLittleReader(PageStore store, Queue<Integer> postNumbers) {
        this.store = store;
        this.postNumbers = postNumbers;
    }

    /**
     * one thread non-blocking read
     */
    public void readAll() {

        System.out.println("readAll() start");

        try {

            Selector selector = Selector.open();
            // rewritable channels
            SocketChannel[] channels = new SocketChannel[store.getCapacity()];

            for (SocketChannel channel : channels) {
                channel = SocketChannel.open();
                channel.configureBlocking(false);
                channel.register(selector, SelectionKey.OP_CONNECT);
                channel.connect(new InetSocketAddress(HOST, PORT));
            }

            while (true) {

                if (selector.select() == 0) {
                    continue;
                }

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                for (SelectionKey key : selectionKeys) {

                    // establish connection
                    if (key.isConnectable() && !postNumbers.isEmpty()) {

                        SocketChannel channel = (SocketChannel) key.channel();

                        key.attach(postNumbers.poll());
                        channel.finishConnect();
                        key.interestOps(SelectionKey.OP_WRITE);
                    }

                    // write request
                    if (key.isWritable()) {

                        SocketChannel channel = (SocketChannel) key.channel();

                        Integer postNumber = (Integer) key.attachment();
                        System.out.println("+ requested " + postNumber);

                        ByteBuffer buffer = ByteBuffer.wrap(composeGetRequest(postNumber).getBytes());
                        channel.write(buffer); // write response to channel

                        key.interestOps(SelectionKey.OP_READ);
                    }

                    if (key.isReadable()) {

                        SocketChannel channel = (SocketChannel) key.channel();

                        String responseText = getResponseText(channel);
                        Integer postNumber = (Integer) key.attachment();

                        store.save(new Page(postNumber, responseText));
                        System.out.println("+ \t saved " + postNumber);
                    }

                }

            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private String composeGetRequest(int postNumber){
        return String.format("GET %s HTTP/1.0\r\nHost: %s\r\n\r\n", getRequestUrl(postNumber), HOST);
    }

    private String getRequestUrl(int postNumber) {
        return String.format("/post/%d/", postNumber);
    }

    private String getResponseText(SocketChannel channel) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(2560);
        StringBuilder stringBuf = new StringBuilder();

        try {

            int read = 0;

            while (read != -1) {

                read = channel.read(byteBuffer);

                if (read < 1) continue;

                byteBuffer.rewind();
                stringBuf.append(new String(byteBuffer.array(), Charset.defaultCharset()));
                byteBuffer.clear();
            }

            return stringBuf.toString();

        } catch (IOException e) {
            System.out.println("read exception");
        }

        return "";
    }


}
