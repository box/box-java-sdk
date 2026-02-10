package com.box.sdkgen.schemas.v2025r0.archivev2025r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * An archive is a folder dedicated to storing content that is redundant, outdated, or trivial.
 * Content in an archive is not accessible to its owner and collaborators. To use this feature, you
 * must request GCM scope for your Box application.
 */
@JsonFilter("nullablePropertyFilter")
public class ArchiveV2025R0 extends SerializableObject {

  /** The unique identifier that represents an archive. */
  protected final String id;

  /** The value is always `archive`. */
  @JsonDeserialize(using = ArchiveV2025R0TypeField.ArchiveV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(using = ArchiveV2025R0TypeField.ArchiveV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<ArchiveV2025R0TypeField> type;

  /**
   * The name of the archive.
   *
   * <p>The following restrictions to the archive name apply: names containing non-printable ASCII
   * characters, forward and backward slashes (`/`, `\`), names with trailing spaces, and names `.`
   * and `..` are not allowed.
   */
  protected final String name;

  /** The size of the archive in bytes. */
  protected final long size;

  /** The description of the archive. */
  @Nullable protected String description;

  /** The part of an archive API response that describes the user who owns the archive. */
  @JsonProperty("owned_by")
  protected ArchiveV2025R0OwnedByField ownedBy;

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
    this.description = builder.description;
    this.ownedBy = builder.ownedBy;
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

  public String getDescription() {
    return description;
  }

  public ArchiveV2025R0OwnedByField getOwnedBy() {
    return ownedBy;
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
        && Objects.equals(size, casted.size)
        && Objects.equals(description, casted.description)
        && Objects.equals(ownedBy, casted.ownedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name, size, description, ownedBy);
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
        + ", "
        + "description='"
        + description
        + '\''
        + ", "
        + "ownedBy='"
        + ownedBy
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<ArchiveV2025R0TypeField> type;

    protected final String name;

    protected final long size;

    protected String description;

    protected ArchiveV2025R0OwnedByField ownedBy;

    public Builder(String id, String name, long size) {
      super();
      this.id = id;
      this.name = name;
      this.size = size;
    }

    public Builder type(ArchiveV2025R0TypeField type) {
      this.type = new EnumWrapper<ArchiveV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ArchiveV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      this.markNullableFieldAsSet("description");
      return this;
    }

    public Builder ownedBy(ArchiveV2025R0OwnedByField ownedBy) {
      this.ownedBy = ownedBy;
      return this;
    }

    public ArchiveV2025R0 build() {
      if (this.type == null) {
        this.type = new EnumWrapper<ArchiveV2025R0TypeField>(ArchiveV2025R0TypeField.ARCHIVE);
      }
      return new ArchiveV2025R0(this);
    }
  }
}
