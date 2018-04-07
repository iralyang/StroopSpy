package info.lyanguzov.irina.stroopspy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import info.lyanguzov.irina.stroopspy.R;
import info.lyanguzov.irina.stroopspy.enums.Color;
import info.lyanguzov.irina.stroopspy.util.MainThesaurus;
import info.lyanguzov.irina.stroopspy.util.Word;

public class MainActivity extends AppCompatActivity {
    TextView wordView;
    Button colour1;
    Button colour2;
    Button colour3;
    Button colour4;
    Random random;
    int count;
    final int NUMBER = 50;

    MainThesaurus thesaurus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.random = new Random();
        this.count = NUMBER;
        this.thesaurus = new MainThesaurus();

        this.wordView = findViewById(R.id.word);
        this.colour1 = findViewById(R.id.color1);
        this.colour2 = findViewById(R.id.color2);
        this.colour3 = findViewById(R.id.color3);
        this.colour4 = findViewById(R.id.color4);

        replaceWord();
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

    private void replaceWord() {
        this.shuffleButtons();
        Word w = this.thesaurus.getRandomWord();
        this.wordView.setText(w.getRepresentation());
        int c = getResources().getColor(Color.getRandomColor().getResource());
        this.wordView.setTextColor(c);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        this.wordView.startAnimation(animation);
    }


    public void onColorButton(View view) {
        if (--this.count == 0) {
            Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
        }
        else {
            replaceWord();
        }
    }

}
