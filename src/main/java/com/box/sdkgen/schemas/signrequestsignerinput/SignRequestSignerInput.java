package com.box.sdkgen.schemas.signrequestsignerinput;

import com.box.sdkgen.schemas.signrequestprefilltag.SignRequestPrefillTag;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class SignRequestSignerInput extends SignRequestPrefillTag {

  @JsonDeserialize(
      using = SignRequestSignerInputTypeField.SignRequestSignerInputTypeFieldDeserializer.class)
  @JsonSerialize(
      using = SignRequestSignerInputTypeField.SignRequestSignerInputTypeFieldSerializer.class)
  protected EnumWrapper<SignRequestSignerInputTypeField> type;

  @JsonDeserialize(
      using =
          SignRequestSignerInputContentTypeField.SignRequestSignerInputContentTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          SignRequestSignerInputContentTypeField.SignRequestSignerInputContentTypeFieldSerializer
              .class)
  @JsonProperty("content_type")
  protected EnumWrapper<SignRequestSignerInputContentTypeField> contentType;

  @JsonProperty("page_index")
  protected final long pageIndex;

  @JsonProperty("read_only")
  protected Boolean readOnly;

  public SignRequestSignerInput(@JsonProperty("page_index") long pageIndex) {
    super();
    this.pageIndex = pageIndex;
  }

  protected SignRequestSignerInput(Builder builder) {
    super(builder);
    this.type = builder.type;
    this.contentType = builder.contentType;
    this.pageIndex = builder.pageIndex;
    this.readOnly = builder.readOnly;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<SignRequestSignerInputTypeField> getType() {
    return type;
  }

  public EnumWrapper<SignRequestSignerInputContentTypeField> getContentType() {
    return contentType;
  }

  public long getPageIndex() {
    return pageIndex;
  }

  public Boolean getReadOnly() {
    return readOnly;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignRequestSignerInput casted = (SignRequestSignerInput) o;
    return Objects.equals(documentTagId, casted.documentTagId)
        && Objects.equals(textValue, casted.textValue)
        && Objects.equals(checkboxValue, casted.checkboxValue)
        && Objects.equals(dateValue, casted.dateValue)
        && Objects.equals(type, casted.type)
        && Objects.equals(contentType, casted.contentType)
        && Objects.equals(pageIndex, casted.pageIndex)
        && Objects.equals(readOnly, casted.readOnly);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        documentTagId, textValue, checkboxValue, dateValue, type, contentType, pageIndex, readOnly);
  }

  @Override
  public String toString() {
    return "SignRequestSignerInput{"
        + "documentTagId='"
        + documentTagId
        + '\''
        + ", "
        + "textValue='"
        + textValue
        + '\''
        + ", "
        + "checkboxValue='"
        + checkboxValue
        + '\''
        + ", "
        + "dateValue='"
        + dateValue
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "contentType='"
        + contentType
        + '\''
        + ", "
        + "pageIndex='"
        + pageIndex
        + '\''
        + ", "
        + "readOnly='"
        + readOnly
        + '\''
        + "}";
  }

  public static class Builder extends SignRequestPrefillTag.Builder {

    protected EnumWrapper<SignRequestSignerInputTypeField> type;

    protected EnumWrapper<SignRequestSignerInputContentTypeField> contentType;

    protected final long pageIndex;

    protected Boolean readOnly;

    public Builder(long pageIndex) {
      super();
      this.pageIndex = pageIndex;
    }

    public Builder type(SignRequestSignerInputTypeField type) {
      this.type = new EnumWrapper<SignRequestSignerInputTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<SignRequestSignerInputTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder contentType(SignRequestSignerInputContentTypeField contentType) {
      this.contentType = new EnumWrapper<SignRequestSignerInputContentTypeField>(contentType);
      return this;
    }

    public Builder contentType(EnumWrapper<SignRequestSignerInputContentTypeField> contentType) {
      this.contentType = contentType;
      return this;
    }

    public Builder readOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
    }

    @Override
    public Builder documentTagId(String documentTagId) {
      this.documentTagId = documentTagId;
      this.markNullableFieldAsSet("document_tag_id");
      return this;
    }

    @Override
    public Builder textValue(String textValue) {
      this.textValue = textValue;
      this.markNullableFieldAsSet("text_value");
      return this;
    }

    @Override
    public Builder checkboxValue(Boolean checkboxValue) {
      this.checkboxValue = checkboxValue;
      this.markNullableFieldAsSet("checkbox_value");
      return this;
    }

    @Override
    public Builder dateValue(Date dateValue) {
      this.dateValue = dateValue;
      this.markNullableFieldAsSet("date_value");
      return this;
    }

    public SignRequestSignerInput build() {
      return new SignRequestSignerInput(this);
    }
  }
}
