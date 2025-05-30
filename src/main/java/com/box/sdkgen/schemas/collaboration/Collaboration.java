package com.box.sdkgen.schemas.collaboration;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.appitem.AppItem;
import com.box.sdkgen.schemas.fileorfolderorweblink.FileOrFolderOrWebLink;
import com.box.sdkgen.schemas.groupminiorusercollaborations.GroupMiniOrUserCollaborations;
import com.box.sdkgen.schemas.usercollaborations.UserCollaborations;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class Collaboration extends SerializableObject {

  protected final String id;

  @JsonDeserialize(using = CollaborationTypeField.CollaborationTypeFieldDeserializer.class)
  @JsonSerialize(using = CollaborationTypeField.CollaborationTypeFieldSerializer.class)
  protected EnumWrapper<CollaborationTypeField> type;

  protected FileOrFolderOrWebLink item;

  @JsonProperty("app_item")
  protected AppItem appItem;

  @JsonProperty("accessible_by")
  protected GroupMiniOrUserCollaborations accessibleBy;

  @JsonProperty("invite_email")
  protected String inviteEmail;

  @JsonDeserialize(using = CollaborationRoleField.CollaborationRoleFieldDeserializer.class)
  @JsonSerialize(using = CollaborationRoleField.CollaborationRoleFieldSerializer.class)
  protected EnumWrapper<CollaborationRoleField> role;

  @JsonProperty("expires_at")
  protected String expiresAt;

  @JsonProperty("is_access_only")
  protected Boolean isAccessOnly;

  @JsonDeserialize(using = CollaborationStatusField.CollaborationStatusFieldDeserializer.class)
  @JsonSerialize(using = CollaborationStatusField.CollaborationStatusFieldSerializer.class)
  protected EnumWrapper<CollaborationStatusField> status;

  @JsonProperty("acknowledged_at")
  protected String acknowledgedAt;

  @JsonProperty("created_by")
  protected UserCollaborations createdBy;

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("modified_at")
  protected String modifiedAt;

  @JsonProperty("acceptance_requirements_status")
  protected CollaborationAcceptanceRequirementsStatusField acceptanceRequirementsStatus;

  public Collaboration(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type = new EnumWrapper<CollaborationTypeField>(CollaborationTypeField.COLLABORATION);
  }

  protected Collaboration(CollaborationBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.item = builder.item;
    this.appItem = builder.appItem;
    this.accessibleBy = builder.accessibleBy;
    this.inviteEmail = builder.inviteEmail;
    this.role = builder.role;
    this.expiresAt = builder.expiresAt;
    this.isAccessOnly = builder.isAccessOnly;
    this.status = builder.status;
    this.acknowledgedAt = builder.acknowledgedAt;
    this.createdBy = builder.createdBy;
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
    this.acceptanceRequirementsStatus = builder.acceptanceRequirementsStatus;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<CollaborationTypeField> getType() {
    return type;
  }

  public FileOrFolderOrWebLink getItem() {
    return item;
  }

  public AppItem getAppItem() {
    return appItem;
  }

  public GroupMiniOrUserCollaborations getAccessibleBy() {
    return accessibleBy;
  }

  public String getInviteEmail() {
    return inviteEmail;
  }

  public EnumWrapper<CollaborationRoleField> getRole() {
    return role;
  }

  public String getExpiresAt() {
    return expiresAt;
  }

  public Boolean getIsAccessOnly() {
    return isAccessOnly;
  }

  public EnumWrapper<CollaborationStatusField> getStatus() {
    return status;
  }

  public String getAcknowledgedAt() {
    return acknowledgedAt;
  }

  public UserCollaborations getCreatedBy() {
    return createdBy;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getModifiedAt() {
    return modifiedAt;
  }

  public CollaborationAcceptanceRequirementsStatusField getAcceptanceRequirementsStatus() {
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
    Collaboration casted = (Collaboration) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(item, casted.item)
        && Objects.equals(appItem, casted.appItem)
        && Objects.equals(accessibleBy, casted.accessibleBy)
        && Objects.equals(inviteEmail, casted.inviteEmail)
        && Objects.equals(role, casted.role)
        && Objects.equals(expiresAt, casted.expiresAt)
        && Objects.equals(isAccessOnly, casted.isAccessOnly)
        && Objects.equals(status, casted.status)
        && Objects.equals(acknowledgedAt, casted.acknowledgedAt)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(modifiedAt, casted.modifiedAt)
        && Objects.equals(acceptanceRequirementsStatus, casted.acceptanceRequirementsStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        type,
        item,
        appItem,
        accessibleBy,
        inviteEmail,
        role,
        expiresAt,
        isAccessOnly,
        status,
        acknowledgedAt,
        createdBy,
        createdAt,
        modifiedAt,
        acceptanceRequirementsStatus);
  }

  @Override
  public String toString() {
    return "Collaboration{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "item='"
        + item
        + '\''
        + ", "
        + "appItem='"
        + appItem
        + '\''
        + ", "
        + "accessibleBy='"
        + accessibleBy
        + '\''
        + ", "
        + "inviteEmail='"
        + inviteEmail
        + '\''
        + ", "
        + "role='"
        + role
        + '\''
        + ", "
        + "expiresAt='"
        + expiresAt
        + '\''
        + ", "
        + "isAccessOnly='"
        + isAccessOnly
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + ", "
        + "acknowledgedAt='"
        + acknowledgedAt
        + '\''
        + ", "
        + "createdBy='"
        + createdBy
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
        + "acceptanceRequirementsStatus='"
        + acceptanceRequirementsStatus
        + '\''
        + "}";
  }

  public static class CollaborationBuilder {

    protected final String id;

    protected EnumWrapper<CollaborationTypeField> type;

    protected FileOrFolderOrWebLink item;

    protected AppItem appItem;

    protected GroupMiniOrUserCollaborations accessibleBy;

    protected String inviteEmail;

    protected EnumWrapper<CollaborationRoleField> role;

    protected String expiresAt;

    protected Boolean isAccessOnly;

    protected EnumWrapper<CollaborationStatusField> status;

    protected String acknowledgedAt;

    protected UserCollaborations createdBy;

    protected String createdAt;

    protected String modifiedAt;

    protected CollaborationAcceptanceRequirementsStatusField acceptanceRequirementsStatus;

    public CollaborationBuilder(String id) {
      this.id = id;
      this.type = new EnumWrapper<CollaborationTypeField>(CollaborationTypeField.COLLABORATION);
    }

    public CollaborationBuilder type(CollaborationTypeField type) {
      this.type = new EnumWrapper<CollaborationTypeField>(type);
      return this;
    }

    public CollaborationBuilder type(EnumWrapper<CollaborationTypeField> type) {
      this.type = type;
      return this;
    }

    public CollaborationBuilder item(FileOrFolderOrWebLink item) {
      this.item = item;
      return this;
    }

    public CollaborationBuilder appItem(AppItem appItem) {
      this.appItem = appItem;
      return this;
    }

    public CollaborationBuilder accessibleBy(GroupMiniOrUserCollaborations accessibleBy) {
      this.accessibleBy = accessibleBy;
      return this;
    }

    public CollaborationBuilder inviteEmail(String inviteEmail) {
      this.inviteEmail = inviteEmail;
      return this;
    }

    public CollaborationBuilder role(CollaborationRoleField role) {
      this.role = new EnumWrapper<CollaborationRoleField>(role);
      return this;
    }

    public CollaborationBuilder role(EnumWrapper<CollaborationRoleField> role) {
      this.role = role;
      return this;
    }

    public CollaborationBuilder expiresAt(String expiresAt) {
      this.expiresAt = expiresAt;
      return this;
    }

    public CollaborationBuilder isAccessOnly(Boolean isAccessOnly) {
      this.isAccessOnly = isAccessOnly;
      return this;
    }

    public CollaborationBuilder status(CollaborationStatusField status) {
      this.status = new EnumWrapper<CollaborationStatusField>(status);
      return this;
    }

    public CollaborationBuilder status(EnumWrapper<CollaborationStatusField> status) {
      this.status = status;
      return this;
    }

    public CollaborationBuilder acknowledgedAt(String acknowledgedAt) {
      this.acknowledgedAt = acknowledgedAt;
      return this;
    }

    public CollaborationBuilder createdBy(UserCollaborations createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public CollaborationBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public CollaborationBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public CollaborationBuilder acceptanceRequirementsStatus(
        CollaborationAcceptanceRequirementsStatusField acceptanceRequirementsStatus) {
      this.acceptanceRequirementsStatus = acceptanceRequirementsStatus;
      return this;
    }

    public Collaboration build() {
      return new Collaboration(this);
    }
  }
}
