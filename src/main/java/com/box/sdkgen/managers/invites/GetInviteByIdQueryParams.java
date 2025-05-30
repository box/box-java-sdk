package com.box.sdkgen.managers.invites;

import java.util.List;

public class GetInviteByIdQueryParams {

  public List<String> fields;

  public GetInviteByIdQueryParams() {}

  protected GetInviteByIdQueryParams(GetInviteByIdQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class GetInviteByIdQueryParamsBuilder {

    protected List<String> fields;

    public GetInviteByIdQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetInviteByIdQueryParams build() {
      return new GetInviteByIdQueryParams(this);
    }
  }
}
