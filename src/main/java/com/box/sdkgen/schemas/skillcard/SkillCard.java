package com.box.sdkgen.schemas.skillcard;

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
import java.time.OffsetDateTime;

@JsonDeserialize(using = SkillCard.SkillCardDeserializer.class)
@JsonSerialize(using = OneOfFour.OneOfFourSerializer.class)
public class SkillCard
    extends OneOfFour<KeywordSkillCard, TimelineSkillCard, TranscriptSkillCard, StatusSkillCard> {

  protected final OffsetDateTime createdAt;

  protected final String type;

  protected final String skillCardType;

  public SkillCard(KeywordSkillCard keywordSkillCard) {
    super(keywordSkillCard, null, null, null);
    this.createdAt = keywordSkillCard.getCreatedAt();
    this.type = EnumWrapper.convertToString(keywordSkillCard.getType());
    this.skillCardType = EnumWrapper.convertToString(keywordSkillCard.getSkillCardType());
  }

  public SkillCard(TimelineSkillCard timelineSkillCard) {
    super(null, timelineSkillCard, null, null);
    this.createdAt = timelineSkillCard.getCreatedAt();
    this.type = EnumWrapper.convertToString(timelineSkillCard.getType());
    this.skillCardType = EnumWrapper.convertToString(timelineSkillCard.getSkillCardType());
  }

  public SkillCard(TranscriptSkillCard transcriptSkillCard) {
    super(null, null, transcriptSkillCard, null);
    this.createdAt = transcriptSkillCard.getCreatedAt();
    this.type = EnumWrapper.convertToString(transcriptSkillCard.getType());
    this.skillCardType = EnumWrapper.convertToString(transcriptSkillCard.getSkillCardType());
  }

  public SkillCard(StatusSkillCard statusSkillCard) {
    super(null, null, null, statusSkillCard);
    this.createdAt = statusSkillCard.getCreatedAt();
    this.type = EnumWrapper.convertToString(statusSkillCard.getType());
    this.skillCardType = EnumWrapper.convertToString(statusSkillCard.getSkillCardType());
  }

  public boolean isKeywordSkillCard() {
    return value0 != null;
  }

  public KeywordSkillCard getKeywordSkillCard() {
    return value0;
  }

  public boolean isTimelineSkillCard() {
    return value1 != null;
  }

  public TimelineSkillCard getTimelineSkillCard() {
    return value1;
  }

  public boolean isTranscriptSkillCard() {
    return value2 != null;
  }

  public TranscriptSkillCard getTranscriptSkillCard() {
    return value2;
  }

  public boolean isStatusSkillCard() {
    return value3 != null;
  }

  public StatusSkillCard getStatusSkillCard() {
    return value3;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public String getType() {
    return type;
  }

  public String getSkillCardType() {
    return skillCardType;
  }

  static class SkillCardDeserializer extends JsonDeserializer<SkillCard> {

    public SkillCardDeserializer() {
      super();
    }

    @Override
    public SkillCard deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("skill_card_type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "keyword":
            return new SkillCard(JsonManager.deserialize(node, KeywordSkillCard.class));
          case "timeline":
            return new SkillCard(JsonManager.deserialize(node, TimelineSkillCard.class));
          case "transcript":
            return new SkillCard(JsonManager.deserialize(node, TranscriptSkillCard.class));
          case "status":
            return new SkillCard(JsonManager.deserialize(node, StatusSkillCard.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize SkillCard");
    }
  }
}
