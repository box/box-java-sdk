package com.box.sdkgen.schemas.transcriptskillcard;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TranscriptSkillCardEntriesField extends SerializableObject {

  /**
   * The text of the entry. This would be the transcribed text assigned to the entry on the
   * timeline.
   */
  protected String text;

  /**
   * Defines when a transcribed bit of text appears. This only includes a start time and no end
   * time.
   */
  protected List<TranscriptSkillCardEntriesAppearsField> appears;

  public TranscriptSkillCardEntriesField() {
    super();
  }

  protected TranscriptSkillCardEntriesField(Builder builder) {
    super();
    this.text = builder.text;
    this.appears = builder.appears;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getText() {
    return text;
  }

  public List<TranscriptSkillCardEntriesAppearsField> getAppears() {
    return appears;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TranscriptSkillCardEntriesField casted = (TranscriptSkillCardEntriesField) o;
    return Objects.equals(text, casted.text) && Objects.equals(appears, casted.appears);
  }

  @Override
  public int hashCode() {
    return Objects.hash(text, appears);
  }

  @Override
  public String toString() {
    return "TranscriptSkillCardEntriesField{"
        + "text='"
        + text
        + '\''
        + ", "
        + "appears='"
        + appears
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String text;

    protected List<TranscriptSkillCardEntriesAppearsField> appears;

    public Builder text(String text) {
      this.text = text;
      return this;
    }

    public Builder appears(List<TranscriptSkillCardEntriesAppearsField> appears) {
      this.appears = appears;
      return this;
    }

    public TranscriptSkillCardEntriesField build() {
      return new TranscriptSkillCardEntriesField(this);
    }
  }
}
