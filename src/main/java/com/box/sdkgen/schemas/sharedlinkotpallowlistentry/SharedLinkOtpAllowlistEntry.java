package com.box.sdkgen.schemas.sharedlinkotpallowlistentry;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.userbase.UserBase;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * An entry on the OTP allowlist for a shared link. The entry can match either a specific email
 * address or every email address that belongs to a domain.
 */
@JsonFilter("nullablePropertyFilter")
public class SharedLinkOtpAllowlistEntry extends SerializableObject {

  /**
   * The kind of allowlist entry.
   *
   * <p>* `email` - The entry matches a single email address. * `domain` - The entry matches every
   * email address under the given domain.
   */
  @JsonDeserialize(
      using =
          SharedLinkOtpAllowlistEntryTypeField.SharedLinkOtpAllowlistEntryTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          SharedLinkOtpAllowlistEntryTypeField.SharedLinkOtpAllowlistEntryTypeFieldSerializer.class)
  protected final EnumWrapper<SharedLinkOtpAllowlistEntryTypeField> type;

  /**
   * The value of the allowlist entry.
   *
   * <p>* For `email` entries, the full email address (must contain `&#64;`). * For `domain`
   * entries, the bare domain (e.g. `example.com`).
   */
  protected final String value;

  /** The date and time when the allowlist entry was added. */
  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected final OffsetDateTime createdAt;

  @JsonProperty("created_by")
  protected final UserBase createdBy;

  public SharedLinkOtpAllowlistEntry(
      SharedLinkOtpAllowlistEntryTypeField type,
      String value,
      OffsetDateTime createdAt,
      UserBase createdBy) {
    super();
    this.type = new EnumWrapper<SharedLinkOtpAllowlistEntryTypeField>(type);
    this.value = value;
    this.createdAt = createdAt;
    this.createdBy = createdBy;
  }

  public SharedLinkOtpAllowlistEntry(
      @JsonProperty("type") EnumWrapper<SharedLinkOtpAllowlistEntryTypeField> type,
      @JsonProperty("value") String value,
      @JsonProperty("created_at") OffsetDateTime createdAt,
      @JsonProperty("created_by") UserBase createdBy) {
    super();
    this.type = type;
    this.value = value;
    this.createdAt = createdAt;
    this.createdBy = createdBy;
  }

  public EnumWrapper<SharedLinkOtpAllowlistEntryTypeField> getType() {
    return type;
  }

  public String getValue() {
    return value;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public UserBase getCreatedBy() {
    return createdBy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SharedLinkOtpAllowlistEntry casted = (SharedLinkOtpAllowlistEntry) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(value, casted.value)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(createdBy, casted.createdBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, value, createdAt, createdBy);
  }

  @Override
  public String toString() {
    return "SharedLinkOtpAllowlistEntry{"
        + "type='"
        + type
        + '\''
        + ", "
        + "value='"
        + value
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "createdBy='"
        + createdBy
        + '\''
        + "}";
  }
}
