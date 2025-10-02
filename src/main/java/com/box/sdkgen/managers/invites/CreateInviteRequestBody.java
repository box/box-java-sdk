package com.box.sdkgen.managers.invites;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateInviteRequestBody extends SerializableObject {

  /** The enterprise to invite the user to. */
  protected final CreateInviteRequestBodyEnterpriseField enterprise;

  /** The user to invite. */
  @JsonProperty("actionable_by")
  protected final CreateInviteRequestBodyActionableByField actionableBy;

  public CreateInviteRequestBody(
      @JsonProperty("enterprise") CreateInviteRequestBodyEnterpriseField enterprise,
      @JsonProperty("actionable_by") CreateInviteRequestBodyActionableByField actionableBy) {
    super();
    this.enterprise = enterprise;
    this.actionableBy = actionableBy;
  }

  public CreateInviteRequestBodyEnterpriseField getEnterprise() {
    return enterprise;
  }

  public CreateInviteRequestBodyActionableByField getActionableBy() {
    return actionableBy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateInviteRequestBody casted = (CreateInviteRequestBody) o;
    return Objects.equals(enterprise, casted.enterprise)
        && Objects.equals(actionableBy, casted.actionableBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(enterprise, actionableBy);
  }

  @Override
  public String toString() {
    return "CreateInviteRequestBody{"
        + "enterprise='"
        + enterprise
        + '\''
        + ", "
        + "actionableBy='"
        + actionableBy
        + '\''
        + "}";
  }
}
