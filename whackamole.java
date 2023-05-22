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
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.Random;
import javafx.event.EventHandler;


public class whackamole extends Application {
    private Stage primaryStage;
    private Scene scene;
    private ImageView imageView;
    private StackPane stackPane;
    private static final int BOARD_SIZE = 4; // Adjust this to change the board size
    private int scorecount; // Adjust this to change the cell size

    private static final int MOLE_APPEAR_TIME = 1000; // Adjust this to change mole appearance time (in milliseconds)
    private static final int MOLE_DISAPPEAR_TIME = 50000; // Adjust this to change mole disappearance time (in milliseconds)

    private ImageView [][] moles;
    private Rectangle inrect = new Rectangle();
    private Rectangle rect = new Rectangle();
    Image img = new Image("wmole.png");
    Random ran;

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

        Text text = new Text();
        Button btn = new Button("Start Game");
        btn.setPrefWidth(200);
        btn.setPrefHeight(50);
        btn.setStyle("-fx-font-size: 20px; -fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-effect:dropshadow(one-pass-box,black,10,10.10,2,0);");

        Button closeButton = new Button("Quit Game");
        closeButton.setOnAction(event -> primaryStage.close());
        closeButton.setPrefWidth(200);
        closeButton.setPrefHeight(50);
        closeButton.setStyle("-fx-font-size: 20px; -fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-effect:dropshadow(one-pass-box,black,10,10.10,2,0);");

        text.setFont(new Font(45));

        VBox root = new VBox();
        root.setAlignment(Pos.BOTTOM_CENTER);
        root.setSpacing(20);
        VBox.setMargin(closeButton, new Insets(0, 0, 40, 0));
        root.getChildren().addAll(text, btn, closeButton);

        stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, root);

        Button levelButton = new Button("Select Difficulty");
        levelButton.setPrefWidth(200);
        levelButton.setPrefHeight(50);
        levelButton.setStyle("-fx-font-size: 20px; -fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-effect:dropshadow(one-pass-box,black,10,10.10,2,0);");

        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            VBox root2 = new VBox();
            root2.setAlignment(Pos.BOTTOM_CENTER);
            root2.setSpacing(20);
            VBox.setMargin(levelButton, new Insets(0, 0, 40, 0));
            root2.getChildren().addAll(levelButton);
            stackPane.getChildren().setAll(imageView, root2);
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

        levelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            VBox root3 = new VBox();
            root3.setAlignment(Pos.BOTTOM_CENTER);
            root3.setSpacing(20);
            VBox.setMargin(hard, new Insets(0, 0, 20, 0));
            root3.getChildren().addAll(easy, medium, hard);
            stackPane.getChildren().setAll(imageView, root3);
        });

        easy.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            stackPane.getChildren().setAll(setBoardAndMoles()); // Pass board size as argument
        });

        medium.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            stackPane.getChildren().setAll(setBoardAndMoles()); // Pass board size as argument
        });

        hard.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            stackPane.getChildren().setAll(setBoardAndMoles()); // Pass board size as argument
        });


        startMoleAnimation();
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

        GridPane gridP = new GridPane();
        gridP.setAlignment(Pos.CENTER);
    
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
        Text sCORE = new Text();  
        Image moleImage = img;

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
                    GridPane.setMargin(holeImageView, new Insets(0, 0,0 , 8));
            }
        }


        
        root5.getChildren().add(sCORE);
        stackPane.getChildren().addAll(gridPane,gridP);
    
        return stackPane;
    }
    

    private void startMoleAnimation() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        KeyFrame appearFrame = new KeyFrame(Duration.millis(MOLE_APPEAR_TIME), event -> {
            int randomRow = ran.nextInt(BOARD_SIZE);
            int randomCol = ran.nextInt(BOARD_SIZE);
            ImageView moleImageView = moles[randomRow][randomCol];
            moleImageView.setOpacity(1.0); // Make the mole visible
            
        });

        KeyFrame disappearFrame = new KeyFrame(Duration.millis(MOLE_APPEAR_TIME + MOLE_DISAPPEAR_TIME), event -> {
            for (ImageView[] row : moles) {
                for (ImageView hole : row) {
                    hole.setOpacity(0.0); // Make the mole invisible
                }
            }
        });


        timeline.getKeyFrames().addAll(appearFrame, disappearFrame);
        timeline.play();
        }



    public static void main(String[] args) {
        launch(args);
    }
}
