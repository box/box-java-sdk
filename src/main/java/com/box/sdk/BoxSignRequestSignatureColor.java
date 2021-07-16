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

    private BoxSignRequestSignatureColor(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    static BoxSignRequestSignatureColor fromJSONString(String jsonValue) {
        if (jsonValue.equals("blue")) {
            return Blue;
        } else if (jsonValue.equals("black")) {
            return Black;
        } else if (jsonValue.equals("red")) {
            return Red;
        } else {
            throw new IllegalArgumentException("The provided JSON value isn't a valid signature color.");
        }
    }
}
