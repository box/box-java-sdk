package com.box.sdkgen.schemas.groups;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.groupfull.GroupFull;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class Groups extends SerializableObject {

  @JsonProperty("total_count")
  protected Long totalCount;

  protected Long limit;

  protected Long offset;

  protected List<GroupsOrderField> order;

  protected List<GroupFull> entries;

  public Groups() {
    super();
  }

  protected Groups(GroupsBuilder builder) {
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

  public List<GroupsOrderField> getOrder() {
    return order;
  }

  public List<GroupFull> getEntries() {
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
    Groups casted = (Groups) o;
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
    return "Groups{"
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

  public static class GroupsBuilder {

    protected Long totalCount;

    protected Long limit;

    protected Long offset;

    protected List<GroupsOrderField> order;

    protected List<GroupFull> entries;

    public GroupsBuilder totalCount(Long totalCount) {
      this.totalCount = totalCount;
      return this;
    }

    public GroupsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GroupsBuilder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public GroupsBuilder order(List<GroupsOrderField> order) {
      this.order = order;
      return this;
    }

    public GroupsBuilder entries(List<GroupFull> entries) {
      this.entries = entries;
      return this;
    }

    public Groups build() {
      return new Groups(this);
    }
  }
}
