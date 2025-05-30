package com.box.sdkgen.schemas.groupmemberships;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.groupmembership.GroupMembership;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class GroupMemberships extends SerializableObject {

  @JsonProperty("total_count")
  protected Long totalCount;

  protected Long limit;

  protected Long offset;

  protected List<GroupMembershipsOrderField> order;

  protected List<GroupMembership> entries;

  public GroupMemberships() {
    super();
  }

  protected GroupMemberships(GroupMembershipsBuilder builder) {
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

  public List<GroupMembershipsOrderField> getOrder() {
    return order;
  }

  public List<GroupMembership> getEntries() {
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
    GroupMemberships casted = (GroupMemberships) o;
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
    return "GroupMemberships{"
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

  public static class GroupMembershipsBuilder {

    protected Long totalCount;

    protected Long limit;

    protected Long offset;

    protected List<GroupMembershipsOrderField> order;

    protected List<GroupMembership> entries;

    public GroupMembershipsBuilder totalCount(Long totalCount) {
      this.totalCount = totalCount;
      return this;
    }

    public GroupMembershipsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GroupMembershipsBuilder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public GroupMembershipsBuilder order(List<GroupMembershipsOrderField> order) {
      this.order = order;
      return this;
    }

    public GroupMembershipsBuilder entries(List<GroupMembership> entries) {
      this.entries = entries;
      return this;
    }

    public GroupMemberships build() {
      return new GroupMemberships(this);
    }
  }
}
