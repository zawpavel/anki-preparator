package com.zawpavel.anki;

public class AnkiPreparatorMain {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("The path to the file as the first parameter is required");
        }
        final var wordsProcessor = new WordsProcessor(args[0]);
        wordsProcessor.process();
    }
}
