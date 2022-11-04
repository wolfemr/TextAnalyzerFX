package com.wolfe.textanalyzerfx;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test implementation for the TextAnalyzerFX class by verifying
 * that the sortWords method can accurately sort the entries present in
 * a provided HashMap.
 *
 * @author Wolfe
 */

class TextAnalyzerFXTest {

    // create HashMap to test sortWords method of TextAnalyzerFX.java
    public static HashMap<String, Integer> testHashMap() {
        HashMap<String, Integer> testMap = new HashMap<>();
        testMap.put("xyz", 2);
        testMap.put("qrs", 1);
        testMap.put("abc", 3);
        return testMap;
    }

    // create comparison HashMap to verify output of sortWords method of TextAnalyzerFX.java
    public static HashMap<String, Integer> baseHashMap() {
        HashMap<String, Integer> baseMap = new HashMap<>();
        baseMap.put("abc", 3);
        baseMap.put("xyz", 2);
        baseMap.put("qrs", 1);
        return baseMap;
    }

    // validate output of sortWords method of TextAnalyzerFX.java
    @Test
    void sortWords() {

        assertEquals(baseHashMap(), TextAnalyzerFX.sortWords(testHashMap()));
    }


}