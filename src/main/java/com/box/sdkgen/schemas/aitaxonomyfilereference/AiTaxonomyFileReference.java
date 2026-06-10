package com.box.sdkgen.schemas.aitaxonomyfilereference;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * A taxonomy `.csv` file to be used for the structured extraction. For your request to work,
 * `fields` must also be provided.
 */
@JsonFilter("nullablePropertyFilter")
public class AiTaxonomyFileReference extends SerializableObject {

  /** The type of the taxonomy source. */
  @JsonDeserialize(
      using = AiTaxonomyFileReferenceTypeField.AiTaxonomyFileReferenceTypeFieldDeserializer.class)
  @JsonSerialize(
      using = AiTaxonomyFileReferenceTypeField.AiTaxonomyFileReferenceTypeFieldSerializer.class)
  protected EnumWrapper<AiTaxonomyFileReferenceTypeField> type;

  /**
   * The identifier for a taxonomy, which corresponds to the `taxonomy_key` of the taxonomy source.
   */
  @JsonProperty("taxonomy_key")
  protected String taxonomyKey;

  /**
   * The ID of the taxonomy source. Required if the type is `file` and unsupported if the type is
   * `taxonomy`.
   */
  protected String id;

  public AiTaxonomyFileReference() {
    super();
  }

  protected AiTaxonomyFileReference(Builder builder) {
    super();
    this.type = builder.type;
    this.taxonomyKey = builder.taxonomyKey;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<AiTaxonomyFileReferenceTypeField> getType() {
    return type;
  }

  public String getTaxonomyKey() {
    return taxonomyKey;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiTaxonomyFileReference casted = (AiTaxonomyFileReference) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(taxonomyKey, casted.taxonomyKey)
        && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, taxonomyKey, id);
  }

  @Override
  public String toString() {
    return "AiTaxonomyFileReference{"
        + "type='"
        + type
        + '\''
        + ", "
        + "taxonomyKey='"
        + taxonomyKey
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<AiTaxonomyFileReferenceTypeField> type;

    protected String taxonomyKey;

    protected String id;

    public Builder type(AiTaxonomyFileReferenceTypeField type) {
      this.type = new EnumWrapper<AiTaxonomyFileReferenceTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<AiTaxonomyFileReferenceTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder taxonomyKey(String taxonomyKey) {
      this.taxonomyKey = taxonomyKey;
      return this;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public AiTaxonomyFileReference build() {
      return new AiTaxonomyFileReference(this);
    }
  }
}
