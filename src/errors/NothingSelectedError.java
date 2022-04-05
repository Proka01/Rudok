package errors;

public class NothingSelectedError implements Error{
    @Override
    public String errorMessage() {
        return "Select a component from tree before action";
    }
}
