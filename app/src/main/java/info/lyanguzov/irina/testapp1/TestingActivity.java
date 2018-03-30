package info.lyanguzov.irina.testapp1;

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

public class TestingActivity extends AppCompatActivity {
    TextView wordView;
    Button colour1;
    Button colour2;
    Button colour3;
    Button colour4;
    Random random;
    int count;

    Thesaurus thesaurus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.random = new Random();
        this.count = 12;
        this.thesaurus = new Thesaurus();

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
        Word w = this.thesaurus.getRandomTestingWord();
        this.wordView.setText(w.representation);
        int c = getResources().getColor(w.meaning.resource);
        this.wordView.setTextColor(c);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        this.wordView.startAnimation(animation);
    }

    public void onColorButton(View view) {
        if (--count == 0) {
            Intent intent = new Intent(this, InfoActivity.class);
            startActivity(intent);
        }
        else {
            replaceTestingWord();
        }
    }
}
