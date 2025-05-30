package com.box.sdkgen.schemas.legalholdpolicies;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.legalholdpolicy.LegalHoldPolicy;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class LegalHoldPolicies extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  @JsonProperty("prev_marker")
  protected String prevMarker;

  protected List<LegalHoldPolicy> entries;

  public LegalHoldPolicies() {
    super();
  }

  protected LegalHoldPolicies(LegalHoldPoliciesBuilder builder) {
    super();
    this.limit = builder.limit;
    this.nextMarker = builder.nextMarker;
    this.prevMarker = builder.prevMarker;
    this.entries = builder.entries;
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

  public List<LegalHoldPolicy> getEntries() {
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
    LegalHoldPolicies casted = (LegalHoldPolicies) o;
    return Objects.equals(limit, casted.limit)
        && Objects.equals(nextMarker, casted.nextMarker)
        && Objects.equals(prevMarker, casted.prevMarker)
        && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(limit, nextMarker, prevMarker, entries);
  }

  @Override
  public String toString() {
    return "LegalHoldPolicies{"
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
        + "entries='"
        + entries
        + '\''
        + "}";
  }

  public static class LegalHoldPoliciesBuilder {

    protected Long limit;

    protected String nextMarker;

    protected String prevMarker;

    protected List<LegalHoldPolicy> entries;

    public LegalHoldPoliciesBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public LegalHoldPoliciesBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public LegalHoldPoliciesBuilder prevMarker(String prevMarker) {
      this.prevMarker = prevMarker;
      return this;
    }

    public LegalHoldPoliciesBuilder entries(List<LegalHoldPolicy> entries) {
      this.entries = entries;
      return this;
    }

    public LegalHoldPolicies build() {
      return new LegalHoldPolicies(this);
    }
  }
}
