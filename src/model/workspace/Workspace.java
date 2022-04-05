package model.workspace;

import MVCObserver.observer.ISubscriber;
import app.MainFrame;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Workspace extends RuNodeComposite{
    private List<RuNode> projects = new ArrayList<>();
    private File file = null;
    private String directoriumPath = null;

    public Workspace(RuNode parent, String name) {
        super(parent, name);
    }

    public Workspace(RuNode parent, String name, RuNodeType ruNodeType) {
        super(parent, name, ruNodeType);
    }

    @Override
    public void updateName(String name) {

    }

    @Override
    public void addChild(RuNode node) {
        if(projects.contains(projects))
            return;
        projects.add((Project)node);
    }

    @Override
    public void removeChild(RuNode node) {
        projects.remove((Project)node);

        Project currentLoadedProject = (Project) MainFrame.getInstance().getProjectView().getProject();
        if(currentLoadedProject != null && currentLoadedProject.equals((Project)node)) //TODO: dodatao 10/11/2021
            MainFrame.getInstance().getProjectView().removeProjec();

        node = null;
    }

    public List<RuNode> getProjects() {
        return projects;
    }

    public void setProjects(List<RuNode> projects) {
        this.projects = projects;
    }

    public void addProject(Project project){
        this.projects.add(project);
    }

    @Override
    public void addSubscriber(ISubscriber sub) {

    }

    @Override
    public void removeSubscriber(ISubscriber sub) {

    }

    @Override
    public void notifySubscribers(Object notification) {

    }

    @Override
    public void removeAllSubscribers() {

    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getDirectoriumPath() {
        return directoriumPath;
    }

    public void setDirectoriumPath(String directoriumPath) {
        this.directoriumPath = directoriumPath;
    }
}
