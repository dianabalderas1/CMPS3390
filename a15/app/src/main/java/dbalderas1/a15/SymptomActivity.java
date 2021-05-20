package dbalderas1.a15;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Symptom Activity Driver class for A15
 * @author Diana Balderas
 * @version 1.0
 */
public class SymptomActivity extends AppCompatActivity {

    ImageView mainImageView;
    TextView title, description;

    String data1, data2;
    int symptomsView;

    /**
     * The view displays the symptom topic and its detailed information
     * @param savedInstanceState saves the information created in the view
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom);

        mainImageView = findViewById(R.id.mainImageView);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);

        getData();
        setData();
    }

    /**
     * The activity gets the data from the previous activity
     */
    private void getData() {
        if(getIntent().hasExtra("symptomsView") && getIntent().hasExtra("data1") &&
        getIntent().hasExtra("data2")) {

            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            symptomsView = getIntent().getIntExtra("symptomsView",1);

        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * The data is set in the activity
     */
    private void setData() {
        title.setText(data1);
        description.setText(data2);
        mainImageView.setImageResource(symptomsView);
    }
}