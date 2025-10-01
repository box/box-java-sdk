package com.box.sdkgen.schemas.aiitemask;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** The item to be processed by the LLM for ask requests. */
@JsonFilter("nullablePropertyFilter")
public class AiItemAsk extends SerializableObject {

  /** The ID of the file. */
  protected final String id;

  /** The type of the item. A `hubs` item must be used as a single item. */
  @JsonDeserialize(using = AiItemAskTypeField.AiItemAskTypeFieldDeserializer.class)
  @JsonSerialize(using = AiItemAskTypeField.AiItemAskTypeFieldSerializer.class)
  protected final EnumWrapper<AiItemAskTypeField> type;

  /** The content of the item, often the text representation. */
  protected String content;

  public AiItemAsk(String id, AiItemAskTypeField type) {
    super();
    this.id = id;
    this.type = new EnumWrapper<AiItemAskTypeField>(type);
  }

  public AiItemAsk(
      @JsonProperty("id") String id, @JsonProperty("type") EnumWrapper<AiItemAskTypeField> type) {
    super();
    this.id = id;
    this.type = type;
  }

  protected AiItemAsk(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.content = builder.content;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<AiItemAskTypeField> getType() {
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
    AiItemAsk casted = (AiItemAsk) o;
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
    return "AiItemAsk{"
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

    protected final EnumWrapper<AiItemAskTypeField> type;

    protected String content;

    public Builder(String id, AiItemAskTypeField type) {
      super();
      this.id = id;
      this.type = new EnumWrapper<AiItemAskTypeField>(type);
    }

    public Builder(String id, EnumWrapper<AiItemAskTypeField> type) {
      super();
      this.id = id;
      this.type = type;
    }

    public Builder content(String content) {
      this.content = content;
      return this;
    }

    public AiItemAsk build() {
      return new AiItemAsk(this);
    }
  }
}
