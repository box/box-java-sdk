package com.box.sdkgen.schemas.filefullorfolderfull;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;

@JsonDeserialize(using = FileFullOrFolderFull.FileFullOrFolderFullDeserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class FileFullOrFolderFull extends OneOfTwo<FileFull, FolderFull> {

  public FileFullOrFolderFull(FileFull fileFull) {
    super(fileFull, null);
  }

  public FileFullOrFolderFull(FolderFull folderFull) {
    super(null, folderFull);
  }

  public FileFull getFileFull() {
    return value0;
  }

  public FolderFull getFolderFull() {
    return value1;
  }

  static class FileFullOrFolderFullDeserializer extends JsonDeserializer<FileFullOrFolderFull> {

    public FileFullOrFolderFullDeserializer() {
      super();
    }

    @Override
    public FileFullOrFolderFull deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "file":
            return new FileFullOrFolderFull(JsonManager.deserialize(node, FileFull.class));
          case "folder":
            return new FileFullOrFolderFull(JsonManager.deserialize(node, FolderFull.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize FileFullOrFolderFull");
    }
  }
}
