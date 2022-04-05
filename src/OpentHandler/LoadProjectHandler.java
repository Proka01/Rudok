package OpentHandler;

import app.MainFrame;
import gui.tree.model.MyTreeNode;
import model.workspace.Project;
import model.workspace.Workspace;

import javax.swing.*;

public class LoadProjectHandler implements LoadHandler{

    private MyTreeNode whereToLoadTreeNode;
    private MyTreeNode projectTreeNode = null;

    public LoadProjectHandler(MyTreeNode whereToLoadTreeNode) {
        this.whereToLoadTreeNode = whereToLoadTreeNode;
    }

    public MyTreeNode getProjectTreeNode() {
        return projectTreeNode;
    }

    @Override
    public void load(Object whatToLoad) {
        Project project = (Project)whatToLoad;
        project.readResolve();
        projectTreeNode = new MyTreeNode(project);
        project.addSubscriber(MainFrame.getInstance().getProjectView());

        //Roditelju (Workspace) dodeljujemo novo dete (project) u lictu dece
        ((Workspace)(whereToLoadTreeNode.getNode())).addChild(project);
        whereToLoadTreeNode.add(projectTreeNode);
        projectTreeNode.setParent(whereToLoadTreeNode);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyJTree());
    }
}
