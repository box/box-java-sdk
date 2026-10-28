package com.box.sdkgen.schemas.v2026r0.shuttleagentcredentialsv2026r0;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * The installation credentials issued for a Windows shuttle system. The registration token is
 * pasted into the Windows agent installer to register the agent with its system.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttleAgentCredentialsV2026R0 extends SerializableObject {

  /** The installation token to paste into the Windows agent installer to register the agent. */
  @JsonProperty("registration_token")
  protected final String registrationToken;

  /** When the registration token expires and can no longer be used. */
  @JsonProperty("expires_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected final OffsetDateTime expiresAt;

  public ShuttleAgentCredentialsV2026R0(
      @JsonProperty("registration_token") String registrationToken,
      @JsonProperty("expires_at") OffsetDateTime expiresAt) {
    super();
    this.registrationToken = registrationToken;
    this.expiresAt = expiresAt;
  }

  public String getRegistrationToken() {
    return registrationToken;
  }

  public OffsetDateTime getExpiresAt() {
    return expiresAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttleAgentCredentialsV2026R0 casted = (ShuttleAgentCredentialsV2026R0) o;
    return Objects.equals(registrationToken, casted.registrationToken)
        && Objects.equals(expiresAt, casted.expiresAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(registrationToken, expiresAt);
  }

  @Override
  public String toString() {
    return "ShuttleAgentCredentialsV2026R0{"
        + "registrationToken='"
        + registrationToken
        + '\''
        + ", "
        + "expiresAt='"
        + expiresAt
        + '\''
        + "}";
  }
}
