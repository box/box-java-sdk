package com.box.sdk;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * {@link BoxAI} related unit tests.
 */
public class BoxAITest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicHttpsPort().httpDisabled(true));
    private final BoxAPIConnection api = TestUtils.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("https://localhost:%d", wireMockRule.httpsPort()));
    }

    @Test
    public void testSendAIRequestSuccess() {
        final String fileId = "12345";
        final String prompt = "What is the name of the file?";

        String result = TestUtils.getFixture("BoxAI/SendAIRequest200");
        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo("/2.0/ai/ask"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", APPLICATION_JSON)
                        .withBody(result)));

        BoxAIResponse response = BoxAI.sendAIRequest(
                api,
                prompt,
                Collections.singletonList(new BoxAIItem(fileId, BoxAIItem.Type.FILE)),
                BoxAI.Mode.SINGLE_ITEM_QA
        );

        assertThat(
                response.getAnswer(), equalTo("Public APIs are important because of key and important reasons.")
        );
        assertThat(response.getCreatedAt(), equalTo(new Date(1355338423123L)));
        assertThat(response.getCompletionReason(), equalTo("done"));
    }

    @Test
    public void testSendAITexGenRequestWithNoDialogueHistorySuccess() {
        final String fileId = "12345";
        final String prompt = "What is the name of the file?";

        String result = TestUtils.getFixture("BoxAI/SendAITextGen200");
        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo("/2.0/ai/text_gen"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", APPLICATION_JSON)
                        .withBody(result)));

        BoxAIResponse response = BoxAI.sendAITextGenRequest(
                api,
                prompt,
                Collections.singletonList(new BoxAIItem(fileId, BoxAIItem.Type.FILE))
        );

        assertThat(
                response.getAnswer(), equalTo("Public APIs are important because of key and important reasons.")
        );
        assertThat(response.getCreatedAt(), equalTo(new Date(1355338423123L)));
        assertThat(response.getCompletionReason(), equalTo("done"));
    }

    @Test
    public void testSendAITexGenRequestWithDialogueHistorySuccess() throws ParseException {
        final String fileId = "12345";
        final String prompt = "What is the name of the file?";

        Date date1 = BoxDateFormat.parse("2013-05-16T15:27:57-07:00");
        Date date2 = BoxDateFormat.parse("2013-05-16T15:26:57-07:00");

        List<BoxAIDialogueEntry> dialogueHistory = new ArrayList<>();
        dialogueHistory.add(
                new BoxAIDialogueEntry("What is the name of the file?", "Test file", date1)
        );
        dialogueHistory.add(
                new BoxAIDialogueEntry("What is the size of the file?", "10kb", date2)
        );

        String result = TestUtils.getFixture("BoxAI/SendAITextGen200");
        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo("/2.0/ai/text_gen"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", APPLICATION_JSON)
                        .withBody(result)));

        BoxAIResponse response = BoxAI.sendAITextGenRequest(
                api,
                prompt,
                Collections.singletonList(new BoxAIItem(fileId, BoxAIItem.Type.FILE)),
                dialogueHistory
        );

        assertThat(
                response.getAnswer(), equalTo("Public APIs are important because of key and important reasons.")
        );
        assertThat(response.getCreatedAt(), equalTo(new Date(1355338423123L)));
        assertThat(response.getCompletionReason(), equalTo("done"));
    }

    @Test
    public void testSendAIRequestWithAgentSuccess() throws ParseException {
        final String fileId = "12345";
        final String prompt = "What is the name of the file?";
        List<BoxAIDialogueEntry> dialogueHistory = new ArrayList<>();
        Date date1 = BoxDateFormat.parse("2013-05-16T15:27:57-07:00");
        Date date2 = BoxDateFormat.parse("2013-05-16T15:26:57-07:00");
        dialogueHistory.add(
            new BoxAIDialogueEntry("What is the name of the file?", "Test file", date1)
        );
        dialogueHistory.add(
            new BoxAIDialogueEntry("What is the size of the file?", "10kb", date2)
        );

        String result = TestUtils.getFixture("BoxAI/SendAIRequest200");
        String agentText = TestUtils.getFixture("BoxAI/GetAIAgentDefaultConfigAsk200");
        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo("/2.0/ai/ask"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", APPLICATION_JSON)
                        .withBody(result)));

        BoxAIAgentAsk agent = new BoxAIAgentAsk(Json.parse(agentText).asObject());
        BoxAIResponse response = BoxAI.sendAIRequest(
                api,
                prompt,
                Collections.singletonList(new BoxAIItem(fileId, BoxAIItem.Type.FILE)),
                BoxAI.Mode.SINGLE_ITEM_QA,
                dialogueHistory,
                agent,
                false
        );

        assertThat(
                response.getAnswer(), equalTo("Public APIs are important because of key and important reasons.")
        );
        assertThat(response.getCreatedAt(), equalTo(new Date(1355338423123L)));
        assertThat(response.getCompletionReason(), equalTo("done"));
    }

    @Test
    public void testSendAITextGenRequestWithAgentSuccess() throws ParseException {
        final String fileId = "12345";
        final String prompt = "What is the name of the file?";
        Date date1 = BoxDateFormat.parse("2013-05-16T15:27:57-07:00");
        Date date2 = BoxDateFormat.parse("2013-05-16T15:26:57-07:00");
        List<BoxAIDialogueEntry> dialogueHistory = new ArrayList<>();
        dialogueHistory.add(
            new BoxAIDialogueEntry("What is the name of the file?", "Test file", date1)
        );
        dialogueHistory.add(
            new BoxAIDialogueEntry("What is the size of the file?", "10kb", date2)
        );

        String result = TestUtils.getFixture("BoxAI/SendAITextGen200");
        String agentText = TestUtils.getFixture("BoxAI/GetAIAgentDefaultConfigTextGen200");
        BoxAIAgentTextGen agent = new BoxAIAgentTextGen(Json.parse(agentText).asObject());

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo("/2.0/ai/text_gen"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxAIResponse response = BoxAI.sendAITextGenRequest(
            api,
            prompt,
            Collections.singletonList(new BoxAIItem(fileId, BoxAIItem.Type.FILE)),
            dialogueHistory,
            agent
        );

        assertThat(
            response.getAnswer(), equalTo("Public APIs are important because of key and important reasons.")
        );
        assertThat(response.getCreatedAt(), equalTo(new Date(1355338423123L)));
        assertThat(response.getCompletionReason(), equalTo("done"));
    }

    @Test
    public void testGetAIAgentDefaultConfigAskSuccess() {
        String result = TestUtils.getFixture("BoxAI/GetAIAgentDefaultConfigAsk200");
        String urlPath = "/2.0/ai_agent_default";

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(urlPath))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", APPLICATION_JSON)
                        .withBody(result)));

        BoxAIAgent agent = BoxAI.getAiAgentDefaultConfig(api, BoxAIAgent.Mode.ASK, "en", "openai__gpt_3_5_turbo");
        BoxAIAgentAsk agentAsk = (BoxAIAgentAsk) agent;

        assertThat(agent.getType(), equalTo("ai_agent_ask"));
        assertThat(agentAsk.getBasicText().getModel(), equalTo("openai__gpt_3_5_turbo"));

        JsonObject jsonResult = Json.parse(result).asObject();
        assertThat(agent.getJSONObject().toString(), equalTo(jsonResult.toString()));
    }

    @Test
    public void testGetAIAgentDefaultConfigTextGenSuccess() {
        String result = TestUtils.getFixture("BoxAI/GetAIAgentDefaultConfigTextGen200");
        String urlPath = "/2.0/ai_agent_default";

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(urlPath))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", APPLICATION_JSON)
                        .withBody(result)));

        BoxAIAgent agent = BoxAI.getAiAgentDefaultConfig(api, BoxAIAgent.Mode.TEXT_GEN, "en", "openai__gpt_3_5_turbo");
        BoxAIAgentTextGen agentTextGen = (BoxAIAgentTextGen) agent;

        assertThat(agent.getType(), equalTo("ai_agent_text_gen"));
        assertThat(agentTextGen.getBasicGen().getModel(), equalTo("openai__gpt_3_5_turbo"));

        JsonObject jsonResult = Json.parse(result).asObject();
        assertThat(agent.getJSONObject().toString(), equalTo(jsonResult.toString()));
    }
}
