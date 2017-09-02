package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;

import com.eclipsesource.json.JsonObject;

/**
 * The class represents one instance of a file representation.
 */
public class Representation {

    /**
     * Used to validate if the hints header has (near) valid value.
     */
    protected static final String X_REP_HINTS_PATTERN = "^(?:\\[[a-z0-9_]+(?:\\?[a-z0-9_]+\\=[a-z0-9_]+(?:"
        + "\\|[a-z0-9_]+)*(?:&[a-z0-9_]+\\=[a-z0-9_]+(?:\\|[a-z0-9_]+)*)*)?(?:,[a-z0-9_]+(?:\\?[a-z0-9_]+\\=[a-z0-9_]+"
        + "(?:\\|[a-z0-9_]+)*(?:&[a-z0-9_]+\\=[a-z0-9_]+(?:\\|[a-z0-9_]+)*)*)?)*\\])+$";

    private String representation;
    private JsonObject properties;
    private JsonObject metadata;
    private Info info;
    private Content content;
    private Status status;

    /**
     * Construct a representation from JsonObject.
     * @param representationJson representaion entry
     */
    public Representation(JsonObject representationJson) {
        for (JsonObject.Member member : representationJson) {
            if (member.getName().equals("representation")) {
                this.representation = member.getValue().asString();
            } else if (member.getName().equals("properties")) {
                this.properties = member.getValue().asObject();
            } else if (member.getName().equals("metadata")) {
                this.metadata = member.getValue().asObject();
            } else if (member.getName().equals("info")) {
                this.info = new Info(member.getValue().asObject());
            } else if (member.getName().equals("content")) {
                this.content = new Content(member.getValue().asObject());
            } else if (member.getName().equals("status")) {
                this.status = new Status(member.getValue().asObject());
            }
        }
    }

    /**
     * Get the extension of the format, but occasionally a name of a standard (potentially de facto) format
     * or a proprietary format that Box supports.
     *
     * @return representation name
     */
    public String getRepresentation() {
        return this.representation;
    }

    /**
     * Get representation's set of static properties to distinguish between subtypes of a given representation,
     * for example, different sizes of jpg's. Each representation has its own set of properties.
     * @return properties of representation as JsonObject
     */
    public JsonObject getProperties() {
        return this.properties;
    }

    /**
     * Get representation's metadata.
     *
     * @return metadataas JsonObject
     */
    public JsonObject getMetadata() {
        return this.metadata;
    }

    /**
     * Get Info which has an opaque URL which will return status information about the file.
     * It may change over time and should not be hard-coded or cached.
     * @return info
     */
    public Info getInfo() {
        return this.info;
    }

    /**
     * Get representation's content which includes a url template.
     * @return content
     */
    public Content getContent() {
        return this.content;
    }

    /**
     * A string with one of the following values: 'none', 'pending', 'viewable', 'error' and 'success'.
     * @return status
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * Representation's info URL.
     */
    public class Info {

        private URL url;

        /**
         * Construct Representation's info.
         * @param members json object
         */
        public Info(JsonObject members) {
            for (JsonObject.Member member : members) {
                if (member.getName().equals("url")) {
                    try {
                        this.url = new URL(member.getValue().asString());
                    } catch (MalformedURLException e) {
                        throw new BoxAPIException("Couldn't parse info.url for a file representation", e);
                    }
                }
            }
        }

        /**
         * An opaque URL which will return status information about the file.
         * @return url
         */
        public URL getUrl() {
            return this.url;
        }
    }

    /**
     * Representation's content.
     */
    public class Content {

        private String urlTemplate;

        /**
         * Construct a representation's content.
         * @param members json object
         */
        public Content(JsonObject members) {
            for (JsonObject.Member member : members) {
                if (member.getName().equals("url_template")) {
                    this.urlTemplate = member.getValue().asString();
                }
            }
        }

        /**
         * Get an opaque URL template to the content, which follows RFC 6570. There is an asset_path variable that
         * should be replaced with a valid path. Valid paths are different for each representation subtype.
         * It may change over time and should not be hard-coded or cached.
         * @return url template
         */
        public String getUrlTemplate() {
            return this.urlTemplate;
        }
    }

    /**
     * Representation's status.
     */
    public class Status {

        private String state;

        /**
         * Construct a status object for a representation.
         * @param members of status object
         */
        public Status(JsonObject members) {
            for (JsonObject.Member member : members) {
                if (member.getName().equals("state")) {
                    this.state = member.getValue().asString();
                }
            }
        }

        /**
         * A string with one of the following values: 'none', 'pending', 'viewable', 'error' and 'success'.
         * none - the unknown or initial state.
         * pending - content is being generated but is not ready yet.
         * viewable - like pending, though indicates that enough content is available to be useful.
         * error - an error happened and this content is not available.
         * success - all of the content is available and complete.
         * @return state
         */
        public String getState() {
            return this.state;
        }
    }
}
