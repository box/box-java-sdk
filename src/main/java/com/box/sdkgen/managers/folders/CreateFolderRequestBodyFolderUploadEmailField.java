package com.box.sdkgen.managers.folders;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateFolderRequestBodyFolderUploadEmailField extends SerializableObject {

  @JsonDeserialize(
      using =
          CreateFolderRequestBodyFolderUploadEmailAccessField
              .CreateFolderRequestBodyFolderUploadEmailAccessFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateFolderRequestBodyFolderUploadEmailAccessField
              .CreateFolderRequestBodyFolderUploadEmailAccessFieldSerializer.class)
  protected EnumWrapper<CreateFolderRequestBodyFolderUploadEmailAccessField> access;

  public CreateFolderRequestBodyFolderUploadEmailField() {
    super();
  }

  protected CreateFolderRequestBodyFolderUploadEmailField(Builder builder) {
    super();
    this.access = builder.access;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<CreateFolderRequestBodyFolderUploadEmailAccessField> getAccess() {
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
    CreateFolderRequestBodyFolderUploadEmailField casted =
        (CreateFolderRequestBodyFolderUploadEmailField) o;
    return Objects.equals(access, casted.access);
  }

  @Override
  public int hashCode() {
    return Objects.hash(access);
  }

  @Override
  public String toString() {
    return "CreateFolderRequestBodyFolderUploadEmailField{" + "access='" + access + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<CreateFolderRequestBodyFolderUploadEmailAccessField> access;

    public Builder access(CreateFolderRequestBodyFolderUploadEmailAccessField access) {
      this.access = new EnumWrapper<CreateFolderRequestBodyFolderUploadEmailAccessField>(access);
      return this;
    }

    public Builder access(EnumWrapper<CreateFolderRequestBodyFolderUploadEmailAccessField> access) {
      this.access = access;
      return this;
    }

    public CreateFolderRequestBodyFolderUploadEmailField build() {
      return new CreateFolderRequestBodyFolderUploadEmailField(this);
    }
  }
}
