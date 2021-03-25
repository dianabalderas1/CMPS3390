package dbalderas1.a10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * Main Driver class for A10
 * @author Diana Balderas
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener{
    private ListView listView;
    private EditText input;
    private TabLayout tabs;
    private ArrayList<ListItem> items;
    private ArrayAdapter<ListItem> itemsAdapter;
    private String selectedCollection = "Todo";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    /**
     * The items on the list are array oriented
     * @param savedInstanceState the layout is saved from the structure of the list
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lvItems);
        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, R.layout.list_item_layout, items);
        listView.setAdapter(itemsAdapter);
        setupLongClickHandler();


        input = findViewById(R.id.etInput);
        input.setOnEditorActionListener(this);


        tabs = findViewById(R.id.tabLayout);
        setupTabClickListener();

        updateList();


    }

    /**
     * When connecting to database, signals when the list is being received
     */
    private void updateList() {
        showToast("Getting List", Toast.LENGTH_SHORT);
        Database.getList(db, selectedCollection, items, itemsAdapter);
    }

    /**
     * The items of the list can be cleared, set, and changed based on the user
     */
    private void setupTabClickListener() {
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Clear Items
                items.clear();

                // Set the selected collection
                selectedCollection = tab.getText().toString();

                // Loads Items from collection
                updateList();

                // Notify data set changed
                itemsAdapter.notifyDataSetChanged();

            }

            /**
             * The division between the type of item is being stored when it is not selected
             * @param tab the division of the different categories in the list
             */
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            /**
             * The division between the type of item is being stored when it is selected
             * @param tab the division of the different categories in the list
             */
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * The ability to removed an item from the list
     */
    private void setupLongClickHandler() {
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            ListItem tmpItem = items.remove(position);
            itemsAdapter.notifyDataSetChanged();
            Database.removeItem(db, selectedCollection, items, itemsAdapter, tmpItem);
            showToast("Removed Item", Toast.LENGTH_SHORT);
            return true;
        });
    }

    /**
     * The item is listed on the list
     * @param message the item's value
     * @param length the size of the list
     */
    private void showToast(String message, int length) {
        Toast toast = Toast.makeText(this, message, length);
        toast.setGravity(Gravity.CENTER, 0, -30);
        toast.show();
    }

    /**
     * Once item is entered, the item is saved on the list
     * @param v the list view of items
     * @param actionId the id referred to each item
     * @param event the action of add and deleting an item
     * @return representing the items displayed when changes are mase
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (event == null || event.getAction() == KeyEvent.ACTION_UP) {
            ListItem tmpItem = new ListItem(input.getText().toString());
            items.add(tmpItem);
            input.setText("");
            itemsAdapter.notifyDataSetChanged();
            Database.add(db, selectedCollection, tmpItem);
            showToast("Item Added", Toast.LENGTH_SHORT);
         }
        return true;
    }
}