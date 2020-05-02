package generic_calculator.custom_exceptions;

/*
    Custom Unchecked exception
 */
public class DivisionByZeroException extends RuntimeException {

    public DivisionByZeroException(int statusCode, String message){
        super("Error: " + statusCode + " " + message);
    }
}
