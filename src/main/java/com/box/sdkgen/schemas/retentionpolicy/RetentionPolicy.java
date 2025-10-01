package com.box.sdkgen.schemas.retentionpolicy;

import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.retentionpolicybase.RetentionPolicyBaseTypeField;
import com.box.sdkgen.schemas.retentionpolicymini.RetentionPolicyMini;
import com.box.sdkgen.schemas.retentionpolicymini.RetentionPolicyMiniDispositionActionField;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

/**
 * A retention policy blocks permanent deletion of content for a specified amount of time. Admins
 * can create retention policies and then later assign them to specific folders, metadata templates,
 * or their entire enterprise. To use this feature, you must have the manage retention policies
 * scope enabled for your API key via your application management console.
 */
@JsonFilter("nullablePropertyFilter")
public class RetentionPolicy extends RetentionPolicyMini {

  /** The additional text description of the retention policy. */
  protected String description;

  /**
   * The type of the retention policy. A retention policy type can either be `finite`, where a
   * specific amount of time to retain the content is known upfront, or `indefinite`, where the
   * amount of time to retain the content is still unknown.
   */
  @JsonDeserialize(
      using = RetentionPolicyPolicyTypeField.RetentionPolicyPolicyTypeFieldDeserializer.class)
  @JsonSerialize(
      using = RetentionPolicyPolicyTypeField.RetentionPolicyPolicyTypeFieldSerializer.class)
  @JsonProperty("policy_type")
  protected EnumWrapper<RetentionPolicyPolicyTypeField> policyType;

  /**
   * Specifies the retention type:
   *
   * <p>* `modifiable`: You can modify the retention policy. For example, you can add or remove
   * folders, shorten or lengthen the policy duration, or delete the assignment. Use this type if
   * your retention policy is not related to any regulatory purposes.
   *
   * <p>* `non-modifiable`: You can modify the retention policy only in a limited way: add a folder,
   * lengthen the duration, retire the policy, change the disposition action or notification
   * settings. You cannot perform other actions, such as deleting the assignment or shortening the
   * policy duration. Use this type to ensure compliance with regulatory retention policies.
   */
  @JsonDeserialize(
      using = RetentionPolicyRetentionTypeField.RetentionPolicyRetentionTypeFieldDeserializer.class)
  @JsonSerialize(
      using = RetentionPolicyRetentionTypeField.RetentionPolicyRetentionTypeFieldSerializer.class)
  @JsonProperty("retention_type")
  protected EnumWrapper<RetentionPolicyRetentionTypeField> retentionType;

  /**
   * The status of the retention policy. The status of a policy will be `active`, unless explicitly
   * retired by an administrator, in which case the status will be `retired`. Once a policy has been
   * retired, it cannot become active again.
   */
  @JsonDeserialize(using = RetentionPolicyStatusField.RetentionPolicyStatusFieldDeserializer.class)
  @JsonSerialize(using = RetentionPolicyStatusField.RetentionPolicyStatusFieldSerializer.class)
  protected EnumWrapper<RetentionPolicyStatusField> status;

  @JsonProperty("created_by")
  protected UserMini createdBy;

  /** When the retention policy object was created. */
  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  /** When the retention policy object was last modified. */
  @JsonProperty("modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime modifiedAt;

  /**
   * Determines if the owner of items under the policy can extend the retention when the original
   * retention duration is about to end.
   */
  @JsonProperty("can_owner_extend_retention")
  protected Boolean canOwnerExtendRetention;

  /**
   * Determines if owners and co-owners of items under the policy are notified when the retention
   * duration is about to end.
   */
  @JsonProperty("are_owners_notified")
  protected Boolean areOwnersNotified;

  /** A list of users notified when the retention policy duration is about to end. */
  @JsonProperty("custom_notification_recipients")
  protected List<UserMini> customNotificationRecipients;

  /** Counts the retention policy assignments for each item type. */
  @JsonProperty("assignment_counts")
  protected RetentionPolicyAssignmentCountsField assignmentCounts;

  public RetentionPolicy(@JsonProperty("id") String id) {
    super(id);
  }

  protected RetentionPolicy(Builder builder) {
    super(builder);
    this.description = builder.description;
    this.policyType = builder.policyType;
    this.retentionType = builder.retentionType;
    this.status = builder.status;
    this.createdBy = builder.createdBy;
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
    this.canOwnerExtendRetention = builder.canOwnerExtendRetention;
    this.areOwnersNotified = builder.areOwnersNotified;
    this.customNotificationRecipients = builder.customNotificationRecipients;
    this.assignmentCounts = builder.assignmentCounts;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getDescription() {
    return description;
  }

  public EnumWrapper<RetentionPolicyPolicyTypeField> getPolicyType() {
    return policyType;
  }

  public EnumWrapper<RetentionPolicyRetentionTypeField> getRetentionType() {
    return retentionType;
  }

  public EnumWrapper<RetentionPolicyStatusField> getStatus() {
    return status;
  }

  public UserMini getCreatedBy() {
    return createdBy;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public OffsetDateTime getModifiedAt() {
    return modifiedAt;
  }

  public Boolean getCanOwnerExtendRetention() {
    return canOwnerExtendRetention;
  }

  public Boolean getAreOwnersNotified() {
    return areOwnersNotified;
  }

  public List<UserMini> getCustomNotificationRecipients() {
    return customNotificationRecipients;
  }

  public RetentionPolicyAssignmentCountsField getAssignmentCounts() {
    return assignmentCounts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RetentionPolicy casted = (RetentionPolicy) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(policyName, casted.policyName)
        && Objects.equals(retentionLength, casted.retentionLength)
        && Objects.equals(dispositionAction, casted.dispositionAction)
        && Objects.equals(description, casted.description)
        && Objects.equals(policyType, casted.policyType)
        && Objects.equals(retentionType, casted.retentionType)
        && Objects.equals(status, casted.status)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(modifiedAt, casted.modifiedAt)
        && Objects.equals(canOwnerExtendRetention, casted.canOwnerExtendRetention)
        && Objects.equals(areOwnersNotified, casted.areOwnersNotified)
        && Objects.equals(customNotificationRecipients, casted.customNotificationRecipients)
        && Objects.equals(assignmentCounts, casted.assignmentCounts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        type,
        policyName,
        retentionLength,
        dispositionAction,
        description,
        policyType,
        retentionType,
        status,
        createdBy,
        createdAt,
        modifiedAt,
        canOwnerExtendRetention,
        areOwnersNotified,
        customNotificationRecipients,
        assignmentCounts);
  }

  @Override
  public String toString() {
    return "RetentionPolicy{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "policyName='"
        + policyName
        + '\''
        + ", "
        + "retentionLength='"
        + retentionLength
        + '\''
        + ", "
        + "dispositionAction='"
        + dispositionAction
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + ", "
        + "policyType='"
        + policyType
        + '\''
        + ", "
        + "retentionType='"
        + retentionType
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + ", "
        + "createdBy='"
        + createdBy
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "modifiedAt='"
        + modifiedAt
        + '\''
        + ", "
        + "canOwnerExtendRetention='"
        + canOwnerExtendRetention
        + '\''
        + ", "
        + "areOwnersNotified='"
        + areOwnersNotified
        + '\''
        + ", "
        + "customNotificationRecipients='"
        + customNotificationRecipients
        + '\''
        + ", "
        + "assignmentCounts='"
        + assignmentCounts
        + '\''
        + "}";
  }

  public static class Builder extends RetentionPolicyMini.Builder {

    protected String description;

    protected EnumWrapper<RetentionPolicyPolicyTypeField> policyType;

    protected EnumWrapper<RetentionPolicyRetentionTypeField> retentionType;

    protected EnumWrapper<RetentionPolicyStatusField> status;

    protected UserMini createdBy;

    protected OffsetDateTime createdAt;

    protected OffsetDateTime modifiedAt;

    protected Boolean canOwnerExtendRetention;

    protected Boolean areOwnersNotified;

    protected List<UserMini> customNotificationRecipients;

    protected RetentionPolicyAssignmentCountsField assignmentCounts;

    public Builder(String id) {
      super(id);
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder policyType(RetentionPolicyPolicyTypeField policyType) {
      this.policyType = new EnumWrapper<RetentionPolicyPolicyTypeField>(policyType);
      return this;
    }

    public Builder policyType(EnumWrapper<RetentionPolicyPolicyTypeField> policyType) {
      this.policyType = policyType;
      return this;
    }

    public Builder retentionType(RetentionPolicyRetentionTypeField retentionType) {
      this.retentionType = new EnumWrapper<RetentionPolicyRetentionTypeField>(retentionType);
      return this;
    }

    public Builder retentionType(EnumWrapper<RetentionPolicyRetentionTypeField> retentionType) {
      this.retentionType = retentionType;
      return this;
    }

    public Builder status(RetentionPolicyStatusField status) {
      this.status = new EnumWrapper<RetentionPolicyStatusField>(status);
      return this;
    }

    public Builder status(EnumWrapper<RetentionPolicyStatusField> status) {
      this.status = status;
      return this;
    }

    public Builder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder modifiedAt(OffsetDateTime modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public Builder canOwnerExtendRetention(Boolean canOwnerExtendRetention) {
      this.canOwnerExtendRetention = canOwnerExtendRetention;
      return this;
    }

    public Builder areOwnersNotified(Boolean areOwnersNotified) {
      this.areOwnersNotified = areOwnersNotified;
      return this;
    }

    public Builder customNotificationRecipients(List<UserMini> customNotificationRecipients) {
      this.customNotificationRecipients = customNotificationRecipients;
      return this;
    }

    public Builder assignmentCounts(RetentionPolicyAssignmentCountsField assignmentCounts) {
      this.assignmentCounts = assignmentCounts;
      return this;
    }

    @Override
    public Builder type(RetentionPolicyBaseTypeField type) {
      this.type = new EnumWrapper<RetentionPolicyBaseTypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<RetentionPolicyBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public Builder policyName(String policyName) {
      this.policyName = policyName;
      return this;
    }

    @Override
    public Builder retentionLength(String retentionLength) {
      this.retentionLength = retentionLength;
      return this;
    }

    @Override
    public Builder dispositionAction(RetentionPolicyMiniDispositionActionField dispositionAction) {
      this.dispositionAction =
          new EnumWrapper<RetentionPolicyMiniDispositionActionField>(dispositionAction);
      return this;
    }

    @Override
    public Builder dispositionAction(
        EnumWrapper<RetentionPolicyMiniDispositionActionField> dispositionAction) {
      this.dispositionAction = dispositionAction;
      return this;
    }

    public RetentionPolicy build() {
      return new RetentionPolicy(this);
    }
  }
}
