package com.box.sdkgen.schemas.v2026r0.shuttlepathmappingsharedspacelocationtargetinputv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** A shared-container destination location supplied when creating or updating a path mapping. */
@JsonFilter("nullablePropertyFilter")
public class ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0 extends SerializableObject {

  /** The value will always be `shared_space`. */
  @JsonDeserialize(
      using =
          ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0TypeField
              .ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0TypeField
              .ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0TypeField> type;

  /** The display name of the shared container. Required for systems with multiple containers. */
  protected String space;

  /** The human-readable path relative to the shared container. */
  protected final String path;

  /** An optional stable identifier of the location, when obtained from browsing. */
  @JsonProperty("path_id")
  protected String pathId;

  public ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0(
      @JsonProperty("path") String path) {
    super();
    this.path = path;
    this.type =
        new EnumWrapper<ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0TypeField>(
            ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0TypeField.SHARED_SPACE);
  }

  protected ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0(Builder builder) {
    super();
    this.type = builder.type;
    this.space = builder.space;
    this.path = builder.path;
    this.pathId = builder.pathId;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0TypeField> getType() {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0 casted =
        (ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(space, casted.space)
        && Objects.equals(path, casted.path)
        && Objects.equals(pathId, casted.pathId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, space, path, pathId);
  }

  @Override
  public String toString() {
    return "ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0{"
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
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0TypeField> type;

    protected String space;

    protected final String path;

    protected String pathId;

    public Builder(String path) {
      super();
      this.path = path;
    }

    public Builder type(ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0TypeField type) {
      this.type =
          new EnumWrapper<ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0TypeField>(type);
      return this;
    }

    public Builder type(
        EnumWrapper<ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0TypeField> type) {
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

    public ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0TypeField>(
                ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0TypeField.SHARED_SPACE);
      }
      return new ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0(this);
    }
  }
}
