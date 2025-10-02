package com.box.sdkgen.schemas.legalholdpolicy;

import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.legalholdpolicymini.LegalHoldPolicyMini;
import com.box.sdkgen.schemas.legalholdpolicymini.LegalHoldPolicyMiniTypeField;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Legal Hold Policy information describes the basic characteristics of the Policy, such as name,
 * description, and filter dates.
 */
@JsonFilter("nullablePropertyFilter")
public class LegalHoldPolicy extends LegalHoldPolicyMini {

  /** Name of the legal hold policy. */
  @JsonProperty("policy_name")
  protected String policyName;

  /** Description of the legal hold policy. Optional property with a 500 character limit. */
  protected String description;

  /**
   * Possible values: * 'active' - the policy is not in a transition state. * 'applying' - that the
   * policy is in the process of being applied. * 'releasing' - that the process is in the process
   * of being released. * 'released' - the policy is no longer active.
   */
  @JsonDeserialize(using = LegalHoldPolicyStatusField.LegalHoldPolicyStatusFieldDeserializer.class)
  @JsonSerialize(using = LegalHoldPolicyStatusField.LegalHoldPolicyStatusFieldSerializer.class)
  protected EnumWrapper<LegalHoldPolicyStatusField> status;

  /** Counts of assignments within this a legal hold policy by item type. */
  @JsonProperty("assignment_counts")
  protected LegalHoldPolicyAssignmentCountsField assignmentCounts;

  @JsonProperty("created_by")
  protected UserMini createdBy;

  /** When the legal hold policy object was created. */
  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  /**
   * When the legal hold policy object was modified. Does not update when assignments are added or
   * removed.
   */
  @JsonProperty("modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime modifiedAt;

  /**
   * When the policy release request was sent. (Because it can take time for a policy to fully
   * delete, this isn't quite the same time that the policy is fully deleted).
   *
   * <p>If `null`, the policy was not deleted.
   */
  @JsonProperty("deleted_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime deletedAt;

  /** User-specified, optional date filter applies to Custodian assignments only. */
  @JsonProperty("filter_started_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime filterStartedAt;

  /** User-specified, optional date filter applies to Custodian assignments only. */
  @JsonProperty("filter_ended_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime filterEndedAt;

  /** Optional notes about why the policy was created. */
  @JsonProperty("release_notes")
  protected String releaseNotes;

  public LegalHoldPolicy(@JsonProperty("id") String id) {
    super(id);
  }

  protected LegalHoldPolicy(Builder builder) {
    super(builder);
    this.policyName = builder.policyName;
    this.description = builder.description;
    this.status = builder.status;
    this.assignmentCounts = builder.assignmentCounts;
    this.createdBy = builder.createdBy;
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
    this.deletedAt = builder.deletedAt;
    this.filterStartedAt = builder.filterStartedAt;
    this.filterEndedAt = builder.filterEndedAt;
    this.releaseNotes = builder.releaseNotes;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getPolicyName() {
    return policyName;
  }

  public String getDescription() {
    return description;
  }

  public EnumWrapper<LegalHoldPolicyStatusField> getStatus() {
    return status;
  }

  public LegalHoldPolicyAssignmentCountsField getAssignmentCounts() {
    return assignmentCounts;
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

  public OffsetDateTime getDeletedAt() {
    return deletedAt;
  }

  public OffsetDateTime getFilterStartedAt() {
    return filterStartedAt;
  }

  public OffsetDateTime getFilterEndedAt() {
    return filterEndedAt;
  }

  public String getReleaseNotes() {
    return releaseNotes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LegalHoldPolicy casted = (LegalHoldPolicy) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(policyName, casted.policyName)
        && Objects.equals(description, casted.description)
        && Objects.equals(status, casted.status)
        && Objects.equals(assignmentCounts, casted.assignmentCounts)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(modifiedAt, casted.modifiedAt)
        && Objects.equals(deletedAt, casted.deletedAt)
        && Objects.equals(filterStartedAt, casted.filterStartedAt)
        && Objects.equals(filterEndedAt, casted.filterEndedAt)
        && Objects.equals(releaseNotes, casted.releaseNotes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        type,
        policyName,
        description,
        status,
        assignmentCounts,
        createdBy,
        createdAt,
        modifiedAt,
        deletedAt,
        filterStartedAt,
        filterEndedAt,
        releaseNotes);
  }

  @Override
  public String toString() {
    return "LegalHoldPolicy{"
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
        + "description='"
        + description
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + ", "
        + "assignmentCounts='"
        + assignmentCounts
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
        + "deletedAt='"
        + deletedAt
        + '\''
        + ", "
        + "filterStartedAt='"
        + filterStartedAt
        + '\''
        + ", "
        + "filterEndedAt='"
        + filterEndedAt
        + '\''
        + ", "
        + "releaseNotes='"
        + releaseNotes
        + '\''
        + "}";
  }

  public static class Builder extends LegalHoldPolicyMini.Builder {

    protected String policyName;

    protected String description;

    protected EnumWrapper<LegalHoldPolicyStatusField> status;

    protected LegalHoldPolicyAssignmentCountsField assignmentCounts;

    protected UserMini createdBy;

    protected OffsetDateTime createdAt;

    protected OffsetDateTime modifiedAt;

    protected OffsetDateTime deletedAt;

    protected OffsetDateTime filterStartedAt;

    protected OffsetDateTime filterEndedAt;

    protected String releaseNotes;

    public Builder(String id) {
      super(id);
    }

    public Builder policyName(String policyName) {
      this.policyName = policyName;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder status(LegalHoldPolicyStatusField status) {
      this.status = new EnumWrapper<LegalHoldPolicyStatusField>(status);
      return this;
    }

    public Builder status(EnumWrapper<LegalHoldPolicyStatusField> status) {
      this.status = status;
      return this;
    }

    public Builder assignmentCounts(LegalHoldPolicyAssignmentCountsField assignmentCounts) {
      this.assignmentCounts = assignmentCounts;
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

    public Builder deletedAt(OffsetDateTime deletedAt) {
      this.deletedAt = deletedAt;
      return this;
    }

    public Builder filterStartedAt(OffsetDateTime filterStartedAt) {
      this.filterStartedAt = filterStartedAt;
      return this;
    }

    public Builder filterEndedAt(OffsetDateTime filterEndedAt) {
      this.filterEndedAt = filterEndedAt;
      return this;
    }

    public Builder releaseNotes(String releaseNotes) {
      this.releaseNotes = releaseNotes;
      return this;
    }

    @Override
    public Builder type(LegalHoldPolicyMiniTypeField type) {
      this.type = new EnumWrapper<LegalHoldPolicyMiniTypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<LegalHoldPolicyMiniTypeField> type) {
      this.type = type;
      return this;
    }

    public LegalHoldPolicy build() {
      return new LegalHoldPolicy(this);
    }
  }
}
