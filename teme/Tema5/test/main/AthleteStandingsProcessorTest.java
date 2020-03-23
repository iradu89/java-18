package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AthleteStandingsProcessorTest {
    List<Athlete> athleteList;
    AthleteStandingsProcessor athleteStandingsProcessor;

    @BeforeEach
    void setup(){
        athleteList = new ArrayList<>();
        athleteStandingsProcessor = new AthleteStandingsProcessor();
    }

    @Test
    void test_sort_athletesList(){
        Athlete jimmy = new Athlete(1,"Jimmy Smiles","UK",new SkiTimeResult(29, 15), new String[]{"xxoox","xooxo","xxxxo"});
        Athlete ragnar = new Athlete(3,"Ragnar Lothbrok","SWE",new SkiTimeResult(29,1), new String[]{"xxxxx","xxxxx","xxxxx"});

        athleteList.add(jimmy);
        athleteList.add(ragnar); //is the winner

        assertNotEquals(athleteList.get(0), ragnar);
        athleteStandingsProcessor.calculateStandings(athleteList); //sort
        assertEquals(athleteList.get(0), ragnar);
    }

    @Test
    void test_penalty_time_for_shooting_range(){
        Athlete jimmy = new Athlete(1,"Jimmy Smiles","UK",new SkiTimeResult(29, 15), new String[]{"xxoox","xooxo","xxxxo"});
        Athlete ragnar = new Athlete(3,"Ragnar Lothbrok","SWE",new SkiTimeResult(29, 1), new String[]{"xxxxx","xxxxx","xxxxx"});

        athleteList.add(jimmy); //60 seconds penalty
        athleteList.add(ragnar); //0 seconds penalty

        athleteStandingsProcessor.calculateStandings(athleteList);

        assertEquals(athleteList.get(0).getPenaltyTime(), 0);
        assertEquals(athleteList.get(1).getPenaltyTime(), 60);
    }
}