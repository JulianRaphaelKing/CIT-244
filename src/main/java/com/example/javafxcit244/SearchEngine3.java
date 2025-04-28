package com.example.javafxcit244;

import Jwiki.Jwiki;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SearchEngine3 extends Application {

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


        
        // More Info Button
        Button infoButton = new Button("More Information");
        infoButton.setVisible(false);
        // Lambda for info button
        infoButton.setOnAction((e) -> {
            getInfo(title.getText(), primaryStage);
        });


        searchTF.setOnKeyPressed((e) -> {
            if(e.getCode() == KeyCode.ENTER){
                System.out.println("Searching for " + searchTF.getText());
                setTitle(searchTF, title);
                // Set the image to the center
                pane.setCenter(setImage(title.getText(), infoButton));
                searchTF.clear();
                searchTF.requestFocus();
            }
        });

        // Search Button (Button)
        Button searchButton = new Button("Search");
        searchButton.setPrefWidth(100);
        
        // Lambda functionality for search button
        searchButton.setOnAction((e) -> {
            System.out.println("Searching for " + searchTF.getText());
            setTitle(searchTF, title);
            // Set the image to the center
            pane.setCenter(setImage(title.getText(), infoButton));
            searchTF.clear();
            searchTF.requestFocus();
        });
 
        
        // Container to hold all nodes in the top of the pane
        VBox topBox = new VBox(10);
        // Add nodes to the VBox
        topBox.setAlignment(Pos.CENTER);
        topBox.getChildren().addAll(title, searchTF, searchButton, infoButton);
        
        // Add nodes to pane
        pane.setTop(topBox);
        
        Scene scene = new Scene(pane, 500, 500);
        
        primaryStage.setTitle("Crappy Search Engine");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public static void setTitle(TextField tf, Label title) {
        tf.setText(tf.getText().trim());
        if (tf.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Search bar cannot be empty.");
            alert.show(); 
        } else {
           title.setText(tf.getText());
        }
    }
    
    public static ImageView setImage(String search, Button infoButton) {
        String imageURL = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Juvenile_Ragdoll.jpg/1200px-Juvenile_Ragdoll.jpg";
        Color[] colors = {Color.BLUEVIOLET, 
            Color.RED, Color.GOLD, Color.DARKCYAN, Color.CHOCOLATE};
        int random = (int)(Math.random() * colors.length);
        
        DropShadow ds = new DropShadow(500, colors[random]);
        
        try {
            Jwiki jwiki = new Jwiki(search);
            imageURL = jwiki.getImageURL();
            infoButton.setVisible(true);
        } catch(Exception e) {
            infoButton.setVisible(false);
            System.err.println(search + " image not found. So, here's a kitten!");
        }
        
        Image image = new Image(imageURL, 250, 250, false, false);
        ImageView imageView = new ImageView(image);
        imageView.setEffect(ds);
        return imageView;
    }
    
    public static void getInfo(String search, Stage stage) {
        Stage infoStage = new Stage();
        TextArea text = new TextArea();
        
        Jwiki jwiki = new Jwiki(search);
        text.appendText(jwiki.getExtractText());
        text.setWrapText(true);
        infoStage.setTitle(search);

        //Ensure new stage is below current stage
        infoStage.setX(stage.getX());
        infoStage.setY((stage.getY()*4) + 50);

        Scene infoScene = new Scene(text);
        infoStage.setScene(infoScene);
        infoStage.show();
    }
    
}
