package com.box.sdkgen.schemas.v2026r0.shuttlepathmappingsharedspacelocationinputv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** A shared-container source location supplied when creating or updating a path mapping. */
@JsonFilter("nullablePropertyFilter")
public class ShuttlePathMappingSharedSpaceLocationInputV2026R0 extends SerializableObject {

  /** The value will always be `shared_space`. */
  @JsonDeserialize(
      using =
          ShuttlePathMappingSharedSpaceLocationInputV2026R0TypeField
              .ShuttlePathMappingSharedSpaceLocationInputV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttlePathMappingSharedSpaceLocationInputV2026R0TypeField
              .ShuttlePathMappingSharedSpaceLocationInputV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<ShuttlePathMappingSharedSpaceLocationInputV2026R0TypeField> type;

  /** The display name of the shared container. Required for systems with multiple containers. */
  protected String space;

  /** The human-readable path relative to the shared container. */
  protected final String path;

  /** An optional stable identifier of the location, when obtained from browsing. */
  @JsonProperty("path_id")
  protected String pathId;

  /**
   * Whether the location is a directory. When set, the destination must be the same kind of object.
   */
  @JsonProperty("is_directory")
  protected Boolean isDirectory;

  public ShuttlePathMappingSharedSpaceLocationInputV2026R0(@JsonProperty("path") String path) {
    super();
    this.path = path;
    this.type =
        new EnumWrapper<ShuttlePathMappingSharedSpaceLocationInputV2026R0TypeField>(
            ShuttlePathMappingSharedSpaceLocationInputV2026R0TypeField.SHARED_SPACE);
  }

  protected ShuttlePathMappingSharedSpaceLocationInputV2026R0(Builder builder) {
    super();
    this.type = builder.type;
    this.space = builder.space;
    this.path = builder.path;
    this.pathId = builder.pathId;
    this.isDirectory = builder.isDirectory;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<ShuttlePathMappingSharedSpaceLocationInputV2026R0TypeField> getType() {
    return type;
  }

  public String getSpace() {
    return space;
  }

  public String getPath() {
    return path;
  }

  public String getPathId() {
    return pathId;
  }

  public Boolean getIsDirectory() {
    return isDirectory;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttlePathMappingSharedSpaceLocationInputV2026R0 casted =
        (ShuttlePathMappingSharedSpaceLocationInputV2026R0) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(space, casted.space)
        && Objects.equals(path, casted.path)
        && Objects.equals(pathId, casted.pathId)
        && Objects.equals(isDirectory, casted.isDirectory);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, space, path, pathId, isDirectory);
  }

  @Override
  public String toString() {
    return "ShuttlePathMappingSharedSpaceLocationInputV2026R0{"
        + "type='"
        + type
        + '\''
        + ", "
        + "space='"
        + space
        + '\''
        + ", "
        + "path='"
        + path
        + '\''
        + ", "
        + "pathId='"
        + pathId
        + '\''
        + ", "
        + "isDirectory='"
        + isDirectory
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<ShuttlePathMappingSharedSpaceLocationInputV2026R0TypeField> type;

    protected String space;

    protected final String path;

    protected String pathId;

    protected Boolean isDirectory;

    public Builder(String path) {
      super();
      this.path = path;
    }

    public Builder type(ShuttlePathMappingSharedSpaceLocationInputV2026R0TypeField type) {
      this.type = new EnumWrapper<ShuttlePathMappingSharedSpaceLocationInputV2026R0TypeField>(type);
      return this;
    }

    public Builder type(
        EnumWrapper<ShuttlePathMappingSharedSpaceLocationInputV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder space(String space) {
      this.space = space;
      return this;
    }

    public Builder pathId(String pathId) {
      this.pathId = pathId;
      return this;
    }

    public Builder isDirectory(Boolean isDirectory) {
      this.isDirectory = isDirectory;
      return this;
    }

    public ShuttlePathMappingSharedSpaceLocationInputV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<ShuttlePathMappingSharedSpaceLocationInputV2026R0TypeField>(
                ShuttlePathMappingSharedSpaceLocationInputV2026R0TypeField.SHARED_SPACE);
      }
      return new ShuttlePathMappingSharedSpaceLocationInputV2026R0(this);
    }
  }
}
