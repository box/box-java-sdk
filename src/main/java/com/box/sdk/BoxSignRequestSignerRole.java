package com.box.sdk;

/**
 * Defines the role of the signer in the sign request. Signers will need to sign the document,
 * Approvers may just approve the document. Finally, FinalCopyReader will only receive the finished
 * sign request with a sign log.
 */
public enum BoxSignRequestSignerRole {

  /** Signer role. Needs to sign the document. */
  Signer("signer"),

  /** Approver role. Approves the document. */
  Approver("approver"),

  /** Final copy reader role. Receives finished sign request with sign log. */
  FinalCopyReader("final_copy_reader");

  private final String jsonValue;

  BoxSignRequestSignerRole(String jsonValue) {
    this.jsonValue = jsonValue;
  }

  static BoxSignRequestSignerRole fromJSONString(String jsonValue) {
    if ("signer".equals(jsonValue)) {
      return Signer;
    } else if ("approver".equals(jsonValue)) {
      return Approver;
    } else if ("final_copy_reader".equals(jsonValue)) {
      return FinalCopyReader;
    }
    throw new IllegalArgumentException(
        "The provided JSON value isn't a valid BoxSignRequestSignerRole.");
  }
}
