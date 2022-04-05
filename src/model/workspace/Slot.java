package model.workspace;

import MVCObserver.observer.IPublisher;
import MVCObserver.observer.ISubscriber;
import serialization.SerializableStrokeAdapter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Slot implements IPublisher {

    private int X;
    private int Y;
    private int width = 100;
    private int height = 50;
    private Color color = Color.RED;
    private SerializableStrokeAdapter stroke = new SerializableStrokeAdapter(new BasicStroke(5));
    private Slide slide;
    private boolean isSelected;
    private SlotType slotType;
    private String content = "";
    private transient List<ISubscriber> subscribers = new ArrayList<>();

    public void updatePerformed(){
        slide.updatePerformed();
    }

    public Slot(int x, int y, int width, int height, Color color, Stroke stroke) {
        X = x;
        Y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.stroke = new SerializableStrokeAdapter(stroke);
        //this.stroke = stroke;
        this.isSelected = false;
    }

    public void readResolve(){
        subscribers = new ArrayList<>();
    }

    public Slot(int x, int y, Slide slide) {
        X = x;
        Y = y;
        this.slide = slide;
        this.stroke = new SerializableStrokeAdapter(new BasicStroke());
        //stroke = new BasicStroke();
    }

    public Slot(int x, int y, Color color, Slide slide, SlotType slotType) {
        X = x;
        Y = y;
        this.color = color;
        this.slide = slide;
        this.slotType = slotType;
        this.stroke = new SerializableStrokeAdapter(new BasicStroke());
        //stroke = new BasicStroke();
        //slotHandler = slotType.equals(SlotType.TEXT) ? new TextSlotHandler() : new MultimediaSlotHandler();
    }

    public Slot(Slot s){
        this.X = s.getX();
        this.Y = s.getY();
        this.width = s.getWidth();
        this.height = s.getHeight();
        this.color = s.getColor();
        this.slide = s.getSlide();
        this.isSelected = s.isSelected();
        this.content = s.getContent();
        this.slotType = s.getSlotType();
        Stroke str = s.getStroke();
        this.stroke = new SerializableStrokeAdapter(str);
    }

    /** Moje metode*/

    public boolean intersectWith(Slot s){
        int x1 = this.getX();
        int y1 = this.getY();

        int x2 = s.getX();
        int y2 = s.getY();

        int width = this.getWidth();
        int height = this.getHeight();

        if((Math.abs(x1-x2) <= width) && (Math.abs(y1-y2) <= height))
            return true;

        return false;
    }

    public boolean isPointAtSlot(Point point){
        int X = this.getX();
        int Y = this.getY();
        int w = this.getWidth();
        int h = this.getHeight();

        int x0 = (int) point.getX();
        int y0 = (int) point.getY();

        boolean insideSlot = false;

        if((x0 >= X && x0 <= X + w) && (y0 >= Y && y0 <= Y + h))
            insideSlot = true;

        return insideSlot;
    }

    /** Getters and setters */
    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        updatePerformed();
    }

    public Stroke getStroke() {
        return stroke.getStroke();
    }

    public void setStroke(Stroke stroke) {
        this.stroke = new SerializableStrokeAdapter(stroke);
        //this.stroke = stroke;
        //updatePerformed(); //TODO: mozda treba i ovde update
    }

    public Slide getSlide() {
        return slide;
    }

    public void setSlide(Slide slide) {
        this.slide = slide;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public void setSlotType(SlotType slotType) {
        this.slotType = slotType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        updatePerformed();
    }

    /** Metode IPublisher-a*/
    @Override
    public void addSubscriber(ISubscriber sub) {
        subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        for(ISubscriber sub : subscribers)
            sub.update(notification);
    }

    @Override
    public void removeAllSubscribers() {

    }
}
