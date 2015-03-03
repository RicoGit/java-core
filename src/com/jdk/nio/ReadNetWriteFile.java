package com.jdk.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * User: Constantine Solovev
 * Created: 01.03.15  20:20
 */


public class ReadNetWriteFile {

    /* читаем хабр http://habrahabr.ru/post/244531/ пишем в файл */

    public static void main(String[] args) {

        String url = "habrahabr.ru/post/244531";
        String targetFile = "resources/post-244531.txt";

        try (SocketChannel socketChannel = SocketChannel.open()) {

            InetSocketAddress socketAddress = new InetSocketAddress(url, 80);
            boolean connect = socketChannel.connect(socketAddress);

            if (connect) {

                File file = new File(targetFile);
                //create new file if not exists
                if (file.exists() || file.createNewFile()) {
                    // todo not finish
                    FileChannel fileChannel = new FileOutputStream(file).getChannel();
                    MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 1000);
                    socketChannel.read(byteBuffer);
                }
            }


        } catch (IOException e) {
            System.out.println(e);
        }


    }



}
