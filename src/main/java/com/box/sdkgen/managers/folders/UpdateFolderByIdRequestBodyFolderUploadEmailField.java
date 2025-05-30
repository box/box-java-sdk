package com.box.sdkgen.managers.folders;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class UpdateFolderByIdRequestBodyFolderUploadEmailField extends SerializableObject {

  @JsonDeserialize(
      using =
          UpdateFolderByIdRequestBodyFolderUploadEmailAccessField
              .UpdateFolderByIdRequestBodyFolderUploadEmailAccessFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateFolderByIdRequestBodyFolderUploadEmailAccessField
              .UpdateFolderByIdRequestBodyFolderUploadEmailAccessFieldSerializer.class)
  protected EnumWrapper<UpdateFolderByIdRequestBodyFolderUploadEmailAccessField> access;

  public UpdateFolderByIdRequestBodyFolderUploadEmailField() {
    super();
  }

  protected UpdateFolderByIdRequestBodyFolderUploadEmailField(
      UpdateFolderByIdRequestBodyFolderUploadEmailFieldBuilder builder) {
    super();
    this.access = builder.access;
  }

  public EnumWrapper<UpdateFolderByIdRequestBodyFolderUploadEmailAccessField> getAccess() {
    return access;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateFolderByIdRequestBodyFolderUploadEmailField casted =
        (UpdateFolderByIdRequestBodyFolderUploadEmailField) o;
    return Objects.equals(access, casted.access);
  }

  @Override
  public int hashCode() {
    return Objects.hash(access);
  }

  @Override
  public String toString() {
    return "UpdateFolderByIdRequestBodyFolderUploadEmailField{" + "access='" + access + '\'' + "}";
  }

  public static class UpdateFolderByIdRequestBodyFolderUploadEmailFieldBuilder {

    protected EnumWrapper<UpdateFolderByIdRequestBodyFolderUploadEmailAccessField> access;

    public UpdateFolderByIdRequestBodyFolderUploadEmailFieldBuilder access(
        UpdateFolderByIdRequestBodyFolderUploadEmailAccessField access) {
      this.access =
          new EnumWrapper<UpdateFolderByIdRequestBodyFolderUploadEmailAccessField>(access);
      return this;
    }

    public UpdateFolderByIdRequestBodyFolderUploadEmailFieldBuilder access(
        EnumWrapper<UpdateFolderByIdRequestBodyFolderUploadEmailAccessField> access) {
      this.access = access;
      return this;
    }

    public UpdateFolderByIdRequestBodyFolderUploadEmailField build() {
      return new UpdateFolderByIdRequestBodyFolderUploadEmailField(this);
    }
  }
}
