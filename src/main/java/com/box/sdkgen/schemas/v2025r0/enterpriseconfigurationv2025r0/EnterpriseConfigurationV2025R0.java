package com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationcontentandsharingv2025r0.EnterpriseConfigurationContentAndSharingV2025R0;
import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationsecurityv2025r0.EnterpriseConfigurationSecurityV2025R0;
import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationshieldv2025r0.EnterpriseConfigurationShieldV2025R0;
import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationusersettingsv2025r0.EnterpriseConfigurationUserSettingsV2025R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** The enterprise configuration for a given enterprise. */
@JsonFilter("nullablePropertyFilter")
public class EnterpriseConfigurationV2025R0 extends SerializableObject {

  /** The identifier of the enterprise configuration which is the ID of the enterprise. */
  protected String id;

  /** The value will always be `enterprise_configuration`. */
  @JsonDeserialize(
      using =
          EnterpriseConfigurationV2025R0TypeField
              .EnterpriseConfigurationV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          EnterpriseConfigurationV2025R0TypeField.EnterpriseConfigurationV2025R0TypeFieldSerializer
              .class)
  protected EnumWrapper<EnterpriseConfigurationV2025R0TypeField> type;

  protected EnterpriseConfigurationSecurityV2025R0 security;

  @JsonProperty("content_and_sharing")
  protected EnterpriseConfigurationContentAndSharingV2025R0 contentAndSharing;

  @JsonProperty("user_settings")
  protected EnterpriseConfigurationUserSettingsV2025R0 userSettings;

  protected EnterpriseConfigurationShieldV2025R0 shield;

  public EnterpriseConfigurationV2025R0() {
    super();
  }

  protected EnterpriseConfigurationV2025R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.security = builder.security;
    this.contentAndSharing = builder.contentAndSharing;
    this.userSettings = builder.userSettings;
    this.shield = builder.shield;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<EnterpriseConfigurationV2025R0TypeField> getType() {
    return type;
  }

  public EnterpriseConfigurationSecurityV2025R0 getSecurity() {
    return security;
  }

  public EnterpriseConfigurationContentAndSharingV2025R0 getContentAndSharing() {
    return contentAndSharing;
  }

  public EnterpriseConfigurationUserSettingsV2025R0 getUserSettings() {
    return userSettings;
  }

  public EnterpriseConfigurationShieldV2025R0 getShield() {
    return shield;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EnterpriseConfigurationV2025R0 casted = (EnterpriseConfigurationV2025R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(security, casted.security)
        && Objects.equals(contentAndSharing, casted.contentAndSharing)
        && Objects.equals(userSettings, casted.userSettings)
        && Objects.equals(shield, casted.shield);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, security, contentAndSharing, userSettings, shield);
  }

  @Override
  public String toString() {
    return "EnterpriseConfigurationV2025R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "security='"
        + security
        + '\''
        + ", "
        + "contentAndSharing='"
        + contentAndSharing
        + '\''
        + ", "
        + "userSettings='"
        + userSettings
        + '\''
        + ", "
        + "shield='"
        + shield
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<EnterpriseConfigurationV2025R0TypeField> type;

    protected EnterpriseConfigurationSecurityV2025R0 security;

    protected EnterpriseConfigurationContentAndSharingV2025R0 contentAndSharing;

    protected EnterpriseConfigurationUserSettingsV2025R0 userSettings;

    protected EnterpriseConfigurationShieldV2025R0 shield;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(EnterpriseConfigurationV2025R0TypeField type) {
      this.type = new EnumWrapper<EnterpriseConfigurationV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<EnterpriseConfigurationV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder security(EnterpriseConfigurationSecurityV2025R0 security) {
      this.security = security;
      return this;
    }

    public Builder contentAndSharing(
        EnterpriseConfigurationContentAndSharingV2025R0 contentAndSharing) {
      this.contentAndSharing = contentAndSharing;
      return this;
    }

    public Builder userSettings(EnterpriseConfigurationUserSettingsV2025R0 userSettings) {
      this.userSettings = userSettings;
      return this;
    }

    public Builder shield(EnterpriseConfigurationShieldV2025R0 shield) {
      this.shield = shield;
      return this;
    }

    public EnterpriseConfigurationV2025R0 build() {
      return new EnterpriseConfigurationV2025R0(this);
    }
  }
}
