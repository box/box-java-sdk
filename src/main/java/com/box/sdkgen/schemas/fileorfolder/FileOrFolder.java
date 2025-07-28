package com.box.sdkgen.schemas.fileorfolder;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.schemas.file.File;
import com.box.sdkgen.schemas.folder.Folder;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.usermini.UserMini;
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
import java.util.Date;

@JsonDeserialize(using = FileOrFolder.FileOrFolderDeserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class FileOrFolder extends OneOfTwo<File, Folder> {

  protected final String description;

  protected final Long size;

  protected final Date createdAt;

  protected final Date modifiedAt;

  protected final Date trashedAt;

  protected final Date purgedAt;

  protected final Date contentCreatedAt;

  protected final Date contentModifiedAt;

  protected final UserMini createdBy;

  protected final UserMini modifiedBy;

  protected final UserMini ownedBy;

  protected final FolderMini parent;

  protected final String itemStatus;

  protected final String sequenceId;

  protected final String name;

  protected final String id;

  protected final String etag;

  protected final String type;

  public FileOrFolder(File file) {
    super(file, null);
    this.description = file.getDescription();
    this.size = file.getSize();
    this.createdAt = file.getCreatedAt();
    this.modifiedAt = file.getModifiedAt();
    this.trashedAt = file.getTrashedAt();
    this.purgedAt = file.getPurgedAt();
    this.contentCreatedAt = file.getContentCreatedAt();
    this.contentModifiedAt = file.getContentModifiedAt();
    this.createdBy = file.getCreatedBy();
    this.modifiedBy = file.getModifiedBy();
    this.ownedBy = file.getOwnedBy();
    this.parent = file.getParent();
    this.itemStatus = EnumWrapper.convertToString(file.getItemStatus());
    this.sequenceId = file.getSequenceId();
    this.name = file.getName();
    this.id = file.getId();
    this.etag = file.getEtag();
    this.type = EnumWrapper.convertToString(file.getType());
  }

  public FileOrFolder(Folder folder) {
    super(null, folder);
    this.description = folder.getDescription();
    this.size = folder.getSize();
    this.createdAt = folder.getCreatedAt();
    this.modifiedAt = folder.getModifiedAt();
    this.trashedAt = folder.getTrashedAt();
    this.purgedAt = folder.getPurgedAt();
    this.contentCreatedAt = folder.getContentCreatedAt();
    this.contentModifiedAt = folder.getContentModifiedAt();
    this.createdBy = folder.getCreatedBy();
    this.modifiedBy = folder.getModifiedBy();
    this.ownedBy = folder.getOwnedBy();
    this.parent = folder.getParent();
    this.itemStatus = EnumWrapper.convertToString(folder.getItemStatus());
    this.sequenceId = folder.getSequenceId();
    this.name = folder.getName();
    this.id = folder.getId();
    this.etag = folder.getEtag();
    this.type = EnumWrapper.convertToString(folder.getType());
  }

  public boolean isFile() {
    return value0 != null;
  }

  public File getFile() {
    return value0;
  }

  public boolean isFolder() {
    return value1 != null;
  }

  public Folder getFolder() {
    return value1;
  }

  public String getDescription() {
    return description;
  }

  public long getSize() {
    return size;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getModifiedAt() {
    return modifiedAt;
  }

  public Date getTrashedAt() {
    return trashedAt;
  }

  public Date getPurgedAt() {
    return purgedAt;
  }

  public Date getContentCreatedAt() {
    return contentCreatedAt;
  }

  public Date getContentModifiedAt() {
    return contentModifiedAt;
  }

  public UserMini getCreatedBy() {
    return createdBy;
  }

  public UserMini getModifiedBy() {
    return modifiedBy;
  }

  public UserMini getOwnedBy() {
    return ownedBy;
  }

  public FolderMini getParent() {
    return parent;
  }

  public String getItemStatus() {
    return itemStatus;
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
