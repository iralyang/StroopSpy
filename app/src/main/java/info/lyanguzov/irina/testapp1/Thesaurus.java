package info.lyanguzov.irina.testapp1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Thesaurus {
    //Declaring instance variables
    private Word testingWords[]; //array of words
    private Word words[];
    private Random random;

    // Constructor
    public Thesaurus() {
        this.random = new Random();
        this.testingWords = new Word[] {
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

        this.words = new Word[] {
                new Word(Color.RED, Language.ENGLISH, "Red"),
                new Word(Color.YELLOW, Language.ENGLISH, "Yellow"),
                new Word(Color.BLUE, Language.ENGLISH, "Blue"),
                new Word(Color.GREEN, Language.ENGLISH, "Green"),

                new Word(Color.RED, Language.RUSSIAN, "Красный"),
                new Word(Color.YELLOW, Language.RUSSIAN, "Желтый"),
                new Word(Color.BLUE, Language.RUSSIAN, "Синий"),
                new Word(Color.GREEN, Language.RUSSIAN, "Зеленый"),

                new Word(Color.RED, Language.PORTUGUESE, "Vermelho"),
                new Word(Color.YELLOW, Language.PORTUGUESE, "Amarelo"),
                new Word(Color.BLUE, Language.PORTUGUESE, "Azul"),
                new Word(Color.GREEN, Language.PORTUGUESE, "Verde"),

               // new Word(Color.RED, Word.Language.SPANISH, "Rojo"),
              //  new Word(Color.YELLOW, Word.Language.SPANISH, "Amarillo"),
              //  new Word(Color.BLUE, Word.Language.SPANISH, "Azul"),
              //  new Word(Color.GREEN, Word.Language.SPANISH, "Verde"),

                new Word(Color.RED, Language.SINHALA, "රතු"),
                new Word(Color.YELLOW, Language.SINHALA, "කහ"),
                new Word(Color.BLUE, Language.SINHALA, "නිල්"),
                new Word(Color.GREEN, Language.SINHALA, "කොළ"),

                new Word(Color.RED, Language.TAGALOG, "Pula"),
                new Word(Color.YELLOW, Language.TAGALOG, "Dilaw"),
                new Word(Color.BLUE, Language.TAGALOG, "Asul"),
                new Word(Color.GREEN, Language.TAGALOG, "Berde"),

                new Word(Color.RED, Language.HINDI, "लाल"),
                new Word(Color.YELLOW, Language.HINDI, "पीला"),
                new Word(Color.BLUE, Language.HINDI, "नीला"),
                new Word(Color.GREEN, Language.HINDI, "हरा"),

                new Word(Color.RED, Language.MANDARIN, "红"),
                new Word(Color.YELLOW, Language.MANDARIN, "黄色"),
                new Word(Color.BLUE, Language.MANDARIN, "蓝色"),
                new Word(Color.GREEN, Language.MANDARIN, "绿色"),

        };
    }
    // Method to get random testing word
    public Word getRandomTestingWord() {
        int n = this.random.nextInt(this.testingWords.length);
        return this.testingWords[n];
    }

    //method to get array of n random testing words
    public Word[] getRandomTestingWords(int n) {
        List<Word> result = new ArrayList<Word>();
        for (int k=0; k<n; ++k) {
            result.add(getRandomTestingWord());
        }
        return (Word[]) result.toArray();
    }

}
