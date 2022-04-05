package gui.dialogs;

import app.MainFrame;
import controllers.MyJFileChooserActionListener;
import errors.ErrorFactory;
import errors.ErrorTypes;
import gui.tree.model.MyTreeNode;
import model.workspace.Presentation;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class EditPresentationDialog extends JDialog {

    private JLabel authorNameJL;
    private JTextField authorNameTf;
    private JLabel chooseImgJL;
    private JButton chooseFromFileBtn;
    private JFileChooser jFileChooser;
    private JButton save;

    public EditPresentationDialog(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        initializeComponents();
        initializeGUI(parent);

    }

    private void initializeGUI(Frame parent){
        this.setLayout(new GridLayout(0,1));
        setSize(500, 300);
        setLocationRelativeTo(parent);

        JPanel hBox1 = new JPanel(new FlowLayout());
        hBox1.setBackground(Color.LIGHT_GRAY);
        hBox1.add(authorNameJL);
        hBox1.add(authorNameTf);
        JPanel vBox1 = new JPanel();
        vBox1.add(hBox1);
        vBox1.add(save);

        JPanel hBox2 = new JPanel(new FlowLayout());
        hBox2.setBackground(Color.gray);
        hBox2.add(chooseImgJL);
        hBox2.add(chooseFromFileBtn);
        JPanel vBox2 = new JPanel(new BorderLayout());
        vBox2.add(hBox2,BorderLayout.CENTER);

        add(vBox1);
        add(vBox2);
    }

    private void initializeComponents(){
        authorNameJL = new JLabel("Author name: ");
        authorNameTf = new JTextField(20);
        //authorNameTe.setSize(new Dimension(5,20));
        chooseImgJL = new JLabel("Choose img from file: ");
        chooseFromFileBtn = new JButton("Choose");
        jFileChooser = new JFileChooser(new File("C:\\Users\\Aleksa\\Desktop\\rudok-Proka01\\src\\images"));
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileFilter filter = new FileNameExtensionFilter("Image","jpg","jpeg","jfif","pjpeg","pjp","gif","png");
        jFileChooser.setFileFilter(filter);
        save = new JButton("Save");

        chooseFromFileBtn.addActionListener(new MyJFileChooserActionListener(jFileChooser));

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyTreeNode myTreeNode = (MyTreeNode) MainFrame.getInstance().getMyJTree().getLastSelectedPathComponent();
                if(myTreeNode == null)
                    ErrorFactory.getInstance().generateError(ErrorTypes.NOTHING_SELECTED_ERROR);

                if(!authorNameTf.getText().isBlank()){
                    if(myTreeNode.getNode() instanceof Presentation){
                        ((Presentation) myTreeNode.getNode()).setAuthorName(authorNameTf.getText());
                    }
                    else
                        ErrorFactory.getInstance().generateError(ErrorTypes.WRONG_COMPONENT_SELECTED);
                }
                else
                    ErrorFactory.getInstance().generateError(ErrorTypes.EMPTY_AUTHOR_NAME);
            }
        });

    }
}
