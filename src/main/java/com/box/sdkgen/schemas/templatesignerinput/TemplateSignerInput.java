package com.box.sdkgen.schemas.templatesignerinput;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.schemas.signrequestprefilltag.SignRequestPrefillTag;
import com.box.sdkgen.schemas.signrequestsignerinputcustomvalidation.SignRequestSignerInputCustomValidation;
import com.box.sdkgen.schemas.signrequestsignerinputdateasiavalidation.SignRequestSignerInputDateAsiaValidation;
import com.box.sdkgen.schemas.signrequestsignerinputdateeuvalidation.SignRequestSignerInputDateEuValidation;
import com.box.sdkgen.schemas.signrequestsignerinputdateisovalidation.SignRequestSignerInputDateIsoValidation;
import com.box.sdkgen.schemas.signrequestsignerinputdateusvalidation.SignRequestSignerInputDateUsValidation;
import com.box.sdkgen.schemas.signrequestsignerinputemailvalidation.SignRequestSignerInputEmailValidation;
import com.box.sdkgen.schemas.signrequestsignerinputnumberwithcommavalidation.SignRequestSignerInputNumberWithCommaValidation;
import com.box.sdkgen.schemas.signrequestsignerinputnumberwithperiodvalidation.SignRequestSignerInputNumberWithPeriodValidation;
import com.box.sdkgen.schemas.signrequestsignerinputssnvalidation.SignRequestSignerInputSsnValidation;
import com.box.sdkgen.schemas.signrequestsignerinputvalidation.SignRequestSignerInputValidation;
import com.box.sdkgen.schemas.signrequestsignerinputzip4validation.SignRequestSignerInputZip4Validation;
import com.box.sdkgen.schemas.signrequestsignerinputzipvalidation.SignRequestSignerInputZipValidation;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

/** Input created by a Signer on a Template. */
@JsonFilter("nullablePropertyFilter")
public class TemplateSignerInput extends SignRequestPrefillTag {

  /** Type of input. */
  @JsonDeserialize(
      using = TemplateSignerInputTypeField.TemplateSignerInputTypeFieldDeserializer.class)
  @JsonSerialize(using = TemplateSignerInputTypeField.TemplateSignerInputTypeFieldSerializer.class)
  protected EnumWrapper<TemplateSignerInputTypeField> type;

  /** Content type of input. */
  @JsonDeserialize(
      using =
          TemplateSignerInputContentTypeField.TemplateSignerInputContentTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          TemplateSignerInputContentTypeField.TemplateSignerInputContentTypeFieldSerializer.class)
  @JsonProperty("content_type")
  protected EnumWrapper<TemplateSignerInputContentTypeField> contentType;

  /** Whether or not the input is required. */
  @JsonProperty("is_required")
  protected Boolean isRequired;

  /** Index of page that the input is on. */
  @JsonProperty("page_index")
  protected final long pageIndex;

  /** Document identifier. */
  @JsonProperty("document_id")
  @Nullable
  protected String documentId;

  /**
   * When the input is of the type `dropdown` this values will be filled with all the dropdown
   * options.
   */
  @JsonProperty("dropdown_choices")
  @Nullable
  protected List<String> dropdownChoices;

  /** When the input is of type `radio` they can be grouped to gather with this identifier. */
  @JsonProperty("group_id")
  @Nullable
  protected String groupId;

  /** Where the input is located on a page. */
  protected TemplateSignerInputCoordinatesField coordinates;

  /** The size of the input. */
  protected TemplateSignerInputDimensionsField dimensions;

  /** The label field is used especially for text, attachment, radio, and checkbox type inputs. */
  @Nullable protected String label;

  /** Indicates whether this input is read-only (cannot be modified by signers). */
  @JsonProperty("read_only")
  protected Boolean readOnly;

  /**
   * Specifies the formatting rules that signers must follow for text field inputs. If set, this
   * validation is mandatory.
   */
  protected SignRequestSignerInputValidation validation;

  public TemplateSignerInput(@JsonProperty("page_index") long pageIndex) {
    super();
    this.pageIndex = pageIndex;
  }

  protected TemplateSignerInput(Builder builder) {
    super(builder);
    this.type = builder.type;
    this.contentType = builder.contentType;
    this.isRequired = builder.isRequired;
    this.pageIndex = builder.pageIndex;
    this.documentId = builder.documentId;
    this.dropdownChoices = builder.dropdownChoices;
    this.groupId = builder.groupId;
    this.coordinates = builder.coordinates;
    this.dimensions = builder.dimensions;
    this.label = builder.label;
    this.readOnly = builder.readOnly;
    this.validation = builder.validation;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<TemplateSignerInputTypeField> getType() {
    return type;
  }

  public EnumWrapper<TemplateSignerInputContentTypeField> getContentType() {
    return contentType;
  }

  public Boolean getIsRequired() {
    return isRequired;
  }

  public long getPageIndex() {
    return pageIndex;
  }

  public String getDocumentId() {
    return documentId;
  }

  public List<String> getDropdownChoices() {
    return dropdownChoices;
  }

  public String getGroupId() {
    return groupId;
  }

  public TemplateSignerInputCoordinatesField getCoordinates() {
    return coordinates;
  }

  public TemplateSignerInputDimensionsField getDimensions() {
    return dimensions;
  }

  public String getLabel() {
    return label;
  }

  public Boolean getReadOnly() {
    return readOnly;
  }

  public SignRequestSignerInputValidation getValidation() {
    return validation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TemplateSignerInput casted = (TemplateSignerInput) o;
    return Objects.equals(documentTagId, casted.documentTagId)
        && Objects.equals(textValue, casted.textValue)
        && Objects.equals(checkboxValue, casted.checkboxValue)
        && Objects.equals(dateValue, casted.dateValue)
        && Objects.equals(type, casted.type)
        && Objects.equals(contentType, casted.contentType)
        && Objects.equals(isRequired, casted.isRequired)
        && Objects.equals(pageIndex, casted.pageIndex)
        && Objects.equals(documentId, casted.documentId)
        && Objects.equals(dropdownChoices, casted.dropdownChoices)
        && Objects.equals(groupId, casted.groupId)
        && Objects.equals(coordinates, casted.coordinates)
        && Objects.equals(dimensions, casted.dimensions)
        && Objects.equals(label, casted.label)
        && Objects.equals(readOnly, casted.readOnly)
        && Objects.equals(validation, casted.validation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        documentTagId,
        textValue,
        checkboxValue,
        dateValue,
        type,
        contentType,
        isRequired,
        pageIndex,
        documentId,
        dropdownChoices,
        groupId,
        coordinates,
        dimensions,
        label,
        readOnly,
        validation);
  }

  @Override
  public String toString() {
    return "TemplateSignerInput{"
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
        + "isRequired='"
        + isRequired
        + '\''
        + ", "
        + "pageIndex='"
        + pageIndex
        + '\''
        + ", "
        + "documentId='"
        + documentId
        + '\''
        + ", "
        + "dropdownChoices='"
        + dropdownChoices
        + '\''
        + ", "
        + "groupId='"
        + groupId
        + '\''
        + ", "
        + "coordinates='"
        + coordinates
        + '\''
        + ", "
        + "dimensions='"
        + dimensions
        + '\''
        + ", "
        + "label='"
        + label
        + '\''
        + ", "
        + "readOnly='"
        + readOnly
        + '\''
        + ", "
        + "validation='"
        + validation
        + '\''
        + "}";
  }

  public static class Builder extends SignRequestPrefillTag.Builder {

    protected EnumWrapper<TemplateSignerInputTypeField> type;

    protected EnumWrapper<TemplateSignerInputContentTypeField> contentType;

    protected Boolean isRequired;

    protected final long pageIndex;

    protected String documentId;

    protected List<String> dropdownChoices;

    protected String groupId;

    protected TemplateSignerInputCoordinatesField coordinates;

    protected TemplateSignerInputDimensionsField dimensions;

    protected String label;

    protected Boolean readOnly;

    protected SignRequestSignerInputValidation validation;

    public Builder(long pageIndex) {
      super();
      this.pageIndex = pageIndex;
    }

    public Builder type(TemplateSignerInputTypeField type) {
      this.type = new EnumWrapper<TemplateSignerInputTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<TemplateSignerInputTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder contentType(TemplateSignerInputContentTypeField contentType) {
      this.contentType = new EnumWrapper<TemplateSignerInputContentTypeField>(contentType);
      return this;
    }

    public Builder contentType(EnumWrapper<TemplateSignerInputContentTypeField> contentType) {
      this.contentType = contentType;
      return this;
    }

    public Builder isRequired(Boolean isRequired) {
      this.isRequired = isRequired;
      return this;
    }

    public Builder documentId(String documentId) {
      this.documentId = documentId;
      this.markNullableFieldAsSet("document_id");
      return this;
    }

    public Builder dropdownChoices(List<String> dropdownChoices) {
      this.dropdownChoices = dropdownChoices;
      this.markNullableFieldAsSet("dropdown_choices");
      return this;
    }

    public Builder groupId(String groupId) {
      this.groupId = groupId;
      this.markNullableFieldAsSet("group_id");
      return this;
    }

    public Builder coordinates(TemplateSignerInputCoordinatesField coordinates) {
      this.coordinates = coordinates;
      return this;
    }

    public Builder dimensions(TemplateSignerInputDimensionsField dimensions) {
      this.dimensions = dimensions;
      return this;
    }

    public Builder label(String label) {
      this.label = label;
      this.markNullableFieldAsSet("label");
      return this;
    }

    public Builder readOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
    }

    public Builder validation(SignRequestSignerInputEmailValidation validation) {
      this.validation = new SignRequestSignerInputValidation(validation);
      return this;
    }

    public Builder validation(SignRequestSignerInputCustomValidation validation) {
      this.validation = new SignRequestSignerInputValidation(validation);
      return this;
    }

    public Builder validation(SignRequestSignerInputZipValidation validation) {
      this.validation = new SignRequestSignerInputValidation(validation);
      return this;
    }

    public Builder validation(SignRequestSignerInputZip4Validation validation) {
      this.validation = new SignRequestSignerInputValidation(validation);
      return this;
    }

    public Builder validation(SignRequestSignerInputSsnValidation validation) {
      this.validation = new SignRequestSignerInputValidation(validation);
      return this;
    }

    public Builder validation(SignRequestSignerInputNumberWithPeriodValidation validation) {
      this.validation = new SignRequestSignerInputValidation(validation);
      return this;
    }

    public Builder validation(SignRequestSignerInputNumberWithCommaValidation validation) {
      this.validation = new SignRequestSignerInputValidation(validation);
      return this;
    }

    public Builder validation(SignRequestSignerInputDateIsoValidation validation) {
      this.validation = new SignRequestSignerInputValidation(validation);
      return this;
    }

    public Builder validation(SignRequestSignerInputDateUsValidation validation) {
      this.validation = new SignRequestSignerInputValidation(validation);
      return this;
    }

    public Builder validation(SignRequestSignerInputDateEuValidation validation) {
      this.validation = new SignRequestSignerInputValidation(validation);
      return this;
    }

    public Builder validation(SignRequestSignerInputDateAsiaValidation validation) {
      this.validation = new SignRequestSignerInputValidation(validation);
      return this;
    }

    public Builder validation(SignRequestSignerInputValidation validation) {
      this.validation = validation;
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
    public Builder dateValue(OffsetDateTime dateValue) {
      this.dateValue = dateValue;
      this.markNullableFieldAsSet("date_value");
      return this;
    }

    public TemplateSignerInput build() {
      return new TemplateSignerInput(this);
    }
  }
}
