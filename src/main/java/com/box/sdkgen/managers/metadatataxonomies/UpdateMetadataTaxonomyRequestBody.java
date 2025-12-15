package com.box.sdkgen.managers.metadatataxonomies;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateMetadataTaxonomyRequestBody extends SerializableObject {

  /** The display name of the taxonomy. */
  protected final String displayName;

  public UpdateMetadataTaxonomyRequestBody(@JsonProperty("displayName") String displayName) {
    super();
    this.displayName = displayName;
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
    UpdateMetadataTaxonomyRequestBody casted = (UpdateMetadataTaxonomyRequestBody) o;
    return Objects.equals(displayName, casted.displayName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(displayName);
  }

  @Override
  public String toString() {
    return "UpdateMetadataTaxonomyRequestBody{" + "displayName='" + displayName + '\'' + "}";
  }
}
