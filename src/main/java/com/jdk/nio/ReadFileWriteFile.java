package com.jdk.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

/**
 * User: Solovev Constantine
 * Date: 30.11.14
 * Time: 10:57
 */

public class ReadFileWriteFile {

    public static void main(String[] args) {

        /* читаем из файла в файл */

        String data = "Это нужно записать в source.file !!!\n IMPORTANT!";

        String sourcePath = "resources/source.file";
        String targetPath = "resources/target.file";

        File sourceFile = new File(sourcePath);
        File targetFile = new File(targetPath);

        try {
            // проверяем и создаем файл
            if (sourceFile.exists() || sourceFile.createNewFile()) {
                // запишем в первый файл данные
                Files.write(sourceFile.toPath(), data.getBytes(), WRITE, CREATE);
            } else {
                System.out.printf("can't create %s file", sourceFile.getName() );
            }
        } catch (IOException e) {
            System.out.printf("write data to file failed cause: %s \n", e);
        }

        try(
            FileChannel sourceChanel = new FileInputStream(sourceFile).getChannel();
            FileChannel targetChannel = new FileOutputStream(targetFile).getChannel()
        ) {

            MappedByteBuffer sourceMappedBb = sourceChanel.map(FileChannel.MapMode.READ_ONLY, 0, sourceChanel.size());
            targetChannel.write(sourceMappedBb);

        } catch (IOException e) {
            System.out.printf("can't read file '%s'", sourcePath);
        }

    }

}
