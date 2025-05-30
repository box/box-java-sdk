package com.box.sdkgen.managers.sharedlinksweblinks;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class UpdateSharedLinkOnWebLinkRequestBodySharedLinkPermissionsField
    extends SerializableObject {

  @JsonProperty("can_download")
  protected Boolean canDownload;

  @JsonProperty("can_preview")
  protected Boolean canPreview;

  @JsonProperty("can_edit")
  protected Boolean canEdit;

  public UpdateSharedLinkOnWebLinkRequestBodySharedLinkPermissionsField() {
    super();
  }

  protected UpdateSharedLinkOnWebLinkRequestBodySharedLinkPermissionsField(
      UpdateSharedLinkOnWebLinkRequestBodySharedLinkPermissionsFieldBuilder builder) {
    super();
    this.canDownload = builder.canDownload;
    this.canPreview = builder.canPreview;
    this.canEdit = builder.canEdit;
  }

  public Boolean getCanDownload() {
    return canDownload;
  }

  public Boolean getCanPreview() {
    return canPreview;
  }

  public Boolean getCanEdit() {
    return canEdit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateSharedLinkOnWebLinkRequestBodySharedLinkPermissionsField casted =
        (UpdateSharedLinkOnWebLinkRequestBodySharedLinkPermissionsField) o;
    return Objects.equals(canDownload, casted.canDownload)
        && Objects.equals(canPreview, casted.canPreview)
        && Objects.equals(canEdit, casted.canEdit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(canDownload, canPreview, canEdit);
  }

  @Override
  public String toString() {
    return "UpdateSharedLinkOnWebLinkRequestBodySharedLinkPermissionsField{"
        + "canDownload='"
        + canDownload
        + '\''
        + ", "
        + "canPreview='"
        + canPreview
        + '\''
        + ", "
        + "canEdit='"
        + canEdit
        + '\''
        + "}";
  }

  public static class UpdateSharedLinkOnWebLinkRequestBodySharedLinkPermissionsFieldBuilder {

    protected Boolean canDownload;

    protected Boolean canPreview;

    protected Boolean canEdit;

    public UpdateSharedLinkOnWebLinkRequestBodySharedLinkPermissionsFieldBuilder canDownload(
        Boolean canDownload) {
      this.canDownload = canDownload;
      return this;
    }

    public UpdateSharedLinkOnWebLinkRequestBodySharedLinkPermissionsFieldBuilder canPreview(
        Boolean canPreview) {
      this.canPreview = canPreview;
      return this;
    }

    public UpdateSharedLinkOnWebLinkRequestBodySharedLinkPermissionsFieldBuilder canEdit(
        Boolean canEdit) {
      this.canEdit = canEdit;
      return this;
    }

    public UpdateSharedLinkOnWebLinkRequestBodySharedLinkPermissionsField build() {
      return new UpdateSharedLinkOnWebLinkRequestBodySharedLinkPermissionsField(this);
    }
  }
}
