package info.lyanguzov.irina.stroopspy.enums;

import java.util.Random;

import info.lyanguzov.irina.stroopspy.R;

public enum Color {
    RED(R.color.colorRed, R.string.colour_red),
    YELLOW(R.color.colorYellow, R.string.colour_yellow),
    BLUE(R.color.colorBlue, R.string.colour_blue),
    GREEN(R.color.colorGreen, R.string.colour_green);

    final int resource;
    final int textResource;

    Color(int r, int t) {
        resource = r;
        textResource = t;
    }

    //array of all my colours
    static final Color[] mapping = {
            RED,
            YELLOW,
            BLUE,
            GREEN,
    };
    static final Random random = new Random();

    public static Color getRandomColor() {
        return mapping[random.nextInt(mapping.length)];
    }

    public int getResource() {
        return resource;
    }

    public int getTextResource() { return textResource; }
}
