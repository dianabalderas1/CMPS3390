package dbalderas1.a9;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Coin Driver class for A9
 * @author Diana Balderas
 * @version 1.0
 */
public class Coin extends BaseObservable {
    private String name;
    private double curValue;

    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);

    /**
     * Inputs a name to the historical values
     * @param name  a description to the value in the chart
     */
    public Coin(String name) {
        this.name = name;
    }

    /**
     * Gets the name of bitcoin and ethereum
     * @return string representing the name of the coin
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of bitcoin and ethereum
     * @param name representing the type of name for the coin
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the current price of bitcoin and ethereum
     * @return string representing the formatted price
     */
    @Bindable
    public String getCurValue() {
        return numberFormat.format(curValue);
    }

    /**
     * Sets the current price of bitcoin and ethereum
     * @param curValue representing the current price of the coin
     */
    public void setCurValue(double curValue) {
        this.curValue = curValue;
        notifyPropertyChanged(BR.curValue);
    }
}
