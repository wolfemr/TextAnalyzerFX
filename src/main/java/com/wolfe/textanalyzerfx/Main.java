package com.wolfe.textanalyzerfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

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

    public static void main(String[] args) throws FileNotFoundException {

        launch(args);
    }
}
