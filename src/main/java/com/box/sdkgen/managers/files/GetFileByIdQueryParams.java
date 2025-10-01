package com.box.sdkgen.managers.files;

import java.util.List;

public class GetFileByIdQueryParams {

  /**
   * A comma-separated list of attributes to include in the response. This can be used to request
   * fields that are not normally returned in a standard response.
   *
   * <p>Be aware that specifying this parameter will have the effect that none of the standard
   * fields are returned in the response unless explicitly specified, instead only fields for the
   * mini representation are returned, additional to the fields requested.
   *
   * <p>Additionally this field can be used to query any metadata applied to the file by specifying
   * the `metadata` field as well as the scope and key of the template to retrieve, for example
   * `?fields=metadata.enterprise_12345.contractTemplate`.
   */
  public List<String> fields;

  public GetFileByIdQueryParams() {}

  protected GetFileByIdQueryParams(Builder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class Builder {

    protected List<String> fields;

    public Builder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetFileByIdQueryParams build() {
      return new GetFileByIdQueryParams(this);
    }
  }
}
