package gui.dialogs.DialogControllers;

import model.workspace.Slot;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextDialogListener implements ActionListener {
    private Slot slot;
    private JTextPane jTextPane;

    public TextDialogListener(Slot slot, JTextPane jTextPane) {
        this.slot = slot;
        this.jTextPane = jTextPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        slot.setContent(jTextPane.getText());
    }
}
