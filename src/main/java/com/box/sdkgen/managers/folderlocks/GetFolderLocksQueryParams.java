package com.box.sdkgen.managers.folderlocks;

public class GetFolderLocksQueryParams {

  public final String folderId;

  public GetFolderLocksQueryParams(String folderId) {
    this.folderId = folderId;
  }

  public String getFolderId() {
    return folderId;
  }
}
