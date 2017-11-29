package com.box.sdk;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.internal.matchers.NotNull;

public class BoxCollaborationWhitelistExemptTargetTest {
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
    public void getAllCCollaborationWhitelistForUserSucceeds() {
        final String whitelistType = "collaboration_whitelist_exempt_target";

        BoxAPIConnection api = new BoxAPIConnection("tGQ7LAXilruFEhhYbbTRDGmSVWqVTPdN");
        Iterable<BoxCollaborationWhitelistExemptTarget.Info> whitelists =
                BoxCollaborationWhitelistExemptTarget.getAll(api);
        List<BoxCollaborationWhitelistExemptTarget.Info> whitelistList = Lists.newArrayList(whitelists);

        for (BoxCollaborationWhitelistExemptTarget.Info whitelistInfo : whitelistList) {
            assertThat(whitelistInfo, is(notNullValue()));
            assertEquals(whitelistInfo.getType(), whitelistType);
        }
    }
}
