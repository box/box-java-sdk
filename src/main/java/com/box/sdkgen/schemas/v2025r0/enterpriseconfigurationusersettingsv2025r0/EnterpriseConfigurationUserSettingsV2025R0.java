package com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationusersettingsv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationitembooleanv2025r0.EnterpriseConfigurationItemBooleanV2025R0;
import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationitemintegerv2025r0.EnterpriseConfigurationItemIntegerV2025R0;
import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationitemstringv2025r0.EnterpriseConfigurationItemStringV2025R0;
import com.box.sdkgen.schemas.v2025r0.enterprisefeaturesettingsitemv2025r0.EnterpriseFeatureSettingsItemV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** The enterprise configuration for the user settings category. */
@JsonFilter("nullablePropertyFilter")
public class EnterpriseConfigurationUserSettingsV2025R0 extends SerializableObject {

  @JsonProperty("enterprise_feature_settings")
  protected List<EnterpriseFeatureSettingsItemV2025R0> enterpriseFeatureSettings;

  @JsonProperty("user_invites_expiration_time_frame")
  protected EnterpriseConfigurationItemStringV2025R0 userInvitesExpirationTimeFrame;

  @JsonProperty("is_username_change_restricted")
  protected EnterpriseConfigurationItemBooleanV2025R0 isUsernameChangeRestricted;

  @JsonProperty("is_box_sync_restricted_for_new_users")
  protected EnterpriseConfigurationItemBooleanV2025R0 isBoxSyncRestrictedForNewUsers;

  @JsonProperty("is_view_all_users_enabled_for_new_users")
  protected EnterpriseConfigurationItemBooleanV2025R0 isViewAllUsersEnabledForNewUsers;

  @JsonProperty("is_device_limit_exemption_enabled_for_new_users")
  protected EnterpriseConfigurationItemBooleanV2025R0 isDeviceLimitExemptionEnabledForNewUsers;

  @JsonProperty("is_external_collaboration_restricted_for_new_users")
  protected EnterpriseConfigurationItemBooleanV2025R0 isExternalCollaborationRestrictedForNewUsers;

  @JsonProperty("is_unlimited_storage_enabled_for_new_users")
  protected EnterpriseConfigurationItemBooleanV2025R0 isUnlimitedStorageEnabledForNewUsers;

  @JsonProperty("new_user_default_storage_limit")
  protected EnterpriseConfigurationItemIntegerV2025R0 newUserDefaultStorageLimit;

  @JsonProperty("new_user_default_timezone")
  protected EnterpriseConfigurationItemStringV2025R0 newUserDefaultTimezone;

  @JsonProperty("new_user_default_language")
  protected EnterpriseConfigurationItemStringV2025R0 newUserDefaultLanguage;

  @JsonProperty("is_enterprise_sso_required")
  protected EnterpriseConfigurationItemBooleanV2025R0 isEnterpriseSsoRequired;

  @JsonProperty("is_enterprise_sso_in_testing")
  protected EnterpriseConfigurationItemBooleanV2025R0 isEnterpriseSsoInTesting;

  @JsonProperty("is_sso_auto_add_groups_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isSsoAutoAddGroupsEnabled;

  @JsonProperty("is_sso_auto_add_user_to_groups_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isSsoAutoAddUserToGroupsEnabled;

  @JsonProperty("is_sso_auto_remove_user_from_groups_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isSsoAutoRemoveUserFromGroupsEnabled;

  @JsonProperty("user_tracking_codes")
  protected EnterpriseConfigurationUserSettingsV2025R0UserTrackingCodesField userTrackingCodes;

  @JsonProperty("number_of_user_tracking_codes_remaining")
  protected EnterpriseConfigurationItemIntegerV2025R0 numberOfUserTrackingCodesRemaining;

  @JsonProperty("is_instant_login_restricted")
  protected EnterpriseConfigurationItemBooleanV2025R0 isInstantLoginRestricted;

  public EnterpriseConfigurationUserSettingsV2025R0() {
    super();
  }

  protected EnterpriseConfigurationUserSettingsV2025R0(Builder builder) {
    super();
    this.enterpriseFeatureSettings = builder.enterpriseFeatureSettings;
    this.userInvitesExpirationTimeFrame = builder.userInvitesExpirationTimeFrame;
    this.isUsernameChangeRestricted = builder.isUsernameChangeRestricted;
    this.isBoxSyncRestrictedForNewUsers = builder.isBoxSyncRestrictedForNewUsers;
    this.isViewAllUsersEnabledForNewUsers = builder.isViewAllUsersEnabledForNewUsers;
    this.isDeviceLimitExemptionEnabledForNewUsers =
        builder.isDeviceLimitExemptionEnabledForNewUsers;
    this.isExternalCollaborationRestrictedForNewUsers =
        builder.isExternalCollaborationRestrictedForNewUsers;
    this.isUnlimitedStorageEnabledForNewUsers = builder.isUnlimitedStorageEnabledForNewUsers;
    this.newUserDefaultStorageLimit = builder.newUserDefaultStorageLimit;
    this.newUserDefaultTimezone = builder.newUserDefaultTimezone;
    this.newUserDefaultLanguage = builder.newUserDefaultLanguage;
    this.isEnterpriseSsoRequired = builder.isEnterpriseSsoRequired;
    this.isEnterpriseSsoInTesting = builder.isEnterpriseSsoInTesting;
    this.isSsoAutoAddGroupsEnabled = builder.isSsoAutoAddGroupsEnabled;
    this.isSsoAutoAddUserToGroupsEnabled = builder.isSsoAutoAddUserToGroupsEnabled;
    this.isSsoAutoRemoveUserFromGroupsEnabled = builder.isSsoAutoRemoveUserFromGroupsEnabled;
    this.userTrackingCodes = builder.userTrackingCodes;
    this.numberOfUserTrackingCodesRemaining = builder.numberOfUserTrackingCodesRemaining;
    this.isInstantLoginRestricted = builder.isInstantLoginRestricted;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<EnterpriseFeatureSettingsItemV2025R0> getEnterpriseFeatureSettings() {
    return enterpriseFeatureSettings;
  }

  public EnterpriseConfigurationItemStringV2025R0 getUserInvitesExpirationTimeFrame() {
    return userInvitesExpirationTimeFrame;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsUsernameChangeRestricted() {
    return isUsernameChangeRestricted;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsBoxSyncRestrictedForNewUsers() {
    return isBoxSyncRestrictedForNewUsers;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsViewAllUsersEnabledForNewUsers() {
    return isViewAllUsersEnabledForNewUsers;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsDeviceLimitExemptionEnabledForNewUsers() {
    return isDeviceLimitExemptionEnabledForNewUsers;
  }

  public EnterpriseConfigurationItemBooleanV2025R0
      getIsExternalCollaborationRestrictedForNewUsers() {
    return isExternalCollaborationRestrictedForNewUsers;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsUnlimitedStorageEnabledForNewUsers() {
    return isUnlimitedStorageEnabledForNewUsers;
  }

  public EnterpriseConfigurationItemIntegerV2025R0 getNewUserDefaultStorageLimit() {
    return newUserDefaultStorageLimit;
  }

  public EnterpriseConfigurationItemStringV2025R0 getNewUserDefaultTimezone() {
    return newUserDefaultTimezone;
  }

  public EnterpriseConfigurationItemStringV2025R0 getNewUserDefaultLanguage() {
    return newUserDefaultLanguage;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsEnterpriseSsoRequired() {
    return isEnterpriseSsoRequired;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsEnterpriseSsoInTesting() {
    return isEnterpriseSsoInTesting;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsSsoAutoAddGroupsEnabled() {
    return isSsoAutoAddGroupsEnabled;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsSsoAutoAddUserToGroupsEnabled() {
    return isSsoAutoAddUserToGroupsEnabled;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsSsoAutoRemoveUserFromGroupsEnabled() {
    return isSsoAutoRemoveUserFromGroupsEnabled;
  }

  public EnterpriseConfigurationUserSettingsV2025R0UserTrackingCodesField getUserTrackingCodes() {
    return userTrackingCodes;
  }

  public EnterpriseConfigurationItemIntegerV2025R0 getNumberOfUserTrackingCodesRemaining() {
    return numberOfUserTrackingCodesRemaining;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsInstantLoginRestricted() {
    return isInstantLoginRestricted;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EnterpriseConfigurationUserSettingsV2025R0 casted =
        (EnterpriseConfigurationUserSettingsV2025R0) o;
    return Objects.equals(enterpriseFeatureSettings, casted.enterpriseFeatureSettings)
        && Objects.equals(userInvitesExpirationTimeFrame, casted.userInvitesExpirationTimeFrame)
        && Objects.equals(isUsernameChangeRestricted, casted.isUsernameChangeRestricted)
        && Objects.equals(isBoxSyncRestrictedForNewUsers, casted.isBoxSyncRestrictedForNewUsers)
        && Objects.equals(isViewAllUsersEnabledForNewUsers, casted.isViewAllUsersEnabledForNewUsers)
        && Objects.equals(
            isDeviceLimitExemptionEnabledForNewUsers,
            casted.isDeviceLimitExemptionEnabledForNewUsers)
        && Objects.equals(
            isExternalCollaborationRestrictedForNewUsers,
            casted.isExternalCollaborationRestrictedForNewUsers)
        && Objects.equals(
            isUnlimitedStorageEnabledForNewUsers, casted.isUnlimitedStorageEnabledForNewUsers)
        && Objects.equals(newUserDefaultStorageLimit, casted.newUserDefaultStorageLimit)
        && Objects.equals(newUserDefaultTimezone, casted.newUserDefaultTimezone)
        && Objects.equals(newUserDefaultLanguage, casted.newUserDefaultLanguage)
        && Objects.equals(isEnterpriseSsoRequired, casted.isEnterpriseSsoRequired)
        && Objects.equals(isEnterpriseSsoInTesting, casted.isEnterpriseSsoInTesting)
        && Objects.equals(isSsoAutoAddGroupsEnabled, casted.isSsoAutoAddGroupsEnabled)
        && Objects.equals(isSsoAutoAddUserToGroupsEnabled, casted.isSsoAutoAddUserToGroupsEnabled)
        && Objects.equals(
            isSsoAutoRemoveUserFromGroupsEnabled, casted.isSsoAutoRemoveUserFromGroupsEnabled)
        && Objects.equals(userTrackingCodes, casted.userTrackingCodes)
        && Objects.equals(
            numberOfUserTrackingCodesRemaining, casted.numberOfUserTrackingCodesRemaining)
        && Objects.equals(isInstantLoginRestricted, casted.isInstantLoginRestricted);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        enterpriseFeatureSettings,
        userInvitesExpirationTimeFrame,
        isUsernameChangeRestricted,
        isBoxSyncRestrictedForNewUsers,
        isViewAllUsersEnabledForNewUsers,
        isDeviceLimitExemptionEnabledForNewUsers,
        isExternalCollaborationRestrictedForNewUsers,
        isUnlimitedStorageEnabledForNewUsers,
        newUserDefaultStorageLimit,
        newUserDefaultTimezone,
        newUserDefaultLanguage,
        isEnterpriseSsoRequired,
        isEnterpriseSsoInTesting,
        isSsoAutoAddGroupsEnabled,
        isSsoAutoAddUserToGroupsEnabled,
        isSsoAutoRemoveUserFromGroupsEnabled,
        userTrackingCodes,
        numberOfUserTrackingCodesRemaining,
        isInstantLoginRestricted);
  }

  @Override
  public String toString() {
    return "EnterpriseConfigurationUserSettingsV2025R0{"
        + "enterpriseFeatureSettings='"
        + enterpriseFeatureSettings
        + '\''
        + ", "
        + "userInvitesExpirationTimeFrame='"
        + userInvitesExpirationTimeFrame
        + '\''
        + ", "
        + "isUsernameChangeRestricted='"
        + isUsernameChangeRestricted
        + '\''
        + ", "
        + "isBoxSyncRestrictedForNewUsers='"
        + isBoxSyncRestrictedForNewUsers
        + '\''
        + ", "
        + "isViewAllUsersEnabledForNewUsers='"
        + isViewAllUsersEnabledForNewUsers
        + '\''
        + ", "
        + "isDeviceLimitExemptionEnabledForNewUsers='"
        + isDeviceLimitExemptionEnabledForNewUsers
        + '\''
        + ", "
        + "isExternalCollaborationRestrictedForNewUsers='"
        + isExternalCollaborationRestrictedForNewUsers
        + '\''
        + ", "
        + "isUnlimitedStorageEnabledForNewUsers='"
        + isUnlimitedStorageEnabledForNewUsers
        + '\''
        + ", "
        + "newUserDefaultStorageLimit='"
        + newUserDefaultStorageLimit
        + '\''
        + ", "
        + "newUserDefaultTimezone='"
        + newUserDefaultTimezone
        + '\''
        + ", "
        + "newUserDefaultLanguage='"
        + newUserDefaultLanguage
        + '\''
        + ", "
        + "isEnterpriseSsoRequired='"
        + isEnterpriseSsoRequired
        + '\''
        + ", "
        + "isEnterpriseSsoInTesting='"
        + isEnterpriseSsoInTesting
        + '\''
        + ", "
        + "isSsoAutoAddGroupsEnabled='"
        + isSsoAutoAddGroupsEnabled
        + '\''
        + ", "
        + "isSsoAutoAddUserToGroupsEnabled='"
        + isSsoAutoAddUserToGroupsEnabled
        + '\''
        + ", "
        + "isSsoAutoRemoveUserFromGroupsEnabled='"
        + isSsoAutoRemoveUserFromGroupsEnabled
        + '\''
        + ", "
        + "userTrackingCodes='"
        + userTrackingCodes
        + '\''
        + ", "
        + "numberOfUserTrackingCodesRemaining='"
        + numberOfUserTrackingCodesRemaining
        + '\''
        + ", "
        + "isInstantLoginRestricted='"
        + isInstantLoginRestricted
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected List<EnterpriseFeatureSettingsItemV2025R0> enterpriseFeatureSettings;

    protected EnterpriseConfigurationItemStringV2025R0 userInvitesExpirationTimeFrame;

    protected EnterpriseConfigurationItemBooleanV2025R0 isUsernameChangeRestricted;

    protected EnterpriseConfigurationItemBooleanV2025R0 isBoxSyncRestrictedForNewUsers;

    protected EnterpriseConfigurationItemBooleanV2025R0 isViewAllUsersEnabledForNewUsers;

    protected EnterpriseConfigurationItemBooleanV2025R0 isDeviceLimitExemptionEnabledForNewUsers;

    protected EnterpriseConfigurationItemBooleanV2025R0
        isExternalCollaborationRestrictedForNewUsers;

    protected EnterpriseConfigurationItemBooleanV2025R0 isUnlimitedStorageEnabledForNewUsers;

    protected EnterpriseConfigurationItemIntegerV2025R0 newUserDefaultStorageLimit;

    protected EnterpriseConfigurationItemStringV2025R0 newUserDefaultTimezone;

    protected EnterpriseConfigurationItemStringV2025R0 newUserDefaultLanguage;

    protected EnterpriseConfigurationItemBooleanV2025R0 isEnterpriseSsoRequired;

    protected EnterpriseConfigurationItemBooleanV2025R0 isEnterpriseSsoInTesting;

    protected EnterpriseConfigurationItemBooleanV2025R0 isSsoAutoAddGroupsEnabled;

    protected EnterpriseConfigurationItemBooleanV2025R0 isSsoAutoAddUserToGroupsEnabled;

    protected EnterpriseConfigurationItemBooleanV2025R0 isSsoAutoRemoveUserFromGroupsEnabled;

    protected EnterpriseConfigurationUserSettingsV2025R0UserTrackingCodesField userTrackingCodes;

    protected EnterpriseConfigurationItemIntegerV2025R0 numberOfUserTrackingCodesRemaining;

    protected EnterpriseConfigurationItemBooleanV2025R0 isInstantLoginRestricted;

    public Builder enterpriseFeatureSettings(
        List<EnterpriseFeatureSettingsItemV2025R0> enterpriseFeatureSettings) {
      this.enterpriseFeatureSettings = enterpriseFeatureSettings;
      return this;
    }

    public Builder userInvitesExpirationTimeFrame(
        EnterpriseConfigurationItemStringV2025R0 userInvitesExpirationTimeFrame) {
      this.userInvitesExpirationTimeFrame = userInvitesExpirationTimeFrame;
      return this;
    }

    public Builder isUsernameChangeRestricted(
        EnterpriseConfigurationItemBooleanV2025R0 isUsernameChangeRestricted) {
      this.isUsernameChangeRestricted = isUsernameChangeRestricted;
      return this;
    }

    public Builder isBoxSyncRestrictedForNewUsers(
        EnterpriseConfigurationItemBooleanV2025R0 isBoxSyncRestrictedForNewUsers) {
      this.isBoxSyncRestrictedForNewUsers = isBoxSyncRestrictedForNewUsers;
      return this;
    }

    public Builder isViewAllUsersEnabledForNewUsers(
        EnterpriseConfigurationItemBooleanV2025R0 isViewAllUsersEnabledForNewUsers) {
      this.isViewAllUsersEnabledForNewUsers = isViewAllUsersEnabledForNewUsers;
      return this;
    }

    public Builder isDeviceLimitExemptionEnabledForNewUsers(
        EnterpriseConfigurationItemBooleanV2025R0 isDeviceLimitExemptionEnabledForNewUsers) {
      this.isDeviceLimitExemptionEnabledForNewUsers = isDeviceLimitExemptionEnabledForNewUsers;
      return this;
    }

    public Builder isExternalCollaborationRestrictedForNewUsers(
        EnterpriseConfigurationItemBooleanV2025R0 isExternalCollaborationRestrictedForNewUsers) {
      this.isExternalCollaborationRestrictedForNewUsers =
          isExternalCollaborationRestrictedForNewUsers;
      return this;
    }

    public Builder isUnlimitedStorageEnabledForNewUsers(
        EnterpriseConfigurationItemBooleanV2025R0 isUnlimitedStorageEnabledForNewUsers) {
      this.isUnlimitedStorageEnabledForNewUsers = isUnlimitedStorageEnabledForNewUsers;
      return this;
    }

    public Builder newUserDefaultStorageLimit(
        EnterpriseConfigurationItemIntegerV2025R0 newUserDefaultStorageLimit) {
      this.newUserDefaultStorageLimit = newUserDefaultStorageLimit;
      return this;
    }

    public Builder newUserDefaultTimezone(
        EnterpriseConfigurationItemStringV2025R0 newUserDefaultTimezone) {
      this.newUserDefaultTimezone = newUserDefaultTimezone;
      return this;
    }

    public Builder newUserDefaultLanguage(
        EnterpriseConfigurationItemStringV2025R0 newUserDefaultLanguage) {
      this.newUserDefaultLanguage = newUserDefaultLanguage;
      return this;
    }

    public Builder isEnterpriseSsoRequired(
        EnterpriseConfigurationItemBooleanV2025R0 isEnterpriseSsoRequired) {
      this.isEnterpriseSsoRequired = isEnterpriseSsoRequired;
      return this;
    }

    public Builder isEnterpriseSsoInTesting(
        EnterpriseConfigurationItemBooleanV2025R0 isEnterpriseSsoInTesting) {
      this.isEnterpriseSsoInTesting = isEnterpriseSsoInTesting;
      return this;
    }

    public Builder isSsoAutoAddGroupsEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isSsoAutoAddGroupsEnabled) {
      this.isSsoAutoAddGroupsEnabled = isSsoAutoAddGroupsEnabled;
      return this;
    }

    public Builder isSsoAutoAddUserToGroupsEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isSsoAutoAddUserToGroupsEnabled) {
      this.isSsoAutoAddUserToGroupsEnabled = isSsoAutoAddUserToGroupsEnabled;
      return this;
    }

    public Builder isSsoAutoRemoveUserFromGroupsEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isSsoAutoRemoveUserFromGroupsEnabled) {
      this.isSsoAutoRemoveUserFromGroupsEnabled = isSsoAutoRemoveUserFromGroupsEnabled;
      return this;
    }

    public Builder userTrackingCodes(
        EnterpriseConfigurationUserSettingsV2025R0UserTrackingCodesField userTrackingCodes) {
      this.userTrackingCodes = userTrackingCodes;
      return this;
    }

    public Builder numberOfUserTrackingCodesRemaining(
        EnterpriseConfigurationItemIntegerV2025R0 numberOfUserTrackingCodesRemaining) {
      this.numberOfUserTrackingCodesRemaining = numberOfUserTrackingCodesRemaining;
      return this;
    }

    public Builder isInstantLoginRestricted(
        EnterpriseConfigurationItemBooleanV2025R0 isInstantLoginRestricted) {
      this.isInstantLoginRestricted = isInstantLoginRestricted;
      return this;
    }

    public EnterpriseConfigurationUserSettingsV2025R0 build() {
      return new EnterpriseConfigurationUserSettingsV2025R0(this);
    }
  }
}
