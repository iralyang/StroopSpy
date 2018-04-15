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
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import info.lyanguzov.irina.stroopspy.R;
import info.lyanguzov.irina.stroopspy.enums.*;
import info.lyanguzov.irina.stroopspy.util.*;

public class MainActivity extends AppCompatActivity {
    private TextView wordView;
    private Button button_color1;
    private Button button_color2;
    private Button button_color3;
    private Button button_color4;
    private TextView textview_count;
    private TextView textview_time;
    private Random random;
    private Word word;
    private Color color;
    private int counting;
    private final int NUMBER = 50;
    private Statistics statistics;
    private Handler handler;
    private Timer timer;
    private long startTime;
    private MainThesaurus thesaurus;
    private float testingPercentage;
    private float testingAverageTime;
    private Dictionary<Language, Integer> languages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getBundleExtra("EXTRA_STATISTICS");
        if (extras != null) {
            this.testingAverageTime = extras.getFloat("AVERAGE_TIME");
            this.testingPercentage = extras.getFloat("PERCENTAGE_CORRECT");
        }
        setupTimer();
        this.random = new Random();
        this.counting = 0;
        this.statistics = new Statistics();
        this.thesaurus = new MainThesaurus();
        this.languages = new Hashtable<>();
        for (Language language : Language.getAll()) {
            this.languages.put(language, 0);
        }

        this.wordView = findViewById(R.id.word);
        this.button_color1 = findViewById(R.id.color1);
        this.button_color2 = findViewById(R.id.color2);
        this.button_color3 = findViewById(R.id.color3);
        this.button_color4 = findViewById(R.id.color4);

        this.textview_time = findViewById(R.id.textview_time);
        this.textview_count = findViewById(R.id.textview_count);
        replaceWord();
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
        Collections.shuffle(colors, this.random);
        this.button_color1.setText(colors.get(0).getTextResource());
        this.button_color1.setTag(colors.get(0));
        this.button_color2.setText(colors.get(1).getTextResource());
        this.button_color2.setTag(colors.get(1));
        this.button_color3.setText(colors.get(2).getTextResource());
        this.button_color3.setTag(colors.get(2));
        this.button_color4.setText(colors.get(3).getTextResource());
        this.button_color4.setTag(colors.get(3));
    }

    private void replaceWord() {
        this.shuffleButtons();
        this.word = this.thesaurus.getRandomWord();
        this.wordView.setText(this.word.getRepresentation());
        this.color = Color.getRandomColor();
        this.wordView.setTextColor(getResources().getColor(this.color.getResource()));
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        this.wordView.startAnimation(animation);
        this.textview_count.setText(String.format(Locale.getDefault(), "%d / %d", this.counting, NUMBER));
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
            this.startTime = System.currentTimeMillis();
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
        updateStatistics(view);
        if (++this.counting == NUMBER) {
            Intent intent = new Intent(this, ResultActivity.class);
            Bundle bundle = new Bundle(3);
            bundle.putFloat("AVERAGE_TIME", this.statistics.getAverageTime());
            bundle.putFloat("PERCENTAGE_CORRECT", this.statistics.getCorrectPercentage());
            Bundle l = new Bundle(Language.getCount());
            for (Language language : Language.getAll()) {
                l.putInt(language.getText(), this.languages.get(language));
            }
            bundle.putBundle("EXTRA_LANGUAGES", l);
            intent.putExtra("EXTRA_STATISTICS", bundle);
            startActivity(intent);
        } else {
            replaceWord();
        }
    }

    private void updateStatistics(View view) {
        long t = System.currentTimeMillis() - this.startTime;
        Color c = (Color) view.getTag();
        boolean correct = c == this.color;
        this.statistics.addTime(t, correct);
        if (t > this.testingAverageTime * 1.05f) {
            Integer w = this.languages.get(this.word.getLanguage());
            ++w;
            this.languages.put(this.word.getLanguage(), w);
        }
    }
}
