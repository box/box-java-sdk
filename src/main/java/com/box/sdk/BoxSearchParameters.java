package com.box.sdk;
import java.util.Date;
import java.util.List;
/**
 * Used to Setup Box Search Parameters
 *
 * <p>Advanced Search support here allows a number of parameters be specified to take full advantage of
 * box search capabilities. Query parameter is required in all cases except when Metadata templates
 * searching is being used.</p>
 *
 */
public class BoxSearchParameters {
    private String query = null;
    private String keyword = null;
    private List<String> fields = null;
    private String scope = null;
    private List<String> fileExtensions = null;
    private Date createdAtRangeFromDate = null;
    private Date createdAtRangeToDate = null;
    private Date updatedAtRangeFromDate = null;
    private Date updatedAtRangeToDate = null;
    private int sizeRangeLowerBoundBytes = 0;
    private int sizeRangeUpperBoundBytes = 0;
    private List<String> ownerUserIds = null;
    private List<String> ancestorFolderIds = null;
    private List<String> contentTypes = null;
    private String type = null;
    private String trashContent = null;
    private List<BoxMetadataFilter> mdFilters = null;
    /**
     * Creates a Box Search Parameters Objects without query set, specific for Metadata Only Searchs
     * @param query parameter.
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
        this.keyword = null;
        this.fields = null;
        this.scope = null;
        this.fileExtensions = null;
        this.createdAtRangeFromDate = null;
        this.createdAtRangeToDate = null;
        this.updatedAtRangeFromDate = null;
        this.updatedAtRangeToDate = null;
        this.sizeRangeLowerBoundBytes = 0;
        this.sizeRangeUpperBoundBytes = 0;
        this.ownerUserIds = null;
        this.ancestorFolderIds = null;
        this.contentTypes = null;
        this.type = null;
        this.trashContent = null;
        this.mdFilters = null;
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
     * Get existing keyword String that is being used.
     * @return this.keyword String.
     */
    public String getKeyword() {
        return this.keyword;
    }
    /**
     * Set keyword string for that will be used for searching.
     * @param keyword String.
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
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
     * Get the from date filter to specify the when a file was created.
     * @return this.created date from.
     */
    public Date getCreatedAtRangeFromDate() {
        return this.createdAtRangeFromDate;
    }
    /**
     * Set the from date filter to specify when a file was created.
     * @param createdAtRangeFromDate a start date on which a file was created.
     */
    public void setCreatedAtRangeFromDate(Date createdAtRangeFromDate) {
        this.createdAtRangeFromDate = createdAtRangeFromDate;
    }
    /**
     * Return the from date (when a file was created) that is being used as a filter.
     * @return date.
     */
    public Date getCreatedAtRangeToDate() {
        return this.createdAtRangeToDate;
    }
    /**
     * Set the to date filter to specify when a file was created.
     * @param createdAtRangeToDate a end date on which a file was created.
     */
    public void setCreatedAtRangeToDate(Date createdAtRangeToDate) {
        this.createdAtRangeToDate = createdAtRangeToDate;
    }
    /**
     * Return the from date (when a file was updated) that is being used as a filter.
     * @return date.
     */
    public Date getUpdatedAtRangeFromDate() {
        return this.updatedAtRangeFromDate;
    }
    /**
     * Set the to date filter to specify when a file was updated.
     * @param updatedAtRangeFromDate a start date when a file was updated.
     */
    public void setUpdatedAtRangeFromDate(Date updatedAtRangeFromDate) {
        this.updatedAtRangeFromDate = updatedAtRangeFromDate;
    }
    /**
     * Return the to date (when a file was updated) that is being used as a filter.
     * @return Date updatedAtRangeToDate.
     */
    public Date getUpdatedAtRangeToDate() {
        return this.updatedAtRangeToDate;
    }
    /**
     * Set the to date filter to specify when a file was updated.
     * @param updatedAtRangeToDate a end date when a file was updated.
     */
    public void setUpdatedAtRangeToDate(Date updatedAtRangeToDate) {
        this.updatedAtRangeToDate = updatedAtRangeToDate;
    }
    /**
     * Return the size range lower bounds that is being used as a filter.
     * @return sizeRangeLowerBoundBytes.
     */
    public int getSizeRangeLowerBoundBytes() {
        return this.sizeRangeLowerBoundBytes;
    }
    /**
     * Set the file size range lower bounds Bytes.
     * @param sizeRangeLowerBoundBytes a size filter that will be lower range.
     */
    public void setSizeRangeLowerBoundBytes(int sizeRangeLowerBoundBytes) {
        this.sizeRangeLowerBoundBytes = sizeRangeLowerBoundBytes;
    }
    /**
     * Return the size range upper bounds that is being used as a filter.
     * @return sizeRangeUpperBoundBytes.
     */
    public int getSizeRangeUpperBoundBytes() {
        return this.sizeRangeUpperBoundBytes;
    }
    /**
     * Set the file size range upper bounds Bytes.
     * @param sizeRangeUpperBoundBytes a size filter that will be upper range.
     */
    public void setSizeRangeUpperBoundBytes(int sizeRangeUpperBoundBytes) {
        this.sizeRangeUpperBoundBytes = sizeRangeUpperBoundBytes;
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
     * Retrieve the existing BoxMetaDataFilterRequest.
     * @return this.BoxMetaDataFilterRequest
     */
    public BoxMetadataFilterRequest getMdFilters() {
        BoxMetadataFilterRequest bmfr = new BoxMetadataFilterRequest();
        for (BoxMetadataFilter bmf : this.mdFilters) {
            bmfr.addBoxMetadataFilter(bmf);
        }
        return bmfr;
    }
    /**
     * Set the current list of Metadata Filters.
     * @param mdFilters a list of the current metadata filters.
     */
    public void setMdFilters(List<BoxMetadataFilter> mdFilters) {
        this.mdFilters = mdFilters;
    }
    /**
     * Formated String is returned.
     * @param    Object that will be printed.
     * @return this.String
     */
    private String printParam(Object obj)    {
        return obj.toString().replaceAll("[\\[\\]]", "");
    }
    /**
     * Checks String to see if the parameter is null.
     * @param    paramValue Object that will be checked if null.
     * @return this.true if the parameter that is being checked is not null
     */
    public boolean isNotNull(Object paramValue) {
        boolean isNotNull = true;
        if (paramValue == null) {
            isNotNull = false;
        }
        if (paramValue instanceof String) {
            if (((String) paramValue).trim().equalsIgnoreCase("")) {
                isNotNull = false;
            }
        }
        return isNotNull;
    }
    /**
     * Get the Query Paramaters to be used for search request.
     * @return this.QueryStringBuilder.
     */
    public QueryStringBuilder getQueryParameters() {
        QueryStringBuilder builder = new QueryStringBuilder();
        
        //Set the query of the search
        if (this.isNotNull(this.query)) {
          builder.appendParam("query", this.query);
        }
        //Set the scope of the search
        if (this.isNotNull(this.scope)) {
            builder.appendParam("scope", this.printParam(this.scope));
        }
        //Acceptable Value: "jpg,png"
        if (this.isNotNull(this.fileExtensions)) {
            builder.appendParam("file_extensions", this.printParam(this.fileExtensions));
        }
        //Creaed Date Range: From Date - To Date
        if (this.isNotNull(this.createdAtRangeFromDate) && this.isNotNull(this.createdAtRangeToDate)) {
            String createFromDate = BoxDateFormat.format(this.createdAtRangeFromDate);
            String createToDate = BoxDateFormat.format(this.createdAtRangeToDate);
            builder.appendParam("created_at_range", this.buildRangeString(createFromDate, createToDate));
        }
        //Updated Date Range: From Date - To Date
        if (this.isNotNull(this.updatedAtRangeFromDate) && this.isNotNull(this.updatedAtRangeToDate)) {
            String updateFromDate = BoxDateFormat.format(this.updatedAtRangeFromDate);
            String updateToDate = BoxDateFormat.format(this.updatedAtRangeToDate);
            builder.appendParam("updated_at_range", this.buildRangeString(updateFromDate, updateToDate));
        }
        //Filesize Range
        if (this.sizeRangeLowerBoundBytes > -1 && this.sizeRangeUpperBoundBytes > 0
                && this.isNotNull(this.sizeRangeLowerBoundBytes) && this.isNotNull(this.sizeRangeUpperBoundBytes)) {
            builder.appendParam(
                "size_range", this.buildSizeRangeField(this.sizeRangeLowerBoundBytes, this.sizeRangeUpperBoundBytes));
        }
        //Owner Id's
        if (this.isNotNull(this.ownerUserIds)) {
            builder.appendParam("owner_user_ids", this.printParam(this.ownerUserIds));
        }
        //Ancestor ID's
        if (this.isNotNull(this.ancestorFolderIds)) {
            builder.appendParam("ancestor_folder_ids", this.printParam(this.ancestorFolderIds));
        }
        //Content Types: "name, description"
        if (this.isNotNull(this.contentTypes)) {
            builder.appendParam("content_types", this.printParam(this.contentTypes));
        }
        //Type of File: "file,folder,web_link"
        if (this.isNotNull(this.type)) {
            builder.appendParam("type", this.printParam(this.type));
        }
        //Trash Content
        if (this.isNotNull(this.trashContent)) {
            builder.appendParam("trash_content", this.printParam(this.trashContent));
        }
        //Metadata filters
        if (this.isNotNull(this.mdFilters)) {
            builder.appendParam("mdfilters", this.getMdFilters().getBoxMetadataFilterRequestArray().toString());
        }
        return builder;
    }
    /**
     * Allows you to construct a range field to be used for created and updated range filters.
     * @param from a start date from where files were created or updated.
     * @param to a end date from where files were created or updated.
     * @return String of a formated range that is combined.
     */
    private String buildDateRangeField(Date from, Date to) {
        String fromString = BoxDateFormat.format(from);
        String toString = BoxDateFormat.format(to);
        return this.buildRangeString(fromString, toString);
    }
    private String buildSizeRangeField(int lowerBoundBytes, int upperBoundBytes) {
        String lowerBoundString = "";
        if (lowerBoundBytes > -1) {
            lowerBoundString = String.valueOf(lowerBoundBytes);
        }

        String upperBoundString = "";
        if (upperBoundBytes > -1) {
            upperBoundString = String.valueOf(upperBoundBytes);
        }
        return this.buildRangeString(lowerBoundString, upperBoundString);
    }
    private String buildRangeString(String from, String to) {
        String rangeString = String.format("%s,%s", from, to);
        if    (rangeString == ",") {
            rangeString = null;
        }
        return rangeString;
    }

}
