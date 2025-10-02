package com.box.sdkgen.managers.sharedlinksweblinks;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class RemoveSharedLinkFromWebLinkRequestBody extends SerializableObject {

  /** By setting this value to `null`, the shared link is removed from the web link. */
  @JsonProperty("shared_link")
  @Nullable
  protected RemoveSharedLinkFromWebLinkRequestBodySharedLinkField sharedLink;

  public RemoveSharedLinkFromWebLinkRequestBody() {
    super();
  }

  protected RemoveSharedLinkFromWebLinkRequestBody(Builder builder) {
    super();
    this.sharedLink = builder.sharedLink;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public RemoveSharedLinkFromWebLinkRequestBodySharedLinkField getSharedLink() {
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
    RemoveSharedLinkFromWebLinkRequestBody casted = (RemoveSharedLinkFromWebLinkRequestBody) o;
    return Objects.equals(sharedLink, casted.sharedLink);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sharedLink);
  }

  @Override
  public String toString() {
    return "RemoveSharedLinkFromWebLinkRequestBody{" + "sharedLink='" + sharedLink + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected RemoveSharedLinkFromWebLinkRequestBodySharedLinkField sharedLink;

    public Builder sharedLink(RemoveSharedLinkFromWebLinkRequestBodySharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      this.markNullableFieldAsSet("shared_link");
      return this;
    }

    public RemoveSharedLinkFromWebLinkRequestBody build() {
      return new RemoveSharedLinkFromWebLinkRequestBody(this);
    }
  }
}
