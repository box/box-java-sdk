package com.box.sdkgen.managers.users;

public class DeleteUserByIdQueryParams {

  public Boolean notify;

  public Boolean force;

  public DeleteUserByIdQueryParams() {}

  protected DeleteUserByIdQueryParams(DeleteUserByIdQueryParamsBuilder builder) {
    this.notify = builder.notify;
    this.force = builder.force;
  }

  public Boolean getNotify() {
    return notify;
  }

  public Boolean getForce() {
    return force;
  }

  public static class DeleteUserByIdQueryParamsBuilder {

    protected Boolean notify;

    protected Boolean force;

    public DeleteUserByIdQueryParamsBuilder notify(Boolean notify) {
      this.notify = notify;
      return this;
    }

    public DeleteUserByIdQueryParamsBuilder force(Boolean force) {
      this.force = force;
      return this;
    }

    public DeleteUserByIdQueryParams build() {
      return new DeleteUserByIdQueryParams(this);
    }
  }
}
