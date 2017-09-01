package com.box.sdk;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 */
public class RepresentationHints {

    private Set<Representation.RepresentationType> representationTypes;
    private Set<Representation.Dimensions> dimensions;
    private Set<String> pagedHints;
    private Set<String> thumbHints;

    /**
     * Construct an empty RepresentationHints object.
     */
    public RepresentationHints() {
        this.representationTypes = new HashSet<Representation.RepresentationType>();
        this.dimensions = new LinkedHashSet<Representation.Dimensions>();
        this.pagedHints = new LinkedHashSet<String>();
        this.thumbHints = new LinkedHashSet<String>();
    }

    /**
     * Get set of representation types.
     * @return RepresentationType
     */
    public Set<Representation.RepresentationType> getRepresentationTypes() {
        return this.representationTypes;
    }

    /**
     * Add a representation type to the hint.
     * @param type RepresentationType
     */
    public void addRepresentationType(Representation.RepresentationType type) {
        this.representationTypes.add(type);
    }

    /**
     * Get dimensions.
     * @return dimensions
     */
    public Set<Representation.Dimensions> getDimensions() {
        return this.dimensions;
    }

    /**
     * Add a dimension hint.
     * @param dimensions dimension to retrieve
     */
    public void addDimensions(Representation.Dimensions dimensions) {
        if (this.representationTypes.size() > 0) {
            this.dimensions.add(dimensions);
        } else {
            throw new NullPointerException("At least one representation type should be added "
                + "before properties can be added ");
        }
    }

    /**
     * Get paged hints.
     * @return set of paged hints
     */
    public Set<String> getPagedHints() {
        return this.pagedHints;
    }

    /**
     * Add paged hint.
     * @param pagedHint hint
     */
    public void addPagedHint(String pagedHint) {
        if (this.representationTypes.size() > 0) {
            this.pagedHints.add(pagedHint);
        } else {
            throw new NullPointerException("At least one representation type should be added "
                + "before properties can be added ");
        }
    }

    /**
     * Get thumb hints.
     * @return set of thumb hints
     */
    public Set<String> getThumbHints() {
        return this.thumbHints;
    }

    /**
     * Add hints for thumb.
     * @param thumbHint hint
     */
    public void addThumbHint(String thumbHint) {
        if (this.representationTypes.size() > 0) {
            this.thumbHints.add(thumbHint);
        } else {
            throw new NullPointerException("At least one representation type should be added "
                + "before properties can be added ");
        }
    }
}
