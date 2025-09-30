package com.box.sdkgen.sharedlinksappitems;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.sharedlinksappitems.FindAppItemForSharedLinkHeaders;
import com.box.sdkgen.schemas.appitem.AppItem;
import org.junit.jupiter.api.Test;

public class SharedLinksAppItemsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testSharedLinksAppItems() {
    String appItemSharedLink = getEnvVar("APP_ITEM_SHARED_LINK");
    AppItem appItem =
        client
            .getSharedLinksAppItems()
            .findAppItemForSharedLink(
                new FindAppItemForSharedLinkHeaders(
                    String.join("", "shared_link=", appItemSharedLink)));
    assert convertToString(appItem.getType()).equals("app_item");
    assert appItem.getApplicationType().equals("hubs");
  }
}
