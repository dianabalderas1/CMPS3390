package dbalderas1.a15;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Precaution Activity Driver class for A15
 * @author Diana Balderas
 * @version 1.0
 */
public class PrecautionActivity extends SymptomActivity {

    ImageView precaution_mainImageView;
    TextView precaution_title, precaution_description;

    String data3, data4;
    int precautionsView;

    /**
     * The view displays the precaution topic and its detailed information
     * @param savedInstanceState saves the information created in the view
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precaution);

        precaution_mainImageView = findViewById(R.id.precaution_mainImageView);
        precaution_title = findViewById(R.id.precaution_title);
        precaution_description = findViewById(R.id.precaution_description);

        getData();
        setData();
    }

    /**
     * The activity gets the data from the previous activity
     */
    private void getData() {
        if(getIntent().hasExtra("precautionsView") && getIntent().hasExtra("data3") &&
                getIntent().hasExtra("data4")) {

            data3 = getIntent().getStringExtra("data3");
            data4 = getIntent().getStringExtra("data4");
            precautionsView = getIntent().getIntExtra("precautionsView",1);

        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * The data is set in the activity
     */
    private void setData() {
        precaution_title.setText(data3);
        precaution_description.setText(data4);
        precaution_mainImageView.setImageResource(precautionsView);
    }
}
