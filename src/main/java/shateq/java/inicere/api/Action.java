package shateq.java.inicere.api;

/**
 * Action event factors.
 */
@SuppressWarnings("unused")
public class Action {
    /*private boolean cancelled = false; TODO: how reading an entry could be cancelled? */
    private final Object value;
    private final String key;
    private final Type type;

    public Action(Type type) {
        this.type = type;
        this.key = null;
        this.value = null;
    }

    public Action(String key, Object value, Type type) {
        this.key = key;
        this.value = value;
        this.type = type;
    }

    /* Should, or shouldn't this be exposed? TODO: rethink */
    public Object getValue() {
        return value;
    }

    /*public void cancel() {
        cancelled = true;
    }
    public boolean wasCancelled() {
        return cancelled;
    }*/

    public String getKey() {
        return key;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        SET, GET, DELETE, KILL, DEFAULT
    }
}
