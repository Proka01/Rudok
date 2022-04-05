package SaveHandler;

import app.MainFrame;
import model.workspace.Project;
import serialization.DiagramFileFilter;

import javax.swing.*;
import java.io.*;

public class SaveProjectHandler {
    public void saveProject(Project project){
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new DiagramFileFilter());

        File projectFile=project.getProjectFile();

        if (!project.isChanged()){
            return;
        }

        if (project.getProjectFile()==null){
            if(jfc.showSaveDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
                projectFile=jfc.getSelectedFile();

            }else{
                return;
            }
        }

        ObjectOutputStream os;
        try {
            os = new ObjectOutputStream(new FileOutputStream(projectFile));
            os.writeObject(project);
            project.setProjectFile(projectFile);
            project.setChanged(false);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
