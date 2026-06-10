package com.box.sdkgen.schemas.aiextractstructured;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aiextractsubfield.AiExtractSubField;
import com.box.sdkgen.schemas.aioptionsrules.AiOptionsRules;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class AiExtractStructuredFieldsField extends SerializableObject {

  /** A unique identifier for the field. */
  protected final String key;

  /** A description of the field. */
  protected String description;

  /** The display name of the field. */
  protected String displayName;

  /** The context about the key that may include how to find and format it. */
  protected String prompt;

  /**
   * The type of the field. It can include but is not limited to `string`, `float`, `date`, `enum`,
   * `multiSelect`,`taxonomy`, `struct`, and `table`.
   */
  protected String type;

  /**
   * A list of options for this field. This is most often used in combination with the `enum` and
   * `multiSelect` field types.
   */
  protected List<AiExtractStructuredFieldsOptionsField> options;

  /**
   * The nested fields for this field. Used with `struct` and `table` field types to define the
   * nested structure.
   */
  protected List<AiExtractSubField> fields;

  /**
   * The identifier for a taxonomy, which corresponds to the `key` of the taxonomy source. Required
   * if using `taxonomy` type field.
   */
  @JsonProperty("taxonomy_key")
  protected String taxonomyKey;

  /**
   * The namespace of the taxonomy source. Required if using `taxonomy` type field from an existing
   * taxonomy.
   */
  protected String namespace;

  @JsonProperty("options_rules")
  protected AiOptionsRules optionsRules;

  public AiExtractStructuredFieldsField(@JsonProperty("key") String key) {
    super();
    this.key = key;
  }

  protected AiExtractStructuredFieldsField(Builder builder) {
    super();
    this.key = builder.key;
    this.description = builder.description;
    this.displayName = builder.displayName;
    this.prompt = builder.prompt;
    this.type = builder.type;
    this.options = builder.options;
    this.fields = builder.fields;
    this.taxonomyKey = builder.taxonomyKey;
    this.namespace = builder.namespace;
    this.optionsRules = builder.optionsRules;
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

  public List<AiExtractStructuredFieldsOptionsField> getOptions() {
    return options;
  }

  public List<AiExtractSubField> getFields() {
    return fields;
  }

  public String getTaxonomyKey() {
    return taxonomyKey;
  }

  public String getNamespace() {
    return namespace;
  }

  public AiOptionsRules getOptionsRules() {
    return optionsRules;
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
        && Objects.equals(options, casted.options)
        && Objects.equals(fields, casted.fields)
        && Objects.equals(taxonomyKey, casted.taxonomyKey)
        && Objects.equals(namespace, casted.namespace)
        && Objects.equals(optionsRules, casted.optionsRules);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        key,
        description,
        displayName,
        prompt,
        type,
        options,
        fields,
        taxonomyKey,
        namespace,
        optionsRules);
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
        + ", "
        + "fields='"
        + fields
        + '\''
        + ", "
        + "taxonomyKey='"
        + taxonomyKey
        + '\''
        + ", "
        + "namespace='"
        + namespace
        + '\''
        + ", "
        + "optionsRules='"
        + optionsRules
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String key;

    protected String description;

    protected String displayName;

    protected String prompt;

    protected String type;

    protected List<AiExtractStructuredFieldsOptionsField> options;

    protected List<AiExtractSubField> fields;

    protected String taxonomyKey;

    protected String namespace;

    protected AiOptionsRules optionsRules;

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

    public Builder options(List<AiExtractStructuredFieldsOptionsField> options) {
      this.options = options;
      return this;
    }

    public Builder fields(List<AiExtractSubField> fields) {
      this.fields = fields;
      return this;
    }

    public Builder taxonomyKey(String taxonomyKey) {
      this.taxonomyKey = taxonomyKey;
      return this;
    }

    public Builder namespace(String namespace) {
      this.namespace = namespace;
      return this;
    }

    public Builder optionsRules(AiOptionsRules optionsRules) {
      this.optionsRules = optionsRules;
      return this;
    }

    public AiExtractStructuredFieldsField build() {
      return new AiExtractStructuredFieldsField(this);
    }
  }
}
