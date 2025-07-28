package com.box.sdkgen.schemas.v2025r0.hubcollaborationcreaterequestv2025r0;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class HubCollaborationCreateRequestV2025R0 extends SerializableObject {

  protected final HubCollaborationCreateRequestV2025R0HubField hub;

  @JsonProperty("accessible_by")
  protected final HubCollaborationCreateRequestV2025R0AccessibleByField accessibleBy;

  protected final String role;

  public HubCollaborationCreateRequestV2025R0(
      @JsonProperty("hub") HubCollaborationCreateRequestV2025R0HubField hub,
      @JsonProperty("accessible_by")
          HubCollaborationCreateRequestV2025R0AccessibleByField accessibleBy,
      @JsonProperty("role") String role) {
    super();
    this.hub = hub;
    this.accessibleBy = accessibleBy;
    this.role = role;
  }

  public HubCollaborationCreateRequestV2025R0HubField getHub() {
    return hub;
  }

  public HubCollaborationCreateRequestV2025R0AccessibleByField getAccessibleBy() {
    return accessibleBy;
  }

  public String getRole() {
    return role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HubCollaborationCreateRequestV2025R0 casted = (HubCollaborationCreateRequestV2025R0) o;
    return Objects.equals(hub, casted.hub)
        && Objects.equals(accessibleBy, casted.accessibleBy)
        && Objects.equals(role, casted.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hub, accessibleBy, role);
  }

  @Override
  public String toString() {
    return "HubCollaborationCreateRequestV2025R0{"
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
        + "}";
  }
}
