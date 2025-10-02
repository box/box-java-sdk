package com.box.sdkgen.managers.folderlocks;

public class GetFolderLocksQueryParams {

  /**
   * The unique identifier that represent a folder.
   *
   * <p>The ID for any folder can be determined by visiting this folder in the web application and
   * copying the ID from the URL. For example, for the URL `https://*.app.box.com/folder/123` the
   * `folder_id` is `123`.
   *
   * <p>The root folder of a Box account is always represented by the ID `0`.
   */
  public final String folderId;

  public GetFolderLocksQueryParams(String folderId) {
    this.folderId = folderId;
  }

  public String getFolderId() {
    return folderId;
  }
}
