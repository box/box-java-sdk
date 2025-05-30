package com.box.sdkgen.trasheditems;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.commons.CommonsManager.uploadNewFile;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.items.Items;
import org.junit.jupiter.api.Test;

public class TrashedItemsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testListTrashedItems() {
    FileFull file = uploadNewFile();
    client.getFiles().deleteFileById(file.getId());
    Items trashedItems = client.getTrashedItems().getTrashedItems();
    assert trashedItems.getEntries().size() > 0;
  }
}
