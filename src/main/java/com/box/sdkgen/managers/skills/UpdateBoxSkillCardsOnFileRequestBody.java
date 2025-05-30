package com.box.sdkgen.managers.skills;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.keywordskillcardorstatusskillcardortimelineskillcardortranscriptskillcard.KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

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

  protected KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard value;

  public UpdateBoxSkillCardsOnFileRequestBody() {
    super();
  }

  protected UpdateBoxSkillCardsOnFileRequestBody(
      UpdateBoxSkillCardsOnFileRequestBodyBuilder builder) {
    super();
    this.op = builder.op;
    this.path = builder.path;
    this.value = builder.value;
  }

  public EnumWrapper<UpdateBoxSkillCardsOnFileRequestBodyOpField> getOp() {
    return op;
  }

  public String getPath() {
    return path;
  }

  public KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard getValue() {
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

  public static class UpdateBoxSkillCardsOnFileRequestBodyBuilder {

    protected EnumWrapper<UpdateBoxSkillCardsOnFileRequestBodyOpField> op;

    protected String path;

    protected KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard value;

    public UpdateBoxSkillCardsOnFileRequestBodyBuilder op(
        UpdateBoxSkillCardsOnFileRequestBodyOpField op) {
      this.op = new EnumWrapper<UpdateBoxSkillCardsOnFileRequestBodyOpField>(op);
      return this;
    }

    public UpdateBoxSkillCardsOnFileRequestBodyBuilder op(
        EnumWrapper<UpdateBoxSkillCardsOnFileRequestBodyOpField> op) {
      this.op = op;
      return this;
    }

    public UpdateBoxSkillCardsOnFileRequestBodyBuilder path(String path) {
      this.path = path;
      return this;
    }

    public UpdateBoxSkillCardsOnFileRequestBodyBuilder value(
        KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard value) {
      this.value = value;
      return this;
    }

    public UpdateBoxSkillCardsOnFileRequestBody build() {
      return new UpdateBoxSkillCardsOnFileRequestBody(this);
    }
  }
}
