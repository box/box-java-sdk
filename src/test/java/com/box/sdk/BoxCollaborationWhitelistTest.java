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

public class BoxCollaborationWhitelistTest {
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
        final int whitelistSize = 10;

        BoxAPIConnection api = new BoxAPIConnection("tGQ7LAXilruFEhhYbbTRDGmSVWqVTPdN");
        Iterable<BoxCollaborationWhitelist.Info> whitelists = BoxCollaborationWhitelist.getAll(api, whitelistSize, null);
        List<BoxCollaborationWhitelist.Info> whitelistList = Lists.newArrayList(whitelists);

        assertEquals(whitelistList.size(), whitelistSize);

        for (BoxCollaborationWhitelist.Info whitelistInfo : whitelistList) {
            assertThat(whitelistInfo, is(notNullValue()));
            assertEquals(whitelistInfo.getType(), whitelistType);
        }
    }
}
