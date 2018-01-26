package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Sachin on 2018-01-22.
 */


public class WordAdapter extends ArrayAdapter<Word> {

    private static int mcolorResourceId;
    MediaPlayer mediaPlayer;
    private int voiceResourceId ;

    public WordAdapter(Context context, List<Word> words, int colorResourceId){
        super(context,0, words);
        mcolorResourceId=colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem=convertView;

        if(listItem == null){
            listItem= LayoutInflater.from(getContext()).inflate(R.layout.list_items, parent, false);
        }

        View textContainer= listItem.findViewById(R.id.text_container);

        int color= ContextCompat.getColor(getContext(), mcolorResourceId);
        textContainer.setBackgroundColor(color);

        Word word = getItem(position);

        TextView englishtv  = (TextView) listItem.findViewById(R.id.englishtv);
        TextView miwoktv    = (TextView) listItem.findViewById(R.id.miwoktv);
        ImageView imagevw   = (ImageView) listItem.findViewById(R.id.imagevw);
        ImageView voicevm   = (ImageView) listItem.findViewById(R.id.voicevw);

        voicevm.setBackgroundColor(color);




   /*      voiceResourceId= word.getVoiceResourceId();

        voicevm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 mediaPlayer=MediaPlayer.create(getContext(), voiceResourceId);
                mediaPlayer.start();

            }
        });
*/

        if (word.hasImage()) {
                        // If an image is available, display the provided image based on the resource ID
            imagevw.setImageResource(word.getImageResourceId());
                        // Make sure the view is visible
            imagevw.setVisibility(View.VISIBLE);
                    } else {
                        // Otherwise hide the ImageView (set visibility to GONE)
                    imagevw.setVisibility(View.GONE);
                    }
        englishtv.setText(word.getEnglishWord());

        miwoktv.setText(word.getMiwokWord());



        return listItem;
    }
}
