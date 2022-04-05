package errors;

import MVCObserver.observer.IPublisher;
import MVCObserver.observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;

public class ErrorFactory implements IPublisher {

    private static ErrorFactory instance;
    private List<ISubscriber> subscribers = new ArrayList<>();

    public ErrorFactory() {
    }

    public static ErrorFactory getInstance() {
        if(instance == null)
            instance = new ErrorFactory();

        return instance;
    }

    public void generateError(ErrorTypes errorType){
        if(errorType.equals(ErrorTypes.NO_PERMISSION_ERROR))
            notifySubscribers(new NoPermissionError());
        else if(errorType.equals(ErrorTypes.EMPTY_AUTHOR_NAME))
            notifySubscribers(new EmptyAuthorNameError());
        else if(errorType.equals(ErrorTypes.NOTHING_SELECTED_ERROR))
            notifySubscribers(new NothingSelectedError());
        else if(errorType.equals(ErrorTypes.INVALID_PICTURE_EXTENSION))
            notifySubscribers(new InvalidPictureExtensionError());
        else if(errorType.equals(ErrorTypes.WRONG_COMPONENT_SELECTED))
            notifySubscribers(new WrongComponentError());
        else if(errorType.equals(ErrorTypes.NO_OPENED_PRESENTATION))
            notifySubscribers(new NoOpenedPresentationError());
        else if(errorType.equals(ErrorTypes.INTERSECTING_SLOTS))
            notifySubscribers(new IntersectingSlotsError());
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        this.subscribers.remove(sub);
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
}
