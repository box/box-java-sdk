package com.box.sdkgen.schemas.trackingcode;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TrackingCode extends SerializableObject {

  @JsonDeserialize(using = TrackingCodeTypeField.TrackingCodeTypeFieldDeserializer.class)
  @JsonSerialize(using = TrackingCodeTypeField.TrackingCodeTypeFieldSerializer.class)
  protected EnumWrapper<TrackingCodeTypeField> type;

  protected String name;

  protected String value;

  public TrackingCode() {
    super();
  }

  protected TrackingCode(Builder builder) {
    super();
    this.type = builder.type;
    this.name = builder.name;
    this.value = builder.value;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<TrackingCodeTypeField> getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public String getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TrackingCode casted = (TrackingCode) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name)
        && Objects.equals(value, casted.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, name, value);
  }

  @Override
  public String toString() {
    return "TrackingCode{"
        + "type='"
        + type
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + ", "
        + "value='"
        + value
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<TrackingCodeTypeField> type;

    protected String name;

    protected String value;

    public Builder type(TrackingCodeTypeField type) {
      this.type = new EnumWrapper<TrackingCodeTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<TrackingCodeTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder value(String value) {
      this.value = value;
      return this;
    }

    public TrackingCode build() {
      return new TrackingCode(this);
    }
  }
}
