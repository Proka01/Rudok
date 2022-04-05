package gui.dialogs.DialogControllers;

import app.MainFrame;
import comandPattern.AddRuNodeCommand;
import comandPattern.SharePresentationCommand;
import gui.tree.model.MyTreeNode;
import model.workspace.Presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShareListener implements ActionListener {

    private JComboBox<MyTreeNode> jComboBox;

    public ShareListener(JComboBox<MyTreeNode> jComboBox) {
        this.jComboBox = jComboBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyTreeNode lastSelectedComponent = (MyTreeNode) MainFrame.getInstance().getMyJTree().getLastSelectedPathComponent();
        if(lastSelectedComponent.getNode() instanceof Presentation){
            MyTreeNode whatToShareTreeNode = lastSelectedComponent;
            MyTreeNode whereToShareTreeNode = selectedFromCBox();
            MainFrame.getInstance().getCommandManager().addCommand(new SharePresentationCommand(whatToShareTreeNode,whereToShareTreeNode));
            //MainFrame.getInstance().getCommandManager().executeCommand();
        }
    }

    private MyTreeNode selectedFromCBox(){
        int index = jComboBox.getSelectedIndex();
        return  jComboBox.getItemAt(index);
    }
}
