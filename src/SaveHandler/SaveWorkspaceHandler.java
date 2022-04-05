package SaveHandler;

import app.MainFrame;
import gui.tree.model.MyTreeNode;
import model.workspace.Project;
import model.workspace.Workspace;
import serialization.DiagramFileFilter;
import serialization.WorkspaceFileFilter;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveWorkspaceHandler {
    public void saveWorkspace(){
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new WorkspaceFileFilter());
        SaveProjectHandler saveProjectHandler = new SaveProjectHandler();
        MyTreeNode root = ((MyTreeNode) MainFrame.getInstance().getMyJTree().getModel().getRoot());
        Workspace workspace = (Workspace) root.getNode();

        if (workspace.getDirectoriumPath()==null){
            if(jfc.showSaveDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
                workspace.setFile(jfc.getSelectedFile());
                workspace.setDirectoriumPath(jfc.getCurrentDirectory().getAbsolutePath());
            }else{
                return;
            }
        }

        for(int i = 0; i < root.getChildCount(); i++){
            MyTreeNode child = (MyTreeNode) root.getChildAt(i);
            Project project = (Project) child.getNode();
            File newProjectFile = new File(workspace.getDirectoriumPath()+"\\"+project.getName()+".rd");
            project.setProjectFile(newProjectFile);
            project.setChanged(true);
            saveProjectHandler.saveProject(project);
        }

        try {
            FileWriter myWriter = new FileWriter(workspace.getFile());

            for(int i = 0; i < root.getChildCount(); i++){
                MyTreeNode child = (MyTreeNode) root.getChildAt(i);
                Project project = (Project) child.getNode();
                myWriter.write(project.getProjectFile().getAbsolutePath());
                myWriter.write("\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException a) {
            System.out.println("An error occurred.");
            a.printStackTrace();
        }
    }
}
