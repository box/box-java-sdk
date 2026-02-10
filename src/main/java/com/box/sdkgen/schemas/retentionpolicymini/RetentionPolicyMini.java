package com.box.sdkgen.schemas.retentionpolicymini;

import com.box.sdkgen.schemas.retentionpolicybase.RetentionPolicyBase;
import com.box.sdkgen.schemas.retentionpolicybase.RetentionPolicyBaseTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** A mini representation of a retention policy, used when nested within another resource. */
@JsonFilter("nullablePropertyFilter")
public class RetentionPolicyMini extends RetentionPolicyBase {

  /** The name given to the retention policy. */
  @JsonProperty("policy_name")
  protected String policyName;

  /**
   * The length of the retention policy. This value specifies the duration in days that the
   * retention policy will be active for after being assigned to content. If the policy has a
   * `policy_type` of `indefinite`, the `retention_length` will also be `indefinite`.
   */
  @JsonProperty("retention_length")
  protected String retentionLength;

  /**
   * The disposition action of the retention policy. This action can be `permanently_delete`, which
   * will cause the content retained by the policy to be permanently deleted, or `remove_retention`,
   * which will lift the retention policy from the content, allowing it to be deleted by users, once
   * the retention policy has expired.
   */
  @JsonDeserialize(
      using =
          RetentionPolicyMiniDispositionActionField
              .RetentionPolicyMiniDispositionActionFieldDeserializer.class)
  @JsonSerialize(
      using =
          RetentionPolicyMiniDispositionActionField
              .RetentionPolicyMiniDispositionActionFieldSerializer.class)
  @JsonProperty("disposition_action")
  protected EnumWrapper<RetentionPolicyMiniDispositionActionField> dispositionAction;

  public RetentionPolicyMini(@JsonProperty("id") String id) {
    super(id);
  }

  protected RetentionPolicyMini(Builder builder) {
    super(builder);
    this.policyName = builder.policyName;
    this.retentionLength = builder.retentionLength;
    this.dispositionAction = builder.dispositionAction;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getPolicyName() {
    return policyName;
  }

  public String getRetentionLength() {
    return retentionLength;
  }

  public EnumWrapper<RetentionPolicyMiniDispositionActionField> getDispositionAction() {
    return dispositionAction;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RetentionPolicyMini casted = (RetentionPolicyMini) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(policyName, casted.policyName)
        && Objects.equals(retentionLength, casted.retentionLength)
        && Objects.equals(dispositionAction, casted.dispositionAction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, policyName, retentionLength, dispositionAction);
  }

  @Override
  public String toString() {
    return "RetentionPolicyMini{"
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
        + "}";
  }

  public static class Builder extends RetentionPolicyBase.Builder {

    protected String policyName;

    protected String retentionLength;

    protected EnumWrapper<RetentionPolicyMiniDispositionActionField> dispositionAction;

    public Builder(String id) {
      super(id);
    }

    public Builder policyName(String policyName) {
      this.policyName = policyName;
      return this;
    }

    public Builder retentionLength(String retentionLength) {
      this.retentionLength = retentionLength;
      return this;
    }

    public Builder dispositionAction(RetentionPolicyMiniDispositionActionField dispositionAction) {
      this.dispositionAction =
          new EnumWrapper<RetentionPolicyMiniDispositionActionField>(dispositionAction);
      return this;
    }

    public Builder dispositionAction(
        EnumWrapper<RetentionPolicyMiniDispositionActionField> dispositionAction) {
      this.dispositionAction = dispositionAction;
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

    public RetentionPolicyMini build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<RetentionPolicyBaseTypeField>(
                RetentionPolicyBaseTypeField.RETENTION_POLICY);
      }
      return new RetentionPolicyMini(this);
    }
  }
}
