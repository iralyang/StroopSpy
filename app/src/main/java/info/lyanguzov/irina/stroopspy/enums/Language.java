package info.lyanguzov.irina.stroopspy.enums;

public enum Language {
    KLINGON(1),
    ELVISH(2),
    DOTHRAKI(3),
    ENGLISH(0),
    RUSSIAN(4),
    SPANISH(5),
    PORTUGUESE(6),
    TAGALOG(7),
    HINDI(8),
    SINHALA(9),
    MANDARIN(10),
    MALAY(11);

    int value;
    Language(int v) {
        value = v;
    }
}
