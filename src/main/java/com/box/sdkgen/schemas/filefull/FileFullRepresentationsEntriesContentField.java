package com.box.sdkgen.schemas.filefull;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FileFullRepresentationsEntriesContentField extends SerializableObject {

  @JsonProperty("url_template")
  protected String urlTemplate;

  public FileFullRepresentationsEntriesContentField() {
    super();
  }

  protected FileFullRepresentationsEntriesContentField(Builder builder) {
    super();
    this.urlTemplate = builder.urlTemplate;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getUrlTemplate() {
    return urlTemplate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileFullRepresentationsEntriesContentField casted =
        (FileFullRepresentationsEntriesContentField) o;
    return Objects.equals(urlTemplate, casted.urlTemplate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(urlTemplate);
  }

  @Override
  public String toString() {
    return "FileFullRepresentationsEntriesContentField{"
        + "urlTemplate='"
        + urlTemplate
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String urlTemplate;

    public Builder urlTemplate(String urlTemplate) {
      this.urlTemplate = urlTemplate;
      return this;
    }

    public FileFullRepresentationsEntriesContentField build() {
      return new FileFullRepresentationsEntriesContentField(this);
    }
  }
}
