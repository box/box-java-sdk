package com.box.sdk;

/**
 * Specific, forced color for a signature.
 */
public enum BoxSignRequestSignatureColor {

    /**
     * Blue signature color.
     */
    Blue("blue"),

    /**
     * Black signature color.
     */
    Black("black"),

    /**
     * Red signature color.
     */
    Red("red");

    private final String jsonValue;

    BoxSignRequestSignatureColor(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    static BoxSignRequestSignatureColor fromJSONString(String jsonValue) {
        if ("blue".equals(jsonValue)) {
            return Blue;
        } else if ("black".equals(jsonValue)) {
            return Black;
        } else if ("red".equals(jsonValue)) {
            return Red;
        }
        throw new IllegalArgumentException("The provided JSON value isn't a valid "
            + "BoxSignRequestSignatureColor.");
    }
}
