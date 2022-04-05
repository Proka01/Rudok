package ActionManagers;

import app.MainFrame;
import gui.tree.model.MyTreeNode;
import model.workspace.Project;
import model.workspace.RuNode;
import SaveHandler.SaveProjectHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SaveAction extends AbstractRudokAction {

    public SaveAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("images/icons/savePrj.png"));
        putValue(NAME,"Save");
        putValue(SHORT_DESCRIPTION,"Save");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RuNode node = ((MyTreeNode)MainFrame.getInstance().getMyJTree().getLastSelectedPathComponent()).getNode();
        if(!(node instanceof Project)){
            System.out.println("Nista nije izabrano");
            return;
        }
        Project project = (Project) ((MyTreeNode)MainFrame.getInstance().getMyJTree().getLastSelectedPathComponent()).getNode();

        SaveProjectHandler saveProjectHandler = new SaveProjectHandler();
        saveProjectHandler.saveProject(project);
    }
}
