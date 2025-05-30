package com.box.sdkgen.managers.tasks;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class CreateTaskRequestBody extends SerializableObject {

  protected final CreateTaskRequestBodyItemField item;

  @JsonDeserialize(
      using = CreateTaskRequestBodyActionField.CreateTaskRequestBodyActionFieldDeserializer.class)
  @JsonSerialize(
      using = CreateTaskRequestBodyActionField.CreateTaskRequestBodyActionFieldSerializer.class)
  protected EnumWrapper<CreateTaskRequestBodyActionField> action;

  protected String message;

  @JsonProperty("due_at")
  protected String dueAt;

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

  protected CreateTaskRequestBody(CreateTaskRequestBodyBuilder builder) {
    super();
    this.item = builder.item;
    this.action = builder.action;
    this.message = builder.message;
    this.dueAt = builder.dueAt;
    this.completionRule = builder.completionRule;
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

  public String getDueAt() {
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

  public static class CreateTaskRequestBodyBuilder {

    protected final CreateTaskRequestBodyItemField item;

    protected EnumWrapper<CreateTaskRequestBodyActionField> action;

    protected String message;

    protected String dueAt;

    protected EnumWrapper<CreateTaskRequestBodyCompletionRuleField> completionRule;

    public CreateTaskRequestBodyBuilder(CreateTaskRequestBodyItemField item) {
      this.item = item;
    }

    public CreateTaskRequestBodyBuilder action(CreateTaskRequestBodyActionField action) {
      this.action = new EnumWrapper<CreateTaskRequestBodyActionField>(action);
      return this;
    }

    public CreateTaskRequestBodyBuilder action(
        EnumWrapper<CreateTaskRequestBodyActionField> action) {
      this.action = action;
      return this;
    }

    public CreateTaskRequestBodyBuilder message(String message) {
      this.message = message;
      return this;
    }

    public CreateTaskRequestBodyBuilder dueAt(String dueAt) {
      this.dueAt = dueAt;
      return this;
    }

    public CreateTaskRequestBodyBuilder completionRule(
        CreateTaskRequestBodyCompletionRuleField completionRule) {
      this.completionRule =
          new EnumWrapper<CreateTaskRequestBodyCompletionRuleField>(completionRule);
      return this;
    }

    public CreateTaskRequestBodyBuilder completionRule(
        EnumWrapper<CreateTaskRequestBodyCompletionRuleField> completionRule) {
      this.completionRule = completionRule;
      return this;
    }

    public CreateTaskRequestBody build() {
      return new CreateTaskRequestBody(this);
    }
  }
}
