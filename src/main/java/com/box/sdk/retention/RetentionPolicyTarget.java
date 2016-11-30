package com.box.sdk.retention;

/**
 * The object on which a policy will be applied.
 */
public enum RetentionPolicyTarget {

    /**
     * the policy will be applied to a folder.
     */
    folder,

    /**
     * policy will be applied to a file version.
     */
    fileversion

}
