package com.box.sdkgen.schemas.aitextgen;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class AiTextGenItemsField extends SerializableObject {

  protected final String id;

  @JsonDeserialize(using = AiTextGenItemsTypeField.AiTextGenItemsTypeFieldDeserializer.class)
  @JsonSerialize(using = AiTextGenItemsTypeField.AiTextGenItemsTypeFieldSerializer.class)
  protected EnumWrapper<AiTextGenItemsTypeField> type;

  protected String content;

  public AiTextGenItemsField(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type = new EnumWrapper<AiTextGenItemsTypeField>(AiTextGenItemsTypeField.FILE);
  }

  protected AiTextGenItemsField(AiTextGenItemsFieldBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.content = builder.content;
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

  public static class AiTextGenItemsFieldBuilder {

    protected final String id;

    protected EnumWrapper<AiTextGenItemsTypeField> type;

    protected String content;

    public AiTextGenItemsFieldBuilder(String id) {
      this.id = id;
      this.type = new EnumWrapper<AiTextGenItemsTypeField>(AiTextGenItemsTypeField.FILE);
    }

    public AiTextGenItemsFieldBuilder type(AiTextGenItemsTypeField type) {
      this.type = new EnumWrapper<AiTextGenItemsTypeField>(type);
      return this;
    }

    public AiTextGenItemsFieldBuilder type(EnumWrapper<AiTextGenItemsTypeField> type) {
      this.type = type;
      return this;
    }

    public AiTextGenItemsFieldBuilder content(String content) {
      this.content = content;
      return this;
    }

    public AiTextGenItemsField build() {
      return new AiTextGenItemsField(this);
    }
  }
}
