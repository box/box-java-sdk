package com.box.sdkgen.schemas.filefull;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FileFullClassificationField extends SerializableObject {

  protected String name;

  protected String definition;

  protected String color;

  public FileFullClassificationField() {
    super();
  }

  protected FileFullClassificationField(Builder builder) {
    super();
    this.name = builder.name;
    this.definition = builder.definition;
    this.color = builder.color;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public String getDefinition() {
    return definition;
  }

  public String getColor() {
    return color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileFullClassificationField casted = (FileFullClassificationField) o;
    return Objects.equals(name, casted.name)
        && Objects.equals(definition, casted.definition)
        && Objects.equals(color, casted.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, definition, color);
  }

  @Override
  public String toString() {
    return "FileFullClassificationField{"
        + "name='"
        + name
        + '\''
        + ", "
        + "definition='"
        + definition
        + '\''
        + ", "
        + "color='"
        + color
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String name;

    protected String definition;

    protected String color;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder definition(String definition) {
      this.definition = definition;
      return this;
    }

    public Builder color(String color) {
      this.color = color;
      return this;
    }

    public FileFullClassificationField build() {
      return new FileFullClassificationField(this);
    }
  }
}
