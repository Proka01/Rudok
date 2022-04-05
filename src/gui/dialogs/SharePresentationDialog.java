package gui.dialogs;

import app.MainFrame;
import gui.dialogs.DialogControllers.ShareListener;
import gui.tree.model.MyTreeNode;
import model.workspace.Presentation;
import model.workspace.Project;
import model.workspace.RuNode;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SharePresentationDialog extends JDialog {
    private JPanel jPanel;
    private JComboBox<MyTreeNode> jComboBox;
    private JButton shareButton;

    public SharePresentationDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        initializeGUI(owner);
        loadJComboBox();
    }

    private void loadJComboBox() {
        MyTreeNode root = (MyTreeNode) MainFrame.getInstance().getMyJTree().getModel().getRoot();

        for(int i = 0; i < root.getChildCount(); i++){
            MyTreeNode child = (MyTreeNode) root.getChildAt(i);
            jComboBox.addItem(child);
        }
    }

    private void initializeGUI(Frame owner) {
        setSize(500,100);
        setLocationRelativeTo(owner);

        jPanel = new JPanel(new BorderLayout());
        shareButton = new JButton("Share presentation");
        jComboBox = new JComboBox<>();
        jComboBox.setMaximumSize(new Dimension(500,200));

        jPanel.add(jComboBox,BorderLayout.CENTER);
        jPanel.add(shareButton,BorderLayout.SOUTH);

        shareButton.addActionListener(new ShareListener(jComboBox));

        add(jPanel);
    }
}
