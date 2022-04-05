package controllers;

import SaveHandler.SaveWorkspaceHandler;
import app.MainFrame;
import gui.tree.model.MyTreeNode;
import model.workspace.Workspace;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.PrintWriter;

public class MyWindowActionListener implements WindowListener {
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

        JFrame frame= (JFrame) e.getComponent();
        int code=JOptionPane.showConfirmDialog(frame, "Da li ste sigurni da želite da zatvorite aplikaciju?","Zatvaranje aplikacije?",JOptionPane.YES_NO_OPTION);
        if (code!=JOptionPane.YES_OPTION){
            frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        }
        else{
            File file = new File("context.txt");


            try {
                PrintWriter pw = new PrintWriter(file);

                SaveWorkspaceHandler saveWorkspaceHandler = new SaveWorkspaceHandler();
                saveWorkspaceHandler.saveWorkspace();

                MyTreeNode workspaceTreeNode = (MyTreeNode) MainFrame.getInstance().getMyJTree().getModel().getRoot();
                Workspace workspace = (Workspace) workspaceTreeNode.getNode();
                pw.write(workspace.getFile().getAbsolutePath());

                pw.close();
            }catch (Exception exception){
                exception.printStackTrace();
            }

            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
