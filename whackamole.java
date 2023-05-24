import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import java.io.FileNotFoundException; 
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileInputStream;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.*;
import javafx.scene.control.ProgressBar;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.Random;
import javafx.scene.layout.AnchorPane;
import javafx.event.EventHandler;


public class whackamole extends Application {
    private Stage primaryStage;
    private Scene scene;
    private ImageView imageView;
    private StackPane stackPane;
    private static final int BOARD_SIZE = 4; // Adjust this to change the board size
    private int scorecount; // Adjust this to change the cell size
    private int seconds = 60;

    private int MOLE_APPEAR_TIME = 1000; // Adjust this to change mole appearance time (in milliseconds)
    private int MOLE_DISAPPEAR_TIME = 1000; // Adjust this to change mole disappearance time (in milliseconds)

    private Timeline timeline = new Timeline();
    private Timeline timeli = new Timeline();
    private Text won = new Text("Congratulations!, You Won!");
    private Text lost = new Text("Sorry!, You Lost");
    private Button replay = new Button("Play Again");

    private ImageView [][] moles;
    private Rectangle inrect = new Rectangle();
    private Rectangle rect = new Rectangle();
    Image img = new Image("wmole.png");
    Random ran;

    private VBox root3;
    private Text tim =new Text("Time Left: " + seconds + " Seconds");

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        this.primaryStage = primaryStage;

        FileInputStream inputStream = new FileInputStream("mole.jpeg");
        Image image = new Image(inputStream);
        imageView = new ImageView(image);
        imageView.setFitHeight(455);
        imageView.setFitWidth(500);
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(primaryStage.widthProperty());
        imageView.fitHeightProperty().bind(primaryStage.heightProperty());




        Button backbButton =new Button("Back");
        backbButton.setPrefWidth(200);
        backbButton.setPrefHeight(50);
        backbButton.setStyle("-fx-font-size: 20px; -fx-background-color: #873260; -fx-text-fill: #ffffff; -fx-effect:dropshadow(one-pass-box,black,10,10.10,2,0);");

        Button backbButto =new Button("Back");
        backbButto.setPrefWidth(200);
        backbButto.setPrefHeight(50);
        backbButto.setStyle("-fx-font-size: 20px; -fx-background-color: #873260; -fx-text-fill: #ffffff; -fx-effect:dropshadow(one-pass-box,black,10,10.10,2,0);");

        Button btn = new Button("Start Game");
        btn.setPrefWidth(200);
        btn.setPrefHeight(50);
        btn.setStyle("-fx-font-size: 20px; -fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-effect:dropshadow(one-pass-box,black,10,10.10,2,0);");

        Button closeButton = new Button("Quit Game");
        closeButton.setOnAction(event -> primaryStage.close());
        closeButton.setPrefWidth(200);
        closeButton.setPrefHeight(50);
        closeButton.setStyle("-fx-font-size: 20px; -fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-effect:dropshadow(one-pass-box,black,10,10.10,2,0);");



        VBox root = new VBox();
        root.setAlignment(Pos.BOTTOM_CENTER);
        root.setSpacing(20);
        VBox.setMargin(closeButton, new Insets(0, 0, 40, 0));
        root.getChildren().addAll(btn, closeButton);

        stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, root);
        

        Button levelButton = new Button("Select Difficulty");
        levelButton.setPrefWidth(200);
        levelButton.setPrefHeight(50);
        levelButton.setStyle("-fx-font-size: 20px; -fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-effect:dropshadow(one-pass-box,black,10,10.10,2,0);");
           
        VBox root2 = new VBox();
            root2.setAlignment(Pos.BOTTOM_CENTER);
            root2.setSpacing(20);

        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            VBox.setMargin(backbButton, new Insets(0, 0, 40, 0));
            root2.getChildren().setAll(levelButton,backbButton);
            stackPane.getChildren().setAll(imageView, root2);
            
        }); 

        backbButton.setOnMouseClicked(event -> {
            mainmenue(imageView,root);
        });

        Button easy = new Button("Easy");
        easy.setPrefWidth(200);
        easy.setPrefHeight(50);
        easy.setStyle("-fx-font-size: 20px; -fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-effect:dropshadow(one-pass-box,black,10,10.10,2,0);");

        Button medium = new Button("Medium");
        medium.setPrefWidth(200);
        medium.setPrefHeight(50);
        medium.setStyle("-fx-font-size: 20px; -fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-effect:dropshadow(one-pass-box,black,10,10.10,2,0);");

        Button hard = new Button("Hard");
        hard.setPrefWidth(200);
        hard.setPrefHeight(50);
        hard.setStyle("-fx-font-size: 20px; -fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-effect:dropshadow(one-pass-box,black,10,10.10,2,0);");
            
        root3 = new VBox();
        root3.setAlignment(Pos.BOTTOM_CENTER);
        root3.setSpacing(20);


        levelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            VBox.setMargin(backbButto, new Insets(0, 0, 20, 0));
            root3.getChildren().setAll(easy, medium, hard, backbButto);
            stackPane.getChildren().setAll(imageView, root3); 
        
        });

        backbButto.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            levelmenue(imageView, root2);
        });
        


        easy.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            
            MOLE_APPEAR_TIME = 800;
            MOLE_DISAPPEAR_TIME = 800; 
            startMoleAnimation();
            stackPane.getChildren().setAll(setBoardAndMoles()); // Pass board size as argument
            
        });

        medium.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            MOLE_APPEAR_TIME = 740;
            MOLE_DISAPPEAR_TIME = 740; 
            startMoleAnimation();
            stackPane.getChildren().setAll(setBoardAndMoles());// Pass board size as argument
             
        });

        hard.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            MOLE_APPEAR_TIME = 500;
            MOLE_DISAPPEAR_TIME = 500; // Pass board size as argument
            startMoleAnimation();
            stackPane.getChildren().setAll(setBoardAndMoles()); 
            
        });

        scene = new Scene(stackPane);
        scene.getRoot().setStyle("-fx-background-color: brown;");
        primaryStage.setTitle("Whack a Mole");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private StackPane setBoardAndMoles() {

        rect.setX(20);
        rect.setY(20);
        rect.widthProperty().bind(primaryStage.widthProperty().multiply(0.5));
        rect.heightProperty().bind(primaryStage.heightProperty());
        rect.setFill(Color.BEIGE);
    
        inrect.widthProperty().bind(primaryStage.widthProperty().multiply(0.5));
        inrect.heightProperty().bind(rect.heightProperty().multiply(0.5));
        inrect.setFill(Color.GRAY);
    
        VBox root4 = new VBox(rect);
        root4.setAlignment(Pos.CENTER);
        VBox root5 = new VBox(inrect);
        root5.setAlignment(Pos.CENTER);
    
        StackPane stackPane = new StackPane(root4, root5);
    
        // Create a grid pane for the ellipses
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

    
        // Set the dimensions of each ellipse
        double ellipseWidth = inrect.getWidth() / 4;
        double ellipseHeight = inrect.getHeight() / 4;
    
        // Set the gaps between ellipses
        double horizontalGap = ellipseWidth * 0.1;
        double verticalGap = ellipseHeight * 0.1;
       
        // Create and add ellipses to the grid pane
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                 Ellipse ellipse = new Ellipse(ellipseWidth / 2 - horizontalGap, ellipseHeight / 2 - verticalGap);
                ellipse.setFill(Color.LIGHTGRAY);
                gridPane.add(ellipse, col, row);
                
                // Add gaps between ellipses
                GridPane.setMargin(ellipse, new Insets(verticalGap, horizontalGap, verticalGap, horizontalGap));

                ellipse.radiusXProperty().bind(inrect.widthProperty().multiply(0.1));
                ellipse.radiusYProperty().bind(inrect.heightProperty().multiply(0.1));
                
                // Reduce gaps around the border and edge of the rectangl
            }
        }
        

        moles = new ImageView[4][4];
        ran = new Random();
        Text sCORE = new Text("Score: " + scorecount + "/20");
        sCORE.setFont(new Font(15));  
        sCORE.setStyle("-fx-border-color: black;  -fx-text-fill: brown;");

        ProgressBar progressBar = new ProgressBar();
        progressBar.setMinWidth(200);
        progressBar.setMaxWidth(300);
        
        Button backbBut =new Button("Back");
        backbBut.setPrefWidth(90);
        backbBut.setPrefHeight(50);
        backbBut.setStyle("-fx-font-size: 20px; -fx-background-color: #873260; -fx-text-fill: #ffffff; -fx-effect:dropshadow(one-pass-box,black,10,10.10,2,0);");
        

        backbBut.setOnMouseClicked( event -> {
            Difficultymenue(imageView, root3);
            scorecount = 0;
            seconds = 60;
            timeline.playFromStart();
            timeli.playFromStart();
        });

        VBox bom = new VBox(tim);
        bom.setAlignment(Pos.TOP_CENTER);
        bom.setSpacing(30);

        AnchorPane scorePane = new AnchorPane();
        AnchorPane scorePa= new AnchorPane();
        HBox scoreBox = new HBox(sCORE,progressBar);
        scoreBox.setAlignment(Pos.TOP_CENTER);
        scoreBox.setSpacing(10);

        HBox scoreB = new HBox(backbBut);
        scoreB.setAlignment(Pos.TOP_LEFT);
        scoreB.setSpacing(10);
        
        // Set top anchor for the HBox to position it at the top
        AnchorPane.setTopAnchor(scoreBox, 70.0);
        
        
        // Set left and right anchors for the HBox to stretch it horizontally
        AnchorPane.setLeftAnchor(scoreBox, 0.0);
        AnchorPane.setRightAnchor(scoreBox, 0.0);
        AnchorPane.setTopAnchor(bom, 40.0);
        
        
        // Set left and right anchors for the HBox to stretch it horizontally
        AnchorPane.setLeftAnchor(bom, 0.0);
        AnchorPane.setRightAnchor(bom, 0.0);

        AnchorPane.setTopAnchor(scoreB, 40.0);
    
        // Set left and right anchors for the HBox to stretch it horizontally
        AnchorPane.setLeftAnchor(scoreB, 20.0);
        AnchorPane.setRightAnchor(scoreB, 0.0);

        scorePane.getChildren().addAll(scoreBox ,bom);
        scorePa.getChildren().add(scoreB);
        Image moleImage = img; 
        // Image hammerImage = new Image("mole.png"); // Replace "hammer.png" with your actual hammer image file
        // ImageView hammerImageView = new ImageView(hammerImage);
        // hammerImageView.setVisible(false);


        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                ImageView holeImageView = new ImageView(moleImage);

                // holeImageView.setFitWidth(inrect.getHeight() / 4 - (inrect.getHeight() / 4 * 0.1));
                // holeImageView.setFitHeight((inrect.getHeight() / 4 - (inrect.getHeight() / 4 * 0.1))/2);
                holeImageView.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid; -fx-max-width: 100%;");
                holeImageView.fitWidthProperty().bind(inrect.widthProperty().multiply(0.25).subtract(inrect.widthProperty().multiply(0.056)));
                holeImageView.fitHeightProperty().bind(inrect.heightProperty().multiply(0.36).subtract(inrect.heightProperty().multiply(0.03)).divide(2));

                 moles[row][col] = holeImageView;
                 gridPane.add(holeImageView, col, row); 
                    GridPane.setMargin(holeImageView, new Insets(0, 0,0 , 11));

                    final int finalRow = row;
                    final int finalCol = col;
        
                    holeImageView.setOnMouseClicked(mouseEvent -> {
                        if (moles[finalRow][finalCol].getOpacity() == 1.0) {
                            scorecount++;  
                            sCORE.setText("Score: " + scorecount + "/20");
                            progressBar.setProgress(scorecount / 21.0);
                            if (scorecount == 20 ){
                                timeline.stop();
                                timeli.stop();
                            }
                            holeImageView.setOpacity(0.0);

                                System.out.println("Burger");
                        
                        }
                    });
                }
            }
            VBox vbox = new VBox(gridPane, scorePa);
            VBox.setMargin(gridPane, new Insets(90, 0, 0, 0));
            vbox.setAlignment(Pos.CENTER);
        
            stackPane.getChildren().addAll(scorePane, vbox);

    
        return stackPane;
    }
    

    private void startMoleAnimation() {
        timeline.stop();
        timeline.getKeyFrames().clear();
        timeli.stop();
        timeli.getKeyFrames().clear();
        
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeli.setCycleCount(Timeline.INDEFINITE);

        replay.setPrefWidth(200);
        replay.setPrefHeight(50);
        replay.setStyle("-fx-font-size: 20px; -fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-effect:dropshadow(one-pass-box,black,10,10.10,2,0);");
        Button replay = new Button("Play Again");
        replay.setPrefWidth(200);
        replay.setPrefHeight(50);
        replay.setStyle("-fx-font-size: 20px; -fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-effect:dropshadow(one-pass-box,black,10,10.10,2,0);");
        won.setFont(Font.font("Arial", 55)); // Set the font and size
        won.setFill(Color.GRAY);

        lost.setFont(Font.font("Arial", 55)); // Set the font and size
        lost.setFill(Color.GRAY);

        replay.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->{
            seconds =60;
            scorecount=0;
            stackPane.getChildren().setAll(setBoardAndMoles());
        });

        KeyFrame appearFrame = new KeyFrame(Duration.millis(MOLE_APPEAR_TIME), event -> {
            int randomRow = ran.nextInt(BOARD_SIZE);
            int randomCol = ran.nextInt(BOARD_SIZE);
            ImageView moleImageView = moles[randomRow][randomCol];
            moleImageView.setOpacity(1.0); // Make the mole visible
        
            // Add mouse click event handler to the moleImageView
        });
        
           KeyFrame countdown = new KeyFrame(Duration.seconds(1), event -> {
                seconds--;
                tim.setText("Time Left: " + seconds + " Seconds");

                if(seconds == 0 && scorecount < 20){
                    VBox np = new VBox(lost, replay);
                    np.setAlignment(Pos.CENTER);
                    np.setSpacing(30);
                    stackPane.getChildren().setAll(np);
                }
        
                if(scorecount == 20){
                    VBox np = new VBox(won, replay);
                    np.setAlignment(Pos.CENTER);
                    np.setSpacing(30);
                    stackPane.getChildren().setAll(np);

                }
            });

        KeyFrame disappearFrame = new KeyFrame(Duration.millis(MOLE_APPEAR_TIME + MOLE_DISAPPEAR_TIME), event -> {
            for (ImageView[] row : moles) {
                for (ImageView hole : row) {
                    hole.setOpacity(0.0); // Make the mole invisible
                }
            }
        });



        timeline.getKeyFrames().addAll(appearFrame, disappearFrame);
        timeli.getKeyFrames().addAll(countdown);
        timeline.play();
        timeli.play();
        }

private void mainmenue(ImageView img , VBox vb){
    stackPane.getChildren().setAll(img,vb);
 }

private void levelmenue(ImageView vw, VBox jl){
    stackPane.getChildren().setAll(vw,jl);
}
private void Difficultymenue(ImageView kl , VBox hl){
    stackPane.getChildren().setAll(kl, hl);
}


    public static void main(String[] args) {
        launch(args);
    }
}
