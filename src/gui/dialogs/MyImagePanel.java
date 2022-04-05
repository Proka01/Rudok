package gui.dialogs;

import model.workspace.Presentation;
import model.workspace.Slot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyImagePanel extends JPanel {
    private Slot slot;
    private String imageURL = null;

    public MyImagePanel(Slot slot) {
        this.slot = slot;
        imageURL = slot.getContent().equals("") ? null : slot.getContent();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //postavljanje pozadinske slike
        String url = imageURL;
        if(url == null) return;
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(url));
        } catch (IOException e) {
        }
        g.drawImage(img,0,0,getWidth(),getHeight(),null);
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
        repaint();
    }
}
