package calc.exception;

/**
 * Created by joseph on 6/5/17.
 */
public class ParseException extends CalculatorException {
    private String message;
    public ParseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
