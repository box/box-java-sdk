package com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequestsmartv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * Validation rules for creating a Document Split job using Box-generated page ranges guided by a
 * caller-supplied prompt.
 */
@JsonFilter("nullablePropertyFilter")
public class DocumentSplitCreateRequestSmartV2026R0 extends SerializableObject {

  /** Discriminator. Must be `smart` for this variant. */
  @JsonDeserialize(
      using =
          DocumentSplitCreateRequestSmartV2026R0SplitTypeField
              .DocumentSplitCreateRequestSmartV2026R0SplitTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          DocumentSplitCreateRequestSmartV2026R0SplitTypeField
              .DocumentSplitCreateRequestSmartV2026R0SplitTypeFieldSerializer.class)
  @JsonProperty("split_type")
  protected EnumWrapper<DocumentSplitCreateRequestSmartV2026R0SplitTypeField> splitType;

  /** A natural language prompt that guides automatic page range detection. */
  protected final String prompt;

  public DocumentSplitCreateRequestSmartV2026R0(@JsonProperty("prompt") String prompt) {
    super();
    this.prompt = prompt;
    this.splitType =
        new EnumWrapper<DocumentSplitCreateRequestSmartV2026R0SplitTypeField>(
            DocumentSplitCreateRequestSmartV2026R0SplitTypeField.SMART);
  }

  protected DocumentSplitCreateRequestSmartV2026R0(Builder builder) {
    super();
    this.splitType = builder.splitType;
    this.prompt = builder.prompt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<DocumentSplitCreateRequestSmartV2026R0SplitTypeField> getSplitType() {
    return splitType;
  }

  public String getPrompt() {
    return prompt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocumentSplitCreateRequestSmartV2026R0 casted = (DocumentSplitCreateRequestSmartV2026R0) o;
    return Objects.equals(splitType, casted.splitType) && Objects.equals(prompt, casted.prompt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(splitType, prompt);
  }

  @Override
  public String toString() {
    return "DocumentSplitCreateRequestSmartV2026R0{"
        + "splitType='"
        + splitType
        + '\''
        + ", "
        + "prompt='"
        + prompt
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<DocumentSplitCreateRequestSmartV2026R0SplitTypeField> splitType;

    protected final String prompt;

    public Builder(String prompt) {
      super();
      this.prompt = prompt;
    }

    public Builder splitType(DocumentSplitCreateRequestSmartV2026R0SplitTypeField splitType) {
      this.splitType =
          new EnumWrapper<DocumentSplitCreateRequestSmartV2026R0SplitTypeField>(splitType);
      return this;
    }

    public Builder splitType(
        EnumWrapper<DocumentSplitCreateRequestSmartV2026R0SplitTypeField> splitType) {
      this.splitType = splitType;
      return this;
    }

    public DocumentSplitCreateRequestSmartV2026R0 build() {
      if (this.splitType == null) {
        this.splitType =
            new EnumWrapper<DocumentSplitCreateRequestSmartV2026R0SplitTypeField>(
                DocumentSplitCreateRequestSmartV2026R0SplitTypeField.SMART);
      }
      return new DocumentSplitCreateRequestSmartV2026R0(this);
    }
  }
}
