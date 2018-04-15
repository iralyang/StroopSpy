package info.lyanguzov.irina.stroopspy.activities;

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
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import info.lyanguzov.irina.stroopspy.R;
import info.lyanguzov.irina.stroopspy.enums.Color;
import info.lyanguzov.irina.stroopspy.util.*;

abstract class BasicActivity extends AppCompatActivity {
    protected TextView wordView;
    protected TextView textview_time;
    protected TextView textview_count;
    protected Button button_color1;
    protected Button button_color2;
    protected Button button_color3;
    protected Button button_color4;
    protected Animation animation;
    protected long startTime;
    private Handler handler;
    private Timer timer;
    protected Random random;
    private Thesaurus thesaurus;
    private Word word;
    private Color color;
    protected int count;
    protected Statistics statistics;

    protected void initialize(int contentView) {
        setContentView(contentView);
        setupTimer();
        this.random = new Random();
        this.thesaurus = createThesaurus();
        this.count = 0;
        this.statistics = new Statistics();
        this.wordView = findViewById(R.id.word);
        this.textview_time = findViewById(R.id.textview_time);
        this.textview_count = findViewById(R.id.textview_count);
        this.button_color1 = findViewById(R.id.color1);
        this.button_color2 = findViewById(R.id.color2);
        this.button_color3 = findViewById(R.id.color3);
        this.button_color4 = findViewById(R.id.color4);
        this.animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        replaceWord();
    }

    protected abstract Thesaurus createThesaurus();

    protected abstract int getMaxCount();

    private void updateWord() {
        this.word = this.thesaurus.getRandomWord();
    }

    protected Word getWord() {
        return word;
    }

    protected abstract void updateColor();

    protected Color getColor() {
        return color;
    }

    protected void setColor(Color color) {
        this.color = color;
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

    private void replaceWord() {
        shuffleButtons();
        resetTime();
        updateWord();
        updateColor();
        this.wordView.setText(this.word.getRepresentation());
        this.wordView.setTextColor(getResources().getColor(this.color.getResource()));
        this.wordView.startAnimation(this.animation);
        this.textview_count.setText(String.format(Locale.getDefault(), "%d / %d", this.count, getMaxCount()));
    }

    public void onButton(View view) {
        Color c = (Color) view.getTag();
        updateStatistics(c, this.color, this.word);
        if (++this.count == getMaxCount()) {
            startNextActivity();
        } else {
            replaceWord();
        }
    }

    protected abstract void startNextActivity();

    protected abstract void updateStatistics(Color clickedColor, Color referenceColor, Word word);
}
