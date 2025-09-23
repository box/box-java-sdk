package com.box.sdkgen.schemas.v2025r0.docgendocumentgenerationdatav2025r0;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class DocGenDocumentGenerationDataV2025R0 extends SerializableObject {

  @JsonProperty("generated_file_name")
  protected final String generatedFileName;

  @JsonProperty("user_input")
  protected final Map<String, Object> userInput;

  public DocGenDocumentGenerationDataV2025R0(
      @JsonProperty("generated_file_name") String generatedFileName,
      @JsonProperty("user_input") Map<String, Object> userInput) {
    super();
    this.generatedFileName = generatedFileName;
    this.userInput = userInput;
  }

  public String getGeneratedFileName() {
    return generatedFileName;
  }

  public Map<String, Object> getUserInput() {
    return userInput;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocGenDocumentGenerationDataV2025R0 casted = (DocGenDocumentGenerationDataV2025R0) o;
    return Objects.equals(generatedFileName, casted.generatedFileName)
        && Objects.equals(userInput, casted.userInput);
  }

  @Override
  public int hashCode() {
    return Objects.hash(generatedFileName, userInput);
  }

  @Override
  public String toString() {
    return "DocGenDocumentGenerationDataV2025R0{"
        + "generatedFileName='"
        + generatedFileName
        + '\''
        + ", "
        + "userInput='"
        + userInput
        + '\''
        + "}";
  }
}
