package com.box.sdkgen.managers.workflows;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class StartWorkflowRequestBodyFilesField extends SerializableObject {

  /** The type of the file object. */
  @JsonDeserialize(
      using =
          StartWorkflowRequestBodyFilesTypeField.StartWorkflowRequestBodyFilesTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          StartWorkflowRequestBodyFilesTypeField.StartWorkflowRequestBodyFilesTypeFieldSerializer
              .class)
  protected EnumWrapper<StartWorkflowRequestBodyFilesTypeField> type;

  /** The id of the file. */
  protected String id;

  public StartWorkflowRequestBodyFilesField() {
    super();
  }

  protected StartWorkflowRequestBodyFilesField(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<StartWorkflowRequestBodyFilesTypeField> getType() {
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
    StartWorkflowRequestBodyFilesField casted = (StartWorkflowRequestBodyFilesField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "StartWorkflowRequestBodyFilesField{"
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

    protected EnumWrapper<StartWorkflowRequestBodyFilesTypeField> type;

    protected String id;

    public Builder type(StartWorkflowRequestBodyFilesTypeField type) {
      this.type = new EnumWrapper<StartWorkflowRequestBodyFilesTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<StartWorkflowRequestBodyFilesTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public StartWorkflowRequestBodyFilesField build() {
      return new StartWorkflowRequestBodyFilesField(this);
    }
  }
}
