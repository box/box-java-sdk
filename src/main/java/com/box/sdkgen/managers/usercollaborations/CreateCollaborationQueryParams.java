package com.box.sdkgen.managers.usercollaborations;

import java.util.List;

public class CreateCollaborationQueryParams {

  public List<String> fields;

  public Boolean notify;

  public CreateCollaborationQueryParams() {}

  protected CreateCollaborationQueryParams(Builder builder) {
    this.fields = builder.fields;
    this.notify = builder.notify;
  }

  public List<String> getFields() {
    return fields;
  }

  public Boolean getNotify() {
    return notify;
  }

  public static class Builder {

    protected List<String> fields;

    protected Boolean notify;

    public Builder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public Builder notify(Boolean notify) {
      this.notify = notify;
      return this;
    }

    public CreateCollaborationQueryParams build() {
      return new CreateCollaborationQueryParams(this);
    }
  }
}
