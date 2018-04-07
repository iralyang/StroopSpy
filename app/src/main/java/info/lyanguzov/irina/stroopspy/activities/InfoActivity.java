package info.lyanguzov.irina.stroopspy.activities;

import android.content.Intent;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import info.lyanguzov.irina.stroopspy.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Bundle extras = getIntent().getBundleExtra("EXTRA_STATISTICS");
        if (extras != null) {
            float t = extras.getFloat("AVERAGE_TIME") * 0.001f;
            float p = extras.getFloat("PERCENTAGE_CORRECT");
            String text = getString(R.string.text_info1, t, p);
            TextView info = findViewById(R.id.textInfo1);
            info.setText(text);
        }
    }

    public void exit(View view) {
        Process.killProcess(Process.myPid());
    }
    public void next(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
