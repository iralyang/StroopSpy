package info.lyanguzov.irina.stroopspy.util;

import info.lyanguzov.irina.stroopspy.enums.Color;
import info.lyanguzov.irina.stroopspy.enums.Language;

public class Word {

    public Color getMeaning() {
        return meaning;
    }

    private Color meaning;
    private Language language;
    private String representation;

    Word(Color meaning, Language language, String representation) {
        this.meaning = meaning;
        this.language = language;
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}
