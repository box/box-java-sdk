package com.box.sdkgen.schemas.v2025r0.archivev2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ArchiveV2025R0 extends SerializableObject {

  protected final String id;

  @JsonDeserialize(using = ArchiveV2025R0TypeField.ArchiveV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(using = ArchiveV2025R0TypeField.ArchiveV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<ArchiveV2025R0TypeField> type;

  protected final String name;

  protected final long size;

  public ArchiveV2025R0(
      @JsonProperty("id") String id,
      @JsonProperty("name") String name,
      @JsonProperty("size") long size) {
    super();
    this.id = id;
    this.name = name;
    this.size = size;
    this.type = new EnumWrapper<ArchiveV2025R0TypeField>(ArchiveV2025R0TypeField.ARCHIVE);
  }

  protected ArchiveV2025R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.name = builder.name;
    this.size = builder.size;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ArchiveV2025R0TypeField> getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public long getSize() {
    return size;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ArchiveV2025R0 casted = (ArchiveV2025R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name)
        && Objects.equals(size, casted.size);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name, size);
  }

  @Override
  public String toString() {
    return "ArchiveV2025R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + ", "
        + "size='"
        + size
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<ArchiveV2025R0TypeField> type;

    protected final String name;

    protected final long size;

    public Builder(String id, String name, long size) {
      super();
      this.id = id;
      this.name = name;
      this.size = size;
      this.type = new EnumWrapper<ArchiveV2025R0TypeField>(ArchiveV2025R0TypeField.ARCHIVE);
    }

    public Builder type(ArchiveV2025R0TypeField type) {
      this.type = new EnumWrapper<ArchiveV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ArchiveV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public ArchiveV2025R0 build() {
      return new ArchiveV2025R0(this);
    }
  }
}
