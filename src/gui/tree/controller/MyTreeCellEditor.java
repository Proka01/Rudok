package gui.tree.controller;

import app.MainFrame;
import comandPattern.RenameRuNodeCommand;
import gui.tree.model.MyTreeNode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class MyTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {

    private Object stavka=null;
    private JTextField edit=null;

    public MyTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }

    public MyTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer, TreeCellEditor editor) {
        super(tree, renderer, editor);
    }

    //////////////////////////////////////////////////////////////////////////////
    ///ukoliko je isCellEditable true, java sama poziva getTreeCellEditorComponent

    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
        stavka=arg1;

        edit=new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }

    public boolean isCellEditable(EventObject arg0) {
        if (arg0 instanceof MouseEvent)
            if (((MouseEvent)arg0).getClickCount()==3){
                return true;
            }
        return false;
    }

    public void actionPerformed(ActionEvent e) {
        if(stavka instanceof MyTreeNode){
            String oldName = ((MyTreeNode) stavka).getNode().getName();
            String newName = e.getActionCommand();
            MainFrame.getInstance().getCommandManager().addCommand(new RenameRuNodeCommand(oldName,newName,(MyTreeNode) stavka));
            ((MyTreeNode) stavka).getNode().updateName(e.getActionCommand());
        }
    }

}
