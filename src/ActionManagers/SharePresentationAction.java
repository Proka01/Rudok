package ActionManagers;

import app.MainFrame;
import gui.dialogs.SharePresentationDialog;
import gui.dialogs.SimpleDialog;
import gui.tree.model.MyTreeNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SharePresentationAction extends AbstractRudokAction {
    public SharePresentationAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("images/icons/share.png"));
        putValue(NAME,"Share presentation");
        putValue(SHORT_DESCRIPTION,"Share presentation");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SharePresentationDialog dialog=new SharePresentationDialog(MainFrame.getInstance(),"Share presentation", true);
        dialog.setVisible(true);
    }
}
