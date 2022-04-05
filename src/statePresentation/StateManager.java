package statePresentation;

public class StateManager {

    private State currentState;
    private EditPresentationState editPresentationState;
    private SlideShowState slideShowState;

    public StateManager() {initializeStates();}

    private void initializeStates(){
        editPresentationState = new EditPresentationState();
        slideShowState = new SlideShowState();
        currentState = editPresentationState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setEditPresentationState(){currentState = editPresentationState;}
    public void setSlideShowState(){currentState = slideShowState;}
}
