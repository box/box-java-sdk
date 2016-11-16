package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

/**
 * {@link BoxFileVersionRetention} related unit tests.
 */
public class BoxFileVersionRetentionTest {

    /**
     * Unit test for {@link BoxFileVersionRetention#getInfo(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/file_version_retentions/0?fields=file%2Capplied_at",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxFileVersionRetention retention = new BoxFileVersionRetention(api, "0");
        retention.getInfo("file", "applied_at");
    }

    /**
     * Unit test for {@link BoxFileVersionRetention#getInfo(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoParseAllFieldsCorrectly() throws ParseException {
        final String id = "112729";
        final Date appliedAt = BoxDateFormat.parse("2015-08-06T22:02:24-07:00");
        final Date dispositionAt = BoxDateFormat.parse("2016-08-06T21:45:28-07:00");
        final String winningPolicyID = "41173";
        final String winningPolicyName = "Tax Documents";
        final String fileVersionID = "124887629";
        final String fileVersionSHA1 = "4262d6250b0e6f440dca43a2337bd4621bad9136";
        final String fileID = "5011706273";
        final String fileEtag = "2";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"type\": \"file_version_retention\",\n"
                + "    \"id\": \"112729\",\n"
                + "    \"applied_at\": \"2015-08-06T22:02:24-07:00\",\n"
                + "    \"disposition_at\": \"2016-08-06T21:45:28-07:00\",\n"
                + "    \"winning_retention_policy\": {\n"
                + "        \"type\": \"retention_policy\",\n"
                + "        \"id\": \"41173\",\n"
                + "        \"policy_name\": \"Tax Documents\"\n"
                + "    },\n"
                + "    \"file_version\": {\n"
                + "        \"type\": \"file_version\",\n"
                + "        \"id\": \"124887629\",\n"
                + "        \"sha1\": \"4262d6250b0e6f440dca43a2337bd4621bad9136\"\n"
                + "    },\n"
                + "    \"file\": {\n"
                + "        \"type\": \"file_version\",\n"
                + "        \"id\": \"5011706273\",\n"
                + "        \"etag\": \"2\"\n"
                + "    }\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxFileVersionRetention retention = new BoxFileVersionRetention(api, id);
        BoxFileVersionRetention.Info info = retention.getInfo();
        Assert.assertEquals(appliedAt, info.getAppliedAt());
        Assert.assertEquals(dispositionAt, info.getDispositionAt());
        Assert.assertEquals(winningPolicyID, info.getWinningPolicy().getID());
        Assert.assertEquals(winningPolicyName, info.getWinningPolicy().getPolicyName());
        Assert.assertEquals(fileVersionID, info.getFileVersion().getID());
        Assert.assertEquals(fileVersionSHA1, info.getFileVersion().getSha1());
        Assert.assertEquals(fileID, info.getFile().getID());
        Assert.assertEquals(fileEtag, info.getFile().getEtag());
    }

    /**
     * Unit test for {@link BoxFileVersionRetention#getAll(BoxAPIConnection, String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetAllSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/file_version_retentions", request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"entries\": []}";
                    }
                };
            }
        });

        BoxFileVersionRetention.getAll(api);
    }

    /**
     * Unit test for
     * {@link BoxFileVersionRetention#getRetentions(BoxAPIConnection, BoxFileVersionRetention.QueryFilter, String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetRetentionsSendsCorrectRequest() throws ParseException {
        final String urlString = "https://api.box.com/2.0/file_version_retentions"
                + "?file_id=0&file_version_id=1&policy_id=2"
                + "&disposition_action=permanently_delete"
                + "&disposition_before=2016-09-15T13%3A15%3A35%2B0000"
                + "&disposition_after=2014-09-15T13%3A15%3A35%2B0000"
                + "&fields=file%2Capplied_at"
                + "&limit=100";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                try {
                    Assert.assertEquals(new URL(urlString), request.getUrl());
                } catch (MalformedURLException e) {
                    assert false;
                }
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"entries\": []}";
                    }
                };
            }
        });

        BoxFileVersionRetention.QueryFilter filter = new BoxFileVersionRetention.QueryFilter()
                .addFileID("0")
                .addFileVersionID("1")
                .addPolicyID("2")
                .addDispositionAction(BoxRetentionPolicy.ACTION_PERMANENTLY_DELETE)
                .addDispositionBefore(BoxDateFormat.parse("2016-09-15T13:15:35+0000"))
                .addDispositionAfter(BoxDateFormat.parse("2014-09-15T13:15:35+0000"));

        Iterator<BoxFileVersionRetention.Info> iterator
            = BoxFileVersionRetention.getRetentions(api, filter, "file", "applied_at").iterator();
        iterator.hasNext();
    }

    /**
     * Unit test for {@link BoxFileVersionRetention#getAll(BoxAPIConnection, String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetAllParseAllFieldsCorrectly() {
        final String firstEntryID = "112725";
        final String secondEntryID = "112733";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "      \"entries\": [\n"
                + "            {\n"
                + "                \"type\": \"file_version_retention\",\n"
                + "                \"id\": \"112725\"\n"
                + "            },\n"
                + "            {\n"
                + "                \"type\": \"file_version_retention\",\n"
                + "                \"id\": \"112733\"\n"
                + "            }\n"
                + "        ],\n"
                + "        \"limit\": 100,\n"
                + "        \"order\": [\n"
                + "            {\n"
                + "                \"by\": \"file_version_id\",\n"
                + "                \"direction\": \"ASC\"\n"
                + "            }\n"
                + "        ]\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        Iterator<BoxFileVersionRetention.Info> iterator = BoxFileVersionRetention.getAll(api).iterator();
        BoxFileVersionRetention.Info info = iterator.next();
        Assert.assertEquals(firstEntryID, info.getID());
        info = iterator.next();
        Assert.assertEquals(secondEntryID, info.getID());
        Assert.assertEquals(false, iterator.hasNext());
    }

}
