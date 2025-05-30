package com.box.sdkgen.schemas.transcriptskillcard;

import com.box.sdkgen.internal.SerializableObject;
import java.util.List;
import java.util.Objects;

public class TranscriptSkillCardEntriesField extends SerializableObject {

  protected String text;

  protected List<TranscriptSkillCardEntriesAppearsField> appears;

  public TranscriptSkillCardEntriesField() {
    super();
  }

  protected TranscriptSkillCardEntriesField(TranscriptSkillCardEntriesFieldBuilder builder) {
    super();
    this.text = builder.text;
    this.appears = builder.appears;
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

  public static class TranscriptSkillCardEntriesFieldBuilder {

    protected String text;

    protected List<TranscriptSkillCardEntriesAppearsField> appears;

    public TranscriptSkillCardEntriesFieldBuilder text(String text) {
      this.text = text;
      return this;
    }

    public TranscriptSkillCardEntriesFieldBuilder appears(
        List<TranscriptSkillCardEntriesAppearsField> appears) {
      this.appears = appears;
      return this;
    }

    public TranscriptSkillCardEntriesField build() {
      return new TranscriptSkillCardEntriesField(this);
    }
  }
}
