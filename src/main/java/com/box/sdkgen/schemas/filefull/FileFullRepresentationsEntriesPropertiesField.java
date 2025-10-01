package com.box.sdkgen.schemas.filefull;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FileFullRepresentationsEntriesPropertiesField extends SerializableObject {

  /** The width by height size of this representation in pixels. */
  protected String dimensions;

  /** Indicates if the representation is build up out of multiple pages. */
  protected String paged;

  /** Indicates if the representation can be used as a thumbnail of the file. */
  protected String thumb;

  public FileFullRepresentationsEntriesPropertiesField() {
    super();
  }

  protected FileFullRepresentationsEntriesPropertiesField(Builder builder) {
    super();
    this.dimensions = builder.dimensions;
    this.paged = builder.paged;
    this.thumb = builder.thumb;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getDimensions() {
    return dimensions;
  }

  public String getPaged() {
    return paged;
  }

  public String getThumb() {
    return thumb;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileFullRepresentationsEntriesPropertiesField casted =
        (FileFullRepresentationsEntriesPropertiesField) o;
    return Objects.equals(dimensions, casted.dimensions)
        && Objects.equals(paged, casted.paged)
        && Objects.equals(thumb, casted.thumb);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dimensions, paged, thumb);
  }

  @Override
  public String toString() {
    return "FileFullRepresentationsEntriesPropertiesField{"
        + "dimensions='"
        + dimensions
        + '\''
        + ", "
        + "paged='"
        + paged
        + '\''
        + ", "
        + "thumb='"
        + thumb
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String dimensions;

    protected String paged;

    protected String thumb;

    public Builder dimensions(String dimensions) {
      this.dimensions = dimensions;
      return this;
    }

    public Builder paged(String paged) {
      this.paged = paged;
      return this;
    }

    public Builder thumb(String thumb) {
      this.thumb = thumb;
      return this;
    }

    public FileFullRepresentationsEntriesPropertiesField build() {
      return new FileFullRepresentationsEntriesPropertiesField(this);
    }
  }
}
