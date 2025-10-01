package com.box.sdkgen.schemas.metadataquery;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class MetadataQueryOrderByField extends SerializableObject {

  /**
   * The metadata template field to order by.
   *
   * <p>The `field_key` represents the `key` value of a field from the metadata template being
   * searched for.
   */
  @JsonProperty("field_key")
  protected String fieldKey;

  /**
   * The direction to order by, either ascending or descending.
   *
   * <p>The `ordering` direction must be the same for each item in the array.
   */
  @JsonDeserialize(
      using =
          MetadataQueryOrderByDirectionField.MetadataQueryOrderByDirectionFieldDeserializer.class)
  @JsonSerialize(
      using = MetadataQueryOrderByDirectionField.MetadataQueryOrderByDirectionFieldSerializer.class)
  protected EnumWrapper<MetadataQueryOrderByDirectionField> direction;

  public MetadataQueryOrderByField() {
    super();
  }

  protected MetadataQueryOrderByField(Builder builder) {
    super();
    this.fieldKey = builder.fieldKey;
    this.direction = builder.direction;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getFieldKey() {
    return fieldKey;
  }

  public EnumWrapper<MetadataQueryOrderByDirectionField> getDirection() {
    return direction;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataQueryOrderByField casted = (MetadataQueryOrderByField) o;
    return Objects.equals(fieldKey, casted.fieldKey) && Objects.equals(direction, casted.direction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fieldKey, direction);
  }

  @Override
  public String toString() {
    return "MetadataQueryOrderByField{"
        + "fieldKey='"
        + fieldKey
        + '\''
        + ", "
        + "direction='"
        + direction
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String fieldKey;

    protected EnumWrapper<MetadataQueryOrderByDirectionField> direction;

    public Builder fieldKey(String fieldKey) {
      this.fieldKey = fieldKey;
      return this;
    }

    public Builder direction(MetadataQueryOrderByDirectionField direction) {
      this.direction = new EnumWrapper<MetadataQueryOrderByDirectionField>(direction);
      return this;
    }

    public Builder direction(EnumWrapper<MetadataQueryOrderByDirectionField> direction) {
      this.direction = direction;
      return this;
    }

    public MetadataQueryOrderByField build() {
      return new MetadataQueryOrderByField(this);
    }
  }
}
