package com.box.sdkgen.schemas.eventsource;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class EventSourceClassificationField extends SerializableObject {

  protected String name;

  public EventSourceClassificationField() {
    super();
  }

  protected EventSourceClassificationField(Builder builder) {
    super();
    this.name = builder.name;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventSourceClassificationField casted = (EventSourceClassificationField) o;
    return Objects.equals(name, casted.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    return "EventSourceClassificationField{" + "name='" + name + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String name;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public EventSourceClassificationField build() {
      return new EventSourceClassificationField(this);
    }
  }
}
