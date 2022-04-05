package MVCObserver.controller;

import model.workspace.Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectController implements ActionListener {

    private Project project;

    public ProjectController(Project project) {
        this.project = project;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
