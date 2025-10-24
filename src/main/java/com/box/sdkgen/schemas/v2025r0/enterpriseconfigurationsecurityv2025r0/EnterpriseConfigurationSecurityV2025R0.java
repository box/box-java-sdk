package com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationsecurityv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationitembooleanv2025r0.EnterpriseConfigurationItemBooleanV2025R0;
import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationitemintegerv2025r0.EnterpriseConfigurationItemIntegerV2025R0;
import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationitemstringv2025r0.EnterpriseConfigurationItemStringV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** The enterprise configuration for the security category. */
@JsonFilter("nullablePropertyFilter")
public class EnterpriseConfigurationSecurityV2025R0 extends SerializableObject {

  @JsonProperty("is_managed_user_signup_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isManagedUserSignupEnabled;

  @JsonProperty("is_managed_user_signup_notification_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isManagedUserSignupNotificationEnabled;

  @JsonProperty("is_managed_user_signup_corporate_email_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isManagedUserSignupCorporateEmailEnabled;

  @JsonProperty("is_new_user_notification_daily_digest_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isNewUserNotificationDailyDigestEnabled;

  @JsonProperty("is_managed_user_email_change_disabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isManagedUserEmailChangeDisabled;

  @JsonProperty("is_multi_factor_auth_required")
  protected EnterpriseConfigurationItemBooleanV2025R0 isMultiFactorAuthRequired;

  @JsonProperty("is_weak_password_prevention_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isWeakPasswordPreventionEnabled;

  @JsonProperty("is_password_leak_detection_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isPasswordLeakDetectionEnabled;

  @JsonProperty("last_password_reset_at")
  protected EnterpriseConfigurationSecurityV2025R0LastPasswordResetAtField lastPasswordResetAt;

  @JsonProperty("is_password_request_notification_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isPasswordRequestNotificationEnabled;

  @JsonProperty("is_password_change_notification_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isPasswordChangeNotificationEnabled;

  @JsonProperty("is_strong_password_for_ext_collab_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isStrongPasswordForExtCollabEnabled;

  @JsonProperty("is_managed_user_migration_disabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isManagedUserMigrationDisabled;

  @JsonProperty("join_link")
  protected EnterpriseConfigurationItemStringV2025R0 joinLink;

  @JsonProperty("join_url")
  protected EnterpriseConfigurationItemStringV2025R0 joinUrl;

  @JsonProperty("failed_login_attempts_to_trigger_admin_notification")
  protected EnterpriseConfigurationItemIntegerV2025R0 failedLoginAttemptsToTriggerAdminNotification;

  @JsonProperty("password_min_length")
  protected EnterpriseConfigurationItemIntegerV2025R0 passwordMinLength;

  @JsonProperty("password_min_uppercase_characters")
  protected EnterpriseConfigurationItemIntegerV2025R0 passwordMinUppercaseCharacters;

  @JsonProperty("password_min_numeric_characters")
  protected EnterpriseConfigurationItemIntegerV2025R0 passwordMinNumericCharacters;

  @JsonProperty("password_min_special_characters")
  protected EnterpriseConfigurationItemIntegerV2025R0 passwordMinSpecialCharacters;

  @JsonProperty("password_reset_frequency")
  protected EnterpriseConfigurationItemStringV2025R0 passwordResetFrequency;

  @JsonProperty("previous_password_reuse_limit")
  protected EnterpriseConfigurationItemStringV2025R0 previousPasswordReuseLimit;

  @JsonProperty("session_duration")
  protected EnterpriseConfigurationItemStringV2025R0 sessionDuration;

  @JsonProperty("external_collab_multi_factor_auth_settings")
  protected EnterpriseConfigurationSecurityV2025R0ExternalCollabMultiFactorAuthSettingsField
      externalCollabMultiFactorAuthSettings;

  protected EnterpriseConfigurationSecurityV2025R0KeysafeField keysafe;

  @JsonProperty("is_custom_session_duration_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isCustomSessionDurationEnabled;

  @JsonProperty("custom_session_duration_value")
  protected EnterpriseConfigurationItemStringV2025R0 customSessionDurationValue;

  @JsonProperty("custom_session_duration_groups")
  protected EnterpriseConfigurationSecurityV2025R0CustomSessionDurationGroupsField
      customSessionDurationGroups;

  @JsonProperty("multi_factor_auth_type")
  protected EnterpriseConfigurationItemStringV2025R0 multiFactorAuthType;

  @JsonProperty("enforced_mfa_frequency")
  protected EnterpriseConfigurationSecurityV2025R0EnforcedMfaFrequencyField enforcedMfaFrequency;

  public EnterpriseConfigurationSecurityV2025R0() {
    super();
  }

  protected EnterpriseConfigurationSecurityV2025R0(Builder builder) {
    super();
    this.isManagedUserSignupEnabled = builder.isManagedUserSignupEnabled;
    this.isManagedUserSignupNotificationEnabled = builder.isManagedUserSignupNotificationEnabled;
    this.isManagedUserSignupCorporateEmailEnabled =
        builder.isManagedUserSignupCorporateEmailEnabled;
    this.isNewUserNotificationDailyDigestEnabled = builder.isNewUserNotificationDailyDigestEnabled;
    this.isManagedUserEmailChangeDisabled = builder.isManagedUserEmailChangeDisabled;
    this.isMultiFactorAuthRequired = builder.isMultiFactorAuthRequired;
    this.isWeakPasswordPreventionEnabled = builder.isWeakPasswordPreventionEnabled;
    this.isPasswordLeakDetectionEnabled = builder.isPasswordLeakDetectionEnabled;
    this.lastPasswordResetAt = builder.lastPasswordResetAt;
    this.isPasswordRequestNotificationEnabled = builder.isPasswordRequestNotificationEnabled;
    this.isPasswordChangeNotificationEnabled = builder.isPasswordChangeNotificationEnabled;
    this.isStrongPasswordForExtCollabEnabled = builder.isStrongPasswordForExtCollabEnabled;
    this.isManagedUserMigrationDisabled = builder.isManagedUserMigrationDisabled;
    this.joinLink = builder.joinLink;
    this.joinUrl = builder.joinUrl;
    this.failedLoginAttemptsToTriggerAdminNotification =
        builder.failedLoginAttemptsToTriggerAdminNotification;
    this.passwordMinLength = builder.passwordMinLength;
    this.passwordMinUppercaseCharacters = builder.passwordMinUppercaseCharacters;
    this.passwordMinNumericCharacters = builder.passwordMinNumericCharacters;
    this.passwordMinSpecialCharacters = builder.passwordMinSpecialCharacters;
    this.passwordResetFrequency = builder.passwordResetFrequency;
    this.previousPasswordReuseLimit = builder.previousPasswordReuseLimit;
    this.sessionDuration = builder.sessionDuration;
    this.externalCollabMultiFactorAuthSettings = builder.externalCollabMultiFactorAuthSettings;
    this.keysafe = builder.keysafe;
    this.isCustomSessionDurationEnabled = builder.isCustomSessionDurationEnabled;
    this.customSessionDurationValue = builder.customSessionDurationValue;
    this.customSessionDurationGroups = builder.customSessionDurationGroups;
    this.multiFactorAuthType = builder.multiFactorAuthType;
    this.enforcedMfaFrequency = builder.enforcedMfaFrequency;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsManagedUserSignupEnabled() {
    return isManagedUserSignupEnabled;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsManagedUserSignupNotificationEnabled() {
    return isManagedUserSignupNotificationEnabled;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsManagedUserSignupCorporateEmailEnabled() {
    return isManagedUserSignupCorporateEmailEnabled;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsNewUserNotificationDailyDigestEnabled() {
    return isNewUserNotificationDailyDigestEnabled;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsManagedUserEmailChangeDisabled() {
    return isManagedUserEmailChangeDisabled;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsMultiFactorAuthRequired() {
    return isMultiFactorAuthRequired;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsWeakPasswordPreventionEnabled() {
    return isWeakPasswordPreventionEnabled;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsPasswordLeakDetectionEnabled() {
    return isPasswordLeakDetectionEnabled;
  }

  public EnterpriseConfigurationSecurityV2025R0LastPasswordResetAtField getLastPasswordResetAt() {
    return lastPasswordResetAt;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsPasswordRequestNotificationEnabled() {
    return isPasswordRequestNotificationEnabled;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsPasswordChangeNotificationEnabled() {
    return isPasswordChangeNotificationEnabled;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsStrongPasswordForExtCollabEnabled() {
    return isStrongPasswordForExtCollabEnabled;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsManagedUserMigrationDisabled() {
    return isManagedUserMigrationDisabled;
  }

  public EnterpriseConfigurationItemStringV2025R0 getJoinLink() {
    return joinLink;
  }

  public EnterpriseConfigurationItemStringV2025R0 getJoinUrl() {
    return joinUrl;
  }

  public EnterpriseConfigurationItemIntegerV2025R0
      getFailedLoginAttemptsToTriggerAdminNotification() {
    return failedLoginAttemptsToTriggerAdminNotification;
  }

  public EnterpriseConfigurationItemIntegerV2025R0 getPasswordMinLength() {
    return passwordMinLength;
  }

  public EnterpriseConfigurationItemIntegerV2025R0 getPasswordMinUppercaseCharacters() {
    return passwordMinUppercaseCharacters;
  }

  public EnterpriseConfigurationItemIntegerV2025R0 getPasswordMinNumericCharacters() {
    return passwordMinNumericCharacters;
  }

  public EnterpriseConfigurationItemIntegerV2025R0 getPasswordMinSpecialCharacters() {
    return passwordMinSpecialCharacters;
  }

  public EnterpriseConfigurationItemStringV2025R0 getPasswordResetFrequency() {
    return passwordResetFrequency;
  }

  public EnterpriseConfigurationItemStringV2025R0 getPreviousPasswordReuseLimit() {
    return previousPasswordReuseLimit;
  }

  public EnterpriseConfigurationItemStringV2025R0 getSessionDuration() {
    return sessionDuration;
  }

  public EnterpriseConfigurationSecurityV2025R0ExternalCollabMultiFactorAuthSettingsField
      getExternalCollabMultiFactorAuthSettings() {
    return externalCollabMultiFactorAuthSettings;
  }

  public EnterpriseConfigurationSecurityV2025R0KeysafeField getKeysafe() {
    return keysafe;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsCustomSessionDurationEnabled() {
    return isCustomSessionDurationEnabled;
  }

  public EnterpriseConfigurationItemStringV2025R0 getCustomSessionDurationValue() {
    return customSessionDurationValue;
  }

  public EnterpriseConfigurationSecurityV2025R0CustomSessionDurationGroupsField
      getCustomSessionDurationGroups() {
    return customSessionDurationGroups;
  }

  public EnterpriseConfigurationItemStringV2025R0 getMultiFactorAuthType() {
    return multiFactorAuthType;
  }

  public EnterpriseConfigurationSecurityV2025R0EnforcedMfaFrequencyField getEnforcedMfaFrequency() {
    return enforcedMfaFrequency;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EnterpriseConfigurationSecurityV2025R0 casted = (EnterpriseConfigurationSecurityV2025R0) o;
    return Objects.equals(isManagedUserSignupEnabled, casted.isManagedUserSignupEnabled)
        && Objects.equals(
            isManagedUserSignupNotificationEnabled, casted.isManagedUserSignupNotificationEnabled)
        && Objects.equals(
            isManagedUserSignupCorporateEmailEnabled,
            casted.isManagedUserSignupCorporateEmailEnabled)
        && Objects.equals(
            isNewUserNotificationDailyDigestEnabled, casted.isNewUserNotificationDailyDigestEnabled)
        && Objects.equals(isManagedUserEmailChangeDisabled, casted.isManagedUserEmailChangeDisabled)
        && Objects.equals(isMultiFactorAuthRequired, casted.isMultiFactorAuthRequired)
        && Objects.equals(isWeakPasswordPreventionEnabled, casted.isWeakPasswordPreventionEnabled)
        && Objects.equals(isPasswordLeakDetectionEnabled, casted.isPasswordLeakDetectionEnabled)
        && Objects.equals(lastPasswordResetAt, casted.lastPasswordResetAt)
        && Objects.equals(
            isPasswordRequestNotificationEnabled, casted.isPasswordRequestNotificationEnabled)
        && Objects.equals(
            isPasswordChangeNotificationEnabled, casted.isPasswordChangeNotificationEnabled)
        && Objects.equals(
            isStrongPasswordForExtCollabEnabled, casted.isStrongPasswordForExtCollabEnabled)
        && Objects.equals(isManagedUserMigrationDisabled, casted.isManagedUserMigrationDisabled)
        && Objects.equals(joinLink, casted.joinLink)
        && Objects.equals(joinUrl, casted.joinUrl)
        && Objects.equals(
            failedLoginAttemptsToTriggerAdminNotification,
            casted.failedLoginAttemptsToTriggerAdminNotification)
        && Objects.equals(passwordMinLength, casted.passwordMinLength)
        && Objects.equals(passwordMinUppercaseCharacters, casted.passwordMinUppercaseCharacters)
        && Objects.equals(passwordMinNumericCharacters, casted.passwordMinNumericCharacters)
        && Objects.equals(passwordMinSpecialCharacters, casted.passwordMinSpecialCharacters)
        && Objects.equals(passwordResetFrequency, casted.passwordResetFrequency)
        && Objects.equals(previousPasswordReuseLimit, casted.previousPasswordReuseLimit)
        && Objects.equals(sessionDuration, casted.sessionDuration)
        && Objects.equals(
            externalCollabMultiFactorAuthSettings, casted.externalCollabMultiFactorAuthSettings)
        && Objects.equals(keysafe, casted.keysafe)
        && Objects.equals(isCustomSessionDurationEnabled, casted.isCustomSessionDurationEnabled)
        && Objects.equals(customSessionDurationValue, casted.customSessionDurationValue)
        && Objects.equals(customSessionDurationGroups, casted.customSessionDurationGroups)
        && Objects.equals(multiFactorAuthType, casted.multiFactorAuthType)
        && Objects.equals(enforcedMfaFrequency, casted.enforcedMfaFrequency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        isManagedUserSignupEnabled,
        isManagedUserSignupNotificationEnabled,
        isManagedUserSignupCorporateEmailEnabled,
        isNewUserNotificationDailyDigestEnabled,
        isManagedUserEmailChangeDisabled,
        isMultiFactorAuthRequired,
        isWeakPasswordPreventionEnabled,
        isPasswordLeakDetectionEnabled,
        lastPasswordResetAt,
        isPasswordRequestNotificationEnabled,
        isPasswordChangeNotificationEnabled,
        isStrongPasswordForExtCollabEnabled,
        isManagedUserMigrationDisabled,
        joinLink,
        joinUrl,
        failedLoginAttemptsToTriggerAdminNotification,
        passwordMinLength,
        passwordMinUppercaseCharacters,
        passwordMinNumericCharacters,
        passwordMinSpecialCharacters,
        passwordResetFrequency,
        previousPasswordReuseLimit,
        sessionDuration,
        externalCollabMultiFactorAuthSettings,
        keysafe,
        isCustomSessionDurationEnabled,
        customSessionDurationValue,
        customSessionDurationGroups,
        multiFactorAuthType,
        enforcedMfaFrequency);
  }

  @Override
  public String toString() {
    return "EnterpriseConfigurationSecurityV2025R0{"
        + "isManagedUserSignupEnabled='"
        + isManagedUserSignupEnabled
        + '\''
        + ", "
        + "isManagedUserSignupNotificationEnabled='"
        + isManagedUserSignupNotificationEnabled
        + '\''
        + ", "
        + "isManagedUserSignupCorporateEmailEnabled='"
        + isManagedUserSignupCorporateEmailEnabled
        + '\''
        + ", "
        + "isNewUserNotificationDailyDigestEnabled='"
        + isNewUserNotificationDailyDigestEnabled
        + '\''
        + ", "
        + "isManagedUserEmailChangeDisabled='"
        + isManagedUserEmailChangeDisabled
        + '\''
        + ", "
        + "isMultiFactorAuthRequired='"
        + isMultiFactorAuthRequired
        + '\''
        + ", "
        + "isWeakPasswordPreventionEnabled='"
        + isWeakPasswordPreventionEnabled
        + '\''
        + ", "
        + "isPasswordLeakDetectionEnabled='"
        + isPasswordLeakDetectionEnabled
        + '\''
        + ", "
        + "lastPasswordResetAt='"
        + lastPasswordResetAt
        + '\''
        + ", "
        + "isPasswordRequestNotificationEnabled='"
        + isPasswordRequestNotificationEnabled
        + '\''
        + ", "
        + "isPasswordChangeNotificationEnabled='"
        + isPasswordChangeNotificationEnabled
        + '\''
        + ", "
        + "isStrongPasswordForExtCollabEnabled='"
        + isStrongPasswordForExtCollabEnabled
        + '\''
        + ", "
        + "isManagedUserMigrationDisabled='"
        + isManagedUserMigrationDisabled
        + '\''
        + ", "
        + "joinLink='"
        + joinLink
        + '\''
        + ", "
        + "joinUrl='"
        + joinUrl
        + '\''
        + ", "
        + "failedLoginAttemptsToTriggerAdminNotification='"
        + failedLoginAttemptsToTriggerAdminNotification
        + '\''
        + ", "
        + "passwordMinLength='"
        + passwordMinLength
        + '\''
        + ", "
        + "passwordMinUppercaseCharacters='"
        + passwordMinUppercaseCharacters
        + '\''
        + ", "
        + "passwordMinNumericCharacters='"
        + passwordMinNumericCharacters
        + '\''
        + ", "
        + "passwordMinSpecialCharacters='"
        + passwordMinSpecialCharacters
        + '\''
        + ", "
        + "passwordResetFrequency='"
        + passwordResetFrequency
        + '\''
        + ", "
        + "previousPasswordReuseLimit='"
        + previousPasswordReuseLimit
        + '\''
        + ", "
        + "sessionDuration='"
        + sessionDuration
        + '\''
        + ", "
        + "externalCollabMultiFactorAuthSettings='"
        + externalCollabMultiFactorAuthSettings
        + '\''
        + ", "
        + "keysafe='"
        + keysafe
        + '\''
        + ", "
        + "isCustomSessionDurationEnabled='"
        + isCustomSessionDurationEnabled
        + '\''
        + ", "
        + "customSessionDurationValue='"
        + customSessionDurationValue
        + '\''
        + ", "
        + "customSessionDurationGroups='"
        + customSessionDurationGroups
        + '\''
        + ", "
        + "multiFactorAuthType='"
        + multiFactorAuthType
        + '\''
        + ", "
        + "enforcedMfaFrequency='"
        + enforcedMfaFrequency
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnterpriseConfigurationItemBooleanV2025R0 isManagedUserSignupEnabled;

    protected EnterpriseConfigurationItemBooleanV2025R0 isManagedUserSignupNotificationEnabled;

    protected EnterpriseConfigurationItemBooleanV2025R0 isManagedUserSignupCorporateEmailEnabled;

    protected EnterpriseConfigurationItemBooleanV2025R0 isNewUserNotificationDailyDigestEnabled;

    protected EnterpriseConfigurationItemBooleanV2025R0 isManagedUserEmailChangeDisabled;

    protected EnterpriseConfigurationItemBooleanV2025R0 isMultiFactorAuthRequired;

    protected EnterpriseConfigurationItemBooleanV2025R0 isWeakPasswordPreventionEnabled;

    protected EnterpriseConfigurationItemBooleanV2025R0 isPasswordLeakDetectionEnabled;

    protected EnterpriseConfigurationSecurityV2025R0LastPasswordResetAtField lastPasswordResetAt;

    protected EnterpriseConfigurationItemBooleanV2025R0 isPasswordRequestNotificationEnabled;

    protected EnterpriseConfigurationItemBooleanV2025R0 isPasswordChangeNotificationEnabled;

    protected EnterpriseConfigurationItemBooleanV2025R0 isStrongPasswordForExtCollabEnabled;

    protected EnterpriseConfigurationItemBooleanV2025R0 isManagedUserMigrationDisabled;

    protected EnterpriseConfigurationItemStringV2025R0 joinLink;

    protected EnterpriseConfigurationItemStringV2025R0 joinUrl;

    protected EnterpriseConfigurationItemIntegerV2025R0
        failedLoginAttemptsToTriggerAdminNotification;

    protected EnterpriseConfigurationItemIntegerV2025R0 passwordMinLength;

    protected EnterpriseConfigurationItemIntegerV2025R0 passwordMinUppercaseCharacters;

    protected EnterpriseConfigurationItemIntegerV2025R0 passwordMinNumericCharacters;

    protected EnterpriseConfigurationItemIntegerV2025R0 passwordMinSpecialCharacters;

    protected EnterpriseConfigurationItemStringV2025R0 passwordResetFrequency;

    protected EnterpriseConfigurationItemStringV2025R0 previousPasswordReuseLimit;

    protected EnterpriseConfigurationItemStringV2025R0 sessionDuration;

    protected EnterpriseConfigurationSecurityV2025R0ExternalCollabMultiFactorAuthSettingsField
        externalCollabMultiFactorAuthSettings;

    protected EnterpriseConfigurationSecurityV2025R0KeysafeField keysafe;

    protected EnterpriseConfigurationItemBooleanV2025R0 isCustomSessionDurationEnabled;

    protected EnterpriseConfigurationItemStringV2025R0 customSessionDurationValue;

    protected EnterpriseConfigurationSecurityV2025R0CustomSessionDurationGroupsField
        customSessionDurationGroups;

    protected EnterpriseConfigurationItemStringV2025R0 multiFactorAuthType;

    protected EnterpriseConfigurationSecurityV2025R0EnforcedMfaFrequencyField enforcedMfaFrequency;

    public Builder isManagedUserSignupEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isManagedUserSignupEnabled) {
      this.isManagedUserSignupEnabled = isManagedUserSignupEnabled;
      return this;
    }

    public Builder isManagedUserSignupNotificationEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isManagedUserSignupNotificationEnabled) {
      this.isManagedUserSignupNotificationEnabled = isManagedUserSignupNotificationEnabled;
      return this;
    }

    public Builder isManagedUserSignupCorporateEmailEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isManagedUserSignupCorporateEmailEnabled) {
      this.isManagedUserSignupCorporateEmailEnabled = isManagedUserSignupCorporateEmailEnabled;
      return this;
    }

    public Builder isNewUserNotificationDailyDigestEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isNewUserNotificationDailyDigestEnabled) {
      this.isNewUserNotificationDailyDigestEnabled = isNewUserNotificationDailyDigestEnabled;
      return this;
    }

    public Builder isManagedUserEmailChangeDisabled(
        EnterpriseConfigurationItemBooleanV2025R0 isManagedUserEmailChangeDisabled) {
      this.isManagedUserEmailChangeDisabled = isManagedUserEmailChangeDisabled;
      return this;
    }

    public Builder isMultiFactorAuthRequired(
        EnterpriseConfigurationItemBooleanV2025R0 isMultiFactorAuthRequired) {
      this.isMultiFactorAuthRequired = isMultiFactorAuthRequired;
      return this;
    }

    public Builder isWeakPasswordPreventionEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isWeakPasswordPreventionEnabled) {
      this.isWeakPasswordPreventionEnabled = isWeakPasswordPreventionEnabled;
      return this;
    }

    public Builder isPasswordLeakDetectionEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isPasswordLeakDetectionEnabled) {
      this.isPasswordLeakDetectionEnabled = isPasswordLeakDetectionEnabled;
      return this;
    }

    public Builder lastPasswordResetAt(
        EnterpriseConfigurationSecurityV2025R0LastPasswordResetAtField lastPasswordResetAt) {
      this.lastPasswordResetAt = lastPasswordResetAt;
      return this;
    }

    public Builder isPasswordRequestNotificationEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isPasswordRequestNotificationEnabled) {
      this.isPasswordRequestNotificationEnabled = isPasswordRequestNotificationEnabled;
      return this;
    }

    public Builder isPasswordChangeNotificationEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isPasswordChangeNotificationEnabled) {
      this.isPasswordChangeNotificationEnabled = isPasswordChangeNotificationEnabled;
      return this;
    }

    public Builder isStrongPasswordForExtCollabEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isStrongPasswordForExtCollabEnabled) {
      this.isStrongPasswordForExtCollabEnabled = isStrongPasswordForExtCollabEnabled;
      return this;
    }

    public Builder isManagedUserMigrationDisabled(
        EnterpriseConfigurationItemBooleanV2025R0 isManagedUserMigrationDisabled) {
      this.isManagedUserMigrationDisabled = isManagedUserMigrationDisabled;
      return this;
    }

    public Builder joinLink(EnterpriseConfigurationItemStringV2025R0 joinLink) {
      this.joinLink = joinLink;
      return this;
    }

    public Builder joinUrl(EnterpriseConfigurationItemStringV2025R0 joinUrl) {
      this.joinUrl = joinUrl;
      return this;
    }

    public Builder failedLoginAttemptsToTriggerAdminNotification(
        EnterpriseConfigurationItemIntegerV2025R0 failedLoginAttemptsToTriggerAdminNotification) {
      this.failedLoginAttemptsToTriggerAdminNotification =
          failedLoginAttemptsToTriggerAdminNotification;
      return this;
    }

    public Builder passwordMinLength(EnterpriseConfigurationItemIntegerV2025R0 passwordMinLength) {
      this.passwordMinLength = passwordMinLength;
      return this;
    }

    public Builder passwordMinUppercaseCharacters(
        EnterpriseConfigurationItemIntegerV2025R0 passwordMinUppercaseCharacters) {
      this.passwordMinUppercaseCharacters = passwordMinUppercaseCharacters;
      return this;
    }

    public Builder passwordMinNumericCharacters(
        EnterpriseConfigurationItemIntegerV2025R0 passwordMinNumericCharacters) {
      this.passwordMinNumericCharacters = passwordMinNumericCharacters;
      return this;
    }

    public Builder passwordMinSpecialCharacters(
        EnterpriseConfigurationItemIntegerV2025R0 passwordMinSpecialCharacters) {
      this.passwordMinSpecialCharacters = passwordMinSpecialCharacters;
      return this;
    }

    public Builder passwordResetFrequency(
        EnterpriseConfigurationItemStringV2025R0 passwordResetFrequency) {
      this.passwordResetFrequency = passwordResetFrequency;
      return this;
    }

    public Builder previousPasswordReuseLimit(
        EnterpriseConfigurationItemStringV2025R0 previousPasswordReuseLimit) {
      this.previousPasswordReuseLimit = previousPasswordReuseLimit;
      return this;
    }

    public Builder sessionDuration(EnterpriseConfigurationItemStringV2025R0 sessionDuration) {
      this.sessionDuration = sessionDuration;
      return this;
    }

    public Builder externalCollabMultiFactorAuthSettings(
        EnterpriseConfigurationSecurityV2025R0ExternalCollabMultiFactorAuthSettingsField
            externalCollabMultiFactorAuthSettings) {
      this.externalCollabMultiFactorAuthSettings = externalCollabMultiFactorAuthSettings;
      return this;
    }

    public Builder keysafe(EnterpriseConfigurationSecurityV2025R0KeysafeField keysafe) {
      this.keysafe = keysafe;
      return this;
    }

    public Builder isCustomSessionDurationEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isCustomSessionDurationEnabled) {
      this.isCustomSessionDurationEnabled = isCustomSessionDurationEnabled;
      return this;
    }

    public Builder customSessionDurationValue(
        EnterpriseConfigurationItemStringV2025R0 customSessionDurationValue) {
      this.customSessionDurationValue = customSessionDurationValue;
      return this;
    }

    public Builder customSessionDurationGroups(
        EnterpriseConfigurationSecurityV2025R0CustomSessionDurationGroupsField
            customSessionDurationGroups) {
      this.customSessionDurationGroups = customSessionDurationGroups;
      return this;
    }

    public Builder multiFactorAuthType(
        EnterpriseConfigurationItemStringV2025R0 multiFactorAuthType) {
      this.multiFactorAuthType = multiFactorAuthType;
      return this;
    }

    public Builder enforcedMfaFrequency(
        EnterpriseConfigurationSecurityV2025R0EnforcedMfaFrequencyField enforcedMfaFrequency) {
      this.enforcedMfaFrequency = enforcedMfaFrequency;
      return this;
    }

    public EnterpriseConfigurationSecurityV2025R0 build() {
      return new EnterpriseConfigurationSecurityV2025R0(this);
    }
  }
}
