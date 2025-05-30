package com.box.sdkgen.managers.sharedlinksfolders;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class UpdateSharedLinkOnFolderRequestBody extends SerializableObject {

  @JsonProperty("shared_link")
  protected UpdateSharedLinkOnFolderRequestBodySharedLinkField sharedLink;

  public UpdateSharedLinkOnFolderRequestBody() {
    super();
  }

  protected UpdateSharedLinkOnFolderRequestBody(
      UpdateSharedLinkOnFolderRequestBodyBuilder builder) {
    super();
    this.sharedLink = builder.sharedLink;
  }

  public UpdateSharedLinkOnFolderRequestBodySharedLinkField getSharedLink() {
    return sharedLink;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateSharedLinkOnFolderRequestBody casted = (UpdateSharedLinkOnFolderRequestBody) o;
    return Objects.equals(sharedLink, casted.sharedLink);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sharedLink);
  }

  @Override
  public String toString() {
    return "UpdateSharedLinkOnFolderRequestBody{" + "sharedLink='" + sharedLink + '\'' + "}";
  }

  public static class UpdateSharedLinkOnFolderRequestBodyBuilder {

    protected UpdateSharedLinkOnFolderRequestBodySharedLinkField sharedLink;

    public UpdateSharedLinkOnFolderRequestBodyBuilder sharedLink(
        UpdateSharedLinkOnFolderRequestBodySharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    public UpdateSharedLinkOnFolderRequestBody build() {
      return new UpdateSharedLinkOnFolderRequestBody(this);
    }
  }
}
