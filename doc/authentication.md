# Authentication

The Box API uses OAuth2 for authentication, which can be difficult to implement.
The SDK makes it easier by providing classes that handle obtaining tokens and
automatically refreshing them.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [Ways to Authenticate](#ways-to-authenticate)
  - [Developer Token](#developer-token)
  - [Server Authentication with JWT](#server-authentication-with-jwt)
  - [Standard 3-Legged Oauth 2.0](#standard-3-legged-oauth-20)
  - [Box View Authentication with App Token](#box-view-authentication-with-app-token)
  - [Client Credentials Grant](#client-credentials-grant)
- [Manual Token Creation](#manual-token-creation)
- [As User](#as-user)
- [Token Exchange](#token-exchange)
- [Revoke Token](#revoke-token)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->
## Ways to Authenticate

### Developer Token

The fastest way to get started using the API is with developer tokens. A
developer token is simply a short-lived access token that cannot be refreshed
and can only be used with your own account. Therefore, they're only useful for
testing an app and aren't suitable for production. You can obtain a developer
token from your application's [developer
console](https://cloud.app.box.com/developers/services).

The following example creates an API connection with a developer token:

<!-- sample x_auth init_with_dev_token -->
```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-DEVELOPER-TOKEN");
```

### Server Authentication with JWT


App Users allows your application to provision and control Box accounts that do not have an associated login
and can only be accessed through the Content API by the controlling application. An App User is a Box account that 
belongs to your Box Platform application and not an end-user of Box, like a [managed user](https://developer.box.com/v2.0/reference#user-object)
It is important to emphasize that unlike typical Box accounts, these accounts do not have an associated login and can only be accessed through the Box API. 

You may authenticate as the service 
account to provision and manage users, or as an individual app user to make calls as that user. See the 
[API documentation](https://github.com/box/box-node-sdk/blob/main/docs/authentication.md#app-user-authentication)
for detailed instruction on how to use app auth. 

The Java SDK also has a convenient helper function `BoxConfig.readFrom()` to assist in constructing an API connection.
The `readFrom()` method takes in a stream constructed by the JSON config downloaded from the Developer Console seen 
[here](https://developer.box.com/docs/setting-up-a-jwt-app#section-use-an-application-config-file). Once a `BoxConfig`
object has been created you can use that to create an API connection.

<!-- sample x_auth init_with_jwt_enterprise -->
```java
Reader reader = new FileReader("src/example/config/config.json");
BoxConfig boxConfig = BoxConfig.readFrom(reader);

BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(boxConfig);
```

It is also possible to get an API connection for an app user or managed user by doing something like this:

<!-- sample x_auth init_with_jwt_with_user_id -->
```java
Reader reader = new FileReader("src/example/config/config.json");
BoxConfig boxConfig = BoxConfig.readFrom(reader);

InMemoryLRUAccessTokenCache accessTokenCache = new InMemoryLRUAccessTokenCache(100);
BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getUserConnection(userId, boxConfig, accessTokenCache);
```

However, if you would like to do a manual set up then that is also possible with the below options.

App User example: 
```java
JWTEncryptionPreferences jwtPreferences = new JWTEncryptionPreferences();
jwtPreferences.setPublicKeyID("PUBLIC-KEY-ID");
jwtPreferences.setPrivateKeyPassword("PRIVATE-KEY-PASSWORD");
jwtPreferences.setPrivateKey("PRIVATE-KEY");
jwtPreferences.setEncryptionAlgorithm(EncryptionAlgorithm.RSA_SHA_256);

InMemoryLRUAccessTokenCache accessTokenCache = new InMemoryLRUAccessTokenCache(100);
BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection
    .getUserConnection("USER-ID", "CLIENT-ID","CLIENT-SECRET", jwtPreferences, accessTokenCache);

BoxUser.Info userInfo = BoxUser.getCurrentUser(api).getInfo();
```

Server authentication allows your application to authenticate itself with the Box API for a given enterprise. A 
[Service Account](https://developer.box.com/v2.0/docs/service-account) always exists for a Box application. It is important to
note that a Service Account is separate from the Box accounts of the applicaton developer and the enterprise admin of any enterprise that has
authorized the app, meaning files stored in that account are not accessible in any other account by default. 

Service Account example:
<!-- sample x_auth init_with_jwt_enterprise_with_config -->
```java
JWTEncryptionPreferences jwtPreferences = new JWTEncryptionPreferences();
jwtPreferences.setPublicKeyID("PUBLIC-KEY-ID");
jwtPreferences.setPrivateKeyPassword("PRIVATE-KEY-PASSWORD");
jwtPreferences.setPrivateKey("PRIVATE-KEY");
jwtPreferences.setEncryptionAlgorithm(EncryptionAlgorithm.RSA_SHA_256);

BoxConfig boxConfig = new BoxConfig("YOUR-CLIENT-ID", "YOUR-CLIENT-SECRET", "ENTERPRISE-ID", jwtPreferences);

BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(boxConfig);
```

### Standard 3-Legged Oauth 2.0

Using an auth code is the most common way of authenticating with the Box API.
Your application must provide a way for the user to login to Box (usually with a
browser or web view) in order to obtain an auth code.

After a user logs in and grants your application access to their Box account,
they will be redirected to your application's `redirect_uri` which will contain
an auth code. This auth code can then be used along with your client ID and
client secret to establish an API connection.

<!-- sample post_token -->
```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-CLIENT-ID",
    "YOUR-CLIENT-SECRET", "YOUR-AUTH-CODE");
```

### Box View Authentication with App Token

Allows applications that do not own content stored in Box (e.g. app-owned content) to be able to use Box as a service
provider rather than a content store. This is currently mostly used for previewing items. For scopes you can choose 
between "item_preview", "item_upload", or "item_delete". See the 
[Getting Started with the New Box View](https://developer.box.com/docs/getting-started-with-new-box-view) for detailed
instruction.

<!-- sample x_auth init_with_app_token -->
```java
BoxTransactionalAPIConnection api = new BoxTransactionalAPIConnection("YOUR-ACCESS-TOKEN");
```

You can also request a specific scope of the transaction token by passing in: "item_preview", "item_upload", or "item_delete". 

```java
BoxAPIConnection api = BoxTransactionalAPIConnection.getTransactionConnection("YOUR-ACCESS-TOKEN", "item_preview");
```

Lastly, you can choose to specify a resource to generate a token for with. If you're passing a token down to your client
this is a great way to restrict access on that token in turn locking down what the token has access to. 

```java
BoxAPIConnection api = BoxTransactionalAPIConnection.getTransactionConnection("YOUR-ACCESS-TOKEN", "item_preview", 
"https://api.box.com/2.0/files/YOUR-FILE-ID");
```

### Client Credentials Grant
Allows you to obtain an access token by having client credentials and secret with enterprise or user ID, 
which allows you to work using service or user account.

#### Obtaining Service Account token

To obtain service account you will have to provide enterprise ID with client id and secret
```java
BoxCCGAPIConnection api = BoxCCGAPIConnection.applicationServiceAccountConnection(
    "client_id",
    "client_secret",
    "enterprise_id"
);
```

#### Obtaining User token

To obtain user account you will have to provide user ID with client id and secret
```java
BoxCCGAPIConnection api = BoxCCGAPIConnection.userConnection(
    "client_id",
    "client_secret",
    "user_id"
);
```

The `BoxCCGAPIConnection` works the same way as the `BoxAPIConnection` so to for example get root folder you can do:
```java
BoxCCGAPIConnection api = BoxCCGAPIConnection.userConnection(
    "client_id",
    "client_secret",
    "user_id"
);
BoxFolder root = BoxFolder.getRootFolder(api);
```

## Manual Token Creation

In certain advanced scenarios, you may want to obtain an access and refresh
token yourself through manual calls to the API. In this case, you can create an
API connection with the tokens directly.

<!-- sample x_auth init_with_access_and_refresh_token -->
```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-CLIENT-ID",
    "YOUR-CLIENT-SECRET", "YOUR-ACCESS-TOKEN", "YOUR-REFRESH-TOKEN");
```

## As User

The purpose of as user is to be used by enterprise administrators to make API calls on behalf of their managed users. 
This can also be used by a Service Account to make API calls for managed users or app users. This is only meant to be used
for `BoxAPIConnection`s and not `BoxDeveloperEditionAPIConnection`s.

In order to invoke as user calls you can use 

<!-- sample x_auth init_with_as_user_header -->
```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-ACCESS-TOKEN");
api.asUser("USER-ID");
```

Once you are done making calls on behalf of a managed user or app user you can switch back to the admin or service account with

<!-- sample x_auth init_with_as_self -->
```java
api.asSelf();
```
### Client Credentials Grant
One important thing is that you can use user impersonation ony with service account API:
```java
BoxCCGAPIConnection api = BoxCCGAPIConnection.userConnection(
    "client_id",
    "client_secret",
    "user_id"
);
api.asUser("user_id")
```
Calling `asUser` or `asSelf` on user connection will fail with `IllegalStateException`.

## Token Exchange

You can exchange a API connection's access token for one with a lower scope, in order to restrict the permissions
or to pass to a less secure location (e.g. a browser-based app). This is useful if you want to use the 
[Box UI Kits](https://developer.box.com/docs/box-ui-elements), since they generally do not need full read/write 
permissions to run. 

<!-- sample post_oauth2_token downscope_token -->
```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-ACCESS-TOKEN");

String resource = "https://api.box.com/2.0/files/RESOURCE-ID";
List<String> scopes = new ArrayList<String>();
scopes.add("item_preview");
scopes.add("item_content_upload");

ScopedToken token = api.getLowerScopedToken(scopes, resource);
```

The above example will downscope an access token to only allow for previewing an item and uploading an item. 

Revoke Token
------------

At any point if you wish to revoke your tokens you can do so by calling the following. 

<!-- sample post_revoke -->
```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-ACCESS-TOKEN");
api.revokeToken();
```

