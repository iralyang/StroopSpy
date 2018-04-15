package info.lyanguzov.irina.stroopspy.activities;

import android.content.Intent;
import android.os.Bundle;

import info.lyanguzov.irina.stroopspy.R;
import info.lyanguzov.irina.stroopspy.util.*;

public class ReferenceActivity extends BasicActivity {
    private final int NUMBER = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(R.layout.activity_reference, Thesaurus.createReferenceThesaurus(),
                new Statistics());
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
        intent.putExtra(Statistics.BUNDLE_NAME, this.getStatistics().getResults());
        startActivity(intent);
    }
}
