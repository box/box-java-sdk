package com.box.sdkgen.schemas.v2026r0.shuttlepathmappingaccountlocationv2026r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingaccountv2026r0.ShuttlePathMappingAccountV2026R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** A path mapping location scoped to a user or service account. */
@JsonFilter("nullablePropertyFilter")
public class ShuttlePathMappingAccountLocationV2026R0 extends SerializableObject {

  /** The value will always be `account`. */
  @JsonDeserialize(
      using =
          ShuttlePathMappingAccountLocationV2026R0TypeField
              .ShuttlePathMappingAccountLocationV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttlePathMappingAccountLocationV2026R0TypeField
              .ShuttlePathMappingAccountLocationV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<ShuttlePathMappingAccountLocationV2026R0TypeField> type;

  /** The human-readable path relative to the account root. */
  protected final String path;

  /** The stable identifier of the location. `null` when the system does not provide one. */
  @JsonProperty("path_id")
  @Nullable
  protected String pathId;

  protected ShuttlePathMappingAccountV2026R0 account;

  /** Whether the location is a directory. `null` when unknown. */
  @JsonProperty("is_directory")
  @Nullable
  protected Boolean isDirectory;

  public ShuttlePathMappingAccountLocationV2026R0(@JsonProperty("path") String path) {
    super();
    this.path = path;
    this.type =
        new EnumWrapper<ShuttlePathMappingAccountLocationV2026R0TypeField>(
            ShuttlePathMappingAccountLocationV2026R0TypeField.ACCOUNT);
  }

  protected ShuttlePathMappingAccountLocationV2026R0(Builder builder) {
    super();
    this.type = builder.type;
    this.path = builder.path;
    this.pathId = builder.pathId;
    this.account = builder.account;
    this.isDirectory = builder.isDirectory;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<ShuttlePathMappingAccountLocationV2026R0TypeField> getType() {
    return type;
  }

  public String getPath() {
    return path;
  }

  public String getPathId() {
    return pathId;
  }

  public ShuttlePathMappingAccountV2026R0 getAccount() {
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
    ShuttlePathMappingAccountLocationV2026R0 casted = (ShuttlePathMappingAccountLocationV2026R0) o;
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
    return "ShuttlePathMappingAccountLocationV2026R0{"
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

    protected EnumWrapper<ShuttlePathMappingAccountLocationV2026R0TypeField> type;

    protected final String path;

    protected String pathId;

    protected ShuttlePathMappingAccountV2026R0 account;

    protected Boolean isDirectory;

    public Builder(String path) {
      super();
      this.path = path;
    }

    public Builder type(ShuttlePathMappingAccountLocationV2026R0TypeField type) {
      this.type = new EnumWrapper<ShuttlePathMappingAccountLocationV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShuttlePathMappingAccountLocationV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder pathId(String pathId) {
      this.pathId = pathId;
      this.markNullableFieldAsSet("path_id");
      return this;
    }

    public Builder account(ShuttlePathMappingAccountV2026R0 account) {
      this.account = account;
      return this;
    }

    public Builder isDirectory(Boolean isDirectory) {
      this.isDirectory = isDirectory;
      this.markNullableFieldAsSet("is_directory");
      return this;
    }

    public ShuttlePathMappingAccountLocationV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<ShuttlePathMappingAccountLocationV2026R0TypeField>(
                ShuttlePathMappingAccountLocationV2026R0TypeField.ACCOUNT);
      }
      return new ShuttlePathMappingAccountLocationV2026R0(this);
    }
  }
}
