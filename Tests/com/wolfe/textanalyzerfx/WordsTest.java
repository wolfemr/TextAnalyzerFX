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

}