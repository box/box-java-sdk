package com.box.sdk;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class BoxTermsOfServiceUserStatusTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
    private final BoxAPIConnection api = TestUtils.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("http://localhost:%d", wireMockRule.port()));
    }

    @Test
    public void testGetUserStatusInfoOnTermsOfServiceSucceeds() throws IOException {
        final String statusID = "5678";
        final String tosID = "1234";
        final String userID = "7777";
        final String userLogin = "test@example.com";
        final String statusURL = "/2.0/terms_of_service_user_statuses";

        String result = TestUtils.getFixture("BoxTermsOfService/GetTermsOfServiceForUserStatuses200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(statusURL))
            .withQueryParam("tos_id", WireMock.containing(tosID))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        List<BoxTermsOfServiceUserStatus.Info> tosUserStatusInfo =
            BoxTermsOfServiceUserStatus.getInfo(this.api, tosID, userID);

        Assert.assertEquals(statusID, tosUserStatusInfo.get(0).getID());
        Assert.assertEquals(tosID, tosUserStatusInfo.get(0).getTermsOfService().getID());
        Assert.assertEquals(userID, tosUserStatusInfo.get(0).getUser().getID());
        Assert.assertEquals(userLogin, tosUserStatusInfo.get(0).getUser().getLogin());
    }
}
