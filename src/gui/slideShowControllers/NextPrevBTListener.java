package gui.slideShowControllers;

import MVCObserver.mvc.SlideView;
import gui.SlideShowJPanel;
import model.workspace.Slide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextPrevBTListener implements ActionListener {

    private SlideShowJPanel slideShowJPanel;
    private JPanel slideShowCardLayoutPanel;

    public NextPrevBTListener(SlideShowJPanel slideShowJPanel) {
        this.slideShowJPanel = slideShowJPanel;
        this.slideShowCardLayoutPanel = slideShowJPanel.getSlideShowCardLayoutPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout cardLayout = (CardLayout) (slideShowCardLayoutPanel.getLayout());
        String cmd = e.getActionCommand();

        if(cmd.equals("next")){
            cardLayout.next(slideShowCardLayoutPanel);
            updateCurrentSlide(+1);
        }
        else if(cmd.equals("prev")){
            cardLayout.previous(slideShowCardLayoutPanel);
            updateCurrentSlide(-1);
        }

    }

    private void updateCurrentSlide(int val){
        int size = slideShowJPanel.getSlideList().size();
        int index = slideShowJPanel.getIndexOfCurrentDisplayedSlide();
        System.out.println("previous index: "+index);
        index = index + val;
        if(index < 0)
            index = index + size;
        else
            index = index % size;

        System.out.println("Current index: "+index);
        slideShowJPanel.setIndexOfCurrentDisplayedSlide(index);


        Slide slide = (Slide) slideShowJPanel.getSlideList().get(index);
        slideShowJPanel.setCurrentDisplayedSlide(slide);
    }
}
