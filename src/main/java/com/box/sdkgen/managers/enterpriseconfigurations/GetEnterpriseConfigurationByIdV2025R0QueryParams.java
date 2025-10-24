package com.box.sdkgen.managers.enterpriseconfigurations;

public class GetEnterpriseConfigurationByIdV2025R0QueryParams {

  /**
   * The comma-delimited list of the enterprise configuration categories. Allowed values:
   * `security`, `content_and_sharing`, `user_settings`, `shield`.
   */
  public final String categories;

  public GetEnterpriseConfigurationByIdV2025R0QueryParams(String categories) {
    this.categories = categories;
  }

  public String getCategories() {
    return categories;
  }
}
