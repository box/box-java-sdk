package com.box.sdkgen.test.trashedweblinks;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.weblinks.CreateWebLinkRequestBody;
import com.box.sdkgen.managers.weblinks.CreateWebLinkRequestBodyParentField;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.trashweblink.TrashWebLink;
import com.box.sdkgen.schemas.trashweblinkrestored.TrashWebLinkRestored;
import com.box.sdkgen.schemas.weblink.WebLink;
import org.junit.jupiter.api.Test;

public class TrashedWebLinksITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testTrashedWebLinks() {
    String url = "https://www.box.com";
    FolderFull parent = client.getFolders().getFolderById("0");
    String name = getUuid();
    String description = "Weblink description";
    WebLink weblink =
        client
            .getWebLinks()
            .createWebLink(
                new CreateWebLinkRequestBody.Builder(
                        url, new CreateWebLinkRequestBodyParentField(parent.getId()))
                    .name(name)
                    .description(description)
                    .build());
    client.getWebLinks().deleteWebLinkById(weblink.getId());
    TrashWebLink fromTrash = client.getTrashedWebLinks().getTrashedWebLinkById(weblink.getId());
    assert fromTrash.getId().equals(weblink.getId());
    assert fromTrash.getName().equals(weblink.getName());
    WebLink fromApiAfterTrashed = client.getWebLinks().getWebLinkById(weblink.getId());
    assert convertToString(fromApiAfterTrashed.getItemStatus()).equals("trashed");
    TrashWebLinkRestored restoredWeblink =
        client.getTrashedWebLinks().restoreWeblinkFromTrash(weblink.getId());
    WebLink fromApi = client.getWebLinks().getWebLinkById(weblink.getId());
    assert restoredWeblink.getId().equals(fromApi.getId());
    assert restoredWeblink.getName().equals(fromApi.getName());
    client.getWebLinks().deleteWebLinkById(weblink.getId());
    client.getTrashedWebLinks().deleteTrashedWebLinkById(weblink.getId());
    assertThrows(
        RuntimeException.class,
        () -> client.getTrashedWebLinks().getTrashedWebLinkById(weblink.getId()));
  }
}
