package info.lyanguzov.irina.stroopspy.activities;

import android.content.Intent;
import android.os.Bundle;

import info.lyanguzov.irina.stroopspy.R;
import info.lyanguzov.irina.stroopspy.enums.Color;
import info.lyanguzov.irina.stroopspy.util.*;

public class MainActivity extends BasicActivity {
    private final int NUMBER = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(R.layout.activity_main, Thesaurus.createMainThesaurus(),
                new MainStatistics(getIntent().getBundleExtra(Statistics.BUNDLE_NAME)));
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
        intent.putExtra(Statistics.BUNDLE_NAME, this.getStatistics().getResults());
        startActivity(intent);
    }
}
