package com.box.sdkgen.managers.storagepolicyassignments;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyField
    extends SerializableObject {

  /** The type to assign. */
  @JsonDeserialize(
      using =
          UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField
              .UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField
              .UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeFieldSerializer.class)
  protected EnumWrapper<UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField> type;

  /** The ID of the storage policy to assign. */
  protected final String id;

  public UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyField(
      @JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField>(
            UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField.STORAGE_POLICY);
  }

  protected UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyField(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField> type;

    protected final String id;

    public Builder(String id) {
      super();
      this.id = id;
    }

    public Builder type(UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField type) {
      this.type =
          new EnumWrapper<UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField>(type);
      return this;
    }

    public Builder type(
        EnumWrapper<UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField> type) {
      this.type = type;
      return this;
    }

    public UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyField build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField>(
                UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField.STORAGE_POLICY);
      }
      return new UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyField(this);
    }
  }
}
