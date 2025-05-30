package com.box.sdkgen.schemas.legalholdpolicy;

import com.box.sdkgen.schemas.legalholdpolicymini.LegalHoldPolicyMini;
import com.box.sdkgen.schemas.legalholdpolicymini.LegalHoldPolicyMiniTypeField;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class LegalHoldPolicy extends LegalHoldPolicyMini {

  @JsonProperty("policy_name")
  protected String policyName;

  protected String description;

  @JsonDeserialize(using = LegalHoldPolicyStatusField.LegalHoldPolicyStatusFieldDeserializer.class)
  @JsonSerialize(using = LegalHoldPolicyStatusField.LegalHoldPolicyStatusFieldSerializer.class)
  protected EnumWrapper<LegalHoldPolicyStatusField> status;

  @JsonProperty("assignment_counts")
  protected LegalHoldPolicyAssignmentCountsField assignmentCounts;

  @JsonProperty("created_by")
  protected UserMini createdBy;

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("modified_at")
  protected String modifiedAt;

  @JsonProperty("deleted_at")
  protected String deletedAt;

  @JsonProperty("filter_started_at")
  protected String filterStartedAt;

  @JsonProperty("filter_ended_at")
  protected String filterEndedAt;

  @JsonProperty("release_notes")
  protected String releaseNotes;

  public LegalHoldPolicy(@JsonProperty("id") String id) {
    super(id);
  }

  protected LegalHoldPolicy(LegalHoldPolicyBuilder builder) {
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

  public String getCreatedAt() {
    return createdAt;
  }

  public String getModifiedAt() {
    return modifiedAt;
  }

  public String getDeletedAt() {
    return deletedAt;
  }

  public String getFilterStartedAt() {
    return filterStartedAt;
  }

  public String getFilterEndedAt() {
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

  public static class LegalHoldPolicyBuilder extends LegalHoldPolicyMiniBuilder {

    protected String policyName;

    protected String description;

    protected EnumWrapper<LegalHoldPolicyStatusField> status;

    protected LegalHoldPolicyAssignmentCountsField assignmentCounts;

    protected UserMini createdBy;

    protected String createdAt;

    protected String modifiedAt;

    protected String deletedAt;

    protected String filterStartedAt;

    protected String filterEndedAt;

    protected String releaseNotes;

    public LegalHoldPolicyBuilder(String id) {
      super(id);
    }

    public LegalHoldPolicyBuilder policyName(String policyName) {
      this.policyName = policyName;
      return this;
    }

    public LegalHoldPolicyBuilder description(String description) {
      this.description = description;
      return this;
    }

    public LegalHoldPolicyBuilder status(LegalHoldPolicyStatusField status) {
      this.status = new EnumWrapper<LegalHoldPolicyStatusField>(status);
      return this;
    }

    public LegalHoldPolicyBuilder status(EnumWrapper<LegalHoldPolicyStatusField> status) {
      this.status = status;
      return this;
    }

    public LegalHoldPolicyBuilder assignmentCounts(
        LegalHoldPolicyAssignmentCountsField assignmentCounts) {
      this.assignmentCounts = assignmentCounts;
      return this;
    }

    public LegalHoldPolicyBuilder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public LegalHoldPolicyBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public LegalHoldPolicyBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public LegalHoldPolicyBuilder deletedAt(String deletedAt) {
      this.deletedAt = deletedAt;
      return this;
    }

    public LegalHoldPolicyBuilder filterStartedAt(String filterStartedAt) {
      this.filterStartedAt = filterStartedAt;
      return this;
    }

    public LegalHoldPolicyBuilder filterEndedAt(String filterEndedAt) {
      this.filterEndedAt = filterEndedAt;
      return this;
    }

    public LegalHoldPolicyBuilder releaseNotes(String releaseNotes) {
      this.releaseNotes = releaseNotes;
      return this;
    }

    @Override
    public LegalHoldPolicyBuilder type(LegalHoldPolicyMiniTypeField type) {
      this.type = new EnumWrapper<LegalHoldPolicyMiniTypeField>(type);
      return this;
    }

    @Override
    public LegalHoldPolicyBuilder type(EnumWrapper<LegalHoldPolicyMiniTypeField> type) {
      this.type = type;
      return this;
    }

    public LegalHoldPolicy build() {
      return new LegalHoldPolicy(this);
    }
  }
}
