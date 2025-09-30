package com.box.sdkgen.skills;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.commons.CommonsManager.uploadNewFile;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.skills.CreateBoxSkillCardsOnFileRequestBody;
import com.box.sdkgen.managers.skills.UpdateBoxSkillCardsOnFileRequestBody;
import com.box.sdkgen.managers.skills.UpdateBoxSkillCardsOnFileRequestBodyOpField;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.keywordskillcard.KeywordSkillCard;
import com.box.sdkgen.schemas.keywordskillcard.KeywordSkillCardEntriesField;
import com.box.sdkgen.schemas.keywordskillcard.KeywordSkillCardInvocationField;
import com.box.sdkgen.schemas.keywordskillcard.KeywordSkillCardInvocationTypeField;
import com.box.sdkgen.schemas.keywordskillcard.KeywordSkillCardSkillCardTitleField;
import com.box.sdkgen.schemas.keywordskillcard.KeywordSkillCardSkillCardTypeField;
import com.box.sdkgen.schemas.keywordskillcard.KeywordSkillCardSkillField;
import com.box.sdkgen.schemas.keywordskillcard.KeywordSkillCardSkillTypeField;
import com.box.sdkgen.schemas.keywordskillcard.KeywordSkillCardTypeField;
import com.box.sdkgen.schemas.skillcard.SkillCard;
import com.box.sdkgen.schemas.skillcardsmetadata.SkillCardsMetadata;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SkillsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testSkillsCardsCrud() {
    FileFull file = uploadNewFile();
    String skillId = getUuid();
    String invocationId = getUuid();
    String titleMessage = "License Plates";
    KeywordSkillCard cardToCreate =
        new KeywordSkillCard.Builder(
                new KeywordSkillCardSkillField.Builder(skillId)
                    .type(KeywordSkillCardSkillTypeField.SERVICE)
                    .build(),
                new KeywordSkillCardInvocationField.Builder(invocationId)
                    .type(KeywordSkillCardInvocationTypeField.SKILL_INVOCATION)
                    .build(),
                Arrays.asList(new KeywordSkillCardEntriesField.Builder().text("DN86 BOX").build()))
            .type(KeywordSkillCardTypeField.SKILL_CARD)
            .skillCardType(KeywordSkillCardSkillCardTypeField.KEYWORD)
            .skillCardTitle(
                new KeywordSkillCardSkillCardTitleField.Builder(titleMessage)
                    .code("license-plates")
                    .build())
            .build();
    List<SkillCard> cardsToCreate = Arrays.asList(new SkillCard(cardToCreate));
    SkillCardsMetadata skillCardsMetadata =
        client
            .getSkills()
            .createBoxSkillCardsOnFile(
                file.getId(), new CreateBoxSkillCardsOnFileRequestBody(cardsToCreate));
    assert skillCardsMetadata.getCards().size() == 1;
    KeywordSkillCard keywordSkillCard = skillCardsMetadata.getCards().get(0).getKeywordSkillCard();
    assert keywordSkillCard.getSkill().getId().equals(skillId);
    assert keywordSkillCard.getSkillCardTitle().getMessage().equals(titleMessage);
    String updatedTitleMessage = "Updated License Plates";
    KeywordSkillCard cardToUpdate =
        new KeywordSkillCard.Builder(
                new KeywordSkillCardSkillField.Builder(skillId)
                    .type(KeywordSkillCardSkillTypeField.SERVICE)
                    .build(),
                new KeywordSkillCardInvocationField.Builder(invocationId)
                    .type(KeywordSkillCardInvocationTypeField.SKILL_INVOCATION)
                    .build(),
                Arrays.asList(new KeywordSkillCardEntriesField.Builder().text("DN86 BOX").build()))
            .type(KeywordSkillCardTypeField.SKILL_CARD)
            .skillCardType(KeywordSkillCardSkillCardTypeField.KEYWORD)
            .skillCardTitle(
                new KeywordSkillCardSkillCardTitleField.Builder(updatedTitleMessage)
                    .code("license-plates")
                    .build())
            .build();
    SkillCardsMetadata updatedSkillCardsMetadata =
        client
            .getSkills()
            .updateBoxSkillCardsOnFile(
                file.getId(),
                Arrays.asList(
                    new UpdateBoxSkillCardsOnFileRequestBody.Builder()
                        .op(UpdateBoxSkillCardsOnFileRequestBodyOpField.REPLACE)
                        .path("/cards/0")
                        .value(cardToUpdate)
                        .build()));
    KeywordSkillCard updatedKeywordSkillCard =
        updatedSkillCardsMetadata.getCards().get(0).getKeywordSkillCard();
    assert updatedKeywordSkillCard.getSkill().getId().equals(skillId);
    assert updatedKeywordSkillCard.getSkillCardTitle().getMessage().equals(updatedTitleMessage);
    SkillCardsMetadata receivedSkillCardsMetadata =
        client.getSkills().getBoxSkillCardsOnFile(file.getId());
    KeywordSkillCard receivedKeywordSkillCard =
        receivedSkillCardsMetadata.getCards().get(0).getKeywordSkillCard();
    assert receivedKeywordSkillCard.getSkill().getId().equals(skillId);
    client.getSkills().deleteBoxSkillCardsFromFile(file.getId());
    client.getFiles().deleteFileById(file.getId());
  }
}
