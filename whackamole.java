import javafx.application.Application;
import javafx.geometry.Pos;
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

public class whackamole extends Application {
    @Override
    public void start(Stage stage) throws FileNotFoundException{
        // Creating a Text object
        FileInputStream inputstream = new FileInputStream("mole.jpeg");

        Image image = new Image(inputstream); 

        ImageView imageView = new ImageView(image);

        //Setting the position of the image 
        imageView.setX(50); 
        imageView.setY(25); 
        
        //setting the fit height and width of the image view 
        imageView.setFitHeight(455);
        imageView.setFitWidth(500);
        
        //Setting the preserve ratio of the image view 
        imageView.setPreserveRatio(true);  

        Text text = new Text();
        Button btn = new Button("Start Game"); 
        btn.setPrefWidth(200);
        btn.setPrefHeight(50);

        // Apply CSS styles
        btn.setStyle("-fx-font-size: 20px; -fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-effect:dropshadow(one-pass-box,black,10,10.10,2,0);");

        Button easy = new Button("Easy"); 
        easy.setPrefWidth(200);
        easy.setPrefHeight(50);

        // Apply CSS styles
        easy.setStyle("-fx-font-size: 20px; -fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-effect:dropshadow(one-pass-box,black,10,10.10,2,0);");

        Button Medium = new Button("Medium"); 
        Medium.setPrefWidth(200);
        Medium.setPrefHeight(50);

        // Apply CSS styles
        Medium.setStyle("-fx-font-size: 20px; -fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-effect:dropshadow(one-pass-box,black,10,10.10,2,0);");

        Button Hard = new Button("Hard"); 
        Hard.setPrefWidth(200);
        Hard.setPrefHeight(50);

        // Apply CSS styles
        Hard.setStyle("-fx-font-size: 20px; -fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-effect:dropshadow(one-pass-box,black,10,10.10,2,0);");


        Button closeButton = new Button("Quit Game");
        closeButton.setOnAction(event -> stage.close());
        closeButton.setPrefWidth(200);
        closeButton.setPrefHeight(50);

        // Apply CSS styles
        closeButton.setStyle("-fx-font-size: 20px; -fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-effect:dropshadow(one-pass-box,black,10,10.10,2,0);");

        // Setting font to the text
        text.setFont(new Font(45));

        // Setting the text to be added.
        // text.setText("Welcome To Whack A Mole");
      
        // Creating a VBox layout
        VBox root = new VBox();
        root.setAlignment(Pos.BOTTOM_CENTER);
        root.setSpacing(20);

        // Adding the elements to the VBox
        root.getChildren().addAll(text, btn, closeButton);

        imageView.fitWidthProperty().bind(stage.widthProperty());
        imageView.fitHeightProperty().bind(stage.heightProperty());

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, root);
        // Creating a scene object            
            Button levelButton = new Button("Select Difficulty");
            levelButton.setPrefWidth(200);
            levelButton.setPrefHeight(50);

            // Apply CSS styles
            levelButton.setStyle("-fx-font-size: 20px; -fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-effect:dropshadow(one-pass-box,black,10,10.10,2,0);");
             
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->  {
            // Start the game

            VBox roo = new VBox();
            roo.setAlignment(Pos.BOTTOM_CENTER);
            roo.setSpacing(20);
    
            // Adding the elements to the VBox
            roo.getChildren().addAll(levelButton);

            StackPane stackPa = new StackPane();
            stackPa.getChildren().addAll(imageView, roo);

            Scene scene = new Scene(stackPa);

            scene.getRoot().setStyle("-fx-background-color: brown;");
    
            // Setting title to the Stage
            stage.setTitle("Whack a Mole");
    
            // Adding scene to the stage
            stage.setScene(scene);
    
            // Displaying the contents of the stage
            stage.show();

        });

        levelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->  {
            // Start the game
            // Apply CSS styles
            levelButton.setStyle("-fx-font-size: 20px; -fx-background-color: #ff0000; -fx-text-fill: #ffffff; -fx-effect:dropshadow(one-pass-box,black,10,10.10,2,0);");
           
            HBox roo = new HBox();
            roo.setAlignment(Pos.BOTTOM_CENTER);
            roo.setSpacing(20);
    
            // Adding the elements to the VBox
            roo.getChildren().addAll(easy,Medium,Hard);

            StackPane stackPa = new StackPane();
            stackPa.getChildren().addAll(imageView, roo);

            Scene scene = new Scene(stackPa);

            scene.getRoot().setStyle("-fx-background-color: brown;");
    
            // Setting title to the Stage
            stage.setTitle("Whack a Mole");
    
            // Adding scene to the stage
            stage.setScene(scene);
    
            // Displaying the contents of the stage
            stage.show();

        });

        Scene scene = new Scene(stackPane);

        scene.getRoot().setStyle("-fx-background-color: brown;");

        // Setting title to the Stage
        stage.setTitle("Whack a Mole");

        // Adding scene to the stage
        stage.setScene(scene);

        // Displaying the contents of the stage
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
