package shateq.inicere.impl;

import org.jetbrains.annotations.Nullable;
import shateq.inicere.api.ActionBreed;

/**
 * Action Context
 * ASAP (As safe as possible)
 *
 * @param type Config action type
 * @param key  Key used inside config action
 */
public record Action(ActionBreed type, String filename, @Nullable String key) {
}
