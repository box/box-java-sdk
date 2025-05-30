package com.box.sdkgen.schemas.transcriptskillcard;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class TranscriptSkillCardEntriesAppearsField extends SerializableObject {

  protected Long start;

  public TranscriptSkillCardEntriesAppearsField() {
    super();
  }

  protected TranscriptSkillCardEntriesAppearsField(
      TranscriptSkillCardEntriesAppearsFieldBuilder builder) {
    super();
    this.start = builder.start;
  }

  public Long getStart() {
    return start;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TranscriptSkillCardEntriesAppearsField casted = (TranscriptSkillCardEntriesAppearsField) o;
    return Objects.equals(start, casted.start);
  }

  @Override
  public int hashCode() {
    return Objects.hash(start);
  }

  @Override
  public String toString() {
    return "TranscriptSkillCardEntriesAppearsField{" + "start='" + start + '\'' + "}";
  }

  public static class TranscriptSkillCardEntriesAppearsFieldBuilder {

    protected Long start;

    public TranscriptSkillCardEntriesAppearsFieldBuilder start(Long start) {
      this.start = start;
      return this;
    }

    public TranscriptSkillCardEntriesAppearsField build() {
      return new TranscriptSkillCardEntriesAppearsField(this);
    }
  }
}
