package com.box.sdk;

/**
 *
 */
public class Representation {
    private String representation;
    private Properties properties;
    //private Metadata

    public class Properties {
        private String dimensions;
        private String paged;
        private String thumb;

        public String getDimensions() {
            return this.dimensions;
        }

        public String getPaged() {
            return this.paged;
        }

        public String getThumb() {
            return this.thumb;
        }
    }
}
