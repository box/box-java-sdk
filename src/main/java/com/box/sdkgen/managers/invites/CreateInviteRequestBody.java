package com.box.sdkgen.managers.invites;

import com.box.sdkgen.internal.NullableFieldTracker;
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

  /**
   * If set to `true`, the user is added to the enterprise automatically after 14 days instead of
   * having to accept the invite. Only unmanaged users can be invited this way. Availability depends
   * on the enterprise's plan.
   */
  @JsonProperty("is_auto_roll_in_enabled")
  protected Boolean isAutoRollInEnabled;

  public CreateInviteRequestBody(
      @JsonProperty("enterprise") CreateInviteRequestBodyEnterpriseField enterprise,
      @JsonProperty("actionable_by") CreateInviteRequestBodyActionableByField actionableBy) {
    super();
    this.enterprise = enterprise;
    this.actionableBy = actionableBy;
  }

  protected CreateInviteRequestBody(Builder builder) {
    super();
    this.enterprise = builder.enterprise;
    this.actionableBy = builder.actionableBy;
    this.isAutoRollInEnabled = builder.isAutoRollInEnabled;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public CreateInviteRequestBodyEnterpriseField getEnterprise() {
    return enterprise;
  }

  public CreateInviteRequestBodyActionableByField getActionableBy() {
    return actionableBy;
  }

  public Boolean getIsAutoRollInEnabled() {
    return isAutoRollInEnabled;
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
        && Objects.equals(actionableBy, casted.actionableBy)
        && Objects.equals(isAutoRollInEnabled, casted.isAutoRollInEnabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(enterprise, actionableBy, isAutoRollInEnabled);
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
        + ", "
        + "isAutoRollInEnabled='"
        + isAutoRollInEnabled
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final CreateInviteRequestBodyEnterpriseField enterprise;

    protected final CreateInviteRequestBodyActionableByField actionableBy;

    protected Boolean isAutoRollInEnabled;

    public Builder(
        CreateInviteRequestBodyEnterpriseField enterprise,
        CreateInviteRequestBodyActionableByField actionableBy) {
      super();
      this.enterprise = enterprise;
      this.actionableBy = actionableBy;
    }

    public Builder isAutoRollInEnabled(Boolean isAutoRollInEnabled) {
      this.isAutoRollInEnabled = isAutoRollInEnabled;
      return this;
    }

    public CreateInviteRequestBody build() {
      return new CreateInviteRequestBody(this);
    }
  }
}
