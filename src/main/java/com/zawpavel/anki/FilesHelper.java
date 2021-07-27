package com.zawpavel.anki;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FilesHelper {

    public static List<String> readAllLines(String wordsFilePath) {
        final Path path = Path.of(wordsFilePath);
        List<String> initialString = null;
        try {
            initialString = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("Couldn't open the file: " + wordsFilePath + "\n");
            e.printStackTrace();
        }
        return initialString;
    }

    public static void writeLinesToResultFile(List<String> lines) {
        try {
            Files.write(Path.of("result"), lines, StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("Couldn't write to result file");
            e.printStackTrace();
        }
    }
}
