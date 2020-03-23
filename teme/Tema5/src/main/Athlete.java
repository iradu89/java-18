package main;

import java.util.Arrays;
import java.util.Objects;

class Athlete {
    private int number;
    private String name;
    private String countryCode;
    private SkiTimeResult skiTimeResult;
    private int penaltyTime = 0; // in seconds
    private String[] shootingRangeResult;

    public Athlete(int number, String name, String countryCode, SkiTimeResult skiTimeResult, String[] shootingRangeResult) {
        this.number = number;
        this.name = name;
        this.countryCode = countryCode;
        this.skiTimeResult = skiTimeResult;
        this.shootingRangeResult = shootingRangeResult;
    }

    public SkiTimeResult getSkiTimeResult() {
        return skiTimeResult;
    }

    public String[] getShootingRangeResult() {
        return shootingRangeResult;
    }

    public int getPenaltyTime() {
        return penaltyTime;
    }

    public String getName() {
        return name;
    }

    public void setPenaltyTime(int penaltyTime) {
        this.penaltyTime = penaltyTime;
    }

    //for testing purposes
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Athlete athlete = (Athlete) o;
        return number == athlete.number &&
                penaltyTime == athlete.penaltyTime &&
                Objects.equals(name, athlete.name) &&
                Objects.equals(countryCode, athlete.countryCode) &&
                Objects.equals(skiTimeResult, athlete.skiTimeResult) &&
                Arrays.equals(shootingRangeResult, athlete.shootingRangeResult);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(number, name, countryCode, skiTimeResult, penaltyTime);
        result = 31 * result + Arrays.hashCode(shootingRangeResult);
        return result;
    }

    //method for printing time + penalty
    //calculates the time with a penalty given and returns it as a String in the proper format
    public String getTimePlusPenalty() {
        String result = skiTimeResult.toString();
        if (penaltyTime > 0) {
            int minutes = skiTimeResult.getMinutes();
            int seconds = skiTimeResult.getSeconds();
            if ((seconds + penaltyTime) >= 60) {
                minutes += (seconds + penaltyTime) / 60;
                seconds = (seconds + penaltyTime) % 60;
            } else {
                seconds += penaltyTime;
            }
            if (seconds < 10) { // ensuring proper format to account for single digit seconds
                return minutes + ":" + "0" + seconds;
            } else
                return minutes + ":" + seconds;
        } else
            return result;
    }

    @Override
    public String toString() {
        return "No." + number +
                " - " + name +
                " representing: " + countryCode +
                " - with the final result of: " + getTimePlusPenalty() + " (" + getSkiTimeResult() +
                " + " + penaltyTime + " penalty" + ") " +
                " - shooting range results: " + Arrays.toString(shootingRangeResult);
    }
}
