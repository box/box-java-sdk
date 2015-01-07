package com.box.sdk;

import java.text.ParseException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.skyscreamer.jsonassert.JSONCompareMode.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class BoxGroupTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);

    @Test
    @Category(IntegrationTest.class)
    public void createGroupSucceeds() {
        final String groupName = "[createGroupSucceeds] Test Group";
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxGroup.Info createdGroupInfo = BoxGroup.createGroup(api, groupName);

        assertThat(createdGroupInfo.getName(), equalTo(groupName));

        createdGroupInfo.getResource().delete();
    }

    @Test
    @Category(UnitTest.class)
    public void createGroupSendsCorrectRequestAndParsesResponseCorrectly() throws ParseException {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:8080/");

        final String name = "Test Group";

        JsonObject mockJSON = new JsonObject()
            .add("type", "group")
            .add("id", "305742")
            .add("name", name)
            .add("created_at", "2015-01-05T16:08:27-08:00")
            .add("modified_at", "2015-01-05T16:08:27-08:00");

        stubFor(post(urlEqualTo("/groups"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(mockJSON.toString())));

        BoxGroup.Info groupInfo = BoxGroup.createGroup(api, name);
        assertThat(groupInfo.getID(), is(equalTo(mockJSON.get("id").asString())));
        assertThat(groupInfo.getName(), is(equalTo(mockJSON.get("name").asString())));
        assertThat(groupInfo.getCreatedAt(), is(equalTo(BoxDateFormat.parse(mockJSON.get("created_at").asString()))));
        assertThat(groupInfo.getModifiedAt(), is(equalTo(BoxDateFormat.parse(mockJSON.get("modified_at").asString()))));

        JsonObject expectedJSON = new JsonObject()
            .add("name", name);

        verify(postRequestedFor(urlEqualTo("/groups"))
            .withHeader("Content-Type", WireMock.equalTo("application/json"))
            .withRequestBody(equalToJson(expectedJSON.toString(), LENIENT)));
    }

    @Test
    @Category(UnitTest.class)
    public void deleteGroupSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:8080/");

        final String groupID = "1";
        final String groupURL = "/groups/" + groupID;

        stubFor(delete(urlEqualTo(groupURL))
            .willReturn(aResponse()
                .withStatus(204)));

        BoxGroup group = new BoxGroup(api, groupID);
        group.delete();

        verify(deleteRequestedFor(urlEqualTo(groupURL))
            .withRequestBody(WireMock.equalTo("")));
    }
}
