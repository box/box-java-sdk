package com.box.sdkgen.schemas.retentionpolicy;

import com.box.sdkgen.schemas.retentionpolicybase.RetentionPolicyBaseTypeField;
import com.box.sdkgen.schemas.retentionpolicymini.RetentionPolicyMini;
import com.box.sdkgen.schemas.retentionpolicymini.RetentionPolicyMiniDispositionActionField;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

public class RetentionPolicy extends RetentionPolicyMini {

  protected String description;

  @JsonDeserialize(
      using = RetentionPolicyPolicyTypeField.RetentionPolicyPolicyTypeFieldDeserializer.class)
  @JsonSerialize(
      using = RetentionPolicyPolicyTypeField.RetentionPolicyPolicyTypeFieldSerializer.class)
  @JsonProperty("policy_type")
  protected EnumWrapper<RetentionPolicyPolicyTypeField> policyType;

  @JsonDeserialize(
      using = RetentionPolicyRetentionTypeField.RetentionPolicyRetentionTypeFieldDeserializer.class)
  @JsonSerialize(
      using = RetentionPolicyRetentionTypeField.RetentionPolicyRetentionTypeFieldSerializer.class)
  @JsonProperty("retention_type")
  protected EnumWrapper<RetentionPolicyRetentionTypeField> retentionType;

  @JsonDeserialize(using = RetentionPolicyStatusField.RetentionPolicyStatusFieldDeserializer.class)
  @JsonSerialize(using = RetentionPolicyStatusField.RetentionPolicyStatusFieldSerializer.class)
  protected EnumWrapper<RetentionPolicyStatusField> status;

  @JsonProperty("created_by")
  protected UserMini createdBy;

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("modified_at")
  protected String modifiedAt;

  @JsonProperty("can_owner_extend_retention")
  protected Boolean canOwnerExtendRetention;

  @JsonProperty("are_owners_notified")
  protected Boolean areOwnersNotified;

  @JsonProperty("custom_notification_recipients")
  protected List<UserMini> customNotificationRecipients;

  @JsonProperty("assignment_counts")
  protected RetentionPolicyAssignmentCountsField assignmentCounts;

  public RetentionPolicy(@JsonProperty("id") String id) {
    super(id);
  }

  protected RetentionPolicy(RetentionPolicyBuilder builder) {
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

  public String getCreatedAt() {
    return createdAt;
  }

  public String getModifiedAt() {
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

  public static class RetentionPolicyBuilder extends RetentionPolicyMiniBuilder {

    protected String description;

    protected EnumWrapper<RetentionPolicyPolicyTypeField> policyType;

    protected EnumWrapper<RetentionPolicyRetentionTypeField> retentionType;

    protected EnumWrapper<RetentionPolicyStatusField> status;

    protected UserMini createdBy;

    protected String createdAt;

    protected String modifiedAt;

    protected Boolean canOwnerExtendRetention;

    protected Boolean areOwnersNotified;

    protected List<UserMini> customNotificationRecipients;

    protected RetentionPolicyAssignmentCountsField assignmentCounts;

    public RetentionPolicyBuilder(String id) {
      super(id);
    }

    public RetentionPolicyBuilder description(String description) {
      this.description = description;
      return this;
    }

    public RetentionPolicyBuilder policyType(RetentionPolicyPolicyTypeField policyType) {
      this.policyType = new EnumWrapper<RetentionPolicyPolicyTypeField>(policyType);
      return this;
    }

    public RetentionPolicyBuilder policyType(
        EnumWrapper<RetentionPolicyPolicyTypeField> policyType) {
      this.policyType = policyType;
      return this;
    }

    public RetentionPolicyBuilder retentionType(RetentionPolicyRetentionTypeField retentionType) {
      this.retentionType = new EnumWrapper<RetentionPolicyRetentionTypeField>(retentionType);
      return this;
    }

    public RetentionPolicyBuilder retentionType(
        EnumWrapper<RetentionPolicyRetentionTypeField> retentionType) {
      this.retentionType = retentionType;
      return this;
    }

    public RetentionPolicyBuilder status(RetentionPolicyStatusField status) {
      this.status = new EnumWrapper<RetentionPolicyStatusField>(status);
      return this;
    }

    public RetentionPolicyBuilder status(EnumWrapper<RetentionPolicyStatusField> status) {
      this.status = status;
      return this;
    }

    public RetentionPolicyBuilder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public RetentionPolicyBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public RetentionPolicyBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public RetentionPolicyBuilder canOwnerExtendRetention(Boolean canOwnerExtendRetention) {
      this.canOwnerExtendRetention = canOwnerExtendRetention;
      return this;
    }

    public RetentionPolicyBuilder areOwnersNotified(Boolean areOwnersNotified) {
      this.areOwnersNotified = areOwnersNotified;
      return this;
    }

    public RetentionPolicyBuilder customNotificationRecipients(
        List<UserMini> customNotificationRecipients) {
      this.customNotificationRecipients = customNotificationRecipients;
      return this;
    }

    public RetentionPolicyBuilder assignmentCounts(
        RetentionPolicyAssignmentCountsField assignmentCounts) {
      this.assignmentCounts = assignmentCounts;
      return this;
    }

    @Override
    public RetentionPolicyBuilder type(RetentionPolicyBaseTypeField type) {
      this.type = new EnumWrapper<RetentionPolicyBaseTypeField>(type);
      return this;
    }

    @Override
    public RetentionPolicyBuilder type(EnumWrapper<RetentionPolicyBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public RetentionPolicyBuilder policyName(String policyName) {
      this.policyName = policyName;
      return this;
    }

    @Override
    public RetentionPolicyBuilder retentionLength(String retentionLength) {
      this.retentionLength = retentionLength;
      return this;
    }

    @Override
    public RetentionPolicyBuilder dispositionAction(
        RetentionPolicyMiniDispositionActionField dispositionAction) {
      this.dispositionAction =
          new EnumWrapper<RetentionPolicyMiniDispositionActionField>(dispositionAction);
      return this;
    }

    @Override
    public RetentionPolicyBuilder dispositionAction(
        EnumWrapper<RetentionPolicyMiniDispositionActionField> dispositionAction) {
      this.dispositionAction = dispositionAction;
      return this;
    }

    public RetentionPolicy build() {
      return new RetentionPolicy(this);
    }
  }
}
