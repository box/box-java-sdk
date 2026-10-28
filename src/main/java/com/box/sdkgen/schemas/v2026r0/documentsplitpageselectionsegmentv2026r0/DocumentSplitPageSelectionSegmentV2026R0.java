package com.box.sdkgen.schemas.v2026r0.documentsplitpageselectionsegmentv2026r0;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.schemas.v2026r0.documentsplitpageselectionpagerangev2026r0.DocumentSplitPageSelectionPageRangeV2026R0;
import com.box.sdkgen.schemas.v2026r0.documentsplitpageselectionsinglepagev2026r0.DocumentSplitPageSelectionSinglePageV2026R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;

@JsonDeserialize(
    using =
        DocumentSplitPageSelectionSegmentV2026R0
            .DocumentSplitPageSelectionSegmentV2026R0Deserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class DocumentSplitPageSelectionSegmentV2026R0
    extends OneOfTwo<
        DocumentSplitPageSelectionSinglePageV2026R0, DocumentSplitPageSelectionPageRangeV2026R0> {

  protected final String type;

  public DocumentSplitPageSelectionSegmentV2026R0(
      DocumentSplitPageSelectionSinglePageV2026R0 documentSplitPageSelectionSinglePageV2026R0) {
    super(documentSplitPageSelectionSinglePageV2026R0, null);
    this.type = EnumWrapper.convertToString(documentSplitPageSelectionSinglePageV2026R0.getType());
  }

  public DocumentSplitPageSelectionSegmentV2026R0(
      DocumentSplitPageSelectionPageRangeV2026R0 documentSplitPageSelectionPageRangeV2026R0) {
    super(null, documentSplitPageSelectionPageRangeV2026R0);
    this.type = EnumWrapper.convertToString(documentSplitPageSelectionPageRangeV2026R0.getType());
  }

  public boolean isDocumentSplitPageSelectionSinglePageV2026R0() {
    return value0 != null;
  }

  public DocumentSplitPageSelectionSinglePageV2026R0
      getDocumentSplitPageSelectionSinglePageV2026R0() {
    return value0;
  }

  public boolean isDocumentSplitPageSelectionPageRangeV2026R0() {
    return value1 != null;
  }

  public DocumentSplitPageSelectionPageRangeV2026R0
      getDocumentSplitPageSelectionPageRangeV2026R0() {
    return value1;
  }

  public String getType() {
    return type;
  }

  static class DocumentSplitPageSelectionSegmentV2026R0Deserializer
      extends JsonDeserializer<DocumentSplitPageSelectionSegmentV2026R0> {

    public DocumentSplitPageSelectionSegmentV2026R0Deserializer() {
      super();
    }

    @Override
    public DocumentSplitPageSelectionSegmentV2026R0 deserialize(
        JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "single_page":
            return new DocumentSplitPageSelectionSegmentV2026R0(
                JsonManager.deserialize(node, DocumentSplitPageSelectionSinglePageV2026R0.class));
          case "page_range":
            return new DocumentSplitPageSelectionSegmentV2026R0(
                JsonManager.deserialize(node, DocumentSplitPageSelectionPageRangeV2026R0.class));
        }
      }
      throw new JsonMappingException(
          jp, "Unable to deserialize DocumentSplitPageSelectionSegmentV2026R0");
    }
  }
}
