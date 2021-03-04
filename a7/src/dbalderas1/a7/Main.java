package dbalderas1.a7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main Driver class for A7
 * @author Diana Balderas
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Loads the primaryStage when the server connects and is running
     * @param primaryStage Stage is the primary scene of the application
     * @throws Exception can cause an exception if port is not available
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chat.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("CMPS3390 Chat");
        primaryStage.setScene(scene);
        primaryStage.setOnHiding(e-> {
            try {
                controller.exit();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        primaryStage.show();
    }

    /**
     * Launches the application on its first primaryStage
     * @param args String[] that holds command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
