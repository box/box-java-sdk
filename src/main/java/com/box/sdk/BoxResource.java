package com.box.sdk;

public abstract class BoxResource {
    private final OAuthSession session;
    private final String id;

    public BoxResource(OAuthSession session, String id) {
        this.session = session;
        this.id = id;
    }

    public OAuthSession getSession() {
        return this.session;
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
}
