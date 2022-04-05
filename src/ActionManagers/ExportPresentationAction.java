package ActionManagers;

import app.MainFrame;
import gui.tree.model.MyTreeNode;
import model.workspace.Presentation;
import SaveHandler.ExportPresentationHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ExportPresentationAction extends AbstractRudokAction{

    public ExportPresentationAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("images/icons/export.png"));
        putValue(NAME,"Export");
        putValue(SHORT_DESCRIPTION,"Export");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyTreeNode myTreeNode = (MyTreeNode) MainFrame.getInstance().getMyJTree().getLastSelectedPathComponent();
        if(!(myTreeNode.getNode() instanceof Presentation))
            return;

        Presentation presentation = (Presentation)myTreeNode.getNode();
        ExportPresentationHandler exportPresentationHandler = new ExportPresentationHandler();
        exportPresentationHandler.exportPresentation(presentation);
    }
}
