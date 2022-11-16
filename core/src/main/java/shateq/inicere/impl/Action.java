package shateq.inicere.impl;

/**
 * Generic Action context.
 */
@SuppressWarnings("unused")
public class Action {
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

    /* TODO: Should, or shouldn't this be exposed? */
    public Object getValue() {
        return value;
    }

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
