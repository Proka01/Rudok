package MVCObserver.observer;

import MVCObserver.mvc.PresentationView;
import model.workspace.*;

public class DummyNotification {

    private Project project;
    private Presentation presentation;
    private Slide slide;
    private Slot slot;
    private NotificationStatus status;

    public DummyNotification(Project project, Presentation presentation, NotificationStatus status) {
        this.project = project;
        this.presentation = presentation;
        this.status = status;
    }

    public DummyNotification(Slot slot, NotificationStatus status) {
        this.slot = slot;
        this.status = status;
    }

    public DummyNotification(NotificationStatus status) {
        this.status = status;
    }

    public int getPresentationIndex(){
        int br = 0;
        for(RuNode p : project.getPresentations()){
            if(p instanceof Presentation)
                if(presentation.equals(p))
                    return br;
            br++;
        }

        return -1;
    }

    public Project getProject() {
        return project;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public PresentationView getPresentationView(){
        return (PresentationView) presentation.getSubscribers().get(0);
    }

    public Slide getSlide() {
        return slide;
    }

    public void setSlide(Slide slide) {
        this.slide = slide;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }
}
