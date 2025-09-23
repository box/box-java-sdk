package com.box.sdkgen.schemas.appitemassociateditem;

import com.box.sdkgen.internal.OneOfThree;
import com.box.sdkgen.schemas.filebase.FileBase;
import com.box.sdkgen.schemas.folderbase.FolderBase;
import com.box.sdkgen.schemas.weblinkbase.WebLinkBase;
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

@JsonDeserialize(using = AppItemAssociatedItem.AppItemAssociatedItemDeserializer.class)
@JsonSerialize(using = OneOfThree.OneOfThreeSerializer.class)
public class AppItemAssociatedItem extends OneOfThree<FileBase, FolderBase, WebLinkBase> {

  protected final String id;

  protected final String etag;

  protected final String type;

  public AppItemAssociatedItem(FileBase fileBase) {
    super(fileBase, null, null);
    this.id = fileBase.getId();
    this.etag = fileBase.getEtag();
    this.type = EnumWrapper.convertToString(fileBase.getType());
  }

  public AppItemAssociatedItem(FolderBase folderBase) {
    super(null, folderBase, null);
    this.id = folderBase.getId();
    this.etag = folderBase.getEtag();
    this.type = EnumWrapper.convertToString(folderBase.getType());
  }

  public AppItemAssociatedItem(WebLinkBase webLinkBase) {
    super(null, null, webLinkBase);
    this.id = webLinkBase.getId();
    this.etag = webLinkBase.getEtag();
    this.type = EnumWrapper.convertToString(webLinkBase.getType());
  }

  public boolean isFileBase() {
    return value0 != null;
  }

  public FileBase getFileBase() {
    return value0;
  }

  public boolean isFolderBase() {
    return value1 != null;
  }

  public FolderBase getFolderBase() {
    return value1;
  }

  public boolean isWebLinkBase() {
    return value2 != null;
  }

  public WebLinkBase getWebLinkBase() {
    return value2;
  }

  public String getId() {
    return id;
  }

  public String getEtag() {
    return etag;
  }

  public String getType() {
    return type;
  }

  static class AppItemAssociatedItemDeserializer extends JsonDeserializer<AppItemAssociatedItem> {

    public AppItemAssociatedItemDeserializer() {
      super();
    }

    @Override
    public AppItemAssociatedItem deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "file":
            return new AppItemAssociatedItem(JsonManager.deserialize(node, FileBase.class));
          case "folder":
            return new AppItemAssociatedItem(JsonManager.deserialize(node, FolderBase.class));
          case "web_link":
            return new AppItemAssociatedItem(JsonManager.deserialize(node, WebLinkBase.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize AppItemAssociatedItem");
    }
  }
}
