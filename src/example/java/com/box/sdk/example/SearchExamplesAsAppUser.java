package com.box.sdk.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.BoxItem;
import com.box.sdk.BoxMetadataFilter;
import com.box.sdk.BoxSearch;
import com.box.sdk.BoxSearchParameters;
import com.box.sdk.BoxUser;
import com.box.sdk.DateRange;
import com.box.sdk.EncryptionAlgorithm;
import com.box.sdk.IAccessTokenCache;
import com.box.sdk.InMemoryLRUAccessTokenCache;
import com.box.sdk.JWTEncryptionPreferences;
import com.box.sdk.PartialCollection;
import com.box.sdk.SizeRange;

public final class SearchExamplesAsAppUser {

    private static final String CLIENT_ID = "";
    private static final String CLIENT_SECRET = "";
    private static final String USER_ID = "";
    private static final String PUBLIC_KEY_ID = "";
    private static final String PRIVATE_KEY_FILE = "";
    private static final String PRIVATE_KEY_PASSWORD = "";
    private static final int MAX_DEPTH = 1;
    private static final int MAX_CACHE_ENTRIES = 100;

    private static BoxDeveloperEditionAPIConnection api;

    private SearchExamplesAsAppUser() { }

    public static void main(String[] args) throws IOException {
        // Turn off logging to prevent polluting the output.
        Logger.getLogger("com.box.sdk").setLevel(Level.SEVERE);

        String privateKey = new String(Files.readAllBytes(Paths.get(PRIVATE_KEY_FILE)));

        JWTEncryptionPreferences encryptionPref = new JWTEncryptionPreferences();
        encryptionPref.setPublicKeyID(PUBLIC_KEY_ID);
        encryptionPref.setPrivateKey(privateKey);
        encryptionPref.setPrivateKeyPassword(PRIVATE_KEY_PASSWORD);
        encryptionPref.setEncryptionAlgorithm(EncryptionAlgorithm.RSA_SHA_256);

        //It is a best practice to use an access token cache to prevent unneeded requests to Box for access tokens.
        //For production applications it is recommended to use a distributed cache like Memcached or Redis, and to
        //implement IAccessTokenCache to store and retrieve access tokens appropriately for your environment.
        IAccessTokenCache accessTokenCache = new InMemoryLRUAccessTokenCache(MAX_CACHE_ENTRIES);

        api = BoxDeveloperEditionAPIConnection.getAppUserConnection(USER_ID, CLIENT_ID,
                CLIENT_SECRET, encryptionPref, accessTokenCache);

        BoxUser.Info userInfo = BoxUser.getCurrentUser(api).getInfo();
        System.out.format("Welcome, %s!\n\n", userInfo.getName());

        BoxSearch boxSearch = new BoxSearch(api);

        searchExample(boxSearch);
    }

    private static void searchExample(BoxSearch bs) {
        /**
         * Use this class to specify all the different search parameters that you may want to use.
         * Examples include: type, contentType, folderId's, metadata filters, created, updated.
         * Also shows how to incrementally crawl result sets.
         */
        BoxSearchParameters bsp = new BoxSearchParameters();

        fileExtensionExample(bsp, bs);
        ownerIdFilterExample(bsp, bs);
        contentTypeFilterExample(bsp, bs);
        createdDateFilterExample(bsp, bs);
        updatedDateFilterExample(bsp, bs);
        metaDataFilterDropdownExample(bsp, bs);
        metaDataFilterGreaterThanNumberExample(bsp, bs);
        metaDataFilterLessThanNumberExample(bsp, bs);
        metaDataFilterGreaterThanDateExample(bsp, bs);
        metaDataFilterLessThanDateExample(bsp, bs);
        metaDataFilterGreaterLessThanDateExample(bsp, bs);

    }

    public static void crawlSearchResultExample(BoxSearchParameters bsp, BoxSearch bs)
    {
        /**
         * Example of how to crawl more than 1000+ results in a search query
         */

        //Setup Result Partial Object
        PartialCollection<BoxItem.Info> searchResults;

        //Starting point of the result set
        long offset = 0;
        //Number of results that would be pulled back
        long limit = 1000;

        //Storing the full size of the results
        long fullSizeOfResult = 0;

        while (offset <= fullSizeOfResult) {
            searchResults = bs.searchRange(offset, limit, bsp);
            fullSizeOfResult = searchResults.fullSize();

            print("offset: " + offset + " of fullSizeOfResult: " + fullSizeOfResult);
            printSearchResults(searchResults);

            offset += limit;
        }
    }
    public static void fileExtensionExample(BoxSearchParameters bsp, BoxSearch bs)
    {

        print("******File Extension Search******");
        List<String> fileExtensions = new ArrayList<String>();
        fileExtensions.add("png");
        fileExtensions.add("docx");

        List<String> ancestorFolderIds = new ArrayList<String>();
        ancestorFolderIds.add("6725599265");

        bsp.setFileExtensions(fileExtensions);
        bsp.setAncestorFolderIds(ancestorFolderIds);
        bsp.setQuery("Testing");

        crawlSearchResultExample(bsp, bs);
    }

    public static void ownerIdFilterExample(BoxSearchParameters bsp, BoxSearch bs) {

        print("******Owner Id's Filter Search******");
        List<String> ownerUserIds = new ArrayList<String>();
        ownerUserIds.add("268117237");

        bsp.clearParameters();
        bsp.setQuery("Assemble");
        bsp.setOwnerUserIds(ownerUserIds);

        crawlSearchResultExample(bsp, bs);

    }

    public static void contentTypeFilterExample(BoxSearchParameters bsp, BoxSearch bs)
    {
        print("*****Description Search******");
        List<String> contentTypes = new ArrayList<String>();
        contentTypes.add("description");

        bsp.clearParameters();
        bsp.setContentTypes(contentTypes);
        bsp.setQuery("test");

        crawlSearchResultExample(bsp, bs);
    }

    public static void createdDateFilterExample(BoxSearchParameters bsp, BoxSearch bs)
    {
        print("*****Created Date Range Search******");
        bsp.clearParameters();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("M-dd-yyyy hh:mm:ss");
            String createdFromDateString = "01-31-2016 00:00:00";
            String createdToDateString = "04-01-2016 00:00:00";

            Date createdFromDate = sdf.parse(createdFromDateString);
            Date createdToDate = sdf.parse(createdToDateString);

            DateRange createdRange = new DateRange(createdFromDate, createdToDate);

            bsp.setCreatedRange(createdRange);
            bsp.setQuery("File");

            crawlSearchResultExample(bsp, bs);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void updatedDateFilterExample(BoxSearchParameters bsp, BoxSearch bs)
    {
        print("*****Updated Date Range Search******");
        bsp.clearParameters();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("M-dd-yyyy hh:mm:ss");
            String updatedFromDateString = "01-31-2016 00:00:00";
            String updatedToDateString = "05-01-2016 00:00:00";

            Date updatedFromDate = sdf.parse(updatedFromDateString);
            Date updatedToDate = sdf.parse(updatedToDateString);


            DateRange updatedRange = new DateRange(updatedFromDate, updatedToDate);

            bsp.setUpdatedRange(updatedRange);
            bsp.setQuery("File");

            crawlSearchResultExample(bsp, bs);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void metaDataFilterDropdownExample(BoxSearchParameters bsp, BoxSearch bs) {
        print("******Metadata Filter Search Dropdown Value Check******");
        bsp.clearParameters();

        BoxMetadataFilter bmf = new BoxMetadataFilter();
        bmf.setScope("enterprise");
        bmf.setTemplateKey("test");

        SizeRange sizeRange = new SizeRange(12, 19);
        bmf.addNumberRangeFilter("testnumber", sizeRange);

        bmf.addFilter("test", "example");

        bsp.setMetadataFilter(bmf);

        crawlSearchResultExample(bsp, bs);
    }

    public static void metaDataFilterGreaterThanNumberExample(BoxSearchParameters bsp, BoxSearch bs) {
        print("******Metadata Filter Search Numbers Greater Than******");
        bsp.clearParameters();

        BoxMetadataFilter bmf = new BoxMetadataFilter();
        bmf.setScope("enterprise");
        bmf.setTemplateKey("test");

        SizeRange sizeRange = new SizeRange(20, 0);
        bmf.addNumberRangeFilter("testnumber", sizeRange);

        bsp.setMetadataFilter(bmf);

        crawlSearchResultExample(bsp, bs);
    }

    public static void metaDataFilterLessThanNumberExample(BoxSearchParameters bsp, BoxSearch bs) {
        print("******Metadata Filter Search Numbers Less Than******");
        bsp.clearParameters();

        BoxMetadataFilter bmf = new BoxMetadataFilter();
        bmf.setScope("enterprise");
        bmf.setTemplateKey("test");

        SizeRange sizeRange = new SizeRange(0, 20);
        bmf.addNumberRangeFilter("testnumber", sizeRange);

        bsp.setMetadataFilter(bmf);

        crawlSearchResultExample(bsp, bs);
    }

    public static void metaDataFilterGreaterThanDateExample(BoxSearchParameters bsp, BoxSearch bs) {
        print("******Metadata Filter Search Date Greater Than******");
        try {
            bsp.clearParameters();

            BoxMetadataFilter bmf = new BoxMetadataFilter();

            bmf = new BoxMetadataFilter();
            bmf.setScope("enterprise");
            bmf.setTemplateKey("test");

            SimpleDateFormat sdf = new SimpleDateFormat("M-dd-yyyy hh:mm:ss");
            String dateGreaterThanString = "03-01-2016 00:00:00";

            Date dateGreaterThan = sdf.parse(dateGreaterThanString);

            DateRange dr = new DateRange(dateGreaterThan, null);

            bmf.addDateRangeFilter("testDate", dr);

            bsp.setMetadataFilter(bmf);

            crawlSearchResultExample(bsp, bs);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void metaDataFilterLessThanDateExample(BoxSearchParameters bsp, BoxSearch bs) {
        print("******Metadata Filter Search Date Less Than******");
        try {
            bsp.clearParameters();

            BoxMetadataFilter bmf = new BoxMetadataFilter();
            bsp.clearParameters();

            bmf = new BoxMetadataFilter();
            bmf.setScope("enterprise");
            bmf.setTemplateKey("test");

            SimpleDateFormat sdf = new SimpleDateFormat("M-dd-yyyy hh:mm:ss");
            String dateLessThanString = "05-03-2016 00:00:00";


            Date dateLessThan = sdf.parse(dateLessThanString);

            DateRange dr = new DateRange(null, dateLessThan);

            bmf.addDateRangeFilter("testDate", dr);

            bsp.setMetadataFilter(bmf);

            crawlSearchResultExample(bsp, bs);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void metaDataFilterGreaterLessThanDateExample(BoxSearchParameters bsp, BoxSearch bs) {
        print("******Metadata Filter Search Date Range Both GT and LT******");
        try {
            bsp.clearParameters();

            BoxMetadataFilter bmf = new BoxMetadataFilter();
            bmf.setScope("enterprise");
            bmf.setTemplateKey("test");

            SimpleDateFormat sdf = new SimpleDateFormat("M-dd-yyyy hh:mm:ss");
            String dateLessThanString = "05-15-2016 00:00:00";
            String dateGreaterThanString = "03-01-2016 00:00:00";

            Date dateGreaterThan = sdf.parse(dateGreaterThanString);
            Date dateLessThan = sdf.parse(dateLessThanString);

            DateRange dr = new DateRange(dateGreaterThan, dateLessThan);

            bmf.addDateRangeFilter("testDate", dr);

            bsp.setMetadataFilter(bmf);

            crawlSearchResultExample(bsp, bs);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void printSearchResults(PartialCollection<BoxItem.Info> searchResults) {
        //Crawl the folder
        System.out.println("--==Results fullResultSize: " + searchResults.fullSize() + "==--");

        for (BoxItem.Info itemInfo : searchResults) {
            System.out.println("File Found: " + itemInfo.getName() + ", Owner: " + itemInfo.getOwnedBy().getID());
        }

        System.out.println("");
    }

    /**
     *     Used for a simple print function, leverages System.out.println.
     * @param s string to be used as a print.
     */
    private static void print(String s) {
        System.out.println(s);
    }
}
