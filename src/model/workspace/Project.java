package model.workspace;

import MVCObserver.observer.DummyNotification;
import MVCObserver.observer.ISubscriber;
import MVCObserver.observer.NotificationStatus;
import app.MainFrame;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Project extends RuNodeComposite implements Serializable {

    private List<RuNode> presentations = new ArrayList<>();
    private transient List<ISubscriber> subscribers = new ArrayList<>();
    private transient boolean changed;
    private File projectFile;

    public Project() {
    }

    public Project(RuNode parent, String name) {
        super(parent, name);
        this.changed=false;
    }

    public void updatePerformed() {
        setChanged(true);
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        if (this.changed!=changed){
            this.changed=changed;
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyJTree());
        }
    }

    public void readResolve(){
        subscribers = new ArrayList<>();
    }

    @Override
    public void updateName(String name) {
        updatePerformed();
        this.setName(name);
        notifySubscribers(new DummyNotification(this,null, NotificationStatus.PROJECT_NAME_CHANGED));
    }

    public Project(List<RuNode> presentations) {
        this.presentations = presentations;
    }

    public Project(RuNode parent, String name, List<RuNode> presentations) {
        super(parent, name);
        this.presentations = presentations;
    }

    @Override
    public void addChild(RuNode node) {
        updatePerformed();
        if(presentations.contains(node))
            return;
        presentations.add((Presentation)node);
        Integer index = -1; //TODO: DODAO SAD
        this.notifySubscribers(new DummyNotification(this,(Presentation) node, NotificationStatus.PRESENTATION_ADDED)); //bilo je index ranije
        //TODO: ovde notify
    }

    public void addSharedChild(RuNode node){
        presentations.add((Presentation)node);
    }

    @Override
    public void removeChild(RuNode node) {
        updatePerformed();
        Integer index = presentations.indexOf((Presentation)node);
        this.notifySubscribers(new DummyNotification(this,(Presentation) node, NotificationStatus.PRESENTATION_REMOVED)); //bilo je index ranije
        presentations.remove(((Presentation)node));
        //TODO: ovde notify
    }

    public void removeSharedChild(RuNode node){
        presentations.remove(((Presentation)node));
    }

    @Override
    public String toString() {
        return ((changed?"* ":"")+ this.getName());
    }

    public void refresh(Object notification){
        this.notifySubscribers(notification);
    }

    public List<RuNode> getPresentations() {
        return presentations;
    }

    public void setPresentations(List<RuNode> presentations) {
        this.presentations = presentations;
    }

    public void addPresentation(Presentation presentation){
        this.presentations.add(presentation);
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        this.subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        //TODO: implementirati telo ove metode; ovde ce se zvati update metoda iz projectView
        subscribers.get(0).update(notification);
    }

    @Override
    public void removeAllSubscribers() {
        this.subscribers = new ArrayList<>();
    }


    public List<ISubscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<ISubscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public File getProjectFile() {
        return projectFile;
    }

    public void setProjectFile(File projectFile) {
        this.projectFile = projectFile;
    }
}
