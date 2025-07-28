package com.box.sdkgen.managers.metadatacascadepolicies;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateMetadataCascadePolicyRequestBody extends SerializableObject {

  @JsonProperty("folder_id")
  protected final String folderId;

  @JsonDeserialize(
      using =
          CreateMetadataCascadePolicyRequestBodyScopeField
              .CreateMetadataCascadePolicyRequestBodyScopeFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateMetadataCascadePolicyRequestBodyScopeField
              .CreateMetadataCascadePolicyRequestBodyScopeFieldSerializer.class)
  protected final EnumWrapper<CreateMetadataCascadePolicyRequestBodyScopeField> scope;

  protected final String templateKey;

  public CreateMetadataCascadePolicyRequestBody(
      String folderId, CreateMetadataCascadePolicyRequestBodyScopeField scope, String templateKey) {
    super();
    this.folderId = folderId;
    this.scope = new EnumWrapper<CreateMetadataCascadePolicyRequestBodyScopeField>(scope);
    this.templateKey = templateKey;
  }

  public CreateMetadataCascadePolicyRequestBody(
      @JsonProperty("folder_id") String folderId,
      @JsonProperty("scope") EnumWrapper<CreateMetadataCascadePolicyRequestBodyScopeField> scope,
      @JsonProperty("templateKey") String templateKey) {
    super();
    this.folderId = folderId;
    this.scope = scope;
    this.templateKey = templateKey;
  }

  public String getFolderId() {
    return folderId;
  }

  public EnumWrapper<CreateMetadataCascadePolicyRequestBodyScopeField> getScope() {
    return scope;
  }

  public String getTemplateKey() {
    return templateKey;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateMetadataCascadePolicyRequestBody casted = (CreateMetadataCascadePolicyRequestBody) o;
    return Objects.equals(folderId, casted.folderId)
        && Objects.equals(scope, casted.scope)
        && Objects.equals(templateKey, casted.templateKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(folderId, scope, templateKey);
  }

  @Override
  public String toString() {
    return "CreateMetadataCascadePolicyRequestBody{"
        + "folderId='"
        + folderId
        + '\''
        + ", "
        + "scope='"
        + scope
        + '\''
        + ", "
        + "templateKey='"
        + templateKey
        + '\''
        + "}";
  }
}
