package com.box.sdkgen.managers.sharedlinksfiles;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateSharedLinkOnFileRequestBody extends SerializableObject {

  @JsonProperty("shared_link")
  protected UpdateSharedLinkOnFileRequestBodySharedLinkField sharedLink;

  public UpdateSharedLinkOnFileRequestBody() {
    super();
  }

  protected UpdateSharedLinkOnFileRequestBody(Builder builder) {
    super();
    this.sharedLink = builder.sharedLink;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public UpdateSharedLinkOnFileRequestBodySharedLinkField getSharedLink() {
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
    UpdateSharedLinkOnFileRequestBody casted = (UpdateSharedLinkOnFileRequestBody) o;
    return Objects.equals(sharedLink, casted.sharedLink);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sharedLink);
  }

  @Override
  public String toString() {
    return "UpdateSharedLinkOnFileRequestBody{" + "sharedLink='" + sharedLink + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected UpdateSharedLinkOnFileRequestBodySharedLinkField sharedLink;

    public Builder sharedLink(UpdateSharedLinkOnFileRequestBodySharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    public UpdateSharedLinkOnFileRequestBody build() {
      return new UpdateSharedLinkOnFileRequestBody(this);
    }
  }
}
