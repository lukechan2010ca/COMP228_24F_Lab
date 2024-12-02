package com.example.chiwochan_comp228lab5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.util.Objects;

import org.kordamp.bootstrapfx.BootstrapFX;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        GridPane root = fxmlLoader.load();
        Image icon = new Image("https://upload.wikimedia.org/wikipedia/en/c/cc/JavaFX_Logo.png");
        Scene scene = new Scene(root,810, 612);

        // Set minimum window dimensions
        stage.setMinWidth(650);  // Set the minimum width
        stage.setMinHeight(380); // Set the minimum height

        stage.setMaxWidth(1200);  // Set the minimum width
        stage.setMaxHeight(1000); // Set the minimum height

        stage.getIcons().add(icon);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/style.css")).toExternalForm()); // Add Stylesheet to the scene
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet()); // Add BootstrapFX stylesheet to the scene

        stage.setTitle("Player Score");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}