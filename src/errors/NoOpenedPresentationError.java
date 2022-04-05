package errors;

public class NoOpenedPresentationError implements Error{
    @Override
    public String errorMessage() {
        return "You have to choose and open some presentation\n before slide show";
    }
}
