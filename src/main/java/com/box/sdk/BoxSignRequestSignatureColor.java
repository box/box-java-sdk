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
        switch (jsonValue) {
            case "blue":
                return Blue;
            case "black":
                return Black;
            case "red":
                return Red;
            default:
                throw new IllegalArgumentException("The provided JSON value isn't a valid "
                        + "BoxSignRequestSignatureColor.");
        }
    }
}
