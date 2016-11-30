package com.box.sdk.retention;

/**
 * The type of Retention Policy.
 */
public enum RetentionPolicyType {
    /**
     * No end date for policy.
     */
    indefinite,

    /**
     * There is a terminal date for the policy.
     */
    finite;
}
