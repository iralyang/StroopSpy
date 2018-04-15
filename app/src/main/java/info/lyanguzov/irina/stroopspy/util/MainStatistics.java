package info.lyanguzov.irina.stroopspy.util;

import android.os.Bundle;

import java.util.Dictionary;
import java.util.Hashtable;

import info.lyanguzov.irina.stroopspy.enums.*;

public class MainStatistics extends Statistics {
    public static final String EXTRA_LANGUAGES = "EXTRA_LANGUAGES";
    private float testingPercentage;
    private float testingAverageTime;
    private Dictionary<Language, Integer> languages;

    private MainStatistics() {
        super();
        this.languages = new Hashtable<>();
        for (Language language : Language.getAll()) {
            this.languages.put(language, 0);
        }
    }

    public MainStatistics(Bundle bundle) {
        this();
        testingPercentage = 100.0f;
        testingAverageTime = 1.0f;
        if (bundle != null) {
            testingAverageTime = bundle.getFloat(AVERAGE_TIME);
            testingPercentage = bundle.getFloat(PERCENTAGE_CORRECT);
        }
    }

    @Override
    public void update(long time, Color clickedColor, Color referenceColor, Word word) {
        super.update(time, clickedColor, referenceColor, word);
        Integer w = this.languages.get(word.getLanguage());
        if (clickedColor != referenceColor) {
            w += 1;
        }
        if (time > this.testingAverageTime * 1.1f) {
            w += 2;
        } else if (time > this.testingAverageTime * 1.05f) {
            w += 1;
        }
        this.languages.put(word.getLanguage(), w);
    }

    @Override
    public Bundle getResults() {
        Bundle results = super.getResults();
        Bundle l = new Bundle(Language.getCount());
        for (Language language : Language.getAll()) {
            l.putInt(language.getText(), this.languages.get(language));
        }
        results.putBundle(EXTRA_LANGUAGES, l);
        return results;
    }
}
