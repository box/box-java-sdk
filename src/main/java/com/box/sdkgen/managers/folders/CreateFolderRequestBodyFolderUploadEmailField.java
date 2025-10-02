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

  /**
   * When this parameter has been set, users can email files to the email address that has been
   * automatically created for this folder.
   *
   * <p>To create an email address, set this property either when creating or updating the folder.
   *
   * <p>When set to `collaborators`, only emails from registered email addresses for collaborators
   * will be accepted. This includes any email aliases a user might have registered.
   *
   * <p>When set to `open` it will accept emails from any email address.
   */
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
