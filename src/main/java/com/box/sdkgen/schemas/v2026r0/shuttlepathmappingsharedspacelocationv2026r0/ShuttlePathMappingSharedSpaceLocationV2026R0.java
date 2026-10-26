package com.box.sdkgen.schemas.v2026r0.shuttlepathmappingsharedspacelocationv2026r0;

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
 * A path mapping location scoped to a shared container such as a site, team drive, bucket, or file
 * share.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttlePathMappingSharedSpaceLocationV2026R0 extends SerializableObject {

  /** The value will always be `shared_space`. */
  @JsonDeserialize(
      using =
          ShuttlePathMappingSharedSpaceLocationV2026R0TypeField
              .ShuttlePathMappingSharedSpaceLocationV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttlePathMappingSharedSpaceLocationV2026R0TypeField
              .ShuttlePathMappingSharedSpaceLocationV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<ShuttlePathMappingSharedSpaceLocationV2026R0TypeField> type;

  /**
   * The display name of the shared container. `null` when the system has a single implicit
   * container.
   */
  @Nullable protected String space;

  /** The human-readable path relative to the shared container. */
  protected final String path;

  /** The stable identifier of the location. `null` when the system does not provide one. */
  @JsonProperty("path_id")
  @Nullable
  protected String pathId;

  /** Whether the location is a directory. `null` when unknown. */
  @JsonProperty("is_directory")
  @Nullable
  protected Boolean isDirectory;

  public ShuttlePathMappingSharedSpaceLocationV2026R0(@JsonProperty("path") String path) {
    super();
    this.path = path;
    this.type =
        new EnumWrapper<ShuttlePathMappingSharedSpaceLocationV2026R0TypeField>(
            ShuttlePathMappingSharedSpaceLocationV2026R0TypeField.SHARED_SPACE);
  }

  protected ShuttlePathMappingSharedSpaceLocationV2026R0(Builder builder) {
    super();
    this.type = builder.type;
    this.space = builder.space;
    this.path = builder.path;
    this.pathId = builder.pathId;
    this.isDirectory = builder.isDirectory;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<ShuttlePathMappingSharedSpaceLocationV2026R0TypeField> getType() {
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
    ShuttlePathMappingSharedSpaceLocationV2026R0 casted =
        (ShuttlePathMappingSharedSpaceLocationV2026R0) o;
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
    return "ShuttlePathMappingSharedSpaceLocationV2026R0{"
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

    protected EnumWrapper<ShuttlePathMappingSharedSpaceLocationV2026R0TypeField> type;

    protected String space;

    protected final String path;

    protected String pathId;

    protected Boolean isDirectory;

    public Builder(String path) {
      super();
      this.path = path;
    }

    public Builder type(ShuttlePathMappingSharedSpaceLocationV2026R0TypeField type) {
      this.type = new EnumWrapper<ShuttlePathMappingSharedSpaceLocationV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShuttlePathMappingSharedSpaceLocationV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder space(String space) {
      this.space = space;
      this.markNullableFieldAsSet("space");
      return this;
    }

    public Builder pathId(String pathId) {
      this.pathId = pathId;
      this.markNullableFieldAsSet("path_id");
      return this;
    }

    public Builder isDirectory(Boolean isDirectory) {
      this.isDirectory = isDirectory;
      this.markNullableFieldAsSet("is_directory");
      return this;
    }

    public ShuttlePathMappingSharedSpaceLocationV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<ShuttlePathMappingSharedSpaceLocationV2026R0TypeField>(
                ShuttlePathMappingSharedSpaceLocationV2026R0TypeField.SHARED_SPACE);
      }
      return new ShuttlePathMappingSharedSpaceLocationV2026R0(this);
    }
  }
}
