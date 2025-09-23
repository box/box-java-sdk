package com.box.sdkgen.managers.invites;

import java.util.List;

public class CreateInviteQueryParams {

  public List<String> fields;

  public CreateInviteQueryParams() {}

  protected CreateInviteQueryParams(Builder builder) {
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

    public CreateInviteQueryParams build() {
      return new CreateInviteQueryParams(this);
    }
  }
}
