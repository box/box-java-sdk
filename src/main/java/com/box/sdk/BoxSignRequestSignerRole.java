package com.box.sdk;

/**
 * Defines the role of the signer in the sign request. Signers will need to sign the document,
 * Approvers may just approve the document, and Subscribers will not need to take any actions
 * but will be notified of each action.
 * Finally, FinalCopyReader will only receive the finished sign request with a sign log.
 */
public enum BoxSignRequestSignerRole {

    /**
     * Signer role. Needs to sign the document.
     */
    Signer("signer"),

    /**
     * Approver role. Approves the document.
     */
    Approver("approver"),

    /**
     * Subscriber role. Notified of each action.
     */
    Subscriber("subscriber"),

    /**
     * Final copy reader role. Receives finished sign request with sign log.
     */
    FinalCopyReader("final_copy_reader");

    private final String jsonValue;

    private BoxSignRequestSignerRole(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    static BoxSignRequestSignerRole fromJSONString(String jsonValue) {
        if ("signer".equals(jsonValue)) {
            return Signer;
        } else if ("approver".equals(jsonValue)) {
            return Approver;
        } else if ("subscriber".equals(jsonValue)) {
            return Subscriber;
        } else if ("final_copy_reader".equals(jsonValue)) {
            return FinalCopyReader;
        }
        throw new IllegalArgumentException("The provided JSON value isn't a valid BoxSignRequestSignerRole.");
    }
}
