package com.box.sdk;

import java.util.List;

/**
 * Box Sign Template additional information on which fields are required and which fields are not
 * editable.
 */
public class BoxSignTemplateAdditionalInfo {
  private final List<String> nonEditable;
  private final BoxSignTemplateAdditionalInfoRequired required;

  public BoxSignTemplateAdditionalInfo(
      List<String> nonEditable, BoxSignTemplateAdditionalInfoRequired required) {
    this.nonEditable = nonEditable;
    this.required = required;
  }

  /**
   * Get non-editable fields.
   *
   * @return list of non-editable fields.\
   */
  public List<String> getNonEditable() {
    return this.nonEditable;
  }

  /**
   * Gets the required fields.
   *
   * @return the required fields.
   */
  public BoxSignTemplateAdditionalInfoRequired getRequired() {
    return this.required;
  }
}
