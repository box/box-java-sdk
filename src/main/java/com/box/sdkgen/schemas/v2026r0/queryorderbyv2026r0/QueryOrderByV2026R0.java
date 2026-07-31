package com.box.sdkgen.schemas.v2026r0.queryorderbyv2026r0;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * A single sorting criterion applied to the query result set. Multiple criteria are applied
 * sequentially in the order specified.
 */
@JsonFilter("nullablePropertyFilter")
public class QueryOrderByV2026R0 extends SerializableObject {

  /** The fully qualified field key to sort by. */
  @JsonProperty("field_key")
  protected final String fieldKey;

  /** The direction in which results are ordered. */
  @JsonDeserialize(
      using = QueryOrderByV2026R0DirectionField.QueryOrderByV2026R0DirectionFieldDeserializer.class)
  @JsonSerialize(
      using = QueryOrderByV2026R0DirectionField.QueryOrderByV2026R0DirectionFieldSerializer.class)
  protected final EnumWrapper<QueryOrderByV2026R0DirectionField> direction;

  public QueryOrderByV2026R0(String fieldKey, QueryOrderByV2026R0DirectionField direction) {
    super();
    this.fieldKey = fieldKey;
    this.direction = new EnumWrapper<QueryOrderByV2026R0DirectionField>(direction);
  }

  public QueryOrderByV2026R0(
      @JsonProperty("field_key") String fieldKey,
      @JsonProperty("direction") EnumWrapper<QueryOrderByV2026R0DirectionField> direction) {
    super();
    this.fieldKey = fieldKey;
    this.direction = direction;
  }

  public String getFieldKey() {
    return fieldKey;
  }

  public EnumWrapper<QueryOrderByV2026R0DirectionField> getDirection() {
    return direction;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryOrderByV2026R0 casted = (QueryOrderByV2026R0) o;
    return Objects.equals(fieldKey, casted.fieldKey) && Objects.equals(direction, casted.direction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fieldKey, direction);
  }

  @Override
  public String toString() {
    return "QueryOrderByV2026R0{"
        + "fieldKey='"
        + fieldKey
        + '\''
        + ", "
        + "direction='"
        + direction
        + '\''
        + "}";
  }
}
