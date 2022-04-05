package MVCObserver.mvc;

import MVCObserver.observer.ISubscriber;
import model.workspace.Slide;
import model.workspace.Slot;
import model.workspace.SlotType;
import slotHandlers.MultimediaSlotViewHandler;
import slotHandlers.SlotViewHandler;
import slotHandlers.TextSlotViewHandler;

import java.awt.*;

public class SlotView implements ISubscriber{

    private Slot slot;
    private SlotViewHandler slotViewHandler;
    private SlideView slideView;

    public SlotView(Slot slot, SlideView slideView) {
        this.slot = slot;
        this.slideView = slideView;
        slot.addSubscriber(this);
        slotViewHandler = slot.getSlotType().equals(SlotType.TEXT) ? new TextSlotViewHandler(slot) : new MultimediaSlotViewHandler(slot);
    }

    public void paint(SlideView toBeScalledSlideView, Graphics2D g, SlideViewType slideViewType){
        Slide slide = slot.getSlide();
        SlideView scallerSlideView = (SlideView) slide.getSubscribers().get(0);

        int Wscaled = (toBeScalledSlideView.getWidth() * slot.getWidth())/scallerSlideView.getWidth();
        int Hscalled = (toBeScalledSlideView.getHeight() * slot.getHeight())/scallerSlideView.getHeight();

        int Xscalled = (toBeScalledSlideView.getWidth() * slot.getX())/scallerSlideView.getWidth();
        int Yscalled = (toBeScalledSlideView.getHeight() * slot.getY())/scallerSlideView.getHeight();

        if(slideViewType.equals(SlideViewType.DUMMY_SLIDE_VIEW))
            slotViewHandler.paint(g,Xscalled,Yscalled,Wscaled,Hscalled,toBeScalledSlideView);
        else{
            if(!slot.isSelected())
                g.setPaint(slot.getColor());
            else
                g.setPaint(Color.cyan);

            g.setStroke(slot.getStroke());
            //g.setStroke(new BasicStroke(5));
            g.drawRect(Xscalled,Yscalled,Wscaled,Hscalled);
        }
    }

    /** Getters and setters */
    public Slot getSlot() {
        return slot;
    }

    /** Metode ISubscriber-a */
    @Override
    public void update(Object notification) {
        slideView.repaint();
    }
}
