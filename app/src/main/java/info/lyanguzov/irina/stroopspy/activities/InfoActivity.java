package info.lyanguzov.irina.stroopspy.activities;

import android.content.Intent;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import info.lyanguzov.irina.stroopspy.R;
import info.lyanguzov.irina.stroopspy.util.Statistics;

public class InfoActivity extends AppCompatActivity {

    private float percentage;
    private float averageTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Bundle extras = getIntent().getBundleExtra(Statistics.BUNDLE_NAME);
        if (extras != null) {
            averageTime = extras.getFloat(Statistics.AVERAGE_TIME);
            percentage = extras.getFloat(Statistics.PERCENTAGE_CORRECT);
            String text = getString(R.string.text_info1, averageTime * 0.001f, percentage);
            TextView info = findViewById(R.id.textInfo1);
            info.setText(text);
        }
    }

    public void exit(View view) {
        Process.killProcess(Process.myPid());
    }

    public void next(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle(2);
        bundle.putFloat(Statistics.AVERAGE_TIME, averageTime);
        bundle.putFloat(Statistics.PERCENTAGE_CORRECT, percentage);
        intent.putExtra(Statistics.BUNDLE_NAME, bundle);
        finish();
        startActivity(intent);
    }
}
