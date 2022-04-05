package ActionManagers;

import MVCObserver.mvc.PresentationView;
import app.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SelectSlotStateAction extends AbstractRudokAction{

    public SelectSlotStateAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("images/icons/select.png"));
        putValue(NAME,"Select slot");
        putValue(SHORT_DESCRIPTION,"Select slot");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = MainFrame.getInstance().getProjectView().getMyTabbedPane().getTabs().getSelectedIndex();
        PresentationView pw = MainFrame.getInstance().getProjectView().getMyTabbedPane().getPresentationsList().get(index);

        pw.startSelectSlotState();
    }
}
