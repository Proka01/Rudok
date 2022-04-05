package MVCObserver.mvc.slideViewController;

import MVCObserver.mvc.PresentationView;
import MVCObserver.mvc.SlideView;
import MVCObserver.mvc.SlideViewType;
import app.MainFrame;
import model.workspace.Slide;
import model.workspace.Slot;
import model.workspace.SlotType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SlideViewMouseListener implements MouseListener{

    private SlideView slideView;

    public SlideViewMouseListener(SlideView slideView) {
        this.slideView = slideView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(slideView.getSlideViewType().equals(SlideViewType.SLIDE_VIEW)){
            int X = e.getX();
            int Y = e.getY();
            Color color = MainFrame.getInstance().getActionManager().getChangeSlotColorAction().getCurrColor();

            int index = MainFrame.getInstance().getProjectView().getMyTabbedPane().getTabs().getSelectedIndex();
            PresentationView pw = MainFrame.getInstance().getProjectView().getMyTabbedPane().getPresentationsList().get(index);

            JComboBox jComboBox = pw.getEditSlidesJPanel().getToolbar().getjComboBox();
            SlotType slotType = SlotType.valueOf((String) jComboBox.getItemAt(jComboBox.getSelectedIndex()));
            Slot slot = new Slot(X,Y,color,slideView.getSlide(),slotType);

            int ticknes = Integer.valueOf((Integer) pw.getEditSlidesJPanel().getToolbar().getjSpinner().getValue());

            //postavljanje strok-a i debljine linije
            if(pw.getEditSlidesJPanel().getToolbar().getJrb2().isSelected()){
                Stroke dashed = new BasicStroke((float) ticknes, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                        0, new float[]{9}, 0);
                slot.setStroke(dashed);
            }
            else
                slot.setStroke(new BasicStroke(ticknes));

            pw.handleSlot(slot);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Slide slide = slideView.getSlide();

        SlideView miniSlideView = (SlideView) slide.getSubscribers().get(1);
        SlideView dummySlideView = (SlideView) slide.getSubscribers().get(2);

        Point prevPoint = e.getPoint();

        slide.setPrevPoint(prevPoint);

        int Xmini = (miniSlideView.getWidth() * prevPoint.x)/slideView.getWidth();
        int Ymini = (miniSlideView.getHeight() * prevPoint.y)/slideView.getHeight();

        Point miniPrevPoint = new Point(Xmini,Ymini);

        slideView.setPrevPoint(prevPoint);
        miniSlideView.setPrevPoint(miniPrevPoint);
        dummySlideView.setPrevPoint(prevPoint);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
