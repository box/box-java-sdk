package com.box.sdkgen.managers.sharedlinksfolders;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class RemoveSharedLinkFromFolderRequestBody extends SerializableObject {

  /** By setting this value to `null`, the shared link is removed from the folder. */
  @JsonProperty("shared_link")
  @Nullable
  protected RemoveSharedLinkFromFolderRequestBodySharedLinkField sharedLink;

  public RemoveSharedLinkFromFolderRequestBody() {
    super();
  }

  protected RemoveSharedLinkFromFolderRequestBody(Builder builder) {
    super();
    this.sharedLink = builder.sharedLink;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public RemoveSharedLinkFromFolderRequestBodySharedLinkField getSharedLink() {
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
    RemoveSharedLinkFromFolderRequestBody casted = (RemoveSharedLinkFromFolderRequestBody) o;
    return Objects.equals(sharedLink, casted.sharedLink);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sharedLink);
  }

  @Override
  public String toString() {
    return "RemoveSharedLinkFromFolderRequestBody{" + "sharedLink='" + sharedLink + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected RemoveSharedLinkFromFolderRequestBodySharedLinkField sharedLink;

    public Builder sharedLink(RemoveSharedLinkFromFolderRequestBodySharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      this.markNullableFieldAsSet("shared_link");
      return this;
    }

    public RemoveSharedLinkFromFolderRequestBody build() {
      return new RemoveSharedLinkFromFolderRequestBody(this);
    }
  }
}
