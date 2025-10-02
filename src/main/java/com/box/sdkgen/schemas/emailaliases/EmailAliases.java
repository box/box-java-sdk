package com.box.sdkgen.schemas.emailaliases;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.emailalias.EmailAlias;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** A list of email aliases. */
@JsonFilter("nullablePropertyFilter")
public class EmailAliases extends SerializableObject {

  /** The number of email aliases. */
  @JsonProperty("total_count")
  protected Long totalCount;

  /** A list of email aliases. */
  protected List<EmailAlias> entries;

  public EmailAliases() {
    super();
  }

  protected EmailAliases(Builder builder) {
    super();
    this.totalCount = builder.totalCount;
    this.entries = builder.entries;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Long getTotalCount() {
    return totalCount;
  }

  public List<EmailAlias> getEntries() {
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
    EmailAliases casted = (EmailAliases) o;
    return Objects.equals(totalCount, casted.totalCount) && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalCount, entries);
  }

  @Override
  public String toString() {
    return "EmailAliases{"
        + "totalCount='"
        + totalCount
        + '\''
        + ", "
        + "entries='"
        + entries
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Long totalCount;

    protected List<EmailAlias> entries;

    public Builder totalCount(Long totalCount) {
      this.totalCount = totalCount;
      return this;
    }

    public Builder entries(List<EmailAlias> entries) {
      this.entries = entries;
      return this;
    }

    public EmailAliases build() {
      return new EmailAliases(this);
    }
  }
}
