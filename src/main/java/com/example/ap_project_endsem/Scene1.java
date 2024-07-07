package com.example.ap_project_endsem;
import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Scene1 extends Application {
    private boolean isVolumeOn = true;
    private boolean isFlipping = false;
    private boolean isFlipped = false;
    private boolean stickbuilded = false;
    private boolean isBuildingStick = false;
    private boolean isMovingHero = false;
    private boolean lastpillar = false;
    private Rectangle lpillar ;
    private Rectangle currpillar ;
    private Rectangle nextpillar ;
    private Rectangle nextnextpillar ;
    int p =0 ;
    private ArrayList<Circle> circles = new ArrayList<>(0);
    private List<Rectangle> pillars = new ArrayList<>();

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


        double circleRadius = 50 * 1.2;
        Circle circle = new Circle(circleRadius, Color.RED);
        circle.setCenterX(scene.getWidth() / 2-150);
        circle.setCenterY(scene.getHeight()-125); // Adjust the vertical position

        // Create white text inside the circle
        Text playText = new Text("PLAY");
        playText.setFont(Font.font("Arial", 35));
        playText.setFill(Color.WHITE);
        playText.setX(circle.getCenterX() - playText.getLayoutBounds().getWidth() / 2);
        playText.setY(circle.getCenterY() + playText.getLayoutBounds().getHeight() / 4);
        // Add event handler to switch to Scene2 when the button is clicked
        circle.setOnMouseClicked(event ->showScene2(stage));
        playText.setOnMouseClicked(event ->showScene2(stage));

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
        btEndGame.setLayoutX((scene.getWidth()) / 2 +100);
        btEndGame.setLayoutY((scene.getHeight())-185);
        applyHoverAnimation(btEndGame);

        // Add action handler for btEndGame button
        btEndGame.setOnAction(event -> {
            // Close the application
            Platform.exit();
        });

        root.getChildren().addAll(stickText, heroText,CurrScore,currtext,hightext,HighScore,circle, playText,btEndGame);
        //createPlayButton(root,scene,stage);
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

    private void buildStick(Rectangle stick) {
        Platform.runLater(() -> {
            double oldY = stick.getY();
            stick.setY(oldY - 2);
            stick.setHeight(stick.getHeight() + 2);
        });
    }

    private void rotateStick(Rectangle stick) {
        double pivotX = stick.getX() + stick.getWidth() / 2; // Set pivotX to the center of the stick
        double pivotY = stick.getY() + stick.getHeight(); // Set pivotY to the bottom of the stick

        Rotate rotate = new Rotate(90, pivotX, pivotY);
        stick.getTransforms().add(rotate);

    }
    private void checkCollisionWithCircles(ImageView hero ,Group root , ArrayList<Circle> circles ) {
        for (Circle circle : circles) {
           // System.out.println(circle.toString());
            if (hero.getBoundsInParent().intersects(circle.getBoundsInParent())) {
                root.getChildren().remove(circle);
                CurrentScore++;
                System.out.println("Score: " + CurrentScore);
            }
        }
    }
    private void moveStickHero(Rectangle stick, ImageView hero, Group root, Stage stage,Scene scene) {
        double startX = stick.getX();
        double endX = stick.getX() + stick.getHeight();
        double heroWidth = hero.getFitWidth();

        // Adjust these values based on your requirements
        double moveStep = 5;
        double fallSpeed = 200;
        double rotationAngle = 90;

        // Animate the hero moving along the stick
        Timeline moveHeroTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.01), event -> {
                    if (hero.getLayoutX() + heroWidth < endX-15) {
                        hero.setLayoutX(hero.getLayoutX() + 1.5);
                        checkCollisionWithCircles(hero,root,circles);

                    }
                })
        );


        moveHeroTimeline.setOnFinished(event -> {
            if (!stickbuilded) {
                Rectangle stickk = new Rectangle(4, 2, Color.BLACK);
                stickk.setX(hero.getX() + 25);
                stickk.setY(hero.getY() + 28);
                Rotate stickRotation = new Rotate(0, 0, 0);
                stickk.getTransforms().add(stickRotation);

                // Set up the timeline for stick building
                Timeline buildTimeline = new Timeline(
                        new KeyFrame(Duration.seconds(0.01), eventt -> buildStick(stickk))
                );
                buildTimeline.setCycleCount(Timeline.INDEFINITE);

                scene.setOnMousePressed(event2 -> {
                    if (!isBuildingStick && isMovingHero) {
                        flipHero(hero);
                        isFlipping = true;  // Set the flipping flag

                    } else if (!isMovingHero && !isBuildingStick) {
                        isBuildingStick = true;
                        buildTimeline.play();
                        stickbuilded = true;
                    }
                });

                scene.setOnMouseReleased(event2 -> {
                    isBuildingStick = false;
                    buildTimeline.pause();

                    if (!isFlipping && !isBuildingStick) {
                        rotateStick(stick);
                        moveStickHero(stick, hero, root, stage,scene);
                        isMovingHero = true;
                    }

                    if (event2.getButton() == MouseButton.SECONDARY) { // Right mouse button
                        // Toggle flipping flag when the right mouse button is released
                        isFlipping = !isFlipping;
                    }
                });
            }

            hero.setX(stick.getX() + stick.getWidth());
            hero.setY(stick.getY() + stick.getHeight());
            if (stick.getX() + stick.getHeight() < currpillar.getX()+currpillar.getWidth()/2 || stick.getX() > nextpillar.getX()-nextpillar.getWidth()/2 ) {
                // Implement the logic for hero falling
                System.out.println("Hero falls!");
                //break; // You may want to break out of the loop or handle falling in some way
            }

            Bounds stickBoundsInScene = stick.localToScene(stick.getBoundsInLocal());
            double stickRightEdge = stickBoundsInScene.getMaxX();

            // Convert nextpillar's local bounds to scene coordinates
            Bounds nextPillarBoundsInScene = nextpillar.localToScene(nextpillar.getBoundsInLocal());
            double nextPillarRightEdge = nextPillarBoundsInScene.getMaxX();
            double nextPillarLeftEdge = nextPillarBoundsInScene.getMinX();

            Bounds nextnextPillarBoundsInScene = nextnextpillar.localToScene(nextnextpillar.getBoundsInLocal());
            double nextnextPillarRightEdge = nextnextPillarBoundsInScene.getMaxX();
            double nextnextPillarLeftEdge = nextnextPillarBoundsInScene.getMinX();

            if (stickRightEdge > nextPillarRightEdge || stickRightEdge < nextPillarLeftEdge) {
                animateFallAndRotate(stick, hero, fallSpeed, rotationAngle, stage);
            } else if (stickRightEdge > nextnextPillarRightEdge || stickRightEdge < nextnextPillarLeftEdge) {
               animateFallAndRotate(stick, hero, fallSpeed, rotationAngle, stage);
            } else {
                // Continue to the next pillar
                p++;
                currpillar = pillars.get(p);
                nextpillar = pillars.get(p + 1);
                nextnextpillar = pillars.get(p + 2);
                stickbuilded=false ;
                moveStickHero(stick, hero, root, stage,scene); // Recursive call for the next pillar
            }
        });
    moveHeroTimeline.setCycleCount(Timeline.INDEFINITE);
        // Start the timeline
        moveHeroTimeline.play();
    }

    private void animateFallAndRotate(Rectangle stick, ImageView hero, double fallSpeed, double rotationAngle, Stage stage) {
        Timeline timeline = new Timeline();

        // Rotation KeyFrame
        KeyFrame rotateKeyFrame = new KeyFrame(Duration.seconds(0.8), event2 -> {
            double angle = stick.getRotate() + rotationAngle; // Adjust the rotation angle as needed
            stick.setRotate(angle);
        });

        // Falling KeyFrame
        KeyFrame fallKeyFrame = new KeyFrame(Duration.seconds(0.8), event2 -> {
            double newY = hero.getTranslateY() + fallSpeed; // Adjust the falling speed as needed
            hero.setTranslateY(newY);
        });

        timeline.getKeyFrames().addAll(rotateKeyFrame, fallKeyFrame);

        timeline.setOnFinished(event2 -> {
            // Stop the animation when finished
            System.out.println("ygucnfuybgr");
            timeline.stop();
            ExitScreen(stage);
        });

        // Set up the exit screen
        stage.setOnHidden(event2 -> {
            // Stop the animation when the stage is hidden or closed
            timeline.stop();
        });

        // Play the timeline
        timeline.play();
    }


    private void flipHero(ImageView hero) {
        if (!isFlipping) {
            isFlipping = true;
            isFlipped=! isFlipped ;
            int q=-1;
            if (isFlipped){
                q=1 ;
            }


            // Flip the hero vertically
            hero.setScaleY(hero.getScaleY() * -1);
            hero.setY(hero.getY()+q*25);

            // Set a small delay to avoid rapid flipping
            Timeline flipTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(0.5), event -> isFlipping = false)
            );
            flipTimeline.play();
        }
    }

    private void showScene2(Stage stage) {
        isBuildingStick=false;
        isMovingHero=false;
        isFlipping=false ;
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
        double screenHeight = stage.getHeight();
        double pillarWidth = 100;
        double minDistanceBetweenPillars = 200;
        double maxDistanceBetweenPillars = 400;
        double totalWidth = 4 * pillarWidth + 3 * minDistanceBetweenPillars;
        double startX = (screenHeight - totalWidth) / 2;
        double maxX = scene.getWidth() - pillarWidth;
        for (int i = 0; i < 4; i++) {
            Rectangle pillar = new Rectangle(50, 300, Color.BLACK);
            pillar.setY(screenHeight - pillar.getHeight()+21);
            double rawX = Math.max(startX + i * (pillarWidth + minDistanceBetweenPillars), 0);

            double adjustedX = Math.min(rawX, maxX);
            pillar.setX(adjustedX);
            System.out.println("pillarx - "+pillar.getX());
            lpillar=pillar ;
            pillars.add(pillar);
            if (i==1){
                nextpillar=pillar ;
            }
            if (i==2){
                nextnextpillar=pillar ;
            }
            if (pillar.getX()>stage.getWidth()){
                lastpillar=true ;
                lpillar=pillar ;
                break ;
            }

            double circleRadius = 2 * 1.8;
            double dist = 100+20*Math.random()+200*i ;
            if (dist < 700){
                Circle circle = new Circle(circleRadius, Color.RED);
                circle.setCenterX(dist);
                double randomValue = Math.random();
                double randomY = ((randomValue < 0.5) ? 40 : -10);
                circle.setCenterY(screenHeight - pillar.getHeight()+randomY);
                circles.add(circle);
                root.getChildren().add(circle);
            }
            root.getChildren().add(pillar);
        }

        Rectangle stick = new Rectangle(4, 2, Color.BLACK);
        stick.setX(heroim.getX()+25);
        stick.setY(heroim.getY()+28);
        Rotate stickRotation = new Rotate(0, 0, 0);
        stick.getTransforms().add(stickRotation);

        // Set up the timeline for stick building
        Timeline buildTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.01), event -> buildStick(stick))
        );
        buildTimeline.setCycleCount(Timeline.INDEFINITE);

        scene.setOnMousePressed(event -> {
            if (!isBuildingStick && isMovingHero) {
                flipHero(heroim);
                isFlipping = true;  // Set the flipping flag

            } else if (!isMovingHero && !isBuildingStick ) {
                isBuildingStick = true;
                buildTimeline.play();
                stickbuilded=true ;
            }
        });

        scene.setOnMouseReleased(event -> {
            isBuildingStick = false;
            buildTimeline.pause();

            if (!isFlipping && !isBuildingStick) {
                rotateStick(stick);
                moveStickHero(stick, heroim, root,stage,scene);
                isMovingHero = true;
            }

            if (event.getButton() == MouseButton.SECONDARY) { // Right mouse button
                // Toggle flipping flag when the right mouse button is released
                isFlipping = !isFlipping;
            }
        });




        root.getChildren().addAll(heroim, pauseButton,stick);
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
        currpillar =pillar ;

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
