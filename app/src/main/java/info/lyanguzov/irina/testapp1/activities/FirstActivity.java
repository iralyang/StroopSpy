package info.lyanguzov.irina.testapp1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import info.lyanguzov.irina.testapp1.R;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }
    public void exit(View view) {
        Process.killProcess(Process.myPid());
    }
    public void start(View view) {
        Intent intent = new Intent(this, TestingActivity.class);
        startActivity(intent);
    }
}
