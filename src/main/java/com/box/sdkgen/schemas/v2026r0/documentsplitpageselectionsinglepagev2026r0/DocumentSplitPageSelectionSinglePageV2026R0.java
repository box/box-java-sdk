package com.box.sdkgen.schemas.v2026r0.documentsplitpageselectionsinglepagev2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** A page selection segment that includes a single page from the source document. */
@JsonFilter("nullablePropertyFilter")
public class DocumentSplitPageSelectionSinglePageV2026R0 extends SerializableObject {

  /** Discriminator. Must be `single_page` for this variant. */
  @JsonDeserialize(
      using =
          DocumentSplitPageSelectionSinglePageV2026R0TypeField
              .DocumentSplitPageSelectionSinglePageV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          DocumentSplitPageSelectionSinglePageV2026R0TypeField
              .DocumentSplitPageSelectionSinglePageV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<DocumentSplitPageSelectionSinglePageV2026R0TypeField> type;

  /** A single page number to include. */
  protected final int page;

  public DocumentSplitPageSelectionSinglePageV2026R0(@JsonProperty("page") int page) {
    super();
    this.page = page;
    this.type =
        new EnumWrapper<DocumentSplitPageSelectionSinglePageV2026R0TypeField>(
            DocumentSplitPageSelectionSinglePageV2026R0TypeField.SINGLE_PAGE);
  }

  protected DocumentSplitPageSelectionSinglePageV2026R0(Builder builder) {
    super();
    this.type = builder.type;
    this.page = builder.page;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<DocumentSplitPageSelectionSinglePageV2026R0TypeField> getType() {
    return type;
  }

  public int getPage() {
    return page;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocumentSplitPageSelectionSinglePageV2026R0 casted =
        (DocumentSplitPageSelectionSinglePageV2026R0) o;
    return Objects.equals(type, casted.type) && Objects.equals(page, casted.page);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, page);
  }

  @Override
  public String toString() {
    return "DocumentSplitPageSelectionSinglePageV2026R0{"
        + "type='"
        + type
        + '\''
        + ", "
        + "page='"
        + page
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<DocumentSplitPageSelectionSinglePageV2026R0TypeField> type;

    protected final int page;

    public Builder(int page) {
      super();
      this.page = page;
    }

    public Builder type(DocumentSplitPageSelectionSinglePageV2026R0TypeField type) {
      this.type = new EnumWrapper<DocumentSplitPageSelectionSinglePageV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<DocumentSplitPageSelectionSinglePageV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public DocumentSplitPageSelectionSinglePageV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<DocumentSplitPageSelectionSinglePageV2026R0TypeField>(
                DocumentSplitPageSelectionSinglePageV2026R0TypeField.SINGLE_PAGE);
      }
      return new DocumentSplitPageSelectionSinglePageV2026R0(this);
    }
  }
}
