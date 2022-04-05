package ActionManagers;

import app.MainFrame;
import gui.dialogs.EditPresentationDialog;
import gui.dialogs.SimpleDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditPresentationAction extends AbstractRudokAction{

    public EditPresentationAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("images/icons/edit.png"));
        putValue(NAME,"Edit");
        putValue(SHORT_DESCRIPTION,"Edit presentation");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EditPresentationDialog dialog=new EditPresentationDialog(MainFrame.getInstance(),"Edit presentation", true);
        dialog.setVisible(true);
    }
}
