package info.lyanguzov.irina.stroopspy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import info.lyanguzov.irina.stroopspy.R;
import info.lyanguzov.irina.stroopspy.enums.Language;

public class ResultActivity extends AppCompatActivity {
    private float testingPercentage;
    private float testingAverageTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle extras = getIntent().getBundleExtra("EXTRA_STATISTICS");
        if (extras != null) {
            this.testingAverageTime = extras.getFloat("AVERAGE_TIME");
            this.testingPercentage = extras.getFloat("PERCENTAGE_CORRECT");
            TextView info = findViewById(R.id.text_result);
            String text = getString(R.string.text_result);
            info.setText(text);
            Bundle l = extras.getBundle("EXTRA_LANGUAGES");
            if (l != null) {
                Log.d("ResultActivity", "Language results:");
                for (Language language : Language.getAll()) {
                    String lang = language.getText();
                    int d = l.getInt(lang);
                    String s = String.format(Locale.getDefault(), "%s = %d", lang, d);
                    Log.d("ResultActivity", s);
                }
            }
        }
    }

    public void exit(View view) {
        Process.killProcess(Process.myPid());
    }

    public void repeat(View view) {
        Intent intent = new Intent(this, TestingActivity.class);
        Bundle bundle = new Bundle(2);
        bundle.putFloat("AVERAGE_TIME", this.testingAverageTime);
        bundle.putFloat("PERCENTAGE_CORRECT", this.testingPercentage);
        intent.putExtra("EXTRA_STATISTICS", bundle);
        startActivity(intent);
    }
}
