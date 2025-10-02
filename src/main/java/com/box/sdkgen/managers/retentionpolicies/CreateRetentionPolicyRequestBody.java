package com.box.sdkgen.managers.retentionpolicies;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateRetentionPolicyRequestBody extends SerializableObject {

  /** The name for the retention policy. */
  @JsonProperty("policy_name")
  protected final String policyName;

  /** The additional text description of the retention policy. */
  protected String description;

  /**
   * The type of the retention policy. A retention policy type can either be `finite`, where a
   * specific amount of time to retain the content is known upfront, or `indefinite`, where the
   * amount of time to retain the content is still unknown.
   */
  @JsonDeserialize(
      using =
          CreateRetentionPolicyRequestBodyPolicyTypeField
              .CreateRetentionPolicyRequestBodyPolicyTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateRetentionPolicyRequestBodyPolicyTypeField
              .CreateRetentionPolicyRequestBodyPolicyTypeFieldSerializer.class)
  @JsonProperty("policy_type")
  protected final EnumWrapper<CreateRetentionPolicyRequestBodyPolicyTypeField> policyType;

  /**
   * The disposition action of the retention policy. `permanently_delete` deletes the content
   * retained by the policy permanently. `remove_retention` lifts retention policy from the content,
   * allowing it to be deleted by users once the retention policy has expired.
   */
  @JsonDeserialize(
      using =
          CreateRetentionPolicyRequestBodyDispositionActionField
              .CreateRetentionPolicyRequestBodyDispositionActionFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateRetentionPolicyRequestBodyDispositionActionField
              .CreateRetentionPolicyRequestBodyDispositionActionFieldSerializer.class)
  @JsonProperty("disposition_action")
  protected final EnumWrapper<CreateRetentionPolicyRequestBodyDispositionActionField>
      dispositionAction;

  /**
   * The length of the retention policy. This value specifies the duration in days that the
   * retention policy will be active for after being assigned to content. If the policy has a
   * `policy_type` of `indefinite`, the `retention_length` will also be `indefinite`.
   */
  @JsonProperty("retention_length")
  protected String retentionLength;

  /**
   * Specifies the retention type:
   *
   * <p>* `modifiable`: You can modify the retention policy. For example, you can add or remove
   * folders, shorten or lengthen the policy duration, or delete the assignment. Use this type if
   * your retention policy is not related to any regulatory purposes.
   *
   * <p>* `non_modifiable`: You can modify the retention policy only in a limited way: add a folder,
   * lengthen the duration, retire the policy, change the disposition action or notification
   * settings. You cannot perform other actions, such as deleting the assignment or shortening the
   * policy duration. Use this type to ensure compliance with regulatory retention policies.
   */
  @JsonDeserialize(
      using =
          CreateRetentionPolicyRequestBodyRetentionTypeField
              .CreateRetentionPolicyRequestBodyRetentionTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateRetentionPolicyRequestBodyRetentionTypeField
              .CreateRetentionPolicyRequestBodyRetentionTypeFieldSerializer.class)
  @JsonProperty("retention_type")
  protected EnumWrapper<CreateRetentionPolicyRequestBodyRetentionTypeField> retentionType;

  /** Whether the owner of a file will be allowed to extend the retention. */
  @JsonProperty("can_owner_extend_retention")
  protected Boolean canOwnerExtendRetention;

  /** Whether owner and co-owners of a file are notified when the policy nears expiration. */
  @JsonProperty("are_owners_notified")
  protected Boolean areOwnersNotified;

  /** A list of users notified when the retention policy duration is about to end. */
  @JsonProperty("custom_notification_recipients")
  protected List<UserMini> customNotificationRecipients;

  public CreateRetentionPolicyRequestBody(
      String policyName,
      CreateRetentionPolicyRequestBodyPolicyTypeField policyType,
      CreateRetentionPolicyRequestBodyDispositionActionField dispositionAction) {
    super();
    this.policyName = policyName;
    this.policyType = new EnumWrapper<CreateRetentionPolicyRequestBodyPolicyTypeField>(policyType);
    this.dispositionAction =
        new EnumWrapper<CreateRetentionPolicyRequestBodyDispositionActionField>(dispositionAction);
  }

  public CreateRetentionPolicyRequestBody(
      String policyName,
      CreateRetentionPolicyRequestBodyPolicyTypeField policyType,
      EnumWrapper<CreateRetentionPolicyRequestBodyDispositionActionField> dispositionAction) {
    super();
    this.policyName = policyName;
    this.policyType = new EnumWrapper<CreateRetentionPolicyRequestBodyPolicyTypeField>(policyType);
    this.dispositionAction = dispositionAction;
  }

  public CreateRetentionPolicyRequestBody(
      String policyName,
      EnumWrapper<CreateRetentionPolicyRequestBodyPolicyTypeField> policyType,
      CreateRetentionPolicyRequestBodyDispositionActionField dispositionAction) {
    super();
    this.policyName = policyName;
    this.policyType = policyType;
    this.dispositionAction =
        new EnumWrapper<CreateRetentionPolicyRequestBodyDispositionActionField>(dispositionAction);
  }

  public CreateRetentionPolicyRequestBody(
      @JsonProperty("policy_name") String policyName,
      @JsonProperty("policy_type")
          EnumWrapper<CreateRetentionPolicyRequestBodyPolicyTypeField> policyType,
      @JsonProperty("disposition_action")
          EnumWrapper<CreateRetentionPolicyRequestBodyDispositionActionField> dispositionAction) {
    super();
    this.policyName = policyName;
    this.policyType = policyType;
    this.dispositionAction = dispositionAction;
  }

  protected CreateRetentionPolicyRequestBody(Builder builder) {
    super();
    this.policyName = builder.policyName;
    this.description = builder.description;
    this.policyType = builder.policyType;
    this.dispositionAction = builder.dispositionAction;
    this.retentionLength = builder.retentionLength;
    this.retentionType = builder.retentionType;
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

  public EnumWrapper<CreateRetentionPolicyRequestBodyPolicyTypeField> getPolicyType() {
    return policyType;
  }

  public EnumWrapper<CreateRetentionPolicyRequestBodyDispositionActionField>
      getDispositionAction() {
    return dispositionAction;
  }

  public String getRetentionLength() {
    return retentionLength;
  }

  public EnumWrapper<CreateRetentionPolicyRequestBodyRetentionTypeField> getRetentionType() {
    return retentionType;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateRetentionPolicyRequestBody casted = (CreateRetentionPolicyRequestBody) o;
    return Objects.equals(policyName, casted.policyName)
        && Objects.equals(description, casted.description)
        && Objects.equals(policyType, casted.policyType)
        && Objects.equals(dispositionAction, casted.dispositionAction)
        && Objects.equals(retentionLength, casted.retentionLength)
        && Objects.equals(retentionType, casted.retentionType)
        && Objects.equals(canOwnerExtendRetention, casted.canOwnerExtendRetention)
        && Objects.equals(areOwnersNotified, casted.areOwnersNotified)
        && Objects.equals(customNotificationRecipients, casted.customNotificationRecipients);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        policyName,
        description,
        policyType,
        dispositionAction,
        retentionLength,
        retentionType,
        canOwnerExtendRetention,
        areOwnersNotified,
        customNotificationRecipients);
  }

  @Override
  public String toString() {
    return "CreateRetentionPolicyRequestBody{"
        + "policyName='"
        + policyName
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
        + "dispositionAction='"
        + dispositionAction
        + '\''
        + ", "
        + "retentionLength='"
        + retentionLength
        + '\''
        + ", "
        + "retentionType='"
        + retentionType
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

    protected final String policyName;

    protected String description;

    protected final EnumWrapper<CreateRetentionPolicyRequestBodyPolicyTypeField> policyType;

    protected final EnumWrapper<CreateRetentionPolicyRequestBodyDispositionActionField>
        dispositionAction;

    protected String retentionLength;

    protected EnumWrapper<CreateRetentionPolicyRequestBodyRetentionTypeField> retentionType;

    protected Boolean canOwnerExtendRetention;

    protected Boolean areOwnersNotified;

    protected List<UserMini> customNotificationRecipients;

    public Builder(
        String policyName,
        CreateRetentionPolicyRequestBodyPolicyTypeField policyType,
        CreateRetentionPolicyRequestBodyDispositionActionField dispositionAction) {
      super();
      this.policyName = policyName;
      this.policyType =
          new EnumWrapper<CreateRetentionPolicyRequestBodyPolicyTypeField>(policyType);
      this.dispositionAction =
          new EnumWrapper<CreateRetentionPolicyRequestBodyDispositionActionField>(
              dispositionAction);
    }

    public Builder(
        String policyName,
        CreateRetentionPolicyRequestBodyPolicyTypeField policyType,
        EnumWrapper<CreateRetentionPolicyRequestBodyDispositionActionField> dispositionAction) {
      super();
      this.policyName = policyName;
      this.policyType =
          new EnumWrapper<CreateRetentionPolicyRequestBodyPolicyTypeField>(policyType);
      this.dispositionAction = dispositionAction;
    }

    public Builder(
        String policyName,
        EnumWrapper<CreateRetentionPolicyRequestBodyPolicyTypeField> policyType,
        CreateRetentionPolicyRequestBodyDispositionActionField dispositionAction) {
      super();
      this.policyName = policyName;
      this.policyType = policyType;
      this.dispositionAction =
          new EnumWrapper<CreateRetentionPolicyRequestBodyDispositionActionField>(
              dispositionAction);
    }

    public Builder(
        String policyName,
        EnumWrapper<CreateRetentionPolicyRequestBodyPolicyTypeField> policyType,
        EnumWrapper<CreateRetentionPolicyRequestBodyDispositionActionField> dispositionAction) {
      super();
      this.policyName = policyName;
      this.policyType = policyType;
      this.dispositionAction = dispositionAction;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder retentionLength(String retentionLength) {
      this.retentionLength = retentionLength;
      return this;
    }

    public Builder retentionType(CreateRetentionPolicyRequestBodyRetentionTypeField retentionType) {
      this.retentionType =
          new EnumWrapper<CreateRetentionPolicyRequestBodyRetentionTypeField>(retentionType);
      return this;
    }

    public Builder retentionType(
        EnumWrapper<CreateRetentionPolicyRequestBodyRetentionTypeField> retentionType) {
      this.retentionType = retentionType;
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

    public CreateRetentionPolicyRequestBody build() {
      return new CreateRetentionPolicyRequestBody(this);
    }
  }
}
