package gui;

import app.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JToolBar {

    public Toolbar(){

        super(JToolBar.HORIZONTAL);
        setFloatable(false);
        //add(MainFrame.getInstance().getActionManager().getNewAction());
        add(MainFrame.getInstance().getActionManager().getNewRuNode());
        add(MainFrame.getInstance().getActionManager().getDeleteRuNode());
        add(MainFrame.getInstance().getActionManager().getEditPresentationAction());
        add(MainFrame.getInstance().getActionManager().getSharePresentationAction());
        addSeparator(new Dimension(15,25));
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        add(MainFrame.getInstance().getActionManager().getRedoAction());
        addSeparator(new Dimension(15,25));
        add(MainFrame.getInstance().getActionManager().getInfoAction());
        addSeparator(new Dimension(15,25));
        add(MainFrame.getInstance().getActionManager().getSaveAction());
        add(MainFrame.getInstance().getActionManager().getSaveWorkspaceAction());
        addSeparator(new Dimension(15,25));
        add(MainFrame.getInstance().getActionManager().getOpenProjectAction());
        add(MainFrame.getInstance().getActionManager().getExportPresentationAction());
        add(MainFrame.getInstance().getActionManager().getImportPresentationAction());
        addSeparator(new Dimension(15,25));
        add(MainFrame.getInstance().getActionManager().getNewAction());
        add(MainFrame.getInstance().getActionManager().getOpenWorkspaceAction());
    }

}
