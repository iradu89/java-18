package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AthleteCSVReaderTest {
    List<Athlete> athleteList;
    AthleteCSVReader athleteCSVReader;
    Path testFolderPath = Paths.get("test/main/resources/");
    ByteArrayOutputStream outContent; // used for getting the messages from the console

    @BeforeEach
    void setup() {
        athleteList = new ArrayList<>();
        athleteCSVReader = new AthleteCSVReader();
        outContent = new ByteArrayOutputStream();
    }

    @Test
    void test_read_from_file_with_correct_input() {
        Athlete jimmy = new Athlete(1, "Jimmy Smiles", "UK", new SkiTimeResult(29, 15), new String[]{"xxoox", "xooxo", "xxxxo"});
        Athlete umar = new Athlete(11, "Umar Jorgson", "SK", new SkiTimeResult(30, 27), new String[]{"xxxox", "xxxxx", "xxoxo"});

        athleteList = athleteCSVReader.readAthletes("src/main/resources/ski_biathlon_results.csv"); //file with proper input values

        assertEquals(athleteList.get(0), umar);
        assertEquals(athleteList.get(1), jimmy);
    }

    @Test
    void test_read_from_file_string_instead_of_int() {
        System.setOut(new PrintStream(outContent));
        athleteList = athleteCSVReader.readAthletes(testFolderPath + "/" + "string_instead_of_int.csv");
        assertEquals("Athlete number must contain only natural numbers\n", outContent.toString());
    }

    @Test
    void test_read_from_file_double_instead_of_int() {
        System.setOut(new PrintStream(outContent));
        athleteList = athleteCSVReader.readAthletes(testFolderPath + "/" + "double_instead_of_int.csv");
        assertEquals("Athlete number must contain only natural numbers\n", outContent.toString());
    }

    @Test
    void test_read_from_file_not_enough_fields() {
        System.setOut(new PrintStream(outContent));
        athleteList = athleteCSVReader.readAthletes(testFolderPath + "/" + "only_two_fields.csv");
        assertEquals("Improper file format\n", outContent.toString());
    }

    @Test
    void test_string_name_contains_only_letters_and_spaces() {
        System.setOut(new PrintStream(outContent));
        athleteList = athleteCSVReader.readAthletes(testFolderPath + "/" + "name_field_with_different_chars.csv");
        assertEquals("Athlete name should contain only alphabet letters\n", outContent.toString());
    }

    @Test
    void test_string_countryCode_contains_only_capital_letters() {
        System.setOut(new PrintStream(outContent));
        athleteList = athleteCSVReader.readAthletes(testFolderPath + "/" + "countryCode_field_with_different_chars.csv");
        assertEquals("Country code must contain max 3 CAPITAL letters\n", outContent.toString());
    }

    @Test
    void test_string_countryCode_contains_max_three_capital_letters() {
        System.setOut(new PrintStream(outContent));
        athleteList = athleteCSVReader.readAthletes(testFolderPath + "/" + "countryCode_field_with_more_than_3_capital_letters.csv");
        assertEquals("Country code must contain max 3 CAPITAL letters\n", outContent.toString());
    }

    @Test
    void test_skiTimeResult_contains_only_ints() {
        System.setOut(new PrintStream(outContent));
        athleteList = athleteCSVReader.readAthletes(testFolderPath + "/" + "skiTimeResult_field_with_different_chars.csv");
        assertEquals("The ski time result format must be \"dd:dd\" where d can only be a digit\n", outContent.toString());
    }

    @Test
    void test_shootingRangeResult_contains_only_x_or_o() {
        System.setOut(new PrintStream(outContent));
        athleteList = athleteCSVReader.readAthletes(testFolderPath + "/" + "shootingRangeResult_field_with_different_chars.csv");
        assertEquals("The shooting range results must be at 5 letters long and only contain x or o lower case\n", outContent.toString());
    }

}