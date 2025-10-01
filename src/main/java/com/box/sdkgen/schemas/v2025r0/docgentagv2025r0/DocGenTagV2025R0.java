package com.box.sdkgen.schemas.v2025r0.docgentagv2025r0;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

/** A Box Doc Gen template tag object. */
@JsonFilter("nullablePropertyFilter")
public class DocGenTagV2025R0 extends SerializableObject {

  /** The content of the tag. */
  @JsonProperty("tag_content")
  protected final String tagContent;

  /** Type of the tag. */
  @JsonDeserialize(
      using = DocGenTagV2025R0TagTypeField.DocGenTagV2025R0TagTypeFieldDeserializer.class)
  @JsonSerialize(using = DocGenTagV2025R0TagTypeField.DocGenTagV2025R0TagTypeFieldSerializer.class)
  @JsonProperty("tag_type")
  protected final EnumWrapper<DocGenTagV2025R0TagTypeField> tagType;

  /** List of the paths. */
  @JsonProperty("json_paths")
  protected final List<String> jsonPaths;

  public DocGenTagV2025R0(
      String tagContent, DocGenTagV2025R0TagTypeField tagType, List<String> jsonPaths) {
    super();
    this.tagContent = tagContent;
    this.tagType = new EnumWrapper<DocGenTagV2025R0TagTypeField>(tagType);
    this.jsonPaths = jsonPaths;
  }

  public DocGenTagV2025R0(
      @JsonProperty("tag_content") String tagContent,
      @JsonProperty("tag_type") EnumWrapper<DocGenTagV2025R0TagTypeField> tagType,
      @JsonProperty("json_paths") List<String> jsonPaths) {
    super();
    this.tagContent = tagContent;
    this.tagType = tagType;
    this.jsonPaths = jsonPaths;
  }

  public String getTagContent() {
    return tagContent;
  }

  public EnumWrapper<DocGenTagV2025R0TagTypeField> getTagType() {
    return tagType;
  }

  public List<String> getJsonPaths() {
    return jsonPaths;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocGenTagV2025R0 casted = (DocGenTagV2025R0) o;
    return Objects.equals(tagContent, casted.tagContent)
        && Objects.equals(tagType, casted.tagType)
        && Objects.equals(jsonPaths, casted.jsonPaths);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tagContent, tagType, jsonPaths);
  }

  @Override
  public String toString() {
    return "DocGenTagV2025R0{"
        + "tagContent='"
        + tagContent
        + '\''
        + ", "
        + "tagType='"
        + tagType
        + '\''
        + ", "
        + "jsonPaths='"
        + jsonPaths
        + '\''
        + "}";
  }
}
