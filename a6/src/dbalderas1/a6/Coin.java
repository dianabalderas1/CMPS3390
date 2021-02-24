package dbalderas1.a6;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.chart.XYChart;

/**
 * Coin Driver class for A5
 * @author Diana Balderas
 * @version 1.0
 */

public class Coin {
    private String name;
    private final DoubleProperty currentPrice;
    private XYChart.Series<Number, Number> historicalValues;

    /**
     * Override constructor to name the historical values
     * @param name representing a description to the valued on the chart
     */
    public Coin(String name) {
        historicalValues = new XYChart.Series<>();
        historicalValues.setName(name);
        currentPrice = new SimpleDoubleProperty();
        this.name = name;
    }

    /**
     * Override constructor representing the values on the chart
     * @return the values of coin
     */
    public XYChart.Series<Number, Number> getHistoricalValues() {
        return historicalValues;
    }

    /**
     * Override constructor displaying values on a chart
     * @param historicalValues representing the values of bitcoin and ethereum
     */
    public void setHistoricalValues(XYChart.Series<Number, Number> historicalValues) {
        this.historicalValues = historicalValues;
    }

    /**
     * Override constructor displaying day and values on a chart
     * @param day representing the time frame on bitcoin and ethereum
     * @param value representing the amount on bitcoin and ethereum
     */
    public void addHistoricalValue (int day, double value) {
        historicalValues.getData().add(new XYChart.Data<>(day, value));
    }

    /**
     * Override constructor gets the name of bitcoin and ethereum
     * @return string representing the name of the coin
     */
    public String getName() {
        return name;
    }

    /**
     * Override constructor sets the name of bitcoin and ethereum
     * @param name representing the type of name for coin
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Override constructor gets the current price of bitcoin and ethereum
     * @return double representing the current price of the coin
     */
    public double getCurrentPrice() {
        return currentPrice.get();
    }

    /**
     * Override constructor displaying the current price of bitcoin and ethereum
     * @return representing the current price of the coin
     */
    public DoubleProperty currentPriceProperty() {
        return currentPrice;
    }

    /**
     * Override constructor sets the current price of bitcoin and ethereum
     * @param currentPrice representing the current price of the coin
     */
    public void setCurrentPrice(double currentPrice) {
        this.currentPrice.set(currentPrice);
    }

    /**
     * Override constructor gets the string of bitcoin and ethereum
     * @return string representing the result of the coin
     */
    @Override
    public String toString() {
        return String.format("%20s: $%-10.2f", this.name, this.currentPrice.getValue());
    }
}
