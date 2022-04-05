package comandPattern;

import MVCObserver.mvc.ProjectView;
import app.MainFrame;
import gui.tree.model.MyTreeNode;
import model.workspace.Presentation;
import model.workspace.Project;
import model.workspace.RuNode;
import model.workspace.RuNodeComposite;

import javax.swing.*;

public class SharePresentationCommand extends AbstractCommand{

    private MyTreeNode whatToShareMyTreeNode;
    private MyTreeNode whereToShareMyTreeNode;
    private MyTreeNode newMyTreeNode;
    private String oldName;

    public SharePresentationCommand(MyTreeNode whatToShareMyTreeNode, MyTreeNode whereToShareMyTreeNode) {
        this.whatToShareMyTreeNode = whatToShareMyTreeNode;
        this.whereToShareMyTreeNode = whereToShareMyTreeNode;
        newMyTreeNode = new MyTreeNode(whatToShareMyTreeNode.getNode());
        oldName = whatToShareMyTreeNode.getNode().getName();
    }

    @Override
    public void doCommand() {
        whatToShareMyTreeNode.getNode().updateName( whatToShareMyTreeNode.getNode().getName() + "-shared");
        RuNode ruNodeWhatToShare = whatToShareMyTreeNode.getNode();
        RuNode ruNodeWhereToShare = whereToShareMyTreeNode.getNode();

        whereToShareMyTreeNode.add(newMyTreeNode);
        ((Presentation) ruNodeWhatToShare).getSharedProjects().add(ruNodeWhereToShare);
        ((Project) ruNodeWhereToShare).addSharedChild(ruNodeWhatToShare);

        deepCopy((Presentation) ruNodeWhatToShare,newMyTreeNode,whatToShareMyTreeNode);

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyJTree());
    }

    @Override
    public void undoCommand() {
        String name = whatToShareMyTreeNode.getNode().getName();
        whatToShareMyTreeNode.getNode().updateName(oldName);
        RuNode ruNodeWhatToShare = whatToShareMyTreeNode.getNode();
        RuNode ruNodeWhereToShare = whereToShareMyTreeNode.getNode();

        ((Presentation) ruNodeWhatToShare).getSharedProjects().remove(ruNodeWhereToShare);
        ((Project) ruNodeWhereToShare).removeSharedChild(ruNodeWhatToShare);
        whereToShareMyTreeNode.remove(newMyTreeNode);

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyJTree());
    }

    private void deepCopy(Presentation pres, MyTreeNode dest, MyTreeNode presTreeNode){
        if(presTreeNode.getChildCount() == dest.getChildCount())
            return;
        for(RuNode slide : pres.getSlides()){
            MyTreeNode newSlideMyTreeNode = new MyTreeNode(slide);
            dest.add(newSlideMyTreeNode);
        }
    }

}
