package com.box.sdkgen.weblinks;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.weblinks.CreateWebLinkRequestBody;
import com.box.sdkgen.managers.weblinks.CreateWebLinkRequestBodyParentField;
import com.box.sdkgen.managers.weblinks.UpdateWebLinkByIdRequestBody;
import com.box.sdkgen.managers.weblinks.UpdateWebLinkByIdRequestBodySharedLinkAccessField;
import com.box.sdkgen.managers.weblinks.UpdateWebLinkByIdRequestBodySharedLinkField;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.weblink.WebLink;
import org.junit.jupiter.api.Test;

public class WeblinksITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testCreateGetDeleteWeblink() {
    String url = "https://www.box.com";
    FolderFull parent = client.getFolders().getFolderById("0");
    String name = getUuid();
    String description = "Weblink description";
    String password = "super-secret-password";
    WebLink weblink =
        client
            .getWebLinks()
            .createWebLink(
                new CreateWebLinkRequestBody.Builder(
                        url, new CreateWebLinkRequestBodyParentField(parent.getId()))
                    .name(name)
                    .description(description)
                    .build());
    assert weblink.getUrl().equals(url);
    assert weblink.getParent().getId().equals(parent.getId());
    assert weblink.getName().equals(name);
    assert weblink.getDescription().equals(description);
    WebLink weblinkById = client.getWebLinks().getWebLinkById(weblink.getId());
    assert weblinkById.getId().equals(weblink.getId());
    assert weblinkById.getUrl().equals(url);
    String updatedName = getUuid();
    WebLink updatedWeblink =
        client
            .getWebLinks()
            .updateWebLinkById(
                weblink.getId(),
                new UpdateWebLinkByIdRequestBody.Builder()
                    .name(updatedName)
                    .sharedLink(
                        new UpdateWebLinkByIdRequestBodySharedLinkField.Builder()
                            .access(UpdateWebLinkByIdRequestBodySharedLinkAccessField.OPEN)
                            .password(password)
                            .build())
                    .build());
    assert updatedWeblink.getName().equals(updatedName);
    assert convertToString(updatedWeblink.getSharedLink().getAccess()).equals("open");
    client.getWebLinks().deleteWebLinkById(weblink.getId());
    WebLink deletedWeblink = client.getWebLinks().getWebLinkById(weblink.getId());
    assert convertToString(deletedWeblink.getItemStatus()).equals("trashed");
  }
}
