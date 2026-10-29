package com.box.sdkgen.schemas.v2026r0.shuttlesystemcreaterequestv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * The request body for creating a shuttle system. Only Windows agent systems can be created through
 * the public API, so `system_type` must be `windows`.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttleSystemCreateRequestV2026R0 extends SerializableObject {

  /** The display name for the new shuttle system. */
  protected final String name;

  /**
   * The public system type identifier for the new shuttle system. Only `windows` is currently
   * supported through the public API.
   */
  @JsonDeserialize(
      using =
          ShuttleSystemCreateRequestV2026R0SystemTypeField
              .ShuttleSystemCreateRequestV2026R0SystemTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttleSystemCreateRequestV2026R0SystemTypeField
              .ShuttleSystemCreateRequestV2026R0SystemTypeFieldSerializer.class)
  @JsonProperty("system_type")
  protected EnumWrapper<ShuttleSystemCreateRequestV2026R0SystemTypeField> systemType;

  public ShuttleSystemCreateRequestV2026R0(@JsonProperty("name") String name) {
    super();
    this.name = name;
    this.systemType =
        new EnumWrapper<ShuttleSystemCreateRequestV2026R0SystemTypeField>(
            ShuttleSystemCreateRequestV2026R0SystemTypeField.WINDOWS);
  }

  protected ShuttleSystemCreateRequestV2026R0(Builder builder) {
    super();
    this.name = builder.name;
    this.systemType = builder.systemType;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public EnumWrapper<ShuttleSystemCreateRequestV2026R0SystemTypeField> getSystemType() {
    return systemType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttleSystemCreateRequestV2026R0 casted = (ShuttleSystemCreateRequestV2026R0) o;
    return Objects.equals(name, casted.name) && Objects.equals(systemType, casted.systemType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, systemType);
  }

  @Override
  public String toString() {
    return "ShuttleSystemCreateRequestV2026R0{"
        + "name='"
        + name
        + '\''
        + ", "
        + "systemType='"
        + systemType
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String name;

    protected EnumWrapper<ShuttleSystemCreateRequestV2026R0SystemTypeField> systemType;

    public Builder(String name) {
      super();
      this.name = name;
    }

    public Builder systemType(ShuttleSystemCreateRequestV2026R0SystemTypeField systemType) {
      this.systemType =
          new EnumWrapper<ShuttleSystemCreateRequestV2026R0SystemTypeField>(systemType);
      return this;
    }

    public Builder systemType(
        EnumWrapper<ShuttleSystemCreateRequestV2026R0SystemTypeField> systemType) {
      this.systemType = systemType;
      return this;
    }

    public ShuttleSystemCreateRequestV2026R0 build() {
      if (this.systemType == null) {
        this.systemType =
            new EnumWrapper<ShuttleSystemCreateRequestV2026R0SystemTypeField>(
                ShuttleSystemCreateRequestV2026R0SystemTypeField.WINDOWS);
      }
      return new ShuttleSystemCreateRequestV2026R0(this);
    }
  }
}
