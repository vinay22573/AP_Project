package com.example.ap_project_endsem;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Scene2 extends Application {
    private Group root;
    private Scene scene;

    public Scene2() {
        root = new Group();
        scene = new Scene(root, 600, 600, Color.LIGHTSKYBLUE);

        // Load the same background image
        Image image = new Image("gamebg.jpg");
        ImageView imageView = new ImageView(image);
        imageView.fitWidthProperty().bind(scene.widthProperty());
        imageView.fitHeightProperty().bind(scene.heightProperty());
        root.getChildren().add(imageView);

        // Additional elements for Scene2 can be added here
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    public Scene getScene() {
        return scene;
    }
}
