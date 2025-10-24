package com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationcontentandsharingv2025r0;

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

/** The enterprise configuration for the content and sharing category. */
@JsonFilter("nullablePropertyFilter")
public class EnterpriseConfigurationContentAndSharingV2025R0 extends SerializableObject {

  @JsonProperty("enterprise_feature_settings")
  protected List<EnterpriseFeatureSettingsItemV2025R0> enterpriseFeatureSettings;

  @JsonProperty("sharing_item_type")
  protected EnterpriseConfigurationItemStringV2025R0 sharingItemType;

  @JsonProperty("shared_link_company_definition")
  protected EnterpriseConfigurationItemStringV2025R0 sharedLinkCompanyDefinition;

  @JsonProperty("shared_link_access")
  protected EnterpriseConfigurationItemStringV2025R0 sharedLinkAccess;

  @JsonProperty("shared_link_default_access")
  protected EnterpriseConfigurationItemStringV2025R0 sharedLinkDefaultAccess;

  @JsonProperty("shared_link_default_permissions_selected")
  protected EnterpriseConfigurationContentAndSharingV2025R0SharedLinkDefaultPermissionsSelectedField
      sharedLinkDefaultPermissionsSelected;

  @JsonProperty("is_open_custom_urls_disabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isOpenCustomUrlsDisabled;

  @JsonProperty("is_custom_domain_hidden_in_shared_link")
  protected EnterpriseConfigurationItemBooleanV2025R0 isCustomDomainHiddenInSharedLink;

  @JsonProperty("collaboration_permissions")
  protected EnterpriseConfigurationContentAndSharingV2025R0CollaborationPermissionsField
      collaborationPermissions;

  @JsonProperty("default_collaboration_role")
  protected EnterpriseConfigurationItemStringV2025R0 defaultCollaborationRole;

  @JsonProperty("is_invite_privilege_restricted")
  protected EnterpriseConfigurationItemBooleanV2025R0 isInvitePrivilegeRestricted;

  @JsonProperty("collaboration_restrictions")
  protected EnterpriseConfigurationContentAndSharingV2025R0CollaborationRestrictionsField
      collaborationRestrictions;

  @JsonProperty("is_collaborator_invite_links_disabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isCollaboratorInviteLinksDisabled;

  @JsonProperty("is_invite_group_collaborator_disabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isInviteGroupCollaboratorDisabled;

  @JsonProperty("is_ownership_transfer_restricted")
  protected EnterpriseConfigurationItemBooleanV2025R0 isOwnershipTransferRestricted;

  @JsonProperty("external_collaboration_status")
  protected EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusField
      externalCollaborationStatus;

  @JsonProperty("external_collaboration_allowlist_users")
  protected EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationAllowlistUsersField
      externalCollaborationAllowlistUsers;

  @JsonProperty("is_watermarking_enterprise_feature_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isWatermarkingEnterpriseFeatureEnabled;

  @JsonProperty("is_root_content_creation_restricted")
  protected EnterpriseConfigurationItemBooleanV2025R0 isRootContentCreationRestricted;

  @JsonProperty("is_tag_creation_restricted")
  protected EnterpriseConfigurationItemBooleanV2025R0 isTagCreationRestricted;

  @JsonProperty("tag_creation_restriction")
  protected EnterpriseConfigurationItemStringV2025R0 tagCreationRestriction;

  @JsonProperty("is_email_uploads_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isEmailUploadsEnabled;

  @JsonProperty("is_custom_settings_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isCustomSettingsEnabled;

  @JsonProperty("is_forms_login_required")
  protected EnterpriseConfigurationItemBooleanV2025R0 isFormsLoginRequired;

  @JsonProperty("is_forms_branding_default_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isFormsBrandingDefaultEnabled;

  @JsonProperty("is_cc_free_trial_active")
  protected EnterpriseConfigurationItemBooleanV2025R0 isCcFreeTrialActive;

  @JsonProperty("is_file_request_editors_allowed")
  protected EnterpriseConfigurationItemBooleanV2025R0 isFileRequestEditorsAllowed;

  @JsonProperty("is_file_request_branding_default_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isFileRequestBrandingDefaultEnabled;

  @JsonProperty("is_file_request_login_required")
  protected EnterpriseConfigurationItemBooleanV2025R0 isFileRequestLoginRequired;

  @JsonProperty("is_shared_links_expiration_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isSharedLinksExpirationEnabled;

  @JsonProperty("shared_links_expiration_days")
  protected EnterpriseConfigurationItemIntegerV2025R0 sharedLinksExpirationDays;

  @JsonProperty("is_public_shared_links_expiration_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isPublicSharedLinksExpirationEnabled;

  @JsonProperty("public_shared_links_expiration_days")
  protected EnterpriseConfigurationItemIntegerV2025R0 publicSharedLinksExpirationDays;

  @JsonProperty("shared_expiration_target")
  protected EnterpriseConfigurationItemStringV2025R0 sharedExpirationTarget;

  @JsonProperty("is_shared_links_expiration_notification_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isSharedLinksExpirationNotificationEnabled;

  @JsonProperty("shared_links_expiration_notification_days")
  protected EnterpriseConfigurationItemIntegerV2025R0 sharedLinksExpirationNotificationDays;

  @JsonProperty("is_shared_links_expiration_notification_prevented")
  protected EnterpriseConfigurationItemBooleanV2025R0 isSharedLinksExpirationNotificationPrevented;

  @JsonProperty("is_auto_delete_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isAutoDeleteEnabled;

  @JsonProperty("auto_delete_days")
  protected EnterpriseConfigurationItemIntegerV2025R0 autoDeleteDays;

  @JsonProperty("is_auto_delete_expiration_modification_prevented")
  protected EnterpriseConfigurationItemBooleanV2025R0 isAutoDeleteExpirationModificationPrevented;

  @JsonProperty("auto_delete_target")
  protected EnterpriseConfigurationItemStringV2025R0 autoDeleteTarget;

  @JsonProperty("is_collaboration_expiration_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isCollaborationExpirationEnabled;

  @JsonProperty("collaboration_expiration_days")
  protected EnterpriseConfigurationItemIntegerV2025R0 collaborationExpirationDays;

  @JsonProperty("is_collaboration_expiration_modification_prevented")
  protected EnterpriseConfigurationItemBooleanV2025R0
      isCollaborationExpirationModificationPrevented;

  @JsonProperty("is_collaboration_expiration_notification_enabled")
  protected EnterpriseConfigurationItemBooleanV2025R0 isCollaborationExpirationNotificationEnabled;

  @JsonProperty("collaboration_expiration_target")
  protected EnterpriseConfigurationItemStringV2025R0 collaborationExpirationTarget;

  @JsonProperty("trash_auto_clear_time")
  protected EnterpriseConfigurationItemIntegerV2025R0 trashAutoClearTime;

  @JsonProperty("permanent_deletion_access")
  protected EnterpriseConfigurationItemStringV2025R0 permanentDeletionAccess;

  @JsonProperty("permanent_deletion_allowlist_users")
  protected EnterpriseConfigurationContentAndSharingV2025R0PermanentDeletionAllowlistUsersField
      permanentDeletionAllowlistUsers;

  public EnterpriseConfigurationContentAndSharingV2025R0() {
    super();
  }

  protected EnterpriseConfigurationContentAndSharingV2025R0(Builder builder) {
    super();
    this.enterpriseFeatureSettings = builder.enterpriseFeatureSettings;
    this.sharingItemType = builder.sharingItemType;
    this.sharedLinkCompanyDefinition = builder.sharedLinkCompanyDefinition;
    this.sharedLinkAccess = builder.sharedLinkAccess;
    this.sharedLinkDefaultAccess = builder.sharedLinkDefaultAccess;
    this.sharedLinkDefaultPermissionsSelected = builder.sharedLinkDefaultPermissionsSelected;
    this.isOpenCustomUrlsDisabled = builder.isOpenCustomUrlsDisabled;
    this.isCustomDomainHiddenInSharedLink = builder.isCustomDomainHiddenInSharedLink;
    this.collaborationPermissions = builder.collaborationPermissions;
    this.defaultCollaborationRole = builder.defaultCollaborationRole;
    this.isInvitePrivilegeRestricted = builder.isInvitePrivilegeRestricted;
    this.collaborationRestrictions = builder.collaborationRestrictions;
    this.isCollaboratorInviteLinksDisabled = builder.isCollaboratorInviteLinksDisabled;
    this.isInviteGroupCollaboratorDisabled = builder.isInviteGroupCollaboratorDisabled;
    this.isOwnershipTransferRestricted = builder.isOwnershipTransferRestricted;
    this.externalCollaborationStatus = builder.externalCollaborationStatus;
    this.externalCollaborationAllowlistUsers = builder.externalCollaborationAllowlistUsers;
    this.isWatermarkingEnterpriseFeatureEnabled = builder.isWatermarkingEnterpriseFeatureEnabled;
    this.isRootContentCreationRestricted = builder.isRootContentCreationRestricted;
    this.isTagCreationRestricted = builder.isTagCreationRestricted;
    this.tagCreationRestriction = builder.tagCreationRestriction;
    this.isEmailUploadsEnabled = builder.isEmailUploadsEnabled;
    this.isCustomSettingsEnabled = builder.isCustomSettingsEnabled;
    this.isFormsLoginRequired = builder.isFormsLoginRequired;
    this.isFormsBrandingDefaultEnabled = builder.isFormsBrandingDefaultEnabled;
    this.isCcFreeTrialActive = builder.isCcFreeTrialActive;
    this.isFileRequestEditorsAllowed = builder.isFileRequestEditorsAllowed;
    this.isFileRequestBrandingDefaultEnabled = builder.isFileRequestBrandingDefaultEnabled;
    this.isFileRequestLoginRequired = builder.isFileRequestLoginRequired;
    this.isSharedLinksExpirationEnabled = builder.isSharedLinksExpirationEnabled;
    this.sharedLinksExpirationDays = builder.sharedLinksExpirationDays;
    this.isPublicSharedLinksExpirationEnabled = builder.isPublicSharedLinksExpirationEnabled;
    this.publicSharedLinksExpirationDays = builder.publicSharedLinksExpirationDays;
    this.sharedExpirationTarget = builder.sharedExpirationTarget;
    this.isSharedLinksExpirationNotificationEnabled =
        builder.isSharedLinksExpirationNotificationEnabled;
    this.sharedLinksExpirationNotificationDays = builder.sharedLinksExpirationNotificationDays;
    this.isSharedLinksExpirationNotificationPrevented =
        builder.isSharedLinksExpirationNotificationPrevented;
    this.isAutoDeleteEnabled = builder.isAutoDeleteEnabled;
    this.autoDeleteDays = builder.autoDeleteDays;
    this.isAutoDeleteExpirationModificationPrevented =
        builder.isAutoDeleteExpirationModificationPrevented;
    this.autoDeleteTarget = builder.autoDeleteTarget;
    this.isCollaborationExpirationEnabled = builder.isCollaborationExpirationEnabled;
    this.collaborationExpirationDays = builder.collaborationExpirationDays;
    this.isCollaborationExpirationModificationPrevented =
        builder.isCollaborationExpirationModificationPrevented;
    this.isCollaborationExpirationNotificationEnabled =
        builder.isCollaborationExpirationNotificationEnabled;
    this.collaborationExpirationTarget = builder.collaborationExpirationTarget;
    this.trashAutoClearTime = builder.trashAutoClearTime;
    this.permanentDeletionAccess = builder.permanentDeletionAccess;
    this.permanentDeletionAllowlistUsers = builder.permanentDeletionAllowlistUsers;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<EnterpriseFeatureSettingsItemV2025R0> getEnterpriseFeatureSettings() {
    return enterpriseFeatureSettings;
  }

  public EnterpriseConfigurationItemStringV2025R0 getSharingItemType() {
    return sharingItemType;
  }

  public EnterpriseConfigurationItemStringV2025R0 getSharedLinkCompanyDefinition() {
    return sharedLinkCompanyDefinition;
  }

  public EnterpriseConfigurationItemStringV2025R0 getSharedLinkAccess() {
    return sharedLinkAccess;
  }

  public EnterpriseConfigurationItemStringV2025R0 getSharedLinkDefaultAccess() {
    return sharedLinkDefaultAccess;
  }

  public EnterpriseConfigurationContentAndSharingV2025R0SharedLinkDefaultPermissionsSelectedField
      getSharedLinkDefaultPermissionsSelected() {
    return sharedLinkDefaultPermissionsSelected;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsOpenCustomUrlsDisabled() {
    return isOpenCustomUrlsDisabled;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsCustomDomainHiddenInSharedLink() {
    return isCustomDomainHiddenInSharedLink;
  }

  public EnterpriseConfigurationContentAndSharingV2025R0CollaborationPermissionsField
      getCollaborationPermissions() {
    return collaborationPermissions;
  }

  public EnterpriseConfigurationItemStringV2025R0 getDefaultCollaborationRole() {
    return defaultCollaborationRole;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsInvitePrivilegeRestricted() {
    return isInvitePrivilegeRestricted;
  }

  public EnterpriseConfigurationContentAndSharingV2025R0CollaborationRestrictionsField
      getCollaborationRestrictions() {
    return collaborationRestrictions;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsCollaboratorInviteLinksDisabled() {
    return isCollaboratorInviteLinksDisabled;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsInviteGroupCollaboratorDisabled() {
    return isInviteGroupCollaboratorDisabled;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsOwnershipTransferRestricted() {
    return isOwnershipTransferRestricted;
  }

  public EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusField
      getExternalCollaborationStatus() {
    return externalCollaborationStatus;
  }

  public EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationAllowlistUsersField
      getExternalCollaborationAllowlistUsers() {
    return externalCollaborationAllowlistUsers;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsWatermarkingEnterpriseFeatureEnabled() {
    return isWatermarkingEnterpriseFeatureEnabled;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsRootContentCreationRestricted() {
    return isRootContentCreationRestricted;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsTagCreationRestricted() {
    return isTagCreationRestricted;
  }

  public EnterpriseConfigurationItemStringV2025R0 getTagCreationRestriction() {
    return tagCreationRestriction;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsEmailUploadsEnabled() {
    return isEmailUploadsEnabled;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsCustomSettingsEnabled() {
    return isCustomSettingsEnabled;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsFormsLoginRequired() {
    return isFormsLoginRequired;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsFormsBrandingDefaultEnabled() {
    return isFormsBrandingDefaultEnabled;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsCcFreeTrialActive() {
    return isCcFreeTrialActive;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsFileRequestEditorsAllowed() {
    return isFileRequestEditorsAllowed;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsFileRequestBrandingDefaultEnabled() {
    return isFileRequestBrandingDefaultEnabled;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsFileRequestLoginRequired() {
    return isFileRequestLoginRequired;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsSharedLinksExpirationEnabled() {
    return isSharedLinksExpirationEnabled;
  }

  public EnterpriseConfigurationItemIntegerV2025R0 getSharedLinksExpirationDays() {
    return sharedLinksExpirationDays;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsPublicSharedLinksExpirationEnabled() {
    return isPublicSharedLinksExpirationEnabled;
  }

  public EnterpriseConfigurationItemIntegerV2025R0 getPublicSharedLinksExpirationDays() {
    return publicSharedLinksExpirationDays;
  }

  public EnterpriseConfigurationItemStringV2025R0 getSharedExpirationTarget() {
    return sharedExpirationTarget;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsSharedLinksExpirationNotificationEnabled() {
    return isSharedLinksExpirationNotificationEnabled;
  }

  public EnterpriseConfigurationItemIntegerV2025R0 getSharedLinksExpirationNotificationDays() {
    return sharedLinksExpirationNotificationDays;
  }

  public EnterpriseConfigurationItemBooleanV2025R0
      getIsSharedLinksExpirationNotificationPrevented() {
    return isSharedLinksExpirationNotificationPrevented;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsAutoDeleteEnabled() {
    return isAutoDeleteEnabled;
  }

  public EnterpriseConfigurationItemIntegerV2025R0 getAutoDeleteDays() {
    return autoDeleteDays;
  }

  public EnterpriseConfigurationItemBooleanV2025R0
      getIsAutoDeleteExpirationModificationPrevented() {
    return isAutoDeleteExpirationModificationPrevented;
  }

  public EnterpriseConfigurationItemStringV2025R0 getAutoDeleteTarget() {
    return autoDeleteTarget;
  }

  public EnterpriseConfigurationItemBooleanV2025R0 getIsCollaborationExpirationEnabled() {
    return isCollaborationExpirationEnabled;
  }

  public EnterpriseConfigurationItemIntegerV2025R0 getCollaborationExpirationDays() {
    return collaborationExpirationDays;
  }

  public EnterpriseConfigurationItemBooleanV2025R0
      getIsCollaborationExpirationModificationPrevented() {
    return isCollaborationExpirationModificationPrevented;
  }

  public EnterpriseConfigurationItemBooleanV2025R0
      getIsCollaborationExpirationNotificationEnabled() {
    return isCollaborationExpirationNotificationEnabled;
  }

  public EnterpriseConfigurationItemStringV2025R0 getCollaborationExpirationTarget() {
    return collaborationExpirationTarget;
  }

  public EnterpriseConfigurationItemIntegerV2025R0 getTrashAutoClearTime() {
    return trashAutoClearTime;
  }

  public EnterpriseConfigurationItemStringV2025R0 getPermanentDeletionAccess() {
    return permanentDeletionAccess;
  }

  public EnterpriseConfigurationContentAndSharingV2025R0PermanentDeletionAllowlistUsersField
      getPermanentDeletionAllowlistUsers() {
    return permanentDeletionAllowlistUsers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EnterpriseConfigurationContentAndSharingV2025R0 casted =
        (EnterpriseConfigurationContentAndSharingV2025R0) o;
    return Objects.equals(enterpriseFeatureSettings, casted.enterpriseFeatureSettings)
        && Objects.equals(sharingItemType, casted.sharingItemType)
        && Objects.equals(sharedLinkCompanyDefinition, casted.sharedLinkCompanyDefinition)
        && Objects.equals(sharedLinkAccess, casted.sharedLinkAccess)
        && Objects.equals(sharedLinkDefaultAccess, casted.sharedLinkDefaultAccess)
        && Objects.equals(
            sharedLinkDefaultPermissionsSelected, casted.sharedLinkDefaultPermissionsSelected)
        && Objects.equals(isOpenCustomUrlsDisabled, casted.isOpenCustomUrlsDisabled)
        && Objects.equals(isCustomDomainHiddenInSharedLink, casted.isCustomDomainHiddenInSharedLink)
        && Objects.equals(collaborationPermissions, casted.collaborationPermissions)
        && Objects.equals(defaultCollaborationRole, casted.defaultCollaborationRole)
        && Objects.equals(isInvitePrivilegeRestricted, casted.isInvitePrivilegeRestricted)
        && Objects.equals(collaborationRestrictions, casted.collaborationRestrictions)
        && Objects.equals(
            isCollaboratorInviteLinksDisabled, casted.isCollaboratorInviteLinksDisabled)
        && Objects.equals(
            isInviteGroupCollaboratorDisabled, casted.isInviteGroupCollaboratorDisabled)
        && Objects.equals(isOwnershipTransferRestricted, casted.isOwnershipTransferRestricted)
        && Objects.equals(externalCollaborationStatus, casted.externalCollaborationStatus)
        && Objects.equals(
            externalCollaborationAllowlistUsers, casted.externalCollaborationAllowlistUsers)
        && Objects.equals(
            isWatermarkingEnterpriseFeatureEnabled, casted.isWatermarkingEnterpriseFeatureEnabled)
        && Objects.equals(isRootContentCreationRestricted, casted.isRootContentCreationRestricted)
        && Objects.equals(isTagCreationRestricted, casted.isTagCreationRestricted)
        && Objects.equals(tagCreationRestriction, casted.tagCreationRestriction)
        && Objects.equals(isEmailUploadsEnabled, casted.isEmailUploadsEnabled)
        && Objects.equals(isCustomSettingsEnabled, casted.isCustomSettingsEnabled)
        && Objects.equals(isFormsLoginRequired, casted.isFormsLoginRequired)
        && Objects.equals(isFormsBrandingDefaultEnabled, casted.isFormsBrandingDefaultEnabled)
        && Objects.equals(isCcFreeTrialActive, casted.isCcFreeTrialActive)
        && Objects.equals(isFileRequestEditorsAllowed, casted.isFileRequestEditorsAllowed)
        && Objects.equals(
            isFileRequestBrandingDefaultEnabled, casted.isFileRequestBrandingDefaultEnabled)
        && Objects.equals(isFileRequestLoginRequired, casted.isFileRequestLoginRequired)
        && Objects.equals(isSharedLinksExpirationEnabled, casted.isSharedLinksExpirationEnabled)
        && Objects.equals(sharedLinksExpirationDays, casted.sharedLinksExpirationDays)
        && Objects.equals(
            isPublicSharedLinksExpirationEnabled, casted.isPublicSharedLinksExpirationEnabled)
        && Objects.equals(publicSharedLinksExpirationDays, casted.publicSharedLinksExpirationDays)
        && Objects.equals(sharedExpirationTarget, casted.sharedExpirationTarget)
        && Objects.equals(
            isSharedLinksExpirationNotificationEnabled,
            casted.isSharedLinksExpirationNotificationEnabled)
        && Objects.equals(
            sharedLinksExpirationNotificationDays, casted.sharedLinksExpirationNotificationDays)
        && Objects.equals(
            isSharedLinksExpirationNotificationPrevented,
            casted.isSharedLinksExpirationNotificationPrevented)
        && Objects.equals(isAutoDeleteEnabled, casted.isAutoDeleteEnabled)
        && Objects.equals(autoDeleteDays, casted.autoDeleteDays)
        && Objects.equals(
            isAutoDeleteExpirationModificationPrevented,
            casted.isAutoDeleteExpirationModificationPrevented)
        && Objects.equals(autoDeleteTarget, casted.autoDeleteTarget)
        && Objects.equals(isCollaborationExpirationEnabled, casted.isCollaborationExpirationEnabled)
        && Objects.equals(collaborationExpirationDays, casted.collaborationExpirationDays)
        && Objects.equals(
            isCollaborationExpirationModificationPrevented,
            casted.isCollaborationExpirationModificationPrevented)
        && Objects.equals(
            isCollaborationExpirationNotificationEnabled,
            casted.isCollaborationExpirationNotificationEnabled)
        && Objects.equals(collaborationExpirationTarget, casted.collaborationExpirationTarget)
        && Objects.equals(trashAutoClearTime, casted.trashAutoClearTime)
        && Objects.equals(permanentDeletionAccess, casted.permanentDeletionAccess)
        && Objects.equals(permanentDeletionAllowlistUsers, casted.permanentDeletionAllowlistUsers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        enterpriseFeatureSettings,
        sharingItemType,
        sharedLinkCompanyDefinition,
        sharedLinkAccess,
        sharedLinkDefaultAccess,
        sharedLinkDefaultPermissionsSelected,
        isOpenCustomUrlsDisabled,
        isCustomDomainHiddenInSharedLink,
        collaborationPermissions,
        defaultCollaborationRole,
        isInvitePrivilegeRestricted,
        collaborationRestrictions,
        isCollaboratorInviteLinksDisabled,
        isInviteGroupCollaboratorDisabled,
        isOwnershipTransferRestricted,
        externalCollaborationStatus,
        externalCollaborationAllowlistUsers,
        isWatermarkingEnterpriseFeatureEnabled,
        isRootContentCreationRestricted,
        isTagCreationRestricted,
        tagCreationRestriction,
        isEmailUploadsEnabled,
        isCustomSettingsEnabled,
        isFormsLoginRequired,
        isFormsBrandingDefaultEnabled,
        isCcFreeTrialActive,
        isFileRequestEditorsAllowed,
        isFileRequestBrandingDefaultEnabled,
        isFileRequestLoginRequired,
        isSharedLinksExpirationEnabled,
        sharedLinksExpirationDays,
        isPublicSharedLinksExpirationEnabled,
        publicSharedLinksExpirationDays,
        sharedExpirationTarget,
        isSharedLinksExpirationNotificationEnabled,
        sharedLinksExpirationNotificationDays,
        isSharedLinksExpirationNotificationPrevented,
        isAutoDeleteEnabled,
        autoDeleteDays,
        isAutoDeleteExpirationModificationPrevented,
        autoDeleteTarget,
        isCollaborationExpirationEnabled,
        collaborationExpirationDays,
        isCollaborationExpirationModificationPrevented,
        isCollaborationExpirationNotificationEnabled,
        collaborationExpirationTarget,
        trashAutoClearTime,
        permanentDeletionAccess,
        permanentDeletionAllowlistUsers);
  }

  @Override
  public String toString() {
    return "EnterpriseConfigurationContentAndSharingV2025R0{"
        + "enterpriseFeatureSettings='"
        + enterpriseFeatureSettings
        + '\''
        + ", "
        + "sharingItemType='"
        + sharingItemType
        + '\''
        + ", "
        + "sharedLinkCompanyDefinition='"
        + sharedLinkCompanyDefinition
        + '\''
        + ", "
        + "sharedLinkAccess='"
        + sharedLinkAccess
        + '\''
        + ", "
        + "sharedLinkDefaultAccess='"
        + sharedLinkDefaultAccess
        + '\''
        + ", "
        + "sharedLinkDefaultPermissionsSelected='"
        + sharedLinkDefaultPermissionsSelected
        + '\''
        + ", "
        + "isOpenCustomUrlsDisabled='"
        + isOpenCustomUrlsDisabled
        + '\''
        + ", "
        + "isCustomDomainHiddenInSharedLink='"
        + isCustomDomainHiddenInSharedLink
        + '\''
        + ", "
        + "collaborationPermissions='"
        + collaborationPermissions
        + '\''
        + ", "
        + "defaultCollaborationRole='"
        + defaultCollaborationRole
        + '\''
        + ", "
        + "isInvitePrivilegeRestricted='"
        + isInvitePrivilegeRestricted
        + '\''
        + ", "
        + "collaborationRestrictions='"
        + collaborationRestrictions
        + '\''
        + ", "
        + "isCollaboratorInviteLinksDisabled='"
        + isCollaboratorInviteLinksDisabled
        + '\''
        + ", "
        + "isInviteGroupCollaboratorDisabled='"
        + isInviteGroupCollaboratorDisabled
        + '\''
        + ", "
        + "isOwnershipTransferRestricted='"
        + isOwnershipTransferRestricted
        + '\''
        + ", "
        + "externalCollaborationStatus='"
        + externalCollaborationStatus
        + '\''
        + ", "
        + "externalCollaborationAllowlistUsers='"
        + externalCollaborationAllowlistUsers
        + '\''
        + ", "
        + "isWatermarkingEnterpriseFeatureEnabled='"
        + isWatermarkingEnterpriseFeatureEnabled
        + '\''
        + ", "
        + "isRootContentCreationRestricted='"
        + isRootContentCreationRestricted
        + '\''
        + ", "
        + "isTagCreationRestricted='"
        + isTagCreationRestricted
        + '\''
        + ", "
        + "tagCreationRestriction='"
        + tagCreationRestriction
        + '\''
        + ", "
        + "isEmailUploadsEnabled='"
        + isEmailUploadsEnabled
        + '\''
        + ", "
        + "isCustomSettingsEnabled='"
        + isCustomSettingsEnabled
        + '\''
        + ", "
        + "isFormsLoginRequired='"
        + isFormsLoginRequired
        + '\''
        + ", "
        + "isFormsBrandingDefaultEnabled='"
        + isFormsBrandingDefaultEnabled
        + '\''
        + ", "
        + "isCcFreeTrialActive='"
        + isCcFreeTrialActive
        + '\''
        + ", "
        + "isFileRequestEditorsAllowed='"
        + isFileRequestEditorsAllowed
        + '\''
        + ", "
        + "isFileRequestBrandingDefaultEnabled='"
        + isFileRequestBrandingDefaultEnabled
        + '\''
        + ", "
        + "isFileRequestLoginRequired='"
        + isFileRequestLoginRequired
        + '\''
        + ", "
        + "isSharedLinksExpirationEnabled='"
        + isSharedLinksExpirationEnabled
        + '\''
        + ", "
        + "sharedLinksExpirationDays='"
        + sharedLinksExpirationDays
        + '\''
        + ", "
        + "isPublicSharedLinksExpirationEnabled='"
        + isPublicSharedLinksExpirationEnabled
        + '\''
        + ", "
        + "publicSharedLinksExpirationDays='"
        + publicSharedLinksExpirationDays
        + '\''
        + ", "
        + "sharedExpirationTarget='"
        + sharedExpirationTarget
        + '\''
        + ", "
        + "isSharedLinksExpirationNotificationEnabled='"
        + isSharedLinksExpirationNotificationEnabled
        + '\''
        + ", "
        + "sharedLinksExpirationNotificationDays='"
        + sharedLinksExpirationNotificationDays
        + '\''
        + ", "
        + "isSharedLinksExpirationNotificationPrevented='"
        + isSharedLinksExpirationNotificationPrevented
        + '\''
        + ", "
        + "isAutoDeleteEnabled='"
        + isAutoDeleteEnabled
        + '\''
        + ", "
        + "autoDeleteDays='"
        + autoDeleteDays
        + '\''
        + ", "
        + "isAutoDeleteExpirationModificationPrevented='"
        + isAutoDeleteExpirationModificationPrevented
        + '\''
        + ", "
        + "autoDeleteTarget='"
        + autoDeleteTarget
        + '\''
        + ", "
        + "isCollaborationExpirationEnabled='"
        + isCollaborationExpirationEnabled
        + '\''
        + ", "
        + "collaborationExpirationDays='"
        + collaborationExpirationDays
        + '\''
        + ", "
        + "isCollaborationExpirationModificationPrevented='"
        + isCollaborationExpirationModificationPrevented
        + '\''
        + ", "
        + "isCollaborationExpirationNotificationEnabled='"
        + isCollaborationExpirationNotificationEnabled
        + '\''
        + ", "
        + "collaborationExpirationTarget='"
        + collaborationExpirationTarget
        + '\''
        + ", "
        + "trashAutoClearTime='"
        + trashAutoClearTime
        + '\''
        + ", "
        + "permanentDeletionAccess='"
        + permanentDeletionAccess
        + '\''
        + ", "
        + "permanentDeletionAllowlistUsers='"
        + permanentDeletionAllowlistUsers
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected List<EnterpriseFeatureSettingsItemV2025R0> enterpriseFeatureSettings;

    protected EnterpriseConfigurationItemStringV2025R0 sharingItemType;

    protected EnterpriseConfigurationItemStringV2025R0 sharedLinkCompanyDefinition;

    protected EnterpriseConfigurationItemStringV2025R0 sharedLinkAccess;

    protected EnterpriseConfigurationItemStringV2025R0 sharedLinkDefaultAccess;

    protected
    EnterpriseConfigurationContentAndSharingV2025R0SharedLinkDefaultPermissionsSelectedField
        sharedLinkDefaultPermissionsSelected;

    protected EnterpriseConfigurationItemBooleanV2025R0 isOpenCustomUrlsDisabled;

    protected EnterpriseConfigurationItemBooleanV2025R0 isCustomDomainHiddenInSharedLink;

    protected EnterpriseConfigurationContentAndSharingV2025R0CollaborationPermissionsField
        collaborationPermissions;

    protected EnterpriseConfigurationItemStringV2025R0 defaultCollaborationRole;

    protected EnterpriseConfigurationItemBooleanV2025R0 isInvitePrivilegeRestricted;

    protected EnterpriseConfigurationContentAndSharingV2025R0CollaborationRestrictionsField
        collaborationRestrictions;

    protected EnterpriseConfigurationItemBooleanV2025R0 isCollaboratorInviteLinksDisabled;

    protected EnterpriseConfigurationItemBooleanV2025R0 isInviteGroupCollaboratorDisabled;

    protected EnterpriseConfigurationItemBooleanV2025R0 isOwnershipTransferRestricted;

    protected EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusField
        externalCollaborationStatus;

    protected
    EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationAllowlistUsersField
        externalCollaborationAllowlistUsers;

    protected EnterpriseConfigurationItemBooleanV2025R0 isWatermarkingEnterpriseFeatureEnabled;

    protected EnterpriseConfigurationItemBooleanV2025R0 isRootContentCreationRestricted;

    protected EnterpriseConfigurationItemBooleanV2025R0 isTagCreationRestricted;

    protected EnterpriseConfigurationItemStringV2025R0 tagCreationRestriction;

    protected EnterpriseConfigurationItemBooleanV2025R0 isEmailUploadsEnabled;

    protected EnterpriseConfigurationItemBooleanV2025R0 isCustomSettingsEnabled;

    protected EnterpriseConfigurationItemBooleanV2025R0 isFormsLoginRequired;

    protected EnterpriseConfigurationItemBooleanV2025R0 isFormsBrandingDefaultEnabled;

    protected EnterpriseConfigurationItemBooleanV2025R0 isCcFreeTrialActive;

    protected EnterpriseConfigurationItemBooleanV2025R0 isFileRequestEditorsAllowed;

    protected EnterpriseConfigurationItemBooleanV2025R0 isFileRequestBrandingDefaultEnabled;

    protected EnterpriseConfigurationItemBooleanV2025R0 isFileRequestLoginRequired;

    protected EnterpriseConfigurationItemBooleanV2025R0 isSharedLinksExpirationEnabled;

    protected EnterpriseConfigurationItemIntegerV2025R0 sharedLinksExpirationDays;

    protected EnterpriseConfigurationItemBooleanV2025R0 isPublicSharedLinksExpirationEnabled;

    protected EnterpriseConfigurationItemIntegerV2025R0 publicSharedLinksExpirationDays;

    protected EnterpriseConfigurationItemStringV2025R0 sharedExpirationTarget;

    protected EnterpriseConfigurationItemBooleanV2025R0 isSharedLinksExpirationNotificationEnabled;

    protected EnterpriseConfigurationItemIntegerV2025R0 sharedLinksExpirationNotificationDays;

    protected EnterpriseConfigurationItemBooleanV2025R0
        isSharedLinksExpirationNotificationPrevented;

    protected EnterpriseConfigurationItemBooleanV2025R0 isAutoDeleteEnabled;

    protected EnterpriseConfigurationItemIntegerV2025R0 autoDeleteDays;

    protected EnterpriseConfigurationItemBooleanV2025R0 isAutoDeleteExpirationModificationPrevented;

    protected EnterpriseConfigurationItemStringV2025R0 autoDeleteTarget;

    protected EnterpriseConfigurationItemBooleanV2025R0 isCollaborationExpirationEnabled;

    protected EnterpriseConfigurationItemIntegerV2025R0 collaborationExpirationDays;

    protected EnterpriseConfigurationItemBooleanV2025R0
        isCollaborationExpirationModificationPrevented;

    protected EnterpriseConfigurationItemBooleanV2025R0
        isCollaborationExpirationNotificationEnabled;

    protected EnterpriseConfigurationItemStringV2025R0 collaborationExpirationTarget;

    protected EnterpriseConfigurationItemIntegerV2025R0 trashAutoClearTime;

    protected EnterpriseConfigurationItemStringV2025R0 permanentDeletionAccess;

    protected EnterpriseConfigurationContentAndSharingV2025R0PermanentDeletionAllowlistUsersField
        permanentDeletionAllowlistUsers;

    public Builder enterpriseFeatureSettings(
        List<EnterpriseFeatureSettingsItemV2025R0> enterpriseFeatureSettings) {
      this.enterpriseFeatureSettings = enterpriseFeatureSettings;
      return this;
    }

    public Builder sharingItemType(EnterpriseConfigurationItemStringV2025R0 sharingItemType) {
      this.sharingItemType = sharingItemType;
      return this;
    }

    public Builder sharedLinkCompanyDefinition(
        EnterpriseConfigurationItemStringV2025R0 sharedLinkCompanyDefinition) {
      this.sharedLinkCompanyDefinition = sharedLinkCompanyDefinition;
      return this;
    }

    public Builder sharedLinkAccess(EnterpriseConfigurationItemStringV2025R0 sharedLinkAccess) {
      this.sharedLinkAccess = sharedLinkAccess;
      return this;
    }

    public Builder sharedLinkDefaultAccess(
        EnterpriseConfigurationItemStringV2025R0 sharedLinkDefaultAccess) {
      this.sharedLinkDefaultAccess = sharedLinkDefaultAccess;
      return this;
    }

    public Builder sharedLinkDefaultPermissionsSelected(
        EnterpriseConfigurationContentAndSharingV2025R0SharedLinkDefaultPermissionsSelectedField
            sharedLinkDefaultPermissionsSelected) {
      this.sharedLinkDefaultPermissionsSelected = sharedLinkDefaultPermissionsSelected;
      return this;
    }

    public Builder isOpenCustomUrlsDisabled(
        EnterpriseConfigurationItemBooleanV2025R0 isOpenCustomUrlsDisabled) {
      this.isOpenCustomUrlsDisabled = isOpenCustomUrlsDisabled;
      return this;
    }

    public Builder isCustomDomainHiddenInSharedLink(
        EnterpriseConfigurationItemBooleanV2025R0 isCustomDomainHiddenInSharedLink) {
      this.isCustomDomainHiddenInSharedLink = isCustomDomainHiddenInSharedLink;
      return this;
    }

    public Builder collaborationPermissions(
        EnterpriseConfigurationContentAndSharingV2025R0CollaborationPermissionsField
            collaborationPermissions) {
      this.collaborationPermissions = collaborationPermissions;
      return this;
    }

    public Builder defaultCollaborationRole(
        EnterpriseConfigurationItemStringV2025R0 defaultCollaborationRole) {
      this.defaultCollaborationRole = defaultCollaborationRole;
      return this;
    }

    public Builder isInvitePrivilegeRestricted(
        EnterpriseConfigurationItemBooleanV2025R0 isInvitePrivilegeRestricted) {
      this.isInvitePrivilegeRestricted = isInvitePrivilegeRestricted;
      return this;
    }

    public Builder collaborationRestrictions(
        EnterpriseConfigurationContentAndSharingV2025R0CollaborationRestrictionsField
            collaborationRestrictions) {
      this.collaborationRestrictions = collaborationRestrictions;
      return this;
    }

    public Builder isCollaboratorInviteLinksDisabled(
        EnterpriseConfigurationItemBooleanV2025R0 isCollaboratorInviteLinksDisabled) {
      this.isCollaboratorInviteLinksDisabled = isCollaboratorInviteLinksDisabled;
      return this;
    }

    public Builder isInviteGroupCollaboratorDisabled(
        EnterpriseConfigurationItemBooleanV2025R0 isInviteGroupCollaboratorDisabled) {
      this.isInviteGroupCollaboratorDisabled = isInviteGroupCollaboratorDisabled;
      return this;
    }

    public Builder isOwnershipTransferRestricted(
        EnterpriseConfigurationItemBooleanV2025R0 isOwnershipTransferRestricted) {
      this.isOwnershipTransferRestricted = isOwnershipTransferRestricted;
      return this;
    }

    public Builder externalCollaborationStatus(
        EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusField
            externalCollaborationStatus) {
      this.externalCollaborationStatus = externalCollaborationStatus;
      return this;
    }

    public Builder externalCollaborationAllowlistUsers(
        EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationAllowlistUsersField
            externalCollaborationAllowlistUsers) {
      this.externalCollaborationAllowlistUsers = externalCollaborationAllowlistUsers;
      return this;
    }

    public Builder isWatermarkingEnterpriseFeatureEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isWatermarkingEnterpriseFeatureEnabled) {
      this.isWatermarkingEnterpriseFeatureEnabled = isWatermarkingEnterpriseFeatureEnabled;
      return this;
    }

    public Builder isRootContentCreationRestricted(
        EnterpriseConfigurationItemBooleanV2025R0 isRootContentCreationRestricted) {
      this.isRootContentCreationRestricted = isRootContentCreationRestricted;
      return this;
    }

    public Builder isTagCreationRestricted(
        EnterpriseConfigurationItemBooleanV2025R0 isTagCreationRestricted) {
      this.isTagCreationRestricted = isTagCreationRestricted;
      return this;
    }

    public Builder tagCreationRestriction(
        EnterpriseConfigurationItemStringV2025R0 tagCreationRestriction) {
      this.tagCreationRestriction = tagCreationRestriction;
      return this;
    }

    public Builder isEmailUploadsEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isEmailUploadsEnabled) {
      this.isEmailUploadsEnabled = isEmailUploadsEnabled;
      return this;
    }

    public Builder isCustomSettingsEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isCustomSettingsEnabled) {
      this.isCustomSettingsEnabled = isCustomSettingsEnabled;
      return this;
    }

    public Builder isFormsLoginRequired(
        EnterpriseConfigurationItemBooleanV2025R0 isFormsLoginRequired) {
      this.isFormsLoginRequired = isFormsLoginRequired;
      return this;
    }

    public Builder isFormsBrandingDefaultEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isFormsBrandingDefaultEnabled) {
      this.isFormsBrandingDefaultEnabled = isFormsBrandingDefaultEnabled;
      return this;
    }

    public Builder isCcFreeTrialActive(
        EnterpriseConfigurationItemBooleanV2025R0 isCcFreeTrialActive) {
      this.isCcFreeTrialActive = isCcFreeTrialActive;
      return this;
    }

    public Builder isFileRequestEditorsAllowed(
        EnterpriseConfigurationItemBooleanV2025R0 isFileRequestEditorsAllowed) {
      this.isFileRequestEditorsAllowed = isFileRequestEditorsAllowed;
      return this;
    }

    public Builder isFileRequestBrandingDefaultEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isFileRequestBrandingDefaultEnabled) {
      this.isFileRequestBrandingDefaultEnabled = isFileRequestBrandingDefaultEnabled;
      return this;
    }

    public Builder isFileRequestLoginRequired(
        EnterpriseConfigurationItemBooleanV2025R0 isFileRequestLoginRequired) {
      this.isFileRequestLoginRequired = isFileRequestLoginRequired;
      return this;
    }

    public Builder isSharedLinksExpirationEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isSharedLinksExpirationEnabled) {
      this.isSharedLinksExpirationEnabled = isSharedLinksExpirationEnabled;
      return this;
    }

    public Builder sharedLinksExpirationDays(
        EnterpriseConfigurationItemIntegerV2025R0 sharedLinksExpirationDays) {
      this.sharedLinksExpirationDays = sharedLinksExpirationDays;
      return this;
    }

    public Builder isPublicSharedLinksExpirationEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isPublicSharedLinksExpirationEnabled) {
      this.isPublicSharedLinksExpirationEnabled = isPublicSharedLinksExpirationEnabled;
      return this;
    }

    public Builder publicSharedLinksExpirationDays(
        EnterpriseConfigurationItemIntegerV2025R0 publicSharedLinksExpirationDays) {
      this.publicSharedLinksExpirationDays = publicSharedLinksExpirationDays;
      return this;
    }

    public Builder sharedExpirationTarget(
        EnterpriseConfigurationItemStringV2025R0 sharedExpirationTarget) {
      this.sharedExpirationTarget = sharedExpirationTarget;
      return this;
    }

    public Builder isSharedLinksExpirationNotificationEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isSharedLinksExpirationNotificationEnabled) {
      this.isSharedLinksExpirationNotificationEnabled = isSharedLinksExpirationNotificationEnabled;
      return this;
    }

    public Builder sharedLinksExpirationNotificationDays(
        EnterpriseConfigurationItemIntegerV2025R0 sharedLinksExpirationNotificationDays) {
      this.sharedLinksExpirationNotificationDays = sharedLinksExpirationNotificationDays;
      return this;
    }

    public Builder isSharedLinksExpirationNotificationPrevented(
        EnterpriseConfigurationItemBooleanV2025R0 isSharedLinksExpirationNotificationPrevented) {
      this.isSharedLinksExpirationNotificationPrevented =
          isSharedLinksExpirationNotificationPrevented;
      return this;
    }

    public Builder isAutoDeleteEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isAutoDeleteEnabled) {
      this.isAutoDeleteEnabled = isAutoDeleteEnabled;
      return this;
    }

    public Builder autoDeleteDays(EnterpriseConfigurationItemIntegerV2025R0 autoDeleteDays) {
      this.autoDeleteDays = autoDeleteDays;
      return this;
    }

    public Builder isAutoDeleteExpirationModificationPrevented(
        EnterpriseConfigurationItemBooleanV2025R0 isAutoDeleteExpirationModificationPrevented) {
      this.isAutoDeleteExpirationModificationPrevented =
          isAutoDeleteExpirationModificationPrevented;
      return this;
    }

    public Builder autoDeleteTarget(EnterpriseConfigurationItemStringV2025R0 autoDeleteTarget) {
      this.autoDeleteTarget = autoDeleteTarget;
      return this;
    }

    public Builder isCollaborationExpirationEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isCollaborationExpirationEnabled) {
      this.isCollaborationExpirationEnabled = isCollaborationExpirationEnabled;
      return this;
    }

    public Builder collaborationExpirationDays(
        EnterpriseConfigurationItemIntegerV2025R0 collaborationExpirationDays) {
      this.collaborationExpirationDays = collaborationExpirationDays;
      return this;
    }

    public Builder isCollaborationExpirationModificationPrevented(
        EnterpriseConfigurationItemBooleanV2025R0 isCollaborationExpirationModificationPrevented) {
      this.isCollaborationExpirationModificationPrevented =
          isCollaborationExpirationModificationPrevented;
      return this;
    }

    public Builder isCollaborationExpirationNotificationEnabled(
        EnterpriseConfigurationItemBooleanV2025R0 isCollaborationExpirationNotificationEnabled) {
      this.isCollaborationExpirationNotificationEnabled =
          isCollaborationExpirationNotificationEnabled;
      return this;
    }

    public Builder collaborationExpirationTarget(
        EnterpriseConfigurationItemStringV2025R0 collaborationExpirationTarget) {
      this.collaborationExpirationTarget = collaborationExpirationTarget;
      return this;
    }

    public Builder trashAutoClearTime(
        EnterpriseConfigurationItemIntegerV2025R0 trashAutoClearTime) {
      this.trashAutoClearTime = trashAutoClearTime;
      return this;
    }

    public Builder permanentDeletionAccess(
        EnterpriseConfigurationItemStringV2025R0 permanentDeletionAccess) {
      this.permanentDeletionAccess = permanentDeletionAccess;
      return this;
    }

    public Builder permanentDeletionAllowlistUsers(
        EnterpriseConfigurationContentAndSharingV2025R0PermanentDeletionAllowlistUsersField
            permanentDeletionAllowlistUsers) {
      this.permanentDeletionAllowlistUsers = permanentDeletionAllowlistUsers;
      return this;
    }

    public EnterpriseConfigurationContentAndSharingV2025R0 build() {
      return new EnterpriseConfigurationContentAndSharingV2025R0(this);
    }
  }
}
