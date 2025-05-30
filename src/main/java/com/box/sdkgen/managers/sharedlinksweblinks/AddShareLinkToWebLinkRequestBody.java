package com.box.sdkgen.managers.sharedlinksweblinks;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class AddShareLinkToWebLinkRequestBody extends SerializableObject {

  @JsonProperty("shared_link")
  protected AddShareLinkToWebLinkRequestBodySharedLinkField sharedLink;

  public AddShareLinkToWebLinkRequestBody() {
    super();
  }

  protected AddShareLinkToWebLinkRequestBody(AddShareLinkToWebLinkRequestBodyBuilder builder) {
    super();
    this.sharedLink = builder.sharedLink;
  }

  public AddShareLinkToWebLinkRequestBodySharedLinkField getSharedLink() {
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
    AddShareLinkToWebLinkRequestBody casted = (AddShareLinkToWebLinkRequestBody) o;
    return Objects.equals(sharedLink, casted.sharedLink);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sharedLink);
  }

  @Override
  public String toString() {
    return "AddShareLinkToWebLinkRequestBody{" + "sharedLink='" + sharedLink + '\'' + "}";
  }

  public static class AddShareLinkToWebLinkRequestBodyBuilder {

    protected AddShareLinkToWebLinkRequestBodySharedLinkField sharedLink;

    public AddShareLinkToWebLinkRequestBodyBuilder sharedLink(
        AddShareLinkToWebLinkRequestBodySharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    public AddShareLinkToWebLinkRequestBody build() {
      return new AddShareLinkToWebLinkRequestBody(this);
    }
  }
}
