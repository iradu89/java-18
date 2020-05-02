package generic_calculator.ui;
import generic_calculator.calculator_logic.Calculator;
import generic_calculator.calculator_logic.DoubleCalculator;
import generic_calculator.calculator_logic.IntegerCalculator;
import generic_calculator.custom_exceptions.CustomInvalidInputException;
import lombok.extern.log4j.Log4j2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Log4j2
public class UserInterface {
    private static Scanner scanner = new Scanner(System.in);

    public static void start() throws CustomInvalidInputException {
        System.out.println("Welcome to the overly-complicated simple Calculator");
        System.out.println("--------------Instructions--------------");
        System.out.println("To exit the calculator just type \"exit\"");
        System.out.println("To reset the current result type \"reset\"");
        System.out.println("First choose between Integer and Double for your calculator by typing \"Integer\" or \"Double\"");
        String input = scanner.nextLine();
        if (input.equals("Integer")) {
            log.info("Integer calculator initialized");
            initializePrompt(new IntegerCalculator());
        } else if (input.equals("Double")) {
            log.info("Double calculator initialized");
            initializePrompt(new DoubleCalculator());
        }
    }

    /*
        Generic method to work for both calculators
        It does NOT handle all types of input and mostly expects the user to write neatly space separated input
     */
    private static <T extends Number> void initializePrompt(Calculator<T> calculator) throws CustomInvalidInputException {
        T result;
        while (true) {
            System.out.println("Please write the equation");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }

            /*
                Checked exception which is thrown
                all the way up in main where it is thrown again (as per problem spec)
             */
            if (input.matches(".*[a-zA-Z].*")) {
                throw new CustomInvalidInputException(1003, "No letters allowed in input");
            }

            List<String> inputList = new ArrayList<>(Arrays.asList(input.split(" ")));

            /*
                Checked and handled exception
                If the user wrote too many parentheses or too few it will log the error and continue
            */
            try {
                validateParentheses(inputList);
            } catch (CustomInvalidInputException e) {
                log.error(e.getMessage());
                continue;
            }

            result = calculator.calculate(inputList);
            System.out.println(result);

            // Modifies the current result with another equation of the users choice
            System.out.println("Do you wish to keep the result? Y/N");
            input = scanner.nextLine();
            if (input.equals("Y") || input.equals("y")) {
                while (true) {
                    System.out.println("Please start the new equation with an operator instead of a digit or parentheses");
                    input = scanner.nextLine();
                    if (input.equals("reset")) {
                        System.out.println("Reset complete , You can type any equation starting with a digit or parentheses");
                        break;
                    }
                    inputList = new ArrayList<>(Arrays.asList(input.split(" ")));
                    //adds the previous result to the input list as the first element
                    inputList.add(0, String.valueOf(result));
                    result = calculator.calculate(inputList);
                    System.out.println(result);
                }
            }
        }
    }

    /*
        Checks if the user left any parenthesis unclosed and throws exception which is caught in initializePrompt
     */
    private static void validateParentheses(List<String> list) throws CustomInvalidInputException {
        long openParentheses = list.stream().filter(elem -> elem.equals("(")).count();
        long closedParentheses = list.stream().filter(elem -> elem.equals(")")).count();
        log.debug("Number of open parenthesis is : " + openParentheses
                + " Number of closed parenthesis is : " + closedParentheses);
        if (openParentheses != closedParentheses) {
            throw new CustomInvalidInputException(1001, "Parentheses were left opened/closed");
        }
    }
}
