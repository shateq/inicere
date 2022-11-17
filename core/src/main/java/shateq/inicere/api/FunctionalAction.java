package shateq.inicere.api;

import shateq.inicere.impl.Action;

@FunctionalInterface
public interface FunctionalAction {
    void proceed(Action action);
}