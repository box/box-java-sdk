package com.box.sdkgen.managers.skills;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.skillcard.SkillCard;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateBoxSkillCardsOnFileRequestBody extends SerializableObject {

  /** A list of Box Skill cards to apply to this file. */
  protected final List<SkillCard> cards;

  public CreateBoxSkillCardsOnFileRequestBody(@JsonProperty("cards") List<SkillCard> cards) {
    super();
    this.cards = cards;
  }

  public List<SkillCard> getCards() {
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
