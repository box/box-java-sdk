package com.box.sdkgen.managers.trashedweblinks;

import java.util.List;

public class GetTrashedWebLinkByIdQueryParams {

  public List<String> fields;

  public GetTrashedWebLinkByIdQueryParams() {}

  protected GetTrashedWebLinkByIdQueryParams(GetTrashedWebLinkByIdQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class GetTrashedWebLinkByIdQueryParamsBuilder {

    protected List<String> fields;

    public GetTrashedWebLinkByIdQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetTrashedWebLinkByIdQueryParams build() {
      return new GetTrashedWebLinkByIdQueryParams(this);
    }
  }
}
