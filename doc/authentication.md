Authentication
==============

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
- [Manual Token Creation](#manual-token-creation)
- [As User](as-user)
- [Token Exchange](#token-exchange)
- [Revoke Token](#revoke-token)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->
Ways to Authenticate
--------------------

### Developer Token

The fastest way to get started using the API is with developer tokens. A
developer token is simply a short-lived access token that cannot be refreshed
and can only be used with your own account. Therefore, they're only useful for
testing an app and aren't suitable for production. You can obtain a developer
token from your application's [developer
console](https://cloud.app.box.com/developers/services).

The following example creates an API connection with a developer token:

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
[API documentation](https://github.com/box/box-node-sdk/blob/master/docs/authentication.md#app-user-authentication)
for detailed instruction on how to use app auth. 

App User example: 
```java
JWTEncryptionPreferences jwtPreferences = new JWTEncryptionPreferences();
jwtPreferences.setPublicKeyID("PUBLIC-KEY-ID");
jwtPreferences.setPrivateKeyPassword("PRIVATE-KEY-PASSWORD");
jwtPreferences.setPrivateKey("PRIVATE-KEY");
jwtPreferences.setEncryptionAlgorithm(EncryptionAlgorithm.RSA_SHA_256);

BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppUserConnection("USER-ID", "CLIENT-ID",
"CLIENT-SECRET", jwtPreferences);

BoxUser.Info userInfo = BoxUser.getCurrentUser(api).getInfo();
```

Server authentication allows your application to authenticate itself with the Box API for a given enterprise. A 
[Service Account](https://developer.box.com/v2.0/docs/service-account) always exists for a Box application. It is important to
note that a Service Account is separate from the Box accounts of the applicaton developer and the enterprise admin of any enterprise that has
authorized the app, meaning files stored in that account are not accessible in any other account by default. 

Service Account example:
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

Manual Token Creation
---------------------

In certain advanced scenarios, you may want to obtain an access and refresh
token yourself through manual calls to the API. In this case, you can create an
API connection with the tokens directly.

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-CLIENT-ID",
    "YOUR-CLIENT-SECRET", "YOUR-ACCESS-TOKEN", "YOUR-REFRESH-TOKEN");
```

As User
-------

The purpose of as user is to be used by enterprise administrators to make API calls on behalf of their managed users. 
This can also be used by a Service Account to make API calls for managed users or app users. 

In order to invoke as user calls you can use 

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-ACCESS-TOKEN");
api.asUser("USER-ID");
```

Once you are done making calls on behalf of a managed user or app user you can switch back to the admin or service account with

```java
api.asSelf();
```

Token Exchange
--------------

You can exchange a API connection's access token for one with a lower scope, in order to restrict the permissions
or to pass to a less secure location (e.g. a browser-based app). This is useful if you want to use the 
[Box UI Kits](https://developer.box.com/docs/box-ui-elements), since they generally do not need full read/write 
permissions to run. 

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

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-ACCESS-TOKEN");
api.revokeToken();
```

