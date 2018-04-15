package info.lyanguzov.irina.stroopspy.activities;

import android.content.Intent;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import info.lyanguzov.irina.stroopspy.R;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle extras = getIntent().getBundleExtra("EXTRA_STATISTICS");
        if (extras != null) {
            String text = getString(R.string.text_info2);
            TextView info = findViewById(R.id.textInfo2);
            info.setText(text);
        }

    }

    public void exit(View view) {
        Process.killProcess(Process.myPid());
    }
    public void repeat(View view) {
        Intent intent = new Intent(this, TestingActivity.class);
        startActivity(intent);
    }
}
