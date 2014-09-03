package com.box.sdk;

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
        public String getID() {
            return BoxResource.this.getID();
        }

        public abstract T getResource();
    }
}
