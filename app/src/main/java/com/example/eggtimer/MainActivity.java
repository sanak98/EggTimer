package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textview;
    SeekBar timerseekbar;
    Boolean counter=false;
    Button go;
    CountDownTimer countdowntimer;
    public void reset()
        {
            textview.setText("0:30");
            timerseekbar.setProgress(30);
            timerseekbar.setEnabled(true);
            countdowntimer.cancel();
            go.setText("Go");
            counter=false;
        }
    public void start(View view) {
        if (counter) {
            reset();
        }
        else {
            counter = true;
            timerseekbar.setEnabled(false);
            go.setText("Stop");
            countdowntimer =new CountDownTimer(timerseekbar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    timerfunc((int)l/ 1000);
                }
                @Override
                public void onFinish() {
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mplayer.start();
                    reset();
                }
            }.start();
        }
    }
    public void timerfunc(int i)
    {
        int mins=i/60;
        int secs=i-(mins*60);
        String secondString = Integer.toString(secs);

        if (secs <= 9) {
            secondString = "0" + secondString;
        }

        textview.setText(Integer.toString(mins) + ":" + secondString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerseekbar=findViewById(R.id.timerseekbar);
        textview=findViewById(R.id.textview);
        go=findViewById(R.id.button);
        timerseekbar.setMax(600);
        timerseekbar.setProgress(30);
        timerseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                timerfunc(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}