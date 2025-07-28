package com.box.sdkgen.schemas.items;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.filefullorfolderfullorweblink.FileFullOrFolderFullOrWebLink;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class Items extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  @Nullable
  protected String nextMarker;

  @JsonProperty("prev_marker")
  @Nullable
  protected String prevMarker;

  @JsonProperty("total_count")
  protected Long totalCount;

  protected Long offset;

  protected List<ItemsOrderField> order;

  protected List<FileFullOrFolderFullOrWebLink> entries;

  public Items() {
    super();
  }

  protected Items(Builder builder) {
    super();
    this.limit = builder.limit;
    this.nextMarker = builder.nextMarker;
    this.prevMarker = builder.prevMarker;
    this.totalCount = builder.totalCount;
    this.offset = builder.offset;
    this.order = builder.order;
    this.entries = builder.entries;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Long getLimit() {
    return limit;
  }

  public String getNextMarker() {
    return nextMarker;
  }

  public String getPrevMarker() {
    return prevMarker;
  }

  public Long getTotalCount() {
    return totalCount;
  }

  public Long getOffset() {
    return offset;
  }

  public List<ItemsOrderField> getOrder() {
    return order;
  }

  public List<FileFullOrFolderFullOrWebLink> getEntries() {
    return entries;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Items casted = (Items) o;
    return Objects.equals(limit, casted.limit)
        && Objects.equals(nextMarker, casted.nextMarker)
        && Objects.equals(prevMarker, casted.prevMarker)
        && Objects.equals(totalCount, casted.totalCount)
        && Objects.equals(offset, casted.offset)
        && Objects.equals(order, casted.order)
        && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(limit, nextMarker, prevMarker, totalCount, offset, order, entries);
  }

  @Override
  public String toString() {
    return "Items{"
        + "limit='"
        + limit
        + '\''
        + ", "
        + "nextMarker='"
        + nextMarker
        + '\''
        + ", "
        + "prevMarker='"
        + prevMarker
        + '\''
        + ", "
        + "totalCount='"
        + totalCount
        + '\''
        + ", "
        + "offset='"
        + offset
        + '\''
        + ", "
        + "order='"
        + order
        + '\''
        + ", "
        + "entries='"
        + entries
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Long limit;

    protected String nextMarker;

    protected String prevMarker;

    protected Long totalCount;

    protected Long offset;

    protected List<ItemsOrderField> order;

    protected List<FileFullOrFolderFullOrWebLink> entries;

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      this.markNullableFieldAsSet("next_marker");
      return this;
    }

    public Builder prevMarker(String prevMarker) {
      this.prevMarker = prevMarker;
      this.markNullableFieldAsSet("prev_marker");
      return this;
    }

    public Builder totalCount(Long totalCount) {
      this.totalCount = totalCount;
      return this;
    }

    public Builder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public Builder order(List<ItemsOrderField> order) {
      this.order = order;
      return this;
    }

    public Builder entries(List<FileFullOrFolderFullOrWebLink> entries) {
      this.entries = entries;
      return this;
    }

    public Items build() {
      return new Items(this);
    }
  }
}
