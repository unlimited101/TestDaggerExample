package de.xappo.presenterinjection.base;

/**
 * Created by knoppik on 11.11.16.
 */

public final class Preconditions {
    private Preconditions() {
    }


    public static void checkState(final boolean condition, final String message, Object... args) {
        if (!condition) {
            throw new IllegalStateException(formatMessage(message, args));
        }
    }

    public static String formatMessage(String message, Object... args) {
        return String.format(message, args);
    }
}
