package slotState;

import MVCObserver.mvc.PresentationView;
import MVCObserver.mvc.SlideView;
import MVCObserver.mvc.SlotView;
import app.MainFrame;
import model.workspace.Slide;
import model.workspace.Slot;

import java.awt.*;

public class SelectSlotState implements SlotState{
    @Override
    public void handleSlot(Slot slot) {
        Slide slide = slot.getSlide();

        //koordinate tacke na koju je korisnik kliknuo
        int X = slot.getX();
        int Y = slot.getY();

        Point point = new Point(X,Y);

        boolean isAnySlotSelected = false;
        int index = MainFrame.getInstance().getProjectView().getMyTabbedPane().getTabs().getSelectedIndex();
        PresentationView pw = MainFrame.getInstance().getProjectView().getMyTabbedPane().getPresentationsList().get(index);
        Slot selectedSlot = pw.getPresentation().getSelectedSlot();

        int n = slide.getSlotsLits().size();
        for(int i = n-1; i >= 0; i--){
            Slot currSlot = slide.getSlotsLits().get(i);

            if(currSlot.isPointAtSlot(point)){
                if(currSlot.equals(selectedSlot)){
                    currSlot.setSelected(false);
                    isAnySlotSelected = false;
                    pw.getPresentation().setSelectedSlot(null);
                }
                else{
                    if(selectedSlot != null){
                        selectedSlot.setSelected(false);
                        selectedSlot.notifySubscribers(null);
                    }
                    currSlot.setSelected(true);
                    pw.getPresentation().setSelectedSlot(currSlot);
                    isAnySlotSelected = true;
                }
                break;
            }

            /*
            ///////////
            Slot s = slide.getSlotsLits().get(i);

            if(s.isPointAtSlot(point)){
                if(s.equals(pw.getPresentation().getSelectedSlot())){
                    s.setSelected(!s.isSelected());
                    pw.getPresentation().setSelectedSlot(null);
                    isAnySlotSelected = false;
                }
                else{
                    if(pw.getPresentation().getSelectedSlot() != null){
                        pw.getPresentation().getSelectedSlot().setSelected(false);
                        pw.getPresentation().getSelectedSlot().notifySubscribers(new Object());
                    }

                    pw.getPresentation().setSelectedSlot(s);
                    s.setSelected(true);
                    isAnySlotSelected = true;
                }

                break;
            }
            */
        }

        slide.selectSlot(isAnySlotSelected);
    }
}
