package ActionManagers;

import app.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RedoAction extends AbstractRudokAction{

    public RedoAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("images/icons/redo.png"));
        putValue(NAME,"Redo");
        putValue(SHORT_DESCRIPTION,"Redo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getCommandManager().doCommand();
        System.out.println("REDO:" + MainFrame.getInstance().getCommandManager().getCurrentCommand());
    }
}
