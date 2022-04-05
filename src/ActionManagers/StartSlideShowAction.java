package ActionManagers;

import MVCObserver.mvc.PresentationView;
import app.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class StartSlideShowAction extends  AbstractRudokAction{

    public StartSlideShowAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("images/icons/start.png"));
        putValue(NAME,"Start slide show");
        putValue(SHORT_DESCRIPTION,"Slide show");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: dodaj error ako je index = -1
        int index = MainFrame.getInstance().getProjectView().getMyTabbedPane().getTabs().getSelectedIndex();
        PresentationView pw = MainFrame.getInstance().getProjectView().getMyTabbedPane().getPresentationsList().get(index);

        pw.startSlideShowState();
        pw.switchWindow();

        //MainFrame.getInstance().startSlideShowState();
        //MainFrame.getInstance().switchWindow();
    }
}
