package com.box.sdkgen.schemas.v2025r0.hubcollaborationupdaterequestv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class HubCollaborationUpdateRequestV2025R0 extends SerializableObject {

  protected String role;

  public HubCollaborationUpdateRequestV2025R0() {
    super();
  }

  protected HubCollaborationUpdateRequestV2025R0(Builder builder) {
    super();
    this.role = builder.role;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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
    HubCollaborationUpdateRequestV2025R0 casted = (HubCollaborationUpdateRequestV2025R0) o;
    return Objects.equals(role, casted.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(role);
  }

  @Override
  public String toString() {
    return "HubCollaborationUpdateRequestV2025R0{" + "role='" + role + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String role;

    public Builder role(String role) {
      this.role = role;
      return this;
    }

    public HubCollaborationUpdateRequestV2025R0 build() {
      return new HubCollaborationUpdateRequestV2025R0(this);
    }
  }
}
