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

public class TextAnalyzerController implements Initializable{
    @FXML
    private TableView<Words> table;
    @FXML
    private TableColumn<Words, String> word;
    @FXML
    private TableColumn<Words, Integer> count;
    Map<String, Integer> topTwentyWords = TextAnalyzerFX.sortWords(TextAnalyzerFX.parseText());
    private final ArrayList<Words> wordsArrayList = new ArrayList<>(TextAnalyzerFX.createWordObjects(topTwentyWords));

    public TextAnalyzerController() throws FileNotFoundException {
    }
    ObservableList<Words> list = FXCollections.observableArrayList(wordsArrayList);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void showTable(ActionEvent event) {
        word.setCellValueFactory(new PropertyValueFactory<Words, String>("word"));
        count.setCellValueFactory(new PropertyValueFactory<Words, Integer>("count"));
        table.setItems(list);
    }
}
