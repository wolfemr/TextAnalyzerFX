package com.wolfe.textanalyzerfx;
/*
 * Michael Wolfe
 * Valencia College CEN3024C-17177
 * 11-September-2022
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Contains the logic for parsing a text file to ultimately determine
 * the top 20 most frequently occurring words in the text file.
 *
 * @author Michael Wolfe
 */

public class TextAnalyzerFX {

    /**
     * Parses through a text file to remove and format the following:
     *<ul>
     *    <li>Remove all HTML tags</li>
     *    <li>Remove punctuation marks, including em-dashes and curly quotes</li>
     *    <li>Sets all letters to lowercase</li>
     *    <li>Splits all words at the space character</li>
     *    <li>Removes all singleton lines</li>
     *</ul>
     *Parsed words are then added to a HashMap, incrementing their occurrence as they are added.
     *
     * @return wordObjects HashMap&lt;String, Integer&gt;
     * @throws FileNotFoundException if file is missing
     */
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

    /**
     * Creates Words objects from provided Map and returns an ArrayList of the objects.
     *
     * @param wordList Map&lt;String, Integer&gt;
     * @return wordObjects ArrayList of type Words
     */
    public static ArrayList<Words> createWordObjects(Map<String, Integer> wordList) {

        ArrayList<Words> wordObjects = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : wordList.entrySet()) {
            Words word = new Words(entry.getKey(), entry.getValue());
            wordObjects.add(word);
        }
        return wordObjects;
    }

    /**
     * Sorts the provided words by value and limits the returned amount by the specified limit.
     *
     * @param wordCount&lt;String, Integer&gt;
     * @return sorted HashMap
     */
    public static Map<String, Integer> sortWords(HashMap<String, Integer> wordCount) {
        return wordCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(20).collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue, (value1, value2) -> value1, LinkedHashMap::new));
    }

    /**
     * Method addToDatabase adds the contents of the wordCount parameter to an established database.
     *
     * The method deletes and replaces the rows of the database when called to ensure the database does not
     * grow unnecessarily.
     *
     * @param wordCount
     * @author Michael Wolfe
     */

    public static void addToDatabase(Map<String, Integer> wordCount) {

        // create connection to database driver
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/word_occurrences", "root", "1234");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from word");

            // delete existing rows in database
            for (int i = 1; i <=20; i++) {
                statement.executeUpdate("DELETE FROM `word_occurrences`.`word` WHERE (`word_id` = '"+i+"')");
            }

            int rowID = 0;

            // add contents of Map to database
            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {

                rowID += 1;

                String query = "INSERT INTO word(word_id, word, occurrence) VALUES('"+rowID+"', '"+entry.getKey()+"', '"+entry.getValue()+"')";
                statement.executeUpdate(query);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
