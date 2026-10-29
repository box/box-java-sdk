package com.box.sdkgen.schemas.v2026r0.shuttlejobstartrequestv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** The request body used to start a shuttle job. */
@JsonFilter("nullablePropertyFilter")
public class ShuttleJobStartRequestV2026R0 extends SerializableObject {

  /** The run mode. Defaults to simulation when omitted. */
  @JsonDeserialize(
      using =
          ShuttleJobStartRequestV2026R0ModeField.ShuttleJobStartRequestV2026R0ModeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          ShuttleJobStartRequestV2026R0ModeField.ShuttleJobStartRequestV2026R0ModeFieldSerializer
              .class)
  protected EnumWrapper<ShuttleJobStartRequestV2026R0ModeField> mode;

  public ShuttleJobStartRequestV2026R0() {
    super();
  }

  protected ShuttleJobStartRequestV2026R0(Builder builder) {
    super();
    this.mode = builder.mode;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<ShuttleJobStartRequestV2026R0ModeField> getMode() {
    return mode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttleJobStartRequestV2026R0 casted = (ShuttleJobStartRequestV2026R0) o;
    return Objects.equals(mode, casted.mode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mode);
  }

  @Override
  public String toString() {
    return "ShuttleJobStartRequestV2026R0{" + "mode='" + mode + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<ShuttleJobStartRequestV2026R0ModeField> mode;

    public Builder mode(ShuttleJobStartRequestV2026R0ModeField mode) {
      this.mode = new EnumWrapper<ShuttleJobStartRequestV2026R0ModeField>(mode);
      return this;
    }

    public Builder mode(EnumWrapper<ShuttleJobStartRequestV2026R0ModeField> mode) {
      this.mode = mode;
      return this;
    }

    public ShuttleJobStartRequestV2026R0 build() {
      return new ShuttleJobStartRequestV2026R0(this);
    }
  }
}
