package com.box.sdkgen.schemas.v2025r0.docgenjobbasev2025r0;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class DocGenJobBaseV2025R0 extends SerializableObject {

  protected final String id;

  @JsonDeserialize(
      using = DocGenJobBaseV2025R0TypeField.DocGenJobBaseV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using = DocGenJobBaseV2025R0TypeField.DocGenJobBaseV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<DocGenJobBaseV2025R0TypeField> type;

  public DocGenJobBaseV2025R0(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<DocGenJobBaseV2025R0TypeField>(DocGenJobBaseV2025R0TypeField.DOCGEN_JOB);
  }

  protected DocGenJobBaseV2025R0(DocGenJobBaseV2025R0Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<DocGenJobBaseV2025R0TypeField> getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocGenJobBaseV2025R0 casted = (DocGenJobBaseV2025R0) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "DocGenJobBaseV2025R0{" + "id='" + id + '\'' + ", " + "type='" + type + '\'' + "}";
  }

  public static class DocGenJobBaseV2025R0Builder {

    protected final String id;

    protected EnumWrapper<DocGenJobBaseV2025R0TypeField> type;

    public DocGenJobBaseV2025R0Builder(String id) {
      this.id = id;
      this.type =
          new EnumWrapper<DocGenJobBaseV2025R0TypeField>(DocGenJobBaseV2025R0TypeField.DOCGEN_JOB);
    }

    public DocGenJobBaseV2025R0Builder type(DocGenJobBaseV2025R0TypeField type) {
      this.type = new EnumWrapper<DocGenJobBaseV2025R0TypeField>(type);
      return this;
    }

    public DocGenJobBaseV2025R0Builder type(EnumWrapper<DocGenJobBaseV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public DocGenJobBaseV2025R0 build() {
      return new DocGenJobBaseV2025R0(this);
    }
  }
}
