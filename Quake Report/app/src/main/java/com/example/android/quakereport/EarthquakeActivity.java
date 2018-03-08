package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private List<Earthquake> earthquakes;

    public static final String URL_TO_FETCH=" https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    /** TextView that is displayed when the list is empty */
    private TextView emptyStateTextView;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);



        getSupportLoaderManager().initLoader(1, null, this).forceLoad();
      /** This is used to use the old behaviour of using AsyncTask to do network calling on backend thread
      /*  EarthquakeAsyncTask earthquakeAsyncTask= new EarthquakeAsyncTask();
      /*  earthquakeAsyncTask.execute(URL_TO_FETCH);*/

        }




   private void updateUI(){
       // Find a reference to the {@link ListView} in the layout
       ListView earthquakeListView = (ListView) findViewById(R.id.list);

       //Setting the Empty View
       emptyStateTextView = (TextView) findViewById(R.id.empty_view);
       earthquakeListView.setEmptyView(emptyStateTextView);


       EarthquakeAdapter earthquakeAdapter= new EarthquakeAdapter(getBaseContext(), earthquakes);

       earthquakeListView.setAdapter(earthquakeAdapter);

       earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               Earthquake currentEarthquake = earthquakes.get(position);

               Intent intent = new Intent(Intent.ACTION_VIEW);
               intent.setData(Uri.parse(currentEarthquake.getUrl()));
               startActivity(intent);


           }
       });

   }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {
        Log.d("TEST" , "onCreateLoader");
        return new EarthquakeLoader(EarthquakeActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> data) {
        Log.d("TEST" , "onLoadFinished");
        earthquakes=data;
        progressBar = (ProgressBar) findViewById(R.id.loading_spinner);
        progressBar.setVisibility(View.INVISIBLE);
        updateUI();



    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        Log.d("TEST" , "onLoaderReset");
        earthquakes=new ArrayList<Earthquake>();
        updateUI();
    }

   /* private class EarthquakeAsyncTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {

            if(urls==null || urls.length< 1 || urls[0]==null ){
                return null;
            }

            URL url = QueryUtils.createUrl(urls[0]);

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = null;
            try {
                jsonResponse = QueryUtils.makeHttpRequest(url);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error closing input stream", e);
            }

            return jsonResponse;
            }

        @Override
        protected void onPostExecute(String jsonResponse) {
            Log.d("SACHIN JSONRESPONSE => ",jsonResponse);
            // Create a fake list of earthquake locations.
            earthquakes=QueryUtils.extractEarthquakes( jsonResponse);//generateEarthquakeDetails();
             updateUI();
        }
    }
*/
}
