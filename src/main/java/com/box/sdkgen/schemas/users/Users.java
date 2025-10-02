package com.box.sdkgen.schemas.users;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.userfull.UserFull;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** A list of users. */
@JsonFilter("nullablePropertyFilter")
public class Users extends SerializableObject {

  /**
   * The limit that was used for these entries. This will be the same as the `limit` query parameter
   * unless that value exceeded the maximum value allowed. The maximum value varies by API.
   */
  protected Long limit;

  /** The marker for the start of the next page of results. */
  @JsonProperty("next_marker")
  @Nullable
  protected String nextMarker;

  /** The marker for the start of the previous page of results. */
  @JsonProperty("prev_marker")
  @Nullable
  protected String prevMarker;

  /**
   * One greater than the offset of the last entry in the entire collection. The total number of
   * entries in the collection may be less than `total_count`.
   *
   * <p>This field is only returned for calls that use offset-based pagination. For marker-based
   * paginated APIs, this field will be omitted.
   */
  @JsonProperty("total_count")
  protected Long totalCount;

  /**
   * The 0-based offset of the first entry in this set. This will be the same as the `offset` query
   * parameter.
   *
   * <p>This field is only returned for calls that use offset-based pagination. For marker-based
   * paginated APIs, this field will be omitted.
   */
  protected Long offset;

  /**
   * The order by which items are returned.
   *
   * <p>This field is only returned for calls that use offset-based pagination. For marker-based
   * paginated APIs, this field will be omitted.
   */
  protected List<UsersOrderField> order;

  /** A list of users. */
  protected List<UserFull> entries;

  public Users() {
    super();
  }

  protected Users(Builder builder) {
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

  public List<UsersOrderField> getOrder() {
    return order;
  }

  public List<UserFull> getEntries() {
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
    Users casted = (Users) o;
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
    return "Users{"
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

    protected List<UsersOrderField> order;

    protected List<UserFull> entries;

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

    public Builder order(List<UsersOrderField> order) {
      this.order = order;
      return this;
    }

    public Builder entries(List<UserFull> entries) {
      this.entries = entries;
      return this;
    }

    public Users build() {
      return new Users(this);
    }
  }
}
