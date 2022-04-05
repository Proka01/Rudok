package gui.tree.controller;

import gui.tree.model.MyTreeNode;
import model.workspace.Presentation;
import model.workspace.Project;
import model.workspace.Slide;
import model.workspace.Workspace;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class MyTreeCellRenderer extends DefaultTreeCellRenderer {

    public MyTreeCellRenderer() {
    }

    public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean sel,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row,hasFocus);

        //TODO: implementirati ovaj deo za potrebe RuDok app
        if(value instanceof MyTreeNode){
            Icon icon = null;
            if(((MyTreeNode) value).getNode() instanceof Workspace){
                icon = loadIcon("images/workspace.png");
                //icon = new ImageIcon("C:\\Users\\Aleksa\\Desktop\\rudok-Proka01\\src\\gui\\tree\\images\\workspace.png");
            }
            else if(((MyTreeNode) value).getNode() instanceof Project){
                icon = loadIcon("images/pres1.png");
                //icon = new ImageIcon("C:\\Users\\Aleksa\\Desktop\\rudok-Proka01\\src\\gui\\tree\\images\\pres1.png");
            }
            else if(((MyTreeNode) value).getNode() instanceof Presentation){
                icon = loadIcon("images/pres2.png");
                //icon = new ImageIcon("C:\\Users\\Aleksa\\Desktop\\rudok-Proka01\\src\\gui\\tree\\images\\pres2.png");
            }
            else if(((MyTreeNode) value).getNode() instanceof Slide){
                icon = loadIcon("images/slide.png");
                //icon = new ImageIcon("C:\\Users\\Aleksa\\Desktop\\rudok-Proka01\\src\\gui\\tree\\images\\slide.png");
            }


            setIcon(icon);

        }
        /*if (value instanceof Diagram ) {
            URL imageURL = getClass().getResource("images/tdiagram.gif");
            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(imageURL);
            setIcon(icon);

        } else if (value instanceof Project ) {
            URL imageURL = getClass().getResource("images/tproject.gif");
            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(imageURL);
            setIcon(icon);

        }*/



        return this;
    }

    private Icon loadIcon(String fileName)
    {
        URL imageURL = getClass().getResource(fileName);
        Icon icon = null;
        if(imageURL != null){
            icon=new ImageIcon(imageURL);
        }
        else{
            System.err.println("Resource not found: "+fileName);
        }
        return icon;
    }

}
