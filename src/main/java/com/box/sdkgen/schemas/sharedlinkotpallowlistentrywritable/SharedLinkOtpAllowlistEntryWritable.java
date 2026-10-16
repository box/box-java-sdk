package com.box.sdkgen.schemas.sharedlinkotpallowlistentrywritable;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * An entry used to write the OTP allowlist for a shared link. The entry can match either a specific
 * email address or every email address that belongs to a domain.
 */
@JsonFilter("nullablePropertyFilter")
public class SharedLinkOtpAllowlistEntryWritable extends SerializableObject {

  /**
   * The kind of allowlist entry.
   *
   * <p>* `email` - The entry matches a single email address. * `domain` - The entry matches every
   * email address under the given domain.
   */
  @JsonDeserialize(
      using =
          SharedLinkOtpAllowlistEntryWritableTypeField
              .SharedLinkOtpAllowlistEntryWritableTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          SharedLinkOtpAllowlistEntryWritableTypeField
              .SharedLinkOtpAllowlistEntryWritableTypeFieldSerializer.class)
  protected final EnumWrapper<SharedLinkOtpAllowlistEntryWritableTypeField> type;

  /**
   * The value of the allowlist entry.
   *
   * <p>* For `email` entries, the full email address (must contain `&#64;`). * For `domain`
   * entries, the bare domain (e.g. `example.com`).
   */
  protected final String value;

  public SharedLinkOtpAllowlistEntryWritable(
      SharedLinkOtpAllowlistEntryWritableTypeField type, String value) {
    super();
    this.type = new EnumWrapper<SharedLinkOtpAllowlistEntryWritableTypeField>(type);
    this.value = value;
  }

  public SharedLinkOtpAllowlistEntryWritable(
      @JsonProperty("type") EnumWrapper<SharedLinkOtpAllowlistEntryWritableTypeField> type,
      @JsonProperty("value") String value) {
    super();
    this.type = type;
    this.value = value;
  }

  public EnumWrapper<SharedLinkOtpAllowlistEntryWritableTypeField> getType() {
    return type;
  }

  public String getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SharedLinkOtpAllowlistEntryWritable casted = (SharedLinkOtpAllowlistEntryWritable) o;
    return Objects.equals(type, casted.type) && Objects.equals(value, casted.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, value);
  }

  @Override
  public String toString() {
    return "SharedLinkOtpAllowlistEntryWritable{"
        + "type='"
        + type
        + '\''
        + ", "
        + "value='"
        + value
        + '\''
        + "}";
  }
}
