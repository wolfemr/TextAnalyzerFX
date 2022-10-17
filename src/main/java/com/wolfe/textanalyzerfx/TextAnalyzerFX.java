package com.wolfe.textanalyzerfx;
/*
 * Michael Wolfe
 * Valencia College CEN3024C-17177
 * 11-September-2022
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class TextAnalyzerFX {

    public static HashMap<String, Integer> parseText() throws FileNotFoundException {

            HashMap<String, Integer> wordsWithCounts = new HashMap<>(); // create hash map to hold strings plus their count
            ArrayList<String> words = new ArrayList<>(); // create ArrayList to hold individual words
            Scanner fileScanner = new Scanner(new FileReader // read file to Scanner object
                    ("src/main/java/com/wolfe/textanalyzerfx/" +
                    "The Project Gutenberg eBook of The Raven, by Edgar Allan Poe.htm"));

            // parse the file line by line
            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine().replaceAll("<.*?>", "") // remove HTML tags
                        .replaceAll("\\p{Punct}", "") // remove punctuation
                        .replaceAll("—", " ") // remove em-dashes
                        .replaceAll("“*”*’*’*", "") // remove 'curly' quotes
                        .toLowerCase(); // set all words to lower case

                // split the lines to words via space character
                String[] wordsInLine = line.split("\s");
                words.addAll(Arrays.asList(wordsInLine));
            }

            // remove all empty lines
            words.removeAll(Collections.singleton(""));

            // convert array list to array
            String[] wordsArray = words.toArray(new String[0]);

            // Poem lies between indexes 258-1349(inclusive) after all removals,
            // not sure of less hacky way to do without magic numbers without using an external library like jsoup
            // but wanted to avoid dependencies if possible
            // add contents of wordsArray to wordCount hashmap
            // increment values for each word
            for (int i = 258; i <= 1349; i++) {
                wordsWithCounts.put(wordsArray[i], wordsWithCounts.getOrDefault(wordsArray[i], 0) + 1);
            }
            return wordsWithCounts;
    }

    public static ArrayList<Words> createWordObjects(Map<String, Integer> wordList) {

        ArrayList<Words> wordObjects = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : wordList.entrySet()) {
            Words word = new Words(entry.getKey(), entry.getValue());
            wordObjects.add(word);
        }
        return wordObjects;
    }

    // sort wordCount entries by value to new map
    public static Map<String, Integer> sortWords(HashMap<String, Integer> wordCount) {
        return wordCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(20).collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue, (value1, value2) -> value1, LinkedHashMap::new));
    }
}
