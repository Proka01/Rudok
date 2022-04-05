package gui;

import MVCObserver.mvc.SlideView;
import MVCObserver.mvc.SlotView;
import gui.slideShowControllers.NextPrevBTListener;
import model.workspace.RuNode;
import model.workspace.Slide;
import model.workspace.Slot;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SlideShowJPanel extends JPanel {

    private List<RuNode> slideList = new ArrayList<>();

    private JPanel slideShowCardLayoutPanel;
    private JPanel panelForButtons;
    private CardLayout cardLayout;
    private ToolbarSlideShowView toolbarSlideShowView;

    private JButton nextBT;
    private JButton prevBT;

    private Slide currentDisplayedSlide = null;
    private int indexOfCurrentDisplayedSlide = -1;

    public SlideShowJPanel() {initializeGUI();}

    public void initializeGUI(){
        this.setLayout(new BorderLayout());

        panelForButtons = new JPanel();

        cardLayout = new CardLayout();
        slideShowCardLayoutPanel = new JPanel();
        slideShowCardLayoutPanel.setLayout(cardLayout);
        slideShowCardLayoutPanel.setBackground(Color.cyan);

        nextBT = new JButton("Next");
        nextBT.setMaximumSize(new Dimension(40, 40));
        prevBT = new JButton("Previous");
        prevBT.setMaximumSize(new Dimension(40, 40));

        panelForButtons.add(prevBT);
        panelForButtons.add(nextBT);

        toolbarSlideShowView = new ToolbarSlideShowView();

        this.add(toolbarSlideShowView,BorderLayout.NORTH);
        this.add(slideShowCardLayoutPanel,BorderLayout.CENTER);
        this.add(panelForButtons,BorderLayout.SOUTH);

        nextBT.addActionListener(new NextPrevBTListener(this));
        nextBT.setActionCommand("next");
        prevBT.addActionListener(new NextPrevBTListener(this));
        prevBT.setActionCommand("prev");

        if(slideList.size() > 0){
            indexOfCurrentDisplayedSlide = 0;
            currentDisplayedSlide = (Slide) slideList.get(0);
        }

        initializeSlideViews();
    }

    private void initializeSlideViews()
    {
        for(RuNode ruNode : slideList){
            int br = 0;
            if(ruNode instanceof Slide){
                SlideView sw = (SlideView) ((Slide)ruNode).getSubscribers().get(2);
                slideShowCardLayoutPanel.add(sw,String.valueOf(br));
                br++;
                // MainFrame.getInstance().getSlideShowStatePanel().getSlideShowCardLayoutPanel().add(sw);
            }
        }
    }

    public void reloadSlideViews()
    {
        slideShowCardLayoutPanel.removeAll();
        initializeSlideViews();
    }

    /**Getters and setters*/
    public JPanel getSlideShowCardLayoutPanel() {
        return slideShowCardLayoutPanel;
    }

    public void setSlideShowCardLayoutPanel(JPanel slideShowCardLayoutPanel) {
        this.slideShowCardLayoutPanel = slideShowCardLayoutPanel;
    }

    public JPanel getPanelForButtons() {
        return panelForButtons;
    }

    public void setPanelForButtons(JPanel panelForButtons) {
        this.panelForButtons = panelForButtons;
    }

    public JButton getNextBT() {
        return nextBT;
    }

    public void setNextBT(JButton nextBT) {
        this.nextBT = nextBT;
    }

    public JButton getPrevBT() {
        return prevBT;
    }

    public void setPrevBT(JButton prevBT) {
        this.prevBT = prevBT;
    }

    public void setSlideList(List<RuNode> slideList) {
        this.slideList = slideList;
    }

    public List<RuNode> getSlideList() {
        return slideList;
    }

    public Slide getCurrentDisplayedSlide() {
        return currentDisplayedSlide;
    }

    public void setCurrentDisplayedSlide(Slide currentDisplayedSlide) {
        this.currentDisplayedSlide = currentDisplayedSlide;
    }

    public int getIndexOfCurrentDisplayedSlide() {
        return indexOfCurrentDisplayedSlide;
    }

    public void setIndexOfCurrentDisplayedSlide(int indexOfCurrentDisplayedSlide) {
        this.indexOfCurrentDisplayedSlide = indexOfCurrentDisplayedSlide;
    }
}
