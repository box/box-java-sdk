package com.box.sdkgen.schemas.aicitation;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class AiCitation extends SerializableObject {

  protected String content;

  protected String id;

  @JsonDeserialize(using = AiCitationTypeField.AiCitationTypeFieldDeserializer.class)
  @JsonSerialize(using = AiCitationTypeField.AiCitationTypeFieldSerializer.class)
  protected EnumWrapper<AiCitationTypeField> type;

  protected String name;

  public AiCitation() {
    super();
  }

  protected AiCitation(AiCitationBuilder builder) {
    super();
    this.content = builder.content;
    this.id = builder.id;
    this.type = builder.type;
    this.name = builder.name;
  }

  public String getContent() {
    return content;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<AiCitationTypeField> getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiCitation casted = (AiCitation) o;
    return Objects.equals(content, casted.content)
        && Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, id, type, name);
  }

  @Override
  public String toString() {
    return "AiCitation{"
        + "content='"
        + content
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + "}";
  }

  public static class AiCitationBuilder {

    protected String content;

    protected String id;

    protected EnumWrapper<AiCitationTypeField> type;

    protected String name;

    public AiCitationBuilder content(String content) {
      this.content = content;
      return this;
    }

    public AiCitationBuilder id(String id) {
      this.id = id;
      return this;
    }

    public AiCitationBuilder type(AiCitationTypeField type) {
      this.type = new EnumWrapper<AiCitationTypeField>(type);
      return this;
    }

    public AiCitationBuilder type(EnumWrapper<AiCitationTypeField> type) {
      this.type = type;
      return this;
    }

    public AiCitationBuilder name(String name) {
      this.name = name;
      return this;
    }

    public AiCitation build() {
      return new AiCitation(this);
    }
  }
}
