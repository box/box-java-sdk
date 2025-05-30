package com.box.sdkgen.managers.sharedlinksfolders;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class AddShareLinkToFolderRequestBody extends SerializableObject {

  @JsonProperty("shared_link")
  protected AddShareLinkToFolderRequestBodySharedLinkField sharedLink;

  public AddShareLinkToFolderRequestBody() {
    super();
  }

  protected AddShareLinkToFolderRequestBody(AddShareLinkToFolderRequestBodyBuilder builder) {
    super();
    this.sharedLink = builder.sharedLink;
  }

  public AddShareLinkToFolderRequestBodySharedLinkField getSharedLink() {
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
    AddShareLinkToFolderRequestBody casted = (AddShareLinkToFolderRequestBody) o;
    return Objects.equals(sharedLink, casted.sharedLink);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sharedLink);
  }

  @Override
  public String toString() {
    return "AddShareLinkToFolderRequestBody{" + "sharedLink='" + sharedLink + '\'' + "}";
  }

  public static class AddShareLinkToFolderRequestBodyBuilder {

    protected AddShareLinkToFolderRequestBodySharedLinkField sharedLink;

    public AddShareLinkToFolderRequestBodyBuilder sharedLink(
        AddShareLinkToFolderRequestBodySharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    public AddShareLinkToFolderRequestBody build() {
      return new AddShareLinkToFolderRequestBody(this);
    }
  }
}
