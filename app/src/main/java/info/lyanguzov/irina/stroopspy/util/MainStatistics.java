package info.lyanguzov.irina.stroopspy.util;

import android.os.Bundle;

import java.util.Dictionary;
import java.util.Hashtable;

import info.lyanguzov.irina.stroopspy.enums.*;

public class MainStatistics extends Statistics {
    public static final String EXTRA_LANGUAGES = "EXTRA_LANGUAGES";
    private float referencingPercentage;
    private float referencingAverageTime;
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
        //setting referencing data as 100%
        referencingPercentage = 100.0f;
        referencingAverageTime = 1.0f;
        if (bundle != null) {
            referencingAverageTime = bundle.getFloat(AVERAGE_TIME);
            referencingPercentage = bundle.getFloat(PERCENTAGE_CORRECT);
        }
    }

    @Override
    public void update(long time, Color clickedColor, Color referenceColor, Word word) {
        super.update(time, clickedColor, referenceColor, word);
        Integer w = this.languages.get(word.getLanguage());
        // adds 1 point for wrong click
        if (clickedColor != referenceColor) {
            w += 1;
        }
        // adds 1 point for 5% extra time and 2 points for 10% extra time used per word
        if (time > this.referencingAverageTime * 1.1f) {
            w += 2;
        } else if (time > this.referencingAverageTime * 1.05f) {
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
