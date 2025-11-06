package com.box.sdkgen.schemas.v2025r0.hubitemreferencev2025r0;

import com.box.sdkgen.internal.OneOfThree;
import com.box.sdkgen.schemas.v2025r0.filereferencev2025r0.FileReferenceV2025R0;
import com.box.sdkgen.schemas.v2025r0.folderreferencev2025r0.FolderReferenceV2025R0;
import com.box.sdkgen.schemas.v2025r0.weblinkreferencev2025r0.WeblinkReferenceV2025R0;
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

@JsonDeserialize(using = HubItemReferenceV2025R0.HubItemReferenceV2025R0Deserializer.class)
@JsonSerialize(using = OneOfThree.OneOfThreeSerializer.class)
public class HubItemReferenceV2025R0
    extends OneOfThree<FileReferenceV2025R0, FolderReferenceV2025R0, WeblinkReferenceV2025R0> {

  protected final String type;

  protected final String id;

  public HubItemReferenceV2025R0(FileReferenceV2025R0 fileReferenceV2025R0) {
    super(fileReferenceV2025R0, null, null);
    this.type = EnumWrapper.convertToString(fileReferenceV2025R0.getType());
    this.id = fileReferenceV2025R0.getId();
  }

  public HubItemReferenceV2025R0(FolderReferenceV2025R0 folderReferenceV2025R0) {
    super(null, folderReferenceV2025R0, null);
    this.type = EnumWrapper.convertToString(folderReferenceV2025R0.getType());
    this.id = folderReferenceV2025R0.getId();
  }

  public HubItemReferenceV2025R0(WeblinkReferenceV2025R0 weblinkReferenceV2025R0) {
    super(null, null, weblinkReferenceV2025R0);
    this.type = EnumWrapper.convertToString(weblinkReferenceV2025R0.getType());
    this.id = weblinkReferenceV2025R0.getId();
  }

  public boolean isFileReferenceV2025R0() {
    return value0 != null;
  }

  public FileReferenceV2025R0 getFileReferenceV2025R0() {
    return value0;
  }

  public boolean isFolderReferenceV2025R0() {
    return value1 != null;
  }

  public FolderReferenceV2025R0 getFolderReferenceV2025R0() {
    return value1;
  }

  public boolean isWeblinkReferenceV2025R0() {
    return value2 != null;
  }

  public WeblinkReferenceV2025R0 getWeblinkReferenceV2025R0() {
    return value2;
  }

  public String getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  static class HubItemReferenceV2025R0Deserializer
      extends JsonDeserializer<HubItemReferenceV2025R0> {

    public HubItemReferenceV2025R0Deserializer() {
      super();
    }

    @Override
    public HubItemReferenceV2025R0 deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "file":
            return new HubItemReferenceV2025R0(
                JsonManager.deserialize(node, FileReferenceV2025R0.class));
          case "folder":
            return new HubItemReferenceV2025R0(
                JsonManager.deserialize(node, FolderReferenceV2025R0.class));
          case "web_link":
            return new HubItemReferenceV2025R0(
                JsonManager.deserialize(node, WeblinkReferenceV2025R0.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize HubItemReferenceV2025R0");
    }
  }
}
