package shateq.inicere.api;

import shateq.inicere.impl.Action;

/**
 * Event handling
 */
public interface Worker {
    void act(Action action);

    void subscribe(FunctionalAction lambda);
}
