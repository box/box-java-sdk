package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import java.util.Iterator;

interface JsonIterator extends Iterator<JsonObject> {
    boolean hasNext();

    JsonObject next();

    void remove();

    void setFilter(Filter<JsonObject> filter);
}
