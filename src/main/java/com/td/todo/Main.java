package com.example.todo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Init bdd
        DatabaseManager.initializeDatabase();

        // charge le FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/todo-view.fxml"));
        Scene scene = new Scene(loader.load(), 800, 400);

        // Ajout css
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        stage.setTitle("To-Do List Améliorée");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
