package slotHandlers;

import MVCObserver.mvc.SlideView;

import java.awt.*;

public interface SlotViewHandler {
    public void paint(Graphics2D g, int X, int Y, int width, int height, SlideView slideView);
}
