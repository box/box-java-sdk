package com.box.sdkgen.schemas.collections;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.collection.Collection;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class Collections extends SerializableObject {

  @JsonProperty("total_count")
  protected Long totalCount;

  protected Long limit;

  protected Long offset;

  protected List<CollectionsOrderField> order;

  protected List<Collection> entries;

  public Collections() {
    super();
  }

  protected Collections(CollectionsBuilder builder) {
    super();
    this.totalCount = builder.totalCount;
    this.limit = builder.limit;
    this.offset = builder.offset;
    this.order = builder.order;
    this.entries = builder.entries;
  }

  public Long getTotalCount() {
    return totalCount;
  }

  public Long getLimit() {
    return limit;
  }

  public Long getOffset() {
    return offset;
  }

  public List<CollectionsOrderField> getOrder() {
    return order;
  }

  public List<Collection> getEntries() {
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
    Collections casted = (Collections) o;
    return Objects.equals(totalCount, casted.totalCount)
        && Objects.equals(limit, casted.limit)
        && Objects.equals(offset, casted.offset)
        && Objects.equals(order, casted.order)
        && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalCount, limit, offset, order, entries);
  }

  @Override
  public String toString() {
    return "Collections{"
        + "totalCount='"
        + totalCount
        + '\''
        + ", "
        + "limit='"
        + limit
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

  public static class CollectionsBuilder {

    protected Long totalCount;

    protected Long limit;

    protected Long offset;

    protected List<CollectionsOrderField> order;

    protected List<Collection> entries;

    public CollectionsBuilder totalCount(Long totalCount) {
      this.totalCount = totalCount;
      return this;
    }

    public CollectionsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public CollectionsBuilder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public CollectionsBuilder order(List<CollectionsOrderField> order) {
      this.order = order;
      return this;
    }

    public CollectionsBuilder entries(List<Collection> entries) {
      this.entries = entries;
      return this;
    }

    public Collections build() {
      return new Collections(this);
    }
  }
}
