package com.box.sdkgen.schemas.metadataqueryindex;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class MetadataQueryIndex extends SerializableObject {

  protected String id;

  protected final String type;

  @JsonDeserialize(
      using = MetadataQueryIndexStatusField.MetadataQueryIndexStatusFieldDeserializer.class)
  @JsonSerialize(
      using = MetadataQueryIndexStatusField.MetadataQueryIndexStatusFieldSerializer.class)
  protected final EnumWrapper<MetadataQueryIndexStatusField> status;

  protected List<MetadataQueryIndexFieldsField> fields;

  public MetadataQueryIndex(String type, MetadataQueryIndexStatusField status) {
    super();
    this.type = type;
    this.status = new EnumWrapper<MetadataQueryIndexStatusField>(status);
  }

  public MetadataQueryIndex(
      @JsonProperty("type") String type,
      @JsonProperty("status") EnumWrapper<MetadataQueryIndexStatusField> status) {
    super();
    this.type = type;
    this.status = status;
  }

  protected MetadataQueryIndex(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.status = builder.status;
    this.fields = builder.fields;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public EnumWrapper<MetadataQueryIndexStatusField> getStatus() {
    return status;
  }

  public List<MetadataQueryIndexFieldsField> getFields() {
    return fields;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataQueryIndex casted = (MetadataQueryIndex) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(status, casted.status)
        && Objects.equals(fields, casted.fields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, status, fields);
  }

  @Override
  public String toString() {
    return "MetadataQueryIndex{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + ", "
        + "fields='"
        + fields
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected final String type;

    protected final EnumWrapper<MetadataQueryIndexStatusField> status;

    protected List<MetadataQueryIndexFieldsField> fields;

    public Builder(String type, MetadataQueryIndexStatusField status) {
      super();
      this.type = type;
      this.status = new EnumWrapper<MetadataQueryIndexStatusField>(status);
    }

    public Builder(String type, EnumWrapper<MetadataQueryIndexStatusField> status) {
      super();
      this.type = type;
      this.status = status;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder fields(List<MetadataQueryIndexFieldsField> fields) {
      this.fields = fields;
      return this;
    }

    public MetadataQueryIndex build() {
      return new MetadataQueryIndex(this);
    }
  }
}
