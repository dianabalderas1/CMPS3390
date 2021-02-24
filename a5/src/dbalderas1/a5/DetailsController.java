package dbalderas1.a5;

import dbalderas1.a6.Coin;
import dbalderas1.a6.UpdateCoinTimerTask;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

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

    Coin bitcoin, ethereum;
    Timer bitcoinTimer, ethereumTimer;

    /**
     * Default constructor representing the updated time on bitcoin and ethereum
     */
    public void initialize() {
        this.bitcoin = new Coin("bitcoin");
        this.ethereum = new Coin("ethereum");

        labBTCValue.textProperty().bind(Bindings.format("$%-10.2f", bitcoin.currentPriceProperty()));
        labETHValue.textProperty().bind(Bindings.format("$%-10.2f", ethereum.currentPriceProperty()));
        bitcoinTimer = new Timer();
        bitcoinTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new UpdateCoinTimerTask(bitcoin));

            }
        }, 0, 5000);

        ethereumTimer = new Timer();
        ethereumTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new UpdateCoinTimerTask(ethereum));

            }
        }, 0, 5000);
    }

    /**
     * Default constructor to print out the constructor
     */
    public DetailsController() {
        System.out.println("Constructor");

    }

    /**
     * Override constructor to demonstrate the chart data on bitcoin and ethereum
     * @param mouseEvent representing the user's interface
     * @throws IOException if an error occurs
     */
    public void onDetailedButtonClicked(MouseEvent mouseEvent) throws IOException {
        shutdown();
        Parent root = FXMLLoader.load(getClass().getResource("Chart.fxml"));
        Stage primaryStage = (Stage) btcHBox.getScene().getWindow();
        primaryStage.setScene(new Scene(root, 700, 575));
    }

    /**
     * Default constructor to print out the time shutdown on bitcoin and ethereum
     */
    public void shutdown() {
        System.out.println("Shutdown was called Stopping Timers");
        bitcoinTimer.cancel();
        ethereumTimer.cancel();

    }
}
