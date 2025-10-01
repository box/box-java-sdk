package com.box.sdkgen.schemas.transcriptskillcard;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TranscriptSkillCardSkillField extends SerializableObject {

  /** The value will always be `service`. */
  @JsonDeserialize(
      using = TranscriptSkillCardSkillTypeField.TranscriptSkillCardSkillTypeFieldDeserializer.class)
  @JsonSerialize(
      using = TranscriptSkillCardSkillTypeField.TranscriptSkillCardSkillTypeFieldSerializer.class)
  protected EnumWrapper<TranscriptSkillCardSkillTypeField> type;

  /** A custom identifier that represent the service that applied this metadata. */
  protected final String id;

  public TranscriptSkillCardSkillField(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<TranscriptSkillCardSkillTypeField>(
            TranscriptSkillCardSkillTypeField.SERVICE);
  }

  protected TranscriptSkillCardSkillField(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<TranscriptSkillCardSkillTypeField> getType() {
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
    TranscriptSkillCardSkillField casted = (TranscriptSkillCardSkillField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "TranscriptSkillCardSkillField{"
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

    protected EnumWrapper<TranscriptSkillCardSkillTypeField> type;

    protected final String id;

    public Builder(String id) {
      super();
      this.id = id;
      this.type =
          new EnumWrapper<TranscriptSkillCardSkillTypeField>(
              TranscriptSkillCardSkillTypeField.SERVICE);
    }

    public Builder type(TranscriptSkillCardSkillTypeField type) {
      this.type = new EnumWrapper<TranscriptSkillCardSkillTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<TranscriptSkillCardSkillTypeField> type) {
      this.type = type;
      return this;
    }

    public TranscriptSkillCardSkillField build() {
      return new TranscriptSkillCardSkillField(this);
    }
  }
}
