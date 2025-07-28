package com.box.sdkgen.schemas.searchresultwithsharedlink;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.filefullorfolderfullorweblink.FileFullOrFolderFullOrWebLink;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.weblink.WebLink;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class SearchResultWithSharedLink extends SerializableObject {

  @JsonProperty("accessible_via_shared_link")
  protected String accessibleViaSharedLink;

  protected FileFullOrFolderFullOrWebLink item;

  protected String type;

  public SearchResultWithSharedLink() {
    super();
  }

  protected SearchResultWithSharedLink(Builder builder) {
    super();
    this.accessibleViaSharedLink = builder.accessibleViaSharedLink;
    this.item = builder.item;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getAccessibleViaSharedLink() {
    return accessibleViaSharedLink;
  }

  public FileFullOrFolderFullOrWebLink getItem() {
    return item;
  }

  public String getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SearchResultWithSharedLink casted = (SearchResultWithSharedLink) o;
    return Objects.equals(accessibleViaSharedLink, casted.accessibleViaSharedLink)
        && Objects.equals(item, casted.item)
        && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accessibleViaSharedLink, item, type);
  }

  @Override
  public String toString() {
    return "SearchResultWithSharedLink{"
        + "accessibleViaSharedLink='"
        + accessibleViaSharedLink
        + '\''
        + ", "
        + "item='"
        + item
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String accessibleViaSharedLink;

    protected FileFullOrFolderFullOrWebLink item;

    protected String type;

    public Builder accessibleViaSharedLink(String accessibleViaSharedLink) {
      this.accessibleViaSharedLink = accessibleViaSharedLink;
      return this;
    }

    public Builder item(FileFull item) {
      this.item = new FileFullOrFolderFullOrWebLink(item);
      return this;
    }

    public Builder item(FolderFull item) {
      this.item = new FileFullOrFolderFullOrWebLink(item);
      return this;
    }

    public Builder item(WebLink item) {
      this.item = new FileFullOrFolderFullOrWebLink(item);
      return this;
    }

    public Builder item(FileFullOrFolderFullOrWebLink item) {
      this.item = item;
      return this;
    }

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public SearchResultWithSharedLink build() {
      return new SearchResultWithSharedLink(this);
    }
  }
}
