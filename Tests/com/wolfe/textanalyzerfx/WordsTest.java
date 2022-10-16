package com.wolfe.textanalyzerfx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordsTest {
    private final Words testWord = new Words("test", 99);

    @Test
    void getWord() {

        assertEquals("test", testWord.getWord());
    }

    @Test
    void getCount() {

        assertEquals(99, testWord.getCount());
    }

    @Test
    void changeCount() {
        testWord.setCount(88);
        assertEquals(88, testWord.getCount());
    }

    @Test
    void changeWord() {
        testWord.setWord("test1");
        assertEquals("test1", testWord.getWord());
    }

}