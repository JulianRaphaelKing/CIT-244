package com.example.javafxcit244;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SearchEngine extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        // Create Nodes
        // Title (Label)
        Label title = new Label("Search for an Image");
        title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 25));

        // Search Field (TextField)
        TextField searchTF = new TextField();

        // Search Button (Button)
        Button searchButton = new Button("Search");
        searchButton.setPrefWidth(100);

        // Lambda functionality for search button
        searchButton.setOnAction((e) -> {
            System.out.println("Search button pressed");
        });


        // Container to hold all nodes in the top of the pane
        VBox topBox = new VBox(10);
        // Add nodes to the VBox
        topBox.setAlignment(Pos.CENTER);
        topBox.getChildren().addAll(title, searchTF, searchButton);

        // Add nodes to pane
        pane.setTop(topBox);

        Scene scene = new Scene(pane, 500, 500);

        primaryStage.setTitle("Crappy Search Engine");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}