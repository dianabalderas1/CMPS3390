package dbalderas1.a5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main Driver class for A5
 * @author Diana Balderas
 * @version 1.0
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Details.fxml"));
        primaryStage.setTitle("Coin Tracker");
        primaryStage.setScene(new Scene(root, 700, 575));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
