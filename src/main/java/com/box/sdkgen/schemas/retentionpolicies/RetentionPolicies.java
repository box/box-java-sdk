package com.box.sdkgen.schemas.retentionpolicies;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.retentionpolicy.RetentionPolicy;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class RetentionPolicies extends SerializableObject {

  protected List<RetentionPolicy> entries;

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  public RetentionPolicies() {
    super();
  }

  protected RetentionPolicies(RetentionPoliciesBuilder builder) {
    super();
    this.entries = builder.entries;
    this.limit = builder.limit;
    this.nextMarker = builder.nextMarker;
  }

  public List<RetentionPolicy> getEntries() {
    return entries;
  }

  public Long getLimit() {
    return limit;
  }

  public String getNextMarker() {
    return nextMarker;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RetentionPolicies casted = (RetentionPolicies) o;
    return Objects.equals(entries, casted.entries)
        && Objects.equals(limit, casted.limit)
        && Objects.equals(nextMarker, casted.nextMarker);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entries, limit, nextMarker);
  }

  @Override
  public String toString() {
    return "RetentionPolicies{"
        + "entries='"
        + entries
        + '\''
        + ", "
        + "limit='"
        + limit
        + '\''
        + ", "
        + "nextMarker='"
        + nextMarker
        + '\''
        + "}";
  }

  public static class RetentionPoliciesBuilder {

    protected List<RetentionPolicy> entries;

    protected Long limit;

    protected String nextMarker;

    public RetentionPoliciesBuilder entries(List<RetentionPolicy> entries) {
      this.entries = entries;
      return this;
    }

    public RetentionPoliciesBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public RetentionPoliciesBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public RetentionPolicies build() {
      return new RetentionPolicies(this);
    }
  }
}
