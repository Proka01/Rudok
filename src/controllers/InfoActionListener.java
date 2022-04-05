package controllers;

import gui.dialogs.SimpleDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoActionListener implements ActionListener {

    private JFrame parent = null;

    public InfoActionListener(JFrame parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SimpleDialog dialog=new SimpleDialog(parent,"Info", true);
        dialog.setVisible(true);
    }
}
