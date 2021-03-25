package dbalderas1.a10;

import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * ListenItem Driver class for A10
 * @author Diana Balderas
 * @version 1.0
 */
public class ListItem implements Serializable {
    private long dttm;
    private String item;

    /**
     * Item is displayed on the list
     * @param item representing the values in the list
     */
    public ListItem(String item) {
        this.item = item;
        dttm = System.nanoTime();
    }

    /**
     * Item and description are displayed on the list
     * @param dttm representing the description of the item
     * @param item representing the values in the list
     */
    public ListItem(long dttm, String item) {
        this.dttm = dttm;
        this.item = item;
    }

    /**
     * Sets the name of the item on the list
     * @return string representing the item
     */
    @NonNull
    @Override
    public String toString() {
        return item;
    }

    /**
     * Gets the description of the item
     * @return long representing description of the item
     */
    public long getDttm() {
        return dttm;
    }

    /**
     * Gets the item of the list
     * @return string representing the item
     */
    public String getItem() {
        return item;
    }
}
