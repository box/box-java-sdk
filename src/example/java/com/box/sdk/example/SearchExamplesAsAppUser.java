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
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.box.sdk.BoxMetadataFilter;
import com.box.sdk.BoxSearchParameters;
import com.box.sdk.BoxUser;
import com.box.sdk.EncryptionAlgorithm;
import com.box.sdk.IAccessTokenCache;
import com.box.sdk.InMemoryLRUAccessTokenCache;
import com.box.sdk.JWTEncryptionPreferences;
import com.box.sdk.PartialCollection;



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

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        searchTest(rootFolder);

        listFolder(rootFolder, 0);
    }

    private static void searchTest(BoxFolder bf) {
        //******Requires a Query as a parameter Unless ******
        BoxSearchParameters bsp = new BoxSearchParameters("Testing");



        print("******File Extension Search******");
        List<String> fileExtensions = new ArrayList<String>();
        fileExtensions.add("png");
        fileExtensions.add("docx");

        List<String> ancestorFolderIds = new ArrayList<String>();
        //Specify a folder ID, can accept multiple
        ancestorFolderIds.add("6725599265");

        bsp.setFileExtensions(fileExtensions);
        bsp.setAncestorFolderIds(ancestorFolderIds);
        bsp.setQuery("Testing");


        /**
         * Example of how to crawl more than 1000+ results in a search query
         */
        //Starting point of the result set
        long offset = 0;
        //Number of results that would be pulled back
        long limit = 1000;

        //Storing the full size of the results
        long fullSizeOfResult = 0;

        PartialCollection<BoxItem.Info> searchResults = null;


        while (offset <= fullSizeOfResult) {
            searchResults = bf.search(offset, limit, bsp);
            fullSizeOfResult = searchResults.fullSize();

            print("offset: " + offset + " of fullSizeOfResult: " + fullSizeOfResult);
            printSearchResults(searchResults);

            offset += limit;
        }


        print("******Owner Id's Filter Search******");
        List<String> ownerUserIds = new ArrayList<String>();
        ownerUserIds.add("268117237");


        bsp.clearParameters();
        bsp.setQuery("Assemble");
        bsp.setOwnerUserIds(ownerUserIds);

        searchResults = bf.search(0, 1000, bsp);
        printSearchResults(searchResults);

        print("******Metadata Search Filter Tests******");
        testMetaDataFilter(bsp, bf);


        print("*****Description Search******");
        List<String> contentTypes = new ArrayList<String>();
        contentTypes.add("description");

        bsp.clearParameters();
        bsp.setContentTypes(contentTypes);
        bsp.setQuery("testing");

        searchResults = bf.search(0, 1000, bsp);
        printSearchResults(searchResults);



        print("*****Created Date Range Search******");
        bsp.clearParameters();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("M-dd-yyyy hh:mm:ss");
            String createdAtRangeFromDateString = "01-31-2016 00:00:00";
            String createdAtRangeToDateString = "04-01-2016 00:00:00";

            Date createdAtRangeFromDate = sdf.parse(createdAtRangeFromDateString);
            Date createdAtRangeToDate = sdf.parse(createdAtRangeToDateString);


            bsp.setCreatedAtRangeFromDate(createdAtRangeFromDate);
            bsp.setCreatedAtRangeToDate(createdAtRangeToDate);
            bsp.setQuery("File");

            searchResults = bf.search(0, 1000, bsp);
            printSearchResults(searchResults);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        print("*****Updated Date Range Search******");
        bsp.clearParameters();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("M-dd-yyyy hh:mm:ss");
            String updatedAtRangeFromDateString = "01-31-2016 00:00:00";
            String updatedAtRangeToDateString = "05-01-2016 00:00:00";

            Date updatedAtRangeFromDate = sdf.parse(updatedAtRangeFromDateString);
            Date updatedAtRangeToDate = sdf.parse(updatedAtRangeToDateString);


            bsp.setUpdatedAtRangeFromDate(updatedAtRangeFromDate);
            bsp.setUpdatedAtRangeToDate(updatedAtRangeToDate);
            bsp.setQuery("File");

            searchResults = bf.search(0, 1000, bsp);
            printSearchResults(searchResults);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void testMetaDataFilter(BoxSearchParameters bsp, BoxFolder bf) {

        PartialCollection<BoxItem.Info> searchResults = bf.search(0, 1000, bsp);

        print("******Metadata Filter Search Dropdown Value Check******");
        List<BoxMetadataFilter> mdFilters = new ArrayList<BoxMetadataFilter>();
        bsp.clearParameters();

        BoxMetadataFilter bmf = new BoxMetadataFilter();
        bmf.setScope("enterprise");
        bmf.setTemplateKey("testing");
        bmf.setFilter("testing", "filterValueTest");

        mdFilters.add(bmf);

        bsp.setMdFilters(mdFilters);
        searchResults = bf.search(0, 1000, bsp);
        printSearchResults(searchResults);

        print("******Metadata Filter Search Numbers Greater Than******");
        mdFilters = new ArrayList<BoxMetadataFilter>();
        bsp.clearParameters();

        bmf = new BoxMetadataFilter();
        bmf.setScope("enterprise");
        bmf.setTemplateKey("testing");
        bmf.setGreaterThanNumberFilter("testingnumber", 20);

        mdFilters.add(bmf);

        bsp.setMdFilters(mdFilters);
        searchResults = bf.search(0, 1000, bsp);
        printSearchResults(searchResults);

        print("******Metadata Filter Search Numbers Less Than******");
        mdFilters = new ArrayList<BoxMetadataFilter>();
        bsp.clearParameters();

        bmf = new BoxMetadataFilter();
        bmf.setScope("enterprise");
        bmf.setTemplateKey("testing");
        bmf.setLessThanNumberFilter("testingnumber", 20);

        mdFilters.add(bmf);

        bsp.setMdFilters(mdFilters);
        searchResults = bf.search(0, 1000, bsp);
        printSearchResults(searchResults);

        print("******Metadata Filter Search Date Greater Than******");
        try {
            mdFilters = new ArrayList<BoxMetadataFilter>();
            bsp.clearParameters();
            bmf = new BoxMetadataFilter();
            bmf.setScope("enterprise");
            bmf.setTemplateKey("testing");

            SimpleDateFormat sdf = new SimpleDateFormat("M-dd-yyyy hh:mm:ss");
            String dateGreaterThanString = "03-01-2016 00:00:00";

            Date dateGreaterThan = sdf.parse(dateGreaterThanString);

            bmf.setGreaterThanDateFilter("testingDate", dateGreaterThan);

            mdFilters.add(bmf);

            bsp.setMdFilters(mdFilters);
            searchResults = bf.search(0, 1000, bsp);
            printSearchResults(searchResults);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        print("******Metadata Filter Search Date Less Than******");
        try {
            mdFilters = new ArrayList<BoxMetadataFilter>();
            bsp.clearParameters();

            bmf = new BoxMetadataFilter();
            bmf.setScope("enterprise");
            bmf.setTemplateKey("testing");

            SimpleDateFormat sdf = new SimpleDateFormat("M-dd-yyyy hh:mm:ss");
            String dateLessThanString = "05-03-2016 00:00:00";

            Date dateLessThan = sdf.parse(dateLessThanString);

            bmf.setLessThanDateFilter("testingDate", dateLessThan);

            mdFilters.add(bmf);

            bsp.setMdFilters(mdFilters);
            searchResults = bf.search(0, 1000, bsp);
            printSearchResults(searchResults);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        print("******Metadata Filter Search Date Range Both GT and LT******");
        try {
            mdFilters = new ArrayList<BoxMetadataFilter>();
            bsp.clearParameters();

            bmf = new BoxMetadataFilter();
            bmf.setScope("enterprise");
            bmf.setTemplateKey("testing");

            SimpleDateFormat sdf = new SimpleDateFormat("M-dd-yyyy hh:mm:ss");
            String dateLessThanString = "05-03-2016 00:00:00";
            String dateGreaterThanString = "03-01-2016 00:00:00";

            Date dateGreaterThan = sdf.parse(dateGreaterThanString);
            Date dateLessThan = sdf.parse(dateLessThanString);

            bmf.setDateRangeFilter("testingDate", dateGreaterThan, dateLessThan);

            mdFilters.add(bmf);

            bsp.setMdFilters(mdFilters);
            searchResults = bf.search(0, 1000, bsp);
            printSearchResults(searchResults);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void listFolder(BoxFolder folder, int depth) {
        for (BoxItem.Info itemInfo : folder) {
            String indent = "";
            for (int i = 0; i < depth; i++) {
                indent += "    ";
            }
            if (itemInfo.getOwnedBy() != null) {
                System.out.println(", owner:" + itemInfo.getOwnedBy().getID());
            }
            System.out.println(indent + itemInfo.getName());

            BoxFolder bf = new BoxFolder(api, itemInfo.getID());


            //Crawl the folder
            for (BoxItem.Info itemInfo1 : folder) {
                if (itemInfo instanceof BoxFile.Info) {
                    BoxFile.Info fileInfo = (BoxFile.Info) itemInfo;
                    // Do something with the file.
                } else if (itemInfo instanceof BoxFolder.Info) {
                    BoxFolder.Info folderInfo = (BoxFolder.Info) itemInfo;
                    // Do something with the folder.
                }
            }

            if (itemInfo instanceof BoxFolder.Info) {
                BoxFolder childFolder = (BoxFolder) itemInfo.getResource();
                if (depth < MAX_DEPTH) {
                    listFolder(childFolder, depth + 1);
                }
            }
        }
    }

    private static void printSearchResults(Iterable<BoxItem.Info> searchResults) {
        //Crawl the folder
        System.out.println("--==Results==--");

        for (BoxItem.Info itemInfo : searchResults) {
            System.out.println("File Found: " + itemInfo.getName() + ", Owner: " + itemInfo.getOwnedBy().getID());
        }

        System.out.println("");
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
