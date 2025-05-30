package com.box.sdkgen.schemas.filefull;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class FileFullRepresentationsEntriesContentField extends SerializableObject {

  @JsonProperty("url_template")
  protected String urlTemplate;

  public FileFullRepresentationsEntriesContentField() {
    super();
  }

  protected FileFullRepresentationsEntriesContentField(
      FileFullRepresentationsEntriesContentFieldBuilder builder) {
    super();
    this.urlTemplate = builder.urlTemplate;
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

  public static class FileFullRepresentationsEntriesContentFieldBuilder {

    protected String urlTemplate;

    public FileFullRepresentationsEntriesContentFieldBuilder urlTemplate(String urlTemplate) {
      this.urlTemplate = urlTemplate;
      return this;
    }

    public FileFullRepresentationsEntriesContentField build() {
      return new FileFullRepresentationsEntriesContentField(this);
    }
  }
}
