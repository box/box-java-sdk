package com.box.sdkgen.managers.folderclassifications;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateClassificationOnFolderRequestBody extends SerializableObject {

  /** The value will always be `replace`. */
  @JsonDeserialize(
      using =
          UpdateClassificationOnFolderRequestBodyOpField
              .UpdateClassificationOnFolderRequestBodyOpFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateClassificationOnFolderRequestBodyOpField
              .UpdateClassificationOnFolderRequestBodyOpFieldSerializer.class)
  protected EnumWrapper<UpdateClassificationOnFolderRequestBodyOpField> op;

  /** Defines classifications available in the enterprise. */
  @JsonDeserialize(
      using =
          UpdateClassificationOnFolderRequestBodyPathField
              .UpdateClassificationOnFolderRequestBodyPathFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateClassificationOnFolderRequestBodyPathField
              .UpdateClassificationOnFolderRequestBodyPathFieldSerializer.class)
  protected EnumWrapper<UpdateClassificationOnFolderRequestBodyPathField> path;

  /**
   * The name of the classification to apply to this folder.
   *
   * <p>To list the available classifications in an enterprise, use the classification API to
   * retrieve the [classification
   * template](https://developer.box.com/reference/get-metadata-templates-enterprise-securityClassification-6VMVochwUWo-schema)
   * which lists all available classification keys.
   */
  protected final String value;

  public UpdateClassificationOnFolderRequestBody(@JsonProperty("value") String value) {
    super();
    this.value = value;
    this.op =
        new EnumWrapper<UpdateClassificationOnFolderRequestBodyOpField>(
            UpdateClassificationOnFolderRequestBodyOpField.REPLACE);
    this.path =
        new EnumWrapper<UpdateClassificationOnFolderRequestBodyPathField>(
            UpdateClassificationOnFolderRequestBodyPathField._BOX__SECURITY__CLASSIFICATION__KEY);
  }

  protected UpdateClassificationOnFolderRequestBody(Builder builder) {
    super();
    this.op = builder.op;
    this.path = builder.path;
    this.value = builder.value;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<UpdateClassificationOnFolderRequestBodyOpField> getOp() {
    return op;
  }

  public EnumWrapper<UpdateClassificationOnFolderRequestBodyPathField> getPath() {
    return path;
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
    UpdateClassificationOnFolderRequestBody casted = (UpdateClassificationOnFolderRequestBody) o;
    return Objects.equals(op, casted.op)
        && Objects.equals(path, casted.path)
        && Objects.equals(value, casted.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(op, path, value);
  }

  @Override
  public String toString() {
    return "UpdateClassificationOnFolderRequestBody{"
        + "op='"
        + op
        + '\''
        + ", "
        + "path='"
        + path
        + '\''
        + ", "
        + "value='"
        + value
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<UpdateClassificationOnFolderRequestBodyOpField> op;

    protected EnumWrapper<UpdateClassificationOnFolderRequestBodyPathField> path;

    protected final String value;

    public Builder(String value) {
      super();
      this.value = value;
      this.op =
          new EnumWrapper<UpdateClassificationOnFolderRequestBodyOpField>(
              UpdateClassificationOnFolderRequestBodyOpField.REPLACE);
      this.path =
          new EnumWrapper<UpdateClassificationOnFolderRequestBodyPathField>(
              UpdateClassificationOnFolderRequestBodyPathField._BOX__SECURITY__CLASSIFICATION__KEY);
    }

    public Builder op(UpdateClassificationOnFolderRequestBodyOpField op) {
      this.op = new EnumWrapper<UpdateClassificationOnFolderRequestBodyOpField>(op);
      return this;
    }

    public Builder op(EnumWrapper<UpdateClassificationOnFolderRequestBodyOpField> op) {
      this.op = op;
      return this;
    }

    public Builder path(UpdateClassificationOnFolderRequestBodyPathField path) {
      this.path = new EnumWrapper<UpdateClassificationOnFolderRequestBodyPathField>(path);
      return this;
    }

    public Builder path(EnumWrapper<UpdateClassificationOnFolderRequestBodyPathField> path) {
      this.path = path;
      return this;
    }

    public UpdateClassificationOnFolderRequestBody build() {
      return new UpdateClassificationOnFolderRequestBody(this);
    }
  }
}
