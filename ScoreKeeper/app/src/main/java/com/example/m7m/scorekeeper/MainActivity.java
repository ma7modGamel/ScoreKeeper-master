package com.example.m7m.scorekeeper;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button a3p, a2p, a1p, b3p, b2p, b1p, hnt, rst;
    TextView scoreTeamA, scoreTeamB, strtmtch;
    RelativeLayout layout;
    LinearLayout layout2, layoutScore;
    ImageView img;
    static final long START_TIME_IN_MILLIS = 600000;
    TextView mTextViewCountDown, mtextPause;
    CountDownTimer mCountDownTimer;
    boolean mTimerRunning;
    long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        a3p = findViewById(R.id.threePointA);
        a2p = findViewById(R.id.twoPointA);
        a1p = findViewById(R.id.onePointA);
        b3p = findViewById(R.id.threePointB);
        b2p = findViewById(R.id.twoPointB);
        b1p = findViewById(R.id.onePointB);
        scoreTeamA = findViewById(R.id.scoreTeamA);
        scoreTeamB = findViewById(R.id.scoreTeamB);
        layout = findViewById(R.id.relateveGone);
        img = findViewById(R.id.img);
        hnt = findViewById(R.id.hnt);
        strtmtch = findViewById(R.id.startMatch);
        layout2 = findViewById(R.id.layout2);
        layoutScore = findViewById(R.id.layoutScore);
        rst = findViewById(R.id.reset);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mtextPause = findViewById(R.id.textPause);
        mtextPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });
    }

    public void threeP_A(View view) {
        increase_A(3);
    }

    public void twoP_A(View view) {
        increase_A(2);
    }

    public void oneP_A(View view) {
        increase_A(1);
    }

    public void threeP_B(View view) {
        increase_B(3);
    }

    public void twoP_B(View view) {
        increase_B(2);
    }

    public void oneP_B(View view) {
        increase_B(1);
    }

    public void resetScore(View view) {
        scoreTeamB.setText("0");
        scoreTeamA.setText("0");
        mTextViewCountDown.setText("00:00");
        pauseTimer();
    }

    public void increase_A(int numberOfPoint) {
        scoreTeamA.setText((Integer.valueOf(scoreTeamA.getText().toString())) + numberOfPoint + "");
    }

    public void increase_B(int numberOfPoint) {
        scoreTeamB.setText((Integer.valueOf(scoreTeamB.getText().toString())) + numberOfPoint + "");
    }

    public void switchBack(View view) {
        layout2.setVisibility(View.VISIBLE);
        strtmtch.setVisibility(View.GONE);
        hnt.setVisibility(View.GONE);
        img.setVisibility(View.VISIBLE);
        layout.setVisibility(View.VISIBLE);
        mtextPause.setVisibility(View.VISIBLE);
        mtextPause.setText("start");
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mtextPause.setText("Start");
                mtextPause.setVisibility(View.INVISIBLE);
            }
        }.start();
        mTimerRunning = true;
        mtextPause.setText("pause");
        layoutScore.setVisibility(View.VISIBLE);
        rst.setVisibility(View.VISIBLE);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mtextPause.setText("Start");
        layoutScore.setVisibility(View.GONE);
        rst.setVisibility(View.GONE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }
}
