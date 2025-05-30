package com.box.sdkgen.managers.invites;

import java.util.List;

public class CreateInviteQueryParams {

  public List<String> fields;

  public CreateInviteQueryParams() {}

  protected CreateInviteQueryParams(CreateInviteQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class CreateInviteQueryParamsBuilder {

    protected List<String> fields;

    public CreateInviteQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public CreateInviteQueryParams build() {
      return new CreateInviteQueryParams(this);
    }
  }
}
