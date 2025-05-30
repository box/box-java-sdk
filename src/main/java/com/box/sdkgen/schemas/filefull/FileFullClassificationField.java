package com.box.sdkgen.schemas.filefull;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class FileFullClassificationField extends SerializableObject {

  protected String name;

  protected String definition;

  protected String color;

  public FileFullClassificationField() {
    super();
  }

  protected FileFullClassificationField(FileFullClassificationFieldBuilder builder) {
    super();
    this.name = builder.name;
    this.definition = builder.definition;
    this.color = builder.color;
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

  public static class FileFullClassificationFieldBuilder {

    protected String name;

    protected String definition;

    protected String color;

    public FileFullClassificationFieldBuilder name(String name) {
      this.name = name;
      return this;
    }

    public FileFullClassificationFieldBuilder definition(String definition) {
      this.definition = definition;
      return this;
    }

    public FileFullClassificationFieldBuilder color(String color) {
      this.color = color;
      return this;
    }

    public FileFullClassificationField build() {
      return new FileFullClassificationField(this);
    }
  }
}
