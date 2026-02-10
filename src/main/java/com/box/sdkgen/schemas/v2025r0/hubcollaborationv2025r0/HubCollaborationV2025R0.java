package com.box.sdkgen.schemas.v2025r0.hubcollaborationv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2025r0.groupminiv2025r0.GroupMiniV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubaccessgranteev2025r0.HubAccessGranteeV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubbasev2025r0.HubBaseV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubcollaborationuserv2025r0.HubCollaborationUserV2025R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * A Box Hub collaboration object grants a user or group access to a Box Hub with permissions
 * defined by a specific role.
 */
@JsonFilter("nullablePropertyFilter")
public class HubCollaborationV2025R0 extends SerializableObject {

  /** The unique identifier for this collaboration. */
  protected final String id;

  /** The value will always be `hub_collaboration`. */
  @JsonDeserialize(
      using = HubCollaborationV2025R0TypeField.HubCollaborationV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using = HubCollaborationV2025R0TypeField.HubCollaborationV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<HubCollaborationV2025R0TypeField> type;

  protected HubBaseV2025R0 hub;

  @JsonProperty("accessible_by")
  protected HubAccessGranteeV2025R0 accessibleBy;

  /**
   * The level of access granted to a Box Hub. Possible values are `editor`, `viewer`, and
   * `co-owner`.
   */
  protected String role;

  /**
   * The status of the collaboration invitation. If the status is `pending`, `login` and `name`
   * return an empty string.
   */
  @JsonDeserialize(
      using =
          HubCollaborationV2025R0StatusField.HubCollaborationV2025R0StatusFieldDeserializer.class)
  @JsonSerialize(
      using = HubCollaborationV2025R0StatusField.HubCollaborationV2025R0StatusFieldSerializer.class)
  protected EnumWrapper<HubCollaborationV2025R0StatusField> status;

  @JsonProperty("acceptance_requirements_status")
  protected HubCollaborationV2025R0AcceptanceRequirementsStatusField acceptanceRequirementsStatus;

  public HubCollaborationV2025R0(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<HubCollaborationV2025R0TypeField>(
            HubCollaborationV2025R0TypeField.HUB_COLLABORATION);
  }

  protected HubCollaborationV2025R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.hub = builder.hub;
    this.accessibleBy = builder.accessibleBy;
    this.role = builder.role;
    this.status = builder.status;
    this.acceptanceRequirementsStatus = builder.acceptanceRequirementsStatus;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<HubCollaborationV2025R0TypeField> getType() {
    return type;
  }

  public HubBaseV2025R0 getHub() {
    return hub;
  }

  public HubAccessGranteeV2025R0 getAccessibleBy() {
    return accessibleBy;
  }

  public String getRole() {
    return role;
  }

  public EnumWrapper<HubCollaborationV2025R0StatusField> getStatus() {
    return status;
  }

  public HubCollaborationV2025R0AcceptanceRequirementsStatusField
      getAcceptanceRequirementsStatus() {
    return acceptanceRequirementsStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HubCollaborationV2025R0 casted = (HubCollaborationV2025R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(hub, casted.hub)
        && Objects.equals(accessibleBy, casted.accessibleBy)
        && Objects.equals(role, casted.role)
        && Objects.equals(status, casted.status)
        && Objects.equals(acceptanceRequirementsStatus, casted.acceptanceRequirementsStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, hub, accessibleBy, role, status, acceptanceRequirementsStatus);
  }

  @Override
  public String toString() {
    return "HubCollaborationV2025R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "hub='"
        + hub
        + '\''
        + ", "
        + "accessibleBy='"
        + accessibleBy
        + '\''
        + ", "
        + "role='"
        + role
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + ", "
        + "acceptanceRequirementsStatus='"
        + acceptanceRequirementsStatus
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<HubCollaborationV2025R0TypeField> type;

    protected HubBaseV2025R0 hub;

    protected HubAccessGranteeV2025R0 accessibleBy;

    protected String role;

    protected EnumWrapper<HubCollaborationV2025R0StatusField> status;

    protected HubCollaborationV2025R0AcceptanceRequirementsStatusField acceptanceRequirementsStatus;

    public Builder(String id) {
      super();
      this.id = id;
    }

    public Builder type(HubCollaborationV2025R0TypeField type) {
      this.type = new EnumWrapper<HubCollaborationV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<HubCollaborationV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder hub(HubBaseV2025R0 hub) {
      this.hub = hub;
      return this;
    }

    public Builder accessibleBy(HubCollaborationUserV2025R0 accessibleBy) {
      this.accessibleBy = new HubAccessGranteeV2025R0(accessibleBy);
      return this;
    }

    public Builder accessibleBy(GroupMiniV2025R0 accessibleBy) {
      this.accessibleBy = new HubAccessGranteeV2025R0(accessibleBy);
      return this;
    }

    public Builder accessibleBy(HubAccessGranteeV2025R0 accessibleBy) {
      this.accessibleBy = accessibleBy;
      return this;
    }

    public Builder role(String role) {
      this.role = role;
      return this;
    }

    public Builder status(HubCollaborationV2025R0StatusField status) {
      this.status = new EnumWrapper<HubCollaborationV2025R0StatusField>(status);
      return this;
    }

    public Builder status(EnumWrapper<HubCollaborationV2025R0StatusField> status) {
      this.status = status;
      return this;
    }

    public Builder acceptanceRequirementsStatus(
        HubCollaborationV2025R0AcceptanceRequirementsStatusField acceptanceRequirementsStatus) {
      this.acceptanceRequirementsStatus = acceptanceRequirementsStatus;
      return this;
    }

    public HubCollaborationV2025R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<HubCollaborationV2025R0TypeField>(
                HubCollaborationV2025R0TypeField.HUB_COLLABORATION);
      }
      return new HubCollaborationV2025R0(this);
    }
  }
}
