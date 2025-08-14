package com.box.sdkgen.schemas.searchresultitem;

import com.box.sdkgen.internal.OneOfThree;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.usermini.UserMini;
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
import java.util.Date;

@JsonDeserialize(using = SearchResultItem.SearchResultItemDeserializer.class)
@JsonSerialize(using = OneOfThree.OneOfThreeSerializer.class)
public class SearchResultItem extends OneOfThree<FileFull, FolderFull, WebLink> {

  protected final String description;

  protected final Date createdAt;

  protected final Date modifiedAt;

  protected final Date trashedAt;

  protected final Date purgedAt;

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

  public SearchResultItem(FileFull fileFull) {
    super(fileFull, null, null);
    this.description = fileFull.getDescription();
    this.createdAt = fileFull.getCreatedAt();
    this.modifiedAt = fileFull.getModifiedAt();
    this.trashedAt = fileFull.getTrashedAt();
    this.purgedAt = fileFull.getPurgedAt();
    this.createdBy = fileFull.getCreatedBy();
    this.modifiedBy = fileFull.getModifiedBy();
    this.ownedBy = fileFull.getOwnedBy();
    this.parent = fileFull.getParent();
    this.itemStatus = EnumWrapper.convertToString(fileFull.getItemStatus());
    this.sequenceId = fileFull.getSequenceId();
    this.name = fileFull.getName();
    this.id = fileFull.getId();
    this.etag = fileFull.getEtag();
    this.type = EnumWrapper.convertToString(fileFull.getType());
  }

  public SearchResultItem(FolderFull folderFull) {
    super(null, folderFull, null);
    this.description = folderFull.getDescription();
    this.createdAt = folderFull.getCreatedAt();
    this.modifiedAt = folderFull.getModifiedAt();
    this.trashedAt = folderFull.getTrashedAt();
    this.purgedAt = folderFull.getPurgedAt();
    this.createdBy = folderFull.getCreatedBy();
    this.modifiedBy = folderFull.getModifiedBy();
    this.ownedBy = folderFull.getOwnedBy();
    this.parent = folderFull.getParent();
    this.itemStatus = EnumWrapper.convertToString(folderFull.getItemStatus());
    this.sequenceId = folderFull.getSequenceId();
    this.name = folderFull.getName();
    this.id = folderFull.getId();
    this.etag = folderFull.getEtag();
    this.type = EnumWrapper.convertToString(folderFull.getType());
  }

  public SearchResultItem(WebLink webLink) {
    super(null, null, webLink);
    this.description = webLink.getDescription();
    this.createdAt = webLink.getCreatedAt();
    this.modifiedAt = webLink.getModifiedAt();
    this.trashedAt = webLink.getTrashedAt();
    this.purgedAt = webLink.getPurgedAt();
    this.createdBy = webLink.getCreatedBy();
    this.modifiedBy = webLink.getModifiedBy();
    this.ownedBy = webLink.getOwnedBy();
    this.parent = webLink.getParent();
    this.itemStatus = EnumWrapper.convertToString(webLink.getItemStatus());
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

  public boolean isFolderFull() {
    return value1 != null;
  }

  public FolderFull getFolderFull() {
    return value1;
  }

  public boolean isWebLink() {
    return value2 != null;
  }

  public WebLink getWebLink() {
    return value2;
  }

  public String getDescription() {
    return description;
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

  static class SearchResultItemDeserializer extends JsonDeserializer<SearchResultItem> {

    public SearchResultItemDeserializer() {
      super();
    }

    @Override
    public SearchResultItem deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "file":
            return new SearchResultItem(JsonManager.deserialize(node, FileFull.class));
          case "folder":
            return new SearchResultItem(JsonManager.deserialize(node, FolderFull.class));
          case "web_link":
            return new SearchResultItem(JsonManager.deserialize(node, WebLink.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize SearchResultItem");
    }
  }
}
