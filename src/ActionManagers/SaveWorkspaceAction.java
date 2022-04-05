package ActionManagers;

import SaveHandler.SaveWorkspaceHandler;
import app.MainFrame;
import gui.tree.model.MyTreeNode;
import model.workspace.Project;
import model.workspace.Workspace;
import SaveHandler.SaveProjectHandler;
import serialization.DiagramFileFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveWorkspaceAction extends AbstractRudokAction{

    public SaveWorkspaceAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("images/icons/saveWs.png"));
        putValue(NAME,"Save workspace");
        putValue(SHORT_DESCRIPTION,"Save workspace");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SaveWorkspaceHandler saveWorkspaceHandler = new SaveWorkspaceHandler();
        saveWorkspaceHandler.saveWorkspace();
    }
}
