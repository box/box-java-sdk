package com.box.sdkgen.managers.users;

public class DeleteUserByIdQueryParams {

  public Boolean notify;

  public Boolean force;

  public DeleteUserByIdQueryParams() {}

  protected DeleteUserByIdQueryParams(Builder builder) {
    this.notify = builder.notify;
    this.force = builder.force;
  }

  public Boolean getNotify() {
    return notify;
  }

  public Boolean getForce() {
    return force;
  }

  public static class Builder {

    protected Boolean notify;

    protected Boolean force;

    public Builder notify(Boolean notify) {
      this.notify = notify;
      return this;
    }

    public Builder force(Boolean force) {
      this.force = force;
      return this;
    }

    public DeleteUserByIdQueryParams build() {
      return new DeleteUserByIdQueryParams(this);
    }
  }
}
