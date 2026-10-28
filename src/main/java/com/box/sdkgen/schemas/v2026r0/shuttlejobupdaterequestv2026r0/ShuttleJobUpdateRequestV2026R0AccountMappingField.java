package com.box.sdkgen.schemas.v2026r0.shuttlejobupdaterequestv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.shuttlejobpermissionconflictstrategyv2026r0.ShuttleJobPermissionConflictStrategyV2026R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ShuttleJobUpdateRequestV2026R0AccountMappingField extends SerializableObject {

  /** Whether account mapping is enabled for the shuttle job. */
  @JsonProperty("is_enabled")
  protected Boolean isEnabled;

  /** The strategy used to resolve permission conflicts. `null` when unset. */
  @JsonDeserialize(
      using =
          ShuttleJobPermissionConflictStrategyV2026R0
              .ShuttleJobPermissionConflictStrategyV2026R0Deserializer.class)
  @JsonSerialize(
      using =
          ShuttleJobPermissionConflictStrategyV2026R0
              .ShuttleJobPermissionConflictStrategyV2026R0Serializer.class)
  @JsonProperty("permission_conflict_strategy")
  protected EnumWrapper<ShuttleJobPermissionConflictStrategyV2026R0> permissionConflictStrategy;

  /** Whether external shares are mapped during the migration. */
  @JsonProperty("are_external_shares_mapped")
  protected Boolean areExternalSharesMapped;

  /** Whether permissions are mapped during the migration. */
  @JsonProperty("are_permissions_mapped")
  protected Boolean arePermissionsMapped;

  public ShuttleJobUpdateRequestV2026R0AccountMappingField() {
    super();
  }

  protected ShuttleJobUpdateRequestV2026R0AccountMappingField(Builder builder) {
    super();
    this.isEnabled = builder.isEnabled;
    this.permissionConflictStrategy = builder.permissionConflictStrategy;
    this.areExternalSharesMapped = builder.areExternalSharesMapped;
    this.arePermissionsMapped = builder.arePermissionsMapped;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Boolean getIsEnabled() {
    return isEnabled;
  }

  public EnumWrapper<ShuttleJobPermissionConflictStrategyV2026R0> getPermissionConflictStrategy() {
    return permissionConflictStrategy;
  }

  public Boolean getAreExternalSharesMapped() {
    return areExternalSharesMapped;
  }

  public Boolean getArePermissionsMapped() {
    return arePermissionsMapped;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttleJobUpdateRequestV2026R0AccountMappingField casted =
        (ShuttleJobUpdateRequestV2026R0AccountMappingField) o;
    return Objects.equals(isEnabled, casted.isEnabled)
        && Objects.equals(permissionConflictStrategy, casted.permissionConflictStrategy)
        && Objects.equals(areExternalSharesMapped, casted.areExternalSharesMapped)
        && Objects.equals(arePermissionsMapped, casted.arePermissionsMapped);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        isEnabled, permissionConflictStrategy, areExternalSharesMapped, arePermissionsMapped);
  }

  @Override
  public String toString() {
    return "ShuttleJobUpdateRequestV2026R0AccountMappingField{"
        + "isEnabled='"
        + isEnabled
        + '\''
        + ", "
        + "permissionConflictStrategy='"
        + permissionConflictStrategy
        + '\''
        + ", "
        + "areExternalSharesMapped='"
        + areExternalSharesMapped
        + '\''
        + ", "
        + "arePermissionsMapped='"
        + arePermissionsMapped
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Boolean isEnabled;

    protected EnumWrapper<ShuttleJobPermissionConflictStrategyV2026R0> permissionConflictStrategy;

    protected Boolean areExternalSharesMapped;

    protected Boolean arePermissionsMapped;

    public Builder isEnabled(Boolean isEnabled) {
      this.isEnabled = isEnabled;
      return this;
    }

    public Builder permissionConflictStrategy(
        ShuttleJobPermissionConflictStrategyV2026R0 permissionConflictStrategy) {
      this.permissionConflictStrategy =
          new EnumWrapper<ShuttleJobPermissionConflictStrategyV2026R0>(permissionConflictStrategy);
      return this;
    }

    public Builder permissionConflictStrategy(
        EnumWrapper<ShuttleJobPermissionConflictStrategyV2026R0> permissionConflictStrategy) {
      this.permissionConflictStrategy = permissionConflictStrategy;
      return this;
    }

    public Builder areExternalSharesMapped(Boolean areExternalSharesMapped) {
      this.areExternalSharesMapped = areExternalSharesMapped;
      return this;
    }

    public Builder arePermissionsMapped(Boolean arePermissionsMapped) {
      this.arePermissionsMapped = arePermissionsMapped;
      return this;
    }

    public ShuttleJobUpdateRequestV2026R0AccountMappingField build() {
      return new ShuttleJobUpdateRequestV2026R0AccountMappingField(this);
    }
  }
}
