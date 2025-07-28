package com.box.sdkgen.schemas.signrequestprefilltag;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class SignRequestPrefillTag extends SerializableObject {

  @JsonProperty("document_tag_id")
  @Nullable
  protected String documentTagId;

  @JsonProperty("text_value")
  @Nullable
  protected String textValue;

  @JsonProperty("checkbox_value")
  @Nullable
  protected Boolean checkboxValue;

  @JsonProperty("date_value")
  @JsonSerialize(using = DateUtils.DateSerializer.class)
  @JsonDeserialize(using = DateUtils.DateDeserializer.class)
  @Nullable
  protected Date dateValue;

  public SignRequestPrefillTag() {
    super();
  }

  protected SignRequestPrefillTag(Builder builder) {
    super();
    this.documentTagId = builder.documentTagId;
    this.textValue = builder.textValue;
    this.checkboxValue = builder.checkboxValue;
    this.dateValue = builder.dateValue;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getDocumentTagId() {
    return documentTagId;
  }

  public String getTextValue() {
    return textValue;
  }

  public Boolean getCheckboxValue() {
    return checkboxValue;
  }

  public Date getDateValue() {
    return dateValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignRequestPrefillTag casted = (SignRequestPrefillTag) o;
    return Objects.equals(documentTagId, casted.documentTagId)
        && Objects.equals(textValue, casted.textValue)
        && Objects.equals(checkboxValue, casted.checkboxValue)
        && Objects.equals(dateValue, casted.dateValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(documentTagId, textValue, checkboxValue, dateValue);
  }

  @Override
  public String toString() {
    return "SignRequestPrefillTag{"
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
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String documentTagId;

    protected String textValue;

    protected Boolean checkboxValue;

    protected Date dateValue;

    public Builder documentTagId(String documentTagId) {
      this.documentTagId = documentTagId;
      this.markNullableFieldAsSet("document_tag_id");
      return this;
    }

    public Builder textValue(String textValue) {
      this.textValue = textValue;
      this.markNullableFieldAsSet("text_value");
      return this;
    }

    public Builder checkboxValue(Boolean checkboxValue) {
      this.checkboxValue = checkboxValue;
      this.markNullableFieldAsSet("checkbox_value");
      return this;
    }

    public Builder dateValue(Date dateValue) {
      this.dateValue = dateValue;
      this.markNullableFieldAsSet("date_value");
      return this;
    }

    public SignRequestPrefillTag build() {
      return new SignRequestPrefillTag(this);
    }
  }
}
