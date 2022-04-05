package gui.tree.view;

import gui.tree.controller.MyMouseListener;
import gui.tree.controller.MyTreeCellEditor;
import gui.tree.controller.MyTreeCellRenderer;
import gui.tree.controller.MyTreeSelectionListener;
import gui.tree.model.MyTreeModel;
import gui.tree.model.MyTreeNode;
import model.workspace.Project;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;

public class MyJTree extends JTree {

    private MyTreeModel myTreeModel;

    public MyJTree() {
        System.out.println("uso");
        addTreeSelectionListener(new MyTreeSelectionListener());
        setCellEditor(new MyTreeCellEditor(this,new DefaultTreeCellRenderer()));
        setCellRenderer(new MyTreeCellRenderer());
        //ovo sam sada dodao
        addMouseListener(new MyMouseListener());
        setEditable(true);
    }

    public MyTreeModel getMyTreeModel() {
        return myTreeModel;
    }
}
