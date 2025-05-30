package com.box.sdkgen.schemas.signrequestprefilltag;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class SignRequestPrefillTag extends SerializableObject {

  @JsonProperty("document_tag_id")
  protected String documentTagId;

  @JsonProperty("text_value")
  protected String textValue;

  @JsonProperty("checkbox_value")
  protected Boolean checkboxValue;

  @JsonProperty("date_value")
  protected String dateValue;

  public SignRequestPrefillTag() {
    super();
  }

  protected SignRequestPrefillTag(SignRequestPrefillTagBuilder builder) {
    super();
    this.documentTagId = builder.documentTagId;
    this.textValue = builder.textValue;
    this.checkboxValue = builder.checkboxValue;
    this.dateValue = builder.dateValue;
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

  public String getDateValue() {
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

  public static class SignRequestPrefillTagBuilder {

    protected String documentTagId;

    protected String textValue;

    protected Boolean checkboxValue;

    protected String dateValue;

    public SignRequestPrefillTagBuilder documentTagId(String documentTagId) {
      this.documentTagId = documentTagId;
      return this;
    }

    public SignRequestPrefillTagBuilder textValue(String textValue) {
      this.textValue = textValue;
      return this;
    }

    public SignRequestPrefillTagBuilder checkboxValue(Boolean checkboxValue) {
      this.checkboxValue = checkboxValue;
      return this;
    }

    public SignRequestPrefillTagBuilder dateValue(String dateValue) {
      this.dateValue = dateValue;
      return this;
    }

    public SignRequestPrefillTag build() {
      return new SignRequestPrefillTag(this);
    }
  }
}
