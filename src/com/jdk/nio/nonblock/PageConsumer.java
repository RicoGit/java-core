package com.jdk.nio.nonblock;

import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

/**
 * User: Constantine Solovev
 * Created: 22.03.15  12:21
 */


public class PageConsumer {

    private static final String DESTINATION_FOLDER = "/home/rico/webdev/core/resources/";
    private Map<String, Integer> wordCount = new HashMap<>(10000);

    private final PageStore store;
    private final int numberOfPage;

    public PageConsumer(PageStore store, int numberOfPage) {
        this.store = store;
        this.numberOfPage = numberOfPage;
    }

    public void process(){

        for (int i = 0; i < numberOfPage; i++) {

            System.out.println("- process() waiting ...");

            Page page = store.take();
            String content = page.getContent();

            writeToFile(String.valueOf(page.getNumber()), content);
            processContent(content);

            System.out.println("- \t processing success " + page.getNumber() + i);
        }

        System.out.println("getTopWords most popular words");

        System.out.println(getTopWords(wordCount));

    }

    private List<Pair<String, Integer>> getTopWords(Map<String, Integer> wordCount) {

        List<Map.Entry<String, Integer>> arrayList = new ArrayList<>(wordCount.entrySet());

        Collections.sort(arrayList, (entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()));

        return arrayList.stream()
                .limit(30)
                .map(entry -> new Pair<>(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

    }


    // map-reduce
    private void processContent(String body) {
        Stream.of(body.split("\\s"))
                .forEach(word -> {
                    wordCount.compute(word, (key, value) ->  value == null ?  1 : value + 1);
                });

    }

    private void writeToFile(String fileName, String body)  {
        try {
            Files.write(Paths.get(DESTINATION_FOLDER + fileName + ".txt"), body.getBytes(), CREATE, WRITE);
        } catch (IOException e) {
            System.out.printf("file %s create failure", fileName);
        }
    }
}
