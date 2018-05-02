package com.box.sdk;

import static org.hamcrest.Matchers.notNullValue;
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
    @Category(IntegrationTest.class)
    public void updateUserStatusOnTermsOfServiceSucceeds() {
        final String statusType = "terms_of_service_user_status";
        final String statusID = "1939280";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

        Boolean isAccepted = false;
        BoxTermsOfServiceUserStatus tosUserStatus = new BoxTermsOfServiceUserStatus(api, statusID);
        BoxTermsOfServiceUserStatus.Info userStatusInfo = tosUserStatus.new Info();
        userStatusInfo.setIsAccepted(isAccepted);
        tosUserStatus.updateInfo(userStatusInfo);

        assertEquals(isAccepted, userStatusInfo.getIsAccepted());
        assertEquals(statusID, userStatusInfo.getID());
        assertEquals(statusType, userStatusInfo.getType());
    }

    @Test
    @Category(IntegrationTest.class)
    public void getUserStatusInfoOnTermsOfServiceSucceeds() {
        final String statusType = "terms_of_service_user_status";
        final String tosID = "2778";
        final int tosStatusSize = 1;
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

        List<BoxTermsOfServiceUserStatus.Info> tosUserStatusInfo = BoxTermsOfServiceUserStatus.getInfo(api, tosID);

        assertEquals(tosUserStatusInfo.size(), tosStatusSize);

        for (BoxTermsOfServiceUserStatus.Info info : tosUserStatusInfo) {
            assertNotNull(info);
            assertNotNull(info.getIsAccepted());
            assertEquals(tosID, info.getTermsOfService().getID());
            assertEquals(statusType, info.getType());
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void getUserStatusInfoOnTermsOfServiceWithUserIDSucceeds() {
        final String statusType = "terms_of_service_user_status";
        final String tosID = "2778";
        final String userID = "235699372";
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

        Iterable<BoxTermsOfServiceUserStatus.Info> tosUserStatusInfo = BoxTermsOfServiceUserStatus.getInfo(api,
                tosID, userID);

        for (BoxTermsOfServiceUserStatus.Info info : tosUserStatusInfo) {
            assertEquals(info, notNullValue());
            assertEquals(statusType, info.getType());
            assertNotNull(info.getTermsOfService());
            assertNotNull(info.getIsAccepted());
        }
    }

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
