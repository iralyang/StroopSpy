package info.lyanguzov.irina.testapp1.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import info.lyanguzov.irina.testapp1.R;
import info.lyanguzov.irina.testapp1.util.TestingThesaurus;
import info.lyanguzov.irina.testapp1.util.Word;

public class TestingActivity extends AppCompatActivity {
    private TextView wordView;
    private TextView count;
    private int time;
    private Button colour1;
    private Button colour2;
    private Button colour3;
    private Button colour4;
    private Random random;
    private int counting;
    private final int NUMBER = 12;

    TestingThesaurus thesaurus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        this.random = new Random();
        this.counting = NUMBER;
        this.thesaurus = new TestingThesaurus();

        this.wordView = findViewById(R.id.word);

        this.colour1 = findViewById(R.id.colour1);
        this.colour2 = findViewById(R.id.colour2);
        this.colour3 = findViewById(R.id.colour3);
        this.colour4 = findViewById(R.id.colour4);

        replaceTestingWord();
    }
    private void shuffleButtons() {
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(R.string.colour_red);
        colors.add(R.string.colour_blue);
        colors.add(R.string.colour_yellow);
        colors.add(R.string.colour_green);
        Collections.shuffle(colors, this.random);
        this.colour1.setText(colors.get(0));
        this.colour2.setText(colors.get(1));
        this.colour3.setText(colors.get(2));
        this.colour4.setText(colors.get(3));
    }

    private void replaceTestingWord() {
        this.shuffleButtons();
        Word w = this.thesaurus.getRandomWord();
        this.wordView.setText(w.getRepresentation());
        int c = getResources().getColor(w.getMeaning().getResource());
        this.wordView.setTextColor(c);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        this.wordView.startAnimation(animation);
    }

    public void onColorButton(View view) {
        if (--counting == 0) {
            Intent intent = new Intent(this, InfoActivity.class);
            startActivity(intent);
        }
        else {
            replaceTestingWord();
        }
    }
    private void setTextView(TextView time) {
    }
}
