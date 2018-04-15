package info.lyanguzov.irina.stroopspy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import info.lyanguzov.irina.stroopspy.R;
import info.lyanguzov.irina.stroopspy.enums.Color;
import info.lyanguzov.irina.stroopspy.util.*;

public class MainActivity extends AppCompatActivity {
    private TextView wordView;
    private TextView count;
    private Button colour1;
    private Button colour2;
    private Button colour3;
    private Button colour4;
    private TextView textview_count;
    private TextView textview_time;
    private Random random;
    int counting;
    final int NUMBER = 50;
    private Statistics statistics;
    private Color color;
    private Handler handler;
    private Timer timer;
    private long startTime;

    MainThesaurus thesaurus;

    private float testingPercentage;
    private float testingAverageTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getBundleExtra("EXTRA_STATISTICS");
        if (extras != null) {
            testingAverageTime = extras.getFloat("AVERAGE_TIME");
            testingPercentage = extras.getFloat("PERCENTAGE_CORRECT");
        }
        setupTimer();
        this.random = new Random();
        this.counting = 0;
        this.statistics = new Statistics();
        this.thesaurus = new MainThesaurus();

        this.wordView = findViewById(R.id.word);
        this.colour1 = findViewById(R.id.color1);
        this.colour2 = findViewById(R.id.color2);
        this.colour3 = findViewById(R.id.color3);
        this.colour4 = findViewById(R.id.color4);

        this.textview_time = findViewById(R.id.textview_time);
        this.timer = new Timer(true);

        this.textview_count = findViewById(R.id.textview_count);
        replaceWord();
    }

    // to set up timer thread
    private void setupTimer() {
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                updateTime();
            }
        };
        timer = new Timer("TESTING_TIMER", true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Message message = handler.obtainMessage(1);
                message.sendToTarget();
            }
        }, 0, 10);
    }

    private void shuffleButtons() {
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(R.string.colour_red);
        colors.add(R.string.colour_blue);
        colors.add(R.string.colour_yellow);
        colors.add(R.string.colour_green);
        Collections.shuffle(colors, this.random);
        this.colour1.setText(colors.get(0));
        this.colour2.setText(colors.get(1));
        this.colour3.setText(colors.get(2));
        this.colour4.setText(colors.get(3));
    }

    private void replaceWord() {
        this.shuffleButtons();
        Word w = this.thesaurus.getRandomWord();
        this.wordView.setText(w.getRepresentation());
        int c = getResources().getColor(Color.getRandomColor().getResource());
        this.wordView.setTextColor(c);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        this.wordView.startAnimation(animation);
        this.textview_count.setText(String.format("%d / %d", counting, NUMBER));
        resetTime();
    }

    private void showTime(long time) {
        long msec = time % 1000;
        long sec = (time / 1000) % 60;
        long min = time / 60000;
        textview_time.setText(String.format("%d:%d.%02d", min, sec, msec / 10));
    }
    // to synchronise threads
    private void resetTime() {
        synchronized (this) {
            startTime = System.currentTimeMillis();
            showTime(0);
        }
    }

    private void updateTime() {
        synchronized (this) {
            long time = System.currentTimeMillis() - startTime;
            showTime(time);
        }
    }

    public void onButton(View view) {
        long t = System.currentTimeMillis() - this.startTime;
        Color c = (Color) view.getTag();
        this.statistics.addTime(t, c == this.color);


        if (++this.counting == NUMBER) {
            Intent intent = new Intent(this, ResultActivity.class);
            Bundle bundle = new Bundle(2);
            bundle.putFloat("AVERAGE_TIME", this.statistics.getAverageTime());
            bundle.putFloat("PERCENTAGE_CORRECT", this.statistics.getCorrectPercentage());
            intent.putExtra("EXTRA_STATISTICS", bundle);
            intent.putExtra("EXTRA_STATISTICS", bundle);
            startActivity(intent);
        } else {
            replaceWord();
        }
    }

}
