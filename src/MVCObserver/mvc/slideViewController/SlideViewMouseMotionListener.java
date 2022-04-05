package MVCObserver.mvc.slideViewController;

import MVCObserver.mvc.PresentationView;
import MVCObserver.mvc.SlideView;
import app.MainFrame;
import model.workspace.Slot;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class SlideViewMouseMotionListener implements MouseMotionListener {

    private SlideView slideView;

    public SlideViewMouseMotionListener(SlideView slideView) {
        this.slideView = slideView;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int X = e.getX();
        int Y = e.getY();

        Slot slot = new Slot(X,Y,slideView.getSlide());

        int index = MainFrame.getInstance().getProjectView().getMyTabbedPane().getTabs().getSelectedIndex();
        PresentationView pw = MainFrame.getInstance().getProjectView().getMyTabbedPane().getPresentationsList().get(index);

        pw.handleSlot(slot);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
