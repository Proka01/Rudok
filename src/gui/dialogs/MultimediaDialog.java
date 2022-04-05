package gui.dialogs;

import gui.dialogs.DialogControllers.MultimediaDialogListener;
import model.workspace.Slot;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class MultimediaDialog extends JDialog {
    private Slot slot;
    private JToolBar jToolBar;
    private JButton saveButton;
    private JFileChooser jFileChooser;
    private MyImagePanel imagePanel;
    private JPanel jPanel;
    private JButton chooseFromFileBtn;

    public MultimediaDialog(Frame owner, String title, boolean modal, Slot slot) {
        super(owner, title, modal);
        this.slot = slot;
        initializeGUI(owner);
    }

    private void initializeGUI(Frame owner) {
        setSize(500, 300);
        setLocationRelativeTo(owner);

        jPanel = new JPanel(new BorderLayout());
        jToolBar = new JToolBar();
        saveButton = new JButton("Save");
        jFileChooser = new JFileChooser(new File("C:\\Users\\Aleksa\\Desktop\\rudok-Proka01\\src\\images"));
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileFilter filter = new FileNameExtensionFilter("Image","jpg","jpeg","jfif","pjpeg","pjp","gif","png");
        jFileChooser.setFileFilter(filter);
        imagePanel = new MyImagePanel(slot);
        chooseFromFileBtn = new JButton("Choose image");
        chooseFromFileBtn.addActionListener(new MyJFCActionListener(jFileChooser,imagePanel));

        jToolBar.setOrientation(JToolBar.HORIZONTAL);
        jToolBar.setFloatable(false);
        jToolBar.add(chooseFromFileBtn);
        jToolBar.add(saveButton);
        jPanel.add(jToolBar,BorderLayout.NORTH);
        jPanel.add(imagePanel,BorderLayout.CENTER);

        saveButton.addActionListener(new MultimediaDialogListener(slot,imagePanel));

        add(jPanel);
    }

    @Override
    public void setDefaultCloseOperation(int operation) {
        super.setDefaultCloseOperation(operation);
        slot.setSelected(false);
    }
}
