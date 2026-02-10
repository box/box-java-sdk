package com.box.sdkgen.schemas.v2025r0.hubv2025r0;

import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.v2025r0.hubbasev2025r0.HubBaseV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubbasev2025r0.HubBaseV2025R0TypeField;
import com.box.sdkgen.schemas.v2025r0.userminiv2025r0.UserMiniV2025R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * A standard representation of a Box Hub, as returned from any Box Hubs API endpoints by default.
 */
@JsonFilter("nullablePropertyFilter")
public class HubV2025R0 extends HubBaseV2025R0 {

  /** The title given to the Box Hub. */
  protected String title;

  /** The description of the Box Hub. First 200 characters are returned. */
  protected String description;

  /**
   * The date and time when the folder was created. This value may be `null` for some folders such
   * as the root folder or the trash folder.
   */
  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  /** The date and time when the Box Hub was last updated. */
  @JsonProperty("updated_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime updatedAt;

  @JsonProperty("created_by")
  protected UserMiniV2025R0 createdBy;

  @JsonProperty("updated_by")
  protected UserMiniV2025R0 updatedBy;

  /** The number of views for the Box Hub. */
  @JsonProperty("view_count")
  protected Integer viewCount;

  /** Indicates if AI features are enabled for the Box Hub. */
  @JsonProperty("is_ai_enabled")
  protected Boolean isAiEnabled;

  /** Indicates if collaboration is restricted to the enterprise. */
  @JsonProperty("is_collaboration_restricted_to_enterprise")
  protected Boolean isCollaborationRestrictedToEnterprise;

  /** Indicates if non-owners can invite others to the Box Hub. */
  @JsonProperty("can_non_owners_invite")
  protected Boolean canNonOwnersInvite;

  /** Indicates if a shared link can be created for the Box Hub. */
  @JsonProperty("can_shared_link_be_created")
  protected Boolean canSharedLinkBeCreated;

  public HubV2025R0(@JsonProperty("id") String id) {
    super(id);
  }

  protected HubV2025R0(Builder builder) {
    super(builder);
    this.title = builder.title;
    this.description = builder.description;
    this.createdAt = builder.createdAt;
    this.updatedAt = builder.updatedAt;
    this.createdBy = builder.createdBy;
    this.updatedBy = builder.updatedBy;
    this.viewCount = builder.viewCount;
    this.isAiEnabled = builder.isAiEnabled;
    this.isCollaborationRestrictedToEnterprise = builder.isCollaborationRestrictedToEnterprise;
    this.canNonOwnersInvite = builder.canNonOwnersInvite;
    this.canSharedLinkBeCreated = builder.canSharedLinkBeCreated;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public UserMiniV2025R0 getCreatedBy() {
    return createdBy;
  }

  public UserMiniV2025R0 getUpdatedBy() {
    return updatedBy;
  }

  public Integer getViewCount() {
    return viewCount;
  }

  public Boolean getIsAiEnabled() {
    return isAiEnabled;
  }

  public Boolean getIsCollaborationRestrictedToEnterprise() {
    return isCollaborationRestrictedToEnterprise;
  }

  public Boolean getCanNonOwnersInvite() {
    return canNonOwnersInvite;
  }

  public Boolean getCanSharedLinkBeCreated() {
    return canSharedLinkBeCreated;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HubV2025R0 casted = (HubV2025R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(title, casted.title)
        && Objects.equals(description, casted.description)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(updatedAt, casted.updatedAt)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(updatedBy, casted.updatedBy)
        && Objects.equals(viewCount, casted.viewCount)
        && Objects.equals(isAiEnabled, casted.isAiEnabled)
        && Objects.equals(
            isCollaborationRestrictedToEnterprise, casted.isCollaborationRestrictedToEnterprise)
        && Objects.equals(canNonOwnersInvite, casted.canNonOwnersInvite)
        && Objects.equals(canSharedLinkBeCreated, casted.canSharedLinkBeCreated);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        type,
        title,
        description,
        createdAt,
        updatedAt,
        createdBy,
        updatedBy,
        viewCount,
        isAiEnabled,
        isCollaborationRestrictedToEnterprise,
        canNonOwnersInvite,
        canSharedLinkBeCreated);
  }

  @Override
  public String toString() {
    return "HubV2025R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "title='"
        + title
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "updatedAt='"
        + updatedAt
        + '\''
        + ", "
        + "createdBy='"
        + createdBy
        + '\''
        + ", "
        + "updatedBy='"
        + updatedBy
        + '\''
        + ", "
        + "viewCount='"
        + viewCount
        + '\''
        + ", "
        + "isAiEnabled='"
        + isAiEnabled
        + '\''
        + ", "
        + "isCollaborationRestrictedToEnterprise='"
        + isCollaborationRestrictedToEnterprise
        + '\''
        + ", "
        + "canNonOwnersInvite='"
        + canNonOwnersInvite
        + '\''
        + ", "
        + "canSharedLinkBeCreated='"
        + canSharedLinkBeCreated
        + '\''
        + "}";
  }

  public static class Builder extends HubBaseV2025R0.Builder {

    protected String title;

    protected String description;

    protected OffsetDateTime createdAt;

    protected OffsetDateTime updatedAt;

    protected UserMiniV2025R0 createdBy;

    protected UserMiniV2025R0 updatedBy;

    protected Integer viewCount;

    protected Boolean isAiEnabled;

    protected Boolean isCollaborationRestrictedToEnterprise;

    protected Boolean canNonOwnersInvite;

    protected Boolean canSharedLinkBeCreated;

    public Builder(String id) {
      super(id);
    }

    public Builder title(String title) {
      this.title = title;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder updatedAt(OffsetDateTime updatedAt) {
      this.updatedAt = updatedAt;
      return this;
    }

    public Builder createdBy(UserMiniV2025R0 createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder updatedBy(UserMiniV2025R0 updatedBy) {
      this.updatedBy = updatedBy;
      return this;
    }

    public Builder viewCount(Integer viewCount) {
      this.viewCount = viewCount;
      return this;
    }

    public Builder isAiEnabled(Boolean isAiEnabled) {
      this.isAiEnabled = isAiEnabled;
      return this;
    }

    public Builder isCollaborationRestrictedToEnterprise(
        Boolean isCollaborationRestrictedToEnterprise) {
      this.isCollaborationRestrictedToEnterprise = isCollaborationRestrictedToEnterprise;
      return this;
    }

    public Builder canNonOwnersInvite(Boolean canNonOwnersInvite) {
      this.canNonOwnersInvite = canNonOwnersInvite;
      return this;
    }

    public Builder canSharedLinkBeCreated(Boolean canSharedLinkBeCreated) {
      this.canSharedLinkBeCreated = canSharedLinkBeCreated;
      return this;
    }

    @Override
    public Builder type(HubBaseV2025R0TypeField type) {
      this.type = new EnumWrapper<HubBaseV2025R0TypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<HubBaseV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public HubV2025R0 build() {
      if (this.type == null) {
        this.type = new EnumWrapper<HubBaseV2025R0TypeField>(HubBaseV2025R0TypeField.HUBS);
      }
      return new HubV2025R0(this);
    }
  }
}
