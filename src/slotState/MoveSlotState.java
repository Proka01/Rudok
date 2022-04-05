package slotState;

import MVCObserver.mvc.SlideView;
import MVCObserver.mvc.SlotView;
import model.workspace.Slide;
import model.workspace.Slot;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MoveSlotState implements SlotState{

    @Override
    public void handleSlot(Slot slot) {
        Slide slide = slot.getSlide();

        int X = slot.getX();
        int Y = slot.getY();
        Point prevPoint = slide.getPrevPoint();
        Point currPoint = new Point(X,Y);
        boolean isAnySlotMooved = false;
        Slot movedSlot = null;

        int n = slide.getSlotsLits().size();
        for(int i = n-1; i >= 0; i--){

            Slot s = slide.getSlotsLits().get(i);

            if(s.isPointAtSlot(prevPoint)) {
                isAnySlotMooved = true;
                movedSlot = s;
                break;
            }
        }

        if(movedSlot != null){
            slide.setPrevPoint(currPoint);
            movedSlot.setX(currPoint.x);
            movedSlot.setY(currPoint.y);
        }

        slide.moveSlot(isAnySlotMooved);
    }

}
