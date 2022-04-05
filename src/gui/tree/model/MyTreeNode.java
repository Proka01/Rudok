package gui.tree.model;

import model.workspace.*;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class MyTreeNode extends DefaultMutableTreeNode {

    private RuNode node;

    public MyTreeNode(RuNode node) {
        this.node = node;
    }

    public RuNode getNode() {
        return node;
    }

    public void setNode(RuNode node) {
        this.node = node;
    }

    @Override
    public boolean isRoot() {
        if(node instanceof Workspace)
            return true;
        else
            return false;
    }

    @Override
    public boolean isLeaf() {
        if(!(node instanceof Slide))
            return false;
        else
            return true;
    }

    @Override
    public boolean getAllowsChildren() {
        if(!isLeaf())
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        if(node instanceof Project){
            boolean changed = ((Project)node).isChanged();
            return ((changed?"* ":"")+ node.getName());
        }
        return node.getName();
    }

}
