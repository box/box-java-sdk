package com.box.sdkgen.schemas.filefullorfolderminiorweblink;

import com.box.sdkgen.internal.OneOfThree;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.weblink.WebLink;
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
    using = FileFullOrFolderMiniOrWebLink.FileFullOrFolderMiniOrWebLinkDeserializer.class)
@JsonSerialize(using = OneOfThree.OneOfThreeSerializer.class)
public class FileFullOrFolderMiniOrWebLink extends OneOfThree<FileFull, FolderMini, WebLink> {

  public FileFullOrFolderMiniOrWebLink(FileFull fileFull) {
    super(fileFull, null, null);
  }

  public FileFullOrFolderMiniOrWebLink(FolderMini folderMini) {
    super(null, folderMini, null);
  }

  public FileFullOrFolderMiniOrWebLink(WebLink webLink) {
    super(null, null, webLink);
  }

  public FileFull getFileFull() {
    return value0;
  }

  public FolderMini getFolderMini() {
    return value1;
  }

  public WebLink getWebLink() {
    return value2;
  }

  static class FileFullOrFolderMiniOrWebLinkDeserializer
      extends JsonDeserializer<FileFullOrFolderMiniOrWebLink> {

    public FileFullOrFolderMiniOrWebLinkDeserializer() {
      super();
    }

    @Override
    public FileFullOrFolderMiniOrWebLink deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "file":
            return new FileFullOrFolderMiniOrWebLink(JsonManager.deserialize(node, FileFull.class));
          case "folder":
            return new FileFullOrFolderMiniOrWebLink(
                JsonManager.deserialize(node, FolderMini.class));
          case "web_link":
            return new FileFullOrFolderMiniOrWebLink(JsonManager.deserialize(node, WebLink.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize FileFullOrFolderMiniOrWebLink");
    }
  }
}
