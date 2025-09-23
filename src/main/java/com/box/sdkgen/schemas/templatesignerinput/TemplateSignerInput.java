package com.box.sdkgen.schemas.templatesignerinput;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.schemas.signrequestprefilltag.SignRequestPrefillTag;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TemplateSignerInput extends SignRequestPrefillTag {

  @JsonDeserialize(
      using = TemplateSignerInputTypeField.TemplateSignerInputTypeFieldDeserializer.class)
  @JsonSerialize(using = TemplateSignerInputTypeField.TemplateSignerInputTypeFieldSerializer.class)
  protected EnumWrapper<TemplateSignerInputTypeField> type;

  @JsonDeserialize(
      using =
          TemplateSignerInputContentTypeField.TemplateSignerInputContentTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          TemplateSignerInputContentTypeField.TemplateSignerInputContentTypeFieldSerializer.class)
  @JsonProperty("content_type")
  protected EnumWrapper<TemplateSignerInputContentTypeField> contentType;

  @JsonProperty("is_required")
  protected Boolean isRequired;

  @JsonProperty("page_index")
  protected final long pageIndex;

  @JsonProperty("document_id")
  @Nullable
  protected String documentId;

  @JsonProperty("dropdown_choices")
  @Nullable
  protected List<String> dropdownChoices;

  @JsonProperty("group_id")
  @Nullable
  protected String groupId;

  protected TemplateSignerInputCoordinatesField coordinates;

  protected TemplateSignerInputDimensionsField dimensions;

  @Nullable protected String label;

  @JsonProperty("read_only")
  protected Boolean readOnly;

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
        && Objects.equals(readOnly, casted.readOnly);
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
        readOnly);
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
