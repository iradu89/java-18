package generic_calculator.calculator_logic;
import generic_calculator.custom_exceptions.DivisionByZeroException;
import lombok.extern.log4j.Log4j2;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Log4j2
public class DoubleCalculator implements Calculator<Double> {
    public Double add(Double first, Double second) {
        return first + second;
    }

    public Double subtract(Double first, Double second) {
        return first - second;
    }

    public Double multiply(Double first, Double second) {
        return first * second;
    }

    public Double divide(Double first, Double second) {
        if (second != 0) {
            return first / second;
            //throws this custom runtime exception if we try to divide by zero
        } else {
            log.error("Error 1002: User tried to divide by 0");
            throw new DivisionByZeroException(1002, "Cannot divide by zero");
        }
    }

    public Double modulo(Double first, Double second) {
        return first % second;
    }

    public Double calculate(List<String> inputList) {
        firstOrderOfOperations(inputList);
        return secondAndThirdOrderOfOperations(inputList);
    }

    /*
        Solves what's inside the parentheses first
     */
    private void firstOrderOfOperations(List<String> list) {
        int startingIndex = 0; //needed to identify where the parentheses starts
        int endIndex; // needed to identify where the parentheses ends
        //the following two ints are needed to make sure we don't close the parentheses too soon in case of nested parentheses
        int openParentheses = 0;
        int closedParentheses = 0;
        while (list.contains("(")) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals("(")) {
                    //ensures it will only get the index of the first openParentheses
                    if (openParentheses < 1) {
                        startingIndex = i;
                    }
                    openParentheses++;
                }
                if (list.get(i).equals(")")) {
                    //ensures it will only get the corresponding closedParentheses
                    closedParentheses++;
                    if (closedParentheses == openParentheses) {
                        endIndex = i;
                        resolveParentheses(list, startingIndex, endIndex);
                        //set them back to 0 since the section they were in is no longer in the list
                        openParentheses = 0;
                        closedParentheses = 0;
                        break;
                    }
                }
            }
        }
    }

    //a helper method combining the second and third order of operations
    private Double secondAndThirdOrderOfOperations(List<String> list) {
        secondOrderOfOperations(list);
        return thirdOrderOfOperations(list);
    }

    /*
        Refactored method for resolving the equation inside parentheses
        If it contains another parenthesis inside this one then it will call firstOrderOfOperations again
        which calculates what's inside the parentheses first

        It is part of the firstOrderOfOperations and is recursive, but the logic is easier to see if separated
     */
    private void resolveParentheses(List<String> list, int startingIndex, int endIndex) {
        //create a new list between those indexes to be able to modify it
        List<String> parenthesesList = new ArrayList<>(list.subList(startingIndex + 1, endIndex));
        //if there is a parenthesis inside this list then resolve it first
        if (parenthesesList.contains(")")) {
            //does it really contain another parenthesis?
            log.debug("Check if it really contains another parenthesis " + parenthesesList);
            firstOrderOfOperations(parenthesesList);
        }
        //calculate the result inside the parentheses
        Double result = secondAndThirdOrderOfOperations(parenthesesList);
        //clear the original list of that section which we solved
        list.subList(startingIndex, endIndex + 1).clear();
        //add the result right where the parenthesis began
        list.add(startingIndex, String.valueOf(result));
    }

    /*
        Performs the second order of operations and modifies the list while doing it
        Also % (modulo) has the same priority as * / because that's how it is treated in most programming languages
     */
    private void secondOrderOfOperations(List<String> list) {
        ListIterator<String> inputIterator = list.listIterator();
        while (inputIterator.hasNext()) {
            String element = inputIterator.next();
            switch (element) {
                case "*": {
                    Double[] values = getValuesAndModifyList(inputIterator);
                    inputIterator.set(String.valueOf(this.multiply(values[0], values[1])));
                    break;
                }
                case "/": {
                    Double[] values = getValuesAndModifyList(inputIterator);
                    inputIterator.set(String.valueOf(this.divide(values[0], values[1])));
                    break;
                }
                case "%": {
                    Double[] values = getValuesAndModifyList(inputIterator);
                    inputIterator.set(String.valueOf(this.modulo(values[0], values[1])));
                    break;
                }
            }
        }
        //how the list looks after the second order of operations
        log.debug("After secondOrderOperations: " + list);
    }

    /*
        Goes through the list and performs + - operations and sets the result in the 2nd operand position
        There is no reason to modify the list so the result just moves until it reaches the end of the list
     */
    private Double thirdOrderOfOperations(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i)) {
                case "+":
                    list.set(i + 1, String.valueOf(this.add(Double.valueOf(list.get(i - 1)), Double.valueOf(list.get(i + 1)))));
                    break;
                case "-":
                    list.set(i + 1, String.valueOf(this.subtract(Double.valueOf(list.get(i - 1)), Double.valueOf(list.get(i + 1)))));
                    break;
            }
        }
        //how the list looks after third order operations
        log.debug("After thirdOrderOperations: " + list);
        return Double.parseDouble(list.get(list.size() - 1));
    }

    /*
        Helper method that makes changes to the list using the iterator
        It moves back 1 position, saves the value then removes the value from that position
        Then it moves forward and does the same
    */
    private Double[] getValuesAndModifyList(ListIterator<String> inputIterator) {
        Double[] result = new Double[2];
        inputIterator.previous();
        result[0] = Double.parseDouble(inputIterator.previous());
        inputIterator.remove();
        inputIterator.next();
        inputIterator.remove();
        result[1] = Double.parseDouble(inputIterator.next());
        return result;
    }
}
