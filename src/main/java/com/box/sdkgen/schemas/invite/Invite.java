package com.box.sdkgen.schemas.invite;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class Invite extends SerializableObject {

  protected final String id;

  @JsonDeserialize(using = InviteTypeField.InviteTypeFieldDeserializer.class)
  @JsonSerialize(using = InviteTypeField.InviteTypeFieldSerializer.class)
  protected EnumWrapper<InviteTypeField> type;

  @JsonProperty("invited_to")
  protected InviteInvitedToField invitedTo;

  @JsonProperty("actionable_by")
  protected UserMini actionableBy;

  @JsonProperty("invited_by")
  protected UserMini invitedBy;

  protected String status;

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("modified_at")
  protected String modifiedAt;

  public Invite(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type = new EnumWrapper<InviteTypeField>(InviteTypeField.INVITE);
  }

  protected Invite(InviteBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.invitedTo = builder.invitedTo;
    this.actionableBy = builder.actionableBy;
    this.invitedBy = builder.invitedBy;
    this.status = builder.status;
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<InviteTypeField> getType() {
    return type;
  }

  public InviteInvitedToField getInvitedTo() {
    return invitedTo;
  }

  public UserMini getActionableBy() {
    return actionableBy;
  }

  public UserMini getInvitedBy() {
    return invitedBy;
  }

  public String getStatus() {
    return status;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getModifiedAt() {
    return modifiedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Invite casted = (Invite) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(invitedTo, casted.invitedTo)
        && Objects.equals(actionableBy, casted.actionableBy)
        && Objects.equals(invitedBy, casted.invitedBy)
        && Objects.equals(status, casted.status)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(modifiedAt, casted.modifiedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, type, invitedTo, actionableBy, invitedBy, status, createdAt, modifiedAt);
  }

  @Override
  public String toString() {
    return "Invite{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "invitedTo='"
        + invitedTo
        + '\''
        + ", "
        + "actionableBy='"
        + actionableBy
        + '\''
        + ", "
        + "invitedBy='"
        + invitedBy
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "modifiedAt='"
        + modifiedAt
        + '\''
        + "}";
  }

  public static class InviteBuilder {

    protected final String id;

    protected EnumWrapper<InviteTypeField> type;

    protected InviteInvitedToField invitedTo;

    protected UserMini actionableBy;

    protected UserMini invitedBy;

    protected String status;

    protected String createdAt;

    protected String modifiedAt;

    public InviteBuilder(String id) {
      this.id = id;
      this.type = new EnumWrapper<InviteTypeField>(InviteTypeField.INVITE);
    }

    public InviteBuilder type(InviteTypeField type) {
      this.type = new EnumWrapper<InviteTypeField>(type);
      return this;
    }

    public InviteBuilder type(EnumWrapper<InviteTypeField> type) {
      this.type = type;
      return this;
    }

    public InviteBuilder invitedTo(InviteInvitedToField invitedTo) {
      this.invitedTo = invitedTo;
      return this;
    }

    public InviteBuilder actionableBy(UserMini actionableBy) {
      this.actionableBy = actionableBy;
      return this;
    }

    public InviteBuilder invitedBy(UserMini invitedBy) {
      this.invitedBy = invitedBy;
      return this;
    }

    public InviteBuilder status(String status) {
      this.status = status;
      return this;
    }

    public InviteBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public InviteBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public Invite build() {
      return new Invite(this);
    }
  }
}
