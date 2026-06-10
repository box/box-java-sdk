package com.box.sdkgen.schemas.aitaxonomyreference;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * A taxonomy source to be used for the structured extraction. For your request to work, `fields`
 * must also be provided.
 */
@JsonFilter("nullablePropertyFilter")
public class AiTaxonomyReference extends SerializableObject {

  /** The type of the taxonomy source. */
  @JsonDeserialize(
      using = AiTaxonomyReferenceTypeField.AiTaxonomyReferenceTypeFieldDeserializer.class)
  @JsonSerialize(using = AiTaxonomyReferenceTypeField.AiTaxonomyReferenceTypeFieldSerializer.class)
  protected EnumWrapper<AiTaxonomyReferenceTypeField> type;

  /**
   * The identifier for a taxonomy, which corresponds to the `taxonomy_key` of the taxonomy source.
   */
  @JsonProperty("taxonomy_key")
  protected String taxonomyKey;

  /** The namespace of the taxonomy source. */
  protected String namespace;

  public AiTaxonomyReference() {
    super();
  }

  protected AiTaxonomyReference(Builder builder) {
    super();
    this.type = builder.type;
    this.taxonomyKey = builder.taxonomyKey;
    this.namespace = builder.namespace;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<AiTaxonomyReferenceTypeField> getType() {
    return type;
  }

  public String getTaxonomyKey() {
    return taxonomyKey;
  }

  public String getNamespace() {
    return namespace;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiTaxonomyReference casted = (AiTaxonomyReference) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(taxonomyKey, casted.taxonomyKey)
        && Objects.equals(namespace, casted.namespace);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, taxonomyKey, namespace);
  }

  @Override
  public String toString() {
    return "AiTaxonomyReference{"
        + "type='"
        + type
        + '\''
        + ", "
        + "taxonomyKey='"
        + taxonomyKey
        + '\''
        + ", "
        + "namespace='"
        + namespace
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<AiTaxonomyReferenceTypeField> type;

    protected String taxonomyKey;

    protected String namespace;

    public Builder type(AiTaxonomyReferenceTypeField type) {
      this.type = new EnumWrapper<AiTaxonomyReferenceTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<AiTaxonomyReferenceTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder taxonomyKey(String taxonomyKey) {
      this.taxonomyKey = taxonomyKey;
      return this;
    }

    public Builder namespace(String namespace) {
      this.namespace = namespace;
      return this;
    }

    public AiTaxonomyReference build() {
      return new AiTaxonomyReference(this);
    }
  }
}
