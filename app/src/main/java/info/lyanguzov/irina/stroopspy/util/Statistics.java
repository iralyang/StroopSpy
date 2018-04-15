package info.lyanguzov.irina.stroopspy.util;

import android.os.Bundle;

import info.lyanguzov.irina.stroopspy.enums.Color;

public class Statistics {
    public static final String BUNDLE_NAME = "EXTRA_STATISTICS";
    public static final String AVERAGE_TIME = "AVERAGE_TIME";
    public static final String PERCENTAGE_CORRECT = "PERCENTAGE_CORRECT";
    private float averageTime;
    private int count;
    private int correctCount;

    public Statistics() {
        reset();
    }

    public void reset() {
        averageTime = 0;
        count = 0;
        correctCount = 0;
    }

    public void update(long time, Color clickedColor, Color referenceColor, Word word) {
        ++count;
        if (clickedColor == referenceColor) {
            averageTime += (time - averageTime) / ++correctCount;
        }
    }

    private float getCorrectPercentage() {
        return 100.0f * correctCount / count;
    }

    private float getAverageTime() {
        return averageTime;
    }

    public Bundle getResults() {
        Bundle results = new Bundle(2);
        results.putFloat(AVERAGE_TIME, getAverageTime());
        results.putFloat(PERCENTAGE_CORRECT, getCorrectPercentage());
        return results;
    }
}
