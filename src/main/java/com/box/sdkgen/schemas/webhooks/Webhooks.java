package com.box.sdkgen.schemas.webhooks;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.webhookmini.WebhookMini;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class Webhooks extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  @JsonProperty("prev_marker")
  protected String prevMarker;

  protected List<WebhookMini> entries;

  public Webhooks() {
    super();
  }

  protected Webhooks(WebhooksBuilder builder) {
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

  public List<WebhookMini> getEntries() {
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
    Webhooks casted = (Webhooks) o;
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
    return "Webhooks{"
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

  public static class WebhooksBuilder {

    protected Long limit;

    protected String nextMarker;

    protected String prevMarker;

    protected List<WebhookMini> entries;

    public WebhooksBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public WebhooksBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public WebhooksBuilder prevMarker(String prevMarker) {
      this.prevMarker = prevMarker;
      return this;
    }

    public WebhooksBuilder entries(List<WebhookMini> entries) {
      this.entries = entries;
      return this;
    }

    public Webhooks build() {
      return new Webhooks(this);
    }
  }
}
