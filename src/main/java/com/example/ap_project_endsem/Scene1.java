package com.example.ap_project_endsem;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Scene1 extends Application {
    private boolean isVolumeOn = true;
    private int HighScore = 0;
    private int CurrentScore = 0;
    private double lastPillarEndX= 0.0;
    private int intialPillar  = 0;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        welcomeScreen(stage);
    }

    private void welcomeScreen(Stage stage) {
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
        double centerY = 80;

        stickText.setX(centerX);
        stickText.setY(centerY);

        heroText.setX(centerX + 10);
        heroText.setY(centerY + 2 * stickTextHeight / 3);

        root.getChildren().addAll(stickText, heroText);

        // Create "PLAY" button
        createPlayButton(root, scene, stage);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    private void ExitScreen(Stage stage) {
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
        double centerY = 80;

        stickText.setX(centerX);
        stickText.setY(centerY);

        heroText.setX(centerX + 10);
        heroText.setY(centerY + 2 * stickTextHeight / 3);

        Text CurrScore = new Text(Integer.toString(CurrentScore));
        CurrScore.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        CurrScore.setY(stickTextHeight + 20); // Set Y position below the Stick text

        CurrScore.setX(400);
        CurrScore.setY(230);

        Text currtext = new Text("Current Score");
        currtext.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        currtext.setY(stickTextHeight + 20); // Set Y position below the Stick text
        currtext.setX(80);
        currtext.setY(230);

        Text HighScore = new Text(Integer.toString(CurrentScore));
        HighScore.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        HighScore.setY(stickTextHeight + 20); // Set Y position below the Stick text

        HighScore.setX(400);
        HighScore.setY(350);

        Text hightext = new Text("High Score");
        hightext.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        hightext.setY(stickTextHeight + 20); // Set Y position below the Stick text
        hightext.setX(80);
        hightext.setY(350);

        root.getChildren().addAll(stickText, heroText,CurrScore,currtext,hightext,HighScore);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    private void createPlayButton(Group root, Scene scene, Stage stage) {
        // Create a red circle (1.5 times larger)
        double circleRadius = 50 * 1.2;
        Circle circle = new Circle(circleRadius, Color.RED);
        circle.setCenterX(scene.getWidth() / 2-4);
        circle.setCenterY(scene.getHeight() / 2 +45); // Adjust the vertical position

        // Create white text inside the circle
        Text playText = new Text("PLAY");
        playText.setFont(Font.font("Arial", 35));
        playText.setFill(Color.WHITE);
        playText.setX(circle.getCenterX() - playText.getLayoutBounds().getWidth() / 2);
        playText.setY(circle.getCenterY() + playText.getLayoutBounds().getHeight() / 4);
        // Add event handler to switch to Scene2 when the button is clicked
        circle.setOnMouseClicked(event -> showScene2(stage));
        playText.setOnMouseClicked(event -> showScene2(stage));

        TranslateTransition floatTransition = new TranslateTransition(Duration.seconds(1), circle);
        floatTransition.setByY(8);
        floatTransition.setCycleCount(TranslateTransition.INDEFINITE);
        floatTransition.setAutoReverse(true);
        floatTransition.play();

        TranslateTransition floatTransition1 = new TranslateTransition(Duration.seconds(1), playText);
        floatTransition1.setByY(8);
        floatTransition1.setCycleCount(TranslateTransition.INDEFINITE);
        floatTransition1.setAutoReverse(true);
        floatTransition1.play();

        Image btexitimg = new Image("btexit.png");
        BackgroundImage btexitimgbg = new BackgroundImage(btexitimg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, false, false, true, true));
        Background btexitbg = new Background(btexitimgbg);
        Button btEndGame = new Button("");
        btEndGame.setBackground(btexitbg);
        btEndGame.setShape(new Circle(50 * 2.6));
        btEndGame.setMaxSize(50 * 2.6, 50 * 2.6);
        btEndGame.setPrefSize(50 * 2.6, 50 * 2.6);
        btEndGame.setLayoutX((scene.getWidth()) / 2 -69);
        btEndGame.setLayoutY((scene.getHeight())-165);

        Image btvolOnImg = new Image("btvol_on.png");
        Image btvolOffImg = new Image("btvol_off.png");

        BackgroundImage btvolimgbg = new BackgroundImage(btvolOnImg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, false, false, true, true));
        Background btvolbg = new Background(btvolimgbg);
        Button btvolGame = new Button("");
        btvolGame.setBackground(btvolbg);
        btvolGame.setShape(new Circle(50 * 2.57));
        btvolGame.setMaxSize(50 * 2.57, 50 * 2.57);
        btvolGame.setPrefSize(50 * 2.57, 50 * 2.57);
        btvolGame.setLayoutX((scene.getWidth()) / 2 -67.3);
        btvolGame.setLayoutY(135);

        Image pauseBtnImage = new Image("Pausebutton.png");
        ImageView pauseButton = new ImageView(pauseBtnImage);
        double iconSize = (3.0 / 40.0) * scene.getWidth();
        pauseButton.setFitWidth(iconSize);
        pauseButton.setFitHeight(iconSize);
        pauseButton.setOnMouseClicked(event -> ExitScreen(stage));

        // Set the left margin
        double leftMargin = 10;
        double topMargin = 10;
        pauseButton.setX(leftMargin);
        pauseButton.setY(topMargin);


        root.getChildren().addAll(circle, playText,btEndGame, btvolGame,pauseButton);

        btvolGame.setOnAction(event -> {
            // Toggle volume state
            isVolumeOn = !isVolumeOn;

            // Change background image based on volume state
            if (isVolumeOn) {
                btvolGame.setBackground(new Background(new BackgroundImage(btvolOnImg,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        new BackgroundSize(1.0, 1.0, false, false, true, true))));
            } else {
                btvolGame.setBackground(new Background(new BackgroundImage(btvolOffImg,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        new BackgroundSize(1.0, 1.0, false, false, true, true))));
            }
        });

        // Apply hover animation to buttons
        applyHoverAnimation(btEndGame);
        applyHoverAnimation(btvolGame);

        // Add action handler for btEndGame button
        btEndGame.setOnAction(event -> {
            // Close the application
            Platform.exit();
        });

    }
    private void applyHoverAnimation(Button button) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), button);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);

        button.setOnMouseEntered(event -> {
            scaleTransition.setRate(1.0);
            scaleTransition.play();
        });

        button.setOnMouseExited(event -> {
            scaleTransition.setRate(-1.0);
            scaleTransition.play();
        });
    }

    private void showScene2(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 600, Color.LIGHTSKYBLUE);

        // Load background image (same as in Scene1)
        Image gamebg = new Image("gamebg.jpg");
        ImageView bg = new ImageView(gamebg);
        bg.fitWidthProperty().bind(scene.widthProperty());
        bg.fitHeightProperty().bind(scene.heightProperty());
        root.getChildren().add(bg);

        // Pause Button
        Image pauseBtnImage = new Image("Pausebutton.png");
        ImageView pauseButton = new ImageView(pauseBtnImage);
        double iconSize = (3.0 / 40.0) * scene.getWidth();
        pauseButton.setFitWidth(iconSize);
        pauseButton.setFitHeight(iconSize);
        pauseButton.setOnMouseClicked(event -> welcomeScreen(stage));

        // Set the left margin
        double leftMargin = 10;
        double topMargin = 10;
        pauseButton.setX(leftMargin);
        pauseButton.setY(topMargin);

        // Hero Image
        Image hero = new Image("heroim.png");
        ImageView heroim = new ImageView(hero);
        double heroSize = (2.0 / 40.0) * scene.getWidth();
        heroim.setFitWidth(heroSize);
        heroim.setFitHeight(heroSize);



        // Generate the first pillar
        PillarInfo firstPillarInfo = generateRandomPillar(root, scene.getWidth(), heroSize);
        setHeroPosition(heroim, firstPillarInfo);
        // Generate the second pillar
        PillarInfo secondPillarInfo = generateRandomPillar(root, scene.getWidth(), heroSize);
        //setHeroPosition(heroim, secondPillarInfo);

        root.getChildren().addAll(heroim, pauseButton);
        stage.setScene(scene);
    }

    // The code requires scene to be rectangle only it is a dependent code
    private PillarInfo generateRandomPillar(Group root, double sceneWidth, double heroSize) {
        double minPillarWidth = 1.5 * heroSize; // Minimum pillar width
        double maxPillarWidth = 0.2 * sceneWidth; // Maximum pillar width

        // Ensure minimum and maximum constraints
        double startX;
        double endX;
        if(intialPillar == 0){
            startX = 0.0;
            endX = startX + Math.max(minPillarWidth, Math.random() * (Math.min(maxPillarWidth, sceneWidth / 2 - startX)));

        }

        else if (lastPillarEndX == 0.0) {
            // For the first pillar, start from the left
            startX = 0.0;
            endX = startX + Math.max(minPillarWidth, Math.random() * (Math.min(maxPillarWidth, sceneWidth / 2 - startX)));
        }
        else {
            // For subsequent pillars, start from the last pillar's end
            startX = lastPillarEndX + Math.random() * 20; // Adjust for spacing
            endX = startX + Math.max(minPillarWidth, Math.random() * (Math.min(maxPillarWidth, sceneWidth / 2 - startX)));
        }

        double startY = 6.0 / 10.0 * sceneWidth; // Fixed y position
        double endY = sceneWidth; // Fixed y position at the bottom of the screen

        // Create the pillar
        double pillarWidth = endX - startX;
        double pillarHeight = endY - startY;
        Rectangle pillar = new Rectangle(startX, startY, pillarWidth, pillarHeight);
        pillar.setFill(Color.BLACK); // You can change the color as needed

        root.getChildren().add(pillar);

        // Update the lastPillarEndX
        lastPillarEndX = endX;

        // Return information about the generated pillar
        return new PillarInfo(startX, endX, startY, endY);
    }

    private void setHeroPosition(ImageView heroim, PillarInfo pillarInfo) {
        // Set the hero's position based on the last generated pillar
        double heroX = pillarInfo.getEndX() - heroim.getFitWidth() ;
        double heroY = pillarInfo.getStartY() - 30; // reducing 30 to align top of pillar and base of hero
        heroim.setX(heroX);
        heroim.setY(heroY);
    }

    // A simple class to hold information about the generated pillar
    private static class PillarInfo {
        private final double startX;
        private final double endX;
        private final double startY;
        private final double endY;

        public PillarInfo(double startX, double endX, double startY, double endY) {
            this.startX = startX;
            this.endX = endX;
            this.startY = startY;
            this.endY = endY;
        }

        public double getStartX() {
            return startX;
        }

        public double getEndX() {
            return endX;
        }

        public double getStartY() {
            return startY;
        }

        public double getEndY() {
            return endY;
        }
    }

}
