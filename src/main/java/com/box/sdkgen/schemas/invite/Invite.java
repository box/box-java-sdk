package com.box.sdkgen.schemas.invite;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

/** An invite for a user to an enterprise. */
@JsonFilter("nullablePropertyFilter")
public class Invite extends SerializableObject {

  /** The unique identifier for this invite. */
  protected final String id;

  /** The value will always be `invite`. */
  @JsonDeserialize(using = InviteTypeField.InviteTypeFieldDeserializer.class)
  @JsonSerialize(using = InviteTypeField.InviteTypeFieldSerializer.class)
  protected EnumWrapper<InviteTypeField> type;

  /** A representation of a Box enterprise. */
  @JsonProperty("invited_to")
  protected InviteInvitedToField invitedTo;

  @JsonProperty("actionable_by")
  protected UserMini actionableBy;

  @JsonProperty("invited_by")
  protected UserMini invitedBy;

  /** The status of the invite. */
  protected String status;

  /** When the invite was created. */
  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  /** When the invite was modified. */
  @JsonProperty("modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime modifiedAt;

  /**
   * The date and time when the user is added to the enterprise automatically, or `null` if the user
   * has to accept the invite.
   */
  @JsonProperty("auto_roll_in_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected OffsetDateTime autoRollInAt;

  /**
   * Whether the user is added to the enterprise automatically, without having to accept the invite.
   * When `true`, `auto_roll_in_at` contains the date and time of the roll-in.
   */
  @JsonProperty("is_auto_roll_in")
  protected Boolean isAutoRollIn;

  public Invite(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type = new EnumWrapper<InviteTypeField>(InviteTypeField.INVITE);
  }

  protected Invite(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.invitedTo = builder.invitedTo;
    this.actionableBy = builder.actionableBy;
    this.invitedBy = builder.invitedBy;
    this.status = builder.status;
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
    this.autoRollInAt = builder.autoRollInAt;
    this.isAutoRollIn = builder.isAutoRollIn;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public OffsetDateTime getModifiedAt() {
    return modifiedAt;
  }

  public OffsetDateTime getAutoRollInAt() {
    return autoRollInAt;
  }

  public Boolean getIsAutoRollIn() {
    return isAutoRollIn;
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
        && Objects.equals(modifiedAt, casted.modifiedAt)
        && Objects.equals(autoRollInAt, casted.autoRollInAt)
        && Objects.equals(isAutoRollIn, casted.isAutoRollIn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        type,
        invitedTo,
        actionableBy,
        invitedBy,
        status,
        createdAt,
        modifiedAt,
        autoRollInAt,
        isAutoRollIn);
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
        + ", "
        + "autoRollInAt='"
        + autoRollInAt
        + '\''
        + ", "
        + "isAutoRollIn='"
        + isAutoRollIn
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<InviteTypeField> type;

    protected InviteInvitedToField invitedTo;

    protected UserMini actionableBy;

    protected UserMini invitedBy;

    protected String status;

    protected OffsetDateTime createdAt;

    protected OffsetDateTime modifiedAt;

    protected OffsetDateTime autoRollInAt;

    protected Boolean isAutoRollIn;

    public Builder(String id) {
      super();
      this.id = id;
    }

    public Builder type(InviteTypeField type) {
      this.type = new EnumWrapper<InviteTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<InviteTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder invitedTo(InviteInvitedToField invitedTo) {
      this.invitedTo = invitedTo;
      return this;
    }

    public Builder actionableBy(UserMini actionableBy) {
      this.actionableBy = actionableBy;
      return this;
    }

    public Builder invitedBy(UserMini invitedBy) {
      this.invitedBy = invitedBy;
      return this;
    }

    public Builder status(String status) {
      this.status = status;
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

    public Builder autoRollInAt(OffsetDateTime autoRollInAt) {
      this.autoRollInAt = autoRollInAt;
      this.markNullableFieldAsSet("auto_roll_in_at");
      return this;
    }

    public Builder isAutoRollIn(Boolean isAutoRollIn) {
      this.isAutoRollIn = isAutoRollIn;
      return this;
    }

    public Invite build() {
      if (this.type == null) {
        this.type = new EnumWrapper<InviteTypeField>(InviteTypeField.INVITE);
      }
      return new Invite(this);
    }
  }
}
