package calc.exception;

/**
 * Created by joseph on 6/5/17.
 */
public class ComputeException extends CalculatorException {
    private String message;
    public ComputeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
