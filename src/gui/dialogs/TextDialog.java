package gui.dialogs;

import gui.dialogs.DialogControllers.TextDialogListener;
import model.workspace.Slot;

import javax.swing.*;
import java.awt.*;

public class TextDialog extends JDialog {
    private Slot slot;

    private JPanel vBox;
    private JButton saveButton;
    private TextDialogToolBar textDialogToolBar;
    private JTextPane jTextPane;
    private JScrollPane jScrollPane;

    public TextDialog(Frame owner, String title, boolean modal, Slot slot) {
        super(owner, title, modal);
        this.slot = slot;
        initializeGUI(owner);
    }

    private void initializeGUI(Frame owner) {
        setSize(500, 300);
        setLocationRelativeTo(owner);

        vBox = new JPanel(new BorderLayout());
        saveButton = new JButton("save");
        textDialogToolBar = new TextDialogToolBar();
        jTextPane = new JTextPane();
        jTextPane.setContentType("text/html");
        jScrollPane = new JScrollPane(jTextPane);

        textDialogToolBar.add(saveButton);
        vBox.add(textDialogToolBar,BorderLayout.NORTH);
        vBox.add(jScrollPane);

        jTextPane.setText(slot.getContent());
        saveButton.addActionListener(new TextDialogListener(slot,jTextPane));

        add(vBox);
    }
}
