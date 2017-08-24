package com.box.sdk;

import java.net.URL;
import java.util.Iterator;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Collections contain information about the items contained inside of them, including files and folders. The only
 * collection available currently is a “Favorites” collection.
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
@BoxResourceType("collection")
public class BoxCollection extends BoxResource implements Iterable<BoxItem.Info> {

    /**
     * Get Collections URL Template.
     */
    public static final URLTemplate GET_COLLECTIONS_URL_TEMPLATE = new URLTemplate("collections/");
    /**
     * Get Collection Items URL Template.
     */
    public static final URLTemplate GET_COLLECTION_ITEMS_URL = new URLTemplate("collections/%s/items/");

    /**
     * Constructs a BoxCollection for a collection with a given ID.
     * @param   api the API connection to be used by the collection.
     * @param   id  the ID of the collection.
     */
    public BoxCollection(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Gets an iterable of all the collections for the given user.
     * @param  api the API connection to be used when retrieving the collections.
     * @return     an iterable containing info about all the collections.
     */
    public static Iterable<BoxCollection.Info> getAllCollections(final BoxAPIConnection api) {
        return new Iterable<BoxCollection.Info>() {
            public Iterator<BoxCollection.Info> iterator() {
                URL url = GET_COLLECTIONS_URL_TEMPLATE.build(api.getBaseURL());
                return new BoxCollectionIterator(api, url);
            }
        };
    }

    /**
     * Returns an iterable containing the items in this collection. Iterating over the iterable returned by this method
     * is equivalent to iterating over this BoxCollection directly.
     * @return an iterable containing the items in this collection.
     */
    public Iterable<BoxItem.Info> getItems() {
        return this;
    }

    /**
     * Returns an iterable containing the items in this collection and specifies which attributes to include in the
     * response.
     * @param   fields  the fields to retrieve.
     * @return          an iterable containing the items in this collection.
     */
    public Iterable<BoxItem.Info> getItems(final String... fields) {
        return new Iterable<BoxItem.Info>() {
            @Override
            public Iterator<BoxItem.Info> iterator() {
                String queryString = new QueryStringBuilder().appendParam("fields", fields).toString();
                URL url = GET_COLLECTION_ITEMS_URL.buildWithQuery(getAPI().getBaseURL(), queryString, getID());
                return new BoxItemIterator(getAPI(), url);
            }
        };
    }

    /**
     * Retrieves a specific range of items in this collection.
     * @param   offset  the index of the first item to retrieve.
     * @param   limit   the maximum number of items to retrieve after the offset.
     * @param   fields  the fields to retrieve.
     * @return          a partial collection containing the specified range of items.
     */
    public PartialCollection<BoxItem.Info> getItemsRange(long offset, long limit, String... fields) {
        QueryStringBuilder builder = new QueryStringBuilder()
                .appendParam("offset", offset)
                .appendParam("limit", limit);

        if (fields.length > 0) {
            builder.appendParam("fields", fields).toString();
        }

        URL url = GET_COLLECTION_ITEMS_URL.buildWithQuery(getAPI().getBaseURL(), builder.toString(), getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        String totalCountString = responseJSON.get("total_count").toString();
        long fullSize = Double.valueOf(totalCountString).longValue();
        PartialCollection<BoxItem.Info> items = new PartialCollection<BoxItem.Info>(offset, limit, fullSize);
        JsonArray entries = responseJSON.get("entries").asArray();
        for (JsonValue entry : entries) {
            BoxItem.Info entryInfo = (BoxItem.Info) BoxResource.parseInfo(this.getAPI(), entry.asObject());
            if (entryInfo != null) {
                items.add(entryInfo);
            }
        }
        return items;
    }

    /**
     * Returns an iterator over the items in this collection.
     * @return an iterator over the items in this collection.
     */
    @Override
    public Iterator<BoxItem.Info> iterator() {
        URL url = GET_COLLECTION_ITEMS_URL.build(this.getAPI().getBaseURL(), BoxCollection.this.getID());
        return new BoxItemIterator(BoxCollection.this.getAPI(), url);
    }

    /**
     * Contains information about a BoxCollection.
     */
    public class Info extends BoxResource.Info {
        private String collectionType;
        private String name;

        /**
         * Constructs an empty Info object.
         */
        public Info() {
            super();
        }

        /**
         * Constructs an Info object by parsing information from a JSON string.
         * @param json the JSON string to parse.
         */
        public Info(String json) {
            super(json);
        }

        /**
         * Constructs an Info object using an already parsed JSON object.
         * @param jsonObject the parsed JSON object.
         */
        Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        /**
         * Gets the type of the collection.
         * @return the type of the collection.
         */
        public String getCollectionType() {
            return this.collectionType;
        }

        /**
         * Gets the name of the collection.
         * @return the name of the collection.
         */
        public String getName() {
            return this.name;
        }

        @Override
        public BoxCollection getResource() {
            return BoxCollection.this;
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            String memberName = member.getName();
            JsonValue value = member.getValue();
            if (memberName.equals("collection_type")) {
                this.collectionType = value.asString();
            } else if (memberName.equals("name")) {
                this.name = value.asString();
            }
        }
    }
}
