package ActionManagers;

import OpentHandler.OpenProjectHandler;
import app.MainFrame;
import model.factory.FactoryManager;
import model.workspace.Project;
import serialization.DiagramFileFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class OpenProjectAction extends AbstractRudokAction{

    private FactoryManager factoryManager = FactoryManager.getInstance();

    public OpenProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("images/icons/open.png"));
        putValue(NAME,"Open project");
        putValue(SHORT_DESCRIPTION,"Open project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new DiagramFileFilter());

        if(jfc.showOpenDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
            try {
                ObjectInputStream os = new ObjectInputStream(new FileInputStream(jfc.getSelectedFile()));

                Project project =null;
                try {
                    project = (Project) os.readObject();
                } catch (ClassNotFoundException a) {
                    // TODO Auto-generated catch block
                    a.printStackTrace();
                }

                OpenProjectHandler openProjectHandler = new OpenProjectHandler(project);
                openProjectHandler.load();

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
