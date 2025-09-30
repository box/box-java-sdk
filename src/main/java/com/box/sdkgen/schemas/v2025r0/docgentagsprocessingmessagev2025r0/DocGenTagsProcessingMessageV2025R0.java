package com.box.sdkgen.schemas.v2025r0.docgentagsprocessingmessagev2025r0;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class DocGenTagsProcessingMessageV2025R0 extends SerializableObject {

  protected final String message;

  public DocGenTagsProcessingMessageV2025R0(@JsonProperty("message") String message) {
    super();
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocGenTagsProcessingMessageV2025R0 casted = (DocGenTagsProcessingMessageV2025R0) o;
    return Objects.equals(message, casted.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message);
  }

  @Override
  public String toString() {
    return "DocGenTagsProcessingMessageV2025R0{" + "message='" + message + '\'' + "}";
  }
}
