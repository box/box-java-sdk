package com.box.sdkgen.schemas.metadataqueryindex;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class MetadataQueryIndexFieldsField extends SerializableObject {

  protected String key;

  @JsonDeserialize(
      using =
          MetadataQueryIndexFieldsSortDirectionField
              .MetadataQueryIndexFieldsSortDirectionFieldDeserializer.class)
  @JsonSerialize(
      using =
          MetadataQueryIndexFieldsSortDirectionField
              .MetadataQueryIndexFieldsSortDirectionFieldSerializer.class)
  @JsonProperty("sort_direction")
  protected EnumWrapper<MetadataQueryIndexFieldsSortDirectionField> sortDirection;

  public MetadataQueryIndexFieldsField() {
    super();
  }

  protected MetadataQueryIndexFieldsField(MetadataQueryIndexFieldsFieldBuilder builder) {
    super();
    this.key = builder.key;
    this.sortDirection = builder.sortDirection;
  }

  public String getKey() {
    return key;
  }

  public EnumWrapper<MetadataQueryIndexFieldsSortDirectionField> getSortDirection() {
    return sortDirection;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataQueryIndexFieldsField casted = (MetadataQueryIndexFieldsField) o;
    return Objects.equals(key, casted.key) && Objects.equals(sortDirection, casted.sortDirection);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, sortDirection);
  }

  @Override
  public String toString() {
    return "MetadataQueryIndexFieldsField{"
        + "key='"
        + key
        + '\''
        + ", "
        + "sortDirection='"
        + sortDirection
        + '\''
        + "}";
  }

  public static class MetadataQueryIndexFieldsFieldBuilder {

    protected String key;

    protected EnumWrapper<MetadataQueryIndexFieldsSortDirectionField> sortDirection;

    public MetadataQueryIndexFieldsFieldBuilder key(String key) {
      this.key = key;
      return this;
    }

    public MetadataQueryIndexFieldsFieldBuilder sortDirection(
        MetadataQueryIndexFieldsSortDirectionField sortDirection) {
      this.sortDirection =
          new EnumWrapper<MetadataQueryIndexFieldsSortDirectionField>(sortDirection);
      return this;
    }

    public MetadataQueryIndexFieldsFieldBuilder sortDirection(
        EnumWrapper<MetadataQueryIndexFieldsSortDirectionField> sortDirection) {
      this.sortDirection = sortDirection;
      return this;
    }

    public MetadataQueryIndexFieldsField build() {
      return new MetadataQueryIndexFieldsField(this);
    }
  }
}
