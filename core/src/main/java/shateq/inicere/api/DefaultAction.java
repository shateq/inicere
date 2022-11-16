package shateq.inicere.api;

@FunctionalInterface
public interface DefaultAction {
    void proceed(Action action);
}