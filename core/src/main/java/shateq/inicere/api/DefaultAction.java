package shateq.inicere.api;

import shateq.inicere.impl.Action;

@FunctionalInterface
public interface DefaultAction {
    void proceed(Action action);
}