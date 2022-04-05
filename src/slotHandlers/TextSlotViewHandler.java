package slotHandlers;

import MVCObserver.mvc.SlideView;
import model.workspace.Slot;

import javax.swing.*;
import java.awt.*;

public class TextSlotViewHandler implements SlotViewHandler{
    private Slot slot;

    public TextSlotViewHandler(Slot slot) {
        this.slot = slot;
    }

    @Override
    public void paint(Graphics2D g, int X, int Y, int width, int height, SlideView slideView) {
        JLabel renderer = new JLabel(slot.getContent());
        CellRendererPane crp = new CellRendererPane();
        renderer.setForeground(Color.WHITE);
        crp.paintComponent(g, renderer, slideView,
                X, Y, width, height);
    }
}
