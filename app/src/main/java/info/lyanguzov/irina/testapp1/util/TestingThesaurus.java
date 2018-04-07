package info.lyanguzov.irina.testapp1.util;

import java.util.Random;

import info.lyanguzov.irina.testapp1.enums.Color;
import info.lyanguzov.irina.testapp1.enums.Language;

public class TestingThesaurus {
    //Declaring instance variables
    private Word words[]; //array of words
    private Random random;

    // Constructor
    public TestingThesaurus() {
        this.random = new Random();
        this.words = new Word[]{
                new Word(Color.RED, Language.KLINGON, "Doq"),
                new Word(Color.YELLOW, Language.KLINGON, "SuD 'ej wov"),
                new Word(Color.BLUE, Language.KLINGON, "SuD"),
                new Word(Color.GREEN, Language.KLINGON, "SuDqu'"),

                new Word(Color.RED, Language.ELVISH, "Carnë"),
                new Word(Color.YELLOW, Language.ELVISH, "Malina "),
                new Word(Color.BLUE, Language.ELVISH, "Luin"),
                new Word(Color.GREEN, Language.ELVISH, "Laiqua"),

                new Word(Color.RED, Language.DOTHRAKI, "Virzeth"),
                new Word(Color.YELLOW, Language.DOTHRAKI, "Veltor"),
                new Word(Color.BLUE, Language.DOTHRAKI, "Thelis"),
                new Word(Color.GREEN, Language.DOTHRAKI, "Dahaan"),
        };
    }

    // Method to get random testing word
    public Word getRandomWord() {
        int n = this.random.nextInt(this.words.length);
        return this.words[n];
    }
}
