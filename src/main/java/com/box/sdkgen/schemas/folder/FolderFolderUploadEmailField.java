package com.box.sdkgen.schemas.folder;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FolderFolderUploadEmailField extends SerializableObject {

  @JsonDeserialize(
      using =
          FolderFolderUploadEmailAccessField.FolderFolderUploadEmailAccessFieldDeserializer.class)
  @JsonSerialize(
      using = FolderFolderUploadEmailAccessField.FolderFolderUploadEmailAccessFieldSerializer.class)
  protected EnumWrapper<FolderFolderUploadEmailAccessField> access;

  protected String email;

  public FolderFolderUploadEmailField() {
    super();
  }

  protected FolderFolderUploadEmailField(Builder builder) {
    super();
    this.access = builder.access;
    this.email = builder.email;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<FolderFolderUploadEmailAccessField> getAccess() {
    return access;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FolderFolderUploadEmailField casted = (FolderFolderUploadEmailField) o;
    return Objects.equals(access, casted.access) && Objects.equals(email, casted.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(access, email);
  }

  @Override
  public String toString() {
    return "FolderFolderUploadEmailField{"
        + "access='"
        + access
        + '\''
        + ", "
        + "email='"
        + email
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<FolderFolderUploadEmailAccessField> access;

    protected String email;

    public Builder access(FolderFolderUploadEmailAccessField access) {
      this.access = new EnumWrapper<FolderFolderUploadEmailAccessField>(access);
      return this;
    }

    public Builder access(EnumWrapper<FolderFolderUploadEmailAccessField> access) {
      this.access = access;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public FolderFolderUploadEmailField build() {
      return new FolderFolderUploadEmailField(this);
    }
  }
}
