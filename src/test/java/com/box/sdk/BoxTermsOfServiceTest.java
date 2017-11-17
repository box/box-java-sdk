package com.box.sdk;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import com.eclipsesource.json.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

public class BoxTermsOfServiceTest {

    @Test
    @Category(UnitTest.class)
    public void testGetTermsOfServiceInfoParseAllFieldsCorrectly() throws ParseException {
        final String type = "terms_of_service";
        final String id = "2778";
        final String status = "enabled";
        final String tosType = "managed";
        final String text = "updated text";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"type\": \"terms_of_service\",\n"
                + "    \"id\": \"2778\",\n"
                + "    \"status\": \"enabled\",\n"
                + "    \"enterprise\": {\n"
                + "        \"type\": \"enterprise\",\n"
                + "        \"id\": \"11111\",\n"
                + "        \"name\": \"Test\"\n"
                + "    },\n"
                + "    \"tos_type\": \"managed\",\n"
                + "    \"text\": \"updated text\"\n"
                + "}");
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxTermsOfService.Info termsOfServiceInfo = new BoxTermsOfService(api, "2778").getInfo();
        Assert.assertEquals(type, termsOfServiceInfo.getType());
        Assert.assertEquals(id, termsOfServiceInfo.getID());
        Assert.assertEquals(status, termsOfServiceInfo.getStatus());
        Assert.assertEquals(tosType, termsOfServiceInfo.getTosType());
        Assert.assertEquals(text, termsOfServiceInfo.getText());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllTermsOfServicesInfoParseAllFieldsCorrectly() throws ParseException {
        final String type = "terms_of_service";
        final String id = "2778";
        final String status = "enabled";
        final String tosType = "managed";
        final String text = "updated text";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "        \"total_count\": 1,\n"
                + "        \"entries\": [\n"
                + "            {\n"
                + "                \"type\": \"terms_of_service\",\n"
                + "                \"id\": \"2778\",\n"
                + "                \"status\": \"enabled\",\n"
                + "                \"enterprise\": {\n"
                + "                     \"type\": \"enterprise\",\n"
                + "                     \"id\": \"11111\",\n"
                + "                     \"name\": \"Test\"\n"
                + "                },\n"
                + "                \"tos_type\": \"managed\",\n"
                + "                \"text\": \"updated text\"\n"
                + "            }\n"
                + "        ]\n"
                + "}");
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        Iterable<BoxTermsOfService.Info> termsOfServices = BoxTermsOfService.getAllTermsOfServices(api);

        for (BoxTermsOfService.Info tosInfo : termsOfServices) {
            Assert.assertEquals(type, tosInfo.getType());
            Assert.assertEquals(id, tosInfo.getID());
            Assert.assertEquals(status, tosInfo.getStatus());
            Assert.assertEquals(tosType, tosInfo.getTosType());
            Assert.assertEquals(text, tosInfo.getText());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void testUpdateTermsOfServiceInfoParseAllFieldsCorrectly() throws ParseException {
        final String type = "terms_of_service";
        final String id = "2778";
        final String status = "disabled";
        final String text = "new updated text";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"type\": \"terms_of_service\",\n"
                + "    \"id\": \"2778\",\n"
                + "    \"status\": \"disabled\",\n"
                + "    \"enterprise\": {\n"
                + "        \"type\": \"enterprise\",\n"
                + "        \"id\": \"11111\",\n"
                + "        \"name\": \"Test\"\n"
                + "    },\n"
                + "    \"tos_type\": \"managed\",\n"
                + "    \"text\": \"new updated text\"\n"
                + "}");
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxTermsOfService termsOfService = new BoxTermsOfService(api, "2778");
        BoxTermsOfService.Info info = termsOfService.new Info();
        info.setText(text);
        info.setStatus(status);
        termsOfService.updateInfo(info);

        Assert.assertEquals(text, info.getText());
        Assert.assertEquals(status, info.getStatus());
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateTermsOfServiceInfoParseAllFieldsCorrectly() throws ParseException {
        final String type = "terms_of_service";
        final String id = "2778";
        final String status = "disabled";
        final String text = "new updated text";
        final String tosType = "managed";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"type\": \"terms_of_service\",\n"
                + "    \"id\": \"2778\",\n"
                + "    \"status\": \"disabled\",\n"
                + "    \"enterprise\": {\n"
                + "        \"type\": \"enterprise\",\n"
                + "        \"id\": \"11111\",\n"
                + "        \"name\": \"Test\"\n"
                + "    },\n"
                + "    \"tos_type\": \"managed\",\n"
                + "    \"text\": \"new updated text\"\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));
        BoxTermsOfService.Info termsOfService = BoxTermsOfService.create(api, status, tosType, text);

        Assert.assertEquals(text, termsOfService.getText());
        Assert.assertEquals(status, termsOfService.getStatus());
        Assert.assertEquals(tosType, termsOfService.getTosType());
    }



    @Test
    @Category(IntegrationTest.class)
    public void getTermsOfServiceInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTermsOfService termsOfService = new BoxTermsOfService(api, "2778");
        BoxTermsOfService.Info tosInfo = termsOfService.getInfo();

        assertThat(tosInfo, is(notNullValue()));
    }

    @Test
    @Category(IntegrationTest.class)
    public void getTermsOfServiceInfoFails() {
        try {
            BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
            BoxTermsOfService termsOfService = new BoxTermsOfService(api, "");
            BoxTermsOfService.Info tosInfo = termsOfService.getInfo();

        } catch (Exception e) {
            fail("Exception during test execution: " + e);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void getAllTermsOfServicesWithNoParamSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        Iterable<BoxTermsOfService.Info> termsOfServicesInfo = BoxTermsOfService.getAllTermsOfServices(api);

        for (BoxTermsOfService.Info info: termsOfServicesInfo) {
            assertThat(info, is(notNullValue()));
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void getAllTermsOfServicesWithParamSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection("");
        Iterable<BoxTermsOfService.Info> termsOfServicesInfo = BoxTermsOfService.getAllTermsOfServices(api,
                "managed");

        for (BoxTermsOfService.Info info: termsOfServicesInfo) {
            assertThat(info, is(notNullValue()));
        }
    }


    @Test
    @Category(IntegrationTest.class)
    public void updateTermsOfServiceInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String newStatus = "enabled";
        String newText = "New text";
        BoxTermsOfService termsOfService = new BoxTermsOfService(api, "2778");
        BoxTermsOfService.Info info = termsOfService.new Info();

        info.setStatus(newStatus);
        info.setText(newText);

        termsOfService.updateInfo(info);

        assertThat(info.getStatus(), is(equalTo("disabled")));
        assertThat(info.getText(),  is(equalTo("This is a new text")));
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateTermsOfServiceInfoFails() {
        try {
            BoxAPIConnection api = new BoxAPIConnection("");
            BoxTermsOfService termsOfService = new BoxTermsOfService(api, "");
            BoxTermsOfService.Info info = termsOfService.new Info();

            info.setStatus(null);
            info.setText(null);

            termsOfService.updateInfo(info);

        } catch (Exception e) {
            fail("Exception during test execution: " + e);
        }
    }
}
