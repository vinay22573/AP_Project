package com.example.ap_project_endsem;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
// NOTE : SEVENTH COMMIT IS MOST USEFUL TILL NOW
public class Scene1 extends Application {
    protected static double lastPillarEndX = 0.0;
    private double finalpillarEndX = 0.0;
    private double firstPillarEndX = 0.0;
    private int initialPillar = 0;
    private double hs = 0;

//    public static void main(String[] args) {
//        launch(args);
//    }

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
        double centerY = 100;

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

    private void createPlayButton(Group root, Scene scene, Stage stage) {
        // Create a red circle (1.5 times larger)
        double circleRadius = 50 * 1.5;
        Circle circle = new Circle(circleRadius, Color.RED);
        circle.setCenterX(scene.getWidth() / 2);
        circle.setCenterY(scene.getHeight() / 2 - 30); // Adjust the vertical position

        // Create white text inside the circle
        Text playText = new Text("PLAY");
        playText.setFont(Font.font("Arial", 35));
        playText.setFill(Color.WHITE);
        playText.setX(circle.getCenterX() - playText.getLayoutBounds().getWidth() / 2);
        playText.setY(circle.getCenterY() + playText.getLayoutBounds().getHeight() / 4);
        // Add event handler to switch to Scene2 when the button is clicked
        playText.setOnMouseClicked(event -> showScene2(stage));

        root.getChildren().addAll(circle, playText);
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
        double leftMargin = 10;// Set the left margin
        double topMargin = 10;
        pauseButton.setX(leftMargin);
        pauseButton.setY(topMargin);

        // Hero Image
        Image heroImage = new Image("heroim.png");
        Hero hero = Hero.getInstance(heroImage);
        double heroSize = (2.0 / 40.0) * scene.getWidth();
        double hs = heroSize;
        hero.setFitWidth(heroSize);
        hero.setFitHeight(heroSize);

        // Generate the first pillar
        PillarInfo firstPillarInfo = generateRandomPillar(root, scene.getWidth());
        hero.setPosition(firstPillarInfo);
        // Generate the second pillar
        PillarInfo secondPillarInfo = generateRandomPillar(root, scene.getWidth());


        root.getChildren().addAll(hero, pauseButton);
        stage.setScene(scene);
    }





    // The code requires scene to be SQUARE only it is a dependent code
    private PillarInfo generateRandomPillar(Group root, double sceneWidth) {
        double minPillarWidth = 1.5 * hs; // Minimum pillar width
        double maxPillarWidth = 0.2 * sceneWidth; // Maximum pillar width

        // Ensure minimum and maximum constraints
        double startX;
        double endX;
        if (initialPillar == 0) {// no pillar generated till now
            startX = 0.0;
            endX = startX + Math.max(minPillarWidth, Math.random() * (Math.min(maxPillarWidth, sceneWidth / 2 - startX)));

        } else if (lastPillarEndX == 0.0) {
            // For the first pillar, start from the left
            startX = 0.0;
            endX = startX + Math.max(minPillarWidth, Math.random() * (Math.min(maxPillarWidth, sceneWidth / 2 - startX)));
        } else {
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




        // This section we need to work upon
//        if no pillar is create the lastpillarendx should be the endx of first pillar
        // if the game has not started then also it should be the first pillar endx only
        // if game has started then it has to change with last generated pillar's endx
        if(initialPillar==0){
            lastPillarEndX = endX;
        }
        if(initialPillar == 0){
            finalpillarEndX  = lastPillarEndX;
        }else{
            finalpillarEndX = endX;
        }

        root.getChildren().add(pillar);
        // Return information about the generated pillar
        return new PillarInfo(startX, endX, startY, endY);
    }




    // THIS CLASS CONTAINS ALL THE INFO ABOUT PILLAR
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





//    THIS CLASS CONTAINS ALL THE INFO ABOUT HERO
    private static class Hero extends ImageView {
        private static Hero instance  = null;

        private Hero(Image image) {
            super(image);
        }

        public static Hero getInstance(Image image) {
            if (instance == null) {
                instance = new Hero(image);
            }
            return instance;
        }

        public double getEndX() {
            return getX() + getTranslateX() + getFitWidth();
        }

        public double getStartX() {
            return getX() + getTranslateX();
        }

        public double getEndY() {
            return getY() + getTranslateY() + getFitHeight();
        }

        public double getStartY() {
            return getY() + getTranslateY();
        }
        // This is the method which we need to work upon
        public void setPosition(PillarInfo pillarInfo) {
            double heroX = lastPillarEndX-30;
            double heroY = pillarInfo.getStartY() - 30; // reducing 30 to align top of pillar and base of hero
            setX(heroX);
            setY(heroY);
            double anchorX = getEndX() - getFitWidth() / 2;
            System.out.println("Adjusted AnchorX: " + anchorX);
            System.out.println("Adjusted EndX of Pillar: " + pillarInfo.getEndX());
        }
    }
}
