package statePresentation;

import MVCObserver.mvc.PresentationView;
import app.MainFrame;
import errors.ErrorFactory;
import errors.ErrorTypes;

import javax.swing.*;
import java.awt.*;

public class SlideShowState implements State{

    @Override
    public void switchWindow() {
        int index = MainFrame.getInstance().getProjectView().getMyTabbedPane().getTabs().getSelectedIndex();
        System.out.println("Index ----------> "+index);

        if(index != -1){
            PresentationView pw = MainFrame.getInstance().getProjectView().getMyTabbedPane().getPresentationsList().get(index);
            System.out.println("-> "+pw+" <-");

            pw.removeAll();
            pw.add(pw.getSlideShowStatePanel(),BorderLayout.CENTER);

            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
        }
        else{
            ErrorFactory.getInstance().generateError(ErrorTypes.NO_OPENED_PRESENTATION);
        }

    }
}
