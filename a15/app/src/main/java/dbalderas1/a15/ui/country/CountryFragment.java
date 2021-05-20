package dbalderas1.a15.ui.country;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import dbalderas1.a15.R;

/**
 * Country Fragment Driver class for A15
 * @author Diana Balderas
 * @version 1.0
 */
public class CountryFragment extends Fragment {

    RecyclerView rvCovidCountry;
    ProgressBar progressBar;

    private static final String TAG = CountryFragment.class.getSimpleName();

    ArrayList<CovidCountry> covidCountries;

    /**
     * Creates a layout to list the countries with COVID-19 cases
     * @param inflater extends the amount of countries listed in the view
     * @param container contains the listed countries with COVID-19 cases
     * @param savedInstanceState saves the data that is actively current based on the date and time
     * @return a view that contains data from the Rest APi
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_country, container, false);

        //call view
        rvCovidCountry = root.findViewById(R.id.rvCovidCountry);
        progressBar = root.findViewById(R.id.progress_circular_country);
        rvCovidCountry.setLayoutManager(new LinearLayoutManager(getActivity()));

        //call Volley method
        getDataFromServer();

        return root;
    }


    /**
     * The REST APi's data is organized and arranged based on the design made in CovidCountryAdapter
     */
    private void showRecyclerView() {
        CovidCountryAdapter covidCountryAdapter = new CovidCountryAdapter(covidCountries);
        rvCovidCountry.setAdapter(covidCountryAdapter);
    }

    /**
     * Data is pulled from the REST API and displayed in the application
     */
    private void getDataFromServer() {
        String url = "https://corona.lmao.ninja/v2/countries";

        covidCountries = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onResponse" + response);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                            covidCountries.add(new CovidCountry(data.getString("country"), data.getString("cases")));
                        }
                        showRecyclerView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        },
                new Response.ErrorListener() {
                    /**
                     * If an error happens to load current COVID-19 cases, an error will be displayed
                     * @param error to be displayed when an error happens with the REST API
                     */
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Log.e(TAG, "onResponse: "+error);

                    }
                });

        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }
}