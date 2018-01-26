package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PhrasesActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        final List<Word> words = getPhrases();

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.listPhrases);
        listView.setAdapter(adapter);


        //Setting the Listener for ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word currentWord= words.get(position);
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(getBaseContext(),currentWord.getVoiceResourceId());
                //Setting OnCompletion Listener on the Media Player
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        releaseMediaPlayer();
                    }
                });
                mediaPlayer.start();
            }
        });


    }

    List<Word> getPhrases() {
        List<Word> words = new ArrayList<Word>();
        words.add(new Word("Where are you going?", "minto wuksus",-1, R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә",-1,R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...",-1, R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?",-1, R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit",-1,R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?", -1 ,R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm",-1,R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm",-1,R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis",-1,R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem",-1,R.raw.phrase_come_here));


        return words;
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
