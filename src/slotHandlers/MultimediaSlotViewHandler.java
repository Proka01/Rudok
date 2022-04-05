package slotHandlers;

import MVCObserver.mvc.SlideView;
import model.workspace.Presentation;
import model.workspace.Slot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MultimediaSlotViewHandler implements SlotViewHandler{
    private Slot slot;

    public MultimediaSlotViewHandler(Slot slot) {
        this.slot = slot;
    }

    @Override
    public void paint(Graphics2D g, int X, int Y, int width, int height, SlideView slideView) {
        String url = slot.getContent();
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(url));
        } catch (IOException e) {
        }
        g.drawImage(img,X,Y,width,height,null);
    }
}
