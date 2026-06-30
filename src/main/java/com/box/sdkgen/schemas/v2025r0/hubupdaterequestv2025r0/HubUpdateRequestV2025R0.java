package com.box.sdkgen.schemas.v2025r0.hubupdaterequestv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** Request schema for updating an existing Box Hub. */
@JsonFilter("nullablePropertyFilter")
public class HubUpdateRequestV2025R0 extends SerializableObject {

  /** Title of the Box Hub. It cannot be empty and should be less than 50 characters. */
  protected String title;

  /** Description of the Box Hub. */
  protected String description;

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

  /** Indicates if a public shared link can be created for the Box Hub. */
  @JsonProperty("can_public_shared_link_be_created")
  protected Boolean canPublicSharedLinkBeCreated;

  /**
   * Specifies who is allowed to copy the Box Hub.
   *
   * <p>* `all` - Any user with access to the Hub can copy it. * `company` - Only users within the
   * same enterprise as the Hub can copy it. * `none` - No one can copy the Hub.
   */
  @JsonDeserialize(
      using =
          HubUpdateRequestV2025R0CopyHubAccessField
              .HubUpdateRequestV2025R0CopyHubAccessFieldDeserializer.class)
  @JsonSerialize(
      using =
          HubUpdateRequestV2025R0CopyHubAccessField
              .HubUpdateRequestV2025R0CopyHubAccessFieldSerializer.class)
  @JsonProperty("copy_hub_access")
  protected EnumWrapper<HubUpdateRequestV2025R0CopyHubAccessField> copyHubAccess;

  public HubUpdateRequestV2025R0() {
    super();
  }

  protected HubUpdateRequestV2025R0(Builder builder) {
    super();
    this.title = builder.title;
    this.description = builder.description;
    this.isAiEnabled = builder.isAiEnabled;
    this.isCollaborationRestrictedToEnterprise = builder.isCollaborationRestrictedToEnterprise;
    this.canNonOwnersInvite = builder.canNonOwnersInvite;
    this.canSharedLinkBeCreated = builder.canSharedLinkBeCreated;
    this.canPublicSharedLinkBeCreated = builder.canPublicSharedLinkBeCreated;
    this.copyHubAccess = builder.copyHubAccess;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
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

  public Boolean getCanPublicSharedLinkBeCreated() {
    return canPublicSharedLinkBeCreated;
  }

  public EnumWrapper<HubUpdateRequestV2025R0CopyHubAccessField> getCopyHubAccess() {
    return copyHubAccess;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HubUpdateRequestV2025R0 casted = (HubUpdateRequestV2025R0) o;
    return Objects.equals(title, casted.title)
        && Objects.equals(description, casted.description)
        && Objects.equals(isAiEnabled, casted.isAiEnabled)
        && Objects.equals(
            isCollaborationRestrictedToEnterprise, casted.isCollaborationRestrictedToEnterprise)
        && Objects.equals(canNonOwnersInvite, casted.canNonOwnersInvite)
        && Objects.equals(canSharedLinkBeCreated, casted.canSharedLinkBeCreated)
        && Objects.equals(canPublicSharedLinkBeCreated, casted.canPublicSharedLinkBeCreated)
        && Objects.equals(copyHubAccess, casted.copyHubAccess);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        title,
        description,
        isAiEnabled,
        isCollaborationRestrictedToEnterprise,
        canNonOwnersInvite,
        canSharedLinkBeCreated,
        canPublicSharedLinkBeCreated,
        copyHubAccess);
  }

  @Override
  public String toString() {
    return "HubUpdateRequestV2025R0{"
        + "title='"
        + title
        + '\''
        + ", "
        + "description='"
        + description
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
        + ", "
        + "canPublicSharedLinkBeCreated='"
        + canPublicSharedLinkBeCreated
        + '\''
        + ", "
        + "copyHubAccess='"
        + copyHubAccess
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String title;

    protected String description;

    protected Boolean isAiEnabled;

    protected Boolean isCollaborationRestrictedToEnterprise;

    protected Boolean canNonOwnersInvite;

    protected Boolean canSharedLinkBeCreated;

    protected Boolean canPublicSharedLinkBeCreated;

    protected EnumWrapper<HubUpdateRequestV2025R0CopyHubAccessField> copyHubAccess;

    public Builder title(String title) {
      this.title = title;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
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

    public Builder canPublicSharedLinkBeCreated(Boolean canPublicSharedLinkBeCreated) {
      this.canPublicSharedLinkBeCreated = canPublicSharedLinkBeCreated;
      return this;
    }

    public Builder copyHubAccess(HubUpdateRequestV2025R0CopyHubAccessField copyHubAccess) {
      this.copyHubAccess =
          new EnumWrapper<HubUpdateRequestV2025R0CopyHubAccessField>(copyHubAccess);
      return this;
    }

    public Builder copyHubAccess(
        EnumWrapper<HubUpdateRequestV2025R0CopyHubAccessField> copyHubAccess) {
      this.copyHubAccess = copyHubAccess;
      return this;
    }

    public HubUpdateRequestV2025R0 build() {
      return new HubUpdateRequestV2025R0(this);
    }
  }
}
