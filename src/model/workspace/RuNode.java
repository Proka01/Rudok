package model.workspace;

import MVCObserver.observer.IPublisher;
import MVCObserver.observer.ISubscriber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class RuNode implements IPublisher, Serializable {

    private RuNode parent;
    private String name;
    private RuNodeType ruNodeType;

    public RuNode() {
    }

    public RuNode(RuNode parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public RuNode(RuNode parent, String name, RuNodeType ruNodeType) {
        this.parent = parent;
        this.name = name;
        this.ruNodeType = ruNodeType;
    }

    public RuNode getParent() {
        return parent;
    }

    public void setParent(RuNode parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        //String pom = "-";
        //this.notifySubscribers(pom); //ranije je u () bilo new Object()
    }

    public abstract void updateName(String name); // u njemu cu zvati notify

    /** Getters and setters*/
    public RuNodeType getRuNodeType() {
        return ruNodeType;
    }

    public void setRuNodeType(RuNodeType ruNodeType) {
        this.ruNodeType = ruNodeType;
    }
}
