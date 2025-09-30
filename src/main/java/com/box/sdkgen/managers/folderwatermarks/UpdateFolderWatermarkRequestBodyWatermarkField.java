package com.box.sdkgen.managers.folderwatermarks;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateFolderWatermarkRequestBodyWatermarkField extends SerializableObject {

  @JsonDeserialize(
      using =
          UpdateFolderWatermarkRequestBodyWatermarkImprintField
              .UpdateFolderWatermarkRequestBodyWatermarkImprintFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateFolderWatermarkRequestBodyWatermarkImprintField
              .UpdateFolderWatermarkRequestBodyWatermarkImprintFieldSerializer.class)
  protected EnumWrapper<UpdateFolderWatermarkRequestBodyWatermarkImprintField> imprint;

  public UpdateFolderWatermarkRequestBodyWatermarkField() {
    super();
    this.imprint =
        new EnumWrapper<UpdateFolderWatermarkRequestBodyWatermarkImprintField>(
            UpdateFolderWatermarkRequestBodyWatermarkImprintField.DEFAULT);
  }

  protected UpdateFolderWatermarkRequestBodyWatermarkField(Builder builder) {
    super();
    this.imprint = builder.imprint;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<UpdateFolderWatermarkRequestBodyWatermarkImprintField> getImprint() {
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
    UpdateFolderWatermarkRequestBodyWatermarkField casted =
        (UpdateFolderWatermarkRequestBodyWatermarkField) o;
    return Objects.equals(imprint, casted.imprint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(imprint);
  }

  @Override
  public String toString() {
    return "UpdateFolderWatermarkRequestBodyWatermarkField{" + "imprint='" + imprint + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<UpdateFolderWatermarkRequestBodyWatermarkImprintField> imprint;

    public Builder() {
      super();
      this.imprint =
          new EnumWrapper<UpdateFolderWatermarkRequestBodyWatermarkImprintField>(
              UpdateFolderWatermarkRequestBodyWatermarkImprintField.DEFAULT);
    }

    public Builder imprint(UpdateFolderWatermarkRequestBodyWatermarkImprintField imprint) {
      this.imprint =
          new EnumWrapper<UpdateFolderWatermarkRequestBodyWatermarkImprintField>(imprint);
      return this;
    }

    public Builder imprint(
        EnumWrapper<UpdateFolderWatermarkRequestBodyWatermarkImprintField> imprint) {
      this.imprint = imprint;
      return this;
    }

    public UpdateFolderWatermarkRequestBodyWatermarkField build() {
      return new UpdateFolderWatermarkRequestBodyWatermarkField(this);
    }
  }
}
