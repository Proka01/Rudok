package gui.slideScrolling;

import MVCObserver.mvc.SlideView;
import model.workspace.Presentation;
import model.workspace.RuNode;
import model.workspace.Slide;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyScrollPanel extends JPanel {

    private List<SlideView> slideViewList = new ArrayList<>();
    private JPanel container = new JPanel(new GridLayout(0,1));
    private Presentation presentation;

    public MyScrollPanel(Presentation presentation,int width,int height){
        this.presentation = presentation;
        initializeGUI(width,height);

        loadSlideViews();
        attachSlides();

        JScrollPane scroll = new JScrollPane(container,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scroll);
    }

    private void initializeGUI(int width,int height){
        setSize(new Dimension(width,height));
        this.setBackground(Color.GRAY);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    }

    private void attachSlides() {
        for(SlideView slideView : slideViewList)
            container.add(slideView);
    }

    private void loadSlideViews() {
        for(RuNode node : presentation.getSlides()){
            if(node instanceof Slide){
                slideViewList.add((SlideView) ((Slide) node).getSubscribers().get(0));
            }
        }
        System.out.println("Number of presentation slides: "+slideViewList.size());
    }


}
