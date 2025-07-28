package com.box.sdkgen.schemas.collaborationsoffsetpaginated;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.collaboration.Collaboration;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CollaborationsOffsetPaginated extends SerializableObject {

  @JsonProperty("total_count")
  protected Long totalCount;

  protected Long limit;

  protected Long offset;

  protected List<Collaboration> entries;

  public CollaborationsOffsetPaginated() {
    super();
  }

  protected CollaborationsOffsetPaginated(Builder builder) {
    super();
    this.totalCount = builder.totalCount;
    this.limit = builder.limit;
    this.offset = builder.offset;
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

  public List<Collaboration> getEntries() {
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
    CollaborationsOffsetPaginated casted = (CollaborationsOffsetPaginated) o;
    return Objects.equals(totalCount, casted.totalCount)
        && Objects.equals(limit, casted.limit)
        && Objects.equals(offset, casted.offset)
        && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalCount, limit, offset, entries);
  }

  @Override
  public String toString() {
    return "CollaborationsOffsetPaginated{"
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
        + "entries='"
        + entries
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Long totalCount;

    protected Long limit;

    protected Long offset;

    protected List<Collaboration> entries;

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

    public Builder entries(List<Collaboration> entries) {
      this.entries = entries;
      return this;
    }

    public CollaborationsOffsetPaginated build() {
      return new CollaborationsOffsetPaginated(this);
    }
  }
}
