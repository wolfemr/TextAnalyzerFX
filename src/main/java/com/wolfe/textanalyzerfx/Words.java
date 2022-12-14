package com.wolfe.textanalyzerfx;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Basic class for handling Words objects, which are comprised of a word(String) and
 * a count of the word's occurrence(int)
 *
 * @author Wolfe
 */

public class Words {
    private final SimpleStringProperty word;
    private final SimpleIntegerProperty count;
    private final ObservableList<Words> list = FXCollections.observableArrayList();

    public Words() {
        word = new SimpleStringProperty(this, "word");
        count = new SimpleIntegerProperty(this, "count");
    }

    public Words(String word, int count) {
        this();
        setWord(word);
        setCount(count);
    }

    public String getWord() {

        return word.get();
    }

    public void setWord(String word) {

        this.word.set(word);
    }

    public int getCount() {

        return count.get();
    }

    public void setCount(int count) {

        this.count.set(count);
    }

    @Override
    public String toString() {

        return ("word=" + word + ", count=" + count + ", list=" + list);
    }
}
