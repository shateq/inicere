package shateq.java.inicere.api;

/**
 * Action event factors.
 */
@SuppressWarnings("unused")
public class Action {
    /*private boolean cancelled = false; TODO: how reading an entry could be cancelled? */
    private final Object value;
    private final String key;
    private final Sort type;

    public Action(Sort type) {
        this.type = type;
        this.key = null;
        this.value = null;
    }

    public Action(String key, Object value, Sort type) {
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

    public Sort getType() {
        return type;
    }

    public enum Sort {
        SET, GET, DELETE, KILL, DEFAULT
    }
}
