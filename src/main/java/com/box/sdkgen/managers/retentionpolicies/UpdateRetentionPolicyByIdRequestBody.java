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

  @JsonProperty("policy_name")
  @Nullable
  protected String policyName;

  @Nullable protected String description;

  @JsonProperty("disposition_action")
  protected String dispositionAction;

  @JsonProperty("retention_type")
  @Nullable
  protected String retentionType;

  @JsonProperty("retention_length")
  protected String retentionLength;

  @Nullable protected String status;

  @JsonProperty("can_owner_extend_retention")
  @Nullable
  protected Boolean canOwnerExtendRetention;

  @JsonProperty("are_owners_notified")
  @Nullable
  protected Boolean areOwnersNotified;

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
