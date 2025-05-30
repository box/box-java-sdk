package com.box.sdkgen.managers.metadatatemplates;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class CreateMetadataTemplateRequestBody extends SerializableObject {

  protected final String scope;

  protected String templateKey;

  protected final String displayName;

  protected Boolean hidden;

  protected List<CreateMetadataTemplateRequestBodyFieldsField> fields;

  protected Boolean copyInstanceOnItemCopy;

  public CreateMetadataTemplateRequestBody(
      @JsonProperty("scope") String scope, @JsonProperty("displayName") String displayName) {
    super();
    this.scope = scope;
    this.displayName = displayName;
  }

  protected CreateMetadataTemplateRequestBody(CreateMetadataTemplateRequestBodyBuilder builder) {
    super();
    this.scope = builder.scope;
    this.templateKey = builder.templateKey;
    this.displayName = builder.displayName;
    this.hidden = builder.hidden;
    this.fields = builder.fields;
    this.copyInstanceOnItemCopy = builder.copyInstanceOnItemCopy;
  }

  public String getScope() {
    return scope;
  }

  public String getTemplateKey() {
    return templateKey;
  }

  public String getDisplayName() {
    return displayName;
  }

  public Boolean getHidden() {
    return hidden;
  }

  public List<CreateMetadataTemplateRequestBodyFieldsField> getFields() {
    return fields;
  }

  public Boolean getCopyInstanceOnItemCopy() {
    return copyInstanceOnItemCopy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateMetadataTemplateRequestBody casted = (CreateMetadataTemplateRequestBody) o;
    return Objects.equals(scope, casted.scope)
        && Objects.equals(templateKey, casted.templateKey)
        && Objects.equals(displayName, casted.displayName)
        && Objects.equals(hidden, casted.hidden)
        && Objects.equals(fields, casted.fields)
        && Objects.equals(copyInstanceOnItemCopy, casted.copyInstanceOnItemCopy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(scope, templateKey, displayName, hidden, fields, copyInstanceOnItemCopy);
  }

  @Override
  public String toString() {
    return "CreateMetadataTemplateRequestBody{"
        + "scope='"
        + scope
        + '\''
        + ", "
        + "templateKey='"
        + templateKey
        + '\''
        + ", "
        + "displayName='"
        + displayName
        + '\''
        + ", "
        + "hidden='"
        + hidden
        + '\''
        + ", "
        + "fields='"
        + fields
        + '\''
        + ", "
        + "copyInstanceOnItemCopy='"
        + copyInstanceOnItemCopy
        + '\''
        + "}";
  }

  public static class CreateMetadataTemplateRequestBodyBuilder {

    protected final String scope;

    protected String templateKey;

    protected final String displayName;

    protected Boolean hidden;

    protected List<CreateMetadataTemplateRequestBodyFieldsField> fields;

    protected Boolean copyInstanceOnItemCopy;

    public CreateMetadataTemplateRequestBodyBuilder(String scope, String displayName) {
      this.scope = scope;
      this.displayName = displayName;
    }

    public CreateMetadataTemplateRequestBodyBuilder templateKey(String templateKey) {
      this.templateKey = templateKey;
      return this;
    }

    public CreateMetadataTemplateRequestBodyBuilder hidden(Boolean hidden) {
      this.hidden = hidden;
      return this;
    }

    public CreateMetadataTemplateRequestBodyBuilder fields(
        List<CreateMetadataTemplateRequestBodyFieldsField> fields) {
      this.fields = fields;
      return this;
    }

    public CreateMetadataTemplateRequestBodyBuilder copyInstanceOnItemCopy(
        Boolean copyInstanceOnItemCopy) {
      this.copyInstanceOnItemCopy = copyInstanceOnItemCopy;
      return this;
    }

    public CreateMetadataTemplateRequestBody build() {
      return new CreateMetadataTemplateRequestBody(this);
    }
  }
}
