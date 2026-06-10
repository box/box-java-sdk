package com.box.sdkgen.schemas.aiextractsubfield;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aiextractfieldoption.AiExtractFieldOption;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** A nested field definition for structured and table field types used in AI extraction. */
@JsonFilter("nullablePropertyFilter")
public class AiExtractSubField extends SerializableObject {

  /** A unique identifier for the nested field. */
  protected final String key;

  /** A description of the nested field. */
  protected String description;

  /** The display name of the nested field. */
  protected String displayName;

  /** Context about the nested field that may include how to find and how to format it. */
  protected String prompt;

  /**
   * The type of the nested field. Allowed types include `string`, `float`, `date`, `number`,
   * `text`, `boolean`, `enum` and `multiSelect`.
   */
  protected String type;

  /** A list of options for this nested field. Used with `enum` and `multiSelect` types. */
  protected List<AiExtractFieldOption> options;

  public AiExtractSubField(@JsonProperty("key") String key) {
    super();
    this.key = key;
  }

  protected AiExtractSubField(Builder builder) {
    super();
    this.key = builder.key;
    this.description = builder.description;
    this.displayName = builder.displayName;
    this.prompt = builder.prompt;
    this.type = builder.type;
    this.options = builder.options;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public List<AiExtractFieldOption> getOptions() {
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
    AiExtractSubField casted = (AiExtractSubField) o;
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
    return "AiExtractSubField{"
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

  public static class Builder extends NullableFieldTracker {

    protected final String key;

    protected String description;

    protected String displayName;

    protected String prompt;

    protected String type;

    protected List<AiExtractFieldOption> options;

    public Builder(String key) {
      super();
      this.key = key;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder displayName(String displayName) {
      this.displayName = displayName;
      return this;
    }

    public Builder prompt(String prompt) {
      this.prompt = prompt;
      return this;
    }

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Builder options(List<AiExtractFieldOption> options) {
      this.options = options;
      return this;
    }

    public AiExtractSubField build() {
      return new AiExtractSubField(this);
    }
  }
}
