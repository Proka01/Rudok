package comandPattern;

import MVCObserver.mvc.ProjectView;
import app.MainFrame;
import gui.tree.model.MyTreeNode;
import model.workspace.Project;

import javax.swing.*;

public class RenameRuNodeCommand extends AbstractCommand{

    private String oldName;
    private String newName;
    private MyTreeNode myTreeNode;

    public RenameRuNodeCommand(String oldName, String newName, MyTreeNode myTreeNode) {
        this.oldName = oldName;
        this.newName = newName;
        this.myTreeNode = myTreeNode;
    }

    @Override
    public void doCommand() {
        if(myTreeNode.getNode() instanceof Project){
            //Na pocetku projetView-u nije dodeljen ni jedan projekat
            //Pa mora da ide ovaj if
            if(MainFrame.getInstance().getProjectView().getProject() == null)
                MainFrame.getInstance().getProjectView().setProject((Project) myTreeNode.getNode());
        }
        myTreeNode.getNode().updateName(newName);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyJTree());
    }

    @Override
    public void undoCommand() {
        if(myTreeNode.getNode() instanceof Project){
            //Na pocetku projetView-u nije dodeljen ni jedan projekat
            //Pa mora da ide ovaj if
            if(MainFrame.getInstance().getProjectView().getProject() == null)
                MainFrame.getInstance().getProjectView().setProject((Project) myTreeNode.getNode());
        }
        myTreeNode.getNode().updateName(oldName);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyJTree());
    }
}
