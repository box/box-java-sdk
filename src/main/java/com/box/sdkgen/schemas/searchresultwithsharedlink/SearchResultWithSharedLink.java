package com.box.sdkgen.schemas.searchresultwithsharedlink;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.filefullorfolderfullorweblink.FileFullOrFolderFullOrWebLink;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class SearchResultWithSharedLink extends SerializableObject {

  @JsonProperty("accessible_via_shared_link")
  protected String accessibleViaSharedLink;

  protected FileFullOrFolderFullOrWebLink item;

  protected String type;

  public SearchResultWithSharedLink() {
    super();
  }

  protected SearchResultWithSharedLink(SearchResultWithSharedLinkBuilder builder) {
    super();
    this.accessibleViaSharedLink = builder.accessibleViaSharedLink;
    this.item = builder.item;
    this.type = builder.type;
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

  public static class SearchResultWithSharedLinkBuilder {

    protected String accessibleViaSharedLink;

    protected FileFullOrFolderFullOrWebLink item;

    protected String type;

    public SearchResultWithSharedLinkBuilder accessibleViaSharedLink(
        String accessibleViaSharedLink) {
      this.accessibleViaSharedLink = accessibleViaSharedLink;
      return this;
    }

    public SearchResultWithSharedLinkBuilder item(FileFullOrFolderFullOrWebLink item) {
      this.item = item;
      return this;
    }

    public SearchResultWithSharedLinkBuilder type(String type) {
      this.type = type;
      return this;
    }

    public SearchResultWithSharedLink build() {
      return new SearchResultWithSharedLink(this);
    }
  }
}
