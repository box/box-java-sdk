package com.box.sdk.samples.appusers.helpers;


import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.box.sdk.*;

/**
 * BoxHelper.  A Helper class for managing Box App User's/
 * Stores and retwieves names and is in the current HTTP Session Object
 * Ceates ne App users or looks up existin app users by name within the enterprise
 * associated with this applications.
 */
public final class BoxHelper {

    static final String ENTERPRISE_ID = ConfigHelper.properties().getProperty("boxEnterpriseId");
    static final String CLIENT_ID = ConfigHelper.properties().getProperty("boxClientId");
    static final String CLIENT_SECRET = ConfigHelper.properties().getProperty("boxClientSecret");
    static final String PRIVATE_KEY_FILE = ConfigHelper.properties().getProperty("boxPrivateKeyFile");
    static final String PRIVATE_KEY_PASSWORD = ConfigHelper.properties().getProperty("boxPrivateKeyPassword");
    static final String PUBLIC_KEY_ID = ConfigHelper.properties().getProperty("boxPublicKeyId");

    static final String BOX_USER_ID_KEY = "box_id";
    static final String BOX_USER_NAME = "name";

    static final int MAX_CACHE_ENTRIES = 100;

    private static IAccessTokenCache accessTokenCache;
    private static JWTEncryptionPreferences jwtEncryptionPreferences;

    /**
     * Private constructor since this is a utility class.
     */
    private BoxHelper() {

    }

    static {
        String privateKey;
        try {
            privateKey = new String(Files.readAllBytes(Paths.get(PRIVATE_KEY_FILE)));
        } catch (Exception ex) {
            throw new BoxAPIException("Unable to read private key file: " + PRIVATE_KEY_FILE);
        }

        jwtEncryptionPreferences = new JWTEncryptionPreferences();
        jwtEncryptionPreferences.setPublicKeyID(PUBLIC_KEY_ID);
        jwtEncryptionPreferences.setPrivateKey(privateKey);
        jwtEncryptionPreferences.setPrivateKeyPassword(PRIVATE_KEY_PASSWORD);
        jwtEncryptionPreferences.setEncryptionAlgorithm(EncryptionAlgorithm.RSA_SHA_256);

        accessTokenCache = new InMemoryLRUAccessTokenCache(MAX_CACHE_ENTRIES);
    }

    /**
     * Uses the statically configured parameters to create Developer Edition connection.
     *
     * @return A new Box Developer Edition API connection with enterprise token leveraging an access token cache.
     */
    public static BoxDeveloperEditionAPIConnection adminClient() {
        try {
            BoxDeveloperEditionAPIConnection adminClient = BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(
                    ENTERPRISE_ID, CLIENT_ID, CLIENT_SECRET, jwtEncryptionPreferences, accessTokenCache);
            System.out.format("returning a BoxDeveloperEditionAPIConnection from adminClient");

            return adminClient;
        } catch (BoxAPIException apiException) {
            System.out.format("Error: BoxDeveloperEditionAPIConnection %s \n\n", apiException.getMessage());
            apiException.printStackTrace();
            throw apiException;
        }
    }

    /**
     * Get a BoxDeveloperEditionAPIConnection that can be used by an App user to access Box.
     *
     * @param userId The UserId of a valid Box App User
     * @return BoxDeveloperEditionAPIConnection new Box Developer Edition connection with App User token
     */
    public static BoxDeveloperEditionAPIConnection userClient(String userId) {
        if (userId == null) { //   session data has expired
            return null;
        }
        System.out.format("userClient called with userId %s \n\n", userId);
        try {
            BoxDeveloperEditionAPIConnection userClient = BoxDeveloperEditionAPIConnection.getAppUserConnection(
                    userId, CLIENT_ID, CLIENT_SECRET, jwtEncryptionPreferences, accessTokenCache);
            return userClient;
        } catch (BoxAPIException apiException) {
            System.out.format("Error: BoxDeveloperEditionAPIConnection %s \n\n", apiException.getMessage());
            apiException.printStackTrace();
            throw apiException;
        }
    }


    /**
     * Prepare an AppUser.  Either log in as an existing Apuser Name in this enterprise
     * or create a new App Users with the given name.
     *
     * Note that this Sample Application DOES NOT DO ANY authorization checks on existing users
     * In a real application it would be up to the application to validate the App users credentials through
     * Auth0 or some similar mecnanism.
     *
     * @param request       The Post request containing a app user name and password
     * @param name          the login name for an existing or new Ap user account
     * @param createAccount boolean. If true allow creation of a new account
     *                      if false look for an existing App User with this name in thie enterprise.
     * @return boolean true if the app user was either found or created successfully, false otherwise
     */
    public static boolean prepareBoxUser(HttpServletRequest request, String name, Boolean createAccount) {

        String boxUserId = boxIdFromRequest(request);
        if (createAccount && (boxUserId != null)) {
            request.setAttribute("error", "As user alredy exists with the name: " + request.getParameter("username"));
            return false;
        }
        if (!createAccount && (boxUserId != null)) {
            request.getSession().setAttribute(BOX_USER_ID_KEY, boxUserId);
            return true;
        }

        //if boxId is still not found that means we need to create a Box app user to get a box id for this pppUser
        if (boxUserId == null && createAccount) {
            CreateUserParams params = new CreateUserParams();
            params.setSpaceAmount(1073741824); //1 GB
            BoxUser.Info user = BoxUser.createAppUser(adminClient(), name, params);
            System.out.format("User created with name %s and id %s  \n\n", user.getLogin(), user.getID());
            boxUserId = user.getID();

            BoxAPIConnection boxUserClient = userClient(boxUserId);

            BoxFolder.getRootFolder(boxUserClient).createFolder("Test Folder");

            InputStream file = request.getServletContext().getResourceAsStream("/assets/test.txt");
            BoxFolder.getRootFolder(boxUserClient).uploadFile(file, "test.txt");
        } else {
            request.setAttribute("error", " Could not find app user: " + request.getParameter("username"));
            return false;
        }
        request.getSession().setAttribute(BOX_USER_ID_KEY, boxUserId);
        request.getSession().setAttribute(BOX_USER_NAME, name);


        return true;
    }

    /**
     * Returns the BoxId for the current App user.  This is either cached in the session attributes
     * or looked up by searching for the User Name in this enterprises' list of users
     *
     * @param request The HTTP request object containing the post parameters
     * @return String the boxId the of the current App User
     */
    public static String boxIdFromRequest(HttpServletRequest request) {
        //first look in the session
        HttpSession session = request.getSession();
        String boxId = (String) session.getAttribute(BOX_USER_ID_KEY);
        //if we don't find in the session then look for an entrprise user with the given name
        if (boxId == null) {
            String appUserName = (String) session.getAttribute(BOX_USER_NAME);
            if (appUserName == null) {  // session has timed out. Force re-login
                return null;
            }


            BoxAPIConnection api = BoxHelper.adminClient();


            Iterable<BoxUser.Info> appUsers = BoxUser.getAllEnterpriseUsers(api);

            //  Iterable<BoxUser.Info> appUsers = adminUser.getAllEnterpriseUsers(api);

            for (BoxUser.Info user : appUsers) {
                if (user.getName().toUpperCase().equals(appUserName.toUpperCase())) {
                    boxId = user.getID();
                    System.out.format("Found User with Login  %s and name %s and  id %s \n",
                            user.getLogin(), user.getName(), user.getID());
                    return boxId;
                } else {
                    System.out.format("Found User with Login  %s and name %s and  id %s \n",
                            user.getLogin(), user.getName(), user.getID());
                }
            }
            System.out.format("Error: Did not find user name %s \n\n", appUserName);
        }
        return boxId;
    }

    /**
     * Set the App User Name attribute in the Session.
     *
     * @param request The HTTP request object containing the post parameters
     * @param name The Box App User;s name
     */
    public static void setBoxAppUserName(HttpServletRequest request, String name) {
        request.getSession().setAttribute(BOX_USER_NAME, name);
    }

    /**
     * Get the App User Name, if any,  from the HTTP Session.
     *
     * @param request The HTTP request object
     * @return App User's Name
     */
    public static String getBoxAppUserName(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(BOX_USER_NAME);
    }

    /**
     * Set the App User's BoxId attribute in the Session.
     *
     * @param request The HTTP request object
     * @param id the Boxid of the App User for this Session.
     */
    public static void setBoxAppUserId(HttpServletRequest request, String id) {
        request.getSession().setAttribute(BOX_USER_ID_KEY, id);
    }

    /**
     * Get the App User's BoxId, if any,  from the HTTP Session.
     *
     * @param request The HTTP request object
     * @return App User's BoxId
     */
    public static String getBoxAppUserId(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(BOX_USER_ID_KEY);
    }
}
