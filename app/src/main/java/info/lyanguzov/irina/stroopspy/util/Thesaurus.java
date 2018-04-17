package info.lyanguzov.irina.stroopspy.util;

import java.util.Random;

import info.lyanguzov.irina.stroopspy.enums.*;

public class Thesaurus {
    private Word words[];
    private Random random;
    private static Word referenceWords[] = new Word[]{
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
    private static Word mainWords[] = new Word[]{
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
            // new Word(Color.YELLOW, Word.Language.SPANISH, "Amarillo"),
            // new Word(Color.BLUE, Word.Language.SPANISH, "Azul"),
            // new Word(Color.GREEN, Word.Language.SPANISH, "Verde"),

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

            //new Word(Color.RED, Language.MANDARIN, "红"),
            //new Word(Color.YELLOW, Language.MANDARIN, "黄色"),
            //new Word(Color.BLUE, Language.MANDARIN, "蓝色"),
            //new Word(Color.GREEN, Language.MANDARIN, "绿色"),

            //new Word(Color.RED, Language.MALAY, "Merah"),
            //new Word(Color.YELLOW, Language.MALAY, "Kuning"),
            //new Word(Color.BLUE, Language.MALAY, "Biru"),
            //new Word(Color.GREEN, Language.MALAY, "Hijau"),

            //new Word(Color.RED, Language.HEBREW, "אָדוֹם"),
            //new Word(Color.YELLOW, Language.HEBREW, "צהוב"),
            //new Word(Color.BLUE, Language.HEBREW, "כָּחוֹל"),
            //new Word(Color.GREEN, Language.HEBREW, "ירוק"),

            //new Word(Color.RED, Language.ARABIC, "أحمر"),
            //new Word(Color.YELLOW, Language.ARABIC, "الأصفر"),
            //new Word(Color.BLUE, Language.ARABIC, "أزرق"),
            //new Word(Color.GREEN, Language.ARABIC, "أخضر"),

            new Word(Color.RED, Language.FRENCH, "Rouge"),
            new Word(Color.YELLOW, Language.FRENCH, "Jaune"),
            new Word(Color.BLUE, Language.FRENCH, "Bleu"),
            new Word(Color.GREEN, Language.FRENCH, "Vert"),

            //new Word(Color.RED, Language.GERMAN, "Rot"),
            //new Word(Color.YELLOW, Language.GERMAN, "Gelb"),
            //new Word(Color.BLUE, Language.GERMAN, "Blau"),
            //new Word(Color.GREEN, Language.GERMAN, "Grün"),

            //new Word(Color.RED, Language.FARSI, "سرخ"),
            //new Word(Color.YELLOW, Language.FARSI, "رنگ زرد"),
            //new Word(Color.BLUE, Language.FARSI, "آبی"),
            //new Word(Color.GREEN, Language.FARSI, "سبز"),
    };

    private Thesaurus(Word[] words) {
        this.words = words;
        this.random = new Random();
    }

    public static Thesaurus createReferenceThesaurus() {
        return new Thesaurus(referenceWords);
    }

    public static Thesaurus createMainThesaurus() {
        return new Thesaurus(mainWords);
    }

    // Method to get random word
    public Word getRandomWord() {
        int n = this.random.nextInt(this.words.length);
        return this.words[n];
    }
}
