package com.box.sdk;

import java.util.Date;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

/**
 * {@link BoxFileVersionLegalHoldTest} related unit tests.
 */
public class BoxFileVersionLegalHoldTest {

    /**
     * Unit test for {@link BoxFileVersionLegalHold#getInfo(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/file_version_legal_holds/0?fields=file",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxFileVersionLegalHold hold = new BoxFileVersionLegalHold(api, "0");
        hold.getInfo("file");
    }

    /**
     * Unit test for {@link BoxFileVersionLegalHold#getInfo(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoParseAllFieldsCorrectly() {
        final String id = "240997";
        final String fileVersionID = "141649417";
        final String fileID = "5025122933";
        final String fileEtag = "1";
        final String firstPolicyID = "255473";
        final String secondPolicyID = "255617";
        final Date deletedAt = null;

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "  \"type\": \"legal_hold\",\n"
                + "  \"id\": \"240997\",\n"
                + "  \"file_version\": {\n"
                + "    \"type\": \"file_version\",\n"
                + "    \"id\": \"141649417\"\n"
                + "  },\n"
                + "  \"file\": {\n"
                + "    \"type\": \"file\",\n"
                + "    \"id\": \"5025122933\",\n"
                + "    \"etag\": \"1\"\n"
                + "  },\n"
                + "  \"legal_hold_policy_assignments\": [\n"
                + "    {\n"
                + "      \"type\": \"legal_hold_policy_assignment\",\n"
                + "      \"id\": \"255473\"\n"
                + "    },\n"
                + "    {\n"
                + "      \"type\": \"legal_hold_policy_assignment\",\n"
                + "      \"id\": \"255617\"\n"
                + "    }\n"
                + "  ],\n"
                + "  \"deleted_at\": null\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxFileVersionLegalHold hold = new BoxFileVersionLegalHold(api, id);
        BoxFileVersionLegalHold.Info info = hold.getInfo();
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(fileVersionID, info.getFileVersion().getID());
        Assert.assertEquals(fileID, info.getFileVersion().getFileID());
        Assert.assertEquals(fileID, info.getFile().getID());
        Assert.assertEquals(fileEtag, info.getFile().getEtag());
        Assert.assertEquals(deletedAt, info.getDeletedAt());
        Iterator<BoxLegalHoldAssignment.Info> iterator = info.getAssignments().iterator();
        Assert.assertEquals(firstPolicyID, iterator.next().getID());
        Assert.assertEquals(secondPolicyID, iterator.next().getID());
        Assert.assertEquals(false, iterator.hasNext());
    }
}
