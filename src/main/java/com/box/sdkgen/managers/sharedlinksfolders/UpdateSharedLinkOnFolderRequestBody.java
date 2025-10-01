package com.box.sdkgen.managers.sharedlinksfolders;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateSharedLinkOnFolderRequestBody extends SerializableObject {

  /** The settings for the shared link to update. */
  @JsonProperty("shared_link")
  protected UpdateSharedLinkOnFolderRequestBodySharedLinkField sharedLink;

  public UpdateSharedLinkOnFolderRequestBody() {
    super();
  }

  protected UpdateSharedLinkOnFolderRequestBody(Builder builder) {
    super();
    this.sharedLink = builder.sharedLink;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected UpdateSharedLinkOnFolderRequestBodySharedLinkField sharedLink;

    public Builder sharedLink(UpdateSharedLinkOnFolderRequestBodySharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    public UpdateSharedLinkOnFolderRequestBody build() {
      return new UpdateSharedLinkOnFolderRequestBody(this);
    }
  }
}
