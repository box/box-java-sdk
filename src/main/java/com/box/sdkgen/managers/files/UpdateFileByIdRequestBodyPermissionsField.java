package com.box.sdkgen.managers.files;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateFileByIdRequestBodyPermissionsField extends SerializableObject {

  @JsonDeserialize(
      using =
          UpdateFileByIdRequestBodyPermissionsCanDownloadField
              .UpdateFileByIdRequestBodyPermissionsCanDownloadFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateFileByIdRequestBodyPermissionsCanDownloadField
              .UpdateFileByIdRequestBodyPermissionsCanDownloadFieldSerializer.class)
  @JsonProperty("can_download")
  protected EnumWrapper<UpdateFileByIdRequestBodyPermissionsCanDownloadField> canDownload;

  public UpdateFileByIdRequestBodyPermissionsField() {
    super();
  }

  protected UpdateFileByIdRequestBodyPermissionsField(Builder builder) {
    super();
    this.canDownload = builder.canDownload;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<UpdateFileByIdRequestBodyPermissionsCanDownloadField> getCanDownload() {
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
    UpdateFileByIdRequestBodyPermissionsField casted =
        (UpdateFileByIdRequestBodyPermissionsField) o;
    return Objects.equals(canDownload, casted.canDownload);
  }

  @Override
  public int hashCode() {
    return Objects.hash(canDownload);
  }

  @Override
  public String toString() {
    return "UpdateFileByIdRequestBodyPermissionsField{"
        + "canDownload='"
        + canDownload
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<UpdateFileByIdRequestBodyPermissionsCanDownloadField> canDownload;

    public Builder canDownload(UpdateFileByIdRequestBodyPermissionsCanDownloadField canDownload) {
      this.canDownload =
          new EnumWrapper<UpdateFileByIdRequestBodyPermissionsCanDownloadField>(canDownload);
      return this;
    }

    public Builder canDownload(
        EnumWrapper<UpdateFileByIdRequestBodyPermissionsCanDownloadField> canDownload) {
      this.canDownload = canDownload;
      return this;
    }

    public UpdateFileByIdRequestBodyPermissionsField build() {
      return new UpdateFileByIdRequestBodyPermissionsField(this);
    }
  }
}
