package ActionManagers;

import OpentHandler.LoadWorkspaceHandler;
import OpentHandler.OpenProjectHandler;
import app.MainFrame;
import gui.tree.model.MyTreeNode;
import model.workspace.Project;
import model.workspace.Workspace;
import serialization.DiagramFileFilter;
import serialization.WorkspaceFileFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class OpenWorkspaceAction extends AbstractRudokAction {

    public OpenWorkspaceAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("images/icons/openWs.png"));
        putValue(NAME,"Open another workspace");
        putValue(SHORT_DESCRIPTION,"Open another workspace");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new WorkspaceFileFilter());
        MyTreeNode root = (MyTreeNode) MainFrame.getInstance().getMyJTree().getModel().getRoot();
        Workspace workspace = (Workspace) root.getNode();

        if(jfc.showOpenDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
            File file = jfc.getSelectedFile();
            root.removeAllChildren();
            workspace.getProjects().removeAll(workspace.getProjects());
            MainFrame.getInstance().getProjectView().removeProjec();
            LoadWorkspaceHandler loadWorkspaceHandler = new LoadWorkspaceHandler();
            loadWorkspaceHandler.loadWorkspace(file.getAbsolutePath());
        }
    }
}
