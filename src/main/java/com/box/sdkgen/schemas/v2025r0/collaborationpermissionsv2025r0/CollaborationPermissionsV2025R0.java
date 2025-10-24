package com.box.sdkgen.schemas.v2025r0.collaborationpermissionsv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** The collaboration permissions. */
@JsonFilter("nullablePropertyFilter")
public class CollaborationPermissionsV2025R0 extends SerializableObject {

  /** The co-owner role is enabled for collaboration. */
  @JsonProperty("is_co_owner_role_enabled")
  protected Boolean isCoOwnerRoleEnabled;

  /** The editor role is enabled for collaboration. */
  @JsonProperty("is_editor_role_enabled")
  protected Boolean isEditorRoleEnabled;

  /** The previewer role is enabled for collaboration. */
  @JsonProperty("is_previewer_role_enabled")
  protected Boolean isPreviewerRoleEnabled;

  /** The previewer uploader role is enabled for collaboration. */
  @JsonProperty("is_previewer_uploader_role_enabled")
  protected Boolean isPreviewerUploaderRoleEnabled;

  /** The uploader role is enabled for collaboration. */
  @JsonProperty("is_uploader_role_enabled")
  protected Boolean isUploaderRoleEnabled;

  /** The viewer role is enabled for collaboration. */
  @JsonProperty("is_viewer_role_enabled")
  protected Boolean isViewerRoleEnabled;

  /** The viewer uploader role is enabled for collaboration. */
  @JsonProperty("is_viewer_uploader_role_enabled")
  protected Boolean isViewerUploaderRoleEnabled;

  public CollaborationPermissionsV2025R0() {
    super();
  }

  protected CollaborationPermissionsV2025R0(Builder builder) {
    super();
    this.isCoOwnerRoleEnabled = builder.isCoOwnerRoleEnabled;
    this.isEditorRoleEnabled = builder.isEditorRoleEnabled;
    this.isPreviewerRoleEnabled = builder.isPreviewerRoleEnabled;
    this.isPreviewerUploaderRoleEnabled = builder.isPreviewerUploaderRoleEnabled;
    this.isUploaderRoleEnabled = builder.isUploaderRoleEnabled;
    this.isViewerRoleEnabled = builder.isViewerRoleEnabled;
    this.isViewerUploaderRoleEnabled = builder.isViewerUploaderRoleEnabled;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Boolean getIsCoOwnerRoleEnabled() {
    return isCoOwnerRoleEnabled;
  }

  public Boolean getIsEditorRoleEnabled() {
    return isEditorRoleEnabled;
  }

  public Boolean getIsPreviewerRoleEnabled() {
    return isPreviewerRoleEnabled;
  }

  public Boolean getIsPreviewerUploaderRoleEnabled() {
    return isPreviewerUploaderRoleEnabled;
  }

  public Boolean getIsUploaderRoleEnabled() {
    return isUploaderRoleEnabled;
  }

  public Boolean getIsViewerRoleEnabled() {
    return isViewerRoleEnabled;
  }

  public Boolean getIsViewerUploaderRoleEnabled() {
    return isViewerUploaderRoleEnabled;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CollaborationPermissionsV2025R0 casted = (CollaborationPermissionsV2025R0) o;
    return Objects.equals(isCoOwnerRoleEnabled, casted.isCoOwnerRoleEnabled)
        && Objects.equals(isEditorRoleEnabled, casted.isEditorRoleEnabled)
        && Objects.equals(isPreviewerRoleEnabled, casted.isPreviewerRoleEnabled)
        && Objects.equals(isPreviewerUploaderRoleEnabled, casted.isPreviewerUploaderRoleEnabled)
        && Objects.equals(isUploaderRoleEnabled, casted.isUploaderRoleEnabled)
        && Objects.equals(isViewerRoleEnabled, casted.isViewerRoleEnabled)
        && Objects.equals(isViewerUploaderRoleEnabled, casted.isViewerUploaderRoleEnabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        isCoOwnerRoleEnabled,
        isEditorRoleEnabled,
        isPreviewerRoleEnabled,
        isPreviewerUploaderRoleEnabled,
        isUploaderRoleEnabled,
        isViewerRoleEnabled,
        isViewerUploaderRoleEnabled);
  }

  @Override
  public String toString() {
    return "CollaborationPermissionsV2025R0{"
        + "isCoOwnerRoleEnabled='"
        + isCoOwnerRoleEnabled
        + '\''
        + ", "
        + "isEditorRoleEnabled='"
        + isEditorRoleEnabled
        + '\''
        + ", "
        + "isPreviewerRoleEnabled='"
        + isPreviewerRoleEnabled
        + '\''
        + ", "
        + "isPreviewerUploaderRoleEnabled='"
        + isPreviewerUploaderRoleEnabled
        + '\''
        + ", "
        + "isUploaderRoleEnabled='"
        + isUploaderRoleEnabled
        + '\''
        + ", "
        + "isViewerRoleEnabled='"
        + isViewerRoleEnabled
        + '\''
        + ", "
        + "isViewerUploaderRoleEnabled='"
        + isViewerUploaderRoleEnabled
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Boolean isCoOwnerRoleEnabled;

    protected Boolean isEditorRoleEnabled;

    protected Boolean isPreviewerRoleEnabled;

    protected Boolean isPreviewerUploaderRoleEnabled;

    protected Boolean isUploaderRoleEnabled;

    protected Boolean isViewerRoleEnabled;

    protected Boolean isViewerUploaderRoleEnabled;

    public Builder isCoOwnerRoleEnabled(Boolean isCoOwnerRoleEnabled) {
      this.isCoOwnerRoleEnabled = isCoOwnerRoleEnabled;
      return this;
    }

    public Builder isEditorRoleEnabled(Boolean isEditorRoleEnabled) {
      this.isEditorRoleEnabled = isEditorRoleEnabled;
      return this;
    }

    public Builder isPreviewerRoleEnabled(Boolean isPreviewerRoleEnabled) {
      this.isPreviewerRoleEnabled = isPreviewerRoleEnabled;
      return this;
    }

    public Builder isPreviewerUploaderRoleEnabled(Boolean isPreviewerUploaderRoleEnabled) {
      this.isPreviewerUploaderRoleEnabled = isPreviewerUploaderRoleEnabled;
      return this;
    }

    public Builder isUploaderRoleEnabled(Boolean isUploaderRoleEnabled) {
      this.isUploaderRoleEnabled = isUploaderRoleEnabled;
      return this;
    }

    public Builder isViewerRoleEnabled(Boolean isViewerRoleEnabled) {
      this.isViewerRoleEnabled = isViewerRoleEnabled;
      return this;
    }

    public Builder isViewerUploaderRoleEnabled(Boolean isViewerUploaderRoleEnabled) {
      this.isViewerUploaderRoleEnabled = isViewerUploaderRoleEnabled;
      return this;
    }

    public CollaborationPermissionsV2025R0 build() {
      return new CollaborationPermissionsV2025R0(this);
    }
  }
}
