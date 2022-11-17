package shateq.inicere.impl;

import org.jetbrains.annotations.Nullable;
import shateq.inicere.api.ActionBreed;

/**
 * Value-protected action context
 * value passed inside the action (shouldn't be there)
 *
 * @param type  Config action type
 * @param key   Key used inside config action
 */
public record Action(ActionBreed type, String filename, @Nullable String key) {
}