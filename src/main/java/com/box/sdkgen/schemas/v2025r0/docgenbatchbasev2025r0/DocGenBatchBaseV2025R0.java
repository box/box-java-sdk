package com.box.sdkgen.schemas.v2025r0.docgenbatchbasev2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * The basic representation of a Box Doc Gen batch object. A Box Doc Gen batch contains one or more
 * Box Doc Gen jobs.
 */
@JsonFilter("nullablePropertyFilter")
public class DocGenBatchBaseV2025R0 extends SerializableObject {

  /** The unique identifier that represents a Box Doc Gen batch. */
  protected final String id;

  /** The value will always be `docgen_batch`. */
  @JsonDeserialize(
      using = DocGenBatchBaseV2025R0TypeField.DocGenBatchBaseV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using = DocGenBatchBaseV2025R0TypeField.DocGenBatchBaseV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<DocGenBatchBaseV2025R0TypeField> type;

  public DocGenBatchBaseV2025R0(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<DocGenBatchBaseV2025R0TypeField>(
            DocGenBatchBaseV2025R0TypeField.DOCGEN_BATCH);
  }

  protected DocGenBatchBaseV2025R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<DocGenBatchBaseV2025R0TypeField> getType() {
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
    DocGenBatchBaseV2025R0 casted = (DocGenBatchBaseV2025R0) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "DocGenBatchBaseV2025R0{" + "id='" + id + '\'' + ", " + "type='" + type + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<DocGenBatchBaseV2025R0TypeField> type;

    public Builder(String id) {
      super();
      this.id = id;
    }

    public Builder type(DocGenBatchBaseV2025R0TypeField type) {
      this.type = new EnumWrapper<DocGenBatchBaseV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<DocGenBatchBaseV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public DocGenBatchBaseV2025R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<DocGenBatchBaseV2025R0TypeField>(
                DocGenBatchBaseV2025R0TypeField.DOCGEN_BATCH);
      }
      return new DocGenBatchBaseV2025R0(this);
    }
  }
}
