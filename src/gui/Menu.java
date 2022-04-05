package gui;

import app.MainFrame;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class Menu extends JMenuBar {

    private JFrame parent=null;

    public Menu(final JFrame parent) {
        this.parent = parent;
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        //stavka file manuBar-a
        JMenu menuFile=new JMenu("File"); //TODO
        menuFile.add(MainFrame.getInstance().getActionManager().getNewRuNode());
        menuFile.add(MainFrame.getInstance().getActionManager().getDeleteRuNode());

        //stavka edit menuBar-a
        JMenu menuEdit = new JMenu("Edit");
        menuEdit.add(MainFrame.getInstance().getActionManager().getEditPresentationAction());

        //stavka help manuBar-a
        JMenu menuHelp = new JMenu("Help");
        menuHelp.add(MainFrame.getInstance().getActionManager().getInfoAction());

        //dodavanje file i help u menu
        this.add(menuFile);
        this.add(menuEdit);
        this.add(menuHelp);
    }
}
