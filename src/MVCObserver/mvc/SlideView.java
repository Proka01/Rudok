package MVCObserver.mvc;

import MVCObserver.mvc.slideViewController.SlideViewMouseListener;
import MVCObserver.mvc.slideViewController.SlideViewMouseMotionListener;
import MVCObserver.observer.DummyNotification;
import MVCObserver.observer.ISubscriber;
import MVCObserver.observer.NotificationStatus;
import app.MainFrame;
import model.workspace.Presentation;
import model.workspace.Slide;
import model.workspace.Slot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SlideView extends JPanel implements ISubscriber{

    private SlideViewType slideViewType;
    private Slide slide;
    private JLabel slideNumberJL;
    private Dimension dimension;
    private List<SlotView> slotViewsList = new ArrayList<>();
    private Point prevPoint;

    public SlideView(Slide slide) {
        this.slide = slide;
        this.slide.addSubscriber(this);
        dimension = new Dimension(350,200);
        initializeGUI();
    }

    public SlideView(Slide slide, Dimension dimension,SlideViewType slideViewType) {
        this.slideViewType = slideViewType;
        this.slide = slide;
        this.slide.addSubscriber(this);
        this.dimension = dimension;
        this.addMouseListener(new SlideViewMouseListener(this));
        this.addMouseMotionListener(new SlideViewMouseMotionListener(this));
        initializeGUI();
    }

    private void initializeGUI(){
        setLayout(new BorderLayout());
        //setSize(new Dimension(5,2));

        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);

        slideNumberJL = new JLabel(String.valueOf(slide.getSlideNumber()));
        slideNumberJL.setForeground(Color.white);
        add(slideNumberJL,BorderLayout.SOUTH);
    }

    public void setColor(Color color){
        setBackground(color);
    }

    /** Metode ISubscriber-a */

    @Override
    public void update(Object notification) {
        DummyNotification dummy = (DummyNotification) notification;
        if(dummy.getStatus().equals(NotificationStatus.SLOT_ADDED)){
            SlotView slotView = new SlotView(dummy.getSlot(),this);
            this.slotViewsList.add(slotView);
        }

        if(dummy.getStatus().equals(NotificationStatus.SLOT_REMOVED)){
            int n = slotViewsList.size();
            for(int i = n - 1; i >= 0; i--){
                if(slotViewsList.get(i).getSlot().equals(dummy.getSlot())){
                    SlotView slotView = slotViewsList.get(i);
                    this.slotViewsList.remove(slotView);
                }
            }
        }

        if(dummy.getStatus().equals(NotificationStatus.SLOTS_DESELECTED)){
            for(SlotView sw : slotViewsList)
                sw.getSlot().setSelected(false);
        }

        repaint();
    }

    /** Overridovane metode */

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //postavljanje pozadinske slike
        String url = ((Presentation)slide.getParent()).getURL();
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(url));
        } catch (IOException e) {
        }
        g.drawImage(img,0,0,getWidth(),getHeight(),null);

        //iscrtavanje slotView-ova
        Graphics2D g2 = (Graphics2D) g;

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        Iterator<SlotView> it = slotViewsList.iterator();
        while(it.hasNext()){
            SlotView slotView = (SlotView) it.next();

            if(this.slideViewType.equals(SlideViewType.DUMMY_SLIDE_VIEW))
                slotView.getSlot().setSelected(false);

            slotView.paint(this,g2,this.slideViewType);
        }

        System.out.println("dimmmm: "+this.getWidth());
        //System.out.println("slotViewsList size:"+this.slotViewsList);
    }

    /** getters and setters */

    public Slide getSlide() {
        return slide;
    }

    public List<SlotView> getSlotViewsList() {
        return slotViewsList;
    }

    public Point getPrevPoint() {
        return prevPoint;
    }

    public void setPrevPoint(Point prevPoint) {
        this.prevPoint = prevPoint;
    }

    public SlideViewType getSlideViewType() {
        return slideViewType;
    }
}
