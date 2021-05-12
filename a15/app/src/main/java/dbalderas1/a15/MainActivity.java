package dbalderas1.a15;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

/**
 * Main Activity Driver class for A10
 * @author Diana Balderas
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    Button button;


    /**
     * Displays the main activity layout with the navigation bar
     * @param savedInstanceState saves the data that is actively current based on the date and time
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setItemIconTintList(null);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupWithNavController(navView, navController);


        button = (Button) findViewById(R.id.btnKnowMore);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             * Open a new activity from the Main Activity
             * @param v the view when the button is clicked
             */
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });

    }

    /**
     * Opens the Know More Activity from the Main Activity
     */
    public void openNewActivity() {
        Intent intent = new Intent(this, KnowMoreActivity.class);
        startActivity(intent);
    }

}