package statePresentation;

import MVCObserver.mvc.PresentationView;
import app.MainFrame;

import java.awt.*;

public class EditPresentationState implements State{

    @Override
    public void switchWindow() {
        int index = MainFrame.getInstance().getProjectView().getMyTabbedPane().getTabs().getSelectedIndex();
        PresentationView pw = MainFrame.getInstance().getProjectView().getMyTabbedPane().getPresentationsList().get(index);

        pw.removeAll();
        pw.add(pw.getEditSlidesJPanel());
    }
}
