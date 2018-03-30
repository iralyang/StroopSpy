package info.lyanguzov.irina.testapp1;

import java.util.Random;

enum Color {
    RED(1, R.color.colorRed),
    YELLOW(2, R.color.colorYellow),
    BLUE(3, R.color.colorBlue),
    GREEN(4, R.color.colorGreen);

    int index;
    int resource;

    Color(int i, int r) {
        index = i;
        resource = r;
    }

    //array of all my colours
    static Color mapping[] = {
            RED,
            YELLOW,
            BLUE,
            GREEN,
    };
    static Random random = new Random();

    static Color getRandomColor() {
        return mapping[random.nextInt(mapping.length)];
    }
}
