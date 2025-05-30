package com.box.sdkgen.schemas.fileorfolder;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.schemas.file.File;
import com.box.sdkgen.schemas.folder.Folder;
import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;

@JsonDeserialize(using = FileOrFolder.FileOrFolderDeserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class FileOrFolder extends OneOfTwo<File, Folder> {

  public FileOrFolder(File file) {
    super(file, null);
  }

  public FileOrFolder(Folder folder) {
    super(null, folder);
  }

  public File getFile() {
    return value0;
  }

  public Folder getFolder() {
    return value1;
  }

  static class FileOrFolderDeserializer extends JsonDeserializer<FileOrFolder> {

    public FileOrFolderDeserializer() {
      super();
    }

    @Override
    public FileOrFolder deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "file":
            return new FileOrFolder(JsonManager.deserialize(node, File.class));
          case "folder":
            return new FileOrFolder(JsonManager.deserialize(node, Folder.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize FileOrFolder");
    }
  }
}
