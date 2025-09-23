package com.box.sdkgen.schemas.filefull;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FileFullRepresentationsEntriesInfoField extends SerializableObject {

  protected String url;

  public FileFullRepresentationsEntriesInfoField() {
    super();
  }

  protected FileFullRepresentationsEntriesInfoField(Builder builder) {
    super();
    this.url = builder.url;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getUrl() {
    return url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileFullRepresentationsEntriesInfoField casted = (FileFullRepresentationsEntriesInfoField) o;
    return Objects.equals(url, casted.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(url);
  }

  @Override
  public String toString() {
    return "FileFullRepresentationsEntriesInfoField{" + "url='" + url + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String url;

    public Builder url(String url) {
      this.url = url;
      return this;
    }

    public FileFullRepresentationsEntriesInfoField build() {
      return new FileFullRepresentationsEntriesInfoField(this);
    }
  }
}
