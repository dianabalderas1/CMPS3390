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

    /**
     * Override constructor to start the coin tracker
     * @param primaryStage representing the coin tracker imagery when it first runs
     * @throws Exception if an error occurs
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Details.fxml"));
        Parent root = loader.load();
        DetailsController controller = loader.getController();
        Scene scene = new Scene(root, 700, 475);
        primaryStage.setTitle("Coin Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnHidden(e -> controller.shutdown());
    }

    /**
     * Main entry of A5555
     * @param args String array that holds command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
