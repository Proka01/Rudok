package errors;

public class EmptyAuthorNameError implements Error{
    @Override
    public String errorMessage() {
        return "Author name can't be empty";
    }
}
