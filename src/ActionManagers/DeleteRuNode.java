package ActionManagers;

import MVCObserver.mvc.ProjectView;
import app.MainFrame;
import comandPattern.DeleteRuNodeCommand;
import errors.ErrorFactory;
import errors.ErrorTypes;
import gui.tree.model.MyTreeNode;
import model.workspace.*;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteRuNode extends AbstractRudokAction{

    public DeleteRuNode() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("images/icons/deleteIcon.png"));
        putValue(NAME,"Delete");
        putValue(SHORT_DESCRIPTION,"Delete");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("usao da brise");
        MyTreeNode lastSelectedComponent = (MyTreeNode) MainFrame.getInstance().getMyJTree().getLastSelectedPathComponent();
        MyTreeNode toBeRemovedTreeNode = lastSelectedComponent;
        System.out.println(">> "+toBeRemovedTreeNode);

        if(lastSelectedComponent != null && !(lastSelectedComponent.getNode() instanceof Workspace)){
            MyTreeNode treeNodeParent = (MyTreeNode) lastSelectedComponent.getParent();
            System.out.println(">> "+treeNodeParent);

            if(treeNodeParent != null){
                if(treeNodeParent.getNode() instanceof Workspace){
                    Workspace ruNodeParent = (Workspace) treeNodeParent.getNode();
                    Project toBeRemovedRuNode = (Project) toBeRemovedTreeNode.getNode();

                    ruNodeParent.removeChild(toBeRemovedRuNode);
                    toBeRemovedRuNode.setParent(null);
                    //treeNodeParent.remove(toBeRemovedTreeNode);
                }

                if(treeNodeParent.getNode() instanceof Project){
                    Project ruNodeParent = (Project) treeNodeParent.getNode();
                    Presentation toBeRemovedRuNode = (Presentation) toBeRemovedTreeNode.getNode();

                    ruNodeParent.removeChild(toBeRemovedRuNode);
                    toBeRemovedRuNode.setParent(null);
                    //treeNodeParent.remove(toBeRemovedTreeNode);
                    //ruNodeParent.notifySubscribers(null); //TODO: dodato 06/11/2021
                }

                if(treeNodeParent.getNode() instanceof Presentation){
                    Presentation ruNodeParent = (Presentation) treeNodeParent.getNode();
                    Slide toBeRemovedRuNode = (Slide) toBeRemovedTreeNode.getNode();

                    ruNodeParent.removeChild(toBeRemovedRuNode);
                    toBeRemovedRuNode.setParent(null);
                    //treeNodeParent.remove(toBeRemovedTreeNode);
                }

                MainFrame.getInstance().getCommandManager().addCommand(new DeleteRuNodeCommand(treeNodeParent,toBeRemovedTreeNode));
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyJTree());
            }
            else
                ErrorFactory.getInstance().generateError(ErrorTypes.NOTHING_SELECTED_ERROR);
        }
        else{
            if(lastSelectedComponent == null)
                ErrorFactory.getInstance().generateError(ErrorTypes.NOTHING_SELECTED_ERROR);
            else if(lastSelectedComponent.getNode() instanceof Workspace)
                ErrorFactory.getInstance().generateError(ErrorTypes.NO_PERMISSION_ERROR);
        }

    }
}
