package gui.dialogs;

import app.MainFrame;
import errors.ErrorFactory;
import errors.ErrorTypes;
import gui.tree.model.MyTreeNode;
import model.workspace.Presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MyJFCActionListener implements ActionListener {
    private JFileChooser jFileChooser;
    private MyImagePanel myImagePanel;

    public MyJFCActionListener(JFileChooser jFileChooser,MyImagePanel myImagePanel) {
        this.jFileChooser = jFileChooser;
        this.myImagePanel = myImagePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int result = jFileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jFileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());

            myImagePanel.setImageURL(selectedFile.getAbsolutePath());

        }
    }
}
