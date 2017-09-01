package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * The class represents one instance of a file representation.
 */
public class Representation {

    /**
     * Enum for dimensions for jpg and png representations.
     */
    public enum Dimensions {

        /**
         * 32x32 dimensions.
         */
        NANO ("32x32"),
        /**
         * 94x94 dimensions.
         */
        MICRO ("94x94"),
        /**
         * 160x160 dimensions.
         */
        MINI ("160x160"),
        /**
         * 320x320 dimensions.
         */
        SMALL ("320x320"),
        /**
         * 1024x1024 dimensions.
         */
        MEDIUM ("1024x1024"),
        /**
         * 2048x2048 dimensions.
         */
        LARGE ("2048x2048");

        private final String dimension;

        /**
         * Construct Dimensions from string value.
         * @param dimension
         */
        Dimensions(String dimension) {
            this.dimension = dimension;
        }

        @Override
        public String toString() {
            return this.dimension;
        }

        /**
         * Get Dimensions enum from value.
         * @param value from api response
         * @return Dimensions
         */
        public static Dimensions getDimensions(String value) {
            if (value.equals("32x32")) {
                return Dimensions.NANO;
            } else if (value.equals("94x94")) {
                return Dimensions.MICRO;
            } else if (value.equals("160x160")) {
                return Dimensions.MINI;
            } else if (value.equals("320x320")) {
                return Dimensions.SMALL;
            } else if (value.equals("1024x1024")) {
                return Dimensions.MEDIUM;
            } else if (value.equals("2048x2048")) {
                return Dimensions.LARGE;
            } else {
                throw new NoSuchFieldError("Unknow dimensions");
            }
        }
    }

    /**
     * Enum for representation types.
     */
    public enum RepresentationType {

        /**
         * jpg representation.
         */
        JPG ("jpg"),

        /**
         * png representation.
         */
        PNG ("png"),

        /**
         * pdf representation.
         */
        PDF ("pdf"),

        /**
         * dash representation.
         */
        DASH ("dash"),

        /**
         * hls representaion.
         */
        hls ("hls"),

        /**
         * filmstrim representaion.
         */
        filmstrip ("filmstrip"),

        /**
         * mp4 representation.
         */
        MP4 ("mp4"),

        /**
         * mp3 representation.
         */
        MP3 ("mp3"),

        /**
         * box dicom representation.
         */
        BOX_DICOM ("box_dicom"),

        /**
         * text representation.
         */
        TEXT ("text"),

        /**
         * extracted text representation.
         */
        EXTRACTED_TEXT ("extracted_text"),

        /**
         * 3d representation.
         */
        THREED ("3d");

        private String representationType;

        /**
         * Construct RepresentationType from string value.
         * @param value
         */
        RepresentationType(String value) {
            this.representationType = value;
        }

        @Override
        public String toString() {
            return this.representationType;
        }

        /**
         * Get representation type from value.
         * @param value from api response
         * @return RepresentationType
         */
        public static RepresentationType getRepresentationType(String value) {
            if (value.equals("jpg")) {
                return  RepresentationType.JPG;
            } else if (value.equals("png")) {
                return  RepresentationType.PNG;
            } else if (value.equals("pdf")) {
                return  RepresentationType.PDF;
            } else if (value.equals("dash")) {
                return  RepresentationType.DASH;
            } else if (value.equals("hls")) {
                return  RepresentationType.DASH;
            } else if (value.equals("filmstrip")) {
                return  RepresentationType.filmstrip;
            } else if (value.equals("mp4")) {
                return  RepresentationType.MP4;
            } else if (value.equals("mp3")) {
                return  RepresentationType.MP3;
            } else if (value.equals("box_dicom")) {
                return  RepresentationType.PNG;
            } else if (value.equals("text")) {
                return  RepresentationType.TEXT;
            } else if (value.equals("extracted_text")) {
                return  RepresentationType.EXTRACTED_TEXT;
            } else if (value.equals("3d")) {
                return  RepresentationType.THREED;
            } else {
                throw new NoSuchFieldError("Unknow representation type");
            }
        }
    }

    private RepresentationType representation;
    private Properties properties;
    private Metadata metadata;
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
                this.representation = RepresentationType.getRepresentationType(member.getValue().asString());
            } else if (member.getName().equals("properties")) {
                this.properties = new Properties(member.getValue().asObject());
            } else if (member.getName().equals("metadata")) {
                this.metadata = new Metadata(member.getValue().asObject());
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
    public RepresentationType getRepresentation() {
        return this.representation;
    }

    /**
     * Get representation's set of static properties to distinguish between subtypes of a given representation,
     * for example, different sizes of jpg's. Each representation has its own set of properties.
     * @return properties of representation
     */
    public Properties getProperties() {
        return this.properties;
    }

    /**
     * Get representation's metadata.
     *
     * @return metadata
     */
    public Metadata getMetadata() {
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
     * A set of static properties to distinguish between subtypes of a given representation,
     * for example, different sizes of jpg's. Each representation has its own set of properties.
     */
    public class Properties {

        private Dimensions dimensions;
        private String paged;
        private String thumb;

        /**
         * Construct a representation's properties.
         * @param members json object
         */
        public Properties(JsonObject members) {
            for (JsonObject.Member member : members) {
                if (member.getName().equals("dimensions")) {
                    this.dimensions = Dimensions.getDimensions(member.getValue().asString());
                } else if (member.getName().equals("paged")) {
                    this.paged = member.getValue().asString();
                } else if (member.getName().equals("thumb")) {
                    this.thumb = member.getValue().asString();
                }
            }
        }

        /**
         * Get dimensions of representation.
         * @return dimensions
         */
        public Dimensions getDimensions() {
            return this.dimensions;
        }

        /**
         * Get whether or not multiple pages are supported or not.
         * @return paged value
         */
        public String getPaged() {
            return this.paged;
        }

        /**
         * When true, down-sampling options are used to produce a better image.
         * @return thumb value
         */
        public String getThumb() {
            return this.thumb;
        }
    }

    /**
     * Representation's metadata which is a set of dynamic properties about this specific representation of this
     * specific file. Metadata is different for each representation subtype.
     */
    public class Metadata {

        private int pages;
        private JsonObject jsonObject;

        /**
         * Construct a representation's metadata.
         * @param members json object
         */
        public Metadata(JsonObject members) {
            for (JsonObject.Member member : members) {
                if (member.getName().equals("pages")) {
                    this.pages = member.getValue().asInt();
                }
            }
            this.jsonObject = members;
        }

        /**
         * No. of pages in a multi-page representation.
         * @return no. of pages
         */
        public int getPages() {
            return this.pages;
        }

        /**
         * Returns a json value for any field in a repreentation's metadata.
         * @param field the field that designates the key
         * @return the metadata property value.
         */
        public JsonValue get(String field) {
            return this.jsonObject.get(field);
        }
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
