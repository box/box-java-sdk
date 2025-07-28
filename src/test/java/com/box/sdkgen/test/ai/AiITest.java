package com.box.sdkgen.test.ai;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.dateTimeFromString;
import static com.box.sdkgen.internal.utils.UtilsManager.delayInSeconds;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.internal.utils.UtilsManager.getValueFromObjectRawData;
import static com.box.sdkgen.internal.utils.UtilsManager.stringToByteStream;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.test.commons.CommonsManager.uploadNewFile;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.ai.GetAiAgentDefaultConfigQueryParams;
import com.box.sdkgen.managers.ai.GetAiAgentDefaultConfigQueryParamsModeField;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBody;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBodyFieldsField;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBodyFieldsOptionsField;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBodyFieldsTypeField;
import com.box.sdkgen.managers.metadatatemplates.DeleteMetadataTemplateScope;
import com.box.sdkgen.managers.uploads.UploadFileRequestBody;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesParentField;
import com.box.sdkgen.schemas.aiagentask.AiAgentAsk;
import com.box.sdkgen.schemas.aiagentaskoraiagentextractoraiagentextractstructuredoraiagenttextgen.AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen;
import com.box.sdkgen.schemas.aiagentextract.AiAgentExtract;
import com.box.sdkgen.schemas.aiagentextractstructured.AiAgentExtractStructured;
import com.box.sdkgen.schemas.aiagentlongtexttool.AiAgentLongTextTool;
import com.box.sdkgen.schemas.aiagenttextgen.AiAgentTextGen;
import com.box.sdkgen.schemas.aiask.AiAsk;
import com.box.sdkgen.schemas.aiask.AiAskModeField;
import com.box.sdkgen.schemas.aidialoguehistory.AiDialogueHistory;
import com.box.sdkgen.schemas.aiextract.AiExtract;
import com.box.sdkgen.schemas.aiextractstructured.AiExtractStructured;
import com.box.sdkgen.schemas.aiextractstructured.AiExtractStructuredFieldsField;
import com.box.sdkgen.schemas.aiextractstructured.AiExtractStructuredFieldsOptionsField;
import com.box.sdkgen.schemas.aiextractstructured.AiExtractStructuredMetadataTemplateField;
import com.box.sdkgen.schemas.aiextractstructuredresponse.AiExtractStructuredResponse;
import com.box.sdkgen.schemas.aiitemask.AiItemAsk;
import com.box.sdkgen.schemas.aiitemask.AiItemAskTypeField;
import com.box.sdkgen.schemas.aiitembase.AiItemBase;
import com.box.sdkgen.schemas.airesponse.AiResponse;
import com.box.sdkgen.schemas.airesponsefull.AiResponseFull;
import com.box.sdkgen.schemas.aitextgen.AiTextGen;
import com.box.sdkgen.schemas.aitextgen.AiTextGenItemsField;
import com.box.sdkgen.schemas.aitextgen.AiTextGenItemsTypeField;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.files.Files;
import com.box.sdkgen.schemas.metadatatemplate.MetadataTemplate;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class AiITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testAskAiSingleItem() {
    AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen aiAgentConfig =
        client
            .getAi()
            .getAiAgentDefaultConfig(
                new GetAiAgentDefaultConfigQueryParams.Builder(
                        GetAiAgentDefaultConfigQueryParamsModeField.ASK)
                    .language("en-US")
                    .build());
    AiAgentAsk aiAskAgentConfig = aiAgentConfig.getAiAgentAsk();
    FileFull fileToAsk = uploadNewFile();
    AiResponseFull response =
        client
            .getAi()
            .createAiAsk(
                new AiAsk.Builder(
                        AiAskModeField.SINGLE_ITEM_QA,
                        "which direction sun rises",
                        Arrays.asList(
                            new AiItemAsk.Builder(fileToAsk.getId(), AiItemAskTypeField.FILE)
                                .content("Sun rises in the East")
                                .build()))
                    .aiAgent(aiAskAgentConfig)
                    .build());
    assert response.getAnswer().contains("East");
    assert response.getCompletionReason().equals("done");
    client.getFiles().deleteFileById(fileToAsk.getId());
  }

  @Test
  public void testAskAiMultipleItems() {
    FileFull fileToAsk1 = uploadNewFile();
    FileFull fileToAsk2 = uploadNewFile();
    AiResponseFull response =
        client
            .getAi()
            .createAiAsk(
                new AiAsk(
                    AiAskModeField.MULTIPLE_ITEM_QA,
                    "Which direction sun rises?",
                    Arrays.asList(
                        new AiItemAsk.Builder(fileToAsk1.getId(), AiItemAskTypeField.FILE)
                            .content("Earth goes around the sun")
                            .build(),
                        new AiItemAsk.Builder(fileToAsk2.getId(), AiItemAskTypeField.FILE)
                            .content("Sun rises in the East in the morning")
                            .build())));
    assert response.getAnswer().contains("East");
    assert response.getCompletionReason().equals("done");
    client.getFiles().deleteFileById(fileToAsk1.getId());
    client.getFiles().deleteFileById(fileToAsk2.getId());
  }

  @Test
  public void testAiTextGenWithDialogueHistory() {
    FileFull fileToAsk = uploadNewFile();
    AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen aiAgentConfig =
        client
            .getAi()
            .getAiAgentDefaultConfig(
                new GetAiAgentDefaultConfigQueryParams.Builder(
                        GetAiAgentDefaultConfigQueryParamsModeField.TEXT_GEN)
                    .language("en-US")
                    .build());
    AiAgentTextGen aiTextGenAgentConfig = aiAgentConfig.getAiAgentTextGen();
    AiResponse response =
        client
            .getAi()
            .createAiTextGen(
                new AiTextGen.Builder(
                        "Parapharse the document.s",
                        Arrays.asList(
                            new AiTextGenItemsField.Builder(fileToAsk.getId())
                                .type(AiTextGenItemsTypeField.FILE)
                                .content(
                                    "The Earth goes around the sun. Sun rises in the East in the morning.")
                                .build()))
                    .dialogueHistory(
                        Arrays.asList(
                            new AiDialogueHistory.Builder()
                                .prompt("What does the earth go around?")
                                .answer("The sun")
                                .createdAt(dateTimeFromString("2021-01-01T00:00:00Z"))
                                .build(),
                            new AiDialogueHistory.Builder()
                                .prompt("On Earth, where does the sun rise?")
                                .answer("East")
                                .createdAt(dateTimeFromString("2021-01-01T00:00:00Z"))
                                .build()))
                    .aiAgent(aiTextGenAgentConfig)
                    .build());
    assert response.getAnswer().contains("sun");
    assert response.getCompletionReason().equals("done");
    client.getFiles().deleteFileById(fileToAsk.getId());
  }

  @Test
  public void testGettingAiAskAgentConfig() {
    AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen aiAgentConfig =
        client
            .getAi()
            .getAiAgentDefaultConfig(
                new GetAiAgentDefaultConfigQueryParams.Builder(
                        GetAiAgentDefaultConfigQueryParamsModeField.ASK)
                    .language("en-US")
                    .build());
    assert aiAgentConfig.getType().equals("ai_agent_ask");
    AiAgentAsk aiAgentAskConfig = aiAgentConfig.getAiAgentAsk();
    assert !(aiAgentAskConfig.getBasicText().getModel().equals(""));
    assert !(aiAgentAskConfig.getBasicText().getPromptTemplate().equals(""));
    assert aiAgentAskConfig.getBasicText().getNumTokensForCompletion() > -1;
    assert !(aiAgentAskConfig.getBasicText().getLlmEndpointParams() == null);
    assert !(aiAgentAskConfig.getBasicTextMulti().getModel().equals(""));
    assert !(aiAgentAskConfig.getBasicTextMulti().getPromptTemplate().equals(""));
    assert aiAgentAskConfig.getBasicTextMulti().getNumTokensForCompletion() > -1;
    assert !(aiAgentAskConfig.getBasicTextMulti().getLlmEndpointParams() == null);
    assert !(aiAgentAskConfig.getLongText().getModel().equals(""));
    assert !(aiAgentAskConfig.getLongText().getPromptTemplate().equals(""));
    assert aiAgentAskConfig.getLongText().getNumTokensForCompletion() > -1;
    assert !(aiAgentAskConfig.getLongText().getEmbeddings().getModel().equals(""));
    assert !(aiAgentAskConfig.getLongText().getEmbeddings().getStrategy().getId().equals(""));
    assert !(aiAgentAskConfig.getLongText().getLlmEndpointParams() == null);
    assert !(aiAgentAskConfig.getLongTextMulti().getModel().equals(""));
    assert !(aiAgentAskConfig.getLongTextMulti().getPromptTemplate().equals(""));
    assert aiAgentAskConfig.getLongTextMulti().getNumTokensForCompletion() > -1;
    assert !(aiAgentAskConfig.getLongTextMulti().getEmbeddings().getModel().equals(""));
    assert !(aiAgentAskConfig.getLongTextMulti().getEmbeddings().getStrategy().getId().equals(""));
    assert !(aiAgentAskConfig.getLongTextMulti().getLlmEndpointParams() == null);
  }

  @Test
  public void testGettingAiTextGenAgentConfig() {
    AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen aiAgentConfig =
        client
            .getAi()
            .getAiAgentDefaultConfig(
                new GetAiAgentDefaultConfigQueryParams.Builder(
                        GetAiAgentDefaultConfigQueryParamsModeField.TEXT_GEN)
                    .language("en-US")
                    .build());
    assert aiAgentConfig.getType().equals("ai_agent_text_gen");
    AiAgentTextGen aiAgentTextGenConfig = aiAgentConfig.getAiAgentTextGen();
    assert !(aiAgentTextGenConfig.getBasicGen().getLlmEndpointParams() == null);
    assert !(aiAgentTextGenConfig.getBasicGen().getModel().equals(""));
    assert !(aiAgentTextGenConfig.getBasicGen().getPromptTemplate().equals(""));
    assert aiAgentTextGenConfig.getBasicGen().getNumTokensForCompletion() > -1;
    assert !(aiAgentTextGenConfig.getBasicGen().getContentTemplate().equals(""));
    assert !(aiAgentTextGenConfig.getBasicGen().getEmbeddings().getModel().equals(""));
    assert !(aiAgentTextGenConfig.getBasicGen().getEmbeddings().getStrategy().getId().equals(""));
  }

  @Test
  public void testAiExtract() {
    AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen aiAgentConfig =
        client
            .getAi()
            .getAiAgentDefaultConfig(
                new GetAiAgentDefaultConfigQueryParams.Builder(
                        GetAiAgentDefaultConfigQueryParamsModeField.EXTRACT)
                    .language("en-US")
                    .build());
    AiAgentExtract aiExtractAgentConfig = aiAgentConfig.getAiAgentExtract();
    AiAgentLongTextTool longTextConfigWithNoEmbeddings =
        new AiAgentLongTextTool.Builder()
            .systemMessage(aiExtractAgentConfig.getLongText().getSystemMessage())
            .promptTemplate(aiExtractAgentConfig.getLongText().getPromptTemplate())
            .model(aiExtractAgentConfig.getLongText().getModel())
            .numTokensForCompletion(aiExtractAgentConfig.getLongText().getNumTokensForCompletion())
            .llmEndpointParams(aiExtractAgentConfig.getLongText().getLlmEndpointParams())
            .build();
    AiAgentExtract agentIgnoringOverridingEmbeddingsModel =
        new AiAgentExtract.Builder()
            .longText(longTextConfigWithNoEmbeddings)
            .basicText(aiExtractAgentConfig.getBasicText())
            .build();
    Files uploadedFiles =
        client
            .getUploads()
            .uploadFile(
                new UploadFileRequestBody(
                    new UploadFileRequestBodyAttributesField(
                        String.join("", getUuid(), ".txt"),
                        new UploadFileRequestBodyAttributesParentField("0")),
                    stringToByteStream(
                        "My name is John Doe. I live in San Francisco. I was born in 1990. I work at Box.")));
    FileFull file = uploadedFiles.getEntries().get(0);
    delayInSeconds(5);
    AiResponse response =
        client
            .getAi()
            .createAiExtract(
                new AiExtract.Builder(
                        "firstName, lastName, location, yearOfBirth, company",
                        Arrays.asList(new AiItemBase(file.getId())))
                    .aiAgent(agentIgnoringOverridingEmbeddingsModel)
                    .build());
    String expectedResponse =
        "{\"firstName\": \"John\", \"lastName\": \"Doe\", \"location\": \"San Francisco\", \"yearOfBirth\": \"1990\", \"company\": \"Box\"}";
    assert response.getAnswer().equals(expectedResponse);
    assert response.getCompletionReason().equals("done");
    client.getFiles().deleteFileById(file.getId());
  }

  @Test
  public void testAiExtractStructuredWithFields() {
    AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen aiAgentConfig =
        client
            .getAi()
            .getAiAgentDefaultConfig(
                new GetAiAgentDefaultConfigQueryParams.Builder(
                        GetAiAgentDefaultConfigQueryParamsModeField.EXTRACT_STRUCTURED)
                    .language("en-US")
                    .build());
    AiAgentExtractStructured aiExtractStructuredAgentConfig =
        aiAgentConfig.getAiAgentExtractStructured();
    AiAgentLongTextTool longTextConfigWithNoEmbeddings =
        new AiAgentLongTextTool.Builder()
            .systemMessage(aiExtractStructuredAgentConfig.getLongText().getSystemMessage())
            .promptTemplate(aiExtractStructuredAgentConfig.getLongText().getPromptTemplate())
            .model(aiExtractStructuredAgentConfig.getLongText().getModel())
            .numTokensForCompletion(
                aiExtractStructuredAgentConfig.getLongText().getNumTokensForCompletion())
            .llmEndpointParams(aiExtractStructuredAgentConfig.getLongText().getLlmEndpointParams())
            .build();
    AiAgentExtractStructured agentIgnoringOverridingEmbeddingsModel =
        new AiAgentExtractStructured.Builder()
            .longText(longTextConfigWithNoEmbeddings)
            .basicText(aiExtractStructuredAgentConfig.getBasicText())
            .build();
    Files uploadedFiles =
        client
            .getUploads()
            .uploadFile(
                new UploadFileRequestBody(
                    new UploadFileRequestBodyAttributesField(
                        String.join("", getUuid(), ".txt"),
                        new UploadFileRequestBodyAttributesParentField("0")),
                    stringToByteStream(
                        "My name is John Doe. I was born in 4th July 1990. I am 34 years old. My hobby is guitar.")));
    FileFull file = uploadedFiles.getEntries().get(0);
    delayInSeconds(5);
    AiExtractStructuredResponse response =
        client
            .getAi()
            .createAiExtractStructured(
                new AiExtractStructured.Builder(Arrays.asList(new AiItemBase(file.getId())))
                    .fields(
                        Arrays.asList(
                            new AiExtractStructuredFieldsField.Builder("firstName")
                                .description("Person first name")
                                .displayName("First name")
                                .prompt("What is the your first name?")
                                .type("string")
                                .build(),
                            new AiExtractStructuredFieldsField.Builder("lastName")
                                .description("Person last name")
                                .displayName("Last name")
                                .prompt("What is the your last name?")
                                .type("string")
                                .build(),
                            new AiExtractStructuredFieldsField.Builder("dateOfBirth")
                                .description("Person date of birth")
                                .displayName("Birth date")
                                .prompt("What is the date of your birth?")
                                .type("date")
                                .build(),
                            new AiExtractStructuredFieldsField.Builder("age")
                                .description("Person age")
                                .displayName("Age")
                                .prompt("How old are you?")
                                .type("float")
                                .build(),
                            new AiExtractStructuredFieldsField.Builder("hobby")
                                .description("Person hobby")
                                .displayName("Hobby")
                                .prompt("What is your hobby?")
                                .type("multiSelect")
                                .options(
                                    Arrays.asList(
                                        new AiExtractStructuredFieldsOptionsField("guitar"),
                                        new AiExtractStructuredFieldsOptionsField("books")))
                                .build()))
                    .aiAgent(agentIgnoringOverridingEmbeddingsModel)
                    .build());
    assert convertToString(getValueFromObjectRawData(response, "answer.hobby"))
        .equals(convertToString(Arrays.asList("guitar")));
    assert convertToString(getValueFromObjectRawData(response, "answer.firstName")).equals("John");
    assert convertToString(getValueFromObjectRawData(response, "answer.lastName")).equals("Doe");
    assert convertToString(getValueFromObjectRawData(response, "answer.dateOfBirth"))
        .equals("1990-07-04");
    assert convertToString(getValueFromObjectRawData(response, "answer.age")).equals("34");
    assert response.getCompletionReason().equals("done");
    client.getFiles().deleteFileById(file.getId());
  }

  @Test
  public void testAiExtractStructuredWithMetadataTemplate() {
    Files uploadedFiles =
        client
            .getUploads()
            .uploadFile(
                new UploadFileRequestBody(
                    new UploadFileRequestBodyAttributesField(
                        String.join("", getUuid(), ".txt"),
                        new UploadFileRequestBodyAttributesParentField("0")),
                    stringToByteStream(
                        "My name is John Doe. I was born in 4th July 1990. I am 34 years old. My hobby is guitar.")));
    FileFull file = uploadedFiles.getEntries().get(0);
    delayInSeconds(5);
    String templateKey = String.join("", "key", getUuid());
    MetadataTemplate template =
        client
            .getMetadataTemplates()
            .createMetadataTemplate(
                new CreateMetadataTemplateRequestBody.Builder("enterprise", templateKey)
                    .templateKey(templateKey)
                    .fields(
                        Arrays.asList(
                            new CreateMetadataTemplateRequestBodyFieldsField.Builder(
                                    CreateMetadataTemplateRequestBodyFieldsTypeField.STRING,
                                    "firstName",
                                    "First name")
                                .description("Person first name")
                                .build(),
                            new CreateMetadataTemplateRequestBodyFieldsField.Builder(
                                    CreateMetadataTemplateRequestBodyFieldsTypeField.STRING,
                                    "lastName",
                                    "Last name")
                                .description("Person last name")
                                .build(),
                            new CreateMetadataTemplateRequestBodyFieldsField.Builder(
                                    CreateMetadataTemplateRequestBodyFieldsTypeField.DATE,
                                    "dateOfBirth",
                                    "Birth date")
                                .description("Person date of birth")
                                .build(),
                            new CreateMetadataTemplateRequestBodyFieldsField.Builder(
                                    CreateMetadataTemplateRequestBodyFieldsTypeField.FLOAT,
                                    "age",
                                    "Age")
                                .description("Person age")
                                .build(),
                            new CreateMetadataTemplateRequestBodyFieldsField.Builder(
                                    CreateMetadataTemplateRequestBodyFieldsTypeField.MULTISELECT,
                                    "hobby",
                                    "Hobby")
                                .description("Person hobby")
                                .options(
                                    Arrays.asList(
                                        new CreateMetadataTemplateRequestBodyFieldsOptionsField(
                                            "guitar"),
                                        new CreateMetadataTemplateRequestBodyFieldsOptionsField(
                                            "books")))
                                .build()))
                    .build());
    AiExtractStructuredResponse response =
        client
            .getAi()
            .createAiExtractStructured(
                new AiExtractStructured.Builder(Arrays.asList(new AiItemBase(file.getId())))
                    .metadataTemplate(
                        new AiExtractStructuredMetadataTemplateField.Builder()
                            .templateKey(templateKey)
                            .scope("enterprise")
                            .build())
                    .build());
    assert convertToString(getValueFromObjectRawData(response, "answer.firstName")).equals("John");
    assert convertToString(getValueFromObjectRawData(response, "answer.lastName")).equals("Doe");
    assert convertToString(getValueFromObjectRawData(response, "answer.dateOfBirth"))
        .equals("1990-07-04T00:00:00Z");
    assert convertToString(getValueFromObjectRawData(response, "answer.age")).equals("34");
    assert convertToString(getValueFromObjectRawData(response, "answer.hobby"))
        .equals(convertToString(Arrays.asList("guitar")));
    assert response.getCompletionReason().equals("done");
    client
        .getMetadataTemplates()
        .deleteMetadataTemplate(DeleteMetadataTemplateScope.ENTERPRISE, template.getTemplateKey());
    client.getFiles().deleteFileById(file.getId());
  }
}
