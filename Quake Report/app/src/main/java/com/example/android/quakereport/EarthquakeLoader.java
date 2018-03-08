package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Sachin on 2018-01-29.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>>{

    public static final String URL_TO_FETCH = " https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    public static final String LOG_TAG = EarthquakeLoader.class.getName();

    public EarthquakeLoader(Context context){
         super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.d("TEST" , "onStartLoading");
    }

    @Override
    public List<Earthquake> loadInBackground() {
        if( URL_TO_FETCH==null ){
            return null;
        }
        Log.d("TEST" , "loadInBackground");
        URL url = QueryUtils.createUrl(URL_TO_FETCH);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = QueryUtils.makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Populating the List of Earthquakes
        List<Earthquake> earthquakesList = QueryUtils.extractEarthquakes( jsonResponse );

        return earthquakesList;

    }
}
