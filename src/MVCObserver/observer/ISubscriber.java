package MVCObserver.observer;

import java.io.Serializable;

public interface ISubscriber{
    void update(Object notification);
}
