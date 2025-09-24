package com.box.sdkgen.schemas.folderfull;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FolderFullClassificationField extends SerializableObject {

  protected String name;

  protected String definition;

  protected String color;

  public FolderFullClassificationField() {
    super();
  }

  protected FolderFullClassificationField(Builder builder) {
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
    FolderFullClassificationField casted = (FolderFullClassificationField) o;
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
    return "FolderFullClassificationField{"
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

    public FolderFullClassificationField build() {
      return new FolderFullClassificationField(this);
    }
  }
}
