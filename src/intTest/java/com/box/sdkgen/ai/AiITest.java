package com.box.sdkgen.ai;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.delayInSeconds;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.internal.utils.UtilsManager.getValueFromObjectRawData;
import static com.box.sdkgen.internal.utils.UtilsManager.stringToByteStream;
import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.commons.CommonsManager.uploadNewFile;

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
import com.box.sdkgen.schemas.aiagentaskoraiagentextractoraiagentextractstructuredoraiagenttextgen.AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen;
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
    FileFull fileToAsk = uploadNewFile();
    AiResponseFull response =
        client
            .getAi()
            .createAiAsk(
                new AiAsk(
                    AiAskModeField.SINGLE_ITEM_QA,
                    "which direction sun rises",
                    Arrays.asList(
                        new AiItemAsk.AiItemAskBuilder(fileToAsk.getId(), AiItemAskTypeField.FILE)
                            .content("Sun rises in the East")
                            .build())));
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
                        new AiItemAsk.AiItemAskBuilder(fileToAsk1.getId(), AiItemAskTypeField.FILE)
                            .content("Earth goes around the sun")
                            .build(),
                        new AiItemAsk.AiItemAskBuilder(fileToAsk2.getId(), AiItemAskTypeField.FILE)
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
    AiResponse response =
        client
            .getAi()
            .createAiTextGen(
                new AiTextGen.AiTextGenBuilder(
                        "Parapharse the document.s",
                        Arrays.asList(
                            new AiTextGenItemsField.AiTextGenItemsFieldBuilder(fileToAsk.getId())
                                .type(AiTextGenItemsTypeField.FILE)
                                .content(
                                    "The Earth goes around the sun. Sun rises in the East in the morning.")
                                .build()))
                    .dialogueHistory(
                        Arrays.asList(
                            new AiDialogueHistory.AiDialogueHistoryBuilder()
                                .prompt("What does the earth go around?")
                                .answer("The sun")
                                .createdAt("2021-01-01T00:00:00Z")
                                .build(),
                            new AiDialogueHistory.AiDialogueHistoryBuilder()
                                .prompt("On Earth, where does the sun rise?")
                                .answer("East")
                                .createdAt("2021-01-01T00:00:00Z")
                                .build()))
                    .build());
    assert response.getAnswer().contains("sun");
    assert response.getCompletionReason().equals("done");
    client.getFiles().deleteFileById(fileToAsk.getId());
  }

  @Test
  public void testGettingAiAskAgentConfig() {
    AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen aiAskConfig =
        client
            .getAi()
            .getAiAgentDefaultConfig(
                new GetAiAgentDefaultConfigQueryParams.GetAiAgentDefaultConfigQueryParamsBuilder(
                        GetAiAgentDefaultConfigQueryParamsModeField.ASK)
                    .language("en-US")
                    .build());
  }

  @Test
  public void testGettingAiTextGenAgentConfig() {
    AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen aiTextGenConfig =
        client
            .getAi()
            .getAiAgentDefaultConfig(
                new GetAiAgentDefaultConfigQueryParams.GetAiAgentDefaultConfigQueryParamsBuilder(
                        GetAiAgentDefaultConfigQueryParamsModeField.TEXT_GEN)
                    .language("en-US")
                    .build());
  }

  @Test
  public void testAiExtract() {
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
                new AiExtract(
                    "firstName, lastName, location, yearOfBirth, company",
                    Arrays.asList(new AiItemBase(file.getId()))));
    String expectedResponse =
        "{\"firstName\": \"John\", \"lastName\": \"Doe\", \"location\": \"San Francisco\", \"yearOfBirth\": \"1990\", \"company\": \"Box\"}";
    assert response.getAnswer().equals(expectedResponse);
    assert response.getCompletionReason().equals("done");
    client.getFiles().deleteFileById(file.getId());
  }

  @Test
  public void testAiExtractStructuredWithFields() {
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
                new AiExtractStructured.AiExtractStructuredBuilder(
                        Arrays.asList(new AiItemBase(file.getId())))
                    .fields(
                        Arrays.asList(
                            new AiExtractStructuredFieldsField
                                    .AiExtractStructuredFieldsFieldBuilder("firstName")
                                .description("Person first name")
                                .displayName("First name")
                                .prompt("What is the your first name?")
                                .type("string")
                                .build(),
                            new AiExtractStructuredFieldsField
                                    .AiExtractStructuredFieldsFieldBuilder("lastName")
                                .description("Person last name")
                                .displayName("Last name")
                                .prompt("What is the your last name?")
                                .type("string")
                                .build(),
                            new AiExtractStructuredFieldsField
                                    .AiExtractStructuredFieldsFieldBuilder("dateOfBirth")
                                .description("Person date of birth")
                                .displayName("Birth date")
                                .prompt("What is the date of your birth?")
                                .type("date")
                                .build(),
                            new AiExtractStructuredFieldsField
                                    .AiExtractStructuredFieldsFieldBuilder("age")
                                .description("Person age")
                                .displayName("Age")
                                .prompt("How old are you?")
                                .type("float")
                                .build(),
                            new AiExtractStructuredFieldsField
                                    .AiExtractStructuredFieldsFieldBuilder("hobby")
                                .description("Person hobby")
                                .displayName("Hobby")
                                .prompt("What is your hobby?")
                                .type("multiSelect")
                                .options(
                                    Arrays.asList(
                                        new AiExtractStructuredFieldsOptionsField("guitar"),
                                        new AiExtractStructuredFieldsOptionsField("books")))
                                .build()))
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
                new CreateMetadataTemplateRequestBody.CreateMetadataTemplateRequestBodyBuilder(
                        "enterprise", templateKey)
                    .templateKey(templateKey)
                    .fields(
                        Arrays.asList(
                            new CreateMetadataTemplateRequestBodyFieldsField
                                    .CreateMetadataTemplateRequestBodyFieldsFieldBuilder(
                                    CreateMetadataTemplateRequestBodyFieldsTypeField.STRING,
                                    "firstName",
                                    "First name")
                                .description("Person first name")
                                .build(),
                            new CreateMetadataTemplateRequestBodyFieldsField
                                    .CreateMetadataTemplateRequestBodyFieldsFieldBuilder(
                                    CreateMetadataTemplateRequestBodyFieldsTypeField.STRING,
                                    "lastName",
                                    "Last name")
                                .description("Person last name")
                                .build(),
                            new CreateMetadataTemplateRequestBodyFieldsField
                                    .CreateMetadataTemplateRequestBodyFieldsFieldBuilder(
                                    CreateMetadataTemplateRequestBodyFieldsTypeField.DATE,
                                    "dateOfBirth",
                                    "Birth date")
                                .description("Person date of birth")
                                .build(),
                            new CreateMetadataTemplateRequestBodyFieldsField
                                    .CreateMetadataTemplateRequestBodyFieldsFieldBuilder(
                                    CreateMetadataTemplateRequestBodyFieldsTypeField.FLOAT,
                                    "age",
                                    "Age")
                                .description("Person age")
                                .build(),
                            new CreateMetadataTemplateRequestBodyFieldsField
                                    .CreateMetadataTemplateRequestBodyFieldsFieldBuilder(
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
                new AiExtractStructured.AiExtractStructuredBuilder(
                        Arrays.asList(new AiItemBase(file.getId())))
                    .metadataTemplate(
                        new AiExtractStructuredMetadataTemplateField
                                .AiExtractStructuredMetadataTemplateFieldBuilder()
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
