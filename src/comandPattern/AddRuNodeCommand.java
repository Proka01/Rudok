package comandPattern;

import MVCObserver.mvc.ProjectView;
import app.MainFrame;
import gui.tree.model.MyTreeNode;
import model.workspace.*;

import javax.swing.*;

public class AddRuNodeCommand extends AbstractCommand{

    MyTreeNode myTreeNodeChild;
    MyTreeNode myTreeNodeParent;

    public AddRuNodeCommand(MyTreeNode myTreeNodeParent, MyTreeNode myTreeNodeChild) {
        this.myTreeNodeChild = myTreeNodeChild;
        this.myTreeNodeParent = myTreeNodeParent;
    }

    @Override
    public void doCommand() {
        RuNode ruNodeChild = myTreeNodeChild.getNode();
        RuNode ruNodeParent = myTreeNodeParent.getNode();
        myTreeNodeParent.add(myTreeNodeChild);
        myTreeNodeChild.setParent(myTreeNodeParent);

        if(ruNodeParent instanceof Project){
            ProjectView projectView = MainFrame.getInstance().getProjectView();
            Project currentProject = (Project) ruNodeParent;

            //Na pocetku projetView-u nije dodeljen ni jedan projekat
            //Pa mora da ide ovaj if
            if(MainFrame.getInstance().getProjectView().getProject() == null)
                projectView.setProject(currentProject);
        }

        ruNodeChild.setParent(ruNodeParent);
        ((RuNodeComposite)ruNodeParent).addChild(ruNodeChild);

        if(myTreeNodeChild.getNode() instanceof Slide){
            Slide slide = (Slide)(myTreeNodeChild.getNode());
            Presentation presentation = (Presentation) (myTreeNodeParent.getNode());
            deepCopyDelete(slide,presentation, (MyTreeNode) myTreeNodeParent.getParent(),false);
        }

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyJTree());
    }

    @Override
    public void undoCommand() {
        RuNode ruNodeChild = myTreeNodeChild.getNode();
        RuNode ruNodeParent = myTreeNodeParent.getNode();

        ((RuNodeComposite)ruNodeParent).removeChild(ruNodeChild);
        ruNodeChild.setParent(null);
        myTreeNodeParent.remove(myTreeNodeChild);

        if(myTreeNodeChild.getNode() instanceof Slide){
            Slide slide = (Slide)(myTreeNodeChild.getNode());
            Presentation presentation = (Presentation) (myTreeNodeParent.getNode());
            deepCopyDelete(slide,presentation, (MyTreeNode) myTreeNodeParent.getParent(),true);
        }

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyJTree());
    }

    private void deepCopyDelete(Slide slide, Presentation pres, MyTreeNode myTreeNodeParent, boolean delete){
        MyTreeNode root = (MyTreeNode) MainFrame.getInstance().getMyJTree().getModel().getRoot();

        for(int i = 0; i < root.getChildCount(); i++){
            MyTreeNode childProject = (MyTreeNode) root.getChildAt(i);
            Project project = (Project) childProject.getNode();

            if(pres.getSharedProjects().contains(project) && !project.equals(myTreeNodeParent.getNode())){
                for(int j = 0; j < childProject.getChildCount(); j++){
                    MyTreeNode childPres = (MyTreeNode) childProject.getChildAt(j);
                    if(childPres.getNode().equals(pres)){
                        if(!delete){
                            MyTreeNode newSlideMyTreeNode = new MyTreeNode(slide);
                            childPres.add(newSlideMyTreeNode);
                        }
                        else{
                            childPres.remove(childPres.getChildCount()-1);
                        }
                    }
                }
            }
        }
    }



}
