package ActionManagers;

import app.MainFrame;
import gui.tree.model.MyTreeNode;
import model.workspace.Workspace;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewAction extends AbstractRudokAction{
    public NewAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("images/icons/plus.png"));
        putValue(NAME,"New workspace");
        putValue(SHORT_DESCRIPTION,"New workspace");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyTreeNode root = (MyTreeNode) MainFrame.getInstance().getMyJTree().getModel().getRoot();
        Workspace workspace = (Workspace) root.getNode();
        root.removeAllChildren();
        MainFrame.getInstance().getProjectView().removeProjec();
        workspace.getProjects().removeAll(workspace.getProjects());
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyJTree());
    }
}
