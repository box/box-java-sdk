package com.box.sdkgen.schemas.signtemplates;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.signtemplate.SignTemplate;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class SignTemplates extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  @JsonProperty("prev_marker")
  protected String prevMarker;

  protected List<SignTemplate> entries;

  public SignTemplates() {
    super();
  }

  protected SignTemplates(SignTemplatesBuilder builder) {
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

  public List<SignTemplate> getEntries() {
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
    SignTemplates casted = (SignTemplates) o;
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
    return "SignTemplates{"
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

  public static class SignTemplatesBuilder {

    protected Long limit;

    protected String nextMarker;

    protected String prevMarker;

    protected List<SignTemplate> entries;

    public SignTemplatesBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public SignTemplatesBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public SignTemplatesBuilder prevMarker(String prevMarker) {
      this.prevMarker = prevMarker;
      return this;
    }

    public SignTemplatesBuilder entries(List<SignTemplate> entries) {
      this.entries = entries;
      return this;
    }

    public SignTemplates build() {
      return new SignTemplates(this);
    }
  }
}
