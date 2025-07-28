package com.box.sdkgen.managers.tasks;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateTaskRequestBody extends SerializableObject {

  protected final CreateTaskRequestBodyItemField item;

  @JsonDeserialize(
      using = CreateTaskRequestBodyActionField.CreateTaskRequestBodyActionFieldDeserializer.class)
  @JsonSerialize(
      using = CreateTaskRequestBodyActionField.CreateTaskRequestBodyActionFieldSerializer.class)
  protected EnumWrapper<CreateTaskRequestBodyActionField> action;

  protected String message;

  @JsonProperty("due_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date dueAt;

  @JsonDeserialize(
      using =
          CreateTaskRequestBodyCompletionRuleField
              .CreateTaskRequestBodyCompletionRuleFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateTaskRequestBodyCompletionRuleField
              .CreateTaskRequestBodyCompletionRuleFieldSerializer.class)
  @JsonProperty("completion_rule")
  protected EnumWrapper<CreateTaskRequestBodyCompletionRuleField> completionRule;

  public CreateTaskRequestBody(@JsonProperty("item") CreateTaskRequestBodyItemField item) {
    super();
    this.item = item;
  }

  protected CreateTaskRequestBody(Builder builder) {
    super();
    this.item = builder.item;
    this.action = builder.action;
    this.message = builder.message;
    this.dueAt = builder.dueAt;
    this.completionRule = builder.completionRule;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public CreateTaskRequestBodyItemField getItem() {
    return item;
  }

  public EnumWrapper<CreateTaskRequestBodyActionField> getAction() {
    return action;
  }

  public String getMessage() {
    return message;
  }

  public Date getDueAt() {
    return dueAt;
  }

  public EnumWrapper<CreateTaskRequestBodyCompletionRuleField> getCompletionRule() {
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
    CreateTaskRequestBody casted = (CreateTaskRequestBody) o;
    return Objects.equals(item, casted.item)
        && Objects.equals(action, casted.action)
        && Objects.equals(message, casted.message)
        && Objects.equals(dueAt, casted.dueAt)
        && Objects.equals(completionRule, casted.completionRule);
  }

  @Override
  public int hashCode() {
    return Objects.hash(item, action, message, dueAt, completionRule);
  }

  @Override
  public String toString() {
    return "CreateTaskRequestBody{"
        + "item='"
        + item
        + '\''
        + ", "
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

    protected final CreateTaskRequestBodyItemField item;

    protected EnumWrapper<CreateTaskRequestBodyActionField> action;

    protected String message;

    protected Date dueAt;

    protected EnumWrapper<CreateTaskRequestBodyCompletionRuleField> completionRule;

    public Builder(CreateTaskRequestBodyItemField item) {
      super();
      this.item = item;
    }

    public Builder action(CreateTaskRequestBodyActionField action) {
      this.action = new EnumWrapper<CreateTaskRequestBodyActionField>(action);
      return this;
    }

    public Builder action(EnumWrapper<CreateTaskRequestBodyActionField> action) {
      this.action = action;
      return this;
    }

    public Builder message(String message) {
      this.message = message;
      return this;
    }

    public Builder dueAt(Date dueAt) {
      this.dueAt = dueAt;
      return this;
    }

    public Builder completionRule(CreateTaskRequestBodyCompletionRuleField completionRule) {
      this.completionRule =
          new EnumWrapper<CreateTaskRequestBodyCompletionRuleField>(completionRule);
      return this;
    }

    public Builder completionRule(
        EnumWrapper<CreateTaskRequestBodyCompletionRuleField> completionRule) {
      this.completionRule = completionRule;
      return this;
    }

    public CreateTaskRequestBody build() {
      return new CreateTaskRequestBody(this);
    }
  }
}
