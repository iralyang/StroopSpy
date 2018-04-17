package info.lyanguzov.irina.stroopspy.util;

import info.lyanguzov.irina.stroopspy.enums.Color;
import info.lyanguzov.irina.stroopspy.enums.Language;

public class Word {
    public Color getMeaning() {
        return meaning;
    }
    public Language getLanguage() { return language; }
    public String getRepresentation() {
        return representation;
    }

    private final Color meaning;
    private final Language language;
    private final String representation;

    public Word(Color meaning, Language language, String representation) {
        this.meaning = meaning;
        this.language = language;
        this.representation = representation;
    }
}
