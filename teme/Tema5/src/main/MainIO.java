package main;

import java.util.List;

/**
 * Ski Biathlon Standings
 * <p>
 * Write a software that takes as an input a CSV file where every entry represents the results of a biathlon athlete.
 * <p>
 * Based on the entries identify the first 3 places (Winner, Runner-up and Third Place).
 * <p>
 * <p>
 * <p>
 * The rules are simple, each athlete has a time-result for the entire skiing session, and 3 shooting range results.
 * <p>
 * Each shooting range has 5 shot results. For every missed shot the athlete obtains a 10 second penalty which affects the final time-result.
 * <p>
 * Final standings are based on the time-results that have been updated with the shooting range results.
 * <p>
 * <p>
 * <p>
 * CSV example:
 * <p>
 * 11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo
 * <p>
 * 1,Jimmy Smiles,UK,29:15,xxoox,xooxo,xxxxo
 * <p>
 * 27,Piotr Smitzer,CZ,30:10,xxxxx,xxxxx,xxxxx
 * <p>
 * <p>
 * <p>
 * Where the columns are:
 * <p>
 * AthleteNumber,AthleteName,CountryCode,SkiTimeResult(Minutes:Seconds),FirstShootingRange.SecondShootingRange,ThirShootingRange
 * <p>
 * Every shooting range consists of 5 charactes, where "x" means hit, "o" mean miss. For every "o" there should be 10 seconds added to the final time.
 * <p>
 * Based on the above data the actual results are the following:
 * <p>
 * Winner - Piotr Smitzer 30:10 (30:10 + 0)
 * <p>
 * Runner-up - Jimmy Smiles 30:15 (29:15 + 60)
 * <p>
 * Third Place - Umar Jorgson 30:57 (30:27 + 30)
 * <p>
 * <p>
 * <p>
 * Task:
 * <p>
 * Write tests for the CSV parsing and the standing calculation
 * In your tests you must not use real files. Make sure the CSVs are read from memory to keep the tests fast.
 * Use Comparator/Comaparable for sorting
 * A little extra (optional):
 * - When printing the winners, try creating a new directory in which you add a new file with some winner details
 */
public class MainIO {

    public static void main(String[] args) {
        List<Athlete> athleteList = readResultsFromCSV();
        calculateStandings(athleteList);
        printWinners(athleteList);
        printToFile(athleteList, "FinalResults.txt", "WinnersDirectory");
    }

    private static List<Athlete> readResultsFromCSV() {
        String fileName = "src/main/resources/ski_biathlon_results.csv"; //custom path to better organize folder
        AthleteCSVReader athleteCSVReader = new AthleteCSVReader();
        return athleteCSVReader.readAthletes(fileName);
    }

    private static void calculateStandings(List<Athlete> athleteList) {
        AthleteStandingsProcessor calculator = new AthleteStandingsProcessor();
        calculator.calculateStandings(athleteList);
    }

    private static void printWinners(List<Athlete> athleteList) {
        AthletePrinterAndWriter athletePrinterAndWriter = new AthletePrinterAndWriter();
        athletePrinterAndWriter.printWinners(athleteList);
    }

    private static void printToFile(List<Athlete> athleteList, String fileName, String dirName){
        AthletePrinterAndWriter athletePrinterAndWriter = new AthletePrinterAndWriter();
        athletePrinterAndWriter.printToFile(athleteList, fileName, dirName);
    }

}
