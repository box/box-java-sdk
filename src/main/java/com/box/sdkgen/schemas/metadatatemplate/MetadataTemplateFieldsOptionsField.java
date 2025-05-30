package com.box.sdkgen.schemas.metadatatemplate;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class MetadataTemplateFieldsOptionsField extends SerializableObject {

  protected final String key;

  protected String id;

  public MetadataTemplateFieldsOptionsField(@JsonProperty("key") String key) {
    super();
    this.key = key;
  }

  protected MetadataTemplateFieldsOptionsField(MetadataTemplateFieldsOptionsFieldBuilder builder) {
    super();
    this.key = builder.key;
    this.id = builder.id;
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

  public static class MetadataTemplateFieldsOptionsFieldBuilder {

    protected final String key;

    protected String id;

    public MetadataTemplateFieldsOptionsFieldBuilder(String key) {
      this.key = key;
    }

    public MetadataTemplateFieldsOptionsFieldBuilder id(String id) {
      this.id = id;
      return this;
    }

    public MetadataTemplateFieldsOptionsField build() {
      return new MetadataTemplateFieldsOptionsField(this);
    }
  }
}
