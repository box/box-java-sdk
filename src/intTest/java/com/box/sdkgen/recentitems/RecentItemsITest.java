package com.box.sdkgen.recentitems;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClientWithUserSubject;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.schemas.recentitems.RecentItems;
import org.junit.jupiter.api.Test;

public class RecentItemsITest {

  @Test
  public void testRecentItems() {
    BoxClient client = getDefaultClientWithUserSubject(getEnvVar("USER_ID"));
    RecentItems recentItems = client.getRecentItems().getRecentItems();
    assert recentItems.getEntries().size() >= 0;
  }
}
