package gui;

import app.MainFrame;

import javax.swing.*;

public class ToolbarSlideShowView extends JToolBar {

    public ToolbarSlideShowView() {
        super(JToolBar.HORIZONTAL);
        setFloatable(false);
        add(MainFrame.getInstance().getActionManager().getEndSlideShowAction());
    }
}
