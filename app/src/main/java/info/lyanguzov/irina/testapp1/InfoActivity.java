package info.lyanguzov.irina.testapp1;

import android.content.Intent;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    public void exit(View view) {
        Process.killProcess(Process.myPid());
    }
    public void next(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
