package slotState;

import MVCObserver.mvc.SlideView;
import MVCObserver.mvc.SlotView;
import errors.ErrorFactory;
import errors.ErrorTypes;
import model.workspace.Slide;
import model.workspace.Slot;

public class AddSlotState implements SlotState{
    @Override
    public void handleSlot(Slot slot) {
        Slide slide = slot.getSlide();

        boolean haveIntersection = false;

        int n = slide.getSlotsLits().size();
        for(int i = n-1; i >= 0; i--){
            Slot s = slide.getSlotsLits().get(i);
            if(slot.intersectWith(s)){
                haveIntersection = true;
                break;
            }
        }

        if(!haveIntersection){
            slide.addSlot(slot);
        }
        else
            ErrorFactory.getInstance().generateError(ErrorTypes.INTERSECTING_SLOTS);
    }
}
