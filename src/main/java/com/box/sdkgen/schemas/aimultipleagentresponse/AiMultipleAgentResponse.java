package com.box.sdkgen.schemas.aimultipleagentresponse;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aisingleagentresponsefull.AiSingleAgentResponseFull;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class AiMultipleAgentResponse extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  @JsonProperty("prev_marker")
  protected String prevMarker;

  protected final List<AiSingleAgentResponseFull> entries;

  public AiMultipleAgentResponse(@JsonProperty("entries") List<AiSingleAgentResponseFull> entries) {
    super();
    this.entries = entries;
  }

  protected AiMultipleAgentResponse(AiMultipleAgentResponseBuilder builder) {
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

  public List<AiSingleAgentResponseFull> getEntries() {
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
    AiMultipleAgentResponse casted = (AiMultipleAgentResponse) o;
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
    return "AiMultipleAgentResponse{"
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

  public static class AiMultipleAgentResponseBuilder {

    protected Long limit;

    protected String nextMarker;

    protected String prevMarker;

    protected final List<AiSingleAgentResponseFull> entries;

    public AiMultipleAgentResponseBuilder(List<AiSingleAgentResponseFull> entries) {
      this.entries = entries;
    }

    public AiMultipleAgentResponseBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public AiMultipleAgentResponseBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public AiMultipleAgentResponseBuilder prevMarker(String prevMarker) {
      this.prevMarker = prevMarker;
      return this;
    }

    public AiMultipleAgentResponse build() {
      return new AiMultipleAgentResponse(this);
    }
  }
}
