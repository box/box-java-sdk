package com.box.sdkgen.managers.metadatataxonomies;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateMetadataTaxonomyNodeRequestBody extends SerializableObject {

  /** The display name of the taxonomy node. */
  protected String displayName;

  public UpdateMetadataTaxonomyNodeRequestBody() {
    super();
  }

  protected UpdateMetadataTaxonomyNodeRequestBody(Builder builder) {
    super();
    this.displayName = builder.displayName;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getDisplayName() {
    return displayName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateMetadataTaxonomyNodeRequestBody casted = (UpdateMetadataTaxonomyNodeRequestBody) o;
    return Objects.equals(displayName, casted.displayName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(displayName);
  }

  @Override
  public String toString() {
    return "UpdateMetadataTaxonomyNodeRequestBody{" + "displayName='" + displayName + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String displayName;

    public Builder displayName(String displayName) {
      this.displayName = displayName;
      return this;
    }

    public UpdateMetadataTaxonomyNodeRequestBody build() {
      return new UpdateMetadataTaxonomyNodeRequestBody(this);
    }
  }
}
