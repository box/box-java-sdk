package com.box.sdkgen.managers.users;

public class DeleteUserByIdQueryParams {

  /** Whether the user will receive email notification of the deletion. */
  public Boolean notify;

  /**
   * Specifies whether to delete the user even if they still own files.
   *
   * <p>If the user owns content and the enterprise has a retention policy, including a retired one,
   * or a legal hold that is not fully released, setting `force` to `true` doesn't delete the user.
   * The request returns a `403` error, and you must transfer the user's content before deleting the
   * user.
   */
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
