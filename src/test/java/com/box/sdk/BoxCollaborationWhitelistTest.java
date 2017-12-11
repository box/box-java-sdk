package com.box.sdk;

import java.util.Iterator;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.eclipsesource.json.JsonObject;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BoxCollaborationWhitelistTest {
    @Test
    @Category(UnitTest.class)
    public void testGetAllWhitelistingForUserWithParamsParseAllFieldsCorrectly() {
        final String firstEntryType = "collaboration_whitelist_entry";
        final String firstEntryID = "558459";
        final String firstEntryUserType = "user";
        final String firstEntryUserID = "275111333";
        final String firstEntryUserName = "Cary Cheng";
        final String firstEntryUserLogin = "AppUser_221486_CSrKz0XXbv@boxdevedition.com";
        final int collaborationWhitelistDefaultLimit = 4;
        final String paramCollaborationWhitelistNextMarker =
                "byJ04XBlIZoiCWQFLHJkaJIiOiJuZXhJIiwudtypuCnn6IjU1ODiyybJ8";
        final String collaborationWhitelistNextMarker = "eyJ0eXBlIjoiaWQiLCJkaXIiOiJuZXh0IiwidGFpbCI6IjU1ODIyMyJ9";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"entries\": ["
                + "        {"
                + "            \"type\": \"collaboration_whitelist_entry\",\n"
                + "            \"id\": \"558459\",\n"
                + "            \"domain\": \"test1.com\",\n"
                + "            \"direction\": \"both\"\n"
                + "        },"
                + "        {"
                + "            \"type\": \"collaboration_whitelist_entry\",\n"
                + "            \"id\": \"567889\",\n"
                + "            \"domain\": \"test2.com\",\n"
                + "            \"direction\": \"inbound\"\n"
                + "        },"
                + "        {"
                + "            \"type\": \"collaboration_whitelist_entry\",\n"
                + "            \"id\": \"554354\",\n"
                + "            \"domain\": \"test3.com\",\n"
                + "            \"direction\": \"both\"\n"
                + "        },"
                + "        {"
                + "            \"type\": \"collaboration_whitelist_entry\",\n"
                + "            \"id\": \"543244\",\n"
                + "            \"domain\": \"test4.com\",\n"
                + "            \"direction\": \"both\"\n"
                + "        }"
                + "    ],"
                + "    \"next_marker\": \"eyJ0eXBlIjoiaWQiLCJkaXIiOiJuZXh0IiwidGFpbCI6IjU1ODIyMyJ9\","
                + "    \"limit\": \"4\""
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        Iterator<BoxCollaborationWhitelist.Info> whitelists =
                BoxCollaborationWhitelist.getAll(api, collaborationWhitelistDefaultLimit,
                        paramCollaborationWhitelistNextMarker).iterator();

        BoxCollaborationWhitelist.Info entry = whitelists.next();
        Assert.assertEquals(firstEntryType, entry.getType());
        Assert.assertEquals(firstEntryID, entry.getID());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllWhitelistingForUserWithoutParamsParseAllFieldsCorrectly() {
        final String firstEntryType = "collaboration_whitelist_entry";
        final String firstEntryID = "558459";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"entries\": ["
                + "        {"
                + "            \"type\": \"collaboration_whitelist_entry\",\n"
                + "            \"id\": \"558459\",\n"
                + "            \"domain\": \"test1.com\",\n"
                + "            \"direction\": \"both\"\n"
                + "        },"
                + "        {"
                + "            \"type\": \"collaboration_whitelist_entry\",\n"
                + "            \"id\": \"567889\",\n"
                + "            \"domain\": \"test2.com\",\n"
                + "            \"direction\": \"inbound\"\n"
                + "        },"
                + "        {"
                + "            \"type\": \"collaboration_whitelist_entry\",\n"
                + "            \"id\": \"554354\",\n"
                + "            \"domain\": \"test3.com\",\n"
                + "            \"direction\": \"both\"\n"
                + "        },"
                + "        {"
                + "            \"type\": \"collaboration_whitelist_entry\",\n"
                + "            \"id\": \"543234\",\n"
                + "            \"domain\": \"test4.com\",\n"
                + "            \"direction\": \"both\"\n"
                + "        },"
                + "        {"
                + "            \"type\": \"collaboration_whitelist_entry\",\n"
                + "            \"id\": \"554354\",\n"
                + "            \"domain\": \"test5.com\",\n"
                + "            \"direction\": \"both\"\n"
                + "        },"
                + "        {"
                + "            \"type\": \"collaboration_whitelist_entry\",\n"
                + "            \"id\": \"546654\",\n"
                + "            \"domain\": \"test6.com\",\n"
                + "            \"direction\": \"both\"\n"
                + "        },"
                + "        {"
                + "            \"type\": \"collaboration_whitelist_entry\",\n"
                + "            \"id\": \"557454\",\n"
                + "            \"domain\": \"test7.com\",\n"
                + "            \"direction\": \"both\"\n"
                + "        }"
                + "    ],"
                + "    \"limit\": \"100\""
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        Iterator<BoxCollaborationWhitelist.Info> whitelists = BoxCollaborationWhitelist.getAll(api).iterator();

        BoxCollaborationWhitelist.Info entry = whitelists.next();
        Assert.assertEquals(firstEntryType, entry.getType());
        Assert.assertEquals(firstEntryID, entry.getID());
    }

    @Test
    @Category(IntegrationTest.class)
    public void createCollaborationWhitelistSucceeds() {
        final String type = "collaboration_whitelist_entry";
        final String domainName = "test14.com";

        BoxAPIConnection api = new BoxAPIConnection("");
        BoxCollaborationWhitelist.Info domainWhitelist =
                BoxCollaborationWhitelist.create(api, domainName,
                        BoxCollaborationWhitelist.WhitelistDirection.BOTH);

        assertThat(domainWhitelist, is(notNullValue()));
        assertEquals(domainWhitelist.getDirection(), BoxCollaborationWhitelist.WhitelistDirection.BOTH);
        assertEquals(domainWhitelist.getType(),  type);
    }

    @Test
    @Category(IntegrationTest.class)
    public void getCollaborationWhitelistInfoSucceeds() {
        final String whitelistID = "34290";

        BoxAPIConnection api = new BoxAPIConnection("");
        BoxCollaborationWhitelist collaborationWhitelist = new BoxCollaborationWhitelist(api, whitelistID);
        BoxCollaborationWhitelist.Info whitelistInfo = collaborationWhitelist.getInfo();

        assertThat(whitelistInfo, is(notNullValue()));
        assertEquals(whitelistInfo.getID(), whitelistID);
    }

    @Test
    @Category(IntegrationTest.class)
    public void deleteCollaborationWhitelistSucceeds() {
        final String whitelistID = "34290";

        BoxAPIConnection api = new BoxAPIConnection("");
        BoxCollaborationWhitelist collaborationWhitelist = new BoxCollaborationWhitelist(api, whitelistID);
        collaborationWhitelist.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void getAllCollaborationWhitelistsSucceeds() {
        final String whitelistType = "collaboration_whitelist_entry";

        BoxAPIConnection api = new BoxAPIConnection("");
        Iterable<BoxCollaborationWhitelist.Info> whitelists = BoxCollaborationWhitelist.getAll(api);
        List<BoxCollaborationWhitelist.Info> whitelistList = Lists.newArrayList(whitelists);

        for (BoxCollaborationWhitelist.Info whitelistInfo : whitelistList) {
            assertThat(whitelistInfo, is(notNullValue()));
            assertEquals(whitelistInfo.getType(), whitelistType);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void getAllCollaborationWhitelistsAdditionalParamsSucceeds() {
        final String whitelistType = "collaboration_whitelist_entry";
        final int whitelistSize = 3;

        BoxAPIConnection api = new BoxAPIConnection("");
        Iterator<BoxCollaborationWhitelist.Info> iterator = BoxCollaborationWhitelist.getAll(api, whitelistSize,
                null).iterator();
        iterator.hasNext();
    }
}
