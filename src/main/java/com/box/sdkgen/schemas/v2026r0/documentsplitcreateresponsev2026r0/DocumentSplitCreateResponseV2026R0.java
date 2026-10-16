package com.box.sdkgen.schemas.v2026r0.documentsplitcreateresponsev2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * A reference to a newly created Document Split job. Returned when a Document Split job is accepted
 * for asynchronous processing. Clients can poll the full job representation using this ID.
 */
@JsonFilter("nullablePropertyFilter")
public class DocumentSplitCreateResponseV2026R0 extends SerializableObject {

  /** The value will always be `document_split`. */
  @JsonDeserialize(
      using =
          DocumentSplitCreateResponseV2026R0TypeField
              .DocumentSplitCreateResponseV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          DocumentSplitCreateResponseV2026R0TypeField
              .DocumentSplitCreateResponseV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<DocumentSplitCreateResponseV2026R0TypeField> type;

  /** The unique identifier of the created Document Split job. */
  protected final String id;

  public DocumentSplitCreateResponseV2026R0(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<DocumentSplitCreateResponseV2026R0TypeField>(
            DocumentSplitCreateResponseV2026R0TypeField.DOCUMENT_SPLIT);
  }

  protected DocumentSplitCreateResponseV2026R0(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<DocumentSplitCreateResponseV2026R0TypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocumentSplitCreateResponseV2026R0 casted = (DocumentSplitCreateResponseV2026R0) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "DocumentSplitCreateResponseV2026R0{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<DocumentSplitCreateResponseV2026R0TypeField> type;

    protected final String id;

    public Builder(String id) {
      super();
      this.id = id;
    }

    public Builder type(DocumentSplitCreateResponseV2026R0TypeField type) {
      this.type = new EnumWrapper<DocumentSplitCreateResponseV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<DocumentSplitCreateResponseV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public DocumentSplitCreateResponseV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<DocumentSplitCreateResponseV2026R0TypeField>(
                DocumentSplitCreateResponseV2026R0TypeField.DOCUMENT_SPLIT);
      }
      return new DocumentSplitCreateResponseV2026R0(this);
    }
  }
}
