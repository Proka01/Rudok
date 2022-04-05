package OpentHandler;

import model.workspace.Project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class LoadWorkspaceHandler {

    public void loadWorkspace(String path){
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String projectPath = myReader.nextLine();

                try {
                    ObjectInputStream os = new ObjectInputStream(new FileInputStream(projectPath));
                    Project project =null;

                    try {
                        project = (Project) os.readObject();
                    }catch (Exception exp2){
                        exp2.printStackTrace();
                    }

                    OpenProjectHandler openProjectHandler = new OpenProjectHandler(project);
                    openProjectHandler.load();

                }catch (Exception exp1){
                    exp1.printStackTrace();
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
