package com.box.sdkgen.schemas.v2025r0.docgendocumentgenerationdatav2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import java.util.Objects;

/** The schema for for creating a Box Doc Gen job request. */
@JsonFilter("nullablePropertyFilter")
public class DocGenDocumentGenerationDataV2025R0 extends SerializableObject {

  /** File name of the output file. */
  @JsonProperty("generated_file_name")
  protected final String generatedFileName;

  @JsonProperty("user_input")
  protected final Map<String, Object> userInput;

  /**
   * An optional identifier supplied by the caller for this Box Doc Gen job. The value is returned
   * on job responses for correlation only. It is not a Box resource ID, is not required to be
   * unique, and cannot be used to look up the job.
   */
  @JsonProperty("external_id")
  protected String externalId;

  public DocGenDocumentGenerationDataV2025R0(
      @JsonProperty("generated_file_name") String generatedFileName,
      @JsonProperty("user_input") Map<String, Object> userInput) {
    super();
    this.generatedFileName = generatedFileName;
    this.userInput = userInput;
  }

  protected DocGenDocumentGenerationDataV2025R0(Builder builder) {
    super();
    this.generatedFileName = builder.generatedFileName;
    this.userInput = builder.userInput;
    this.externalId = builder.externalId;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getGeneratedFileName() {
    return generatedFileName;
  }

  public Map<String, Object> getUserInput() {
    return userInput;
  }

  public String getExternalId() {
    return externalId;
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
        && Objects.equals(userInput, casted.userInput)
        && Objects.equals(externalId, casted.externalId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(generatedFileName, userInput, externalId);
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
        + ", "
        + "externalId='"
        + externalId
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String generatedFileName;

    protected final Map<String, Object> userInput;

    protected String externalId;

    public Builder(String generatedFileName, Map<String, Object> userInput) {
      super();
      this.generatedFileName = generatedFileName;
      this.userInput = userInput;
    }

    public Builder externalId(String externalId) {
      this.externalId = externalId;
      return this;
    }

    public DocGenDocumentGenerationDataV2025R0 build() {
      return new DocGenDocumentGenerationDataV2025R0(this);
    }
  }
}
