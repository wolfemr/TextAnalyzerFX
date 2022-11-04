package com.wolfe.textanalyzerfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

/**
 * TextAnalyzerController contains the logic for generating the JavaFX
 * implementation for the TextAnalyzerFX application.
 *
 * @author Wolfe
 */

public class TextAnalyzerController implements Initializable{
    @FXML
    private TableView<Words> table;
    @FXML
    private TableColumn<Words, String> word;
    @FXML
    private TableColumn<Words, Integer> count;

    // create Map of top twenty words by calling sortWords() with output of parseText()
    Map<String, Integer> topTwentyWords = TextAnalyzerFX.sortWords(TextAnalyzerFX.parseText());

    // create ArrayList from Map for use with ObservableList JavaFX functionality
    private final ArrayList<Words> wordsArrayList = new ArrayList<>(TextAnalyzerFX.createWordObjects(topTwentyWords));

    public TextAnalyzerController() throws FileNotFoundException {
    }

    // create ObservableList for use with JavaFX TableView
    ObservableList<Words> list = FXCollections.observableArrayList(wordsArrayList);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    // populate TableView with ObservableList object
    @FXML
    public void showTable(ActionEvent event) {
        word.setCellValueFactory(new PropertyValueFactory<>("word"));
        count.setCellValueFactory(new PropertyValueFactory<>("count"));
        table.setItems(list);

    }
}
