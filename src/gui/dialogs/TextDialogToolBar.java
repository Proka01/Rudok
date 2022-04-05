package gui.dialogs;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;

public class TextDialogToolBar extends JToolBar {
    private JButton boldButton;
    private JButton italicButton;
    private JButton underlineButton;

    public TextDialogToolBar() {
        super(JToolBar.HORIZONTAL);
        setFloatable(false);
        initializeGUI();
    }

    private void initializeGUI() {
        boldButton = new JButton("B");
        italicButton = new JButton("\\");
        underlineButton = new JButton("U");

        boldButton.addActionListener(new StyledEditorKit.BoldAction());
        italicButton.addActionListener(new StyledEditorKit.ItalicAction());
        underlineButton.addActionListener(new StyledEditorKit.UnderlineAction());

        add(boldButton);
        add(italicButton);
        add(underlineButton);
    }
}
