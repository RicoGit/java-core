package com.jdk.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.util.Set;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

/**
 * User: Constantine Solovev
 * Created: 22.03.15  19:04
 */


public class ReadNetWriteFile {

    /* прочитать страницу в интернете, записать в файл */

    private static final String HOST = "samolisov.blogspot.ru";
    private static final String PATH = "/2013/11/java.html";
    private static final String DESTINATION_FILE = "/home/rico/webdev/core/resources/ReadNetWriteFile.txt";

    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();
        SocketChannel channel = SocketChannel.open();

        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress(HOST, 80));
        channel.register(selector, SelectionKey.OP_CONNECT);

        int counter = 20;
        while(counter-- > 0) {

            if (selector.select() == -1) {
                continue;
            }

            Set<SelectionKey> keys = selector.keys();

            for (SelectionKey key : keys) {

                if (key.isConnectable()) {
                    channel.finishConnect();
                    key.interestOps(SelectionKey.OP_WRITE);
                }

                if (key.isWritable()) {
                    System.out.println("write request");
                    String requestTemplate = "GET %s HTTP/1.0\r\nHost: %s \r\n\r\n";
                    String request = String.format(requestTemplate, PATH, HOST);

                    ByteBuffer byteBuffer = ByteBuffer.wrap(request.getBytes());
                    channel.write(byteBuffer);

                    key.interestOps(SelectionKey.OP_READ);
                }

                if (key.isReadable()) {

                    System.out.println("read answer");
                    ByteBuffer readBuffer = ByteBuffer.allocate(2560);
                    FileChannel fileChannel = FileChannel.open(Paths.get(DESTINATION_FILE), CREATE, WRITE);

                    int read = 0;
                    int total = 0;

                    while ( read != -1) {
                        read = channel.read(readBuffer);
                        if (read > 0) {
                            readBuffer.rewind();
                            fileChannel.write(readBuffer);
                            readBuffer.clear();
                            System.out.println("\t writing to disk " + (total +=read));
                        }
                    }
                    System.out.println("finish");
                    return;

                }


            }

        }


    }

}
