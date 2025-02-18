package com.box.sdk;

import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;
import static com.box.sdk.CleanupTools.deleteFile;
import static com.box.sdk.Retry.retry;
import static com.box.sdk.UniqueTestFolder.removeUniqueFolder;
import static com.box.sdk.UniqueTestFolder.setupUniqeFolder;
import static com.box.sdk.UniqueTestFolder.uploadFileToUniqueFolder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.eclipsesource.json.Json;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * {@link BoxGroup} related integration tests.
 */
public class BoxAIIT {

    @BeforeClass
    public static void setup() {
        setupUniqeFolder();
    }

    @AfterClass
    public static void afterClass() {
        removeUniqueFolder();
    }


    @Test
    public void askAISingleItem() throws InterruptedException {
        BoxAPIConnection api = jwtApiForServiceAccount();
        String fileName = "[askAISingleItem] Test File.txt";
        BoxFile uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");

        try {
            BoxFile.Info uploadedFileInfo = uploadedFile.getInfo();
            // When a file has been just uploaded, AI service may not be ready to return text response
            // and 412 is returned
            retry(() -> {
                BoxAIResponse response = BoxAI.sendAIRequest(
                    api,
                    "What is the name of the file?",
                    Collections.singletonList(new BoxAIItem(uploadedFileInfo.getID(), BoxAIItem.Type.FILE)),
                    BoxAI.Mode.SINGLE_ITEM_QA
                );
                assertThat(response.getAnswer(), containsString("Test file"));
                assert response.getCreatedAt().before(new Date(System.currentTimeMillis()));
                assertThat(response.getCompletionReason(), equalTo("done"));
            }, 2, 2000);

        } finally {
            deleteFile(uploadedFile);
        }
    }

    @Test
    public void askAIMultipleItems() throws InterruptedException {
        BoxAPIConnection api = jwtApiForServiceAccount();
        String fileName1 = "[askAIMultipleItems] Test File.txt";
        BoxFile uploadedFile1 = uploadFileToUniqueFolder(api, fileName1, "Test file");

        try {
            String fileName2 = "[askAIMultipleItems] Weather forecast.txt";
            BoxFile uploadedFile2 = uploadFileToUniqueFolder(api, fileName2, "Test file");

            try {
                BoxFile.Info uploadedFileInfo1 = uploadedFile1.getInfo();
                BoxFile.Info uploadedFileInfo2 = uploadedFile2.getInfo();

                List<BoxAIItem> items = new ArrayList<>();
                items.add(new BoxAIItem(uploadedFileInfo1.getID(), BoxAIItem.Type.FILE));
                items.add(new BoxAIItem(uploadedFileInfo2.getID(), BoxAIItem.Type.FILE));

                // When a file has been just uploaded, AI service may not be ready to return text response
                // and 412 is returned
                retry(() -> {
                    BoxAIResponse response = BoxAI.sendAIRequest(
                        api,
                        "What is the content of these files?",
                        items,
                        BoxAI.Mode.MULTIPLE_ITEM_QA
                    );
                    assertThat(response.getAnswer(), containsString("Test file"));
                    assert response.getCreatedAt().before(new Date(System.currentTimeMillis()));
                    assertThat(response.getCompletionReason(), equalTo("done"));
                }, 2, 2000);
            } finally {
                deleteFile(uploadedFile2);
            }

        } finally {
            deleteFile(uploadedFile1);
        }
    }

    @Test
    public void askAITextGenItemWithDialogueHistory() throws ParseException, InterruptedException {
        BoxAPIConnection api = jwtApiForServiceAccount();
        String fileName = "[askAITextGenItemWithDialogueHistory] Test File.txt";
        Date date1 = BoxDateFormat.parse("2021-01-01T00:00:00Z");
        Date date2 = BoxDateFormat.parse("2021-02-01T00:00:00Z");

        BoxFile uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
        try {
            // When a file has been just uploaded, AI service may not be ready to return text response
            // and 412 is returned
            retry(() -> {
                BoxFile.Info uploadedFileInfo = uploadedFile.getInfo();
                assertThat(uploadedFileInfo.getName(), is(equalTo(fileName)));

                List<BoxAIDialogueEntry> dialogueHistory = new ArrayList<>();
                dialogueHistory.add(
                    new BoxAIDialogueEntry("What is the name of the file?", "Test file", date1)
                );
                dialogueHistory.add(
                    new BoxAIDialogueEntry("What is the size of the file?", "10kb", date2)
                );
                BoxAIResponse response = BoxAI.sendAITextGenRequest(
                    api,
                    "What is the name of the file?",
                    Collections.singletonList(new BoxAIItem(uploadedFileInfo.getID(), BoxAIItem.Type.FILE)),
                    dialogueHistory
                );
                assertThat(response.getAnswer(), containsString("name"));
                assert response.getCreatedAt().before(new Date(System.currentTimeMillis()));
                assertThat(response.getCompletionReason(), equalTo("done"));
            }, 2, 2000);

        } finally {
            deleteFile(uploadedFile);
        }
    }

    @Test
    public void getAIAgentDefaultConfiguration() {
        BoxAPIConnection api = jwtApiForServiceAccount();
        BoxAIAgent agent = BoxAI.getAiAgentDefaultConfig(api, BoxAIAgent.Mode.ASK);
        BoxAIAgentAsk askAgent = (BoxAIAgentAsk) agent;

        assertThat(askAgent.getType(), is(equalTo(BoxAIAgentAsk.TYPE)));
        assertThat(askAgent.getBasicText().getModel(), is(notNullValue()));

        BoxAIAgent agent2 = BoxAI.getAiAgentDefaultConfig(api, BoxAIAgent.Mode.TEXT_GEN);
        BoxAIAgentTextGen textGenAgent = (BoxAIAgentTextGen) agent2;

        assertThat(textGenAgent.getType(), is(equalTo(BoxAIAgentTextGen.TYPE)));
        assertThat(textGenAgent.getBasicGen().getModel(), is(notNullValue()));
    }

    @Test
    public void askAISingleItemWithAgent() throws InterruptedException {
        BoxAPIConnection api = jwtApiForServiceAccount();
        String fileName = "[askAISingleItem] Test File.txt";
        BoxFile uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
        BoxAIAgent agent = BoxAI.getAiAgentDefaultConfig(api, BoxAIAgent.Mode.ASK);
        BoxAIAgentAsk askAgent = (BoxAIAgentAsk) agent;

        try {
            BoxFile.Info uploadedFileInfo = uploadedFile.getInfo();
            // When a file has been just uploaded, AI service may not be ready to return text response
            // and 412 is returned
            retry(() -> {
                BoxAIResponse response = BoxAI.sendAIRequest(
                    api,
                    "What is the name of the file?",
                    Collections.singletonList(new BoxAIItem(uploadedFileInfo.getID(), BoxAIItem.Type.FILE)),
                    BoxAI.Mode.SINGLE_ITEM_QA,
                    null,
                    askAgent,
                    true
                );
                assertThat(response.getAnswer(), containsString("Test file"));
                assert response.getCreatedAt().before(new Date(System.currentTimeMillis()));
                assertThat(response.getCompletionReason(), equalTo("done"));
            }, 2, 2000);

        } finally {
            deleteFile(uploadedFile);
        }
    }

    @Test
    public void aiExtract() throws InterruptedException {
        BoxAPIConnection api = jwtApiForServiceAccount();
        BoxAIAgent agent = BoxAI.getAiAgentDefaultConfig(api, BoxAIAgent.Mode.EXTRACT);
        BoxAIAgentExtract agentExtract = (BoxAIAgentExtract) agent;
        // AI team is going to move away from supporting overriding embeddings model
        agentExtract.getLongText().setEmbeddings(null);

        BoxFile uploadedFile = uploadFileToUniqueFolder(api, "[aiExtract] Test File.txt",
            "My name is John Doe. I live in San Francisco. I was born in 1990. I work at Box.");

        try {
            // When a file has been just uploaded, AI service may not be ready to return text response
            // and 412 is returned
            retry(() -> {
                BoxAIResponse response = BoxAI.extractMetadataFreeform(api,
                    "firstName, lastName, location, yearOfBirth, company",
                    Collections.singletonList(new BoxAIItem(uploadedFile.getID(), BoxAIItem.Type.FILE)),
                    agentExtract);
                assertThat(response.getAnswer(), containsString("John"));
                assertThat(response.getCompletionReason(), equalTo("done"));
            }, 2, 2000);
        } finally {
            deleteFile(uploadedFile);
        }
    }

    @Test
    public void aiExtractStructuredWithFields() throws InterruptedException {
        BoxAPIConnection api = jwtApiForServiceAccount();
        BoxAIAgent agent = BoxAI.getAiAgentDefaultConfig(api, BoxAIAgent.Mode.EXTRACT_STRUCTURED);
        BoxAIAgentExtractStructured agentExtractStructured = (BoxAIAgentExtractStructured) agent;
        // AI team is going to move away from supporting overriding embeddings model
        agentExtractStructured.getLongText().setEmbeddings(null);

        BoxFile uploadedFile = uploadFileToUniqueFolder(api, "[aiExtractStructuredWithFields] Test File.txt",
            "My name is John Doe. I was born in 4th July 1990. I am 34 years old. My hobby is guitar.");

        try {
            // When a file has been just uploaded, AI service may not be ready to return text response
            // and 412 is returned
            retry(() -> {
                BoxAIExtractStructuredResponse response = BoxAI.extractMetadataStructured(api,
                    Collections.singletonList(new BoxAIItem(uploadedFile.getID(), BoxAIItem.Type.FILE)),
                        Arrays.asList(
                            new BoxAIExtractField("firstName"),
                            new BoxAIExtractField("lastName"),
                            new BoxAIExtractField("date",
                                "Person date of birth",
                                "Birth date",
                                "dateOfBirth",
                                null,
                                "What is the date of your birth?"),
                            new BoxAIExtractField("float",
                                "Person age",
                                "Age",
                                "age",
                                null,
                                "How old are you?"),
                            new BoxAIExtractField("multiSelect",
                                "Person hobby",
                                "Hobby",
                                "hobby",
                                Collections.singletonList(new BoxAIExtractFieldOption("guitar")),
                                "What is your hobby?")
                        ),
                    agentExtractStructured);
                assertThat(response.getSourceJson().get("answer"), is(equalTo(response.getAnswer())));

                assertThat(response.getAnswer().get("firstName").asString(), is(equalTo("John")));
                assertThat(response.getAnswer().get("lastName").asString(), is(equalTo("Doe")));
                assertThat(response.getAnswer().get("dateOfBirth").asString(), is(equalTo("1990-07-04")));
                assertThat(response.getAnswer().get("age").asInt(), is(equalTo(34)));
                assertThat(response.getAnswer().get("hobby").asArray().get(0).asString(), is(equalTo("guitar")));

                assertThat(response.getCompletionReason(), equalTo("done"));
                assertThat(response.getCreatedAt(), is(notNullValue()));
            }, 2, 2000);
        } finally {
            deleteFile(uploadedFile);
        }
    }

    @Test
    public void aiExtractStructuredWithMetadataTemplate() throws InterruptedException {
        BoxAPIConnection api = jwtApiForServiceAccount();
        BoxAIAgent agent = BoxAI.getAiAgentDefaultConfig(api, BoxAIAgent.Mode.EXTRACT_STRUCTURED);
        BoxAIAgentExtractStructured agentExtractStructured = (BoxAIAgentExtractStructured) agent;
        // AI team is going to move away from supporting overriding embeddings model
        agentExtractStructured.getLongText().setEmbeddings(null);

        BoxFile uploadedFile = uploadFileToUniqueFolder(api, "[aiExtractStructuredWithMetadataTemplate] Test File.txt",
            "My name is John Doe. I was born in 4th July 1990. I am 34 years old. My hobby is guitar.");
        String templateKey = "key" + java.util.UUID.randomUUID();
        MetadataTemplate template = MetadataTemplate.createMetadataTemplate(api,
            "enterprise",
            templateKey,
            templateKey,
            false,
                Arrays.asList(
                    new MetadataTemplate.Field(Json.parse(
                        "{\"key\":\"firstName\",\"displayName\":\"First name\","
                            + "\"description\":\"Person first name\",\"type\":\"string\"}").asObject()),
                    new MetadataTemplate.Field(Json.parse(
                        "{\"key\":\"lastName\",\"displayName\":\"Last name\","
                            + "\"description\":\"Person last name\",\"type\":\"string\"}").asObject()),
                    new MetadataTemplate.Field(Json.parse(
                        "{\"key\":\"dateOfBirth\",\"displayName\":\"Birth date\","
                            + "\"description\":\"Person date of birth\",\"type\":\"date\"}").asObject()),
                    new MetadataTemplate.Field(Json.parse(
                        "{\"key\":\"age\",\"displayName\":\"Age\","
                            + "\"description\":\"Person age\",\"type\":\"float\"}").asObject()),
                    new MetadataTemplate.Field(Json.parse(
                        "{\"key\":\"hobby\",\"displayName\":\"Hobby\","
                            + "\"description\":\"Person hobby\",\"type\":\"multiSelect\","
                            + " \"options\":[{\"key\": \"guitar\"}, {\"key\": \"books\"}]}").asObject())
                ));

        try {
            // When a file has been just uploaded, AI service may not be ready to return text response
            // and 412 is returned
            retry(() -> {
                BoxAIExtractStructuredResponse response = BoxAI.extractMetadataStructured(api,
                    Collections.singletonList(new BoxAIItem(uploadedFile.getID(), BoxAIItem.Type.FILE)),
                    new BoxAIExtractMetadataTemplate(templateKey, "enterprise"),
                    agentExtractStructured);
                assertThat(response.getSourceJson().get("answer"), is(equalTo(response.getAnswer())));

                assertThat(response.getAnswer().get("firstName").asString(), is(equalTo("John")));
                assertThat(response.getAnswer().get("lastName").asString(), is(equalTo("Doe")));
                assertThat(response.getAnswer().get("dateOfBirth").asString(), is(equalTo("1990-07-04T00:00:00Z")));
                assertThat(response.getAnswer().get("age").asInt(), is(equalTo(34)));
                assertThat(response.getAnswer().get("hobby").asArray().get(0).asString(), is(equalTo("guitar")));
                assertThat(response.getCompletionReason(), equalTo("done"));
                assertThat(response.getCreatedAt(), is(notNullValue()));
            }, 2, 2000);
        } finally {
            deleteFile(uploadedFile);
            MetadataTemplate.deleteMetadataTemplate(api, template.getScope(), template.getTemplateKey());
        }
    }
}
