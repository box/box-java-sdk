package com.box.sdkgen.managers.sharedlinksweblinks;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class AddShareLinkToWebLinkRequestBody extends SerializableObject {

  /**
   * The settings for the shared link to create on the web link.
   *
   * <p>Use an empty object (`{}`) to use the default settings for shared links.
   */
  @JsonProperty("shared_link")
  protected AddShareLinkToWebLinkRequestBodySharedLinkField sharedLink;

  public AddShareLinkToWebLinkRequestBody() {
    super();
  }

  protected AddShareLinkToWebLinkRequestBody(Builder builder) {
    super();
    this.sharedLink = builder.sharedLink;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected AddShareLinkToWebLinkRequestBodySharedLinkField sharedLink;

    public Builder sharedLink(AddShareLinkToWebLinkRequestBodySharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    public AddShareLinkToWebLinkRequestBody build() {
      return new AddShareLinkToWebLinkRequestBody(this);
    }
  }
}
