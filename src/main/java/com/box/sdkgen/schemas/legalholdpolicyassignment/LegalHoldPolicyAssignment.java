package com.box.sdkgen.schemas.legalholdpolicyassignment;

import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.file.File;
import com.box.sdkgen.schemas.folder.Folder;
import com.box.sdkgen.schemas.legalholdpolicyassigneditem.LegalHoldPolicyAssignedItem;
import com.box.sdkgen.schemas.legalholdpolicyassignmentbase.LegalHoldPolicyAssignmentBase;
import com.box.sdkgen.schemas.legalholdpolicyassignmentbase.LegalHoldPolicyAssignmentBaseTypeField;
import com.box.sdkgen.schemas.legalholdpolicymini.LegalHoldPolicyMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.schemas.weblink.WebLink;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class LegalHoldPolicyAssignment extends LegalHoldPolicyAssignmentBase {

  @JsonProperty("legal_hold_policy")
  protected LegalHoldPolicyMini legalHoldPolicy;

  @JsonProperty("assigned_to")
  protected LegalHoldPolicyAssignedItem assignedTo;

  @JsonProperty("assigned_by")
  protected UserMini assignedBy;

  @JsonProperty("assigned_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date assignedAt;

  @JsonProperty("deleted_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date deletedAt;

  public LegalHoldPolicyAssignment() {
    super();
  }

  protected LegalHoldPolicyAssignment(Builder builder) {
    super(builder);
    this.legalHoldPolicy = builder.legalHoldPolicy;
    this.assignedTo = builder.assignedTo;
    this.assignedBy = builder.assignedBy;
    this.assignedAt = builder.assignedAt;
    this.deletedAt = builder.deletedAt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public LegalHoldPolicyMini getLegalHoldPolicy() {
    return legalHoldPolicy;
  }

  public LegalHoldPolicyAssignedItem getAssignedTo() {
    return assignedTo;
  }

  public UserMini getAssignedBy() {
    return assignedBy;
  }

  public Date getAssignedAt() {
    return assignedAt;
  }

  public Date getDeletedAt() {
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

  public static class Builder extends LegalHoldPolicyAssignmentBase.Builder {

    protected LegalHoldPolicyMini legalHoldPolicy;

    protected LegalHoldPolicyAssignedItem assignedTo;

    protected UserMini assignedBy;

    protected Date assignedAt;

    protected Date deletedAt;

    public Builder legalHoldPolicy(LegalHoldPolicyMini legalHoldPolicy) {
      this.legalHoldPolicy = legalHoldPolicy;
      return this;
    }

    public Builder assignedTo(File assignedTo) {
      this.assignedTo = new LegalHoldPolicyAssignedItem(assignedTo);
      return this;
    }

    public Builder assignedTo(Folder assignedTo) {
      this.assignedTo = new LegalHoldPolicyAssignedItem(assignedTo);
      return this;
    }

    public Builder assignedTo(WebLink assignedTo) {
      this.assignedTo = new LegalHoldPolicyAssignedItem(assignedTo);
      return this;
    }

    public Builder assignedTo(LegalHoldPolicyAssignedItem assignedTo) {
      this.assignedTo = assignedTo;
      return this;
    }

    public Builder assignedBy(UserMini assignedBy) {
      this.assignedBy = assignedBy;
      return this;
    }

    public Builder assignedAt(Date assignedAt) {
      this.assignedAt = assignedAt;
      return this;
    }

    public Builder deletedAt(Date deletedAt) {
      this.deletedAt = deletedAt;
      return this;
    }

    @Override
    public Builder id(String id) {
      this.id = id;
      return this;
    }

    @Override
    public Builder type(LegalHoldPolicyAssignmentBaseTypeField type) {
      this.type = new EnumWrapper<LegalHoldPolicyAssignmentBaseTypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<LegalHoldPolicyAssignmentBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public LegalHoldPolicyAssignment build() {
      return new LegalHoldPolicyAssignment(this);
    }
  }
}
