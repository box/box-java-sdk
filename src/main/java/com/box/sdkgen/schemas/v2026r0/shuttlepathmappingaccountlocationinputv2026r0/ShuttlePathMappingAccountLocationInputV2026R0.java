package com.box.sdkgen.schemas.v2026r0.shuttlepathmappingaccountlocationinputv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingaccountinputv2026r0.ShuttlePathMappingAccountInputV2026R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** An account-scoped source location supplied when creating or updating a path mapping. */
@JsonFilter("nullablePropertyFilter")
public class ShuttlePathMappingAccountLocationInputV2026R0 extends SerializableObject {

  /** The value will always be `account`. */
  @JsonDeserialize(
      using =
          ShuttlePathMappingAccountLocationInputV2026R0TypeField
              .ShuttlePathMappingAccountLocationInputV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttlePathMappingAccountLocationInputV2026R0TypeField
              .ShuttlePathMappingAccountLocationInputV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<ShuttlePathMappingAccountLocationInputV2026R0TypeField> type;

  /** The human-readable path relative to the account root. */
  protected final String path;

  /** An optional stable identifier of the location, when obtained from browsing. */
  @JsonProperty("path_id")
  protected String pathId;

  protected final ShuttlePathMappingAccountInputV2026R0 account;

  /**
   * Whether the location is a directory. When set, the destination must be the same kind of object.
   */
  @JsonProperty("is_directory")
  protected Boolean isDirectory;

  public ShuttlePathMappingAccountLocationInputV2026R0(
      @JsonProperty("path") String path,
      @JsonProperty("account") ShuttlePathMappingAccountInputV2026R0 account) {
    super();
    this.path = path;
    this.account = account;
    this.type =
        new EnumWrapper<ShuttlePathMappingAccountLocationInputV2026R0TypeField>(
            ShuttlePathMappingAccountLocationInputV2026R0TypeField.ACCOUNT);
  }

  protected ShuttlePathMappingAccountLocationInputV2026R0(Builder builder) {
    super();
    this.type = builder.type;
    this.path = builder.path;
    this.pathId = builder.pathId;
    this.account = builder.account;
    this.isDirectory = builder.isDirectory;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<ShuttlePathMappingAccountLocationInputV2026R0TypeField> getType() {
    return type;
  }

  public String getPath() {
    return path;
  }

  public String getPathId() {
    return pathId;
  }

  public ShuttlePathMappingAccountInputV2026R0 getAccount() {
    return account;
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
    ShuttlePathMappingAccountLocationInputV2026R0 casted =
        (ShuttlePathMappingAccountLocationInputV2026R0) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(path, casted.path)
        && Objects.equals(pathId, casted.pathId)
        && Objects.equals(account, casted.account)
        && Objects.equals(isDirectory, casted.isDirectory);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, path, pathId, account, isDirectory);
  }

  @Override
  public String toString() {
    return "ShuttlePathMappingAccountLocationInputV2026R0{"
        + "type='"
        + type
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
        + "account='"
        + account
        + '\''
        + ", "
        + "isDirectory='"
        + isDirectory
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<ShuttlePathMappingAccountLocationInputV2026R0TypeField> type;

    protected final String path;

    protected String pathId;

    protected final ShuttlePathMappingAccountInputV2026R0 account;

    protected Boolean isDirectory;

    public Builder(String path, ShuttlePathMappingAccountInputV2026R0 account) {
      super();
      this.path = path;
      this.account = account;
    }

    public Builder type(ShuttlePathMappingAccountLocationInputV2026R0TypeField type) {
      this.type = new EnumWrapper<ShuttlePathMappingAccountLocationInputV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShuttlePathMappingAccountLocationInputV2026R0TypeField> type) {
      this.type = type;
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

    public ShuttlePathMappingAccountLocationInputV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<ShuttlePathMappingAccountLocationInputV2026R0TypeField>(
                ShuttlePathMappingAccountLocationInputV2026R0TypeField.ACCOUNT);
      }
      return new ShuttlePathMappingAccountLocationInputV2026R0(this);
    }
  }
}
