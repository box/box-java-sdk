package com.box.sdk;
import java.util.List;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

/**
 * Used to Setup Box Search Parameters
 *
 * <p>Advanced Search support here allows a number of parameters be specified to take full advantage of
 * box search capabilities. Query parameter is required in all cases except when Metadata templates
 * searching is being used.</p>
 *
 */
public class BoxSearchParameters {
    private String query;
    private List<String> fields;
    private String scope;
    private List<String> fileExtensions;
    private DateRange createdRange;
    private DateRange updatedRange;
    private SizeRange sizeRange;
    private List<String> ownerUserIds;
    private List<String> ancestorFolderIds;
    private List<String> contentTypes;
    private String type;
    private String trashContent;
    private BoxMetadataFilter metadataFilter;
    /**
     * Creates a Box Search Parameters Objects without query set, specific for Metadata Only Searches.
     */
    public BoxSearchParameters() {
    }
    /**
     * Creates a Box Search Parameters Objects with a query initiated.
     * @param query parameter.
     */
    public BoxSearchParameters(String query) {
        this.query = query;
    }
    /**
     * Clears the Parameters before performing a new search.
     * @return this.true;
     */
    public boolean clearParameters() {
        this.query = null;
        this.fields = null;
        this.scope = null;
        this.fileExtensions = null;
        this.createdRange = null;
        this.updatedRange = null;
        this.sizeRange = null;
        this.ownerUserIds = null;
        this.ancestorFolderIds = null;
        this.contentTypes = null;
        this.type = null;
        this.trashContent = null;
        this.metadataFilter = null;
        return true;
    }
    /**
     * Get existing query String that is being used.
     * @return this.query string.
     */
    public String getQuery() {
        return this.query;
    }
    /**
     * Set query string for that will be used to search.
     * @param query is a String value.
     */
    public void setQuery(String query) {
        this.query = query;
    }
    /**
     * Get the existing fields that are used for the search criteria.
     * @return this.List of fields.
     */
    public List<String> getFields() {
        return this.fields;
    }
    /**
     * Set the existing fields that are used for the search criteria.
     * @param fields specify what fields to be returned.
     */
    public void setFields(List<String> fields) {
        this.fields = fields;
    }
    /**
     * Get the scope on which you want to search, ["enterprise","scope"].
     * @return this.current scope that is set.
     */
    public String getScope() {
        return this.scope;
    }
    /**
     * Set the scope for how you want to search, ["enterprise","scope"].
     * @param scope set scope you want to search.
     */
    public void setScope(String scope) {
        this.scope = scope;
    }
    /**
     * Get file extension filter (jpg,docx).
     * @return this.list of extensions.
     */
    public List<String> getFileExtensions() {
        return this.fileExtensions;
    }
    /**
     * Set file extension by providing a list of strings [jpg,docx].
     * @param fileExtensions applied as a filter for extensions.
     */
    public void setFileExtensions(List<String> fileExtensions) {
        this.fileExtensions = fileExtensions;
    }
    /**
     * Get the DateRange filter to specify the when a file was created.
     * @return this.createdRange DateRange.
     */
    public DateRange getCreatedRange() {
        return this.createdRange;
    }
    /**
     * Set the from DateRange filter to specify when a file was created.
     * @param createdRange a start and end date on which a file was created.
     */
    public void setCreatedRange(DateRange createdRange) {
        this.createdRange = createdRange;
    }
    /**
     * Get the DateRange filter to specify the when a file was updated.
     * @return this.updatedRange DateRange.
     */
    public DateRange getUpdatedRange() {
        return this.updatedRange;
    }
    /**
     * Set the from DateRange filter to specify when a file was updated.
     * @param updatedRange a start and end date on which a file was updated.
     */
    public void setUpdatedRange(DateRange updatedRange) {
        this.updatedRange = updatedRange;
    }
    /**
     * Set the file size range for lower and upper bounds Bytes.
     * @param sizeRange a size filter.
     */
    public void setSizeRange(SizeRange sizeRange) {
        this.sizeRange = sizeRange;
    }
    /**
     * Return the size range that is being used as a filter.
     * @return this.sizeRange.
     */
    public SizeRange getSizeRange() {
        return this.sizeRange;
    }
    /**
     * Return the list of owner id's that are being applied as a search filter on the results.
     * @return ownerUserIds.
     */
    public List<String> getOwnerUserIds() {
        return this.ownerUserIds;
    }
    /**
     * Set the owner id's to be applied as a filter to restrict the results on specific file owners.
     * @param ownerUserIds applied ownerId's.
     */
    public void setOwnerUserIds(List<String> ownerUserIds) {
        this.ownerUserIds = ownerUserIds;
    }
    /**
     * Return the list of folder ids that is currently being used as applied filter.
     * @return  ancestorFolderIds.
     */
    public List<String> getAncestorFolderIds() {
        return this.ancestorFolderIds;
    }
    /**
     * Set the list of folder id's to be applied as a filter on the search filters.
     * @param ancestorFolderIds a list of folder ids that will contain results to specific folders.
     */
    public void setAncestorFolderIds(List<String> ancestorFolderIds) {
        this.ancestorFolderIds = ancestorFolderIds;
    }
    /**
     * Return content types that or being applied as a filter (name,description,tags,file_content).
     * @return  contentTypes.
     */
    public List<String> getContentTypes() {
        return this.contentTypes;
    }
    /**
     * Set list of content types that will be used as a filters.
     * @param contentTypes a list of content types such as (name,description,tags,file_content).
     */
    public void setContentTypes(List<String> contentTypes) {
        this.contentTypes = contentTypes;
    }
    /**
     * Return the type you want to return in your search.
     * @return String type.
     */
    public String getType() {
        return this.type;
    }
    /**
     * Set the type you want to search for can be file, folder, or web_link.
     * @param type can be file, folder, or web_link.
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * Return the specified trash search preference.
     * @return String trashContent.
     */
    public String getTrashContent() {
        return this.trashContent;
    }
    /**
     * Set trash filter. Can be trashed_only or non_trashed_only, without this parameter default to non_trashed_only.
     * @param trashContent Can be trashed_only or non_trashed_only.
     */
    public void setTrashContent(String trashContent) {
        this.trashContent = trashContent;
    }
    /**
     * Retrieve the existing BoxMetaDataFilter.
     * @return this.BoxMetaDataFilter
     */
    public BoxMetadataFilter getMetadataFilter() {
        return this.metadataFilter;
    }
    /**
     * Set the current list of Metadata Filters.
     * @param metadataFilter a list of the current metadata filters.
     */
    public void setMetadataFilter(BoxMetadataFilter metadataFilter) {
        this.metadataFilter = metadataFilter;
    }
    /**
     * Checks String to see if the parameter is null.
     * @param    paramValue Object that will be checked if null.
     * @return this.true if the parameter that is being checked is not null
     */
    private boolean isNullOrEmpty(Object paramValue) {
        boolean isNullOrEmpty = false;
        if (paramValue == null) {
            isNullOrEmpty = true;
        }
        if (paramValue instanceof String) {
            if (((String) paramValue).trim().equalsIgnoreCase("")) {
                isNullOrEmpty = true;
            }
        } else if (paramValue instanceof List) {
            return ((List) paramValue).isEmpty();
        }
        return isNullOrEmpty;
    }
    /**
     * Add BoxMetaDataFilter to the JsonArray boxMetadataFilterRequestArray.
     * @param @param bmf accepts a filter that has templateKey, scope, and filters populated.
     * @return JsonArray that is formated Json request
     */
    private JsonArray formatBoxMetadataFilterRequest() {
        JsonArray boxMetadataFilterRequestArray = new JsonArray();

        JsonObject boxMetadataFilter = new JsonObject()
                .add("templateKey", this.metadataFilter.getTemplateKey())
                .add("scope", this.metadataFilter.getScope())
                .add("filters", this.metadataFilter.getFiltersList());
        boxMetadataFilterRequestArray.add(boxMetadataFilter);

        return boxMetadataFilterRequestArray;
    }

    /**
     * Concat a List into a CSV String.
     * @param list list to concat
     * @return csv string
     */
    private String listToCSV(List<String> list) {
        String csvStr = "";
        for (String item : list) {
            csvStr += "," + item;
        }

        return csvStr.length() > 1 ? csvStr.substring(1) : csvStr;
    }
    /**
     * Get the Query Paramaters to be used for search request.
     * @return this.QueryStringBuilder.
     */
    public QueryStringBuilder getQueryParameters() {
        QueryStringBuilder builder = new QueryStringBuilder();

        if (this.isNullOrEmpty(this.query) && this.metadataFilter == null) {
            throw new BoxAPIException(
                "BoxSearchParameters requires either a search query or Metadata filter to be set."
            );
        }

        //Set the query of the search
        if (!this.isNullOrEmpty(this.query)) {
            builder.appendParam("query", this.query);
        }
        //Set the scope of the search
        if (!this.isNullOrEmpty(this.scope)) {
            builder.appendParam("scope", this.scope);
        }
        //Acceptable Value: "jpg,png"
        if (!this.isNullOrEmpty(this.fileExtensions)) {
            builder.appendParam("file_extensions", this.listToCSV(this.fileExtensions));
        }
        //Created Date Range: From Date - To Date
        if ((this.createdRange != null)) {
            builder.appendParam("created_at_range", this.createdRange.buildRangeString());
        }
        //Updated Date Range: From Date - To Date
        if ((this.updatedRange != null)) {
            builder.appendParam("updated_at_range", this.updatedRange.buildRangeString());
        }
        //Filesize Range
        if ((this.sizeRange != null)) {
            builder.appendParam("size_range", this.sizeRange.buildRangeString());
        }
        //Owner Id's
        if (!this.isNullOrEmpty(this.ownerUserIds)) {
            builder.appendParam("owner_user_ids", this.listToCSV(this.ownerUserIds));
        }
        //Ancestor ID's
        if (!this.isNullOrEmpty(this.ancestorFolderIds)) {
            builder.appendParam("ancestor_folder_ids", this.listToCSV(this.ancestorFolderIds));
        }
        //Content Types: "name, description"
        if (!this.isNullOrEmpty(this.contentTypes)) {
            builder.appendParam("content_types", this.listToCSV(this.contentTypes));
        }
        //Type of File: "file,folder,web_link"
        if (this.type != null) {
            builder.appendParam("type", this.type);
        }
        //Trash Content
        if (!this.isNullOrEmpty(this.trashContent)) {
            builder.appendParam("trash_content", this.trashContent);
        }
        //Metadata filters
        if (this.metadataFilter != null) {
            builder.appendParam("mdfilters", this.formatBoxMetadataFilterRequest().toString());
        }
        //Fields
        if (!this.isNullOrEmpty(this.fields)) {
            builder.appendParam("fields", this.listToCSV(this.fields));
        }
        return builder;
    }
}
