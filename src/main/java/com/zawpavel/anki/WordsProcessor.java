package com.zawpavel.anki;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordsProcessor {

    private final String wordsFilePath;
    private final static Pattern pattern = Pattern.compile("(.+) - (.+)");

    public WordsProcessor(final String wordsFilePath) {
        this.wordsFilePath = wordsFilePath;
    }

    public void process() {
        final var lines = FilesHelper.readAllLines(wordsFilePath);

        final Predicate<String> hasDelimiter = line -> line.contains("-");
        Map<Boolean, List<String>> linesGroupedByDelimiter = lines.stream().collect(Collectors.partitioningBy(hasDelimiter));

        final var linesWithDelimiter = linesGroupedByDelimiter.get(Boolean.TRUE);
        final var result = linesWithDelimiter.stream()
                .flatMap(line -> Stream.of(line, getReversedString(line)))
                .collect(Collectors.toList());
        FilesHelper.writeLinesToResultFile(result);

        final var linesWithoutDelimiter = linesGroupedByDelimiter.get(Boolean.FALSE);
        if (!linesWithoutDelimiter.isEmpty()) {
            System.out.println("Couldn't process these lines without delimiter:");
            linesWithoutDelimiter.forEach(System.out::println);
        }
    }

    private String getReversedString(final String line) {
        Matcher matcher = pattern.matcher(line);
        if (!matcher.find()) {
            System.out.println("Pattern matching is failed for line: " + line);
        }
        return matcher.group(2) + " - " + matcher.group(1);
    }
}
