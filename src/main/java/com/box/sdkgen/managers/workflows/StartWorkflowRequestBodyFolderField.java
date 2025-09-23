package com.box.sdkgen.managers.workflows;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class StartWorkflowRequestBodyFolderField extends SerializableObject {

  @JsonDeserialize(
      using =
          StartWorkflowRequestBodyFolderTypeField
              .StartWorkflowRequestBodyFolderTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          StartWorkflowRequestBodyFolderTypeField.StartWorkflowRequestBodyFolderTypeFieldSerializer
              .class)
  protected EnumWrapper<StartWorkflowRequestBodyFolderTypeField> type;

  protected String id;

  public StartWorkflowRequestBodyFolderField() {
    super();
  }

  protected StartWorkflowRequestBodyFolderField(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<StartWorkflowRequestBodyFolderTypeField> getType() {
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
    StartWorkflowRequestBodyFolderField casted = (StartWorkflowRequestBodyFolderField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "StartWorkflowRequestBodyFolderField{"
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

    protected EnumWrapper<StartWorkflowRequestBodyFolderTypeField> type;

    protected String id;

    public Builder type(StartWorkflowRequestBodyFolderTypeField type) {
      this.type = new EnumWrapper<StartWorkflowRequestBodyFolderTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<StartWorkflowRequestBodyFolderTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public StartWorkflowRequestBodyFolderField build() {
      return new StartWorkflowRequestBodyFolderField(this);
    }
  }
}
