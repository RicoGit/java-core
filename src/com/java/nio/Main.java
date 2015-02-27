package com.java.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.*;

/**
 * Project: Shamrock Web Portal.
 * User: Solovev Constantine
 * Date: 30.11.14
 * Time: 10:57
 */

public class Main {

    public static void main(String[] args) {

        // создадим файл прочитав из интерента

        /*1 читаем из файла в файл с фильтрацией */


        String data = "Это нужно скопировать в source.file !!!\n IMPORTANT!";

        String sourcePath = "/home/solovev/webdev/java-core/resources/source.file";
        String targetPath = "/home/solovev/webdev/java-core/resources/target.file";

        Path source = Paths.get(sourcePath);
        Path target = Paths.get(targetPath);

        try {
            source.toFile().createNewFile();
            Files.write(source, data.getBytes(), WRITE, CREATE_NEW);
        } catch (IOException e) {
            System.out.println("write data to file failed");
        }

        try(
            FileChannel sourceChanel = (FileChannel) Files.newByteChannel(source);
            FileChannel targetChannel = (FileChannel) Files.newByteChannel(target, READ, WRITE, CREATE_NEW)
        ) {

            ByteBuffer sourceBb = ByteBuffer.allocate(64);
            sourceChanel.map(FileChannel.MapMode.READ_WRITE, 0, sourceChanel.size());

            do {
            } while(sourceChanel.read(sourceBb) != -1);


        } catch (IOException e) {
            System.out.printf("can't read file '%s'", sourcePath);
        }


        /*2 читаем хабр http://habrahabr.ru/post/244531/*/






    }

}
