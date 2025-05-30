package com.box.sdkgen.managers.sharedlinksfiles;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class RemoveSharedLinkFromFileRequestBody extends SerializableObject {

  @JsonProperty("shared_link")
  protected RemoveSharedLinkFromFileRequestBodySharedLinkField sharedLink;

  public RemoveSharedLinkFromFileRequestBody() {
    super();
  }

  protected RemoveSharedLinkFromFileRequestBody(
      RemoveSharedLinkFromFileRequestBodyBuilder builder) {
    super();
    this.sharedLink = builder.sharedLink;
  }

  public RemoveSharedLinkFromFileRequestBodySharedLinkField getSharedLink() {
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
    RemoveSharedLinkFromFileRequestBody casted = (RemoveSharedLinkFromFileRequestBody) o;
    return Objects.equals(sharedLink, casted.sharedLink);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sharedLink);
  }

  @Override
  public String toString() {
    return "RemoveSharedLinkFromFileRequestBody{" + "sharedLink='" + sharedLink + '\'' + "}";
  }

  public static class RemoveSharedLinkFromFileRequestBodyBuilder {

    protected RemoveSharedLinkFromFileRequestBodySharedLinkField sharedLink;

    public RemoveSharedLinkFromFileRequestBodyBuilder sharedLink(
        RemoveSharedLinkFromFileRequestBodySharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    public RemoveSharedLinkFromFileRequestBody build() {
      return new RemoveSharedLinkFromFileRequestBody(this);
    }
  }
}
