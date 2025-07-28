package com.box.sdkgen.schemas.filefullorfolderminiorweblink;

import com.box.sdkgen.internal.OneOfThree;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.weblink.WebLink;
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

@JsonDeserialize(
    using = FileFullOrFolderMiniOrWebLink.FileFullOrFolderMiniOrWebLinkDeserializer.class)
@JsonSerialize(using = OneOfThree.OneOfThreeSerializer.class)
public class FileFullOrFolderMiniOrWebLink extends OneOfThree<FileFull, FolderMini, WebLink> {

  protected final String sequenceId;

  protected final String name;

  protected final String id;

  protected final String etag;

  protected final String type;

  public FileFullOrFolderMiniOrWebLink(FileFull fileFull) {
    super(fileFull, null, null);
    this.sequenceId = fileFull.getSequenceId();
    this.name = fileFull.getName();
    this.id = fileFull.getId();
    this.etag = fileFull.getEtag();
    this.type = EnumWrapper.convertToString(fileFull.getType());
  }

  public FileFullOrFolderMiniOrWebLink(FolderMini folderMini) {
    super(null, folderMini, null);
    this.sequenceId = folderMini.getSequenceId();
    this.name = folderMini.getName();
    this.id = folderMini.getId();
    this.etag = folderMini.getEtag();
    this.type = EnumWrapper.convertToString(folderMini.getType());
  }

  public FileFullOrFolderMiniOrWebLink(WebLink webLink) {
    super(null, null, webLink);
    this.sequenceId = webLink.getSequenceId();
    this.name = webLink.getName();
    this.id = webLink.getId();
    this.etag = webLink.getEtag();
    this.type = EnumWrapper.convertToString(webLink.getType());
  }

  public boolean isFileFull() {
    return value0 != null;
  }

  public FileFull getFileFull() {
    return value0;
  }

  public boolean isFolderMini() {
    return value1 != null;
  }

  public FolderMini getFolderMini() {
    return value1;
  }

  public boolean isWebLink() {
    return value2 != null;
  }

  public WebLink getWebLink() {
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
