package com.box.sdkgen.managers.usercollaborations;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class CreateCollaborationRequestBody extends SerializableObject {

  protected final CreateCollaborationRequestBodyItemField item;

  @JsonProperty("accessible_by")
  protected final CreateCollaborationRequestBodyAccessibleByField accessibleBy;

  @JsonDeserialize(
      using =
          CreateCollaborationRequestBodyRoleField
              .CreateCollaborationRequestBodyRoleFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateCollaborationRequestBodyRoleField.CreateCollaborationRequestBodyRoleFieldSerializer
              .class)
  protected final EnumWrapper<CreateCollaborationRequestBodyRoleField> role;

  @JsonProperty("is_access_only")
  protected Boolean isAccessOnly;

  @JsonProperty("can_view_path")
  protected Boolean canViewPath;

  @JsonProperty("expires_at")
  protected String expiresAt;

  public CreateCollaborationRequestBody(
      @JsonProperty("item") CreateCollaborationRequestBodyItemField item,
      @JsonProperty("accessible_by") CreateCollaborationRequestBodyAccessibleByField accessibleBy,
      @JsonProperty("role") EnumWrapper<CreateCollaborationRequestBodyRoleField> role) {
    super();
    this.item = item;
    this.accessibleBy = accessibleBy;
    this.role = role;
  }

  public CreateCollaborationRequestBody(
      CreateCollaborationRequestBodyItemField item,
      CreateCollaborationRequestBodyAccessibleByField accessibleBy,
      CreateCollaborationRequestBodyRoleField role) {
    super();
    this.item = item;
    this.accessibleBy = accessibleBy;
    this.role = new EnumWrapper<CreateCollaborationRequestBodyRoleField>(role);
  }

  protected CreateCollaborationRequestBody(CreateCollaborationRequestBodyBuilder builder) {
    super();
    this.item = builder.item;
    this.accessibleBy = builder.accessibleBy;
    this.role = builder.role;
    this.isAccessOnly = builder.isAccessOnly;
    this.canViewPath = builder.canViewPath;
    this.expiresAt = builder.expiresAt;
  }

  public CreateCollaborationRequestBodyItemField getItem() {
    return item;
  }

  public CreateCollaborationRequestBodyAccessibleByField getAccessibleBy() {
    return accessibleBy;
  }

  public EnumWrapper<CreateCollaborationRequestBodyRoleField> getRole() {
    return role;
  }

  public Boolean getIsAccessOnly() {
    return isAccessOnly;
  }

  public Boolean getCanViewPath() {
    return canViewPath;
  }

  public String getExpiresAt() {
    return expiresAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateCollaborationRequestBody casted = (CreateCollaborationRequestBody) o;
    return Objects.equals(item, casted.item)
        && Objects.equals(accessibleBy, casted.accessibleBy)
        && Objects.equals(role, casted.role)
        && Objects.equals(isAccessOnly, casted.isAccessOnly)
        && Objects.equals(canViewPath, casted.canViewPath)
        && Objects.equals(expiresAt, casted.expiresAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(item, accessibleBy, role, isAccessOnly, canViewPath, expiresAt);
  }

  @Override
  public String toString() {
    return "CreateCollaborationRequestBody{"
        + "item='"
        + item
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
        + "isAccessOnly='"
        + isAccessOnly
        + '\''
        + ", "
        + "canViewPath='"
        + canViewPath
        + '\''
        + ", "
        + "expiresAt='"
        + expiresAt
        + '\''
        + "}";
  }

  public static class CreateCollaborationRequestBodyBuilder {

    protected final CreateCollaborationRequestBodyItemField item;

    protected final CreateCollaborationRequestBodyAccessibleByField accessibleBy;

    protected final EnumWrapper<CreateCollaborationRequestBodyRoleField> role;

    protected Boolean isAccessOnly;

    protected Boolean canViewPath;

    protected String expiresAt;

    public CreateCollaborationRequestBodyBuilder(
        CreateCollaborationRequestBodyItemField item,
        CreateCollaborationRequestBodyAccessibleByField accessibleBy,
        EnumWrapper<CreateCollaborationRequestBodyRoleField> role) {
      this.item = item;
      this.accessibleBy = accessibleBy;
      this.role = role;
    }

    public CreateCollaborationRequestBodyBuilder(
        CreateCollaborationRequestBodyItemField item,
        CreateCollaborationRequestBodyAccessibleByField accessibleBy,
        CreateCollaborationRequestBodyRoleField role) {
      this.item = item;
      this.accessibleBy = accessibleBy;
      this.role = new EnumWrapper<CreateCollaborationRequestBodyRoleField>(role);
    }

    public CreateCollaborationRequestBodyBuilder isAccessOnly(Boolean isAccessOnly) {
      this.isAccessOnly = isAccessOnly;
      return this;
    }

    public CreateCollaborationRequestBodyBuilder canViewPath(Boolean canViewPath) {
      this.canViewPath = canViewPath;
      return this;
    }

    public CreateCollaborationRequestBodyBuilder expiresAt(String expiresAt) {
      this.expiresAt = expiresAt;
      return this;
    }

    public CreateCollaborationRequestBody build() {
      return new CreateCollaborationRequestBody(this);
    }
  }
}
