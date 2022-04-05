package model.workspace;

import MVCObserver.observer.DummyNotification;
import MVCObserver.observer.ISubscriber;
import MVCObserver.observer.NotificationStatus;
import gui.StrokeType;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Slide extends RuNode implements Serializable {

    private int slideNumber;
    private transient List<ISubscriber> subscribers = new ArrayList<>();
    private List<Slot> slotsLits = new ArrayList<>();
    private transient Point prevPoint;

    public Slide() {
    }

    public void updatePerformed(){
        ((Presentation) this.getParent()).updatePerformed();
    }

    public void readResolve(){
        subscribers = new ArrayList<>();
    }

    @Override
    public void updateName(String name) {
        updatePerformed();
        setName(name);
        //this.notifySubscribers(new Object());
    }

    public Slide(RuNode parent, String name,int slideNumber) {
        super(parent, name);
        this.slideNumber = slideNumber;
    }

    /** Metode */

    public void addSlot(Slot slot){
        updatePerformed();
        this.slotsLits.add(slot);
        DummyNotification dn = new DummyNotification(slot,NotificationStatus.SLOT_ADDED);
        this.notifySubscribers(dn);
    }

    public void removeSlot(Slot slot){
        updatePerformed();
        this.slotsLits.remove(slot);
        DummyNotification dn = new DummyNotification(slot,NotificationStatus.SLOT_REMOVED);
        this.notifySubscribers(dn);
    }

    public void removeSlotCollection(List<Slot> slotsLits){
        this.slotsLits.removeAll(slotsLits);
        DummyNotification dn = new DummyNotification(null,null, NotificationStatus.SLOT_REMOVED);
        dn.setSlide(this);
        this.notifySubscribers(dn);
    }

    public void moveSlot(boolean isAnySlotMooved){
        updatePerformed();
        if(isAnySlotMooved){
            DummyNotification dn = new DummyNotification(NotificationStatus.SLOT_MOVED);
            this.notifySubscribers(dn);
        }
    }

    public void selectSlot(boolean isAnySlotSelected){
        DummyNotification dn;
        if(isAnySlotSelected)
            dn = new DummyNotification(NotificationStatus.SLOT_SELECTED);
        else
            dn = new DummyNotification(NotificationStatus.SLOTS_DESELECTED);

        this.notifySubscribers(dn);
    }

    /** Metode IPublisher-a */
    @Override
    public void addSubscriber(ISubscriber sub) {
        subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {

    }

    @Override
    public void notifySubscribers(Object notification) {
        for(ISubscriber sub : subscribers){
            sub.update(notification);
        }
    }

    @Override
    public void removeAllSubscribers() {

    }

    /** Getters and setters*/

    public int getSlideNumber() {
        return slideNumber;
    }

    public void setSlideNumber(int slideNumber) {
        this.slideNumber = slideNumber;
    }

    public List<ISubscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<ISubscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public List<Slot> getSlotsLits() {
        return slotsLits;
    }

    public Point getPrevPoint() {
        return prevPoint;
    }

    public void setPrevPoint(Point prevPoint) {
        this.prevPoint = prevPoint;
    }
}
