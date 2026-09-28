package com.box.sdkgen.schemas.collaborationitem;

import com.box.sdkgen.internal.OneOfThree;
import com.box.sdkgen.schemas.filemini.FileMini;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.weblinkmini.WebLinkMini;
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

@JsonDeserialize(using = CollaborationItem.CollaborationItemDeserializer.class)
@JsonSerialize(using = OneOfThree.OneOfThreeSerializer.class)
public class CollaborationItem extends OneOfThree<FileMini, FolderMini, WebLinkMini> {

  protected final String sequenceId;

  protected final String name;

  protected final String id;

  protected final String etag;

  protected final String type;

  public CollaborationItem(FileMini fileMini) {
    super(fileMini, null, null);
    this.sequenceId = fileMini.getSequenceId();
    this.name = fileMini.getName();
    this.id = fileMini.getId();
    this.etag = fileMini.getEtag();
    this.type = EnumWrapper.convertToString(fileMini.getType());
  }

  public CollaborationItem(FolderMini folderMini) {
    super(null, folderMini, null);
    this.sequenceId = folderMini.getSequenceId();
    this.name = folderMini.getName();
    this.id = folderMini.getId();
    this.etag = folderMini.getEtag();
    this.type = EnumWrapper.convertToString(folderMini.getType());
  }

  public CollaborationItem(WebLinkMini webLinkMini) {
    super(null, null, webLinkMini);
    this.sequenceId = webLinkMini.getSequenceId();
    this.name = webLinkMini.getName();
    this.id = webLinkMini.getId();
    this.etag = webLinkMini.getEtag();
    this.type = EnumWrapper.convertToString(webLinkMini.getType());
  }

  public boolean isFileMini() {
    return value0 != null;
  }

  public FileMini getFileMini() {
    return value0;
  }

  public boolean isFolderMini() {
    return value1 != null;
  }

  public FolderMini getFolderMini() {
    return value1;
  }

  public boolean isWebLinkMini() {
    return value2 != null;
  }

  public WebLinkMini getWebLinkMini() {
    return value2;
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

  static class CollaborationItemDeserializer extends JsonDeserializer<CollaborationItem> {

    public CollaborationItemDeserializer() {
      super();
    }

    @Override
    public CollaborationItem deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "file":
            return new CollaborationItem(JsonManager.deserialize(node, FileMini.class));
          case "folder":
            return new CollaborationItem(JsonManager.deserialize(node, FolderMini.class));
          case "web_link":
            return new CollaborationItem(JsonManager.deserialize(node, WebLinkMini.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize CollaborationItem");
    }
  }
}
