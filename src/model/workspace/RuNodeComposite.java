package model.workspace;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class RuNodeComposite extends RuNode implements Serializable {

    List<RuNode> children = new ArrayList<>();

    public RuNodeComposite() {
    }

    public RuNodeComposite(RuNode parent, String name) {
        super(parent, name);
    }

    public RuNodeComposite(RuNode parent, String name, RuNodeType ruNodeType) {
        super(parent, name, ruNodeType);
    }

    public abstract void addChild(RuNode node);
    public abstract void removeChild(RuNode node);

    public List<RuNode> getChildren() {
        return children;
    }

    public void setChildren(List<RuNode> children) {
        this.children = children;
    }
}
