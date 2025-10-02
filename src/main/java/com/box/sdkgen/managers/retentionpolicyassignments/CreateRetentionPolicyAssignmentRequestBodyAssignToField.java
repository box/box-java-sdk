package com.box.sdkgen.managers.retentionpolicyassignments;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateRetentionPolicyAssignmentRequestBodyAssignToField extends SerializableObject {

  /** The type of item to assign the policy to. */
  @JsonDeserialize(
      using =
          CreateRetentionPolicyAssignmentRequestBodyAssignToTypeField
              .CreateRetentionPolicyAssignmentRequestBodyAssignToTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateRetentionPolicyAssignmentRequestBodyAssignToTypeField
              .CreateRetentionPolicyAssignmentRequestBodyAssignToTypeFieldSerializer.class)
  protected final EnumWrapper<CreateRetentionPolicyAssignmentRequestBodyAssignToTypeField> type;

  /**
   * The ID of item to assign the policy to. Set to `null` or omit when `type` is set to
   * `enterprise`.
   */
  @Nullable protected String id;

  public CreateRetentionPolicyAssignmentRequestBodyAssignToField(
      CreateRetentionPolicyAssignmentRequestBodyAssignToTypeField type) {
    super();
    this.type = new EnumWrapper<CreateRetentionPolicyAssignmentRequestBodyAssignToTypeField>(type);
  }

  public CreateRetentionPolicyAssignmentRequestBodyAssignToField(
      @JsonProperty("type")
          EnumWrapper<CreateRetentionPolicyAssignmentRequestBodyAssignToTypeField> type) {
    super();
    this.type = type;
  }

  protected CreateRetentionPolicyAssignmentRequestBodyAssignToField(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<CreateRetentionPolicyAssignmentRequestBodyAssignToTypeField> getType() {
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
    CreateRetentionPolicyAssignmentRequestBodyAssignToField casted =
        (CreateRetentionPolicyAssignmentRequestBodyAssignToField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "CreateRetentionPolicyAssignmentRequestBodyAssignToField{"
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

    protected final EnumWrapper<CreateRetentionPolicyAssignmentRequestBodyAssignToTypeField> type;

    protected String id;

    public Builder(CreateRetentionPolicyAssignmentRequestBodyAssignToTypeField type) {
      super();
      this.type =
          new EnumWrapper<CreateRetentionPolicyAssignmentRequestBodyAssignToTypeField>(type);
    }

    public Builder(EnumWrapper<CreateRetentionPolicyAssignmentRequestBodyAssignToTypeField> type) {
      super();
      this.type = type;
    }

    public Builder id(String id) {
      this.id = id;
      this.markNullableFieldAsSet("id");
      return this;
    }

    public CreateRetentionPolicyAssignmentRequestBodyAssignToField build() {
      return new CreateRetentionPolicyAssignmentRequestBodyAssignToField(this);
    }
  }
}
