package shateq.inicere.api;

import shateq.inicere.impl.Action;

/**
 * Event handling, named Actions due to copyright
 */
public interface Worker {
    /**
     * Pass an Action Context to the emitter
     *
     * @param action Action context
     */
    void act(Action action);

    /**
     * Register an Action
     *
     * @param lambda Action implementation
     */
    void subscribe(FunctionalAction lambda);

    /**
     * Clear subscribed Actions
     */
    void unsubscribe();
}
