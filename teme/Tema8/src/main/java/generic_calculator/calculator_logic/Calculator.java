package generic_calculator.calculator_logic;
import java.util.List;

public interface Calculator<T extends Number> {
    T add(T first, T second);

    T subtract(T first, T second);

    T multiply(T first, T second);

    T divide(T first, T second);

    T modulo(T first, T second);

    //Takes a list as param and calculates the result respecting the right order of operations
    T calculate(List<String> list);
}
