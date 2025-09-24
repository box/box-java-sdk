package com.box.sdkgen.managers.usercollaborations;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
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
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime expiresAt;

  @JsonProperty("can_view_path")
  protected Boolean canViewPath;

  public UpdateCollaborationByIdRequestBody(UpdateCollaborationByIdRequestBodyRoleField role) {
    super();
    this.role = new EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField>(role);
  }

  public UpdateCollaborationByIdRequestBody(
      @JsonProperty("role") EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField> role) {
    super();
    this.role = role;
  }

  protected UpdateCollaborationByIdRequestBody(Builder builder) {
    super();
    this.role = builder.role;
    this.status = builder.status;
    this.expiresAt = builder.expiresAt;
    this.canViewPath = builder.canViewPath;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField> getRole() {
    return role;
  }

  public EnumWrapper<UpdateCollaborationByIdRequestBodyStatusField> getStatus() {
    return status;
  }

  public OffsetDateTime getExpiresAt() {
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

  public static class Builder extends NullableFieldTracker {

    protected final EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField> role;

    protected EnumWrapper<UpdateCollaborationByIdRequestBodyStatusField> status;

    protected OffsetDateTime expiresAt;

    protected Boolean canViewPath;

    public Builder(UpdateCollaborationByIdRequestBodyRoleField role) {
      super();
      this.role = new EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField>(role);
    }

    public Builder(EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField> role) {
      super();
      this.role = role;
    }

    public Builder status(UpdateCollaborationByIdRequestBodyStatusField status) {
      this.status = new EnumWrapper<UpdateCollaborationByIdRequestBodyStatusField>(status);
      return this;
    }

    public Builder status(EnumWrapper<UpdateCollaborationByIdRequestBodyStatusField> status) {
      this.status = status;
      return this;
    }

    public Builder expiresAt(OffsetDateTime expiresAt) {
      this.expiresAt = expiresAt;
      return this;
    }

    public Builder canViewPath(Boolean canViewPath) {
      this.canViewPath = canViewPath;
      return this;
    }

    public UpdateCollaborationByIdRequestBody build() {
      return new UpdateCollaborationByIdRequestBody(this);
    }
  }
}
