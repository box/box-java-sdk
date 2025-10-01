package com.box.sdkgen.schemas.metadatatemplate;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class MetadataTemplateFieldsOptionsField extends SerializableObject {

  /**
   * The text value of the option. This represents both the display name of the option and the
   * internal key used when updating templates.
   */
  protected final String key;

  /** The internal unique identifier of the option. */
  protected String id;

  public MetadataTemplateFieldsOptionsField(@JsonProperty("key") String key) {
    super();
    this.key = key;
  }

  protected MetadataTemplateFieldsOptionsField(Builder builder) {
    super();
    this.key = builder.key;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getKey() {
    return key;
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
    MetadataTemplateFieldsOptionsField casted = (MetadataTemplateFieldsOptionsField) o;
    return Objects.equals(key, casted.key) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, id);
  }

  @Override
  public String toString() {
    return "MetadataTemplateFieldsOptionsField{"
        + "key='"
        + key
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String key;

    protected String id;

    public Builder(String key) {
      super();
      this.key = key;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public MetadataTemplateFieldsOptionsField build() {
      return new MetadataTemplateFieldsOptionsField(this);
    }
  }
}
