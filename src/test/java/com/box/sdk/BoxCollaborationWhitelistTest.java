package com.box.sdk;

import java.util.Arrays;
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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

public class BoxCollaborationWhitelistTest {
    @RunWith(Parameterized.class)
    public static class EnumValueChecker {
        private String inputString;
        private BoxCollaborationWhitelist.WhitelistDirection expectedResult;

        public EnumValueChecker(String inputString, BoxCollaborationWhitelist.WhitelistDirection expectedResult) {
            this.inputString = inputString;
            this.expectedResult = expectedResult;
        }

        @Parameterized.Parameters
        public static List<Object[]> enumValues() {
            return Arrays.asList(new Object[][] {
                    {"inbound", BoxCollaborationWhitelist.WhitelistDirection.INBOUND},
                    {"outbound", BoxCollaborationWhitelist.WhitelistDirection.OUTBOUND},
                    {"both", BoxCollaborationWhitelist.WhitelistDirection.BOTH}
            });
        }

        @Test
        public void whitelistDirectionTest() {
            assertEquals(this.expectedResult,
                    BoxCollaborationWhitelist.WhitelistDirection.fromDirection(this.inputString));
        }
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllWhitelistingForUserWithParamsParseAllFieldsCorrectly() {
        final String firstEntryType = "collaboration_whitelist_entry";
        final String firstEntryID = "558459";
        final int collaborationWhitelistLimit = 4;

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
                BoxCollaborationWhitelist.getAll(api, collaborationWhitelistLimit).iterator();

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

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxCollaborationWhitelist collaborationWhitelist = new BoxCollaborationWhitelist(api, whitelistID);
        BoxCollaborationWhitelist.Info whitelistInfo = collaborationWhitelist.getInfo();

        assertThat(whitelistInfo, is(notNullValue()));
        assertEquals(whitelistInfo.getID(), whitelistID);
    }

    @Test
    @Category(IntegrationTest.class)
    public void deleteCollaborationWhitelistSucceeds() {
        final String whitelistID = "34290";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxCollaborationWhitelist collaborationWhitelist = new BoxCollaborationWhitelist(api, whitelistID);
        collaborationWhitelist.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void getAllCollaborationWhitelistsSucceeds() {
        final String whitelistType = "collaboration_whitelist_entry";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
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
        final int whitelistSize = 3;

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        Iterator<BoxCollaborationWhitelist.Info> iterator =
                BoxCollaborationWhitelist.getAll(api, whitelistSize).iterator();
        iterator.hasNext();
    }
}
