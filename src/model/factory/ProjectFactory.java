package model.factory;

import MVCObserver.mvc.ProjectView;
import app.MainFrame;
import errors.ErrorFactory;
import errors.ErrorTypes;
import gui.tree.model.MyTreeNode;
import model.workspace.Project;
import model.workspace.RuNode;
import model.workspace.RuNodeType;
import model.workspace.Workspace;

import javax.swing.*;

public class ProjectFactory extends RuNodeFactory{

    @Override
    public RuNode createModel(RuNode parentRuNode, int childCount) {

        //kreiramo novi projekat
        Project project = new Project(parentRuNode,"project"+String.valueOf(childCount+1));

        //dodajemo mu projekView u subscribere
        project.addSubscriber(MainFrame.getInstance().getProjectView());
        project.setRuNodeType(RuNodeType.PROJECT);

        //Roditelju (Workspace) dodeljujemo novo dete (project) u lictu dece
        ((Workspace)parentRuNode).addChild(project);
        project.setParent(parentRuNode);

        return project;
    }
}
