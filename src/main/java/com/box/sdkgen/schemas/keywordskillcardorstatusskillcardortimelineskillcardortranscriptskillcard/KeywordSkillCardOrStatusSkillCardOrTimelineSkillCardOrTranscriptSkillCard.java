package com.box.sdkgen.schemas.keywordskillcardorstatusskillcardortimelineskillcardortranscriptskillcard;

import com.box.sdkgen.internal.OneOfFour;
import com.box.sdkgen.schemas.keywordskillcard.KeywordSkillCard;
import com.box.sdkgen.schemas.statusskillcard.StatusSkillCard;
import com.box.sdkgen.schemas.timelineskillcard.TimelineSkillCard;
import com.box.sdkgen.schemas.transcriptskillcard.TranscriptSkillCard;
import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;

@JsonDeserialize(
    using =
        KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard
            .KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCardDeserializer
            .class)
@JsonSerialize(using = OneOfFour.OneOfFourSerializer.class)
public class KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard
    extends OneOfFour<KeywordSkillCard, StatusSkillCard, TimelineSkillCard, TranscriptSkillCard> {

  public KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard(
      KeywordSkillCard keywordSkillCard) {
    super(keywordSkillCard, null, null, null);
  }

  public KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard(
      StatusSkillCard statusSkillCard) {
    super(null, statusSkillCard, null, null);
  }

  public KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard(
      TimelineSkillCard timelineSkillCard) {
    super(null, null, timelineSkillCard, null);
  }

  public KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard(
      TranscriptSkillCard transcriptSkillCard) {
    super(null, null, null, transcriptSkillCard);
  }

  public KeywordSkillCard getKeywordSkillCard() {
    return value0;
  }

  public StatusSkillCard getStatusSkillCard() {
    return value1;
  }

  public TimelineSkillCard getTimelineSkillCard() {
    return value2;
  }

  public TranscriptSkillCard getTranscriptSkillCard() {
    return value3;
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
