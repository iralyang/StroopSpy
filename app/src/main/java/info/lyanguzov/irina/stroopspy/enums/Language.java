package info.lyanguzov.irina.stroopspy.enums;

@SuppressWarnings("SpellCheckingInspection")
public enum Language {
    KLINGON("Klingon"),
    ELVISH("Elvish"),
    DOTHRAKI("Dothraki"),
    ENGLISH("English"),
    RUSSIAN("Russian"),
    SPANISH("Spanish"),
    PORTUGUESE("Portuguese"),
    TAGALOG("Tagalog"),
    HINDI("Hindi"),
    SINHALA("Sinhala"),
    MANDARIN("Mandarin"),
    MALAY("Malay"),
    GERMAN("German"),
    FRENCH("French"),
    HEBREW("Hebrew"),
    ARABIC("Arabic"),
    FARSI("Farsi");

    static final Language[] all = {
      KLINGON,
      ELVISH,
      DOTHRAKI,
      ENGLISH,
      RUSSIAN,
      SPANISH,
      PORTUGUESE,
      TAGALOG,
      HINDI,
      SINHALA,
      MANDARIN,
      MALAY,
      GERMAN,
      FRENCH,
      HEBREW,
      ARABIC,
      FARSI,
    };

    public String getText() {
        return text;
    }

    public static Language[] getAll() {
        return all;
    }

    public static int getCount() {
        return all.length;
    }

    private final String text;
    Language(String text) {
        this.text = text;
    }
}
