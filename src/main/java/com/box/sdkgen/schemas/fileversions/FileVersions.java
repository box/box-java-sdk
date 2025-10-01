package com.box.sdkgen.schemas.fileversions;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.fileversionfull.FileVersionFull;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** A list of file versions. */
@JsonFilter("nullablePropertyFilter")
public class FileVersions extends SerializableObject {

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
   * The limit that was used for these entries. This will be the same as the `limit` query parameter
   * unless that value exceeded the maximum value allowed. The maximum value varies by API.
   */
  protected Long limit;

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
  protected List<FileVersionsOrderField> order;

  /** A list of file versions. */
  protected List<FileVersionFull> entries;

  public FileVersions() {
    super();
  }

  protected FileVersions(Builder builder) {
    super();
    this.totalCount = builder.totalCount;
    this.limit = builder.limit;
    this.offset = builder.offset;
    this.order = builder.order;
    this.entries = builder.entries;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public List<FileVersionsOrderField> getOrder() {
    return order;
  }

  public List<FileVersionFull> getEntries() {
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
    FileVersions casted = (FileVersions) o;
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
    return "FileVersions{"
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

  public static class Builder extends NullableFieldTracker {

    protected Long totalCount;

    protected Long limit;

    protected Long offset;

    protected List<FileVersionsOrderField> order;

    protected List<FileVersionFull> entries;

    public Builder totalCount(Long totalCount) {
      this.totalCount = totalCount;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public Builder order(List<FileVersionsOrderField> order) {
      this.order = order;
      return this;
    }

    public Builder entries(List<FileVersionFull> entries) {
      this.entries = entries;
      return this;
    }

    public FileVersions build() {
      return new FileVersions(this);
    }
  }
}
