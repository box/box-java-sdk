package com.box.sdk;

import java.net.URL;
import java.util.Iterator;

import com.eclipsesource.json.JsonObject;

public class BoxGroup extends BoxCollaborator {
    private static final URLTemplate GROUPS_URL_TEMPLATE = new URLTemplate("groups");
    private static final URLTemplate GROUP_URL_TEMPLATE = new URLTemplate("groups/%s");

    public BoxGroup(BoxAPIConnection api, String id) {
        super(api, id);
    }

    public static BoxGroup.Info createGroup(BoxAPIConnection api, String name) {
        JsonObject requestJSON = new JsonObject();
        requestJSON.add("name", name);

        URL url = GROUPS_URL_TEMPLATE.build(api.getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(api, url, "GET");
        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxGroup group = new BoxGroup(api, responseJSON.get("id").asString());
        return group.new Info(responseJSON);
    }

    public static Iterable<BoxGroup.Info> getAllGroups(final BoxAPIConnection api) {
        return new Iterable<BoxGroup.Info>() {
            public Iterator<BoxGroup.Info> iterator() {
                URL url = GROUPS_URL_TEMPLATE.build(api.getBaseURL());
                return new BoxGroupIterator(api, url);
            }
        };
    }

    public void delete() {
        URL url = GROUP_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    public class Info extends BoxCollaborator.Info {
        /**
         * Constructs an empty Info object.
         */
        public Info() {
            super();
        }

        /**
         * Constructs an Info object by parsing information from a JSON string.
         * @param  json the JSON string to parse.
         */
        public Info(String json) {
            super(json);
        }

        /**
         * Constructs an Info object using an already parsed JSON object.
         * @param  jsonObject the parsed JSON object.
         */
        protected Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        @Override
        public BoxGroup getResource() {
            return BoxGroup.this;
        }
    }
}
