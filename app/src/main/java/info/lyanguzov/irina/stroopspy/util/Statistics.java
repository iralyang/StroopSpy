package info.lyanguzov.irina.stroopspy.util;

public class Statistics {
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

    public void addTime(long time, boolean correct) {
        ++count;
        if (correct) {
            averageTime += (time - averageTime) / ++correctCount;
        }
    }

    public float getCorrectPercentage() {
        return 100.0f * correctCount / count;
    }

    public float getAverageTime() {
        return averageTime;
    }
}
