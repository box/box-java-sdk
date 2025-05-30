package com.box.sdkgen.managers.sharedlinksweblinks;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class UpdateSharedLinkOnWebLinkRequestBody extends SerializableObject {

  @JsonProperty("shared_link")
  protected UpdateSharedLinkOnWebLinkRequestBodySharedLinkField sharedLink;

  public UpdateSharedLinkOnWebLinkRequestBody() {
    super();
  }

  protected UpdateSharedLinkOnWebLinkRequestBody(
      UpdateSharedLinkOnWebLinkRequestBodyBuilder builder) {
    super();
    this.sharedLink = builder.sharedLink;
  }

  public UpdateSharedLinkOnWebLinkRequestBodySharedLinkField getSharedLink() {
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
    UpdateSharedLinkOnWebLinkRequestBody casted = (UpdateSharedLinkOnWebLinkRequestBody) o;
    return Objects.equals(sharedLink, casted.sharedLink);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sharedLink);
  }

  @Override
  public String toString() {
    return "UpdateSharedLinkOnWebLinkRequestBody{" + "sharedLink='" + sharedLink + '\'' + "}";
  }

  public static class UpdateSharedLinkOnWebLinkRequestBodyBuilder {

    protected UpdateSharedLinkOnWebLinkRequestBodySharedLinkField sharedLink;

    public UpdateSharedLinkOnWebLinkRequestBodyBuilder sharedLink(
        UpdateSharedLinkOnWebLinkRequestBodySharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    public UpdateSharedLinkOnWebLinkRequestBody build() {
      return new UpdateSharedLinkOnWebLinkRequestBody(this);
    }
  }
}
