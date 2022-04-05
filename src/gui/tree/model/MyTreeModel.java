package gui.tree.model;

import model.workspace.Presentation;
import model.workspace.Project;
import model.workspace.RuNode;
import model.workspace.Workspace;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class MyTreeModel extends DefaultTreeModel {

    private MyTreeNode root;

    public MyTreeModel(TreeNode root) {
        super(root);
        this.root = (MyTreeNode) root;
    }

    @Override
    public MyTreeNode getRoot() {
        return root;
    }
}
