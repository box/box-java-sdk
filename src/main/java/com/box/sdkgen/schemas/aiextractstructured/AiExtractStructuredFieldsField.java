package com.box.sdkgen.schemas.aiextractstructured;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class AiExtractStructuredFieldsField extends SerializableObject {

  protected final String key;

  protected String description;

  protected String displayName;

  protected String prompt;

  protected String type;

  protected List<AiExtractStructuredFieldsOptionsField> options;

  public AiExtractStructuredFieldsField(@JsonProperty("key") String key) {
    super();
    this.key = key;
  }

  protected AiExtractStructuredFieldsField(AiExtractStructuredFieldsFieldBuilder builder) {
    super();
    this.key = builder.key;
    this.description = builder.description;
    this.displayName = builder.displayName;
    this.prompt = builder.prompt;
    this.type = builder.type;
    this.options = builder.options;
  }

  public String getKey() {
    return key;
  }

  public String getDescription() {
    return description;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getPrompt() {
    return prompt;
  }

  public String getType() {
    return type;
  }

  public List<AiExtractStructuredFieldsOptionsField> getOptions() {
    return options;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiExtractStructuredFieldsField casted = (AiExtractStructuredFieldsField) o;
    return Objects.equals(key, casted.key)
        && Objects.equals(description, casted.description)
        && Objects.equals(displayName, casted.displayName)
        && Objects.equals(prompt, casted.prompt)
        && Objects.equals(type, casted.type)
        && Objects.equals(options, casted.options);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, description, displayName, prompt, type, options);
  }

  @Override
  public String toString() {
    return "AiExtractStructuredFieldsField{"
        + "key='"
        + key
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + ", "
        + "displayName='"
        + displayName
        + '\''
        + ", "
        + "prompt='"
        + prompt
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "options='"
        + options
        + '\''
        + "}";
  }

  public static class AiExtractStructuredFieldsFieldBuilder {

    protected final String key;

    protected String description;

    protected String displayName;

    protected String prompt;

    protected String type;

    protected List<AiExtractStructuredFieldsOptionsField> options;

    public AiExtractStructuredFieldsFieldBuilder(String key) {
      this.key = key;
    }

    public AiExtractStructuredFieldsFieldBuilder description(String description) {
      this.description = description;
      return this;
    }

    public AiExtractStructuredFieldsFieldBuilder displayName(String displayName) {
      this.displayName = displayName;
      return this;
    }

    public AiExtractStructuredFieldsFieldBuilder prompt(String prompt) {
      this.prompt = prompt;
      return this;
    }

    public AiExtractStructuredFieldsFieldBuilder type(String type) {
      this.type = type;
      return this;
    }

    public AiExtractStructuredFieldsFieldBuilder options(
        List<AiExtractStructuredFieldsOptionsField> options) {
      this.options = options;
      return this;
    }

    public AiExtractStructuredFieldsField build() {
      return new AiExtractStructuredFieldsField(this);
    }
  }
}
