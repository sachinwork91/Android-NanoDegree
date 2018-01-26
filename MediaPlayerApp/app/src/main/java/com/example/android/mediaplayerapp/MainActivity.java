package com.example.android.mediaplayerapp;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    private double startTime = 0;
    private double finalTime = 0;

    public static int oneTimeOnly = 0;

    private TextView startTimetv;
    private TextView remainingTimetv;
    private SeekBar sb;
    private  MediaPlayer mediaPlayer;
    private Handler myHandler = new Handler();;
    private double forwardTime=5000;
    private double backTime=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       Button fastfwdbtn = (Button) findViewById(R.id.fastfwdbtn);
       Button pausebtn = (Button) findViewById(R.id.pausebtn);
       Button back= (Button)findViewById(R.id.back);
       Button rewind= (Button)findViewById(R.id.rewind );
           startTimetv = (TextView) findViewById(R.id.startTime);
        remainingTimetv  = (TextView) findViewById(R.id.remainingTime );
         sb = (SeekBar) findViewById(R.id.seekbar);
       ;

     //Attaching the song to the media player
     mediaPlayer= MediaPlayer.create(this, R.raw.song);
        finalTime=mediaPlayer.getDuration();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(getBaseContext(), "Completed", Toast.LENGTH_SHORT).show();
            }
        });

        //THis is the listener to start or stop the song
        pausebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }else{
                    mediaPlayer.start();
                }

                startTime = mediaPlayer.getCurrentPosition();
                finalTime = mediaPlayer.getDuration();
                if (oneTimeOnly == 0) {
                    sb.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }



               remainingTimetv.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        finalTime)))
                );

                startTimetv.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        startTime)))
                );
                sb.setProgress((int)startTime);
                myHandler.postDelayed(UpdateSongTime,100);
            }
        });


        fastfwdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;

                if((temp + forwardTime)<=finalTime){
                    startTime = startTime + forwardTime;
                    Log.d("SACHIN ", "ST => "+ startTime +"  ET "+ forwardTime) ;

                    mediaPlayer.seekTo((int) startTime);
                    sb.setProgress((int)startTime);
                    myHandler.postDelayed(UpdateSongTime,100);
                }
            }
        });


        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;

                if((temp - backTime)>=0){
                    startTime = startTime - backTime;
                    Log.d("SACHIN ", "ST => "+ startTime +"  ET "+ backTime) ;

                    mediaPlayer.seekTo((int) startTime);
                    sb.setProgress((int)startTime);
                    myHandler.postDelayed(UpdateSongTime,100);
               }else{
                    Toast.makeText(getApplicationContext(),"Cannot jump forward 5"
                            +"seconds",Toast.LENGTH_SHORT).show();
                }
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;


                    startTime = 0;
                    mediaPlayer.seekTo((int) startTime);
                    sb.setProgress((int)startTime);
                    myHandler.postDelayed(UpdateSongTime,100);

            }
        });

    }

    Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            double remaingTime= (mediaPlayer.getDuration()-startTime);
            startTimetv.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            remainingTimetv.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) remaingTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) remaingTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) remaingTime)))
            );


            sb.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };
}
