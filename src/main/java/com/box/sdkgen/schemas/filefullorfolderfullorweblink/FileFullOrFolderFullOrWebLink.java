package com.box.sdkgen.schemas.filefullorfolderfullorweblink;

import com.box.sdkgen.internal.OneOfThree;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.folderfull.FolderFull;
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
    using = FileFullOrFolderFullOrWebLink.FileFullOrFolderFullOrWebLinkDeserializer.class)
@JsonSerialize(using = OneOfThree.OneOfThreeSerializer.class)
public class FileFullOrFolderFullOrWebLink extends OneOfThree<FileFull, FolderFull, WebLink> {

  public FileFullOrFolderFullOrWebLink(FileFull fileFull) {
    super(fileFull, null, null);
  }

  public FileFullOrFolderFullOrWebLink(FolderFull folderFull) {
    super(null, folderFull, null);
  }

  public FileFullOrFolderFullOrWebLink(WebLink webLink) {
    super(null, null, webLink);
  }

  public FileFull getFileFull() {
    return value0;
  }

  public FolderFull getFolderFull() {
    return value1;
  }

  public WebLink getWebLink() {
    return value2;
  }

  static class FileFullOrFolderFullOrWebLinkDeserializer
      extends JsonDeserializer<FileFullOrFolderFullOrWebLink> {

    public FileFullOrFolderFullOrWebLinkDeserializer() {
      super();
    }

    @Override
    public FileFullOrFolderFullOrWebLink deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "file":
            return new FileFullOrFolderFullOrWebLink(JsonManager.deserialize(node, FileFull.class));
          case "folder":
            return new FileFullOrFolderFullOrWebLink(
                JsonManager.deserialize(node, FolderFull.class));
          case "web_link":
            return new FileFullOrFolderFullOrWebLink(JsonManager.deserialize(node, WebLink.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize FileFullOrFolderFullOrWebLink");
    }
  }
}
