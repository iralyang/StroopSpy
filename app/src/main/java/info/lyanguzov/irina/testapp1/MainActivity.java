package info.lyanguzov.irina.testapp1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView wordView;
    Button colour1;
    Button colour2;
    Button colour3;
    Button colour4;

    Thesaurus thesaurus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.thesaurus = new Thesaurus();

        this.wordView = findViewById(R.id.word);
        replaceWord();

        View.OnClickListener listener = createListener();
        this.colour1 = findViewById(R.id.colour1);
        this.colour1.setText(R.string.colour_red);
        this.colour1.setOnClickListener(listener);
        this.colour2 = findViewById(R.id.colour2);
        this.colour2.setText(R.string.colour_blue);
        this.colour2.setOnClickListener(listener);
        this.colour3 = findViewById(R.id.colour3);
        this.colour3.setText(R.string.colour_yellow);
        this.colour3.setOnClickListener(listener);
        this.colour4 = findViewById(R.id.colour4);
        this.colour4.setText(R.string.colour_green);
        this.colour4.setOnClickListener(listener);

    }

    private void replaceWord() {
        Word w = this.thesaurus.getRandomTestingWord();
        this.wordView.setText(w.representation);
        int c = getResources().getColor(w.meaning.resource);
        this.wordView.setTextColor(c);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        this.wordView.startAnimation(animation);
    }

    private View.OnClickListener createListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceWord();
            }
        };
    }
}
