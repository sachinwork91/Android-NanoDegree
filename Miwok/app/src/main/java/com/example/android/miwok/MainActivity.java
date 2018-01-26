/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);


        TextView numberstv = (TextView) findViewById(R.id.numbers);
        TextView phrasesv = (TextView) findViewById(R.id.phrases);
        TextView familytv = (TextView) findViewById(R.id.family);
        TextView colorstv = (TextView) findViewById(R.id.colors);

        NumbersClickListener clickListener = new NumbersClickListener();





        numberstv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(view.getContext(), "Open the List of Numbers", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent( view.getContext() , NumbersActivity.class);
                startActivity(intent);
            }
        });

        phrasesv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Open the List of Phrases", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent( view.getContext() , PhrasesActivity.class);
                startActivity(intent);
            }
        });

        familytv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Open the List of Family", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent( view.getContext() , FamilyActivity.class);
                startActivity(intent);
            }
        });

        colorstv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Open the List of Colors", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent( view.getContext() , ColorsActivity.class);
                startActivity(intent);

            }

                    });


    }

    //This method handles the opening of the List
    public void openNumbersList(View view) {

        Intent intent = new Intent(this, NumbersActivity.class);
        startActivity(intent);

    }



}
