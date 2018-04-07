package info.lyanguzov.irina.stroopspy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import info.lyanguzov.irina.stroopspy.R;
import info.lyanguzov.irina.stroopspy.enums.Color;
import info.lyanguzov.irina.stroopspy.util.*;

public class TestingActivity extends AppCompatActivity {
    private TextView wordView;
    private TextView count;
    private long time;
    private Button button_color1;
    private Button button_color2;
    private Button button_color3;
    private Button button_color4;
    private Random random;
    private int counting;
    private final int NUMBER = 12;
    private Statistics statistics;
    private Color color;

    TestingThesaurus thesaurus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        this.random = new Random();
        this.counting = NUMBER;
        this.statistics = new Statistics();
        this.thesaurus = new TestingThesaurus();

        this.wordView = findViewById(R.id.word);

        this.button_color1 = findViewById(R.id.color1);
        this.button_color2 = findViewById(R.id.color2);
        this.button_color3 = findViewById(R.id.color3);
        this.button_color4 = findViewById(R.id.color4);

        replaceTestingWord();
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

    private void replaceTestingWord() {
        this.shuffleButtons();
        this.time = System.currentTimeMillis();
        Word w = this.thesaurus.getRandomWord();
        this.wordView.setText(w.getRepresentation());
        this.color = w.getMeaning();
        this.wordView.setTextColor(getResources().getColor(color.getResource()));
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        this.wordView.startAnimation(animation);
    }

    public void onButton(View view) {
        long t = System.currentTimeMillis() - this.time;
        Color c = (Color)view.getTag();
        this.statistics.addTime(t, c == this.color);
        if (--this.counting == 0) {
            Intent intent = new Intent(this, InfoActivity.class);
            Bundle bundle = new Bundle(2);
            bundle.putFloat("AVERAGE_TIME", this.statistics.getAverageTime());
            bundle.putFloat("PERCENTAGE_CORRECT", this.statistics.getCorrectPercentage());
            intent.putExtra("EXTRA_STATISTICS", bundle);
            startActivity(intent);
        }
        else {
            replaceTestingWord();
        }
    }
}
