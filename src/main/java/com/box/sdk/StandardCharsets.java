package com.box.sdk;

import java.nio.charset.Charset;

/**
 * Constant definitions for the standard Charsets.
 * <p>
 * NB: Replace with java.nio.charset.StandardCharsets when we drop 1.6 support.
 */
public final class StandardCharsets {

    /**
     * Eight-bit UCS Transformation Format.
     */
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    private StandardCharsets() {
        throw new UnsupportedOperationException();
    }
}
