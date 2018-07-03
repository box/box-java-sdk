package com.box.sdk;

import static org.junit.Assert.*;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.List;

public class BoxTermsOfServiceUserStatusTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(UnitTest.class)
    public void testGetUserStatusInfoOnTermsOfServiceSucceeds() throws IOException {
        String result = "";
        final String statusID = "5678";
        final String tosID = "1234";
        final String userID = "7777";
        final String userLogin = "test@example.com";
        final String statusURL = "/terms_of_service_user_statuses";

        result = TestConfig.getFixture("BoxTermsOfService/GetTermsOfServiceForUserStatuses200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(statusURL))
                .withQueryParam("tos_id", WireMock.containing(tosID))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        List<BoxTermsOfServiceUserStatus.Info> tosUserStatusInfo = BoxTermsOfServiceUserStatus.getInfo(this.api,
                tosID, userID);

        Assert.assertEquals(statusID, tosUserStatusInfo.get(0).getID());
        Assert.assertEquals(tosID, tosUserStatusInfo.get(0).getTermsOfService().getID());
        Assert.assertEquals(userID, tosUserStatusInfo.get(0).getUser().getID());
        Assert.assertEquals(userLogin, tosUserStatusInfo.get(0).getUser().getLogin());
    }
}
