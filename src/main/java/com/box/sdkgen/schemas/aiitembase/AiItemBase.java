package com.box.sdkgen.schemas.aiitembase;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** The item to be processed by the LLM. */
@JsonFilter("nullablePropertyFilter")
public class AiItemBase extends SerializableObject {

  /** The ID of the file. */
  protected final String id;

  /** The type of the item. Currently the value can be `file` only. */
  @JsonDeserialize(using = AiItemBaseTypeField.AiItemBaseTypeFieldDeserializer.class)
  @JsonSerialize(using = AiItemBaseTypeField.AiItemBaseTypeFieldSerializer.class)
  protected EnumWrapper<AiItemBaseTypeField> type;

  /** The content of the item, often the text representation. */
  protected String content;

  public AiItemBase(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type = new EnumWrapper<AiItemBaseTypeField>(AiItemBaseTypeField.FILE);
  }

  protected AiItemBase(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.content = builder.content;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<AiItemBaseTypeField> getType() {
    return type;
  }

  public String getContent() {
    return content;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiItemBase casted = (AiItemBase) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(content, casted.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, content);
  }

  @Override
  public String toString() {
    return "AiItemBase{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "content='"
        + content
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<AiItemBaseTypeField> type;

    protected String content;

    public Builder(String id) {
      super();
      this.id = id;
      this.type = new EnumWrapper<AiItemBaseTypeField>(AiItemBaseTypeField.FILE);
    }

    public Builder type(AiItemBaseTypeField type) {
      this.type = new EnumWrapper<AiItemBaseTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<AiItemBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder content(String content) {
      this.content = content;
      return this;
    }

    public AiItemBase build() {
      return new AiItemBase(this);
    }
  }
}
