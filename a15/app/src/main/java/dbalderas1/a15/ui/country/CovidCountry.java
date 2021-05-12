package dbalderas1.a15.ui.country;

/**
 * Covid Country Driver class for A10
 * @author Diana Balderas
 * @version 1.0
 */
public class CovidCountry {
    String mCovidCountry, mCases, mTodayCases, mDeaths, mTodayDeaths, mRecovered, mCritical;

    public CovidCountry(String mCovidCountry, String mCases) {
        this.mCovidCountry = mCovidCountry;
        this.mCases = mCases;
        this.mTodayCases = mTodayCases;
        this.mDeaths = mDeaths;
        this.mTodayDeaths = mTodayDeaths;
        this.mRecovered = mRecovered;
        this.mCritical = mCritical;

    }

    /**
     * Rest API gets every country with COVID-19 cases
     * @return a list of countries that have COVID-19 cases
     */
    public String getmCovidCountry() {
        return mCovidCountry;
    }

    /**
     * Rest API gets the amount of COVID-19 in every country
     * @return an amount of COVID-19 cases available in every country
     */
    public String getmCases() {
        return mCases;
    }
}
