package com.box.sdkgen.managers.folders;

public class DeleteFolderByIdQueryParams {

  /**
   * Delete a folder that is not empty by recursively deleting the folder and all of its content.
   */
  public Boolean recursive;

  public DeleteFolderByIdQueryParams() {}

  protected DeleteFolderByIdQueryParams(Builder builder) {
    this.recursive = builder.recursive;
  }

  public Boolean getRecursive() {
    return recursive;
  }

  public static class Builder {

    protected Boolean recursive;

    public Builder recursive(Boolean recursive) {
      this.recursive = recursive;
      return this;
    }

    public DeleteFolderByIdQueryParams build() {
      return new DeleteFolderByIdQueryParams(this);
    }
  }
}
