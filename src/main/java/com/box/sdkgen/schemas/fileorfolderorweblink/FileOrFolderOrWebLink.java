package com.box.sdkgen.schemas.fileorfolderorweblink;

import com.box.sdkgen.internal.OneOfThree;
import com.box.sdkgen.schemas.file.File;
import com.box.sdkgen.schemas.folder.Folder;
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

@JsonDeserialize(using = FileOrFolderOrWebLink.FileOrFolderOrWebLinkDeserializer.class)
@JsonSerialize(using = OneOfThree.OneOfThreeSerializer.class)
public class FileOrFolderOrWebLink extends OneOfThree<File, Folder, WebLink> {

  public FileOrFolderOrWebLink(File file) {
    super(file, null, null);
  }

  public FileOrFolderOrWebLink(Folder folder) {
    super(null, folder, null);
  }

  public FileOrFolderOrWebLink(WebLink webLink) {
    super(null, null, webLink);
  }

  public File getFile() {
    return value0;
  }

  public Folder getFolder() {
    return value1;
  }

  public WebLink getWebLink() {
    return value2;
  }

  static class FileOrFolderOrWebLinkDeserializer extends JsonDeserializer<FileOrFolderOrWebLink> {

    public FileOrFolderOrWebLinkDeserializer() {
      super();
    }

    @Override
    public FileOrFolderOrWebLink deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "file":
            return new FileOrFolderOrWebLink(JsonManager.deserialize(node, File.class));
          case "folder":
            return new FileOrFolderOrWebLink(JsonManager.deserialize(node, Folder.class));
          case "web_link":
            return new FileOrFolderOrWebLink(JsonManager.deserialize(node, WebLink.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize FileOrFolderOrWebLink");
    }
  }
}
