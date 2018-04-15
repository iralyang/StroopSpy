package info.lyanguzov.irina.stroopspy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import info.lyanguzov.irina.stroopspy.R;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        TextView text = findViewById(R.id.text_welcome);
        text.setText(String.format(getString(R.string.welcome)));
    }
    public void exit(View view) {
        Process.killProcess(Process.myPid());
    }
    public void start(View view) {
        Intent intent = new Intent(this, ReferenceActivity.class);
        startActivity(intent);
    }
}
