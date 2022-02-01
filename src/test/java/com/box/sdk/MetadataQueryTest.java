package com.box.sdk;

import static com.box.sdk.MetadataQuery.ANCESTOR_FOLDER_ID;
import static com.box.sdk.MetadataQuery.FIELDS;
import static com.box.sdk.MetadataQuery.FROM;
import static com.box.sdk.MetadataQuery.LIMIT;
import static com.box.sdk.MetadataQuery.MARKER;
import static com.box.sdk.MetadataQuery.ORDER_BY;
import static com.box.sdk.MetadataQuery.OrderBy.ascending;
import static com.box.sdk.MetadataQuery.OrderBy.descending;
import static com.box.sdk.MetadataQuery.QUERY;
import static com.box.sdk.MetadataQuery.QUERY_PARAMS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import org.junit.Test;

public class MetadataQueryTest {

    @Test
    public void createsMetadataQueryWithRequiredParameters() {
        MetadataQuery query = new MetadataQuery("from somewhere", 100);

        JsonObject json = query.toJsonObject();
        assertThat(json.getString(FROM, ""), is("from somewhere"));
        assertThat(json.getInt(LIMIT, 0), is(100));
        assertThat(json.get(QUERY), nullValue());
        assertThat(json.get(ANCESTOR_FOLDER_ID), is("0"));
        assertThat(json.get(MARKER), nullValue());
        assertThat(json.get(ORDER_BY), nullValue());
        assertThat(json.get(FIELDS), nullValue());
        assertThat(json.get(QUERY_PARAMS), nullValue());
    }

    @Test
    public void createsMetadataQuery() {
        MetadataQuery query = new MetadataQuery("from somewhere", 100)
            .setQuery("some query")
            .setAncestorFolderId("23456789")
            .setMarker("some marker")
            .setOrderBy(ascending("field1"), descending("field2"))
            .setFields("field3", "field4");

        JsonObject json = query.toJsonObject();
        assertThat(json.getString(FROM, ""), is("from somewhere"));
        assertThat(json.getInt(LIMIT, 0), is(100));
        assertThat(json.getString(QUERY, ""), is("some query"));
        assertThat(json.getString(ANCESTOR_FOLDER_ID, ""), is("23456789"));
        assertThat(json.getString(MARKER, ""), is("some marker"));
        assertThat(json.get(ORDER_BY).asArray(),
            is(new JsonArray()
                .add(new JsonObject().add("field_key", "field1").add("direction", "asc"))
                .add(new JsonObject().add("field_key", "field2").add("direction", "desc")))
        );
        assertThat(json.get(FIELDS).asArray(), is(new JsonArray().add("field3").add("field4")));
    }

    @Test
    public void canAddParametersToQuery() {
        MetadataQuery query = new MetadataQuery("from somewhere", 100)
            .addParameter("stringParam", "some string")
            .addParameter("intParam", 10)
            .addParameter("boolParam", true)
            .addParameter("floatParam", 100F)
            .addParameter("longParam", 200L)
            .addParameter("doubleParam", 300d)
            .addParameter("doubleParam", 300d)
            .addParameter("jsonParam", new JsonObject().add("json", "value"));

        JsonObject params = query.toJsonObject().get(QUERY_PARAMS).asObject();
        assertThat(params.getString("stringParam", ""), is("some string"));
        assertThat(params.getInt("intParam", 0), is(10));
        assertThat(params.getBoolean("boolParam", false), is(true));
        assertThat(params.getFloat("floatParam", 0), is(100F));
        assertThat(params.getLong("longParam", 0), is(200L));
        assertThat(params.getDouble("doubleParam", 0), is(300d));
        assertThat(params.get("jsonParam"), is(new JsonObject().add("json", "value")));
    }

    @Test
    public void canSetParametersOnQuery() {
        MetadataQuery query = new MetadataQuery("from somewhere", 100)
            .setQueryParams(
                new JsonObject().add("stringParam", "some value")
            );

        JsonObject params = query.toJsonObject().get(QUERY_PARAMS).asObject();
        assertThat(params.getString("stringParam", ""), is("some value"));
    }

    @Test
    public void canSetOrderByAsJsonArray() {
        MetadataQuery query = new MetadataQuery("from somewhere", 100)
            .setOrderBy(new JsonArray().add(
                new JsonObject().add("field_key", "field").add("direction", "desc")
            ));

        String params = query.toJsonObject().get(ORDER_BY).toString();
        assertThat(params, is("[{\"field_key\":\"field\",\"direction\":\"desc\"}]"));
    }
}
