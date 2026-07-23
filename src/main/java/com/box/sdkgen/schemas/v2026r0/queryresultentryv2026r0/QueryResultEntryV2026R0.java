package com.box.sdkgen.schemas.v2026r0.queryresultentryv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import java.util.Objects;

/**
 * A single item matching the query. Always includes the item `type` and `id`. If a `fields`
 * parameter was specified in the request, then additional item and/or metadata fields will be
 * provided.
 */
@JsonFilter("nullablePropertyFilter")
public class QueryResultEntryV2026R0 extends SerializableObject {

  /** The unique identifier of the matching item. */
  protected final String id;

  /** The type of the matching item. */
  protected final String type;

  @JsonAnyGetter @JsonAnySetter protected Map<String, Object> extraData;

  public QueryResultEntryV2026R0(@JsonProperty("id") String id, @JsonProperty("type") String type) {
    super();
    this.id = id;
    this.type = type;
  }

  protected QueryResultEntryV2026R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.extraData = builder.extraData;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public Map<String, Object> getExtraData() {
    return extraData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryResultEntryV2026R0 casted = (QueryResultEntryV2026R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(extraData, casted.extraData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, extraData);
  }

  @Override
  public String toString() {
    return "QueryResultEntryV2026R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "extraData='"
        + extraData
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected final String type;

    protected Map<String, Object> extraData;

    public Builder(String id, String type) {
      super();
      this.id = id;
      this.type = type;
    }

    public Builder extraData(Map<String, Object> extraData) {
      this.extraData = extraData;
      return this;
    }

    public QueryResultEntryV2026R0 build() {
      return new QueryResultEntryV2026R0(this);
    }
  }
}
