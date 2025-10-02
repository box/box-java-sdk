package com.box.sdkgen.schemas.aitextgen;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class AiTextGenItemsField extends SerializableObject {

  /** The ID of the item. */
  protected final String id;

  /** The type of the item. */
  @JsonDeserialize(using = AiTextGenItemsTypeField.AiTextGenItemsTypeFieldDeserializer.class)
  @JsonSerialize(using = AiTextGenItemsTypeField.AiTextGenItemsTypeFieldSerializer.class)
  protected EnumWrapper<AiTextGenItemsTypeField> type;

  /** The content to use as context for generating new text or editing existing text. */
  protected String content;

  public AiTextGenItemsField(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type = new EnumWrapper<AiTextGenItemsTypeField>(AiTextGenItemsTypeField.FILE);
  }

  protected AiTextGenItemsField(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.content = builder.content;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<AiTextGenItemsTypeField> getType() {
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
    AiTextGenItemsField casted = (AiTextGenItemsField) o;
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
    return "AiTextGenItemsField{"
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

    protected EnumWrapper<AiTextGenItemsTypeField> type;

    protected String content;

    public Builder(String id) {
      super();
      this.id = id;
      this.type = new EnumWrapper<AiTextGenItemsTypeField>(AiTextGenItemsTypeField.FILE);
    }

    public Builder type(AiTextGenItemsTypeField type) {
      this.type = new EnumWrapper<AiTextGenItemsTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<AiTextGenItemsTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder content(String content) {
      this.content = content;
      return this;
    }

    public AiTextGenItemsField build() {
      return new AiTextGenItemsField(this);
    }
  }
}
