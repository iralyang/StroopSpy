package info.lyanguzov.irina.stroopspy.enums;

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
    MALAY("Malay");

    static Language all[] = {
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

    private String text;
    Language(String text) {
        this.text = text;
    }
}
