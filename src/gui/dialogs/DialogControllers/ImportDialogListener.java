package gui.dialogs.DialogControllers;

import OpentHandler.LoadPresenatationHandler;
import gui.tree.model.MyTreeNode;
import model.workspace.Presentation;
import model.workspace.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImportDialogListener implements ActionListener {

    private JComboBox jComboBox;
    private MyTreeNode whereToImportTreeNode;
    private MyTreeNode whatToImportTreeNode;
    private Presentation importedPresentation;

    public ImportDialogListener(JComboBox jComboBox, Presentation importedPresentation) {
        this.jComboBox = jComboBox;
        this.importedPresentation = importedPresentation;
        this.whereToImportTreeNode = (MyTreeNode) jComboBox.getItemAt(jComboBox.getSelectedIndex());
        LoadPresenatationHandler loadPresenatationHandler = new LoadPresenatationHandler(whereToImportTreeNode);
        loadPresenatationHandler.load(importedPresentation);
        this.whatToImportTreeNode = loadPresenatationHandler.getPresentationTreeNode();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Project project = (Project) whereToImportTreeNode.getNode();
        Presentation pres = (Presentation) whatToImportTreeNode.getNode();

        System.out.println("Projekat :" + project);
        System.out.println("Prezentacija: "+ pres);

        project.addChild(pres);
        /*
        System.out.println("Doso dovde");
        whereToImportTreeNode.add(whatToImportTreeNode);
        whatToImportTreeNode.setParent(whereToImportTreeNode);

         */
    }
}
