package com.box.sdkgen.managers.retentionpolicies;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.userbase.UserBase;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateRetentionPolicyByIdRequestBody extends SerializableObject {

  /** The name for the retention policy. */
  @JsonProperty("policy_name")
  @Nullable
  protected String policyName;

  /** The additional text description of the retention policy. */
  @Nullable protected String description;

  /**
   * The disposition action of the retention policy. This action can be `permanently_delete`, which
   * will cause the content retained by the policy to be permanently deleted, or `remove_retention`,
   * which will lift the retention policy from the content, allowing it to be deleted by users, once
   * the retention policy has expired. You can use `null` if you don't want to change
   * `disposition_action`.
   */
  @JsonProperty("disposition_action")
  protected String dispositionAction;

  /**
   * Specifies the retention type:
   *
   * <p>* `modifiable`: You can modify the retention policy. For example, you can add or remove
   * folders, shorten or lengthen the policy duration, or delete the assignment. Use this type if
   * your retention policy is not related to any regulatory purposes. * `non-modifiable`: You can
   * modify the retention policy only in a limited way: add a folder, lengthen the duration, retire
   * the policy, change the disposition action or notification settings. You cannot perform other
   * actions, such as deleting the assignment or shortening the policy duration. Use this type to
   * ensure compliance with regulatory retention policies.
   *
   * <p>When updating a retention policy, you can use `non-modifiable` type only. You can convert a
   * `modifiable` policy to `non-modifiable`, but not the other way around.
   */
  @JsonProperty("retention_type")
  @Nullable
  protected String retentionType;

  /**
   * The length of the retention policy. This value specifies the duration in days that the
   * retention policy will be active for after being assigned to content. If the policy has a
   * `policy_type` of `indefinite`, the `retention_length` will also be `indefinite`.
   */
  @JsonProperty("retention_length")
  protected String retentionLength;

  /**
   * Used to retire a retention policy.
   *
   * <p>If not retiring a policy, do not include this parameter or set it to `null`.
   */
  @Nullable protected String status;

  /**
   * Determines if the owner of items under the policy can extend the retention when the original
   * retention duration is about to end.
   */
  @JsonProperty("can_owner_extend_retention")
  @Nullable
  protected Boolean canOwnerExtendRetention;

  /**
   * Determines if owners and co-owners of items under the policy are notified when the retention
   * duration is about to end.
   */
  @JsonProperty("are_owners_notified")
  @Nullable
  protected Boolean areOwnersNotified;

  /** A list of users notified when the retention duration is about to end. */
  @JsonProperty("custom_notification_recipients")
  @Nullable
  protected List<UserBase> customNotificationRecipients;

  public UpdateRetentionPolicyByIdRequestBody() {
    super();
  }

  protected UpdateRetentionPolicyByIdRequestBody(Builder builder) {
    super();
    this.policyName = builder.policyName;
    this.description = builder.description;
    this.dispositionAction = builder.dispositionAction;
    this.retentionType = builder.retentionType;
    this.retentionLength = builder.retentionLength;
    this.status = builder.status;
    this.canOwnerExtendRetention = builder.canOwnerExtendRetention;
    this.areOwnersNotified = builder.areOwnersNotified;
    this.customNotificationRecipients = builder.customNotificationRecipients;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getPolicyName() {
    return policyName;
  }

  public String getDescription() {
    return description;
  }

  public String getDispositionAction() {
    return dispositionAction;
  }

  public String getRetentionType() {
    return retentionType;
  }

  public String getRetentionLength() {
    return retentionLength;
  }

  public String getStatus() {
    return status;
  }

  public Boolean getCanOwnerExtendRetention() {
    return canOwnerExtendRetention;
  }

  public Boolean getAreOwnersNotified() {
    return areOwnersNotified;
  }

  public List<UserBase> getCustomNotificationRecipients() {
    return customNotificationRecipients;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateRetentionPolicyByIdRequestBody casted = (UpdateRetentionPolicyByIdRequestBody) o;
    return Objects.equals(policyName, casted.policyName)
        && Objects.equals(description, casted.description)
        && Objects.equals(dispositionAction, casted.dispositionAction)
        && Objects.equals(retentionType, casted.retentionType)
        && Objects.equals(retentionLength, casted.retentionLength)
        && Objects.equals(status, casted.status)
        && Objects.equals(canOwnerExtendRetention, casted.canOwnerExtendRetention)
        && Objects.equals(areOwnersNotified, casted.areOwnersNotified)
        && Objects.equals(customNotificationRecipients, casted.customNotificationRecipients);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        policyName,
        description,
        dispositionAction,
        retentionType,
        retentionLength,
        status,
        canOwnerExtendRetention,
        areOwnersNotified,
        customNotificationRecipients);
  }

  @Override
  public String toString() {
    return "UpdateRetentionPolicyByIdRequestBody{"
        + "policyName='"
        + policyName
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + ", "
        + "dispositionAction='"
        + dispositionAction
        + '\''
        + ", "
        + "retentionType='"
        + retentionType
        + '\''
        + ", "
        + "retentionLength='"
        + retentionLength
        + '\''
        + ", "
        + "status='"
        + status
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
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String policyName;

    protected String description;

    protected String dispositionAction;

    protected String retentionType;

    protected String retentionLength;

    protected String status;

    protected Boolean canOwnerExtendRetention;

    protected Boolean areOwnersNotified;

    protected List<UserBase> customNotificationRecipients;

    public Builder policyName(String policyName) {
      this.policyName = policyName;
      this.markNullableFieldAsSet("policy_name");
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      this.markNullableFieldAsSet("description");
      return this;
    }

    public Builder dispositionAction(String dispositionAction) {
      this.dispositionAction = dispositionAction;
      return this;
    }

    public Builder retentionType(String retentionType) {
      this.retentionType = retentionType;
      this.markNullableFieldAsSet("retention_type");
      return this;
    }

    public Builder retentionLength(String retentionLength) {
      this.retentionLength = retentionLength;
      return this;
    }

    public Builder status(String status) {
      this.status = status;
      this.markNullableFieldAsSet("status");
      return this;
    }

    public Builder canOwnerExtendRetention(Boolean canOwnerExtendRetention) {
      this.canOwnerExtendRetention = canOwnerExtendRetention;
      this.markNullableFieldAsSet("can_owner_extend_retention");
      return this;
    }

    public Builder areOwnersNotified(Boolean areOwnersNotified) {
      this.areOwnersNotified = areOwnersNotified;
      this.markNullableFieldAsSet("are_owners_notified");
      return this;
    }

    public Builder customNotificationRecipients(List<UserBase> customNotificationRecipients) {
      this.customNotificationRecipients = customNotificationRecipients;
      this.markNullableFieldAsSet("custom_notification_recipients");
      return this;
    }

    public UpdateRetentionPolicyByIdRequestBody build() {
      return new UpdateRetentionPolicyByIdRequestBody(this);
    }
  }
}
