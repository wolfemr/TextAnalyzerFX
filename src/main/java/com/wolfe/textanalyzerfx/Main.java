package com.wolfe.textanalyzerfx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main extends Application {

    @Override // method to create the stage
    public void start(Stage stage) {
        try {
            // fxml file in src/main/resources/com/wolfe/textanalyzerfx/
            FXMLLoader root = new FXMLLoader(Main.class.getResource("Main.fxml"));
            Scene scene = new Scene(root.load());
            stage.setTitle("Raven Words");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates an ObservableArrayList for JavaFX Tableview
     *
     * @return list
     */
    public static ObservableList<Words> getObservableList() {

        ObservableList<Words> list = FXCollections.observableArrayList();
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/word_occurrences", "root", "1234");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from word");

            // Populate the list with the resultSet from the db query

            while (resultSet.next()) {
                list.add(new Words(resultSet.getString("word"), resultSet.getInt("occurrence")));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    public static void main(String[] args) throws FileNotFoundException {
        TextAnalyzerFX.addToDatabase(TextAnalyzerFX.sortWords(TextAnalyzerFX.parseText()));
        launch(args);
    }
}
