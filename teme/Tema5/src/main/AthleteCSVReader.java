package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AthleteCSVReader {
    //reads the athletes from a csv and puts them into a list
    List<Athlete> readAthletes(String fileName) {
        List<Athlete> athletes = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            String line = bufferedReader.readLine();

            while (line != null) {
                String[] fields = line.split(",");

                //if fields are not valid break out of the loop and print specific error message
                if (!isValid(fields)) {
                    break;
                }

                Athlete athlete = new Athlete(
                        Integer.parseInt(fields[0]), // athlete number
                        fields[1], // athlete name
                        fields[2], // country code
                        new SkiTimeResult(fields[3]), // ski time result
                        new String[]{fields[4], fields[5], fields[6]} // array of shootingRange results
                );

                athletes.add(athlete);

                line = bufferedReader.readLine(); //reading the next line
            }

            bufferedReader.close(); //closing the buffered reader

        } catch (IOException e) {
            e.printStackTrace();
        }

        return athletes;
    }

    //Input validation
    private boolean isValid(String[] fields) {
        if (fields.length != 7) {
            System.out.print("Improper file format\n");
            return false;
        }

        String number = fields[0];
        String name = fields[1];
        String countryCode = fields[2];
        String skiTimeResult = fields[3];
        String[] shootingRangeResults = new String[]{fields[4], fields[5], fields[6]};

        if (!number.matches("[0-9]+")) {
            System.out.print("Athlete number must contain only natural numbers\n");
            return false;
        }

        if (!name.matches("[ a-zA-Z]+")) {
            System.out.print("Athlete name should contain only alphabet letters\n");
            return false;
        }

        if (!countryCode.matches("[A-Z]+") || countryCode.length() > 3) {
            System.out.print("Country code must contain max 3 CAPITAL letters\n");
            return false;
        }

        if (!skiTimeResult.contains(":")) {
            System.out.print("The ski time result format must be \"dd:dd\" where d can only be a digit\n");
            return false;
        }

        String[] minutesAndSeconds = skiTimeResult.split(":");

        if (!minutesAndSeconds[0].matches("[0-9]+") || !minutesAndSeconds[1].matches("[0-9]+")) {
            System.out.print("The ski time result format must be \"dd:dd\" where d can only be a digit\n");
            return false;
        }

        //go through each letter in each shootingRange result and check if it is either x or o
        //if it is increment correctLength by 1
        //if all letters are x or o then length should be 15
        int correctLength = 0;
        for (String result : shootingRangeResults) {
            for (int i = 0; i < result.length(); i++) {
                if (result.charAt(i) == 'o' || result.charAt(i) == 'x') {
                    correctLength++;
                    continue;
                }
                correctLength--; //if character is not o or x then -1 the correct length
            }
        }
        if (correctLength != 15) {
            System.out.print("The shooting range results must be at 5 letters long and only contain x or o lower case\n");
            return false;
        }
        return true;
    }
}
