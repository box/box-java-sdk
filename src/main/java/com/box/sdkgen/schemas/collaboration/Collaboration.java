package com.box.sdkgen.schemas.collaboration;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.appitem.AppItem;
import com.box.sdkgen.schemas.collaborationaccessgrantee.CollaborationAccessGrantee;
import com.box.sdkgen.schemas.collaborationitem.CollaborationItem;
import com.box.sdkgen.schemas.file.File;
import com.box.sdkgen.schemas.folder.Folder;
import com.box.sdkgen.schemas.groupmini.GroupMini;
import com.box.sdkgen.schemas.usercollaborations.UserCollaborations;
import com.box.sdkgen.schemas.weblink.WebLink;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class Collaboration extends SerializableObject {

  protected final String id;

  @JsonDeserialize(using = CollaborationTypeField.CollaborationTypeFieldDeserializer.class)
  @JsonSerialize(using = CollaborationTypeField.CollaborationTypeFieldSerializer.class)
  protected EnumWrapper<CollaborationTypeField> type;

  @Nullable protected CollaborationItem item;

  @JsonProperty("app_item")
  @Nullable
  protected AppItem appItem;

  @JsonProperty("accessible_by")
  protected CollaborationAccessGrantee accessibleBy;

  @JsonProperty("invite_email")
  @Nullable
  protected String inviteEmail;

  @JsonDeserialize(using = CollaborationRoleField.CollaborationRoleFieldDeserializer.class)
  @JsonSerialize(using = CollaborationRoleField.CollaborationRoleFieldSerializer.class)
  protected EnumWrapper<CollaborationRoleField> role;

  @JsonProperty("expires_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected Date expiresAt;

  @JsonProperty("is_access_only")
  protected Boolean isAccessOnly;

  @JsonDeserialize(using = CollaborationStatusField.CollaborationStatusFieldDeserializer.class)
  @JsonSerialize(using = CollaborationStatusField.CollaborationStatusFieldSerializer.class)
  protected EnumWrapper<CollaborationStatusField> status;

  @JsonProperty("acknowledged_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date acknowledgedAt;

  @JsonProperty("created_by")
  protected UserCollaborations createdBy;

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date createdAt;

  @JsonProperty("modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date modifiedAt;

  @JsonProperty("acceptance_requirements_status")
  protected CollaborationAcceptanceRequirementsStatusField acceptanceRequirementsStatus;

  public Collaboration(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type = new EnumWrapper<CollaborationTypeField>(CollaborationTypeField.COLLABORATION);
  }

  protected Collaboration(Builder builder) {
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
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<CollaborationTypeField> getType() {
    return type;
  }

  public CollaborationItem getItem() {
    return item;
  }

  public AppItem getAppItem() {
    return appItem;
  }

  public CollaborationAccessGrantee getAccessibleBy() {
    return accessibleBy;
  }

  public String getInviteEmail() {
    return inviteEmail;
  }

  public EnumWrapper<CollaborationRoleField> getRole() {
    return role;
  }

  public Date getExpiresAt() {
    return expiresAt;
  }

  public Boolean getIsAccessOnly() {
    return isAccessOnly;
  }

  public EnumWrapper<CollaborationStatusField> getStatus() {
    return status;
  }

  public Date getAcknowledgedAt() {
    return acknowledgedAt;
  }

  public UserCollaborations getCreatedBy() {
    return createdBy;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getModifiedAt() {
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

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<CollaborationTypeField> type;

    protected CollaborationItem item;

    protected AppItem appItem;

    protected CollaborationAccessGrantee accessibleBy;

    protected String inviteEmail;

    protected EnumWrapper<CollaborationRoleField> role;

    protected Date expiresAt;

    protected Boolean isAccessOnly;

    protected EnumWrapper<CollaborationStatusField> status;

    protected Date acknowledgedAt;

    protected UserCollaborations createdBy;

    protected Date createdAt;

    protected Date modifiedAt;

    protected CollaborationAcceptanceRequirementsStatusField acceptanceRequirementsStatus;

    public Builder(String id) {
      super();
      this.id = id;
      this.type = new EnumWrapper<CollaborationTypeField>(CollaborationTypeField.COLLABORATION);
    }

    public Builder type(CollaborationTypeField type) {
      this.type = new EnumWrapper<CollaborationTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<CollaborationTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder item(File item) {
      this.item = new CollaborationItem(item);
      this.markNullableFieldAsSet("item");
      return this;
    }

    public Builder item(Folder item) {
      this.item = new CollaborationItem(item);
      this.markNullableFieldAsSet("item");
      return this;
    }

    public Builder item(WebLink item) {
      this.item = new CollaborationItem(item);
      this.markNullableFieldAsSet("item");
      return this;
    }

    public Builder item(CollaborationItem item) {
      this.item = item;
      this.markNullableFieldAsSet("item");
      return this;
    }

    public Builder appItem(AppItem appItem) {
      this.appItem = appItem;
      this.markNullableFieldAsSet("app_item");
      return this;
    }

    public Builder accessibleBy(UserCollaborations accessibleBy) {
      this.accessibleBy = new CollaborationAccessGrantee(accessibleBy);
      return this;
    }

    public Builder accessibleBy(GroupMini accessibleBy) {
      this.accessibleBy = new CollaborationAccessGrantee(accessibleBy);
      return this;
    }

    public Builder accessibleBy(CollaborationAccessGrantee accessibleBy) {
      this.accessibleBy = accessibleBy;
      return this;
    }

    public Builder inviteEmail(String inviteEmail) {
      this.inviteEmail = inviteEmail;
      this.markNullableFieldAsSet("invite_email");
      return this;
    }

    public Builder role(CollaborationRoleField role) {
      this.role = new EnumWrapper<CollaborationRoleField>(role);
      return this;
    }

    public Builder role(EnumWrapper<CollaborationRoleField> role) {
      this.role = role;
      return this;
    }

    public Builder expiresAt(Date expiresAt) {
      this.expiresAt = expiresAt;
      this.markNullableFieldAsSet("expires_at");
      return this;
    }

    public Builder isAccessOnly(Boolean isAccessOnly) {
      this.isAccessOnly = isAccessOnly;
      return this;
    }

    public Builder status(CollaborationStatusField status) {
      this.status = new EnumWrapper<CollaborationStatusField>(status);
      return this;
    }

    public Builder status(EnumWrapper<CollaborationStatusField> status) {
      this.status = status;
      return this;
    }

    public Builder acknowledgedAt(Date acknowledgedAt) {
      this.acknowledgedAt = acknowledgedAt;
      return this;
    }

    public Builder createdBy(UserCollaborations createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder createdAt(Date createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder modifiedAt(Date modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public Builder acceptanceRequirementsStatus(
        CollaborationAcceptanceRequirementsStatusField acceptanceRequirementsStatus) {
      this.acceptanceRequirementsStatus = acceptanceRequirementsStatus;
      return this;
    }

    public Collaboration build() {
      return new Collaboration(this);
    }
  }
}
