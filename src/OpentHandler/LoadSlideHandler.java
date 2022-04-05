package OpentHandler;

import MVCObserver.mvc.SlideView;
import MVCObserver.mvc.SlideViewType;
import app.MainFrame;
import gui.tree.model.MyTreeNode;
import model.workspace.Slide;
import model.workspace.Slot;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LoadSlideHandler implements LoadHandler{

    private MyTreeNode whereToLoadTreeNode;
    private MyTreeNode slideTreeNode = null;

    public LoadSlideHandler(MyTreeNode whereToLoadTreeNode) {
        this.whereToLoadTreeNode = whereToLoadTreeNode;
    }

    @Override
    public void load(Object whatToLoad) {
        Slide slide = (Slide) whatToLoad;
        slide.readResolve();
        slideTreeNode = new MyTreeNode(slide);

        //Pravimo 3 instance slideView-a koje ce biti u subscriberima od slajda
        SlideView slideView = new SlideView(slide,new Dimension(350,200), SlideViewType.SLIDE_VIEW);
        SlideView miniSlideView = new SlideView(slide, new Dimension(100,50), SlideViewType.MINI_SLIDE_VIEW);
        SlideView dummySlideView = new SlideView(slide,new Dimension(350,200), SlideViewType.DUMMY_SLIDE_VIEW);

        List<Slot> slotsCopy = new ArrayList<>();
        List<Slot> slotsToDelete = slide.getSlotsLits();

        for(Slot slot : slide.getSlotsLits()){
            slotsCopy.add(new Slot(slot));
        }

        slide.getSlotsLits().removeAll(slotsToDelete);

        for(Slot slot : slotsCopy)
            slide.addSlot(slot);

        whereToLoadTreeNode.add(slideTreeNode);
        slideTreeNode.setParent(whereToLoadTreeNode);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyJTree());
    }
}
