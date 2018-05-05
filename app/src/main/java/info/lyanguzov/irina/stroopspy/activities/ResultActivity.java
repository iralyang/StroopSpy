package info.lyanguzov.irina.stroopspy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import info.lyanguzov.irina.stroopspy.R;
import info.lyanguzov.irina.stroopspy.enums.*;
import info.lyanguzov.irina.stroopspy.util.*;

public class ResultActivity extends AppCompatActivity {
    private static final boolean DEBUG = false;
    private float testingPercentage;
    private float testingAverageTime;

    class LanguageStat implements Comparable<LanguageStat> {
        final int count;
        final String language;

        LanguageStat(int count, String language) {
            this.count = count;
            this.language = language;
        }

        // to help sorting languages in decending order
        @Override
        public int compareTo(@NonNull LanguageStat o) {
            if (count < o.count)
                return 1;
            if (count > o.count)
                return -1;
            return -language.compareTo(o.language);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle extras = getIntent().getBundleExtra(Statistics.BUNDLE_NAME);
        if (extras != null) {
            this.testingAverageTime = extras.getFloat(Statistics.AVERAGE_TIME);
            this.testingPercentage = extras.getFloat(Statistics.PERCENTAGE_CORRECT);
            TextView info = findViewById(R.id.text_result);
            String text = getString(R.string.text_result);
            String languageText = "";
            ArrayList<LanguageStat> resultList = new ArrayList<>();
            Bundle l = extras.getBundle(MainStatistics.EXTRA_LANGUAGES);
            if (l != null) {
                for (Language language : Language.getAll()) {
                    String lang = language.getText();
                    int d = l.getInt(lang);
                    if (d > 0) {
                        resultList.add(new LanguageStat(d, lang));
                        if (DEBUG) {
                            String s = String.format(Locale.getDefault(), "%s = %d%n", lang, d);
                            languageText += s;
                            Log.d("ResultActivity", s);
                        }
                    }
                }
                Collections.sort(resultList);
                if (resultList.size() > 0) {
                    languageText += resultList.get(0).language;
                }
                if (resultList.size() > 1) {
                    languageText += ", " + resultList.get(1).language;
                }
                if (resultList.size() > 2) {
                    languageText += ", " + resultList.get(2).language;
                }
            }
            if (languageText.isEmpty()) {
                info.setText(getString(R.string.text_result_no_spy));
            }
            else {
                info.setText(getString(R.string.text_result, languageText));
            }
        }
    }

    public void exit(View view) {
        Process.killProcess(Process.myPid());
    }

    public void repeat(View view) {
        Intent intent = new Intent(this, ReferenceActivity.class);
        Bundle bundle = new Bundle(2);
        bundle.putFloat(Statistics.AVERAGE_TIME, this.testingAverageTime);
        bundle.putFloat(Statistics.PERCENTAGE_CORRECT, this.testingPercentage);
        intent.putExtra(Statistics.BUNDLE_NAME, bundle);
        finish();
        startActivity(intent);
    }
}
