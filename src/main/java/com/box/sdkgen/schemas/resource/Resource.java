package com.box.sdkgen.schemas.resource;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.schemas.filemini.FileMini;
import com.box.sdkgen.schemas.foldermini.FolderMini;
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

@JsonDeserialize(using = Resource.ResourceDeserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class Resource extends OneOfTwo<FolderMini, FileMini> {

  protected final String sequenceId;

  protected final String name;

  protected final String id;

  protected final String etag;

  protected final String type;

  public Resource(FolderMini folderMini) {
    super(folderMini, null);
    this.sequenceId = folderMini.getSequenceId();
    this.name = folderMini.getName();
    this.id = folderMini.getId();
    this.etag = folderMini.getEtag();
    this.type = EnumWrapper.convertToString(folderMini.getType());
  }

  public Resource(FileMini fileMini) {
    super(null, fileMini);
    this.sequenceId = fileMini.getSequenceId();
    this.name = fileMini.getName();
    this.id = fileMini.getId();
    this.etag = fileMini.getEtag();
    this.type = EnumWrapper.convertToString(fileMini.getType());
  }

  public boolean isFolderMini() {
    return value0 != null;
  }

  public FolderMini getFolderMini() {
    return value0;
  }

  public boolean isFileMini() {
    return value1 != null;
  }

  public FileMini getFileMini() {
    return value1;
  }

  public String getSequenceId() {
    return sequenceId;
  }

  public String getName() {
    return name;
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

  static class ResourceDeserializer extends JsonDeserializer<Resource> {

    public ResourceDeserializer() {
      super();
    }

    @Override
    public Resource deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "folder":
            return new Resource(JsonManager.deserialize(node, FolderMini.class));
          case "file":
            return new Resource(JsonManager.deserialize(node, FileMini.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize Resource");
    }
  }
}
