package dbalderas1.a5;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Detail Controller Driver class for A5
 * @author Diana Balderas
 * @version 1.0
 */

public class DetailsController {
    @FXML
    Label labBTCValue;
    @FXML
    Label labETHValue;

    @FXML
    HBox btcHBox;
    @FXML
    HBox ethHBox;

    public void initialize() {
        labBTCValue.setText("$48,213.00");
        labETHValue.setText("$1,832.32");
    }

    public DetailsController() {
        System.out.println("Constructor");

    }

    public void onDetailedButtonClicked(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() == btcHBox) {
            System.out.println("Change to BTC Scene");
            Parent root = FXMLLoader.load(getClass().getResource("BTC.fxml"));
            Stage primaryStage = (Stage) btcHBox.getScene().getWindow();
            primaryStage.setScene(new Scene(root, 700, 575));
        }
        if(mouseEvent.getSource() == ethHBox) {
            System.out.println("Change to ETH Scene");
            Parent root = FXMLLoader.load(getClass().getResource("ETH.fxml"));
            Stage primaryStage = (Stage) ethHBox.getScene().getWindow();
            primaryStage.setScene(new Scene(root, 700, 575));

        }
    }
}
