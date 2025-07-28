package com.box.sdkgen.managers.sharedlinksfiles;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class RemoveSharedLinkFromFileRequestBody extends SerializableObject {

  @JsonProperty("shared_link")
  @Nullable
  protected RemoveSharedLinkFromFileRequestBodySharedLinkField sharedLink;

  public RemoveSharedLinkFromFileRequestBody() {
    super();
  }

  protected RemoveSharedLinkFromFileRequestBody(Builder builder) {
    super();
    this.sharedLink = builder.sharedLink;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected RemoveSharedLinkFromFileRequestBodySharedLinkField sharedLink;

    public Builder sharedLink(RemoveSharedLinkFromFileRequestBodySharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      this.markNullableFieldAsSet("shared_link");
      return this;
    }

    public RemoveSharedLinkFromFileRequestBody build() {
      return new RemoveSharedLinkFromFileRequestBody(this);
    }
  }
}
