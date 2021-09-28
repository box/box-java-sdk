package com.box.sdk;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import com.box.sdk.BoxSharedLink.Permissions;
import com.eclipsesource.json.JsonObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

public class BoxSharedLinkTest {

    @Test
    public void vanityNameHasToBeAtLeast12CharactersLong() {
        final BoxSharedLink link = new BoxSharedLink();
        Assert.assertThrows(
            "The vanityName has to be at least 12 characters long.",
            IllegalArgumentException.class,
            new ThrowingRunnable() {
                @Override
                public void run() {
                    link.setVanityName("tooShort");
                }
            }
        );
    }

    @Test
    public void setsNewPermissions() {
        BoxSharedLink link = new BoxSharedLink();

        link.setPermissions(permissions(true, false));

        assertThat(link.getPermissions(), is(this.permissions(true, false)));
        JsonObject pendingChanges = link.getPendingChangesAsJsonObject();
        JsonObject changesToPermissions = pendingChanges.get("permissions").asObject();
        assertThat(changesToPermissions.get("can_download").asBoolean(), is(true));
        assertThat(changesToPermissions.get("can_preview").asBoolean(), is(false));
    }

    @Test
    public void doNotStorePendingChangeWhenPermissionsNotChanged() {
        BoxSharedLink link = new BoxSharedLink();
        link.setPermissions(permissions(true, false));
        link.removeChildObject("permissions");

        link.setPermissions(this.permissions(true, false));

        assertThat(link.getPermissions(), is(permissions(true, false)));
        assertThat(link.getPendingChangesAsJsonObject(), is(nullValue()));
    }

    @Test
    public void storePendingChangeWhenPermissionsChanged() {
        BoxSharedLink link = new BoxSharedLink();
        link.setPermissions(permissions(true, false));
        link.removeChildObject("permissions");

        link.setPermissions(this.permissions(false, false));

        assertThat(link.getPermissions(), is(permissions(false, false)));
        JsonObject pendingChanges = link.getPendingChangesAsJsonObject();
        JsonObject changesToPermissions = pendingChanges.get("permissions").asObject();
        assertThat(changesToPermissions.get("can_download").asBoolean(), is(false));
        assertThat(changesToPermissions.get("can_preview").asBoolean(), is(false));
    }

    private Permissions permissions(boolean canDownload, boolean canPreview) {
        Permissions permissions = new Permissions();
        permissions.setCanDownload(canDownload);
        permissions.setCanPreview(canPreview);
        return permissions;
    }
}
