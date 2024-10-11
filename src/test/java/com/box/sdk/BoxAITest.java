package com.box.sdk;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
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

    @Test
    public void testGetAIAgentDefaultConfigExtractSuccess() {
        String result = TestUtils.getFixture("BoxAI/GetAIAgentDefaultConfigExtract200");
        String urlPath = "/2.0/ai_agent_default";

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(urlPath))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxAIAgent agent = BoxAI.getAiAgentDefaultConfig(api, BoxAIAgent.Mode.EXTRACT,
            "en", "openai__gpt_3_5_turbo");
        BoxAIAgentExtract agentExtract = (BoxAIAgentExtract) agent;

        assertThat(agent.getType(), equalTo("ai_agent_extract"));
        assertThat(agentExtract.getBasicText().getModel(), equalTo("azure__openai__gpt_3_5_turbo_16k"));

        JsonObject jsonResult = Json.parse(result).asObject();
        assertThat(agent.getJSONObject().toString(), equalTo(jsonResult.toString()));
    }

    @Test
    public void testGetAIAgentDefaultConfigExtractStructuredSuccess() {
        String result = TestUtils.getFixture("BoxAI/GetAIAgentDefaultConfigExtractStructured200");
        String urlPath = "/2.0/ai_agent_default";

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(urlPath))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxAIAgent agent = BoxAI.getAiAgentDefaultConfig(api, BoxAIAgent.Mode.EXTRACT_STRUCTURED,
            "en", "openai__gpt_3_5_turbo");
        BoxAIAgentExtractStructured agentExtract = (BoxAIAgentExtractStructured) agent;

        assertThat(agent.getType(), equalTo("ai_agent_extract_structured"));
        assertThat(agentExtract.getBasicText().getModel(), equalTo("azure__openai__gpt_3_5_turbo_16k"));

        JsonObject jsonResult = Json.parse(result).asObject();
        assertThat(agent.getJSONObject().toString(), equalTo(jsonResult.toString()));
    }

    @Test
    public void testExtractMetadataFreeformSuccess() {
        String agentString = TestUtils.getFixture("BoxAI/GetAIAgentDefaultConfigExtract200");
        final String fileId = "12345";
        final String prompt = "Extract data related to contract conditions";
        final BoxAIAgentExtract agent = new BoxAIAgentExtract(Json.parse(agentString).asObject());
        JsonObject expectedRequestBody = new JsonObject();
        expectedRequestBody.add("prompt", prompt);
        expectedRequestBody.add("items", new JsonArray().add(new JsonObject()
            .add("type", "file")
            .add("id", fileId)));
        expectedRequestBody.add("ai_agent", agent.getJSONObject());

        String result = TestUtils.getFixture("BoxAI/ExtractMetadataFreeform200");
        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo("/2.0/ai/extract"))
            .withRequestBody(WireMock.equalToJson(
                expectedRequestBody.toString()
            ))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxAIResponse response = BoxAI.extractMetadataFreeform(
            api,
            prompt,
            Collections.singletonList(new BoxAIItem(fileId, BoxAIItem.Type.FILE)),
            agent
        );

        assertThat(response.getAnswer(), equalTo("Public APIs are important because of key and important reasons."));
        assertThat(response.getCreatedAt(), equalTo(new Date(1355338423123L)));
        assertThat(response.getCompletionReason(), equalTo("done"));
    }

    @Test
    public void testExtractMetadataStructuredSuccess() {
        String agentString = TestUtils.getFixture("BoxAI/GetAIAgentDefaultConfigExtractStructured200");
        final String fileId = "12345";
        final BoxAIExtractMetadataTemplate template = new BoxAIExtractMetadataTemplate("templateId", "enterprise");
        final List<BoxAIItem> items = Collections.singletonList(new BoxAIItem(fileId, BoxAIItem.Type.FILE));
        final String result = TestUtils.getFixture("BoxAI/ExtractMetadataStructured200");
        final BoxAIAgentExtractStructured agent = new BoxAIAgentExtractStructured(Json.parse(agentString).asObject());
        final BoxAIExtractField field = new BoxAIExtractField(
                "text",
                "The name of the file",
                "Name",
                "name",
                new ArrayList<BoxAIExtractFieldOption>() {{
                    add(new BoxAIExtractFieldOption("option 1"));
                    add(new BoxAIExtractFieldOption("option 2"));
                }},
                "What is the name of the file?");

        final JsonObject expectedRequestBody = new JsonObject()
            .add("items", new JsonArray().add(new JsonObject()
                .add("type", "file")
                .add("id", fileId)))
            .add("metadata_template", new JsonObject()
                .add("type", "metadata_template")
                .add("template_key", template.getTemplateKey())
                .add("scope", template.getScope()))
            .add("ai_agent", agent.getJSONObject())
            .add("fields", new JsonArray().add(field.getJSONObject()));

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo("/2.0/ai/extract_structured"))
            .withRequestBody(WireMock.equalToJson(
                expectedRequestBody.toString()
            ))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxAIExtractStructuredResponse response = BoxAI.extractMetadataStructured(
            api,
            items,
            template,
            Collections.singletonList(field),
            agent
        );

        assertThat(response.getSourceJson(), equalTo(Json.parse(result).asObject()));
        assertThat(response.getSourceJson().get("firstName").asString(), equalTo("John"));
    }
}
