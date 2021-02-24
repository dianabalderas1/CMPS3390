package dbalderas1.a5;

import dbalderas1.a6.Coin;
import dbalderas1.a6.CoinGecko;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Chartt Controller Driver class for A5
 * @author Diana Balderas
 * @version 1.0
 */

public class ChartController {
    @FXML
    ImageView btnBackArrow;

    @FXML
    LineChart<Number, Number> priceChart;

    @FXML
    ComboBox cbDateRange;

    @FXML
    ComboBox cbCoinSelector;

    Coin bitcoin, ethereum;

    /**
     * Default constructor to displaying the price history on bitcoin and ethereum
     * within a date range
     */
    public void initialize() {
        priceChart.setAnimated(false);
        bitcoin = new Coin("bitcoin");
        ethereum = new Coin("ethereum");

        CoinGecko.updatePriceHistory(bitcoin, 365);
        CoinGecko.updatePriceHistory(ethereum, 365);

        priceChart.getData().add(bitcoin.getHistoricalValues());
    }

    /**
     * Override constructor displaying the date range values on bitcoin and ethereum
     * @param mouseEvent representing the user's interface
     * @throws IOException
     */
    public void onBackArrowClicked(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Details.fxml"));
        Stage primaryStage = (Stage) btnBackArrow.getScene().getWindow();
        primaryStage.setScene(new Scene(root, 700, 575));
    }

    public void onDateRangeChanged(ActionEvent actionEvent) {
        int days = 0;
        switch ((String)cbDateRange.getValue()) {
            case "Year":
                days = 365;
                break;
            case "90 Days":
                days = 90;
                break;
            case "60 Days":
                days = 60;
                break;
            case "30 Days":
                days = 30;
                break;
            case "Week":
                days = 7;
                break;
        }
        CoinGecko.updatePriceHistory(bitcoin, days);
        CoinGecko.updatePriceHistory(ethereum, days);
        updateChart();
    }

    /**
     * Default constructor representing the overall values on bitcoin and ethereum
     */
    private void updateChart() {
        priceChart.getData().clear();
        switch ((String) cbCoinSelector.getValue()) {
            case "Bitcoin":
                priceChart.getData().add(bitcoin.getHistoricalValues());
                break;
            case "Ethereum":
                priceChart.getData().add(ethereum.getHistoricalValues());
                break;
            case "All":
                priceChart.getData().add(ethereum.getHistoricalValues());
                priceChart.getData().add(bitcoin.getHistoricalValues());
                break;
        }
    }

    /**
     * Override constructor displaying the changes based on a button pressed
     * @param actionEvent representing the user's interface
     */
    public void onCoinSelectionChanged(ActionEvent actionEvent) {
        updateChart();
    }
}
