package com.wolfe.textanalyzerfx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordsTest {

    // create object to be used for testing
    private final Words testWord = new Words("test", 99);

    // validate getWord method
    @Test
    void getWord() {

        assertEquals("test", testWord.getWord());
    }

    // validate getCount method
    @Test
    void getCount() {

        assertEquals(99, testWord.getCount());
    }

    // validate setCount method
    @Test
    void changeCount() {
        testWord.setCount(88);
        assertEquals(88, testWord.getCount());
    }

    // validate setWord method
    @Test
    void changeWord() {
        testWord.setWord("test1");
        assertEquals("test1", testWord.getWord());
    }

}