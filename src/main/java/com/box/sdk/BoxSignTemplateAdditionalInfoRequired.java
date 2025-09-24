package com.box.sdk;

import java.util.List;

/** Box Sign Template additional information on which fields are required. */
public class BoxSignTemplateAdditionalInfoRequired {
  private final List<List<String>> signers;

  /**
   * Constructs a BoxSignTemplateAdditionalInfoRequired object with the provided list of signers.
   */
  public BoxSignTemplateAdditionalInfoRequired(List<List<String>> signers) {
    this.signers = signers;
  }

  /**
   * Gets the required signer fields.
   *
   * @return the required signer fields.
   */
  public List<List<String>> getSigners() {
    return this.signers;
  }
}
