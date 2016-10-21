package com.box.sdk.legalholds;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxAPIRequest;
import com.box.sdk.BoxAPIResponse;
import com.box.sdk.BoxJSONRequest;
import com.box.sdk.JSONRequestInterceptor;
import com.box.sdk.RequestInterceptor;
import com.box.sdk.UnitTest;
import com.eclipsesource.json.JsonObject;
import com.google.gson.Gson;


public class LegalHoldPolicyTest {


    private final BoxAPIConnection apiIntercepted = new BoxAPIConnection("");

    @Test
    @Category(UnitTest.class)
    public void createLegalHoldPolicyWithDefaultParamsReturnsCreatedPolicy() throws Exception {

        final String policyName = "Policy 3";
        final String policyId = "166921";

        final JsonObject fakeJSONResponse = JsonObject.readFrom(
                "{\"type\": \"legal_hold_policy\",\"id\": \"" + policyId + "\",\"policy_name\": \"" + policyName
                        + "\",\"description\":\"postman created policy\",\"created_by\": {\"type\": \"user\",\"id\": "
                        + "\"2030388321\",\"name\": \"Ryan Churchill\",\"login\": \"rchurchill+deventerprise@box"
                        + ".com\"}, \"created_at\": \"2016-05-18T16:18:49-07:00\",\"modified_at\": "
                        + "\"2016-05-18T16:18:49-07:00\",\"deleted_at\": null,\"filter_started_at\": "
                        + "\"2016-05-11T01:00:00-07:00\",\"filter_ended_at\": \"2016-05-13T01:00:00-07:00\"}");

        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fakeJSONResponse
                .toString().getBytes());
        final ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(fakeJSONResponse
                .toString().getBytes());

        this.apiIntercepted.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                assertEquals(policyName, json.get("policy_name").asString());

                return new BoxAPIResponse() {
                    @Override
                    public InputStream getBody() {
                        return byteArrayInputStream;
                    }
                };
            }
        });

        LegalHoldPolicy.Info response = LegalHoldPolicy.createLegalHoldPolicy(this.apiIntercepted, policyName);

        LegalHoldPolicy.Info expected = new Gson().fromJson(IOUtils.toString(byteArrayInputStream2, "UTF-8"),
                LegalHoldPolicy.Info.class);

        assertThat(response, is(expected));
    }

    @Test
    @Category(UnitTest.class)
    public void retreiveLegalHoldPolicyReturnsExistingPolicy() throws Exception {

        final String policyName = "Policy 3";
        final String policyId = "166921";

        final JsonObject fakeJSONResponse = JsonObject.readFrom(
                "{\"type\": \"legal_hold_policy\",\"id\": \"" + policyId + "\",\"policy_name\": \"" + policyName
                        + "\",\"description\":\"postman created policy\",\"created_by\": {\"type\": \"user\",\"id\": "
                        + "\"2030388321\",\"name\": \"Ryan Churchill\",\"login\": \"rchurchill+deventerprise@box"
                        + ".com\"}, \"created_at\": \"2016-05-18T16:18:49-07:00\",\"modified_at\": "
                        + "\"2016-05-18T16:18:49-07:00\",\"deleted_at\": null,\"filter_started_at\": "
                        + "\"2016-05-11T01:00:00-07:00\",\"filter_ended_at\": \"2016-05-13T01:00:00-07:00\"}");

        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fakeJSONResponse
                .toString().getBytes());
        final ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(fakeJSONResponse
                .toString().getBytes());

        this.apiIntercepted.setRequestInterceptor(new RequestInterceptor() {

            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                return new BoxAPIResponse() {
                    @Override
                    public InputStream getBody() {
                        return byteArrayInputStream;
                    }
                };
            }
        });

        LegalHoldPolicy.Info response = LegalHoldPolicy.getLegalHoldPolicy(this.apiIntercepted, policyId);

        LegalHoldPolicy.Info expected = new Gson().fromJson(IOUtils.toString(byteArrayInputStream2, "UTF-8"),
                LegalHoldPolicy.Info.class);

        assertThat(response, is(expected));

    }

    @Test
    @Category(UnitTest.class)
    public void deleteExistingLegalHoldPolicyReturnsSuccessfully() throws Exception {

        final String policyId = "166921";

        this.apiIntercepted.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                return new BoxAPIResponse() {
                    @Override
                    public int getResponseCode() {
                        return 202;
                    }
                };
            }
        });

        BoxAPIResponse response = LegalHoldPolicy.deleteLegalHoldPolicy(this.apiIntercepted, policyId);

        assertThat(response.getResponseCode(), is(202));

    }
}

