package com.box.sdk;

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
}
