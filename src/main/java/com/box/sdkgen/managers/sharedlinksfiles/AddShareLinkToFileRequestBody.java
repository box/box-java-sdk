package com.box.sdkgen.managers.sharedlinksfiles;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class AddShareLinkToFileRequestBody extends SerializableObject {

  @JsonProperty("shared_link")
  protected AddShareLinkToFileRequestBodySharedLinkField sharedLink;

  public AddShareLinkToFileRequestBody() {
    super();
  }

  protected AddShareLinkToFileRequestBody(AddShareLinkToFileRequestBodyBuilder builder) {
    super();
    this.sharedLink = builder.sharedLink;
  }

  public AddShareLinkToFileRequestBodySharedLinkField getSharedLink() {
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
    AddShareLinkToFileRequestBody casted = (AddShareLinkToFileRequestBody) o;
    return Objects.equals(sharedLink, casted.sharedLink);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sharedLink);
  }

  @Override
  public String toString() {
    return "AddShareLinkToFileRequestBody{" + "sharedLink='" + sharedLink + '\'' + "}";
  }

  public static class AddShareLinkToFileRequestBodyBuilder {

    protected AddShareLinkToFileRequestBodySharedLinkField sharedLink;

    public AddShareLinkToFileRequestBodyBuilder sharedLink(
        AddShareLinkToFileRequestBodySharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    public AddShareLinkToFileRequestBody build() {
      return new AddShareLinkToFileRequestBody(this);
    }
  }
}
