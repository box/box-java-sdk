package com.box.sdk;

import com.eclipsesource.json.JsonObject;

/**
 * Utility class to retrieve list of recent items.
 * @see <a href="http://google.com">https://developer.box.com/reference#get-recent-items</a>
 */
public final class BoxRecents {

    /**
     * Recents URL Template.
     */
    public static final URLTemplate RECENTS_URL_TEMPLATE = new URLTemplate("recent_items");

    //Constructor is not allowed
    private BoxRecents() {
    }

    /**
     * Used to retrieve all collaborations associated with the item.
     *
     * @see <a href="http://google.com">https://developer.box.com/reference#get-recent-items</a>
     *
     * @param api    BoxAPIConnection from the associated file.
     * @param limit  limit of items to be retrieved. Default is 100. Maximum is 1000
     * @param fields the optional fields to retrieve.
     * @return An iterable of BoxCollaboration.Info instances associated with the item.
     */
    public static BoxResourceIterable<BoxRecentItem> getRecentItems(final BoxAPIConnection api,
                                                                    int limit, String... fields) {
        QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        return new BoxResourceIterable<BoxRecentItem>(
                api, RECENTS_URL_TEMPLATE.buildWithQuery(api.getBaseURL(), builder.toString()),
                limit) {

            @Override
            protected BoxRecentItem factory(JsonObject jsonObject) {
                return new BoxRecentItem(jsonObject, api);
            }
        };
    }
}
