package com.box.sdk.retention;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isNull;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.box.sdk.TestConfig.getAccessToken;
import static com.box.sdk.retention.RetentionPolicyDispositionAction.remove_retention;
import static com.box.sdk.retention.RetentionPolicyStatus.active;
import static com.box.sdk.retention.RetentionPolicyType.finite;
import static com.box.sdk.retention.RetentionPolicyType.indefinite;
import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxAPIRequest;
import com.box.sdk.BoxAPIResponse;
import com.box.sdk.BoxJSONRequest;
import com.box.sdk.IntegrationTest;
import com.box.sdk.JSONRequestInterceptor;
import com.box.sdk.RequestInterceptor;
import com.box.sdk.UnitTest;
import com.eclipsesource.json.JsonObject;

public class RetentionPolicyTest {

    private static final String POLICY_NAME = "non-empty name";
    private static final String POLICY_ID = "12345";
    private static final String RETENTION_LENGTH = "30";

    private final BoxAPIConnection apiIntercepted = new BoxAPIConnection("");
    private final BoxAPIConnection api = new BoxAPIConnection(getAccessToken());

    @Test
    @Category(UnitTest.class)
    public void createIndefiniteRetentionPolicyReturnsCreatedPolicy() throws Exception {
        final JsonObject fakeJSONResponse = new JsonObject()
                .add("policy_name", POLICY_NAME)
                .add("policy_type", indefinite.toString())
                .add("disposition_action", remove_retention.toString())
                .add("id", POLICY_ID)
                .add("status", active.toString());

        this.apiIntercepted.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                assertEquals(POLICY_NAME, json.get("policy_name").asString());
                assertEquals(indefinite.toString(), json.get("policy_type").asString());
                assertEquals(remove_retention.toString(), json.get("disposition_action").asString());

                return new BoxAPIResponse() {
                    @Override
                    public InputStream getBody() {
                        return new ByteArrayInputStream(fakeJSONResponse.toString().getBytes());
                    }
                };
            }
        });

        RetentionPolicy.Info response = RetentionPolicy.createRetentionPolicy(this.apiIntercepted, POLICY_NAME,
                indefinite, remove_retention);

        RetentionPolicy.Info expected = new RetentionPolicy.Info(POLICY_NAME, indefinite, active, POLICY_ID,
                remove_retention, null);

        assertThat(response, is(expected));
    }

    @Test
    @Category(UnitTest.class)
    public void createFiniteRetentionPolicySendsJSONWithNameTypeDispositionActionAndLength() throws Exception {
        final JsonObject fakeJSONResponse = new JsonObject()
                .add("policy_name", POLICY_NAME)
                .add("policy_type", finite.toString())
                .add("retention_length", RETENTION_LENGTH)
                .add("disposition_action", remove_retention.toString())
                .add("id", POLICY_ID)
                .add("status", active.toString());

        this.apiIntercepted.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                assertEquals(POLICY_NAME, json.get("policy_name").asString());
                assertEquals(finite.toString(), json.get("policy_type").asString());
                assertEquals(RETENTION_LENGTH, json.get("retention_length").asString());
                assertEquals(remove_retention.toString(), json.get("disposition_action").asString());

                return new BoxAPIResponse() {
                    @Override
                    public InputStream getBody() {
                        return new ByteArrayInputStream(fakeJSONResponse.toString().getBytes());
                    }
                };
            }
        });

        RetentionPolicy.Info response = RetentionPolicy.createRetentionPolicy(this.apiIntercepted, POLICY_NAME, finite,
                RETENTION_LENGTH, remove_retention);

        RetentionPolicy.Info expected = new RetentionPolicy.Info(POLICY_NAME, finite, active, POLICY_ID,
                remove_retention, RETENTION_LENGTH);

        assertThat(response, is(expected));
    }

    @Test
    @Category(UnitTest.class)
    public void retrivesRetentionPolicyIndependently() throws MalformedURLException {
        final JsonObject fakeJSONResponse = new JsonObject()
                .add("policy_name", POLICY_NAME)
                .add("policy_type", finite.toString())
                .add("retention_length", RETENTION_LENGTH)
                .add("disposition_action", remove_retention.toString())
                .add("id", POLICY_ID)
                .add("status", active.toString());

        this.apiIntercepted.setRequestInterceptor(new RequestInterceptor() {

            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                return new BoxAPIResponse() {
                    @Override
                    public InputStream getBody() {
                        return new ByteArrayInputStream(fakeJSONResponse.toString().getBytes());
                    }
                };
            }
        });

        RetentionPolicy.Info response = RetentionPolicy.createRetentionPolicy(this.apiIntercepted, POLICY_NAME, finite,
                RETENTION_LENGTH, remove_retention);

        RetentionPolicy.Info getIndependentResponse = RetentionPolicy.getRetentionPolicy(this.apiIntercepted, response
                .getId());

        assertThat(response, is(getIndependentResponse));
    }

    @Test
    @Category(IntegrationTest.class)
    public void createsIndefiniteRetentionPolicySuccessfully() throws Exception {
        final String expectedName = UUID.randomUUID().toString();

        RetentionPolicy.Info response = RetentionPolicy.createRetentionPolicy(this.api, expectedName,
                indefinite, remove_retention);

        assertThat(response.getId(), not(isNull()));
        assertThat(response.getPolicyName(), is(expectedName));
        assertThat(response.getDispositionAction(), is(remove_retention));
        assertThat(response.getType(), is(indefinite));
        assertThat(response.getStatus(), is(active));
        assertThat(response.getRetentionLength(), is("indefinite"));
    }

    @Test
    @Category(IntegrationTest.class)
    public void createsDefiniteRetentionPolicySuccessfully() throws Exception {
        final String expectedName = UUID.randomUUID().toString();

        RetentionPolicy.Info response = RetentionPolicy.createRetentionPolicy(this.api, expectedName,
                finite, RETENTION_LENGTH, remove_retention);

        assertThat(response.getId(), not(isNull()));
        assertThat(response.getPolicyName(), is(expectedName));
        assertThat(response.getDispositionAction(), is(remove_retention));
        assertThat(response.getType(), is(finite));
        assertThat(response.getStatus(), is(active));
        assertThat(response.getRetentionLength(), is(RETENTION_LENGTH));
    }

    @Test
    @Category(IntegrationTest.class)
    public void retrievesRetentionPolicySuccessfully() throws Exception {
        final String expectedName = UUID.randomUUID().toString();

        RetentionPolicy.Info createdPolicy = RetentionPolicy.createRetentionPolicy(this.api, expectedName,
                indefinite, remove_retention);

        RetentionPolicy.Info retrievedPolicy = RetentionPolicy.getRetentionPolicy(this.api, createdPolicy.getId());

        assertThat(retrievedPolicy, is(createdPolicy));
    }

}

