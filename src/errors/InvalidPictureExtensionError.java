package errors;

public class InvalidPictureExtensionError implements Error {
    @Override
    public String errorMessage() {
        return "Choosen file must be a picture";
    }
}
