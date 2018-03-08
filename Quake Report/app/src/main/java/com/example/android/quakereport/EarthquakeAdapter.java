package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;
import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Sachin on 2018-01-27.
 */

public class EarthquakeAdapter extends ArrayAdapter{


    //Setting up the Constructor
    public EarthquakeAdapter(Context context, List<Earthquake> earthquakeDetails){
        super(context, 0, earthquakeDetails);

    }
    /**
     * The part of the location string from the USGS service that we use to determine
     * whether or not there is a location offset present ("5km N of Cairo, Egypt").
     */
    private static final String LOCATION_SEPARATOR = " of ";

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem=convertView;

        if(listItem == null){
            listItem= LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        TextView magnitudetv=(TextView) listItem.findViewById(R.id.magnitudetv);
        TextView slocationtv=(TextView) listItem.findViewById(R.id.slocation);
        TextView plocationtv=(TextView) listItem.findViewById(R.id.plocation);
        TextView datetv=(TextView) listItem.findViewById(R.id.datetv);
        TextView timetv=(TextView) listItem.findViewById(R.id.timetv);



        Earthquake earthquake= (Earthquake) getItem(position);


        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudetv.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(earthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);




        magnitudetv.setText(formatMagnitude(earthquake.getMagnitude()));
        // Get the original location string from the Earthquake object,
        // which can be in the format of "5km N of Cairo, Egypt" or "Pacific-Antarctic Ridge".
        String originalLocation = earthquake.getLocation();

        // If the original location string (i.e. "5km N of Cairo, Egypt") contains
        // a primary location (Cairo, Egypt) and a location offset (5km N of that city)
        // then store the primary location separately from the location offset in 2 Strings,
        // so they can be displayed in 2 TextViews.
        String primaryLocation;
        String locationOffset;

        // Check whether the originalLocation string contains the " of " text
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            // Split the string into different parts (as an array of Strings)
            // based on the " of " text. We expect an array of 2 Strings, where
            // the first String will be "5km N" and the second String will be "Cairo, Egypt".
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            // Location offset should be "5km N " + " of " --> "5km N of"
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            // Primary location should be "Cairo, Egypt"
            primaryLocation = parts[1];
        } else {
            // Otherwise, there is no " of " text in the originalLocation string.
            // Hence, set the default location offset to say "Near the".
            locationOffset = "Near the";
            // The primary location will be the full location string "Pacific-Antarctic Ridge".
            primaryLocation = originalLocation;
        }
        slocationtv.setText(locationOffset);
        plocationtv.setText(primaryLocation);
        Date dateObject = new Date(earthquake.getTimeInMilliseconds());
        datetv.setText( formatDate(dateObject));
        timetv.setText( formatTime(dateObject));

        return listItem;


    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("LLL dd, yyyy");
        return timeFormat.format(dateObject);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId );
    }




}
