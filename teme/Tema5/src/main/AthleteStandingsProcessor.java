package main;

import java.util.List;

public class AthleteStandingsProcessor {
    //Calculates the current standings and then sorts the list according to the time + penalty
    public void CalculateStandings(List<Athlete> athleteList) {

        for (Athlete athlete : athleteList) {
            int penaltySeconds = 0;
            for (String shootingRangeResult : athlete.getShootingRangeResult()) {
                for (int i = 0; i < shootingRangeResult.length(); i++) {
                    //increase the penalty by 10 for each 'o' (missed shot) in the shooting range results
                    if (shootingRangeResult.charAt(i) == 'o') {
                        penaltySeconds += 10;
                    }
                }
            }
            athlete.setPenaltyTime(penaltySeconds);
        }

        //sorting list by time + penalty
        athleteList.sort((athlete1, athlete2) -> {
            int firstAthleteTotalSeconds = athlete1.getSkiTimeResult().getTotalTimeInSeconds() + athlete1.getPenaltyTime();
            int secondAthleteTotalSeconds = athlete2.getSkiTimeResult().getTotalTimeInSeconds() + athlete2.getPenaltyTime();
            return firstAthleteTotalSeconds - secondAthleteTotalSeconds;
        });
    }
}
