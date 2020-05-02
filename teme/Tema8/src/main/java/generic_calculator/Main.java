package generic_calculator;

import generic_calculator.custom_exceptions.CustomInvalidInputException;
import generic_calculator.ui.UserInterface;

/**
 1) Create a generic Calculator using the proper placeholder.
 This can be done either directly in calculator but I would recommend using a generic interface alongside an implementation.
 What does this calculator have to do ? Do any operation +, -, *, /, reset, parentheses, %. Any numbers can be accepted.
 Create two such calculators. What do they have in common ? Do we need an interface ?

 2) Create 2 custom exceptions with status code and message. One should be a checked and one should be an unchecked exception.
 Throw them in at least 1 case each and handle them accordingly.
 For the checked exception, one should be managed using try { } catch { } finally { } and one should be thrown above.
 Write at least 1 test for each exception.

 3) Integrate a Logging system, in order to avoid using System.out.println for your flows.
 Use at least one functionality for each: INFO (client friendly details), DEBUG (developer details), ERROR (for exceptions).
 Options: regular logger or Lombok Log4j2.
 */

/*
    The custom exceptions can be found here:
    1) Unchecked in the calculators implementations in the division method.
    2) Checked in the UserInterface
 */

public class Main {
    public static void main(String[] args) throws CustomInvalidInputException {
        UserInterface.start();
    }
}
