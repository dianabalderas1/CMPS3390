package dbalderas1.a15.ui.country;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dbalderas1.a15.R;

/**
 * Covid Country Adapter Driver class for A15
 * @author Diana Balderas
 * @version 1.0
 */
public class CovidCountryAdapter extends RecyclerView.Adapter<CovidCountryAdapter.ViewHolder>{

    ArrayList<CovidCountry> covidCountries;

    /**
     * Every country is listed based on the amount of COVID-19 cases
     * @param covidCountries organizes the amount of COVID-19 cases based on the country
     */
    public CovidCountryAdapter(ArrayList<CovidCountry> covidCountries) {
        this.covidCountries = covidCountries;
    }

    /**
     * The amount of COVID-19 cases in every country is listed and organized by the view
     * @param parent handles the overall data of the REST API and is inherit to this class
     * @param viewType the type of view layout to display the data
     * @return the view is designed to make the data readable
     */
    @NonNull
    @Override
    public CovidCountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_covid_country, parent, false);
        return new ViewHolder(view);
    }

    /**
     *  The data from the REST API is listed based on the total cases and the name of the country
     * @param holder holds the data in displayed within the view of the application
     * @param position the data is organized and positioned in a readable view
     */
    @Override
    public void onBindViewHolder(@NonNull CovidCountryAdapter.ViewHolder holder, int position) {
        CovidCountry covidCountry = covidCountries.get(position);
        holder.tvTotalCases.setText(covidCountry.getmCases());
        holder.tvCountryName.setText(covidCountry.getmCovidCountry());

    }

    /**
     * Every country is measured based on amount of COVID-19 cases
     * @return the countries with COVID-19 cases
     */
    @Override
    public int getItemCount() {
        return covidCountries.size();
    }

    /**
     * The data from the REST API is listed based on the total cases and the name of the country
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTotalCases, tvCountryName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTotalCases = itemView.findViewById(R.id.tvTotalCases);
            tvCountryName = itemView.findViewById(R.id.tvCountryName);
        }
    }
}
