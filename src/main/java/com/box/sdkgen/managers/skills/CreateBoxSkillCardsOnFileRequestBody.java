package com.box.sdkgen.managers.skills;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.keywordskillcardorstatusskillcardortimelineskillcardortranscriptskillcard.KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateBoxSkillCardsOnFileRequestBody extends SerializableObject {

  protected final List<KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard>
      cards;

  public CreateBoxSkillCardsOnFileRequestBody(
      @JsonProperty("cards")
          List<KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard> cards) {
    super();
    this.cards = cards;
  }

  public List<KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard>
      getCards() {
    return cards;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateBoxSkillCardsOnFileRequestBody casted = (CreateBoxSkillCardsOnFileRequestBody) o;
    return Objects.equals(cards, casted.cards);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cards);
  }

  @Override
  public String toString() {
    return "CreateBoxSkillCardsOnFileRequestBody{" + "cards='" + cards + '\'' + "}";
  }
}
