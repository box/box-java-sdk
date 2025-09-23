package com.box.sdkgen.managers.tasks;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateTaskByIdRequestBody extends SerializableObject {

  @JsonDeserialize(
      using =
          UpdateTaskByIdRequestBodyActionField.UpdateTaskByIdRequestBodyActionFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          UpdateTaskByIdRequestBodyActionField.UpdateTaskByIdRequestBodyActionFieldSerializer.class)
  protected EnumWrapper<UpdateTaskByIdRequestBodyActionField> action;

  protected String message;

  @JsonProperty("due_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime dueAt;

  @JsonDeserialize(
      using =
          UpdateTaskByIdRequestBodyCompletionRuleField
              .UpdateTaskByIdRequestBodyCompletionRuleFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateTaskByIdRequestBodyCompletionRuleField
              .UpdateTaskByIdRequestBodyCompletionRuleFieldSerializer.class)
  @JsonProperty("completion_rule")
  protected EnumWrapper<UpdateTaskByIdRequestBodyCompletionRuleField> completionRule;

  public UpdateTaskByIdRequestBody() {
    super();
  }

  protected UpdateTaskByIdRequestBody(Builder builder) {
    super();
    this.action = builder.action;
    this.message = builder.message;
    this.dueAt = builder.dueAt;
    this.completionRule = builder.completionRule;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<UpdateTaskByIdRequestBodyActionField> getAction() {
    return action;
  }

  public String getMessage() {
    return message;
  }

  public OffsetDateTime getDueAt() {
    return dueAt;
  }

  public EnumWrapper<UpdateTaskByIdRequestBodyCompletionRuleField> getCompletionRule() {
    return completionRule;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateTaskByIdRequestBody casted = (UpdateTaskByIdRequestBody) o;
    return Objects.equals(action, casted.action)
        && Objects.equals(message, casted.message)
        && Objects.equals(dueAt, casted.dueAt)
        && Objects.equals(completionRule, casted.completionRule);
  }

  @Override
  public int hashCode() {
    return Objects.hash(action, message, dueAt, completionRule);
  }

  @Override
  public String toString() {
    return "UpdateTaskByIdRequestBody{"
        + "action='"
        + action
        + '\''
        + ", "
        + "message='"
        + message
        + '\''
        + ", "
        + "dueAt='"
        + dueAt
        + '\''
        + ", "
        + "completionRule='"
        + completionRule
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<UpdateTaskByIdRequestBodyActionField> action;

    protected String message;

    protected OffsetDateTime dueAt;

    protected EnumWrapper<UpdateTaskByIdRequestBodyCompletionRuleField> completionRule;

    public Builder action(UpdateTaskByIdRequestBodyActionField action) {
      this.action = new EnumWrapper<UpdateTaskByIdRequestBodyActionField>(action);
      return this;
    }

    public Builder action(EnumWrapper<UpdateTaskByIdRequestBodyActionField> action) {
      this.action = action;
      return this;
    }

    public Builder message(String message) {
      this.message = message;
      return this;
    }

    public Builder dueAt(OffsetDateTime dueAt) {
      this.dueAt = dueAt;
      return this;
    }

    public Builder completionRule(UpdateTaskByIdRequestBodyCompletionRuleField completionRule) {
      this.completionRule =
          new EnumWrapper<UpdateTaskByIdRequestBodyCompletionRuleField>(completionRule);
      return this;
    }

    public Builder completionRule(
        EnumWrapper<UpdateTaskByIdRequestBodyCompletionRuleField> completionRule) {
      this.completionRule = completionRule;
      return this;
    }

    public UpdateTaskByIdRequestBody build() {
      return new UpdateTaskByIdRequestBody(this);
    }
  }
}
