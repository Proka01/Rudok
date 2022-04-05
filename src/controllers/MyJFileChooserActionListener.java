package controllers;

import app.MainFrame;
import errors.ErrorFactory;
import errors.ErrorTypes;
import gui.tree.model.MyTreeNode;
import model.workspace.Presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MyJFileChooserActionListener implements ActionListener {

    private JFileChooser jFileChooser;

    public MyJFileChooserActionListener(JFileChooser jFileChooser) {
        this.jFileChooser = jFileChooser;
    }

    private boolean isValidExtension(String path){
        int index = path.indexOf('.');
        String pom = path.substring(index+1);
        String[] extensions = new String[]{"jpg","jpeg","jfif","pjpeg","pjp","gif","png"};
        int n = extensions.length;
        for(int i = 0; i < n; i++)
            if(pom.equals(extensions[i]))
                return true;

        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int result = jFileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jFileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());

            MyTreeNode myTreeNode = (MyTreeNode) MainFrame.getInstance().getMyJTree().getLastSelectedPathComponent();

            if(myTreeNode == null){
                ErrorFactory.getInstance().generateError(ErrorTypes.NOTHING_SELECTED_ERROR);
                return;
            }

            if(myTreeNode.getNode() instanceof Presentation){
                if(!isValidExtension(selectedFile.getAbsolutePath()))
                    ErrorFactory.getInstance().generateError(ErrorTypes.INVALID_PICTURE_EXTENSION);
                else
                    ((Presentation) myTreeNode.getNode()).setURL(selectedFile.getAbsolutePath());
            }
            else
                ErrorFactory.getInstance().generateError(ErrorTypes.WRONG_COMPONENT_SELECTED);

        }
    }
}
