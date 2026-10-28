package com.box.sdkgen.schemas.sharedlinkotpwritablenone;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * One-time-passcode (OTP) enforcement is turned off. The shared link is accessible without any
 * one-time-passcode verification.
 */
@JsonFilter("nullablePropertyFilter")
public class SharedLinkOtpWritableNone extends SerializableObject {

  /** Set to `none` to disable OTP enforcement for the shared link. */
  @JsonDeserialize(
      using =
          SharedLinkOtpWritableNoneModeField.SharedLinkOtpWritableNoneModeFieldDeserializer.class)
  @JsonSerialize(
      using = SharedLinkOtpWritableNoneModeField.SharedLinkOtpWritableNoneModeFieldSerializer.class)
  protected EnumWrapper<SharedLinkOtpWritableNoneModeField> mode;

  public SharedLinkOtpWritableNone() {
    super();
    this.mode =
        new EnumWrapper<SharedLinkOtpWritableNoneModeField>(
            SharedLinkOtpWritableNoneModeField.NONE);
  }

  protected SharedLinkOtpWritableNone(Builder builder) {
    super();
    this.mode = builder.mode;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<SharedLinkOtpWritableNoneModeField> getMode() {
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
    SharedLinkOtpWritableNone casted = (SharedLinkOtpWritableNone) o;
    return Objects.equals(mode, casted.mode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mode);
  }

  @Override
  public String toString() {
    return "SharedLinkOtpWritableNone{" + "mode='" + mode + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<SharedLinkOtpWritableNoneModeField> mode;

    public Builder() {
      super();
    }

    public Builder mode(SharedLinkOtpWritableNoneModeField mode) {
      this.mode = new EnumWrapper<SharedLinkOtpWritableNoneModeField>(mode);
      return this;
    }

    public Builder mode(EnumWrapper<SharedLinkOtpWritableNoneModeField> mode) {
      this.mode = mode;
      return this;
    }

    public SharedLinkOtpWritableNone build() {
      if (this.mode == null) {
        this.mode =
            new EnumWrapper<SharedLinkOtpWritableNoneModeField>(
                SharedLinkOtpWritableNoneModeField.NONE);
      }
      return new SharedLinkOtpWritableNone(this);
    }
  }
}
