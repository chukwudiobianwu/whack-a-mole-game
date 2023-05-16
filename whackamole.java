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
        btn.setStyle("-fx-font-size: 20px; -fx-background-color: #ff0000; -fx-text-fill: #ffffff;");


        // Setting font to the text
        text.setFont(new Font(45));

        // Setting the text to be added.
        text.setText("Welcome To Whack A Mole");
      
        // Creating a VBox layout
        VBox root = new VBox();
        root.setAlignment(Pos.BOTTOM_CENTER);
        root.setSpacing(20);

        // Adding the elements to the VBox
        root.getChildren().addAll(text, btn);

        imageView.fitWidthProperty().bind(stage.widthProperty());
        imageView.fitHeightProperty().bind(stage.heightProperty());

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, root);
        // Creating a scene object
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

