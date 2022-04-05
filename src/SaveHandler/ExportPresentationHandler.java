package SaveHandler;

import app.MainFrame;
import model.workspace.Presentation;
import serialization.PresFileFilter;

import javax.swing.*;
import java.io.*;

public class ExportPresentationHandler {
    public void exportPresentation(Presentation pres){
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new PresFileFilter());

        File presFile = pres.getPresFile();

        if (pres.getPresFile()==null){
            if(jfc.showSaveDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
                //File newPresFile = new File(jfc.getCurrentDirectory()+"\\"+pres.getName()+".rd_pres");
                File newPresFile = jfc.getSelectedFile();
                presFile=newPresFile;

            }else{
                return;
            }
        }

        ObjectOutputStream os;
        try {
            os = new ObjectOutputStream(new FileOutputStream(presFile));
            os.writeObject(pres);
            pres.setPresFile(presFile);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
