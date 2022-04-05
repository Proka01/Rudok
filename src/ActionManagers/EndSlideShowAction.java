package ActionManagers;

import MVCObserver.mvc.PresentationView;
import app.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EndSlideShowAction extends AbstractRudokAction{

    public EndSlideShowAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("images/icons/off.png"));
        putValue(NAME,"End slide show");
        putValue(SHORT_DESCRIPTION,"End slide show");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = MainFrame.getInstance().getProjectView().getMyTabbedPane().getTabs().getSelectedIndex();
        PresentationView pw = MainFrame.getInstance().getProjectView().getMyTabbedPane().getPresentationsList().get(index);

        pw.startEditPresentationState();
        pw.switchWindow();
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
    }
}
