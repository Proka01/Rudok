package OpentHandler;

import app.MainFrame;
import gui.tree.model.MyTreeNode;
import model.workspace.*;

public class OpenProjectHandler {
    private Project project;

    public OpenProjectHandler(Project project) {
        this.project = project;
    }

    public void load() {
        MyTreeNode workspaceTreeNode = (MyTreeNode) MainFrame.getInstance().getMyJTree().getModel().getRoot();
        LoadProjectHandler loadProjectHandler = new LoadProjectHandler(workspaceTreeNode);
        loadProjectHandler.load(project);

        for(RuNode pres : project.getPresentations()){
            LoadPresenatationHandler loadPresenatationHandler = new LoadPresenatationHandler(loadProjectHandler.getProjectTreeNode());
            loadPresenatationHandler.load(pres);
        }

        //Na pocetku projetView-u nije dodeljen ni jedan projekat
        //Pa mora da ide ovaj if
        if(MainFrame.getInstance().getProjectView().getProject() == null)
            MainFrame.getInstance().getProjectView().setProject(project);

    }
}
