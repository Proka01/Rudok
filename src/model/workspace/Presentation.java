package model.workspace;

import MVCObserver.observer.DummyNotification;
import MVCObserver.observer.ISubscriber;
import MVCObserver.observer.NotificationStatus;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Presentation extends RuNodeComposite implements Serializable {

    private List<RuNode> slides = new ArrayList<>();
    private transient List<ISubscriber> subscribers = new ArrayList<>();
    private String authorName;
    private String URL;
    private transient Slot selectedSlot;
    private List<RuNode> sharedProjects = new ArrayList<>();
    private File presFile = null;

    public Presentation(RuNode parent, String name) {
        super(parent, name);
    }

    public void updatePerformed(){
        ((Project)this.getParent()).updatePerformed();
    }

    public void readResolve(){
        subscribers = new ArrayList<>();
    }

    @Override
    public void updateName(String name) {
        updatePerformed();
        setName(name);
        this.notifySubscribers(new DummyNotification((Project) this.getParent(),this, NotificationStatus.PRESENTATION_NAME_CHANGED));
    }

    public Presentation(String authorName, String URL) {
        this.authorName = authorName;
        this.URL = URL;
    }

    public Presentation(RuNode parent, String name, String authorName, String URL) {
        super(parent, name);
        this.authorName = authorName;
        this.URL = URL;
    }

    @Override
    public void addChild(RuNode node) {
        updatePerformed();
        if(slides.contains(node))
            return;
        slides.add((Slide)node);
        //TODO: ovde zvati notify
        //TODO: danas je bilo this.notifySubscribers(new Object());
        DummyNotification dn = new DummyNotification((Project)getParent(),this,NotificationStatus.SLIDE_ADDED);
        dn.setSlide((Slide) node);
        this.notifySubscribers(dn);
    }

    @Override
    public void removeChild(RuNode node) {
        updatePerformed();
        slides.remove((Slide)node);
        ((Slide)node).removeAllSubscribers();
        //TODO: ovde zvati notify
        //TODO: danas je bilo this.notifySubscribers(new Object());
        DummyNotification dn = new DummyNotification((Project)getParent(),this,NotificationStatus.SLIDE_REMOVED);
        dn.setSlide((Slide) node);
        this.notifySubscribers(dn);
    }

    public List<RuNode> getSlides() {
        return slides;
    }

    public void setSlides(List<RuNode> slides) {
        this.slides = slides;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
        String pom = "-";
        //this.notifySubscribers(pom);
        this.notifySubscribers(new DummyNotification(null,this,NotificationStatus.PRESENTATION_AUTHOR_NAME_CHANGED));
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
        //TODO: stavi umesto new object dummyNotification sa enumom BACKGROUND_CHANGED
        //TODO: danas je bilo this.notifySubscribers(new Object());
        this.notifySubscribers(new DummyNotification((Project)this.getParent(),this,NotificationStatus.PRESENTATION_BACKGROUND_CHANGED));
    }

    public void addSlide(Slide slide){
        this.slides.add(slide);
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        subscribers.add(sub);
        //TODO: ovde zvati notify
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        //TODO: ovde zvati notify
    }

    @Override
    public void notifySubscribers(Object notification) {
        for(ISubscriber sub : subscribers)
            sub.update(notification);
    }

    @Override
    public void removeAllSubscribers() {

    }

    public List<ISubscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<ISubscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public Slot getSelectedSlot() {
        return selectedSlot;
    }

    public void setSelectedSlot(Slot selectedSlot) {
        this.selectedSlot = selectedSlot;
    }

    public List<RuNode> getSharedProjects() {
        return sharedProjects;
    }

    public void setSharedProjects(List<RuNode> sharedProjects) {
        this.sharedProjects = sharedProjects;
    }

    public File getPresFile() {
        return presFile;
    }

    public void setPresFile(File presFile) {
        this.presFile = presFile;
    }
}
