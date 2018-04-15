package info.lyanguzov.irina.stroopspy.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import info.lyanguzov.irina.stroopspy.R;
import info.lyanguzov.irina.stroopspy.enums.Color;
import info.lyanguzov.irina.stroopspy.util.*;

public class TestingActivity extends AppCompatActivity {
    private TextView wordView;
    private Button button_color1;
    private Button button_color2;
    private Button button_color3;
    private Button button_color4;
    private TextView textview_count;
    private TextView textview_time;
    private Animation animation;
    private Random random;
    private int counting;
    private final int NUMBER = 12;
    private Statistics statistics;
    private Color color;
    private Handler handler;
    private Timer timer;
    private long startTime;
    private TestingThesaurus thesaurus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        setupTimer();
        this.random = new Random();
        this.counting = 0;
        this.statistics = new Statistics();
        this.thesaurus = new TestingThesaurus();

        this.animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        this.wordView = findViewById(R.id.word);

        this.button_color1 = findViewById(R.id.color1);
        this.button_color2 = findViewById(R.id.color2);
        this.button_color3 = findViewById(R.id.color3);
        this.button_color4 = findViewById(R.id.color4);

        this.textview_time = findViewById(R.id.textview_time);
        this.textview_count = findViewById(R.id.textview_count);
        replaceTestingWord();
    }

    // to set up timer thread
    private void setupTimer() {
        this.handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                updateTime();
            }
        };
        this.timer = new Timer("TESTING_TIMER", true);
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Message message = handler.obtainMessage(1);
                message.sendToTarget();
            }
        }, 0, 10);
    }

    private void shuffleButtons() {
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        //Collections.shuffle(colors, this.random);
        this.button_color1.setText(colors.get(0).getTextResource());
        this.button_color1.setTag(colors.get(0));
        this.button_color2.setText(colors.get(1).getTextResource());
        this.button_color2.setTag(colors.get(1));
        this.button_color3.setText(colors.get(2).getTextResource());
        this.button_color3.setTag(colors.get(2));
        this.button_color4.setText(colors.get(3).getTextResource());
        this.button_color4.setTag(colors.get(3));
    }

    private void replaceTestingWord() {
        this.shuffleButtons();
        Word w = this.thesaurus.getRandomWord();
        this.wordView.setText(w.getRepresentation());
        this.color = w.getMeaning();
        this.wordView.setTextColor(getResources().getColor(color.getResource()));
        this.wordView.startAnimation(this.animation);
        this.textview_count.setText(String.format(Locale.getDefault(), "%d / %d", counting, NUMBER));
        resetTime();
    }

    private void showTime(long time) {
        long msec = time % 1000;
        long sec = (time / 1000) % 60;
        long min = time / 60000;
        this.textview_time.setText(String.format(Locale.getDefault(), "%d:%d.%02d", min, sec, msec / 10));
    }

    private void resetTime() {
        // to synchronise threads
        synchronized (this) {
            startTime = System.currentTimeMillis();
            showTime(0);
        }
    }

    private void updateTime() {
        // to synchronise threads
        synchronized (this) {
            long time = System.currentTimeMillis() - this.startTime;
            showTime(time);
        }
    }

    public void onButton(View view) {
        long t = System.currentTimeMillis() - this.startTime;
        Color c = (Color) view.getTag();
        this.statistics.addTime(t, c == this.color);
        if (++this.counting == NUMBER) {
            Intent intent = new Intent(this, InfoActivity.class);
            Bundle bundle = new Bundle(2);
            bundle.putFloat("AVERAGE_TIME", this.statistics.getAverageTime());
            bundle.putFloat("PERCENTAGE_CORRECT", this.statistics.getCorrectPercentage());
            intent.putExtra("EXTRA_STATISTICS", bundle);
            startActivity(intent);
        } else {
            replaceTestingWord();
        }
    }
}
