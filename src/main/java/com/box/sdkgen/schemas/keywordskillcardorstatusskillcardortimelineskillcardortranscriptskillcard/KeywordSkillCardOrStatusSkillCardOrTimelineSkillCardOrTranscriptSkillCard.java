package com.box.sdkgen.schemas.keywordskillcardorstatusskillcardortimelineskillcardortranscriptskillcard;

import com.box.sdkgen.internal.OneOfFour;
import com.box.sdkgen.schemas.keywordskillcard.KeywordSkillCard;
import com.box.sdkgen.schemas.statusskillcard.StatusSkillCard;
import com.box.sdkgen.schemas.timelineskillcard.TimelineSkillCard;
import com.box.sdkgen.schemas.transcriptskillcard.TranscriptSkillCard;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;
import java.util.Date;

@JsonDeserialize(
    using =
        KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard
            .KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCardDeserializer
            .class)
@JsonSerialize(using = OneOfFour.OneOfFourSerializer.class)
public class KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard
    extends OneOfFour<KeywordSkillCard, StatusSkillCard, TimelineSkillCard, TranscriptSkillCard> {

  protected final Date createdAt;

  protected final String type;

  protected final String skillCardType;

  public KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard(
      KeywordSkillCard keywordSkillCard) {
    super(keywordSkillCard, null, null, null);
    this.createdAt = keywordSkillCard.getCreatedAt();
    this.type = EnumWrapper.convertToString(keywordSkillCard.getType());
    this.skillCardType = EnumWrapper.convertToString(keywordSkillCard.getSkillCardType());
  }

  public KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard(
      StatusSkillCard statusSkillCard) {
    super(null, statusSkillCard, null, null);
    this.createdAt = statusSkillCard.getCreatedAt();
    this.type = EnumWrapper.convertToString(statusSkillCard.getType());
    this.skillCardType = EnumWrapper.convertToString(statusSkillCard.getSkillCardType());
  }

  public KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard(
      TimelineSkillCard timelineSkillCard) {
    super(null, null, timelineSkillCard, null);
    this.createdAt = timelineSkillCard.getCreatedAt();
    this.type = EnumWrapper.convertToString(timelineSkillCard.getType());
    this.skillCardType = EnumWrapper.convertToString(timelineSkillCard.getSkillCardType());
  }

  public KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard(
      TranscriptSkillCard transcriptSkillCard) {
    super(null, null, null, transcriptSkillCard);
    this.createdAt = transcriptSkillCard.getCreatedAt();
    this.type = EnumWrapper.convertToString(transcriptSkillCard.getType());
    this.skillCardType = EnumWrapper.convertToString(transcriptSkillCard.getSkillCardType());
  }

  public boolean isKeywordSkillCard() {
    return value0 != null;
  }

  public KeywordSkillCard getKeywordSkillCard() {
    return value0;
  }

  public boolean isStatusSkillCard() {
    return value1 != null;
  }

  public StatusSkillCard getStatusSkillCard() {
    return value1;
  }

  public boolean isTimelineSkillCard() {
    return value2 != null;
  }

  public TimelineSkillCard getTimelineSkillCard() {
    return value2;
  }

  public boolean isTranscriptSkillCard() {
    return value3 != null;
  }

  public TranscriptSkillCard getTranscriptSkillCard() {
    return value3;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public String getType() {
    return type;
  }

  public String getSkillCardType() {
    return skillCardType;
  }

  static class KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCardDeserializer
      extends JsonDeserializer<
          KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard> {

    public KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCardDeserializer() {
      super();
    }

    @Override
    public KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard deserialize(
        JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("skill_card_type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "keyword":
            return new KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard(
                JsonManager.deserialize(node, KeywordSkillCard.class));
          case "status":
            return new KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard(
                JsonManager.deserialize(node, StatusSkillCard.class));
          case "timeline":
            return new KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard(
                JsonManager.deserialize(node, TimelineSkillCard.class));
          case "transcript":
            return new KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard(
                JsonManager.deserialize(node, TranscriptSkillCard.class));
        }
      }
      throw new JsonMappingException(
          jp,
          "Unable to deserialize KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard");
    }
  }
}
