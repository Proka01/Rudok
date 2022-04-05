package errors;

public class WrongComponentError implements Error{

    public WrongComponentError() {
    }

    @Override
    public String errorMessage() {
        return "Wrong component selected for this action";
    }
}
