package com.abdul.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final long Start_time_in_millis=600000;
    private TextView mtextviewcountdown;
    private Button mButtonstartpause;
    private Button mButtonReset;



    private CountDownTimer mcountDownTimer;
    private  boolean mTimerRunning;

    private long mTimeleftinmillis=Start_time_in_millis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtextviewcountdown=findViewById(R.id.text_view_contdown);

        mButtonstartpause=findViewById(R.id.btn_start);
        mButtonReset=findViewById(R.id.btn_reset);

        mButtonstartpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimerRunning){
                    pauseTimer();

                }else{
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();

            }
        });
        updateCountDownText();
    }
    private void startTimer(){
        mcountDownTimer=new CountDownTimer(mTimeleftinmillis,1000) {
            @Override
            public void onTick(long millisUntilfinished) {
                mTimeleftinmillis=millisUntilfinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                mTimerRunning=false;
                mButtonstartpause.setText("Start");
                mButtonstartpause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);


            }
        }.start();


        mTimerRunning =true;
        mButtonstartpause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);



    }
    private void pauseTimer(){
        mcountDownTimer.cancel();
        mTimerRunning=false;
        mButtonstartpause.setText("start");
        mButtonReset.setVisibility(View.VISIBLE);

    }
    private void resetTimer(){
        mTimeleftinmillis=Start_time_in_millis;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonstartpause.setVisibility(View.VISIBLE);
    }
    private void updateCountDownText(){
        int minutes=(int)(mTimeleftinmillis/ 1000)/60;
        int seconds=(int)(mTimeleftinmillis/1000) %60;


        String timeLeftFormatted=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);

        mtextviewcountdown.setText(timeLeftFormatted);

    }


}