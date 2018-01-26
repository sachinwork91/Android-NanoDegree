package com.example.android.miwok;

import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Sachin on 2018-01-22.
 */

public class NumbersClickListener implements View.OnClickListener {


    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(),"Open the List of Numbers", Toast.LENGTH_SHORT ).show();
    }
}
