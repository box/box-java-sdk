package com.box.sdkgen.schemas.uploadparts;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.uploadpart.UploadPart;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class UploadParts extends SerializableObject {

  @JsonProperty("total_count")
  protected Long totalCount;

  protected Long limit;

  protected Long offset;

  protected List<UploadPartsOrderField> order;

  protected List<UploadPart> entries;

  public UploadParts() {
    super();
  }

  protected UploadParts(UploadPartsBuilder builder) {
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

  public List<UploadPartsOrderField> getOrder() {
    return order;
  }

  public List<UploadPart> getEntries() {
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
    UploadParts casted = (UploadParts) o;
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
    return "UploadParts{"
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

  public static class UploadPartsBuilder {

    protected Long totalCount;

    protected Long limit;

    protected Long offset;

    protected List<UploadPartsOrderField> order;

    protected List<UploadPart> entries;

    public UploadPartsBuilder totalCount(Long totalCount) {
      this.totalCount = totalCount;
      return this;
    }

    public UploadPartsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public UploadPartsBuilder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public UploadPartsBuilder order(List<UploadPartsOrderField> order) {
      this.order = order;
      return this;
    }

    public UploadPartsBuilder entries(List<UploadPart> entries) {
      this.entries = entries;
      return this;
    }

    public UploadParts build() {
      return new UploadParts(this);
    }
  }
}
