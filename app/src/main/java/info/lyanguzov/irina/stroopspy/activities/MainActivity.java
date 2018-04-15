package info.lyanguzov.irina.stroopspy.activities;

import android.content.Intent;
import android.os.Bundle;

import java.util.Dictionary;
import java.util.Hashtable;

import info.lyanguzov.irina.stroopspy.R;
import info.lyanguzov.irina.stroopspy.enums.*;
import info.lyanguzov.irina.stroopspy.util.*;

public class MainActivity extends BasicActivity {
    private final int NUMBER = 50;
    private float testingPercentage;
    private float testingAverageTime;
    private Dictionary<Language, Integer> languages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(R.layout.activity_main);
        Bundle extras = getIntent().getBundleExtra("EXTRA_STATISTICS");
        if (extras != null) {
            this.testingAverageTime = extras.getFloat("AVERAGE_TIME");
            this.testingPercentage = extras.getFloat("PERCENTAGE_CORRECT");
        }
        this.languages = new Hashtable<>();
        for (Language language : Language.getAll()) {
            this.languages.put(language, 0);
        }
    }

    @Override
    protected Thesaurus createThesaurus() {
        return Thesaurus.createMainThesaurus();
    }

    @Override
    protected int getMaxCount() {
        return NUMBER;
    }

    @Override
    protected void updateColor() {
        setColor(Color.getRandomColor());
    }

    @Override
    protected void startNextActivity() {
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
    }

    @Override
    protected void updateStatistics(Color clickedColor, Color referenceColor, Word word) {
        long t = System.currentTimeMillis() - this.startTime;
        boolean correct = clickedColor == referenceColor;
        this.statistics.addTime(t, correct);
        Integer w = this.languages.get(word.getLanguage());
        if (!correct) {
            w += 1;
        }
        if (t > this.testingAverageTime * 1.1f) {
            w += 2;
        } else if (t > this.testingAverageTime * 1.05f) {
            w += 1;
        }
        this.languages.put(word.getLanguage(), w);
    }
}
