package com.box.sdk;

import java.util.List;

import com.eclipsesource.json.JsonObject;

public abstract class BoxResource {
    private final BoxAPIConnection api;
    private final String id;

    public BoxResource(BoxAPIConnection api, String id) {
        this.api = api;
        this.id = id;
    }

    public BoxAPIConnection getAPI() {
        return this.api;
    }

    public String getID() {
        return this.id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other instanceof BoxResource) {
            BoxResource otherResource = (BoxResource) other;
            return this.getID().equals(otherResource.getID());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.getID().hashCode();
    }

    public abstract class Info<T extends BoxResource> {
        private JsonObject pendingChanges;

        public Info() {
            this.pendingChanges = new JsonObject();
        }

        public Info(String json) {
            this(JsonObject.readFrom(json));
        }

        protected Info(JsonObject jsonObject) {
            this.updateFromJSON(jsonObject);
        }

        public String getID() {
            return BoxResource.this.getID();
        }

        public List<String> getPendingChanges() {
            return this.pendingChanges.names();
        }

        public abstract T getResource();

        @Override
        public String toString() {
            return this.pendingChanges.toString();
        }

        protected void addPendingChange(String key, String value) {
            this.pendingChanges.set(key, value);
        }

        protected void clearPendingChanges() {
            this.pendingChanges = new JsonObject();
        }

        protected void updateFromJSON(JsonObject jsonObject) {
            for (JsonObject.Member member : jsonObject) {
                if (member.getValue().isNull()) {
                    continue;
                }

                this.parseJSONMember(member);
            }

            this.clearPendingChanges();
        }

        protected void parseJSONMember(JsonObject.Member member) { }
    }
}
