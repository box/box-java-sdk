package com.box.sdkgen.schemas.v2025r0.externalcollabsecuritysettingsv2025r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

/** External collaboration security settings. */
@JsonFilter("nullablePropertyFilter")
public class ExternalCollabSecuritySettingsV2025R0 extends SerializableObject {

  /**
   * List of domains that are not allowed for external collaboration. Applies if state is
   * `denylist`.
   */
  @JsonProperty("denylist_domains")
  protected List<String> denylistDomains;

  /**
   * List of email addresses that are not allowed for external collaboration. Applies if state is
   * `denylist`.
   */
  @JsonProperty("denylist_emails")
  protected List<String> denylistEmails;

  /**
   * List of domains that are allowed for external collaboration. Applies if state is `allowlist`.
   */
  @JsonProperty("allowlist_domains")
  protected List<String> allowlistDomains;

  /**
   * List of email addresses that are allowed for external collaboration. Applies if state is
   * `allowlist`.
   */
  @JsonProperty("allowlist_emails")
  protected List<String> allowlistEmails;

  /**
   * The state of the external collaboration security settings. Possible values include `enabled`,
   * `disabled`, `allowlist`, and `denylist`.
   */
  @Nullable protected String state;

  /**
   * The status of the scheduling to apply external collaboration security settings. Possible values
   * include `in_progress`, `scheduled`, `completed`, `failed`, and `scheduled_immediate`.
   */
  @JsonProperty("scheduled_status")
  @Nullable
  protected String scheduledStatus;

  /** Scheduled at. */
  @JsonProperty("scheduled_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected OffsetDateTime scheduledAt;

  /**
   * Factor type for the external collaborators authentication. Possible values include `totp`,
   * `any`, or `unknown`.
   */
  @JsonProperty("factor_type_settings")
  @Nullable
  protected String factorTypeSettings;

  public ExternalCollabSecuritySettingsV2025R0() {
    super();
  }

  protected ExternalCollabSecuritySettingsV2025R0(Builder builder) {
    super();
    this.denylistDomains = builder.denylistDomains;
    this.denylistEmails = builder.denylistEmails;
    this.allowlistDomains = builder.allowlistDomains;
    this.allowlistEmails = builder.allowlistEmails;
    this.state = builder.state;
    this.scheduledStatus = builder.scheduledStatus;
    this.scheduledAt = builder.scheduledAt;
    this.factorTypeSettings = builder.factorTypeSettings;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<String> getDenylistDomains() {
    return denylistDomains;
  }

  public List<String> getDenylistEmails() {
    return denylistEmails;
  }

  public List<String> getAllowlistDomains() {
    return allowlistDomains;
  }

  public List<String> getAllowlistEmails() {
    return allowlistEmails;
  }

  public String getState() {
    return state;
  }

  public String getScheduledStatus() {
    return scheduledStatus;
  }

  public OffsetDateTime getScheduledAt() {
    return scheduledAt;
  }

  public String getFactorTypeSettings() {
    return factorTypeSettings;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExternalCollabSecuritySettingsV2025R0 casted = (ExternalCollabSecuritySettingsV2025R0) o;
    return Objects.equals(denylistDomains, casted.denylistDomains)
        && Objects.equals(denylistEmails, casted.denylistEmails)
        && Objects.equals(allowlistDomains, casted.allowlistDomains)
        && Objects.equals(allowlistEmails, casted.allowlistEmails)
        && Objects.equals(state, casted.state)
        && Objects.equals(scheduledStatus, casted.scheduledStatus)
        && Objects.equals(scheduledAt, casted.scheduledAt)
        && Objects.equals(factorTypeSettings, casted.factorTypeSettings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        denylistDomains,
        denylistEmails,
        allowlistDomains,
        allowlistEmails,
        state,
        scheduledStatus,
        scheduledAt,
        factorTypeSettings);
  }

  @Override
  public String toString() {
    return "ExternalCollabSecuritySettingsV2025R0{"
        + "denylistDomains='"
        + denylistDomains
        + '\''
        + ", "
        + "denylistEmails='"
        + denylistEmails
        + '\''
        + ", "
        + "allowlistDomains='"
        + allowlistDomains
        + '\''
        + ", "
        + "allowlistEmails='"
        + allowlistEmails
        + '\''
        + ", "
        + "state='"
        + state
        + '\''
        + ", "
        + "scheduledStatus='"
        + scheduledStatus
        + '\''
        + ", "
        + "scheduledAt='"
        + scheduledAt
        + '\''
        + ", "
        + "factorTypeSettings='"
        + factorTypeSettings
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected List<String> denylistDomains;

    protected List<String> denylistEmails;

    protected List<String> allowlistDomains;

    protected List<String> allowlistEmails;

    protected String state;

    protected String scheduledStatus;

    protected OffsetDateTime scheduledAt;

    protected String factorTypeSettings;

    public Builder denylistDomains(List<String> denylistDomains) {
      this.denylistDomains = denylistDomains;
      return this;
    }

    public Builder denylistEmails(List<String> denylistEmails) {
      this.denylistEmails = denylistEmails;
      return this;
    }

    public Builder allowlistDomains(List<String> allowlistDomains) {
      this.allowlistDomains = allowlistDomains;
      return this;
    }

    public Builder allowlistEmails(List<String> allowlistEmails) {
      this.allowlistEmails = allowlistEmails;
      return this;
    }

    public Builder state(String state) {
      this.state = state;
      this.markNullableFieldAsSet("state");
      return this;
    }

    public Builder scheduledStatus(String scheduledStatus) {
      this.scheduledStatus = scheduledStatus;
      this.markNullableFieldAsSet("scheduled_status");
      return this;
    }

    public Builder scheduledAt(OffsetDateTime scheduledAt) {
      this.scheduledAt = scheduledAt;
      this.markNullableFieldAsSet("scheduled_at");
      return this;
    }

    public Builder factorTypeSettings(String factorTypeSettings) {
      this.factorTypeSettings = factorTypeSettings;
      this.markNullableFieldAsSet("factor_type_settings");
      return this;
    }

    public ExternalCollabSecuritySettingsV2025R0 build() {
      return new ExternalCollabSecuritySettingsV2025R0(this);
    }
  }
}
