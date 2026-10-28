package com.box.sdkgen.schemas.sharedlinkotpwritableopen;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * Any anonymous recipient can request a one-time-passcode to any email address and access the
 * shared link after verifying it. Collaborators and registered Box users access the link without
 * being prompted.
 */
@JsonFilter("nullablePropertyFilter")
public class SharedLinkOtpWritableOpen extends SerializableObject {

  /**
   * Set to `open` to allow any anonymous recipient to request a one-time-passcode to any email
   * address.
   */
  @JsonDeserialize(
      using =
          SharedLinkOtpWritableOpenModeField.SharedLinkOtpWritableOpenModeFieldDeserializer.class)
  @JsonSerialize(
      using = SharedLinkOtpWritableOpenModeField.SharedLinkOtpWritableOpenModeFieldSerializer.class)
  protected EnumWrapper<SharedLinkOtpWritableOpenModeField> mode;

  public SharedLinkOtpWritableOpen() {
    super();
    this.mode =
        new EnumWrapper<SharedLinkOtpWritableOpenModeField>(
            SharedLinkOtpWritableOpenModeField.OPEN);
  }

  protected SharedLinkOtpWritableOpen(Builder builder) {
    super();
    this.mode = builder.mode;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<SharedLinkOtpWritableOpenModeField> getMode() {
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
    SharedLinkOtpWritableOpen casted = (SharedLinkOtpWritableOpen) o;
    return Objects.equals(mode, casted.mode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mode);
  }

  @Override
  public String toString() {
    return "SharedLinkOtpWritableOpen{" + "mode='" + mode + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<SharedLinkOtpWritableOpenModeField> mode;

    public Builder() {
      super();
    }

    public Builder mode(SharedLinkOtpWritableOpenModeField mode) {
      this.mode = new EnumWrapper<SharedLinkOtpWritableOpenModeField>(mode);
      return this;
    }

    public Builder mode(EnumWrapper<SharedLinkOtpWritableOpenModeField> mode) {
      this.mode = mode;
      return this;
    }

    public SharedLinkOtpWritableOpen build() {
      if (this.mode == null) {
        this.mode =
            new EnumWrapper<SharedLinkOtpWritableOpenModeField>(
                SharedLinkOtpWritableOpenModeField.OPEN);
      }
      return new SharedLinkOtpWritableOpen(this);
    }
  }
}
