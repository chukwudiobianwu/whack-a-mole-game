import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class whackamole extends Application {
    @Override
    public void start(Stage stage) {
        // Creating a Text object
        Text text = new Text();
        Button btn = new Button("Start Game");

        // Setting font to the text
        text.setFont(new Font(45));

        // Setting the text to be added.
        text.setText("Welcome To Whack A Mole");

        // Creating a VBox layout
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);

        // Adding the elements to the VBox
        root.getChildren().addAll(text, btn);

        // Creating a scene object
        Scene scene = new Scene(root, 600, 300,Color.PURPLE);

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
