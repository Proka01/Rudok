package errors;

public class IntersectingSlotsError implements Error{
    @Override
    public String errorMessage() {
        return "Slots intersection is not allowed. \n" +
                "Try to add new slot to another place on slide.";
    }
}
