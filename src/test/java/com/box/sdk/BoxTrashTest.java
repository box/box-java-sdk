package com.box.sdk;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BoxTrashTest {
    @Test
    @Category(IntegrationTest.class)
    public void getAllTrashedItems() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTrash trash = new BoxTrash(api);
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder trashedFolder = rootFolder.createFolder("[getAllTrashedItems] Trashed Folder");
        trashedFolder.delete(false);

        assertThat(trash, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(trashedFolder.getID()))));
    }
}
