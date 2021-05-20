package dbalderas1.a15.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;

import dbalderas1.a15.KnowMoreActivity;
import dbalderas1.a15.R;
import dbalderas1.a15.SplashScreen;

/**
 * Home Fragment Driver class for A15
 * @author Diana Balderas
 * @version 1.0
 */
public class HomeFragment extends Fragment{

        private TextView tvTotalConfirmed, tvTotalDeaths, tvTotalRecovered;
        private ProgressBar progressBar;

        /**
         * A view displays the data based on the amount of COVID-19 deaths, recovered, and confirmed
         *
         * @param inflater           extends the amount of COVID-19 cases listed in the view
         * @param container          contains the listed COVID-19 cases based on the date
         * @param savedInstanceState saves the data that is actively current based on the date and time
         * @return a view that contains data from the Rest APi
         */
        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {

            View root = inflater.inflate(R.layout.fragment_home, container, false);

            super.onCreate(savedInstanceState);

            // call view
            tvTotalConfirmed = root.findViewById(R.id.tvTotalConfirmed);
            tvTotalDeaths = root.findViewById(R.id.tvTotalDeaths);
            tvTotalRecovered = root.findViewById(R.id.tvTotalRecovered);
            progressBar = root.findViewById(R.id.progress_circular_home);

            //call volley
            getData();

            return root;
        }

        /**
         * Data is pulled from the REST API and displayed in the application
         */
        private void getData() {
            RequestQueue queue = Volley.newRequestQueue(getActivity());

            String url = "https://corona.lmao.ninja/v2/all";

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                /**
                 * The data from the REST API responds to the setup text for each type of COVID-19 case
                 * @param response REST API responds to the layout of the view
                 */
                @Override
                public void onResponse(String response) {
                    progressBar.setVisibility(View.GONE);

                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        tvTotalConfirmed.setText(jsonObject.getString("cases"));
                        tvTotalDeaths.setText(jsonObject.getString("deaths"));
                        tvTotalRecovered.setText(jsonObject.getString("recovered"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }, new Response.ErrorListener() {
                /**
                 * If an error happens to load current COVID-19 cases, an error will be displayed
                 * @param error to be displayed when an error happens with the REST API
                 */
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressBar.setVisibility(View.GONE);
                    Log.d("Error Response", error.toString());

                }
            });

            queue.add(stringRequest);
        }




}