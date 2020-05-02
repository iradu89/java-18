package generic_calculator;
import generic_calculator.calculator_logic.Calculator;
import generic_calculator.calculator_logic.IntegerCalculator;
import generic_calculator.custom_exceptions.DivisionByZeroException;
import generic_calculator.custom_exceptions.CustomInvalidInputException;
import generic_calculator.ui.UserInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class CalculatorTestExceptions {
    Calculator<Integer> integerCalculator;

    @BeforeEach
    void setup() {
        integerCalculator = new IntegerCalculator();
    }

    //If the test doesn't catch the exception then it fails.
    @Test
    void test_DivisionByZeroException_try_to_divide_by_0() {
        try {
            integerCalculator.calculate(new ArrayList<>(Arrays.asList("2", "/", "0")));
            fail();
        } catch (DivisionByZeroException e) {
            assertThat(e.getMessage(), is("Error: 1002 Cannot divide by zero"));
        }
    }

    //If it catches the DivisionByZeroException then it fails.
    @Test
    void test_if_catching_DivisionByZeroException_with_correct_input() {
        try {
            integerCalculator.calculate(new ArrayList<>(Arrays.asList("4", "/", "2")));
        } catch (DivisionByZeroException e) {
            fail();
        }
    }

    /*
        Test that tries to calculate with incorrect input, error is logged but the user doesn't see that
        The test should fail if this checked exception is caught here because it is handled in UserInterface
     */
    @Test
    void test_CustomInvalidInputException_incorrect_parentheses() {
        String simulatedInput = "Integer\n" + //initialises the Integer calculator
                "( ( 2 + 2 )\n" + // tries to do this equation with an opened parenthesis. Error logged and user continues
                "( 2 + 2 )\n" + // tries another time with the correct input
                "N\n" + // when asked if he wants to keep the result he answers No
                "exit\n"; // then he exits
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            UserInterface.start();
        } catch (CustomInvalidInputException e) {
            //if it manages to catch the exception then it has failed because it is already handled
            fail();
        }
    }

    /*
        This is a checked exception but it is thrown all the way up in main (as per problem specs)
        So it is tested the same way a regular runtime exception would be
     */
    @Test
    void test_CustomInvalidInputException_input_containing_letters() {
        String simulatedInput = "Integer\n" + //initialises the Integer calculator
                "( A + 2 + 2 )\n" + // incorrect input with a letter that shouldn't be there
                "N\n" + // when asked if he wants to keep the result he answers No
                "exit\n"; // then he exits
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            UserInterface.start();
        } catch (CustomInvalidInputException e) {
            assertThat(e.getMessage(), is("Error: 1003 No letters allowed in input"));
        }
    }
}