package gui.tree.controller;

import MVCObserver.controller.ProjectController;
import MVCObserver.mvc.PresentationView;
import MVCObserver.mvc.ProjectView;
import app.MainFrame;
import com.sun.tools.javac.Main;
import gui.tree.model.MyTreeNode;
import model.workspace.Project;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2){

            MyTreeNode myTreeNode = (MyTreeNode) MainFrame.getInstance().getMyJTree().getLastSelectedPathComponent();
            if(myTreeNode != null){
                if(myTreeNode.getNode() instanceof Project){
                    System.out.println(">>>>>>>> "+myTreeNode.getNode().getName());

                    Project project = (Project) myTreeNode.getNode();
                    /** u setu treba da se radi update*/
                    if(project.getParent() != null)
                        MainFrame.getInstance().getProjectView().setProject(project);
                }

            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
