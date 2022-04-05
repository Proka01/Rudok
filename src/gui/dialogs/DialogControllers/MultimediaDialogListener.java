package gui.dialogs.DialogControllers;

import gui.dialogs.MyImagePanel;
import model.workspace.Slot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultimediaDialogListener implements ActionListener {
    private Slot slot;
    private MyImagePanel myImagePanel;

    public MultimediaDialogListener(Slot slot, MyImagePanel myImagePanel) {
        this.slot = slot;
        this.myImagePanel = myImagePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        slot.setContent(myImagePanel.getImageURL());
    }
}
