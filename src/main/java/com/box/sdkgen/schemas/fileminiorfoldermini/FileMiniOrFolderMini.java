package com.box.sdkgen.schemas.fileminiorfoldermini;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.schemas.filemini.FileMini;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;

@JsonDeserialize(using = FileMiniOrFolderMini.FileMiniOrFolderMiniDeserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class FileMiniOrFolderMini extends OneOfTwo<FileMini, FolderMini> {

  public FileMiniOrFolderMini(FileMini fileMini) {
    super(fileMini, null);
  }

  public FileMiniOrFolderMini(FolderMini folderMini) {
    super(null, folderMini);
  }

  public FileMini getFileMini() {
    return value0;
  }

  public FolderMini getFolderMini() {
    return value1;
  }

  static class FileMiniOrFolderMiniDeserializer extends JsonDeserializer<FileMiniOrFolderMini> {

    public FileMiniOrFolderMiniDeserializer() {
      super();
    }

    @Override
    public FileMiniOrFolderMini deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "file":
            return new FileMiniOrFolderMini(JsonManager.deserialize(node, FileMini.class));
          case "folder":
            return new FileMiniOrFolderMini(JsonManager.deserialize(node, FolderMini.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize FileMiniOrFolderMini");
    }
  }
}
