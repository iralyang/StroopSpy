package info.lyanguzov.irina.stroopspy.util;

import java.util.Random;

import info.lyanguzov.irina.stroopspy.enums.Color;
import info.lyanguzov.irina.stroopspy.enums.Language;

public class MainThesaurus {
    private Word words[];
    private Random random;

    // Constructor
    public MainThesaurus() {
        this.random = new Random();
        this.words = new Word[]{
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

                new Word(Color.RED, Language.MALAY, "Merah"),
                new Word(Color.YELLOW, Language.MALAY, "Kuning"),
                new Word(Color.BLUE, Language.MALAY, "Biru"),
                new Word(Color.GREEN, Language.MALAY, "Hijau"),
        };
    }

    // Method to get random word
    public Word getRandomWord() {
        int n = this.random.nextInt(this.words.length);
        return this.words[n];
    }
}
