package slotState;

import MVCObserver.mvc.SlideView;
import MVCObserver.mvc.SlotView;
import com.sun.jdi.event.StepEvent;
import model.workspace.Slide;
import model.workspace.Slot;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RemoveSlotState implements SlotState{
    @Override
    public void handleSlot(Slot slot) {
        Slide slide = slot.getSlide();

        //koordinate tacke na koju je korisnik kliknuo
        int X = slot.getX();
        int Y = slot.getY();

        Point point = new Point(X,Y);
        Slot toBeRemovedSlot = null;

        int n = slide.getSlotsLits().size();
        for(int i = n - 1; i >= 0; i--){
            Slot s = slide.getSlotsLits().get(i);
            if(s.isPointAtSlot(point)){
                toBeRemovedSlot = s;
                break;
            }
        }

        if(toBeRemovedSlot != null)
            slide.removeSlot(toBeRemovedSlot);
    }
}
