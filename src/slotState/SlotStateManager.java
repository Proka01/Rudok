package slotState;

public class SlotStateManager{

    private SlotState currentSlotState;

    private AddSlotState addSlotState;
    private RemoveSlotState removeSlotState;
    private MoveSlotState moveSlotState;
    private SelectSlotState selectSlotState;

    public SlotStateManager() {
        initializeStates();
    }

    private void initializeStates(){
        addSlotState = new AddSlotState();
        removeSlotState = new RemoveSlotState();
        moveSlotState = new MoveSlotState();
        selectSlotState = new SelectSlotState();

        currentSlotState = addSlotState;
    }

    public void setAddSlotState() {currentSlotState = addSlotState;}
    public void setRemoveSlotState() {currentSlotState = removeSlotState;}
    public void setMoveSlotState() {currentSlotState = moveSlotState;}
    public void setSelectSlotState() {currentSlotState = selectSlotState;}

    public SlotState getCurrentSlotState() {
        return currentSlotState;
    }
}
