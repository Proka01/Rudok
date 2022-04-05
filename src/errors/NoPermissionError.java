package errors;

public class NoPermissionError implements Error{
    @Override
    public String errorMessage() {
        return "No permission for this action";
    }
}
