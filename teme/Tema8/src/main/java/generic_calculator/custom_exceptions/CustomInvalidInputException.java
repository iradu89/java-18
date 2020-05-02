package generic_calculator.custom_exceptions;

/*
    Custom Checked exception
 */
public class CustomInvalidInputException extends Exception {

    public CustomInvalidInputException(int statusCode, String message){
        super("Error: " + statusCode + " " + message);
    }
}
