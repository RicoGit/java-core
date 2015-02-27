package com.java.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.channels.FileChannel.MapMode.READ_WRITE;
import static java.nio.file.StandardOpenOption.*;

/**
 * Project: Shamrock Web Portal.
 * User: Solovev Constantine
 * Date: 30.11.14
 * Time: 12:16
 */

public class MainTemp {

    public static void main(String[] args) {

        String data = "Write this text! \n";
        String fileName = "/opt/file";
//        writeToFile("1 " + data, fileName);
//        appendToFile("2 " + data, fileName);
//        appendToFile("3 " + data, fileName);
        fileWatcher("/home/rico");

    }

    /**
     * вариант со стримами
     * создает файл если не создан и перетирает все содержимое строкой data
     * @param data
     * @param fileName
     */
    //
    private static void writeToFile(String data, String fileName) {

        try ( OutputStream outputStream = Files.newOutputStream(Paths.get(fileName), WRITE, CREATE) ){
            outputStream.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Это вариант с каналом (без маппинга)
     * дописывает data в конец файла
     * @param data
     * @param fileName
     */
    private static void appendToFile(String data, String fileName) {

        // открыл канал для записи (append - дописывания) файла
        try( FileChannel fileChannel = (FileChannel) Files.newByteChannel(Paths.get(fileName), APPEND, CREATE) ) {

            byte[] dataByteArray = data.getBytes();
            // создаю буфер для записи в файл
            ByteBuffer buffer = ByteBuffer.allocate(dataByteArray.length);
            // записываю в буфер строку data как массив байтов
            buffer.put(dataByteArray);
            // после записи массива байтов нужно вернуть указатель буфера на начало
            buffer.rewind();
            // записываю через кана буфер в файл
            fileChannel.write(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Это вариант с каналом с маппингом
     * дописывает data в конец файла
     * @param data
     * @param fileName
     */
    private static void appendToFile2(String data, String fileName) {

        // второй способ получить канал на файл
        try ( FileChannel fileChannel = FileChannel.open(Paths.get(fileName), APPEND, CREATE) ){

            byte[] dataByteArray = data.getBytes();
            // маппинг файла и буфера байтов
            MappedByteBuffer mappedByteBuffer = fileChannel.map(READ_WRITE, 0, dataByteArray.length);
            // запись в буффер (и соответсвенно в файл)
            mappedByteBuffer.put(dataByteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // not finished
    private static void fileWatcher(String folderName) {

        FileVisitor<Path> fileVisitor = new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                System.out.println(exc.getMessage());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                if (!Files.isDirectory(file)) {
                    return FileVisitResult.CONTINUE;
                }

                System.out.println(file.getFileName());
                return super.visitFile(file, attrs);
            }
        };

        Path folderPath = Paths.get(folderName);

        try {
            Path path = Files.walkFileTree(folderPath, fileVisitor);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}


