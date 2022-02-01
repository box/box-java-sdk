package com.box.sdk;

import static java.util.stream.Collectors.toList;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents Metadata Query.
 */
public class MetadataQuery {
    static final String FROM = "from";
    static final String LIMIT = "limit";
    static final String QUERY = "query";
    static final String ANCESTOR_FOLDER_ID = "ancestor_folder_id";
    static final String MARKER = "marker";
    static final String ORDER_BY = "order_by";
    static final String FIELDS = "fields";
    static final String QUERY_PARAMS = "query_params";
    private final String from;
    private final int limit;
    private String query;
    private JsonObject queryParameters = new JsonObject();
    private String ancestorFolderId = "0";
    private List<OrderBy> orderBy = new ArrayList<>();
    private String marker;
    private List<String> fields = new ArrayList<>();

    /**
     * Creates Metadata Query
     *
     * @param from  The template used in the query. Must be in the form scope.templateKey
     * @param limit Max results to return for a single request (0-100 inclusive)
     */
    public MetadataQuery(String from, int limit) {
        this.from = from;
        this.limit = limit;
    }

    /**
     * Creates Metadata Query
     *
     * @param from The template used in the query. Must be in the form scope.templateKey
     */
    public MetadataQuery(String from) {
        this(from, 100);
    }

    /**
     * The logical expression of the query
     *
     * @param query Query string
     * @return Returns current MetadataQuery object
     */
    public MetadataQuery setQuery(String query) {
        this.query = query;
        return this;
    }

    /**
     * Sets the folder_id to which to restrain the query.
     * If not set query starts at root level.
     *
     * @param ancestorFolderId The folder id
     * @return Returns current MetadataQuery object
     */
    public MetadataQuery setAncestorFolderId(String ancestorFolderId) {
        this.ancestorFolderId = ancestorFolderId;
        return this;
    }

    /**
     * The marker to use for requesting the next page
     *
     * @param marker Marker string.
     * @return Returns current MetadataQuery object
     */
    public MetadataQuery setMarker(String marker) {
        this.marker = marker;
        return this;
    }

    /**
     * The field_key(s) to order on and the corresponding direction(s)
     *
     * @param fields Fields with sort order
     * @return Returns current MetadataQuery object
     */
    public MetadataQuery setOrderBy(OrderBy... fields) {
        this.orderBy = new ArrayList<>();
        this.orderBy.addAll(Arrays.asList(fields));
        return this;
    }

    MetadataQuery setOrderBy(JsonArray orderBy) {
        if (orderBy != null) {
            this.orderBy = orderBy.values().stream().map(OrderBy::fromJson).collect(toList());
        }
        return this;
    }

    /**
     * The fields to retrieve.
     *
     * @param fields Field names
     * @return Returns current MetadataQuery object
     */
    public MetadataQuery setFields(String... fields) {
        this.fields = new ArrayList<>();
        this.fields.addAll(Arrays.asList(fields));
        return this;
    }

    /**
     * Adds parameter to query
     *
     * @param name  Parameter name
     * @param value Parameter value
     * @return Returns current MetadataQuery object
     */
    public MetadataQuery addParameter(String name, String value) {
        this.queryParameters.add(name, value);
        return this;
    }

    /**
     * Adds parameter to query
     *
     * @param name  Parameter name
     * @param value Parameter value
     * @return Returns current MetadataQuery object
     */
    public MetadataQuery addParameter(String name, int value) {
        this.queryParameters.add(name, value);
        return this;
    }

    /**
     * Adds parameter to query
     *
     * @param name  Parameter name
     * @param value Parameter value
     * @return Returns current MetadataQuery object
     */
    public MetadataQuery addParameter(String name, boolean value) {
        this.queryParameters.add(name, value);
        return this;
    }

    /**
     * Adds parameter to query
     *
     * @param name  Parameter name
     * @param value Parameter value
     * @return Returns current MetadataQuery object
     */
    public MetadataQuery addParameter(String name, float value) {
        this.queryParameters.add(name, value);
        return this;
    }

    /**
     * Adds parameter to query
     *
     * @param name  Parameter name
     * @param value Parameter value
     * @return Returns current MetadataQuery object
     */
    public MetadataQuery addParameter(String name, long value) {
        this.queryParameters.add(name, value);
        return this;
    }

    /**
     * Adds parameter to query
     *
     * @param name  Parameter name
     * @param value Parameter value
     * @return Returns current MetadataQuery object
     */
    public MetadataQuery addParameter(String name, double value) {
        this.queryParameters.add(name, value);
        return this;
    }

    /**
     * Adds parameter to query
     *
     * @param name  Parameter name
     * @param value Parameter value
     * @return Returns current MetadataQuery object
     */
    public MetadataQuery addParameter(String name, JsonValue value) {
        this.queryParameters.add(name, Json.parse(value.toString()));
        return this;
    }

    MetadataQuery setQueryParams(JsonObject queryParameters) {
        this.queryParameters = new JsonObject(queryParameters);
        return this;
    }

    JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject()
            .add(FROM, from)
            .add(LIMIT, limit);
        if (query != null) {
            jsonObject.add(QUERY, query);
        }
        if (ancestorFolderId != null) {
            jsonObject.add(ANCESTOR_FOLDER_ID, ancestorFolderId);
        }
        if (marker != null) {
            jsonObject.add(MARKER, marker);
        }
        if (!orderBy.isEmpty()) {
            JsonArray orderByJson = new JsonArray();
            orderBy.stream().map(OrderBy::toJsonObject).forEach(orderByJson::add);
            jsonObject.add(ORDER_BY, orderByJson);
        }
        if (!fields.isEmpty()) {
            JsonArray fieldsJson = new JsonArray();
            fields.forEach(fieldsJson::add);
            jsonObject.add(FIELDS, fieldsJson);
        }
        if (queryParameters.iterator().hasNext()) {
            jsonObject.add(QUERY_PARAMS, new JsonObject(queryParameters));
        }
        return jsonObject;
    }

    int getLimit() {
        return limit;
    }

    String getMarker() {
        return marker;
    }

    public static final class OrderBy {

        static final String FIELD_KEY = "field_key";
        static final String DIRECTION = "direction";
        static final String DIRECTION_ASCENDING = "asc";
        static final String DIRECTION_DESCENDING = "desc";
        private final String fieldName;
        private final String direction;

        private OrderBy(String fieldName, String direction) {
            this.fieldName = fieldName;
            this.direction = direction;
        }

        JsonObject toJsonObject() {
            return new JsonObject().add(FIELD_KEY, fieldName).add(DIRECTION, direction);
        }

        /**
         * Creates OrderBy for ascending sort with a specified field.
         * @param fieldName Name of a field
         * @return OrderBy instance
         */
        public static OrderBy ascending(String fieldName) {
            return new OrderBy(fieldName, DIRECTION_ASCENDING);
        }

        /**
         * Creates OrderBy for descending sort with a specified field.
         * @param fieldName Name of a field
         * @return OrderBy instance
         */
        public static OrderBy descending(String fieldName) {
            return new OrderBy(fieldName, DIRECTION_DESCENDING);
        }

        static OrderBy fromJson(JsonValue jsonValue) {
            if (jsonValue.isObject()) {
                JsonObject object = jsonValue.asObject();
                String fieldName = object.get(FIELD_KEY).asString();
                String direction = object.get(DIRECTION).asString().toLowerCase();
                if (!DIRECTION_ASCENDING.equals(direction) && !DIRECTION_DESCENDING.equals(direction)) {
                    throw new RuntimeException(
                        String.format("Unsupported sort direction [%s] for field [%s]", direction, fieldName)
                    );
                }
                return object.getString(DIRECTION, "").equals(DIRECTION_ASCENDING)
                    ? ascending(fieldName)
                    : descending(fieldName);
            }
            throw new RuntimeException("Unsupported json " + jsonValue);
        }
    }
}
