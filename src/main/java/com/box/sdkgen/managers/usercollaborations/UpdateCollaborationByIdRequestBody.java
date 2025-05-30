package com.box.sdkgen.managers.usercollaborations;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class UpdateCollaborationByIdRequestBody extends SerializableObject {

  @JsonDeserialize(
      using =
          UpdateCollaborationByIdRequestBodyRoleField
              .UpdateCollaborationByIdRequestBodyRoleFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateCollaborationByIdRequestBodyRoleField
              .UpdateCollaborationByIdRequestBodyRoleFieldSerializer.class)
  protected final EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField> role;

  @JsonDeserialize(
      using =
          UpdateCollaborationByIdRequestBodyStatusField
              .UpdateCollaborationByIdRequestBodyStatusFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateCollaborationByIdRequestBodyStatusField
              .UpdateCollaborationByIdRequestBodyStatusFieldSerializer.class)
  protected EnumWrapper<UpdateCollaborationByIdRequestBodyStatusField> status;

  @JsonProperty("expires_at")
  protected String expiresAt;

  @JsonProperty("can_view_path")
  protected Boolean canViewPath;

  public UpdateCollaborationByIdRequestBody(
      @JsonProperty("role") EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField> role) {
    super();
    this.role = role;
  }

  public UpdateCollaborationByIdRequestBody(UpdateCollaborationByIdRequestBodyRoleField role) {
    super();
    this.role = new EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField>(role);
  }

  protected UpdateCollaborationByIdRequestBody(UpdateCollaborationByIdRequestBodyBuilder builder) {
    super();
    this.role = builder.role;
    this.status = builder.status;
    this.expiresAt = builder.expiresAt;
    this.canViewPath = builder.canViewPath;
  }

  public EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField> getRole() {
    return role;
  }

  public EnumWrapper<UpdateCollaborationByIdRequestBodyStatusField> getStatus() {
    return status;
  }

  public String getExpiresAt() {
    return expiresAt;
  }

  public Boolean getCanViewPath() {
    return canViewPath;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateCollaborationByIdRequestBody casted = (UpdateCollaborationByIdRequestBody) o;
    return Objects.equals(role, casted.role)
        && Objects.equals(status, casted.status)
        && Objects.equals(expiresAt, casted.expiresAt)
        && Objects.equals(canViewPath, casted.canViewPath);
  }

  @Override
  public int hashCode() {
    return Objects.hash(role, status, expiresAt, canViewPath);
  }

  @Override
  public String toString() {
    return "UpdateCollaborationByIdRequestBody{"
        + "role='"
        + role
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + ", "
        + "expiresAt='"
        + expiresAt
        + '\''
        + ", "
        + "canViewPath='"
        + canViewPath
        + '\''
        + "}";
  }

  public static class UpdateCollaborationByIdRequestBodyBuilder {

    protected final EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField> role;

    protected EnumWrapper<UpdateCollaborationByIdRequestBodyStatusField> status;

    protected String expiresAt;

    protected Boolean canViewPath;

    public UpdateCollaborationByIdRequestBodyBuilder(
        EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField> role) {
      this.role = role;
    }

    public UpdateCollaborationByIdRequestBodyBuilder(
        UpdateCollaborationByIdRequestBodyRoleField role) {
      this.role = new EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField>(role);
    }

    public UpdateCollaborationByIdRequestBodyBuilder status(
        UpdateCollaborationByIdRequestBodyStatusField status) {
      this.status = new EnumWrapper<UpdateCollaborationByIdRequestBodyStatusField>(status);
      return this;
    }

    public UpdateCollaborationByIdRequestBodyBuilder status(
        EnumWrapper<UpdateCollaborationByIdRequestBodyStatusField> status) {
      this.status = status;
      return this;
    }

    public UpdateCollaborationByIdRequestBodyBuilder expiresAt(String expiresAt) {
      this.expiresAt = expiresAt;
      return this;
    }

    public UpdateCollaborationByIdRequestBodyBuilder canViewPath(Boolean canViewPath) {
      this.canViewPath = canViewPath;
      return this;
    }

    public UpdateCollaborationByIdRequestBody build() {
      return new UpdateCollaborationByIdRequestBody(this);
    }
  }
}
