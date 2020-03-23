package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class AthletePrinterAndWriter {

    public void printToFile(List<Athlete> athleteList, String fileName, String dirName) {
        if (athleteList.isEmpty()) {
            System.out.println("Can't print anything if the list given is empty");
            return;
        }
        StringBuilder content = new StringBuilder("Winner: " + athleteList.get(0).toString());

        for (int i = 1; i < athleteList.size(); i++) {
            content.append("\n"); //making a new line for each result starting with the first one

            if (i == 1) { //ensuring proper format for 2nd place
                content.append("Runner-Up: ").append(athleteList.get(i).toString());

            } else if (i == 2) { //ensuring proper format for 3rd place
                content.append("Third Place: ").append(athleteList.get(i).toString());

            } else { //format for all other participants in order
                content.append(i + 1).append(": ").append(athleteList.get(i).toString());
            }
        }

        //setting the path to the new directory
        Path path = Paths.get(System.getProperty("user.dir") + "//" + dirName);
        //if the path directory doesn't exist then create it
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file = new File(path + "\\" + fileName); //creating a new file with the path in the custom directory
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) { //no need to close the bw since the try block takes care of it

            bw.write(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //printing the 3 winners in the console according to the format given
    public void printWinners(List<Athlete> athleteList) {
        if (athleteList.isEmpty()) {
            System.out.println("Can't print the winners if the list is empty");
            return;
        }
        Athlete winner = athleteList.get(0);
        System.out.println("Winner - " + winner.getName()
                + " " + winner.getTimePlusPenalty()
                + " (" + winner.getSkiTimeResult() + " + " + winner.getPenaltyTime() + ")");

        Athlete runnerUp = athleteList.get(1);
        System.out.println("Runner-up - " + runnerUp.getName()
                + " " + runnerUp.getTimePlusPenalty()
                + " (" + runnerUp.getSkiTimeResult() + " + " + runnerUp.getPenaltyTime() + ")");

        Athlete thirdPlace = athleteList.get(2);
        System.out.println("Third Place - " + thirdPlace.getName()
                + " " + thirdPlace.getTimePlusPenalty()
                + " (" + thirdPlace.getSkiTimeResult() + " + " + thirdPlace.getPenaltyTime() + ")");
    }
}
