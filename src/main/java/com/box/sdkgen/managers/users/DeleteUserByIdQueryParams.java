package com.box.sdkgen.managers.users;

public class DeleteUserByIdQueryParams {

  /** Whether the user will receive email notification of the deletion. */
  public Boolean notify;

  /** Whether the user should be deleted even if this user still own files. */
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
