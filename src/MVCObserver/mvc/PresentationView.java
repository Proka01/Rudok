package MVCObserver.mvc;

import MVCObserver.observer.DummyNotification;
import MVCObserver.observer.ISubscriber;
import MVCObserver.observer.NotificationStatus;
import app.MainFrame;
import gui.EditSlidesJPanel;
import gui.SlideShowJPanel;
import gui.ToolbarPresentationView;
import model.workspace.*;
import slotState.SlotState;
import slotState.SlotStateManager;
import statePresentation.StateManager;

import javax.swing.*;
import java.awt.*;

public class PresentationView extends JPanel implements ISubscriber {

    /** atributi */
    private Presentation presentation;

    //njegov tabbed pane, koristim ga kad menjam ime nekog tabba iz tabbed pane-a
    private JTabbedPane parentTabbedPane;

    private SlideShowJPanel slideShowStatePanel;
    private EditSlidesJPanel editSlidesJPanel;
    private StateManager stateManager;
    private SlotStateManager slotStateManager;

    /** konstruktor i metoda za inicijalizaciju GUI-a */
    public PresentationView(Presentation presentation) {
        this.presentation = presentation;
        this.presentation.addSubscriber(this);
        this.setLayout(new BorderLayout());

        stateManager = new StateManager();
        slotStateManager = new SlotStateManager();
        slideShowStatePanel = new SlideShowJPanel();
        slideShowStatePanel.setSlideList(presentation.getSlides());
        editSlidesJPanel = new EditSlidesJPanel(presentation);

        this.add(editSlidesJPanel,BorderLayout.CENTER);
    }

    /** update metoda interfejsa Publisher*/
    @Override
    public void update(Object notification) {
        //initializeGUI();

        if(notification instanceof DummyNotification){
            DummyNotification dummy = (DummyNotification) notification;
            NotificationStatus notStatus= dummy.getStatus();

            if(notStatus.equals(NotificationStatus.SLIDE_ADDED) || notStatus.equals(NotificationStatus.SLIDE_REMOVED) || notStatus.equals(NotificationStatus.PRESENTATION_BACKGROUND_CHANGED)){
                editSlidesJPanel.initializePreviewAndSlideViews();
                //initializeGUI(); //TODO

                if(notStatus.equals(NotificationStatus.SLIDE_ADDED)){
                    SlideView sw = (SlideView) dummy.getSlide().getSubscribers().get(2);
                    slideShowStatePanel.getSlideShowCardLayoutPanel().add(sw);
                    if(slideShowStatePanel.getSlideList().size() > 0 && slideShowStatePanel.getIndexOfCurrentDisplayedSlide() == -1)
                        slideShowStatePanel.setIndexOfCurrentDisplayedSlide(0);
                }
                else if(notStatus.equals(NotificationStatus.SLIDE_REMOVED)){
                    SlideView sw = (SlideView) dummy.getSlide().getSubscribers().get(2);
                    slideShowStatePanel.getSlideShowCardLayoutPanel().remove(sw);
                    if(slideShowStatePanel.getSlideList().size() > 0 && slideShowStatePanel.getIndexOfCurrentDisplayedSlide() == -1)
                        slideShowStatePanel.setIndexOfCurrentDisplayedSlide(0);
                }
                else
                    slideShowStatePanel.reloadSlideViews();
            }
            else if(dummy.getStatus().equals(NotificationStatus.PRESENTATION_AUTHOR_NAME_CHANGED)){
                editSlidesJPanel.getAuthorNameJL().setText(presentation.getAuthorName());
                //authorNameJL.setText(presentation.getAuthorName()); //TODO: ovde ce biti editSlidePanel.getLBL. ...
            }
            else if(dummy.getProject().equals(MainFrame.getInstance().getProjectView().getProject())){
                //promena naziva taba u kome je prezentacija
                parentTabbedPane.setTitleAt(dummy.getPresentationIndex(),presentation.getName());
            }

        }
    }

    /** Mediatorski deo PresentationView-a za rad sa stanjima i stateManager-om */
    public void startEditPresentationState() {this.stateManager.setEditPresentationState();}
    public void startSlideShowState() {this.stateManager.setSlideShowState();}

    public void switchWindow(){
        this.stateManager.getCurrentState().switchWindow();
    }

    /** Mediatorski deo PresentationView-a za rad sa SlotState-om i SlotStateManager-om */
    public void startAddSlotState() {this.slotStateManager.setAddSlotState();}
    public void startRemoveSlotState() {this.slotStateManager.setRemoveSlotState();}
    public void startMoveSlotState() {this.slotStateManager.setMoveSlotState();}
    public void startSelectSlotState() {this.slotStateManager.setSelectSlotState();}

    public void handleSlot(Slot slot) {this.slotStateManager.getCurrentSlotState().handleSlot(slot);}

    /** getters and setters*/
    public void setPresentation(Presentation presentation){
        this.presentation = presentation;
    }

    public void setParentTabbedPane(JTabbedPane parentTabbedPane) {
        this.parentTabbedPane = parentTabbedPane;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public StateManager getStateManager() {
        return stateManager;
    }

    public SlideShowJPanel getSlideShowStatePanel() {
        return slideShowStatePanel;
    }

    public EditSlidesJPanel getEditSlidesJPanel() {
        return editSlidesJPanel;
    }
}
