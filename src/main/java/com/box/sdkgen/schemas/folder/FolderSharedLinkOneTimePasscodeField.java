package com.box.sdkgen.schemas.folder;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.sharedlinkotpallowlistentry.SharedLinkOtpAllowlistEntry;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FolderSharedLinkOneTimePasscodeField extends SerializableObject {

  /**
   * The current OTP enforcement mode for the shared link.
   *
   * <p>* `none` - OTP enforcement is turned off. The shared link is accessible without any
   * one-time-passcode verification. * `open` - Any anonymous recipient can request a
   * one-time-passcode to any email address and access the shared link after verifying it.
   * Collaborators and registered Box users access the link without being prompted. * `allowlist` -
   * Anonymous recipients can request a one-time-passcode only when they enter an email that matches
   * an entry on the `allowlist`.
   */
  protected final String mode;

  /**
   * The list of email and domain entries that are permitted to access the shared link when `mode`
   * is `allowlist`. Anonymous users whose email matches an entry can request and verify a
   * one-time-passcode.
   *
   * <p>Because the entries carry recipient email addresses and the identity of the user who added
   * them, this field is only returned to users with owner, co-owner, or editor access on the item.
   * For viewer collaborators and below the field is omitted.
   *
   * <p>An allowlist contains at most 1000 entries, matching the limit enforced when setting the
   * allowlist on a shared link.
   */
  protected List<SharedLinkOtpAllowlistEntry> allowlist;

  public FolderSharedLinkOneTimePasscodeField(@JsonProperty("mode") String mode) {
    super();
    this.mode = mode;
  }

  protected FolderSharedLinkOneTimePasscodeField(Builder builder) {
    super();
    this.mode = builder.mode;
    this.allowlist = builder.allowlist;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getMode() {
    return mode;
  }

  public List<SharedLinkOtpAllowlistEntry> getAllowlist() {
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
    FolderSharedLinkOneTimePasscodeField casted = (FolderSharedLinkOneTimePasscodeField) o;
    return Objects.equals(mode, casted.mode) && Objects.equals(allowlist, casted.allowlist);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mode, allowlist);
  }

  @Override
  public String toString() {
    return "FolderSharedLinkOneTimePasscodeField{"
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

    protected final String mode;

    protected List<SharedLinkOtpAllowlistEntry> allowlist;

    public Builder(String mode) {
      super();
      this.mode = mode;
    }

    public Builder allowlist(List<SharedLinkOtpAllowlistEntry> allowlist) {
      this.allowlist = allowlist;
      return this;
    }

    public FolderSharedLinkOneTimePasscodeField build() {
      return new FolderSharedLinkOneTimePasscodeField(this);
    }
  }
}
