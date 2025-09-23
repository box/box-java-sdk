package com.box.sdkgen.schemas.v2025r0.docgenbatchcreaterequestv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class DocGenBatchCreateRequestV2025R0DestinationFolderField extends SerializableObject {

  @JsonDeserialize(
      using =
          DocGenBatchCreateRequestV2025R0DestinationFolderTypeField
              .DocGenBatchCreateRequestV2025R0DestinationFolderTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          DocGenBatchCreateRequestV2025R0DestinationFolderTypeField
              .DocGenBatchCreateRequestV2025R0DestinationFolderTypeFieldSerializer.class)
  protected EnumWrapper<DocGenBatchCreateRequestV2025R0DestinationFolderTypeField> type;

  protected final String id;

  public DocGenBatchCreateRequestV2025R0DestinationFolderField(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<DocGenBatchCreateRequestV2025R0DestinationFolderTypeField>(
            DocGenBatchCreateRequestV2025R0DestinationFolderTypeField.FOLDER);
  }

  protected DocGenBatchCreateRequestV2025R0DestinationFolderField(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<DocGenBatchCreateRequestV2025R0DestinationFolderTypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocGenBatchCreateRequestV2025R0DestinationFolderField casted =
        (DocGenBatchCreateRequestV2025R0DestinationFolderField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "DocGenBatchCreateRequestV2025R0DestinationFolderField{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<DocGenBatchCreateRequestV2025R0DestinationFolderTypeField> type;

    protected final String id;

    public Builder(String id) {
      super();
      this.id = id;
      this.type =
          new EnumWrapper<DocGenBatchCreateRequestV2025R0DestinationFolderTypeField>(
              DocGenBatchCreateRequestV2025R0DestinationFolderTypeField.FOLDER);
    }

    public Builder type(DocGenBatchCreateRequestV2025R0DestinationFolderTypeField type) {
      this.type = new EnumWrapper<DocGenBatchCreateRequestV2025R0DestinationFolderTypeField>(type);
      return this;
    }

    public Builder type(
        EnumWrapper<DocGenBatchCreateRequestV2025R0DestinationFolderTypeField> type) {
      this.type = type;
      return this;
    }

    public DocGenBatchCreateRequestV2025R0DestinationFolderField build() {
      return new DocGenBatchCreateRequestV2025R0DestinationFolderField(this);
    }
  }
}
