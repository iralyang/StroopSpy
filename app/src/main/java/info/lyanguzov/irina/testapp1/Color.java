package info.lyanguzov.irina.testapp1;

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

}
