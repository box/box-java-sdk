package com.box.sdkgen.schemas.v2026r0.shuttlepathentryv2026r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.shuttlepathentryaccountscopev2026r0.ShuttlePathEntryAccountScopeV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathentryscopev2026r0.ShuttlePathEntryScopeV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathentrysharedspacescopev2026r0.ShuttlePathEntrySharedSpaceScopeV2026R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * A browsable entry on the source or target system used to build shuttle path mappings. Shared
 * fields describe the node. `scope` is a discriminated object that identifies the mapping namespace
 * so a path mapping can be created from this row without extra browse state.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttlePathEntryV2026R0 extends SerializableObject {

  /**
   * The unique identifier of this browse row. This is not the account identifier; when `scope.type`
   * is `account`, the account identifier is `scope.id`.
   */
  protected final String id;

  /** The value will always be `shuttle_path_entry`. */
  @JsonDeserialize(
      using = ShuttlePathEntryV2026R0TypeField.ShuttlePathEntryV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using = ShuttlePathEntryV2026R0TypeField.ShuttlePathEntryV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<ShuttlePathEntryV2026R0TypeField> type;

  /** The display name of the path entry. */
  protected final String name;

  /** The human-readable path of the entry. */
  protected final String path;

  /**
   * The system-native identifier of the path. `null` when the selected system does not provide one.
   * Maps to `path_id` on a path mapping location.
   */
  @JsonProperty("path_id")
  @Nullable
  protected String pathId;

  /** The human-readable path of the parent entry, or `null` for a root entry. */
  @JsonProperty("parent_path")
  @Nullable
  protected String parentPath;

  protected final ShuttlePathEntryScopeV2026R0 scope;

  /**
   * Whether the entry is a directory (`true`) or a single file (`false`). Maps to `is_directory` on
   * a path mapping location.
   */
  @JsonProperty("is_directory")
  protected final boolean isDirectory;

  /** Whether the entry can be selected in a path mapping. */
  @JsonProperty("can_map")
  protected final boolean canMap;

  public ShuttlePathEntryV2026R0(
      String id,
      String name,
      String path,
      ShuttlePathEntryAccountScopeV2026R0 scope,
      boolean isDirectory,
      boolean canMap) {
    super();
    this.id = id;
    this.name = name;
    this.path = path;
    this.scope = new ShuttlePathEntryScopeV2026R0(scope);
    this.isDirectory = isDirectory;
    this.canMap = canMap;
    this.type =
        new EnumWrapper<ShuttlePathEntryV2026R0TypeField>(
            ShuttlePathEntryV2026R0TypeField.SHUTTLE_PATH_ENTRY);
  }

  public ShuttlePathEntryV2026R0(
      String id,
      String name,
      String path,
      ShuttlePathEntrySharedSpaceScopeV2026R0 scope,
      boolean isDirectory,
      boolean canMap) {
    super();
    this.id = id;
    this.name = name;
    this.path = path;
    this.scope = new ShuttlePathEntryScopeV2026R0(scope);
    this.isDirectory = isDirectory;
    this.canMap = canMap;
    this.type =
        new EnumWrapper<ShuttlePathEntryV2026R0TypeField>(
            ShuttlePathEntryV2026R0TypeField.SHUTTLE_PATH_ENTRY);
  }

  public ShuttlePathEntryV2026R0(
      @JsonProperty("id") String id,
      @JsonProperty("name") String name,
      @JsonProperty("path") String path,
      @JsonProperty("scope") ShuttlePathEntryScopeV2026R0 scope,
      @JsonProperty("is_directory") boolean isDirectory,
      @JsonProperty("can_map") boolean canMap) {
    super();
    this.id = id;
    this.name = name;
    this.path = path;
    this.scope = scope;
    this.isDirectory = isDirectory;
    this.canMap = canMap;
    this.type =
        new EnumWrapper<ShuttlePathEntryV2026R0TypeField>(
            ShuttlePathEntryV2026R0TypeField.SHUTTLE_PATH_ENTRY);
  }

  protected ShuttlePathEntryV2026R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.name = builder.name;
    this.path = builder.path;
    this.pathId = builder.pathId;
    this.parentPath = builder.parentPath;
    this.scope = builder.scope;
    this.isDirectory = builder.isDirectory;
    this.canMap = builder.canMap;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ShuttlePathEntryV2026R0TypeField> getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public String getPath() {
    return path;
  }

  public String getPathId() {
    return pathId;
  }

  public String getParentPath() {
    return parentPath;
  }

  public ShuttlePathEntryScopeV2026R0 getScope() {
    return scope;
  }

  public boolean getIsDirectory() {
    return isDirectory;
  }

  public boolean getCanMap() {
    return canMap;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttlePathEntryV2026R0 casted = (ShuttlePathEntryV2026R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name)
        && Objects.equals(path, casted.path)
        && Objects.equals(pathId, casted.pathId)
        && Objects.equals(parentPath, casted.parentPath)
        && Objects.equals(scope, casted.scope)
        && Objects.equals(isDirectory, casted.isDirectory)
        && Objects.equals(canMap, casted.canMap);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name, path, pathId, parentPath, scope, isDirectory, canMap);
  }

  @Override
  public String toString() {
    return "ShuttlePathEntryV2026R0{"
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
        + "path='"
        + path
        + '\''
        + ", "
        + "pathId='"
        + pathId
        + '\''
        + ", "
        + "parentPath='"
        + parentPath
        + '\''
        + ", "
        + "scope='"
        + scope
        + '\''
        + ", "
        + "isDirectory='"
        + isDirectory
        + '\''
        + ", "
        + "canMap='"
        + canMap
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<ShuttlePathEntryV2026R0TypeField> type;

    protected final String name;

    protected final String path;

    protected String pathId;

    protected String parentPath;

    protected final ShuttlePathEntryScopeV2026R0 scope;

    protected final boolean isDirectory;

    protected final boolean canMap;

    public Builder(
        String id,
        String name,
        String path,
        ShuttlePathEntryAccountScopeV2026R0 scope,
        boolean isDirectory,
        boolean canMap) {
      super();
      this.id = id;
      this.name = name;
      this.path = path;
      this.scope = new ShuttlePathEntryScopeV2026R0(scope);
      this.isDirectory = isDirectory;
      this.canMap = canMap;
    }

    public Builder(
        String id,
        String name,
        String path,
        ShuttlePathEntrySharedSpaceScopeV2026R0 scope,
        boolean isDirectory,
        boolean canMap) {
      super();
      this.id = id;
      this.name = name;
      this.path = path;
      this.scope = new ShuttlePathEntryScopeV2026R0(scope);
      this.isDirectory = isDirectory;
      this.canMap = canMap;
    }

    public Builder(
        String id,
        String name,
        String path,
        ShuttlePathEntryScopeV2026R0 scope,
        boolean isDirectory,
        boolean canMap) {
      super();
      this.id = id;
      this.name = name;
      this.path = path;
      this.scope = scope;
      this.isDirectory = isDirectory;
      this.canMap = canMap;
    }

    public Builder type(ShuttlePathEntryV2026R0TypeField type) {
      this.type = new EnumWrapper<ShuttlePathEntryV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShuttlePathEntryV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder pathId(String pathId) {
      this.pathId = pathId;
      this.markNullableFieldAsSet("path_id");
      return this;
    }

    public Builder parentPath(String parentPath) {
      this.parentPath = parentPath;
      this.markNullableFieldAsSet("parent_path");
      return this;
    }

    public ShuttlePathEntryV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<ShuttlePathEntryV2026R0TypeField>(
                ShuttlePathEntryV2026R0TypeField.SHUTTLE_PATH_ENTRY);
      }
      return new ShuttlePathEntryV2026R0(this);
    }
  }
}
