package com.box.sdk;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

import com.eclipsesource.json.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

import java.util.List;

public class BoxTermsOfServiceUserStatusTest {
    @Test
    @Category(UnitTest.class)
    public void testCreateTermsOfServiceUserStatusInfoParseAllFieldsCorrectly() throws ParseException {
        final String type = "terms_of_service_user_status";
        final String statusID = "1939280";
        final String tosID = "2778";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"type\": \"terms_of_service_user_status\",\n"
                + "    \"id\": \"1939280\",\n"
                + "    \"tos\": {\n"
                + "        \"type\": \"terms_of_service\",\n"
                + "        \"id\": \"2778\"\n"
                + "    },\n"
                + "    \"user\": {\n"
                + "        \"type\": \"user\",\n"
                + "        \"id\": \"11111\"\n"
                + "    },\n"
                + "    \"is_accepted\": \"true\",\n"
                + "    \"created_at\": \"2013-05-16T15:27:57-07:00\",\n"
                + "    \"modified_at\": \"2013-05-16T15:27:57-07:00\"\n"
                + "}");
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxTermsOfServiceUserStatus.Info tosUserStatusInfo = BoxTermsOfServiceUserStatus.create(api, tosID, true);
        Assert.assertEquals(tosID, tosUserStatusInfo.getTermsOfService().getID());
        Assert.assertEquals(statusID, tosUserStatusInfo.getID());
        Assert.assertEquals(type, tosUserStatusInfo.getType());
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateUserStatusOnTermsOfServiceSucceeds() {
        final String statusType = "terms_of_service_user_status";
        final String statusID = "1939280";

        BoxAPIConnection api = new BoxAPIConnection("");
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

        BoxAPIConnection api = new BoxAPIConnection("");
        List<BoxTermsOfServiceUserStatus.Info> tosUserStatusInfo = BoxTermsOfServiceUserStatus.getInfo(api, tosID);

        assertEquals(tosUserStatusInfo.size(), tosStatusSize);

        for (BoxTermsOfServiceUserStatus.Info info: tosUserStatusInfo) {
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

        for (BoxTermsOfServiceUserStatus.Info info: tosUserStatusInfo) {
            assertEquals(info, notNullValue());
            assertEquals(statusType, info.getType());
            assertNotNull(info.getTermsOfService());
            assertNotNull(info.getIsAccepted());
        }
    }
}
