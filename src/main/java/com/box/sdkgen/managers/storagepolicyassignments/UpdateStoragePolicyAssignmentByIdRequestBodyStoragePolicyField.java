package com.box.sdkgen.managers.storagepolicyassignments;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyField
    extends SerializableObject {

  @JsonDeserialize(
      using =
          UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField
              .UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField
              .UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeFieldSerializer.class)
  protected EnumWrapper<UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField> type;

  protected final String id;

  public UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyField(
      @JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField>(
            UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField.STORAGE_POLICY);
  }

  protected UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyField(
      UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyFieldBuilder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
  }

  public EnumWrapper<UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyField casted =
        (UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyField{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }

  public static class UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyFieldBuilder {

    protected EnumWrapper<UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField> type;

    protected final String id;

    public UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyFieldBuilder(String id) {
      this.id = id;
      this.type =
          new EnumWrapper<UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField>(
              UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField.STORAGE_POLICY);
    }

    public UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyFieldBuilder type(
        UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField type) {
      this.type =
          new EnumWrapper<UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField>(type);
      return this;
    }

    public UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyFieldBuilder type(
        EnumWrapper<UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField> type) {
      this.type = type;
      return this;
    }

    public UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyField build() {
      return new UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyField(this);
    }
  }
}
