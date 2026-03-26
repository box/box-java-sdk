package com.box.sdkgen.schemas.v2025r0.hubdocumentblockentryv2025r0;

import com.box.sdkgen.internal.OneOfFive;
import com.box.sdkgen.schemas.v2025r0.hubcalloutboxtextblockv2025r0.HubCalloutBoxTextBlockV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubdividerblockv2025r0.HubDividerBlockV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubitemlistblockv2025r0.HubItemListBlockV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubparagraphtextblockv2025r0.HubParagraphTextBlockV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubsectiontitletextblockv2025r0.HubSectionTitleTextBlockV2025R0;
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
    using = HubDocumentBlockEntryV2025R0.HubDocumentBlockEntryV2025R0Deserializer.class)
@JsonSerialize(using = OneOfFive.OneOfFiveSerializer.class)
public class HubDocumentBlockEntryV2025R0
    extends OneOfFive<
        HubParagraphTextBlockV2025R0,
        HubSectionTitleTextBlockV2025R0,
        HubCalloutBoxTextBlockV2025R0,
        HubItemListBlockV2025R0,
        HubDividerBlockV2025R0> {

  protected final String type;

  protected final String id;

  protected final String parentId;

  public HubDocumentBlockEntryV2025R0(HubParagraphTextBlockV2025R0 hubParagraphTextBlockV2025R0) {
    super(hubParagraphTextBlockV2025R0, null, null, null, null);
    this.type = EnumWrapper.convertToString(hubParagraphTextBlockV2025R0.getType());
    this.id = hubParagraphTextBlockV2025R0.getId();
    this.parentId = hubParagraphTextBlockV2025R0.getParentId();
  }

  public HubDocumentBlockEntryV2025R0(
      HubSectionTitleTextBlockV2025R0 hubSectionTitleTextBlockV2025R0) {
    super(null, hubSectionTitleTextBlockV2025R0, null, null, null);
    this.type = EnumWrapper.convertToString(hubSectionTitleTextBlockV2025R0.getType());
    this.id = hubSectionTitleTextBlockV2025R0.getId();
    this.parentId = hubSectionTitleTextBlockV2025R0.getParentId();
  }

  public HubDocumentBlockEntryV2025R0(HubCalloutBoxTextBlockV2025R0 hubCalloutBoxTextBlockV2025R0) {
    super(null, null, hubCalloutBoxTextBlockV2025R0, null, null);
    this.type = EnumWrapper.convertToString(hubCalloutBoxTextBlockV2025R0.getType());
    this.id = hubCalloutBoxTextBlockV2025R0.getId();
    this.parentId = hubCalloutBoxTextBlockV2025R0.getParentId();
  }

  public HubDocumentBlockEntryV2025R0(HubItemListBlockV2025R0 hubItemListBlockV2025R0) {
    super(null, null, null, hubItemListBlockV2025R0, null);
    this.type = EnumWrapper.convertToString(hubItemListBlockV2025R0.getType());
    this.id = hubItemListBlockV2025R0.getId();
    this.parentId = hubItemListBlockV2025R0.getParentId();
  }

  public HubDocumentBlockEntryV2025R0(HubDividerBlockV2025R0 hubDividerBlockV2025R0) {
    super(null, null, null, null, hubDividerBlockV2025R0);
    this.type = EnumWrapper.convertToString(hubDividerBlockV2025R0.getType());
    this.id = hubDividerBlockV2025R0.getId();
    this.parentId = hubDividerBlockV2025R0.getParentId();
  }

  public boolean isHubParagraphTextBlockV2025R0() {
    return value0 != null;
  }

  public HubParagraphTextBlockV2025R0 getHubParagraphTextBlockV2025R0() {
    return value0;
  }

  public boolean isHubSectionTitleTextBlockV2025R0() {
    return value1 != null;
  }

  public HubSectionTitleTextBlockV2025R0 getHubSectionTitleTextBlockV2025R0() {
    return value1;
  }

  public boolean isHubCalloutBoxTextBlockV2025R0() {
    return value2 != null;
  }

  public HubCalloutBoxTextBlockV2025R0 getHubCalloutBoxTextBlockV2025R0() {
    return value2;
  }

  public boolean isHubItemListBlockV2025R0() {
    return value3 != null;
  }

  public HubItemListBlockV2025R0 getHubItemListBlockV2025R0() {
    return value3;
  }

  public boolean isHubDividerBlockV2025R0() {
    return value4 != null;
  }

  public HubDividerBlockV2025R0 getHubDividerBlockV2025R0() {
    return value4;
  }

  public String getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  public String getParentId() {
    return parentId;
  }

  static class HubDocumentBlockEntryV2025R0Deserializer
      extends JsonDeserializer<HubDocumentBlockEntryV2025R0> {

    public HubDocumentBlockEntryV2025R0Deserializer() {
      super();
    }

    @Override
    public HubDocumentBlockEntryV2025R0 deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "paragraph":
            return new HubDocumentBlockEntryV2025R0(
                JsonManager.deserialize(node, HubParagraphTextBlockV2025R0.class));
          case "section_title":
            return new HubDocumentBlockEntryV2025R0(
                JsonManager.deserialize(node, HubSectionTitleTextBlockV2025R0.class));
          case "callout_box":
            return new HubDocumentBlockEntryV2025R0(
                JsonManager.deserialize(node, HubCalloutBoxTextBlockV2025R0.class));
          case "item_list":
            return new HubDocumentBlockEntryV2025R0(
                JsonManager.deserialize(node, HubItemListBlockV2025R0.class));
          case "divider":
            return new HubDocumentBlockEntryV2025R0(
                JsonManager.deserialize(node, HubDividerBlockV2025R0.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize HubDocumentBlockEntryV2025R0");
    }
  }
}
