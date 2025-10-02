package com.box.sdkgen.managers.files;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateFileByIdRequestBodySharedLinkPermissionsField extends SerializableObject {

  /**
   * If the shared link allows for downloading of files. This can only be set when `access` is set
   * to `open` or `company`.
   */
  @JsonProperty("can_download")
  protected Boolean canDownload;

  public UpdateFileByIdRequestBodySharedLinkPermissionsField() {
    super();
  }

  protected UpdateFileByIdRequestBodySharedLinkPermissionsField(Builder builder) {
    super();
    this.canDownload = builder.canDownload;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Boolean getCanDownload() {
    return canDownload;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateFileByIdRequestBodySharedLinkPermissionsField casted =
        (UpdateFileByIdRequestBodySharedLinkPermissionsField) o;
    return Objects.equals(canDownload, casted.canDownload);
  }

  @Override
  public int hashCode() {
    return Objects.hash(canDownload);
  }

  @Override
  public String toString() {
    return "UpdateFileByIdRequestBodySharedLinkPermissionsField{"
        + "canDownload='"
        + canDownload
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Boolean canDownload;

    public Builder canDownload(Boolean canDownload) {
      this.canDownload = canDownload;
      return this;
    }

    public UpdateFileByIdRequestBodySharedLinkPermissionsField build() {
      return new UpdateFileByIdRequestBodySharedLinkPermissionsField(this);
    }
  }
}
