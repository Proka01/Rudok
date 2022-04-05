package OpentHandler;

import MVCObserver.mvc.PresentationView;
import MVCObserver.mvc.ProjectView;
import MVCObserver.mvc.SlideView;
import app.MainFrame;
import gui.tree.model.MyTreeNode;
import model.workspace.Presentation;
import model.workspace.Project;
import model.workspace.RuNode;
import model.workspace.Slide;

import javax.swing.*;

public class LoadPresenatationHandler implements LoadHandler{

    private MyTreeNode whereToLoadTreeNode;
    private MyTreeNode presentationTreeNode = null;

    public LoadPresenatationHandler(MyTreeNode whereToLoadTreeNode) {
        this.whereToLoadTreeNode = whereToLoadTreeNode;
    }

    public MyTreeNode getPresentationTreeNode() {
        return presentationTreeNode;
    }

    @Override
    public void load(Object whatToLoad) {
        Presentation pres = (Presentation) whatToLoad;
        pres.readResolve();
        presentationTreeNode = new MyTreeNode(pres);

        //pre nego sto prezentaciju povezemo sa presentationView
        //prvo ucitavamo slajdove i slotove, jer u suprotnom dolazi do pucanja zbog tabbedPane-a
        LoadSlideHandler loadSlideHandler = new LoadSlideHandler(presentationTreeNode);
        for(RuNode slide : pres.getSlides())
            loadSlideHandler.load(slide);

        //napravimo novi presentationView i povezemo ga sa odgovarajucom prezentacijom
        PresentationView presentationView = new PresentationView(pres);

        //Napravljene slideView-eve ubacimo u slideShowJPanel
        for(RuNode slide : pres.getSlides()){
            SlideView sw = (SlideView) ((Slide)slide).getSubscribers().get(2);
            presentationView.getSlideShowStatePanel().getSlideShowCardLayoutPanel().add(sw);
        }

        ProjectView projectView = MainFrame.getInstance().getProjectView();
        Project currentProject = (Project) pres.getParent();

        whereToLoadTreeNode.add(presentationTreeNode);
        presentationTreeNode.setParent(whereToLoadTreeNode);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyJTree());
    }
}
