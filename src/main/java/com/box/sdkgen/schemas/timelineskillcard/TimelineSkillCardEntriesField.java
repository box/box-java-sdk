package com.box.sdkgen.schemas.timelineskillcard;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class TimelineSkillCardEntriesField extends SerializableObject {

  protected String text;

  protected List<TimelineSkillCardEntriesAppearsField> appears;

  @JsonProperty("image_url")
  protected String imageUrl;

  public TimelineSkillCardEntriesField() {
    super();
  }

  protected TimelineSkillCardEntriesField(TimelineSkillCardEntriesFieldBuilder builder) {
    super();
    this.text = builder.text;
    this.appears = builder.appears;
    this.imageUrl = builder.imageUrl;
  }

  public String getText() {
    return text;
  }

  public List<TimelineSkillCardEntriesAppearsField> getAppears() {
    return appears;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TimelineSkillCardEntriesField casted = (TimelineSkillCardEntriesField) o;
    return Objects.equals(text, casted.text)
        && Objects.equals(appears, casted.appears)
        && Objects.equals(imageUrl, casted.imageUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(text, appears, imageUrl);
  }

  @Override
  public String toString() {
    return "TimelineSkillCardEntriesField{"
        + "text='"
        + text
        + '\''
        + ", "
        + "appears='"
        + appears
        + '\''
        + ", "
        + "imageUrl='"
        + imageUrl
        + '\''
        + "}";
  }

  public static class TimelineSkillCardEntriesFieldBuilder {

    protected String text;

    protected List<TimelineSkillCardEntriesAppearsField> appears;

    protected String imageUrl;

    public TimelineSkillCardEntriesFieldBuilder text(String text) {
      this.text = text;
      return this;
    }

    public TimelineSkillCardEntriesFieldBuilder appears(
        List<TimelineSkillCardEntriesAppearsField> appears) {
      this.appears = appears;
      return this;
    }

    public TimelineSkillCardEntriesFieldBuilder imageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
      return this;
    }

    public TimelineSkillCardEntriesField build() {
      return new TimelineSkillCardEntriesField(this);
    }
  }
}
