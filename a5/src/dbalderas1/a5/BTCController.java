package dbalderas1.a5;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * BTC Controller Driver class for A5
 * @author Diana Balderas
 * @version 1.0
 */

public class BTCController {
    @FXML
    ImageView btnBackArrow;

    @FXML
    LineChart<String, Number> priceChart;

    public void initialize() {
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.setName("Bitcoin Price");

        series.getData().add(new XYChart.Data<>("2-20", 10351.13));
        series.getData().add(new XYChart.Data<>("3-20", 8522.30));
        series.getData().add(new XYChart.Data<>("4-20", 6666.11));
        series.getData().add(new XYChart.Data<>("5-20", 8829.42));
        series.getData().add(new XYChart.Data<>("6-20", 10208.96));
        series.getData().add(new XYChart.Data<>("7-20", 9239.31));
        series.getData().add(new XYChart.Data<>("8-20", 11810.07));
        series.getData().add(new XYChart.Data<>("9-20", 11924.22));
        series.getData().add(new XYChart.Data<>("10-20", 10616.30));
        series.getData().add(new XYChart.Data<>("11-20", 13771.70));
        series.getData().add(new XYChart.Data<>("12-20", 18778.18));
        series.getData().add(new XYChart.Data<>("1-21", 29413.29));
        series.getData().add(new XYChart.Data<>("2-21", 48373.00));

        priceChart.getData().add(series);
    }

    public void onBackArrowClicked(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Details.fxml"));
        Stage primaryStage = (Stage) btnBackArrow.getScene().getWindow();
        primaryStage.setScene(new Scene(root, 700, 575));
    }
}
