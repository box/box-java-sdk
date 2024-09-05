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

import java.text.ParseException;
import java.util.ArrayList;
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
        Date date1 = BoxDateFormat.parse("2013-05-16T15:27:57-07:00");
        Date date2 = BoxDateFormat.parse("2013-05-16T15:26:57-07:00");

        BoxFile uploadedFile =  uploadFileToUniqueFolder(api, fileName, "Test file");
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
        BoxAIAgent agent = BoxAI.getAiAgentDefaultConfig(api, BoxAIAgent.Mode.ASK,
            "en", "openai__gpt_3_5_turbo");
        BoxAIAgentAsk askAgent = (BoxAIAgentAsk) agent;

        assertThat(askAgent.getType(), is(equalTo(BoxAIAgentAsk.TYPE)));
        assertThat(askAgent.getBasicText().getModel(), is(equalTo("openai__gpt_3_5_turbo")));

        BoxAIAgent agent2 = BoxAI.getAiAgentDefaultConfig(api, BoxAIAgent.Mode.TEXT_GEN,
            "en", "openai__gpt_3_5_turbo");
        BoxAIAgentTextGen textGenAgent = (BoxAIAgentTextGen) agent2;

        assertThat(textGenAgent.getType(), is(equalTo(BoxAIAgentTextGen.TYPE)));
        assertThat(textGenAgent.getBasicGen().getModel(), is(equalTo("openai__gpt_3_5_turbo")));
    }

    @Test
    public void askAISingleItemWithAgent() throws InterruptedException {
        BoxAPIConnection api = jwtApiForServiceAccount();
        String fileName = "[askAISingleItem] Test File.txt";
        BoxFile uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
        BoxAIAgent agent = BoxAI.getAiAgentDefaultConfig(api, BoxAIAgent.Mode.ASK,
            "en", "openai__gpt_3_5_turbo_16k");
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
}
