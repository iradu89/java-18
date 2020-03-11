package main;

import java.util.Objects;

public class SkiTimeResult {
    private int minutes;
    private int seconds;

    public SkiTimeResult(String input) { //constructor receives a String and parses the data itself
        String[] minutesAndSeconds = input.split(":");
        this.minutes = Integer.parseInt(minutesAndSeconds[0]);
        this.seconds = Integer.parseInt(minutesAndSeconds[1]);
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
