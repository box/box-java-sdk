package com.box.sdk;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.eclipsesource.json.JsonObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BoxCollaborationWhitelistExemptTargetTest {
    @Test
    @Category(UnitTest.class)
    public void testGetAllWhitelistingForUserWithParamsParseAllFieldsCorrectly() {
        final String firstEntryType = "collaboration_whitelist_exempt_target";
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
                + "            \"type\": \"collaboration_whitelist_exempt_target\",\n"
                + "            \"id\": \"558459\",\n"
                + "            \"user\": {"
                + "                \"type\": \"user\","
                + "                \"id\": \"275111333\","
                + "                \"name\": \"Cary Cheng\","
                + "                \"login\": \"AppUser_221486_CSrKz0XXbv@boxdevedition.com\""
                + "            }"
                + "        },"
                + "        {"
                + "            \"type\": \"collaboration_whitelist_exempt_target\",\n"
                + "            \"id\": \"558949\",\n"
                + "            \"user\": {"
                + "                \"type\": \"user\","
                + "                \"id\": \"275111657\","
                + "                \"name\": \"CaryCheng1 Cheng\","
                + "                \"login\": \"AppUser_221486_7MBLdS6CX4@boxdevedition.com\""
                + "            }"
                + "        },"
                + "        {"
                + "            \"type\": \"collaboration_whitelist_exempt_target\",\n"
                + "            \"id\": \"573205\",\n"
                + "            \"user\": {"
                + "                \"type\": \"user\","
                + "                \"id\": \"275393546\","
                + "                \"name\": \"CaryCheng11 Cheng\","
                + "                \"login\": \"AppUser_221486_urNCkSWN9P@boxdevedition.com\""
                + "            }"
                + "        },"
                + "        {"
                + "            \"type\": \"collaboration_whitelist_exempt_target\",\n"
                + "            \"id\": \"573619\",\n"
                + "            \"user\": {"
                + "                \"type\": \"user\","
                + "                \"id\": \"275393890\","
                + "                \"name\": \"CaryCheng12 Cheng\","
                + "                \"login\": \"AppUser_221486_64wO0CADQc@boxdevedition.com\""
                + "            }"
                + "        }"
                + "    ],"
                + "    \"next_marker\": \"eyJ0eXBlIjoiaWQiLCJkaXIiOiJuZXh0IiwidGFpbCI6IjU1ODIyMyJ9\","
                + "    \"limit\": \"4\""
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        Iterator<BoxCollaborationWhitelistExemptTarget.Info> whitelists =
                BoxCollaborationWhitelistExemptTarget.getAll(api, collaborationWhitelistDefaultLimit,
                        paramCollaborationWhitelistNextMarker).iterator();

        BoxCollaborationWhitelistExemptTarget.Info entry = whitelists.next();
        Assert.assertEquals(firstEntryType, entry.getType());
        Assert.assertEquals(firstEntryID, entry.getID());
        Assert.assertEquals(firstEntryUserID, entry.getUser().getID());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllWhitelistingForUserParseAllFieldsCorrectly() {
        final String firstEntryType = "collaboration_whitelist_exempt_target";
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
                + "            \"type\": \"collaboration_whitelist_exempt_target\",\n"
                + "            \"id\": \"558459\",\n"
                + "            \"user\": {"
                + "                \"type\": \"user\","
                + "                \"id\": \"275111333\","
                + "                \"name\": \"Cary Cheng\","
                + "                \"login\": \"AppUser_221486_CSrKz0XXbv@boxdevedition.com\""
                + "            }"
                + "        },"
                + "        {"
                + "            \"type\": \"collaboration_whitelist_exempt_target\",\n"
                + "            \"id\": \"558949\",\n"
                + "            \"user\": {"
                + "                \"type\": \"user\","
                + "                \"id\": \"275111657\","
                + "                \"name\": \"CaryCheng1 Cheng\","
                + "                \"login\": \"AppUser_221486_7MBLdS6CX4@boxdevedition.com\""
                + "            }"
                + "        },"
                + "        {"
                + "            \"type\": \"collaboration_whitelist_exempt_target\",\n"
                + "            \"id\": \"573205\",\n"
                + "            \"user\": {"
                + "                \"type\": \"user\","
                + "                \"id\": \"275393546\","
                + "                \"name\": \"CaryCheng11 Cheng\","
                + "                \"login\": \"AppUser_221486_urNCkSWN9P@boxdevedition.com\""
                + "            }"
                + "        },"
                + "        {"
                + "            \"type\": \"collaboration_whitelist_exempt_target\",\n"
                + "            \"id\": \"573619\",\n"
                + "            \"user\": {"
                + "                \"type\": \"user\","
                + "                \"id\": \"275393890\","
                + "                \"name\": \"CaryCheng12 Cheng\","
                + "                \"login\": \"AppUser_221486_64wO0CADQc@boxdevedition.com\""
                + "            }"
                + "        }"
                + "    ],"
                + "    \"limit\": \"100\""
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        Iterator<BoxCollaborationWhitelistExemptTarget.Info> whitelists =
                BoxCollaborationWhitelistExemptTarget.getAll(api, collaborationWhitelistDefaultLimit,
                        paramCollaborationWhitelistNextMarker).iterator();

        BoxCollaborationWhitelistExemptTarget.Info entry = whitelists.next();
        Assert.assertEquals(firstEntryType, entry.getType());
        Assert.assertEquals(firstEntryID, entry.getID());
        Assert.assertEquals(firstEntryUserID, entry.getUser().getID());
    }

    @Test
    @Category(IntegrationTest.class)
    public void createCollaborationWhitelistForUserSucceeds() {
        final String whitelistType = "collaboration_whitelist_exempt_target";
        final String userID = "275393890";

        BoxAPIConnection api = new BoxAPIConnection("");
        BoxCollaborationWhitelistExemptTarget.Info userWhitelist =
                BoxCollaborationWhitelistExemptTarget.create(api, userID);

        assertThat(userWhitelist, is(notNullValue()));
        assertEquals(userWhitelist.getType(), whitelistType);
    }

    @Test
    @Category(IntegrationTest.class)
    public void getCollaborationWhitelistInfoForUserSucceeds() {
        final String userWhitelistID = "573619";
        final String whitelistType = "collaboration_whitelist_exempt_target";

        BoxAPIConnection api = new BoxAPIConnection("");
        BoxCollaborationWhitelistExemptTarget userCollaborationWhitelist =
                new BoxCollaborationWhitelistExemptTarget(api, userWhitelistID);
        BoxCollaborationWhitelistExemptTarget.Info userWhitelistInfo = userCollaborationWhitelist.getInfo();

        assertThat(userWhitelistInfo, is(notNullValue()));
        assertEquals(userWhitelistInfo.getType(), whitelistType);
        assertEquals(userWhitelistInfo.getID(), userWhitelistID);
    }

    @Test
    @Category(IntegrationTest.class)
    public void deleteCollaborationWhitelistForUserSucceeds() {
        final String whitelistID = "573619";

        BoxAPIConnection api = new BoxAPIConnection("");
        BoxCollaborationWhitelistExemptTarget userCollaborationWhitelist =
                new BoxCollaborationWhitelistExemptTarget(api, whitelistID);
        userCollaborationWhitelist.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void getAllCollaborationWhitelistForUserSucceeds() {
        final String whitelistType = "collaboration_whitelist_exempt_target";

        BoxAPIConnection api = new BoxAPIConnection("");
        Iterator<BoxCollaborationWhitelistExemptTarget.Info> iterator =
                BoxCollaborationWhitelistExemptTarget.getAll(api).iterator();

        BoxCollaborationWhitelistExemptTarget.Info info = iterator.next();
        assertEquals(whitelistType, info.getType());
    }

    @Test
    @Category(IntegrationTest.class)
    public void getAllCollaborationWhitelistForUserWithParamsSucceeds() {
        final int whitelistLimit = 3;

        BoxAPIConnection api = new BoxAPIConnection("st1z6jiRKezjoPEQhL1hcdjxGiu05KMq");
        Iterator<BoxCollaborationWhitelistExemptTarget.Info> iterator =
                BoxCollaborationWhitelistExemptTarget.getAll(api, whitelistLimit, null).iterator();

        iterator.hasNext();
    }
}
