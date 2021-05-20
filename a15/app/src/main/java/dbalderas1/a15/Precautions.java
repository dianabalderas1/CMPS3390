package dbalderas1.a15;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Precautions Driver class for A15
 * @author Diana Balderas
 * @version 1.0
 */
public class Precautions extends Symptoms {

    RecyclerView recyclerView;

    String p3[], p4[];
    int p_images[] = {R.drawable.covid_person, R.drawable.covid_person, R.drawable.covid_person, R.drawable.covid_person, R.drawable.covid_person, R.drawable.covid_person};

    /**
     * Layouts the image, title, and descriptions of the different precaution topics
     * @param savedInstanceState saves the information created in the view
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precautions);

        recyclerView = findViewById(R.id.recyclerView);

        p3 = getResources().getStringArray(R.array.precautions);
        p4 = getResources().getStringArray(R.array.precautions_description);

        PrecautionsAdapter precautionsAdapter = new PrecautionsAdapter(this, p3, p4, p_images);
        recyclerView.setAdapter(precautionsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
