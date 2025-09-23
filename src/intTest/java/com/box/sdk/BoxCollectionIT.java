package com.box.sdk;

import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import org.junit.Test;

public class BoxCollectionIT {

  @Test
  public void getAllCollectionsReturnsFavorites() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    ArrayList<BoxCollection.Info> collectionList = new ArrayList<>();
    for (BoxCollection.Info info : BoxCollection.getAllCollections(api)) {
      collectionList.add(info);
    }

    assertThat(collectionList.size(), is(equalTo(1)));
    BoxCollection.Info firstCollection = collectionList.get(0);
    assertThat(firstCollection.getName(), is(equalTo("Favorites")));
    assertThat(firstCollection.getCollectionType(), is(equalTo("favorites")));
  }
}
