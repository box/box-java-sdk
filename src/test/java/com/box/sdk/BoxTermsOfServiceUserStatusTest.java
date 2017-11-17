package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Iterator;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.eclipsesource.json.ParseException;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class BoxTermsOfServiceUserStatusTest {

    @Test
    @Category(UnitTest.class)
    public void testGetUserStatusInfoForTermsOfServiceParseAllFieldsCorrectly() throws ParseException {
        final String type = "terms_of_service_user_status";
        final String id = "11111111";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "        \"total_count\": 1,\n"
                + "        \"entries\": [\n"
                + "            {\n"
                + "                \"type\": \"terms_of_service_user_status\",\n"
                + "                \"id\": \"11111111\",\n"
                + "                \"tos\": {\n"
                + "                     \"type\": \"terms_of_service\",\n"
                + "                     \"id\": \"11111\"\n"
                + "                },\n"
                + "                \"user\": {\n"
                + "                     \"type\": \"user\",\n"
                + "                     \"id\": \"1234\"\n"
                + "                },\n"
                + "                \"is_accepted\": true\n"
                + "            }\n"
                + "        ]\n"
                + "}");
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        Iterable<BoxTermsOfServiceUserStatus.Info> termsOfServicesUserStatus = BoxTermsOfServiceUserStatus.getInfo(api, "2778");

        for(BoxTermsOfServiceUserStatus.Info tosUserStatusInfo : termsOfServicesUserStatus){
            Assert.assertEquals(type, tosUserStatusInfo.getType());
            Assert.assertEquals(id, tosUserStatusInfo.getID());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateUserStatusForTermsOfServiceParseAllFieldsCorrectly() throws ParseException {
        final String type = "terms_of_service_user_status";
        final String id = "11111111";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"type\": \"terms_of_service_user_status\",\n"
                + "    \"id\": \"11111111\",\n"
                + "    \"tos\": {\n"
                + "        \"type\": \"terms_of_service\",\n"
                + "        \"id\": \"2778\"\n"
                + "    },\n"
                + "    \"user\": {\n"
                + "        \"type\": \"user\",\n"
                + "        \"id\": \"1234\"\n"
                + "    },\n"
                + "    \"is_accepted\": true\n"
                + "}");
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxTermsOfServiceUserStatus.Info termsOfServicesUserStatus = BoxTermsOfServiceUserStatus.create(api, "2778", true);
        Assert.assertEquals(type, termsOfServicesUserStatus.getType());
        Assert.assertEquals(id, termsOfServicesUserStatus.getID());
    }

    @Test
    @Category(UnitTest.class)
    public void testUpdateUserStatusForTermsOfServiceParseAllFieldsCorrectly() throws ParseException {
        final String type = "terms_of_service_user_status";
        final String id = "11111111";
        final Boolean isAccepted = true;

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"type\": \"terms_of_service_user_status\",\n"
                + "    \"id\": \"11111111\",\n"
                + "    \"tos\": {\n"
                + "        \"type\": \"terms_of_service\",\n"
                + "        \"id\": \"2778\"\n"
                + "    },\n"
                + "    \"user\": {\n"
                + "        \"type\": \"user\",\n"
                + "        \"id\": \"1234\"\n"
                + "    },\n"
                + "    \"is_accepted\": true\n"
                + "}");
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));
        BoxTermsOfServiceUserStatus toUserStatus = new BoxTermsOfServiceUserStatus(api, "1939280");
        BoxTermsOfServiceUserStatus.Info userStatusInfo = toUserStatus.new Info();
        userStatusInfo.setIsAccepted(true);
        toUserStatus.updateInfo(userStatusInfo);

        Assert.assertEquals(isAccepted, userStatusInfo.getIsAccepted());
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateUserStatusOnTermsOfServiceSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection("");
        Boolean isAccepted = false;
        BoxTermsOfServiceUserStatus tosUserStatus = new BoxTermsOfServiceUserStatus(api, "1939280");
        BoxTermsOfServiceUserStatus.Info userStatusInfo = tosUserStatus.new Info();
        userStatusInfo.setIsAccepted(isAccepted);
        tosUserStatus.updateInfo(userStatusInfo);

        assertThat(userStatusInfo.getIsAccepted(), is(equalTo(isAccepted)));
    }

    @Test
    @Category(IntegrationTest.class)
    public void getUserStatusInfoOnTermsOfServiceSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        Iterable<BoxTermsOfServiceUserStatus.Info> tosUserStatusInfo = BoxTermsOfServiceUserStatus.getInfo(api, "2778");

        for(BoxTermsOfServiceUserStatus.Info info: tosUserStatusInfo){
            assertThat(info, is(notNullValue()));
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void getUserStatusInfoOnTermsOfServiceWithUserIDSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        Iterable<BoxTermsOfServiceUserStatus.Info> tosUserStatusInfo = BoxTermsOfServiceUserStatus.getInfo(api, "2778", "");

        for(BoxTermsOfServiceUserStatus.Info info: tosUserStatusInfo){
            assertThat(info, is(notNullValue()));
        }
    }
}
