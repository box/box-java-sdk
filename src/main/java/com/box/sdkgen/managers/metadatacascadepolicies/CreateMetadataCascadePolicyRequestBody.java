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

  /**
   * The ID of the folder to apply the policy to. This folder will need to already have an instance
   * of the targeted metadata template applied to it.
   */
  @JsonProperty("folder_id")
  protected final String folderId;

  /**
   * The scope of the targeted metadata template. This template will need to already have an
   * instance applied to the targeted folder.
   */
  @JsonDeserialize(
      using =
          CreateMetadataCascadePolicyRequestBodyScopeField
              .CreateMetadataCascadePolicyRequestBodyScopeFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateMetadataCascadePolicyRequestBodyScopeField
              .CreateMetadataCascadePolicyRequestBodyScopeFieldSerializer.class)
  protected final EnumWrapper<CreateMetadataCascadePolicyRequestBodyScopeField> scope;

  /**
   * The key of the targeted metadata template. This template will need to already have an instance
   * applied to the targeted folder.
   *
   * <p>In many cases the template key is automatically derived of its display name, for example
   * `Contract Template` would become `contractTemplate`. In some cases the creator of the template
   * will have provided its own template key.
   *
   * <p>Please [list the templates for an enterprise][list], or get all instances on a [file][file]
   * or [folder][folder] to inspect a template's key.
   *
   * <p>[list]: e://get-metadata-templates-enterprise [file]: e://get-files-id-metadata [folder]:
   * e://get-folders-id-metadata
   */
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
