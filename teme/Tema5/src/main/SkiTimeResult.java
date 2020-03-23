package main;

import java.util.Objects;

class SkiTimeResult {
    private int minutes;
    private int seconds;

    public SkiTimeResult(int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getTotalTimeInSeconds() {
        return minutes * 60 + seconds;
    }

    //for testing purposes
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkiTimeResult that = (SkiTimeResult) o;
        return minutes == that.minutes &&
                seconds == that.seconds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minutes, seconds);
    }

    @Override
    public String toString() {
        if (seconds < 10) { // ensuring proper format to account for single digit seconds
            return minutes + ":" + "0" + seconds;
        } else
            return minutes + ":" + seconds;
    }
}
