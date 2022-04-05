package gui.dialogs;

import OpentHandler.LoadPresenatationHandler;
import app.MainFrame;
import gui.dialogs.DialogControllers.DummyListener;
import gui.dialogs.DialogControllers.ImportDialogListener;
import gui.dialogs.DialogControllers.ShareListener;
import gui.tree.model.MyTreeNode;
import model.workspace.Presentation;
import model.workspace.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImportPresentationDialog extends JDialog {

    private JPanel jPanel;
    private JComboBox<MyTreeNode> jComboBox;
    private JButton importButton;
    private Presentation importedPresentation = null;

    public ImportPresentationDialog(Frame owner, String title, boolean modal) {
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
        importButton = new JButton("import to selected project");
        jComboBox = new JComboBox<>();
        jComboBox.setMaximumSize(new Dimension(500,200));

        jPanel.add(jComboBox,BorderLayout.CENTER);
        jPanel.add(importButton,BorderLayout.SOUTH);

        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyTreeNode whereToImportTreeNode = (MyTreeNode) jComboBox.getItemAt(jComboBox.getSelectedIndex());
                LoadPresenatationHandler loadPresenatationHandler = new LoadPresenatationHandler(whereToImportTreeNode);
                if(importedPresentation != null){
                    loadPresenatationHandler.load(importedPresentation);
                    Project project = (Project) whereToImportTreeNode.getNode();
                    Presentation pres = (Presentation) loadPresenatationHandler.getPresentationTreeNode().getNode();
                    //Na pocetku projetView-u nije dodeljen ni jedan projekat
                    //Pa mora da ide ovaj if
                    if(MainFrame.getInstance().getProjectView().getProject() == null)
                        MainFrame.getInstance().getProjectView().setProject((Project) whereToImportTreeNode.getNode());
                    project.addChild(pres);
                }

            }
        });

        add(jPanel);
        System.out.println("Initialize GUI 1");
    }

    public void setImportedPresentation(Presentation importedPresentation) {
        this.importedPresentation = importedPresentation;
    }
}
