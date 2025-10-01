package com.box.sdkgen.managers.filewatermarks;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateFileWatermarkRequestBodyWatermarkField extends SerializableObject {

  /**
   * The type of watermark to apply.
   *
   * <p>Currently only supports one option.
   */
  @JsonDeserialize(
      using =
          UpdateFileWatermarkRequestBodyWatermarkImprintField
              .UpdateFileWatermarkRequestBodyWatermarkImprintFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateFileWatermarkRequestBodyWatermarkImprintField
              .UpdateFileWatermarkRequestBodyWatermarkImprintFieldSerializer.class)
  protected EnumWrapper<UpdateFileWatermarkRequestBodyWatermarkImprintField> imprint;

  public UpdateFileWatermarkRequestBodyWatermarkField() {
    super();
    this.imprint =
        new EnumWrapper<UpdateFileWatermarkRequestBodyWatermarkImprintField>(
            UpdateFileWatermarkRequestBodyWatermarkImprintField.DEFAULT);
  }

  protected UpdateFileWatermarkRequestBodyWatermarkField(Builder builder) {
    super();
    this.imprint = builder.imprint;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<UpdateFileWatermarkRequestBodyWatermarkImprintField> getImprint() {
    return imprint;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateFileWatermarkRequestBodyWatermarkField casted =
        (UpdateFileWatermarkRequestBodyWatermarkField) o;
    return Objects.equals(imprint, casted.imprint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(imprint);
  }

  @Override
  public String toString() {
    return "UpdateFileWatermarkRequestBodyWatermarkField{" + "imprint='" + imprint + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<UpdateFileWatermarkRequestBodyWatermarkImprintField> imprint;

    public Builder() {
      super();
      this.imprint =
          new EnumWrapper<UpdateFileWatermarkRequestBodyWatermarkImprintField>(
              UpdateFileWatermarkRequestBodyWatermarkImprintField.DEFAULT);
    }

    public Builder imprint(UpdateFileWatermarkRequestBodyWatermarkImprintField imprint) {
      this.imprint = new EnumWrapper<UpdateFileWatermarkRequestBodyWatermarkImprintField>(imprint);
      return this;
    }

    public Builder imprint(
        EnumWrapper<UpdateFileWatermarkRequestBodyWatermarkImprintField> imprint) {
      this.imprint = imprint;
      return this;
    }

    public UpdateFileWatermarkRequestBodyWatermarkField build() {
      return new UpdateFileWatermarkRequestBodyWatermarkField(this);
    }
  }
}
