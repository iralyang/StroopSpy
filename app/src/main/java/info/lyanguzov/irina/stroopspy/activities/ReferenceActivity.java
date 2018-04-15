package info.lyanguzov.irina.stroopspy.activities;

import android.content.Intent;
import android.os.Bundle;

import info.lyanguzov.irina.stroopspy.R;
import info.lyanguzov.irina.stroopspy.enums.*;
import info.lyanguzov.irina.stroopspy.util.*;

public class ReferenceActivity extends BasicActivity {
    private final int NUMBER = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(R.layout.activity_reference);
    }

    @Override
    protected Thesaurus createThesaurus() {
        return Thesaurus.createReferenceThesaurus();
    }

    @Override
    protected int getMaxCount() {
        return NUMBER;
    }

    @Override
    protected void updateColor() {
        setColor(getWord().getMeaning());
    }

    @Override
    protected void startNextActivity() {
        Intent intent = new Intent(this, InfoActivity.class);
        Bundle bundle = new Bundle(2);
        bundle.putFloat("AVERAGE_TIME", this.statistics.getAverageTime());
        bundle.putFloat("PERCENTAGE_CORRECT", this.statistics.getCorrectPercentage());
        intent.putExtra("EXTRA_STATISTICS", bundle);
        startActivity(intent);
    }

    @Override
    protected void updateStatistics(Color clickedColor, Color referenceColor, Word word) {
        long t = System.currentTimeMillis() - this.startTime;
        this.statistics.addTime(t, clickedColor == referenceColor);
    }
}
