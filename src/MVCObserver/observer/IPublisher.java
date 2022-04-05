package MVCObserver.observer;

import java.io.Serializable;

public interface IPublisher extends Serializable{
    void addSubscriber(ISubscriber sub);
    void removeSubscriber(ISubscriber sub);
    void notifySubscribers(Object notification);
    void removeAllSubscribers();
}
