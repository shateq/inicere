package shateq.java.inicere.api;

import java.io.IOException;

public class ConfigurationException extends IOException {
    public ConfigurationException(String message) {
        super(message, new IOException());
    }
}
