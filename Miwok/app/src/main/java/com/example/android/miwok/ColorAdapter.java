package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sachin on 2018-01-22.
 */

public class ColorAdapter extends ArrayAdapter<Color> {


    public ColorAdapter(Context context, List<Color> colors ){
        super(context,0, colors);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View listItem=convertView;
        if(listItem == null){
            listItem= LayoutInflater.from(getContext()).inflate(R.layout.list_items, parent, false);
        }

        Color color = getItem(position);

        TextView englishtv = (TextView) listItem.findViewById(R.id.englishtv);
        TextView miwoktv = (TextView) listItem.findViewById(R.id.miwoktv);

        englishtv.setText(color.getEnglishWord());

        miwoktv.setText(color.getMiwokWord());


        return listItem;


    }
}
