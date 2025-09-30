package com.box.sdk;

import static com.box.sdk.PagingParameters.offset;

import com.eclipsesource.json.JsonObject;
import java.net.URL;
import java.util.Iterator;

class BoxCollectionIterator implements Iterator<BoxCollection.Info> {
  private static final long LIMIT = 100;
  private final BoxAPIConnection api;
  private final JsonIterator jsonIterator;

  BoxCollectionIterator(BoxAPIConnection api, URL url) {
    this.api = api;
    this.jsonIterator = new JsonIterator(api, url, offset(0, LIMIT));
  }

  @Override
  public boolean hasNext() {
    return this.jsonIterator.hasNext();
  }

  @Override
  public BoxCollection.Info next() {
    JsonObject nextJSONObject = this.jsonIterator.next();
    String id = nextJSONObject.get("id").asString();

    BoxCollection collection = new BoxCollection(this.api, id);
    return collection.new Info(nextJSONObject);
  }

  public void remove() {
    throw new UnsupportedOperationException();
  }
}
