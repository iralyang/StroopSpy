package info.lyanguzov.irina.testapp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    int count;

    Thesaurus thesaurus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.count = 50;
        this.thesaurus = new Thesaurus();

        this.wordView = findViewById(R.id.word);
        replaceWord();


        this.colour1 = findViewById(R.id.colour1);
        this.colour1.setText(R.string.colour_red);

        this.colour2 = findViewById(R.id.colour2);
        this.colour2.setText(R.string.colour_blue);

        this.colour3 = findViewById(R.id.colour3);
        this.colour3.setText(R.string.colour_yellow);

        this.colour4 = findViewById(R.id.colour4);
        this.colour4.setText(R.string.colour_green);


    }

    private void replaceWord() {
        Word w = this.thesaurus.getRandomTestingWord();
        this.wordView.setText(w.representation);
        int c = getResources().getColor(w.meaning.resource);
        this.wordView.setTextColor(c);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        this.wordView.startAnimation(animation);
        if (--count == 0) {
            Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
        }
    }


    public void onColorButton(View view) {
        replaceWord();
    }

}
