package dbalderas1.a15;

import android.os.Bundle;
import android.os.health.SystemHealthManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Symptoms Driver class for A15
 * @author Diana Balderas
 * @version 1.0
 */
public class Symptoms extends AppCompatActivity {

    RecyclerView recyclerView;

    String s1[], s2[];
    int images[] = {R.drawable.covid_person, R.drawable.covid_person, R.drawable.covid_person, R.drawable.covid_person, R.drawable.covid_person, R.drawable.covid_person};

    /**
     * Layouts the image, title, and descriptions of the different symptom topics
     * @param savedInstanceState saves the information created in the view
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.symptoms);
        s2 = getResources().getStringArray(R.array.description);

        SymptomsAdapter symptomsAdapter = new SymptomsAdapter(this, s1, s2, images);
        recyclerView.setAdapter(symptomsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}