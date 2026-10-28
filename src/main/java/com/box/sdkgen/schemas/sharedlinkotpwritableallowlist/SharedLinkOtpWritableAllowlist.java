package com.box.sdkgen.schemas.sharedlinkotpwritableallowlist;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.sharedlinkotpallowlistentrywritable.SharedLinkOtpAllowlistEntryWritable;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

/**
 * Anonymous recipients can request a one-time-passcode only when they enter an email that matches
 * an entry on the `allowlist`.
 */
@JsonFilter("nullablePropertyFilter")
public class SharedLinkOtpWritableAllowlist extends SerializableObject {

  /** Set to `allowlist` to restrict OTP requests to emails matching an entry on the `allowlist`. */
  @JsonDeserialize(
      using =
          SharedLinkOtpWritableAllowlistModeField
              .SharedLinkOtpWritableAllowlistModeFieldDeserializer.class)
  @JsonSerialize(
      using =
          SharedLinkOtpWritableAllowlistModeField.SharedLinkOtpWritableAllowlistModeFieldSerializer
              .class)
  protected EnumWrapper<SharedLinkOtpWritableAllowlistModeField> mode;

  /**
   * The list of email and domain entries that are permitted to access the shared link when `mode`
   * is `allowlist`. Anonymous users whose email matches an entry can request and verify a
   * one-time-passcode.
   *
   * <p>Providing this field replaces the entire existing allowlist; passing an empty array clears
   * all entries. Required when `mode` is `allowlist`.
   */
  protected final List<SharedLinkOtpAllowlistEntryWritable> allowlist;

  public SharedLinkOtpWritableAllowlist(
      @JsonProperty("allowlist") List<SharedLinkOtpAllowlistEntryWritable> allowlist) {
    super();
    this.allowlist = allowlist;
    this.mode =
        new EnumWrapper<SharedLinkOtpWritableAllowlistModeField>(
            SharedLinkOtpWritableAllowlistModeField.ALLOWLIST);
  }

  protected SharedLinkOtpWritableAllowlist(Builder builder) {
    super();
    this.mode = builder.mode;
    this.allowlist = builder.allowlist;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<SharedLinkOtpWritableAllowlistModeField> getMode() {
    return mode;
  }

  public List<SharedLinkOtpAllowlistEntryWritable> getAllowlist() {
    return allowlist;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SharedLinkOtpWritableAllowlist casted = (SharedLinkOtpWritableAllowlist) o;
    return Objects.equals(mode, casted.mode) && Objects.equals(allowlist, casted.allowlist);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mode, allowlist);
  }

  @Override
  public String toString() {
    return "SharedLinkOtpWritableAllowlist{"
        + "mode='"
        + mode
        + '\''
        + ", "
        + "allowlist='"
        + allowlist
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<SharedLinkOtpWritableAllowlistModeField> mode;

    protected final List<SharedLinkOtpAllowlistEntryWritable> allowlist;

    public Builder(List<SharedLinkOtpAllowlistEntryWritable> allowlist) {
      super();
      this.allowlist = allowlist;
    }

    public Builder mode(SharedLinkOtpWritableAllowlistModeField mode) {
      this.mode = new EnumWrapper<SharedLinkOtpWritableAllowlistModeField>(mode);
      return this;
    }

    public Builder mode(EnumWrapper<SharedLinkOtpWritableAllowlistModeField> mode) {
      this.mode = mode;
      return this;
    }

    public SharedLinkOtpWritableAllowlist build() {
      if (this.mode == null) {
        this.mode =
            new EnumWrapper<SharedLinkOtpWritableAllowlistModeField>(
                SharedLinkOtpWritableAllowlistModeField.ALLOWLIST);
      }
      return new SharedLinkOtpWritableAllowlist(this);
    }
  }
}
