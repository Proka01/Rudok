package ActionManagers;

import app.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends AbstractRudokAction{

    public UndoAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("images/icons/undo.png"));
        putValue(NAME,"Undo");
        putValue(SHORT_DESCRIPTION,"Undo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getCommandManager().undoCommand();
        System.out.println("UNDO:" + MainFrame.getInstance().getCommandManager().getCurrentCommand());
    }
}
