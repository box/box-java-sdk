package com.box.sdkgen.schemas.filefull;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FileFullRepresentationsEntriesStatusField extends SerializableObject {

  /**
   * The status of the representation.
   *
   * <p>* `success` defines the representation as ready to be viewed. * `viewable` defines a video
   * to be ready for viewing. * `pending` defines the representation as to be generated. Retry this
   * endpoint to re-check the status. * `none` defines that the representation will be created when
   * requested. Request the URL defined in the `info` object to trigger this generation.
   */
  @JsonDeserialize(
      using =
          FileFullRepresentationsEntriesStatusStateField
              .FileFullRepresentationsEntriesStatusStateFieldDeserializer.class)
  @JsonSerialize(
      using =
          FileFullRepresentationsEntriesStatusStateField
              .FileFullRepresentationsEntriesStatusStateFieldSerializer.class)
  protected EnumWrapper<FileFullRepresentationsEntriesStatusStateField> state;

  public FileFullRepresentationsEntriesStatusField() {
    super();
  }

  protected FileFullRepresentationsEntriesStatusField(Builder builder) {
    super();
    this.state = builder.state;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<FileFullRepresentationsEntriesStatusStateField> getState() {
    return state;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileFullRepresentationsEntriesStatusField casted =
        (FileFullRepresentationsEntriesStatusField) o;
    return Objects.equals(state, casted.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(state);
  }

  @Override
  public String toString() {
    return "FileFullRepresentationsEntriesStatusField{" + "state='" + state + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<FileFullRepresentationsEntriesStatusStateField> state;

    public Builder state(FileFullRepresentationsEntriesStatusStateField state) {
      this.state = new EnumWrapper<FileFullRepresentationsEntriesStatusStateField>(state);
      return this;
    }

    public Builder state(EnumWrapper<FileFullRepresentationsEntriesStatusStateField> state) {
      this.state = state;
      return this;
    }

    public FileFullRepresentationsEntriesStatusField build() {
      return new FileFullRepresentationsEntriesStatusField(this);
    }
  }
}
