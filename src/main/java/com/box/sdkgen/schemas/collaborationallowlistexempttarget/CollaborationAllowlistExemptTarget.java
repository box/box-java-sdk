package com.box.sdkgen.schemas.collaborationallowlistexempttarget;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class CollaborationAllowlistExemptTarget extends SerializableObject {

  protected String id;

  @JsonDeserialize(
      using =
          CollaborationAllowlistExemptTargetTypeField
              .CollaborationAllowlistExemptTargetTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          CollaborationAllowlistExemptTargetTypeField
              .CollaborationAllowlistExemptTargetTypeFieldSerializer.class)
  protected EnumWrapper<CollaborationAllowlistExemptTargetTypeField> type;

  protected CollaborationAllowlistExemptTargetEnterpriseField enterprise;

  protected UserMini user;

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("modified_at")
  protected String modifiedAt;

  public CollaborationAllowlistExemptTarget() {
    super();
  }

  protected CollaborationAllowlistExemptTarget(CollaborationAllowlistExemptTargetBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.enterprise = builder.enterprise;
    this.user = builder.user;
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<CollaborationAllowlistExemptTargetTypeField> getType() {
    return type;
  }

  public CollaborationAllowlistExemptTargetEnterpriseField getEnterprise() {
    return enterprise;
  }

  public UserMini getUser() {
    return user;
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
    CollaborationAllowlistExemptTarget casted = (CollaborationAllowlistExemptTarget) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(enterprise, casted.enterprise)
        && Objects.equals(user, casted.user)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(modifiedAt, casted.modifiedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, enterprise, user, createdAt, modifiedAt);
  }

  @Override
  public String toString() {
    return "CollaborationAllowlistExemptTarget{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "enterprise='"
        + enterprise
        + '\''
        + ", "
        + "user='"
        + user
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

  public static class CollaborationAllowlistExemptTargetBuilder {

    protected String id;

    protected EnumWrapper<CollaborationAllowlistExemptTargetTypeField> type;

    protected CollaborationAllowlistExemptTargetEnterpriseField enterprise;

    protected UserMini user;

    protected String createdAt;

    protected String modifiedAt;

    public CollaborationAllowlistExemptTargetBuilder id(String id) {
      this.id = id;
      return this;
    }

    public CollaborationAllowlistExemptTargetBuilder type(
        CollaborationAllowlistExemptTargetTypeField type) {
      this.type = new EnumWrapper<CollaborationAllowlistExemptTargetTypeField>(type);
      return this;
    }

    public CollaborationAllowlistExemptTargetBuilder type(
        EnumWrapper<CollaborationAllowlistExemptTargetTypeField> type) {
      this.type = type;
      return this;
    }

    public CollaborationAllowlistExemptTargetBuilder enterprise(
        CollaborationAllowlistExemptTargetEnterpriseField enterprise) {
      this.enterprise = enterprise;
      return this;
    }

    public CollaborationAllowlistExemptTargetBuilder user(UserMini user) {
      this.user = user;
      return this;
    }

    public CollaborationAllowlistExemptTargetBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public CollaborationAllowlistExemptTargetBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public CollaborationAllowlistExemptTarget build() {
      return new CollaborationAllowlistExemptTarget(this);
    }
  }
}
