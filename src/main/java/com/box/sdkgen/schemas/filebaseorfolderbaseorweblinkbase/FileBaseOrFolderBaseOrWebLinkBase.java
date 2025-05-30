package com.box.sdkgen.schemas.filebaseorfolderbaseorweblinkbase;

import com.box.sdkgen.internal.OneOfThree;
import com.box.sdkgen.schemas.filebase.FileBase;
import com.box.sdkgen.schemas.folderbase.FolderBase;
import com.box.sdkgen.schemas.weblinkbase.WebLinkBase;
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
    using = FileBaseOrFolderBaseOrWebLinkBase.FileBaseOrFolderBaseOrWebLinkBaseDeserializer.class)
@JsonSerialize(using = OneOfThree.OneOfThreeSerializer.class)
public class FileBaseOrFolderBaseOrWebLinkBase
    extends OneOfThree<FileBase, FolderBase, WebLinkBase> {

  public FileBaseOrFolderBaseOrWebLinkBase(FileBase fileBase) {
    super(fileBase, null, null);
  }

  public FileBaseOrFolderBaseOrWebLinkBase(FolderBase folderBase) {
    super(null, folderBase, null);
  }

  public FileBaseOrFolderBaseOrWebLinkBase(WebLinkBase webLinkBase) {
    super(null, null, webLinkBase);
  }

  public FileBase getFileBase() {
    return value0;
  }

  public FolderBase getFolderBase() {
    return value1;
  }

  public WebLinkBase getWebLinkBase() {
    return value2;
  }

  static class FileBaseOrFolderBaseOrWebLinkBaseDeserializer
      extends JsonDeserializer<FileBaseOrFolderBaseOrWebLinkBase> {

    public FileBaseOrFolderBaseOrWebLinkBaseDeserializer() {
      super();
    }

    @Override
    public FileBaseOrFolderBaseOrWebLinkBase deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "file":
            return new FileBaseOrFolderBaseOrWebLinkBase(
                JsonManager.deserialize(node, FileBase.class));
          case "folder":
            return new FileBaseOrFolderBaseOrWebLinkBase(
                JsonManager.deserialize(node, FolderBase.class));
          case "web_link":
            return new FileBaseOrFolderBaseOrWebLinkBase(
                JsonManager.deserialize(node, WebLinkBase.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize FileBaseOrFolderBaseOrWebLinkBase");
    }
  }
}
