package com.box.sdkgen.managers.skills;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.keywordskillcard.KeywordSkillCard;
import com.box.sdkgen.schemas.skillcard.SkillCard;
import com.box.sdkgen.schemas.statusskillcard.StatusSkillCard;
import com.box.sdkgen.schemas.timelineskillcard.TimelineSkillCard;
import com.box.sdkgen.schemas.transcriptskillcard.TranscriptSkillCard;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateBoxSkillCardsOnFileRequestBody extends SerializableObject {

  @JsonDeserialize(
      using =
          UpdateBoxSkillCardsOnFileRequestBodyOpField
              .UpdateBoxSkillCardsOnFileRequestBodyOpFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateBoxSkillCardsOnFileRequestBodyOpField
              .UpdateBoxSkillCardsOnFileRequestBodyOpFieldSerializer.class)
  protected EnumWrapper<UpdateBoxSkillCardsOnFileRequestBodyOpField> op;

  protected String path;

  protected SkillCard value;

  public UpdateBoxSkillCardsOnFileRequestBody() {
    super();
  }

  protected UpdateBoxSkillCardsOnFileRequestBody(Builder builder) {
    super();
    this.op = builder.op;
    this.path = builder.path;
    this.value = builder.value;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<UpdateBoxSkillCardsOnFileRequestBodyOpField> getOp() {
    return op;
  }

  public String getPath() {
    return path;
  }

  public SkillCard getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateBoxSkillCardsOnFileRequestBody casted = (UpdateBoxSkillCardsOnFileRequestBody) o;
    return Objects.equals(op, casted.op)
        && Objects.equals(path, casted.path)
        && Objects.equals(value, casted.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(op, path, value);
  }

  @Override
  public String toString() {
    return "UpdateBoxSkillCardsOnFileRequestBody{"
        + "op='"
        + op
        + '\''
        + ", "
        + "path='"
        + path
        + '\''
        + ", "
        + "value='"
        + value
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<UpdateBoxSkillCardsOnFileRequestBodyOpField> op;

    protected String path;

    protected SkillCard value;

    public Builder op(UpdateBoxSkillCardsOnFileRequestBodyOpField op) {
      this.op = new EnumWrapper<UpdateBoxSkillCardsOnFileRequestBodyOpField>(op);
      return this;
    }

    public Builder op(EnumWrapper<UpdateBoxSkillCardsOnFileRequestBodyOpField> op) {
      this.op = op;
      return this;
    }

    public Builder path(String path) {
      this.path = path;
      return this;
    }

    public Builder value(KeywordSkillCard value) {
      this.value = new SkillCard(value);
      return this;
    }

    public Builder value(TimelineSkillCard value) {
      this.value = new SkillCard(value);
      return this;
    }

    public Builder value(TranscriptSkillCard value) {
      this.value = new SkillCard(value);
      return this;
    }

    public Builder value(StatusSkillCard value) {
      this.value = new SkillCard(value);
      return this;
    }

    public Builder value(SkillCard value) {
      this.value = value;
      return this;
    }

    public UpdateBoxSkillCardsOnFileRequestBody build() {
      return new UpdateBoxSkillCardsOnFileRequestBody(this);
    }
  }
}
