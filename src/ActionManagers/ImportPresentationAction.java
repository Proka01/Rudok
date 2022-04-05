package ActionManagers;

import OpentHandler.LoadPresenatationHandler;
import OpentHandler.OpenProjectHandler;
import app.MainFrame;
import gui.dialogs.ImportPresentationDialog;
import gui.dialogs.SharePresentationDialog;
import model.workspace.Presentation;
import model.workspace.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ImportPresentationAction extends AbstractRudokAction{

    public ImportPresentationAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("images/icons/import.png"));
        putValue(NAME,"Import");
        putValue(SHORT_DESCRIPTION,"Import");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        //TODO: dodati filter da se prikazu samo prezentacije

        if(jfc.showOpenDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
            try {
                ObjectInputStream os = new ObjectInputStream(new FileInputStream(jfc.getSelectedFile()));

                Presentation presentation =null;
                try {
                    presentation = (Presentation) os.readObject();
                } catch (ClassNotFoundException a) {
                    a.printStackTrace();
                }

                ImportPresentationDialog dialog=new ImportPresentationDialog(MainFrame.getInstance(),"Share presentation", true);
                dialog.setImportedPresentation(presentation);
                dialog.setVisible(true);

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }
}
