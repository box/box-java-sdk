package com.box.sdkgen.managers.sharedlinksweblinks;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateSharedLinkOnWebLinkRequestBody extends SerializableObject {

  @JsonProperty("shared_link")
  protected UpdateSharedLinkOnWebLinkRequestBodySharedLinkField sharedLink;

  public UpdateSharedLinkOnWebLinkRequestBody() {
    super();
  }

  protected UpdateSharedLinkOnWebLinkRequestBody(Builder builder) {
    super();
    this.sharedLink = builder.sharedLink;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected UpdateSharedLinkOnWebLinkRequestBodySharedLinkField sharedLink;

    public Builder sharedLink(UpdateSharedLinkOnWebLinkRequestBodySharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    public UpdateSharedLinkOnWebLinkRequestBody build() {
      return new UpdateSharedLinkOnWebLinkRequestBody(this);
    }
  }
}
