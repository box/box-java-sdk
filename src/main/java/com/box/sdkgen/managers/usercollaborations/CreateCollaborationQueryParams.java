package com.box.sdkgen.managers.usercollaborations;

import java.util.List;

public class CreateCollaborationQueryParams {

  public List<String> fields;

  public Boolean notify;

  public CreateCollaborationQueryParams() {}

  protected CreateCollaborationQueryParams(CreateCollaborationQueryParamsBuilder builder) {
    this.fields = builder.fields;
    this.notify = builder.notify;
  }

  public List<String> getFields() {
    return fields;
  }

  public Boolean getNotify() {
    return notify;
  }

  public static class CreateCollaborationQueryParamsBuilder {

    protected List<String> fields;

    protected Boolean notify;

    public CreateCollaborationQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public CreateCollaborationQueryParamsBuilder notify(Boolean notify) {
      this.notify = notify;
      return this;
    }

    public CreateCollaborationQueryParams build() {
      return new CreateCollaborationQueryParams(this);
    }
  }
}
