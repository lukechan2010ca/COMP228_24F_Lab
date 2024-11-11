package com.example.chiwochan_comp228lab4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        GridPane root = fxmlLoader.load();
        Scene scene = new Scene(root, 950, 400);

        Image icon = new Image("https://upload.wikimedia.org/wikipedia/en/c/cc/JavaFX_Logo.png");
        stage.getIcons().add(icon);

        // Set minimum window dimensions
        stage.setMinWidth(800);  // Set the minimum width
        stage.setMinHeight(375); // Set the minimum height

        stage.setMaxWidth(1200);  // Set the minimum width
        stage.setMaxHeight(450); // Set the minimum height

        stage.setTitle("Student Information Form");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}