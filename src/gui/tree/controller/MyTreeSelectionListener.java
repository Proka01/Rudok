package gui.tree.controller;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class MyTreeSelectionListener implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        //TODO: implementirati fuknciju

        TreePath path = e.getPath();
        for(int i=0; i<path.getPathCount(); i++){
            //TODO: implementirati funkciju
            System.out.println("getPath: "+e.getPath().getLastPathComponent().getClass());
            //System.out.println("getPath: "+e.getNewLeadSelectionPath());
        }

    }
}
