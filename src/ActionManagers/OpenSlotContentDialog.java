package ActionManagers;

import MVCObserver.mvc.PresentationView;
import app.MainFrame;
import gui.dialogs.MultimediaDialog;
import gui.dialogs.TextDialog;
import model.workspace.SlotType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class OpenSlotContentDialog extends AbstractRudokAction{
    public OpenSlotContentDialog() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("images/icons/openDialog.png"));
        putValue(NAME,"Open dialog");
        putValue(SHORT_DESCRIPTION,"Open dialog");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = MainFrame.getInstance().getProjectView().getMyTabbedPane().getTabs().getSelectedIndex();
        PresentationView pw = MainFrame.getInstance().getProjectView().getMyTabbedPane().getPresentationsList().get(index);

        if(pw.getPresentation().getSelectedSlot() != null){
            if(pw.getPresentation().getSelectedSlot().getSlotType().equals(SlotType.TEXT)){
                TextDialog dialog=new TextDialog(MainFrame.getInstance(),"Text dialog", true,pw.getPresentation().getSelectedSlot());
                dialog.setVisible(true);
            }
            else{
                MultimediaDialog dialog=new MultimediaDialog(MainFrame.getInstance(),"Multimedia dialog", true,pw.getPresentation().getSelectedSlot());
                dialog.setVisible(true);
            }

        }
    }
}
