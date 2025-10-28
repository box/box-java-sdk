package com.box.sdkgen.managers.enterpriseconfigurations;

import java.util.List;

public class GetEnterpriseConfigurationByIdV2025R0QueryParams {

  /**
   * A comma-separated list of the enterprise configuration categories. Allowed values: `security`,
   * `content_and_sharing`, `user_settings`, `shield`.
   */
  public final List<String> categories;

  public GetEnterpriseConfigurationByIdV2025R0QueryParams(List<String> categories) {
    this.categories = categories;
  }

  public List<String> getCategories() {
    return categories;
  }
}
