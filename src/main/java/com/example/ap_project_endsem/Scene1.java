package com.example.ap_project_endsem;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Scene1 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 600, Color.LIGHTSKYBLUE);

        // Load background image
        Image image = new Image("gamebg.jpg");
        ImageView imageView = new ImageView(image);
        imageView.fitWidthProperty().bind(scene.widthProperty());
        imageView.fitHeightProperty().bind(scene.heightProperty());
        root.getChildren().add(imageView);

        // Create Stick text
        Text stickText = new Text("STICK");
        stickText.setFont(Font.font("Arial", FontWeight.BOLD, 60));
        double stickTextHeight = stickText.getLayoutBounds().getHeight();

        // Create Hero text
        Text heroText = new Text("HERO");
        heroText.setFont(Font.font("Arial", FontWeight.BOLD, 55));
        heroText.setY(stickTextHeight + 20); // Set Y position below the Stick text

        // Center the text within the image background
        double textWidth = Math.max(stickText.getLayoutBounds().getWidth(), heroText.getLayoutBounds().getWidth());
        double centerX = (scene.getWidth() - textWidth) / 2;
        double centerY = 100;

        stickText.setX(centerX);
        stickText.setY(centerY);

        heroText.setX(centerX+10);
        heroText.setY(centerY + 2*stickTextHeight/3);

        root.getChildren().addAll(stickText, heroText);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
