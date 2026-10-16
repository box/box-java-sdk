package com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequesttypev2026r0;

import com.box.sdkgen.internal.OneOfThree;
import com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequestmanualv2026r0.DocumentSplitCreateRequestManualV2026R0;
import com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequestpageselectionv2026r0.DocumentSplitCreateRequestPageSelectionV2026R0;
import com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequestsmartv2026r0.DocumentSplitCreateRequestSmartV2026R0;
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
        DocumentSplitCreateRequestTypeV2026R0.DocumentSplitCreateRequestTypeV2026R0Deserializer
            .class)
@JsonSerialize(using = OneOfThree.OneOfThreeSerializer.class)
public class DocumentSplitCreateRequestTypeV2026R0
    extends OneOfThree<
        DocumentSplitCreateRequestManualV2026R0,
        DocumentSplitCreateRequestPageSelectionV2026R0,
        DocumentSplitCreateRequestSmartV2026R0> {

  protected final String splitType;

  public DocumentSplitCreateRequestTypeV2026R0(
      DocumentSplitCreateRequestManualV2026R0 documentSplitCreateRequestManualV2026R0) {
    super(documentSplitCreateRequestManualV2026R0, null, null);
    this.splitType =
        EnumWrapper.convertToString(documentSplitCreateRequestManualV2026R0.getSplitType());
  }

  public DocumentSplitCreateRequestTypeV2026R0(
      DocumentSplitCreateRequestPageSelectionV2026R0
          documentSplitCreateRequestPageSelectionV2026R0) {
    super(null, documentSplitCreateRequestPageSelectionV2026R0, null);
    this.splitType =
        EnumWrapper.convertToString(documentSplitCreateRequestPageSelectionV2026R0.getSplitType());
  }

  public DocumentSplitCreateRequestTypeV2026R0(
      DocumentSplitCreateRequestSmartV2026R0 documentSplitCreateRequestSmartV2026R0) {
    super(null, null, documentSplitCreateRequestSmartV2026R0);
    this.splitType =
        EnumWrapper.convertToString(documentSplitCreateRequestSmartV2026R0.getSplitType());
  }

  public boolean isDocumentSplitCreateRequestManualV2026R0() {
    return value0 != null;
  }

  public DocumentSplitCreateRequestManualV2026R0 getDocumentSplitCreateRequestManualV2026R0() {
    return value0;
  }

  public boolean isDocumentSplitCreateRequestPageSelectionV2026R0() {
    return value1 != null;
  }

  public DocumentSplitCreateRequestPageSelectionV2026R0
      getDocumentSplitCreateRequestPageSelectionV2026R0() {
    return value1;
  }

  public boolean isDocumentSplitCreateRequestSmartV2026R0() {
    return value2 != null;
  }

  public DocumentSplitCreateRequestSmartV2026R0 getDocumentSplitCreateRequestSmartV2026R0() {
    return value2;
  }

  public String getSplitType() {
    return splitType;
  }

  static class DocumentSplitCreateRequestTypeV2026R0Deserializer
      extends JsonDeserializer<DocumentSplitCreateRequestTypeV2026R0> {

    public DocumentSplitCreateRequestTypeV2026R0Deserializer() {
      super();
    }

    @Override
    public DocumentSplitCreateRequestTypeV2026R0 deserialize(
        JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      try {
        return new DocumentSplitCreateRequestTypeV2026R0(
            OneOfThree.OBJECT_MAPPER.convertValue(
                node, DocumentSplitCreateRequestManualV2026R0.class));
      } catch (Exception ignored) {
      }
      try {
        return new DocumentSplitCreateRequestTypeV2026R0(
            OneOfThree.OBJECT_MAPPER.convertValue(
                node, DocumentSplitCreateRequestPageSelectionV2026R0.class));
      } catch (Exception ignored) {
      }
      try {
        return new DocumentSplitCreateRequestTypeV2026R0(
            OneOfThree.OBJECT_MAPPER.convertValue(
                node, DocumentSplitCreateRequestSmartV2026R0.class));
      } catch (Exception ignored) {
      }
      throw new JsonMappingException(
          jp, "Unable to deserialize DocumentSplitCreateRequestTypeV2026R0");
    }
  }
}
