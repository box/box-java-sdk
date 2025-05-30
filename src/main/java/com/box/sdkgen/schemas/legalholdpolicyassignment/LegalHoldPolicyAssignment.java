package com.box.sdkgen.schemas.legalholdpolicyassignment;

import com.box.sdkgen.schemas.fileorfolderorweblink.FileOrFolderOrWebLink;
import com.box.sdkgen.schemas.legalholdpolicyassignmentbase.LegalHoldPolicyAssignmentBase;
import com.box.sdkgen.schemas.legalholdpolicyassignmentbase.LegalHoldPolicyAssignmentBaseTypeField;
import com.box.sdkgen.schemas.legalholdpolicymini.LegalHoldPolicyMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class LegalHoldPolicyAssignment extends LegalHoldPolicyAssignmentBase {

  @JsonProperty("legal_hold_policy")
  protected LegalHoldPolicyMini legalHoldPolicy;

  @JsonProperty("assigned_to")
  protected FileOrFolderOrWebLink assignedTo;

  @JsonProperty("assigned_by")
  protected UserMini assignedBy;

  @JsonProperty("assigned_at")
  protected String assignedAt;

  @JsonProperty("deleted_at")
  protected String deletedAt;

  public LegalHoldPolicyAssignment() {
    super();
  }

  protected LegalHoldPolicyAssignment(LegalHoldPolicyAssignmentBuilder builder) {
    super(builder);
    this.legalHoldPolicy = builder.legalHoldPolicy;
    this.assignedTo = builder.assignedTo;
    this.assignedBy = builder.assignedBy;
    this.assignedAt = builder.assignedAt;
    this.deletedAt = builder.deletedAt;
  }

  public LegalHoldPolicyMini getLegalHoldPolicy() {
    return legalHoldPolicy;
  }

  public FileOrFolderOrWebLink getAssignedTo() {
    return assignedTo;
  }

  public UserMini getAssignedBy() {
    return assignedBy;
  }

  public String getAssignedAt() {
    return assignedAt;
  }

  public String getDeletedAt() {
    return deletedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LegalHoldPolicyAssignment casted = (LegalHoldPolicyAssignment) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(legalHoldPolicy, casted.legalHoldPolicy)
        && Objects.equals(assignedTo, casted.assignedTo)
        && Objects.equals(assignedBy, casted.assignedBy)
        && Objects.equals(assignedAt, casted.assignedAt)
        && Objects.equals(deletedAt, casted.deletedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, legalHoldPolicy, assignedTo, assignedBy, assignedAt, deletedAt);
  }

  @Override
  public String toString() {
    return "LegalHoldPolicyAssignment{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "legalHoldPolicy='"
        + legalHoldPolicy
        + '\''
        + ", "
        + "assignedTo='"
        + assignedTo
        + '\''
        + ", "
        + "assignedBy='"
        + assignedBy
        + '\''
        + ", "
        + "assignedAt='"
        + assignedAt
        + '\''
        + ", "
        + "deletedAt='"
        + deletedAt
        + '\''
        + "}";
  }

  public static class LegalHoldPolicyAssignmentBuilder
      extends LegalHoldPolicyAssignmentBaseBuilder {

    protected LegalHoldPolicyMini legalHoldPolicy;

    protected FileOrFolderOrWebLink assignedTo;

    protected UserMini assignedBy;

    protected String assignedAt;

    protected String deletedAt;

    public LegalHoldPolicyAssignmentBuilder legalHoldPolicy(LegalHoldPolicyMini legalHoldPolicy) {
      this.legalHoldPolicy = legalHoldPolicy;
      return this;
    }

    public LegalHoldPolicyAssignmentBuilder assignedTo(FileOrFolderOrWebLink assignedTo) {
      this.assignedTo = assignedTo;
      return this;
    }

    public LegalHoldPolicyAssignmentBuilder assignedBy(UserMini assignedBy) {
      this.assignedBy = assignedBy;
      return this;
    }

    public LegalHoldPolicyAssignmentBuilder assignedAt(String assignedAt) {
      this.assignedAt = assignedAt;
      return this;
    }

    public LegalHoldPolicyAssignmentBuilder deletedAt(String deletedAt) {
      this.deletedAt = deletedAt;
      return this;
    }

    @Override
    public LegalHoldPolicyAssignmentBuilder id(String id) {
      this.id = id;
      return this;
    }

    @Override
    public LegalHoldPolicyAssignmentBuilder type(LegalHoldPolicyAssignmentBaseTypeField type) {
      this.type = new EnumWrapper<LegalHoldPolicyAssignmentBaseTypeField>(type);
      return this;
    }

    @Override
    public LegalHoldPolicyAssignmentBuilder type(
        EnumWrapper<LegalHoldPolicyAssignmentBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public LegalHoldPolicyAssignment build() {
      return new LegalHoldPolicyAssignment(this);
    }
  }
}
