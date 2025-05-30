package com.box.sdkgen.schemas.signrequests;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.signrequest.SignRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class SignRequests extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  protected List<SignRequest> entries;

  public SignRequests() {
    super();
  }

  protected SignRequests(SignRequestsBuilder builder) {
    super();
    this.limit = builder.limit;
    this.nextMarker = builder.nextMarker;
    this.entries = builder.entries;
  }

  public Long getLimit() {
    return limit;
  }

  public String getNextMarker() {
    return nextMarker;
  }

  public List<SignRequest> getEntries() {
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
    SignRequests casted = (SignRequests) o;
    return Objects.equals(limit, casted.limit)
        && Objects.equals(nextMarker, casted.nextMarker)
        && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(limit, nextMarker, entries);
  }

  @Override
  public String toString() {
    return "SignRequests{"
        + "limit='"
        + limit
        + '\''
        + ", "
        + "nextMarker='"
        + nextMarker
        + '\''
        + ", "
        + "entries='"
        + entries
        + '\''
        + "}";
  }

  public static class SignRequestsBuilder {

    protected Long limit;

    protected String nextMarker;

    protected List<SignRequest> entries;

    public SignRequestsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public SignRequestsBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public SignRequestsBuilder entries(List<SignRequest> entries) {
      this.entries = entries;
      return this;
    }

    public SignRequests build() {
      return new SignRequests(this);
    }
  }
}
